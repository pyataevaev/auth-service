package com.osu.repository;

import com.osu.domain.Logs;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Ekaterina Pyataeva
 */
public interface LogsRepository extends MongoRepository<Logs, Long> {
}
