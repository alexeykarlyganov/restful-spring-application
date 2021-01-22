package org.alexeykarlyganov.rest.controllers;

import java.util.List;

import org.alexeykarlyganov.rest.errors.UserNotFoundException;
import org.alexeykarlyganov.rest.models.User;

import org.alexeykarlyganov.rest.repositories.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping("/users")
    public User createNewUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    public User editOrCreateUser(@RequestBody User request_user, @PathVariable Long id) {
        return repository.findById(id)
            .map(user -> {
                user.setName(request_user.getName());
                user.setEmail(request_user.getEmail());

                return repository.save(user);
            })
            .orElseGet(() -> {
                request_user.setId(id);

                return repository.save(request_user);
            });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
