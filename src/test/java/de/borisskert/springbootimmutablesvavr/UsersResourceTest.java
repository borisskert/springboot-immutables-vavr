package de.borisskert.springbootimmutablesvavr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class UsersResourceTest {

    @Autowired
    private WebTestClient client;

    @Test
    void shouldGetAllUsers() throws Exception {
        client.get()
                .uri("/users")
                .exchange()
                .expectBodyList(User.class)
                .contains(
                        ImmutableUser.builder()
                                .id(UUID.fromString("817b12c3-6747-403c-9d01-20e4e9dcc2eb"))
                                .username("admoney")
                                .firstname("Adrian")
                                .lastname("Money")
                                .email("adrian.money@mail.com")
                                .birthdate(LocalDate.of(1985, 4, 23))
                                .build()
                );
    }

    @Test
    void shouldPostNewUser() throws Exception {
        client.post()
                .uri("/users")
                .bodyValue(ImmutableNewUser
                        .builder()
                        .username("defaire")
                        .firstname("Dennis")
                        .lastname("Faire")
                        .email("dennis.faire@mail.com")
                        .birthdate(LocalDate.of(1983, 7, 31))
                        .build()
                )
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}
