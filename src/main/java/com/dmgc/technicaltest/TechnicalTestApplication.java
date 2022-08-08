package com.dmgc.technicaltest;

import com.dmgc.technicaltest.model.Route;
import com.dmgc.technicaltest.model.Town;
import com.dmgc.technicaltest.question.*;
import com.dmgc.technicaltest.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class TechnicalTestApplication {

    @Autowired
    private RouteService routeService;

    @Autowired
    private Question1 question1;
    @Autowired
    private Question2 question2;
    @Autowired
    private Question3 question3;
    @Autowired
    private Question4 question4;
    @Autowired
    private Question5 question5;
    @Autowired
    private Question6 question6;
    @Autowired
    private Question7 question7;
    @Autowired
    private Question8 question8;
    @Autowired
    private Question9 question9;
    @Autowired
    private Question10 question10;

    private static Logger LOG = LoggerFactory
            .getLogger(TechnicalTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TechnicalTestApplication.class, args);
    }


    @EventListener({ApplicationReadyEvent.class})
    public void initOnStartAplication() {

        routeService.initData(getData());
        question1.calculateQuestion();
        question2.calculateQuestion();
        question3.calculateQuestion();
        question4.calculateQuestion();
        question5.calculateQuestion();
        question6.calculateQuestion();
        question7.calculateQuestion();
        question8.calculateQuestion();
        question9.calculateQuestion();
        question10.calculateQuestion();

    }

    private List<Route> getData(){
        Route r1 = new Route(new Town("A"), new Town("B"), 5);
        Route r2 = new Route(new Town("B"), new Town("C"), 4);
        Route r3 = new Route(new Town("C"), new Town("D"), 8);
        Route r4 = new Route(new Town("D"), new Town("C"), 8);
        Route r5 = new Route(new Town("D"), new Town("E"), 6);
        Route r6 = new Route(new Town("A"), new Town("D"), 5);
        Route r7 = new Route(new Town("C"), new Town("E"), 2);
        Route r8 = new Route(new Town("E"), new Town("B"), 3);
        Route r9 = new Route(new Town("A"), new Town("E"), 7);
        List<Route> routeList = new ArrayList<>();
        routeList.add(r1);
        routeList.add(r2);
        routeList.add(r3);
        routeList.add(r4);
        routeList.add(r5);
        routeList.add(r6);
        routeList.add(r7);
        routeList.add(r8);
        routeList.add(r9);
        return routeList;

    }
}
