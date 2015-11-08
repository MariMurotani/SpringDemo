package demo.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="CMS")
public class CMS {
	@Id
	private String id;
	private String path;
	private String name;
	private String ext=null;
	private String content_type=null;
	private Date created_date=new Date();
	private Date release_date=null;
	private Date finish_date=null;
	private boolean active=true;
	
	public CMS(String path, String name) {
		super();
		this.path = path;
		this.name = name;
	}
	public CMS() {
		super();
	}
}
