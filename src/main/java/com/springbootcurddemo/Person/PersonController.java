package com.springbootcurddemo.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  @Autowired
  PersonService personService;

  @GetMapping("/")
  public String getPersonByFirstName(@RequestBody String firstName) {
    return personService.findPersonByFirstName(firstName);
  }

  @GetMapping("/all")
  public String getAllPersons() {
    return personService.getAllPersons();
  }

  @PostMapping("/")
  public void createPerson(@RequestBody Person person) {
    personService.createPerson(person);
  }

  @PutMapping("/")
  public Person updatePerson(@RequestParam String firstName, @RequestBody Person person) {
    return personService.updatePerson(firstName, person);
  }

  @DeleteMapping("/")
  public void deletePersonByFirstName(@RequestBody String firstName) {
    personService.deletePersonByFirstName(firstName);
  }
}
