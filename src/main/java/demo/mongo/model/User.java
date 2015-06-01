package demo.mongo.model;


import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User {

	@Id
	private String id;

	String username;

	String password;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	
    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', password='%s']",
                id, username, password);
    }

}