package com.osu.web.rest.vm;

import com.osu.service.dto.UserDTO;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

/**
 * Created by Ekaterina Pyataeva
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedUserVM(Long id, String login, String password, String firstName, String lastName,
                         String email, Instant createdDate, Instant lastModifiedDate,
                         Set<String> authorities) {

        super(id, login, firstName, lastName, email, createdDate, lastModifiedDate,  authorities);

        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
                "} " + super.toString();
    }
}
