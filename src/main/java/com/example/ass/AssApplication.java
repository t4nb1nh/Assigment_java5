package com.example.ass;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
public class AssApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssApplication.class, args);
    }

//    @Scheduled(fixedRate = 1000)
//    public void getDate(){
//        System.out.println(new Date());
//    }
}
