package com.osu.repository;

import com.osu.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ekaterina Pyataeva
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
