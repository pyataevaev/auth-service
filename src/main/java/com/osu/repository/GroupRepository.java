package com.osu.repository;

import com.osu.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Ekaterina Pyataeva
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long id);
}
