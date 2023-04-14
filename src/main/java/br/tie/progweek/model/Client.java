package br.tie.progweek.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "client")
public class Client implements Serializable {

	@Id
	private String id;
	private String name;
	private String email;

	private String cel;

	private String cpf;
	private String company;
	private String created;
	private String updated;

}
