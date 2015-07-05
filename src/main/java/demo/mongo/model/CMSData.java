package demo.mongo.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="CMSData")
public class CMSData {
	@Id
	private String id;
	private String txtdata = null;
	private Byte[] bindata = null;
	
	public CMSData(String id, Byte[] data) {
		super();
		this.id = id;
		this.bindata = data;
	}	
	
	public CMSData(String id, String data) {
		super();
		this.id = id;
		this.txtdata = data;
	}	
}
