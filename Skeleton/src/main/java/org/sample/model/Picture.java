package org.sample.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Embeddable
public class Picture {

	@Id
	@GeneratedValue
	private Long id;
	private String filePath;
	@ManyToOne
	private Ad ad;
	private boolean isMainPic;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public boolean getIsMainPic() {
		return isMainPic;
	}

	public void setIsMainPic(boolean isMainPic) {
		this.isMainPic = isMainPic;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	} 
}
