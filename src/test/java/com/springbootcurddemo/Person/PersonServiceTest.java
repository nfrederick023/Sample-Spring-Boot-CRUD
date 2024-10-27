package com.springbootcurddemo.Person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTest {
  @Mock
  private PersonRepository personRepository;
  @InjectMocks
  private PersonService personService;

  @Test
  public void shouldCreatePerson() {
    Person person = new Person("Noah", "Frederick");
    personService.createPerson(person);
    verify(personRepository).save(person);
  }

  @Test
  public void shouldGetAllPersons() {
    String person1FirstName = "Noah";
    String person1LastName = "Frederick1";

    String person2FirstName = "Willow";
    String person2LastName = "Frederick2";

    Person person1 = new Person(person1FirstName, person1LastName);
    Person person2 = new Person(person2FirstName, person2LastName);
    List<Person> personList = new ArrayList<Person>();

    personList.add(person1);
    personList.add(person2);

    when(personRepository.findAll()).thenReturn(personList);
    assertThat(personService.getAllPersons())
        .isEqualTo("First Name: " + person1FirstName + " Last Name: " + person1LastName + " First Name: "
            + person2FirstName + " Last Name: " + person2LastName);
  }

  @Test
  public void shouldGetPersonByFirstName() {
    String firstName = "Noah";
    String lastName = "Frederick";
    Person person = new Person(firstName, "Frederick");
    when(personRepository.findByFirstName(firstName)).thenReturn(person);
    assertThat(personService.findPersonByFirstName(firstName))
        .isEqualTo("First Name: " + firstName + " Last Name: " + lastName);
  }

  @Test
  public void shouldUpdatePerson() {
    String oldFirstName = "Noah";
    String newFirstName = "Willow";
    Person personOld = new Person(oldFirstName, "Frederick");
    Person personNew = new Person(newFirstName, "Frederick");

    when(personRepository.findByFirstName(oldFirstName)).thenReturn(personOld);
    personService.updatePerson(oldFirstName, personNew);
    verify(personRepository).save(any());
  }

  @Test
  public void shouldDeletePerson() {
    String firstName = "Noah";
    personService.deletePersonByFirstName(firstName);
    verify(personRepository).deleteByFirstName(firstName);
  }
}
