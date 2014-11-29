package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Picture {

	@Id
	@GeneratedValue
	private Long id;
	private String filePath;
	private String fileName;

	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
