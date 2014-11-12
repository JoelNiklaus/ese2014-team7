package org.sample.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class PictureManager {
	
	public ArrayList<String> uploadMultipleFile(String path, String filename, MultipartFile[] files) {
		ArrayList<String> names = new ArrayList<String>();
		
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			if (file.getSize() != 0) {
				try {
					byte[] bytes = file.getBytes();
					File dir = new File(path);
					if (!dir.exists())
						dir.mkdirs();
					BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(new File(dir.getAbsolutePath()+File.separator+filename+".jpeg"/*+file.getOriginalFilename()*/)));
					outStream.write(bytes);
					outStream.close();
					names.add(dir.getAbsolutePath()+File.separator+filename+file.getOriginalFilename());
				} catch (Exception e) {
					e.getMessage();
	            }
			}
		}
		return names;
	}
	
	/**
	 * Converts picture file path into byte array.
	 * 
	 * @param picture 	file path
	 * @return			byte array of file path
	 */
	public byte[] getByteArrayFromPath(String picture) {
		File file = new File(picture);
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(file);
			byte fileContent[] = new byte[(int)file.length()];
	 		fin.read(fileContent);
	 		return fileContent;
	 	} catch (FileNotFoundException e) {
	 		System.out.println("File not found" + e);
	 	} catch (IOException ioe) {
	 		System.out.println("Exception while reading file " + ioe);
	 	} finally {
	 		try {
	 			if (fin != null) {
	 				fin.close();
	 			}
	 		} catch (IOException ioe) {
	 			System.out.println("Error while closing stream: " + ioe);
	 		}
	 	}
		return null;}
	}

