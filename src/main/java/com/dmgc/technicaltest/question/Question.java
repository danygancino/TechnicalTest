package com.dmgc.technicaltest.question;

import com.dmgc.technicaltest.service.RouteService;
import com.dmgc.technicaltest.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;

abstract class Question {

    @Autowired
    public RouteService routeService;

    @Autowired
    UtilService utilService;

    int questionNumber;
    String answerText = "";

    public Question(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    abstract void calculateQuestion();

    void printAnswer() {
        utilService.printConsole( String.format("Question #%s: %s", questionNumber,  answerText ));
    }
}
