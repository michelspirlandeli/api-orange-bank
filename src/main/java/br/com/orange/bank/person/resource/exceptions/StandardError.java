package br.com.orange.bank.person.resource.exceptions;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
