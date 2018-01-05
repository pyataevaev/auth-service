package com.osu.repository;

import com.osu.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ekaterina Pyataeva
 */
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
