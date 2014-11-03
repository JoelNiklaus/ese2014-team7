package org.sample.model.dao;

import org.sample.model.Bookmark;
import org.sample.model.Enquiry;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkDao extends CrudRepository<Bookmark,Long> {
}
