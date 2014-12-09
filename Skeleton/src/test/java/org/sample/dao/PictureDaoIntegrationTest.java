package org.sample.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.model.Picture;
import org.sample.model.dao.PictureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/springMVC.xml","file:src/main/webapp/WEB-INF/config/springData.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PictureDaoIntegrationTest {
	@Autowired
	PictureDao pictureDao;

	@Test
	public void testFindByFileName() {
		String PATH =  "/Skeleton/src/main/webapp/img/picture.jpg";
    	
    	Picture picture = new Picture();
    	picture.setFilePath(PATH);
    	
        picture =  pictureDao.save(picture);   
        Picture findPicture = pictureDao.findByFileName(PATH);
        assertEquals(findPicture.getFilePath(), PATH);
	}

}
