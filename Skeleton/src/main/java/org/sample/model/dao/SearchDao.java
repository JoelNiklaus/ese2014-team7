package org.sample.model.dao;

import org.sample.model.Search;
import org.springframework.data.repository.CrudRepository;

public interface SearchDao extends CrudRepository<Search,Long> {
}
