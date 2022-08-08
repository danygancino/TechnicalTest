package com.dmgc.technicaltest.question;

import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.model.Town;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Question9 extends Question {

    public Question9() {
        super(9);
    }

    @Override
    public void calculateQuestion() {
        try {
            Town origin = new Town("B");
            Town destiny = new Town("B");
            Double distance = routeService.getShortDistanceRoute(origin, destiny);
            answerText = String.format("%s",distance.intValue());

        } catch (CustomExeption ce){
            answerText = ce.getMessage();
        }
        super.printAnswer();
    }

    @Override
    void printAnswer() {
        super.printAnswer();
    }
}
