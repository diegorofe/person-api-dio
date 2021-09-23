package com.digitalinnovation.personapi.service;

import ch.qos.logback.core.hook.DelayingShutdownHook;
import com.digitalinnovation.personapi.dto.MessageResponseDTO;
import com.digitalinnovation.personapi.dto.request.PersonDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.mapper.PersonMapper;
import com.digitalinnovation.personapi.repository.PersonRepository;
import com.digitalinnovation.personapi.service.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //especifica um serviço
public class PersonService {


    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){

        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Create Person " + savedPerson.getFirstName())
                .build();
    }
    public List<PersonDTO> listAll(){
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyExists(id);
        // Optional<Person> optionalPerson = personRepository.findById(id);
//            if(optionalPerson.isEmpty()){
//                throw  new PersonNotFoundException(id);
//            }

             return personMapper.toDTO(person);
    }


    public void delete(Long id) throws PersonNotFoundException {
verifyExists(id);
personRepository.deleteById(id);
    }


    private Person verifyExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        }
}
