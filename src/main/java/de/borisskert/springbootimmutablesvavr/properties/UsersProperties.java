package de.borisskert.springbootimmutablesvavr.properties;

import de.borisskert.springbootimmutablesvavr.ImmutableUser;
import de.borisskert.springbootimmutablesvavr.User;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value.Modifiable
public abstract class UsersProperties {

    public abstract ModifiableDefaultUser defaultUser();

    @Value.Modifiable
    public static abstract class DefaultUser {
        public abstract UUID id();

        public abstract String username();

        public abstract String firstname();

        public abstract String lastname();

        public abstract String email();

        public abstract String birthdate();

        public User toUser() {
            return ImmutableUser.builder()
                    .id(id())
                    .username(username())
                    .firstname(firstname())
                    .lastname(lastname())
                    .email(email())
                    .birthdate(LocalDate.parse(this.birthdate()))
                    .build();
        }
    }
}
