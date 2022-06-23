package com.shiyangxiao.cinemarest.service;

import com.shiyangxiao.cinemarest.Exception.InvalidPasswordException;
import com.shiyangxiao.cinemarest.entity.Statistics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StatService {
    @Value("${cinema.room.password}")
    String secretPassword;
    private final Statistics statistics;

    public StatService(Statistics statistics) {
        this.statistics = statistics;
    }

    public Statistics getStatistics(String password) throws Exception {
        try {
            if (password.equals(secretPassword)) {
                return statistics;
            } else {
                throw new InvalidPasswordException("Wrong token!");
            }
        } catch (InvalidPasswordException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("not a valid password");
        }
    }
}
