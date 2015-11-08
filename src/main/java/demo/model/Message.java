package demo.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="Message")
public class Message {
	@Id
	private String id;
	private String code;
	private String locale;
	private String value;
	private org.bson.types.BSONTimestamp update_time;
	
	public Message(String locale, String value) {
		super();
		this.locale = (locale == null)?"ja":locale;
		this.value = (value == null)?"":value;
		
	}
	public Message() {
		super();
	}
}
