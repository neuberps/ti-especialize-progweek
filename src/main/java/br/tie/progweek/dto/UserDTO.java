package br.tie.progweek.dto;

import br.tie.progweek.model.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
public class UserDTO implements Serializable {
	private String id;
	@NotBlank
	private String name;
	@NotBlank @Email
	private String email;
	@NotBlank
	private String cel;
	private String created;
	private String updated;

	public User toUser() {
		return User.builder()
				.id(id)
				.name(name)
				.email(email.toLowerCase())
				.cel(cel).build();
	}

}
