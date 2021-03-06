package com.digitalinnovation.personapi;

import com.digitalinnovation.personapi.dto.MessageResponseDTO;
import com.digitalinnovation.personapi.dto.request.PersonDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.repository.PersonRepository;
import com.digitalinnovation.personapi.service.PersonService;
import com.digitalinnovation.personapi.service.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/V1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
    return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll(){

        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id ) throws PersonNotFoundException{
        personService.delete(id);
    }


    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@ PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
return personService.updateById(id, personDTO);
    }

}
