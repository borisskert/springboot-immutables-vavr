package de.borisskert.springbootimmutablesvavr.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("myapp")
public class ApplicationProperties {

    private final ModifiableUsersProperties users;

    public ApplicationProperties(ModifiableUsersProperties users) {
        this.users = users;
    }

    public UsersProperties users() {
        return users;
    }

}
