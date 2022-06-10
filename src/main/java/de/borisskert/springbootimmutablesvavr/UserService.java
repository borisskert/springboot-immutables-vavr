package de.borisskert.springbootimmutablesvavr;

import de.borisskert.springbootimmutablesvavr.properties.ApplicationProperties;
import de.borisskert.springbootimmutablesvavr.properties.UsersProperties.DefaultUser;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final ApplicationProperties properties;
    private List<User> allUsers;

    @Autowired
    public UserService(ApplicationProperties properties) {
        this.properties = properties;
        allUsers = createAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    private List<User> createAllUsers() {
        final DefaultUser defaultuser = properties.users().defaultUser();
        return List.of(defaultuser.toUser());
    }

    public void insert(NewUser user) {
        allUsers = allUsers.append(user.toUser());
    }

    public Option<User> getById(UUID userId) {
        return allUsers.find(user -> user.id().equals(userId));
    }
}
