package com.osu.web.rest;

import com.osu.domain.User;
import com.osu.repository.UserRepository;
import com.osu.service.LogsService;
import com.osu.service.UserService;
import com.osu.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ekaterina Pyataeva
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserRepository userRepository;
    private final UserService userService;
    private final LogsService logsService;

    private static final String CHECK_ERROR_MESSAGE = "Incorrect password";

    public AccountResource(UserRepository userRepository, UserService userService, LogsService logsService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.logsService = logsService;
    }

    @PostMapping(path = "/register1", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> getAllGroups( @RequestBody ManagedUserVM managedUserVM) {
        return new ResponseEntity<>("21", HttpStatus.OK);
    }


    @PostMapping(path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        logsService.save("registration attempt : " + managedUserVM.toString());
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            logsService.save("registration error (incorrect password length): " + managedUserVM.toString());
            return new ResponseEntity<>(CHECK_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        return userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase())
                .map(user -> new ResponseEntity<>("login already in use", textPlainHeaders, HttpStatus.BAD_REQUEST))
                .orElseGet(() -> userRepository.findOneByEmailIgnoreCase(managedUserVM.getEmail())
                        .map(user -> new ResponseEntity<>("email address already in use", textPlainHeaders, HttpStatus.BAD_REQUEST))
                        .orElseGet(() -> {
                            User user = userService
                                    .createUser(managedUserVM.getLogin(), managedUserVM.getPassword(),
                                            managedUserVM.getFirstName(), managedUserVM.getLastName(),
                                            managedUserVM.getEmail().toLowerCase(), managedUserVM.getGroupId());
                            logsService.save("successful registration : " + managedUserVM.toString());
                            return new ResponseEntity<>(HttpStatus.CREATED);
                        })
                );
    }

    private boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }


}
