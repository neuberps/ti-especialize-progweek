package br.tie.progweek.model;

import br.tie.progweek.dto.UserDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document(collection = "users")
public class User implements Serializable {

	@Id
	private String id;
	private String name;
	private String email;
	private String cel;
	private String created;
	private String updated;

	public UserDTO toUserDTO() {
		return UserDTO.builder()
				.id(id)
				.name(name)
				.email(email)
				.cel(cel)
				.created(created)
				.updated(updated)
				.build();
	}
}
