package com.nbicocchi.addressbook.api;

import com.nbicocchi.addressbook.model.Person;
import com.nbicocchi.addressbook.model.PersonNotFoundException;
import com.nbicocchi.addressbook.persistence.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Person> findAll() {
        return repository.findAllByOrderByLastNameAscFirstNameAscIdAsc();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable long id) {
        positive(id);
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody PersonInput input) {
        Person saved = repository.save(input.toPerson(null));
        return ResponseEntity.created(URI.create("/api/persons/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable long id, @RequestBody PersonInput input) {
        positive(id);
        Person replacement = input.toPerson(id);
        requireExisting(id);
        return repository.save(replacement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        positive(id);
        requireExisting(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void requireExisting(long id) {
        if (!repository.existsById(id)) {
            throw new PersonNotFoundException(id);
        }
    }

    private static void positive(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
    }
}
