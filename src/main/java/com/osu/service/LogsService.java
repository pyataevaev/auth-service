package com.osu.service;

import com.osu.domain.Logs;
import com.osu.repository.LogsRepository;
import com.osu.util.LogsUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ekaterina Pyataeva
 */
@Service
@Transactional
public class LogsService {

    private final LogsRepository logsRepository;

    private final NextSequenceService nextSequenceService;

    public LogsService(LogsRepository logsRepository, NextSequenceService nextSequenceService) {
        this.logsRepository = logsRepository;
        this.nextSequenceService = nextSequenceService;
    }

    public void save(String details) {
        Logs logs = new Logs(LogsUtil.LOG_TYPE, details);
        logs.setId(nextSequenceService.getNextSequence("customSequences"));
        logsRepository.save(logs);
    }
}
