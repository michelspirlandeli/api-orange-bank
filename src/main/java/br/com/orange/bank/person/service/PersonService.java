package br.com.orange.bank.person.service;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.orange.bank.person.dto.PersonDTO;
import br.com.orange.bank.person.entity.Person;
import br.com.orange.bank.person.exceptions.ResourceNotFoundException;
import br.com.orange.bank.person.repository.PersonRepository;
import br.com.orange.bank.person.service.exceptions.DataBaseException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Transactional(readOnly = true)
	public Page<PersonDTO> findAllPaged(PageRequest pageRequest) {
		Page<Person> list = personRepository.findAll(pageRequest);
		return list.map(x -> new PersonDTO(x));
	}

	@Transactional(readOnly = true)
	public PersonDTO findById(Long id) {
		Optional<Person> obj = personRepository.findById(id);
		Person entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new PersonDTO(entity);
	}

	@Transactional
	public PersonDTO insert(PersonDTO dto) {
		Person entity = new Person();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setCpfOrCnpj(dto.getCpfOrCnpj());
		entity.setDateOfBirth(dto.getDateOfBirth());
		personRepository.save(entity);
		return new PersonDTO(entity);
	}

	@Transactional
	public PersonDTO update(Long id, PersonDTO dto) {
		try {
			Person entity = personRepository.getOne(id);
			entity.setName(dto.getName());
			entity.setEmail(dto.getEmail());
			entity.setCpfOrCnpj(dto.getCpfOrCnpj());
			entity.setDateOfBirth(dto.getDateOfBirth());
			entity = personRepository.save(entity);
			return new PersonDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not foiund" + id);
		}
	}

	public void delete(Long id) {
		try {
			personRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("id not found" + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integration violation");
		}

	}

}
