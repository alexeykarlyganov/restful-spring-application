package org.alexeykarlyganov.rest.controllers;

import java.util.List;

import org.alexeykarlyganov.rest.errors.UserNotFoundException;
import org.alexeykarlyganov.rest.models.User;

import org.alexeykarlyganov.rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping()
    public User createNewUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
