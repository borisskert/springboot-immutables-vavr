package de.borisskert.springbootimmutablesvavr;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value.Immutable
@JsonSerialize(as = ImmutableNewUser.class)
@JsonDeserialize(as = ImmutableNewUser.class)
public abstract class NewUser {
    public abstract String username();

    public abstract String firstname();

    public abstract String lastname();

    public abstract String email();

    public abstract LocalDate birthdate();

    public User toUser() {
        return ImmutableUser.builder()
                .id(UUID.randomUUID())
                .username(this.username())
                .firstname(this.firstname())
                .lastname(this.lastname())
                .email(this.email())
                .birthdate(this.birthdate())
                .build();
    }
}
