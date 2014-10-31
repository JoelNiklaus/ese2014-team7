package org.sample.model.dao;

import org.sample.model.Picture;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

public interface PictureDao extends CrudRepository<Picture,Long> {
}
