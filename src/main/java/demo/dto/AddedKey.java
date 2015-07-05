package demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddedKey {
	private final String key;
	public AddedKey(String key) {
		this.key = key;
	}
}