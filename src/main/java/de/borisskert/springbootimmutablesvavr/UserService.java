package de.borisskert.springbootimmutablesvavr;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final List<User> allUsers;

    public UserService() {
        allUsers = createAllUsers();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(allUsers);
    }

    private static List<User> createAllUsers() {
        User user = ImmutableUser.builder()
                .id(UUID.fromString("817b12c3-6747-403c-9d01-20e4e9dcc2eb"))
                .username("admoney")
                .firstname("Adrian")
                .lastname("Money")
                .email("adrian.money@mail.com")
                .birthdate(LocalDate.of(1985, 4, 23))
                .build();

        List<User> list = new ArrayList<>();
        list.add(user);

        return list;
    }

    public void insert(NewUser user) {
        allUsers.add(user.toUser());
    }

    public Optional<User> getById(UUID userId) {
        return allUsers.stream()
                .filter(user -> user.id().equals(userId))
                .findFirst();
    }
}
