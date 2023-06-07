package com.example.ass.Schudule;


import com.example.ass.repository.ISanPhamRepo;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@EnableScheduling
@Component
public class CheckActive {

    @Autowired
    private ISanPhamRepo iSanPhamRepo;

    @Scheduled(fixedRate = 1000*60*60)
    public void getDate(){
//        Date today=new Date();
//        iSanPhamRepo.test(today);
    }
}
