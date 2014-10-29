package org.sample.controller.service;

import org.sample.controller.pojos.EnquiryForm;
import org.sample.model.Enquiry;

public interface EnquiryService {

    public EnquiryForm submit(EnquiryForm enquiryForm);
    
    public Iterable<Enquiry> findSentEnquiries();
    
    public Iterable<Enquiry> findReceivedEnquiries();


}
