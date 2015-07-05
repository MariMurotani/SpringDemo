package demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status {
	private final String status;
	public Status(String status) {
		this.status = status;
	}
}
