package de.borisskert.springbootimmutablesvavr;

import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersResource {

    private final UserService service;

    @Autowired
    public UsersResource(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @PostMapping
    public void put(@RequestBody NewUser user) {
        service.insert(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable("userId") UUID userId) {
        return ResponseEntity.of(
                service.getById(userId).toJavaOptional()
        );
    }
}
