package com.example.demo.backend.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserSchedule {

   

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Bangkok")
    public void testEveryMinute() {
        log.info("Hello,What's up?");
    }
}
