package de.borisskert.springbootimmutablesvavr.properties;

import de.borisskert.springbootimmutablesvavr.ImmutableUser;
import de.borisskert.springbootimmutablesvavr.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@ConstructorBinding
@ConfigurationProperties("myapp")
@Validated
public class ApplicationProperties {

    @Valid
    private final UsersProperties users;

    public ApplicationProperties(UsersProperties users) {
        this.users = users;
    }

    public UsersProperties users() {
        return users;
    }

    @Validated
    public static class UsersProperties {

        @Valid
        private final DefaultUser defaultUser;

        public UsersProperties(DefaultUser defaultUser) {
            this.defaultUser = defaultUser;
        }

        public DefaultUser defaultUser() {
            return defaultUser;
        }

        @Validated
        public static class DefaultUser {
            private final UUID id;

            private final String username;

            private final String firstname;

            private final String lastname;

            @Email
            private final String email;

            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
            private final String birthdate;

            public DefaultUser(UUID id, String username, String firstname, String lastname, String email, String birthdate) {
                this.id = id;
                this.username = username;
                this.firstname = firstname;
                this.lastname = lastname;
                this.email = email;
                this.birthdate = birthdate;
            }

            public User toUser() {
                return ImmutableUser.builder()
                        .id(id)
                        .username(username)
                        .firstname(firstname)
                        .lastname(lastname)
                        .email(email)
                        .birthdate(LocalDate.parse(this.birthdate))
                        .build();
            }
        }
    }
}
