package br.tie.progweek.dto;

import br.tie.progweek.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

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
