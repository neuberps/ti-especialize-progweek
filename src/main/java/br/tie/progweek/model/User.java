package br.tie.progweek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Document(collection = "user")
public class User implements Serializable {

	@Id
	private String id;
	private String name;
	private String email;
	private String cel;
	private String created;
	private String updated;

}
