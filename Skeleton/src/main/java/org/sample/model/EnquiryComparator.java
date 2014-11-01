package org.sample.model;

import java.util.Comparator;

public class EnquiryComparator implements Comparator<Enquiry> {

	public int compare(Enquiry arg0, Enquiry arg1) {
		return (-1) * arg0.getTimestamp().compareTo(arg1.getTimestamp());
	}

}
