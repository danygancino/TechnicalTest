package com.dmgc.technicaltest.question;

import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.model.Town;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Question5 extends Question {

    public Question5() {
        super(5);
    }

    @Override
    public void calculateQuestion() {
        try {
            List<Town> townList = new ArrayList<>();
            townList.add(new Town("A"));
            townList.add(new Town("E"));
            townList.add(new Town("D"));
            int distance = routeService.getDistanceFromTownList(townList);
            answerText = String.format("%s",distance);

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
