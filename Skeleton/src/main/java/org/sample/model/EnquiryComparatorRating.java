package org.sample.model;

import java.util.Comparator;

public class EnquiryComparatorRating implements Comparator<Enquiry> {

	public int compare(Enquiry arg0, Enquiry arg1) {
		if(arg0.getRating() > arg1.getRating())
			return -1;
		else if(arg0.getRating() == arg1.getRating())
			return 0;
		else
			return 1;
	}

}
