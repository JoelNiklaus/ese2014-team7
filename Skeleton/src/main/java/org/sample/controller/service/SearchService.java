package org.sample.controller.service;

import org.sample.controller.pojos.SearchForm;
import org.sample.model.Search;
import org.sample.model.User;

public interface SearchService {

    public Search saveSearch(SearchForm searchForm);
        
    public Iterable<Search> findSearches(User user);
    
    public boolean alreadySaved(User user, Long searchId);
    
    public Search removeSearch(Search search);
    
    // TODO Maybe needs to be moved in AdController
    /**
     * Notifies User when new Ad is created that matches his saved search criteria.
     */
    //public Search notifyUser();
}
