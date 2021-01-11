package br.com.orange.bank.person.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.orange.bank.person.entity.Person;
import br.com.orange.bank.person.validation.UniqueValue;
import lombok.Data;

@Data
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;

	@UniqueValue(fieldName = "email", domainClass = Person.class)
	private String email;

	@UniqueValue(fieldName = "cpfOrCnpj", domainClass = Person.class)
	private String cpfOrCnpj;

	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dateOfBirth;

	@JsonCreator
	public PersonDTO(Long id, String name, String email, String cpfOrCnpj, LocalDate dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.dateOfBirth = dateOfBirth;
	}

	public PersonDTO(Person entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.cpfOrCnpj = entity.getCpfOrCnpj();
		this.dateOfBirth = entity.getDateOfBirth();
	}

}
