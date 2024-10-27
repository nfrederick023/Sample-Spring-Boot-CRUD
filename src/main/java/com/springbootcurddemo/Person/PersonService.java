package com.springbootcurddemo.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  PersonRepository personRepository;

  public void createPerson(Person person) {
    personRepository.save(person);
  }

  public String getAllPersons() {
    String listOfPersons = "";

    for (Person person : personRepository.findAll()) {
      listOfPersons += person.toString() + " ";
    }

    listOfPersons = listOfPersons.trim();

    return listOfPersons;
  }

  public String findPersonByFirstName(String firstName) {
    return personRepository.findByFirstName(firstName).toString();
  }

  public Person updatePerson(String firstName, Person person) {
    Person existingPerson = personRepository.findByFirstName(firstName);
    existingPerson.setFirstName(person.getFirstName());
    existingPerson.setLastName(person.getLastName());
    return personRepository.save(existingPerson);
  }

  public void deletePersonByFirstName(String firstName) {
    personRepository.deleteByFirstName(firstName);
  }
}
