package demo.mongo.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="User")
public class User {

	@Id
	private String id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email1;
	private String email2;
	private String mobmail;
	private String tel1;
	private String tel2;
	
	public User(String username, String password) {
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