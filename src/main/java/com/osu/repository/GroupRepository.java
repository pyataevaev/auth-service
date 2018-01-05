package com.osu.repository;

import com.osu.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ekaterina Pyataeva
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
