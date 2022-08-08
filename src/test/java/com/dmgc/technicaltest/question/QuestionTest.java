package com.dmgc.technicaltest.question;


import com.dmgc.technicaltest.model.Route;
import com.dmgc.technicaltest.model.Town;
import com.dmgc.technicaltest.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class QuestionTest {

    @Autowired
    Question1 question1;

    @Autowired
    Question2 question2;

    @Autowired
    Question3 question3;

    @Autowired
    Question4 question4;

    @Autowired
    Question5 question5;

    @Autowired
    Question6 question6;

    @Autowired
    Question7 question7;

    @Autowired
    Question8 question8;

    @Autowired
    Question9 question9;

    @Autowired
    Question10 question10;

    @Autowired
    RouteService routeService;



    @Test
    void question1CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question1.calculateQuestion();

        //then
        assertEquals(question1.answerText, "9");
    }

    @Test
    void question2CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question2.calculateQuestion();

        //then
        assertEquals(question2.answerText, "5");
    }

    @Test
    void question3CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question3.calculateQuestion();

        //then
        assertEquals(question3.answerText, "13");
    }

    @Test
    void question4CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question4.calculateQuestion();

        //then
        assertEquals(question4.answerText, "22");
    }

    @Test
    void question5CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question5.calculateQuestion();

        //then
        assertEquals(question5.answerText, "NO SUCH ROUTE");
    }

    @Test
    void question6CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question6.calculateQuestion();

        //then
        assertEquals(question6.answerText, "2");
    }

    @Test
    void question7CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question7.calculateQuestion();

        //then
        assertEquals(question7.answerText, "3");
    }

    @Test
    void question8CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question8.calculateQuestion();

        //then
        assertEquals(question8.answerText, "9");
    }

    @Test
    void question9CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question9.calculateQuestion();

        //then
        assertEquals(question9.answerText, "9");
    }

    @Test
    void question10CalculateTest() {
        //given
        routeService.initData(getData());

        //when
        question10.calculateQuestion();

        //then
        assertEquals(question10.answerText, "7");
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
