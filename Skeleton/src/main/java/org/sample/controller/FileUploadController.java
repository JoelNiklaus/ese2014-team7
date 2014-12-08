package org.sample.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.ServletContext;

import org.sample.model.Picture;
import org.sample.model.dao.PictureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class FileUploadController { 

	@Autowired private ServletContext servletContext;
	@Autowired private PictureDao pictureDao;

    /**
     * 
     * @return dummy page 
     */
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    /**
     * Upload an Image to /img/ad/
     * 
     * @param name of the file
     * @param file the file to upload
     * @return id of the uploaded file or nothing when it failed. 
     */
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
           @RequestParam("file") MultipartFile file){
    	
    	String fileName = new Date().getTime() +  "_" + file.getOriginalFilename();
    	String path = servletContext.getRealPath("/")+"/img/ad/";
    	String fullPath = path + fileName;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
                stream.write(bytes);
                stream.close();
                
                // Save the picture 
                Picture picture = new Picture();
                picture.setFilePath(path);
                picture.setFileName(fileName);

        		pictureDao.save(picture);
        		picture = pictureDao.findByFileName(picture.getFileName());
        
                return picture.getId().toString();
            } catch (Exception e) {
                return "";
            }
        } else {
            return "";
        }
    }
    
    /**
     * Removes the picture with the given id
     * @param id
     * @return
     */
    @RequestMapping(value="/removePicture", method=RequestMethod.POST)
    public @ResponseBody String removePicutre(@RequestParam("id") String id) {
    	System.out.println(id);
    	Picture picture = pictureDao.findOne(new Long(id));
    	File file = new File(picture.getFilePath() + picture.getFileName());
    	file.delete();
    	pictureDao.delete(new Long(id));
        return "";
    }
    
    /**
     * Returns the filename from a picture by id
     * @param id of the picture
     * @return filename 
     */
    @RequestMapping(value="/getImgUrl", method=RequestMethod.POST)
    public @ResponseBody String getImgUrl(@RequestParam("id") String id) {
    	Picture picture = pictureDao.findOne(new Long(id));
    	if(picture != null)
    		return picture.getFileName();
    	else 
    		return "";
    }

}