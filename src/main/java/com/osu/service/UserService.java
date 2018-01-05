package com.osu.service;

import com.osu.domain.Authority;
import com.osu.domain.Group;
import com.osu.domain.User;
import com.osu.repository.AuthorityRepository;
import com.osu.repository.GroupRepository;
import com.osu.repository.UserRepository;
import com.osu.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ekaterina Pyataeva
 */
@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.groupRepository = groupRepository;
    }

    public User getUserById(long id) {
        log.debug("Getting user ={}", id);
        return userRepository.findOne(id);
    }

    public User getUserByLogin(String login) {
       //log.debug("Getting user by name ={}", login);
       // return userRepository.findByLogin(login);
        return null;
    }

    public List<User> getAllUsers() {
        log.debug("Getting all users");
        List<User> users = new ArrayList<>();
        for (User user:userRepository.findAll()){
            users.add(user);
        }
        return users;
    }

    public User createUser(String login, String password, String firstName, String lastName, String email, long groupId) {
        User newUser = new User();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        Group group = groupRepository.findOne(groupId);
        newUser.setGroup(group);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User save(User user) {
        log.debug("Saving user");
        if (user.getId() == null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public void delete(User user) {
        log.debug("Deleting user");
        userRepository.delete(user);
    }

}
