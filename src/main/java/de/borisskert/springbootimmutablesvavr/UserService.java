package de.borisskert.springbootimmutablesvavr;

import de.borisskert.springbootimmutablesvavr.properties.ApplicationProperties;
import de.borisskert.springbootimmutablesvavr.properties.ApplicationProperties.UsersProperties.DefaultUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final ApplicationProperties properties;
    private final List<User> allUsers;

    @Autowired
    public UserService(ApplicationProperties properties) {
        this.properties = properties;
        allUsers = createAllUsers();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(allUsers);
    }

    private List<User> createAllUsers() {
        List<User> list = new ArrayList<>();
        final DefaultUser defaultuser = properties.users().defaultUser();

        list.add(defaultuser.toUser());

        return list;
    }

    public void insert(NewUser user) {
        allUsers.add(user.toUser());
    }

    public Optional<User> getById(UUID userId) {
        return allUsers.stream().filter(user -> user.id().equals(userId)).findFirst();
    }
}
