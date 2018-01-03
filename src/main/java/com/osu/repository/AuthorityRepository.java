package com.osu.repository;

import com.osu.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ekaterina Pyataeva
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
