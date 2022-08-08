package com.dmgc.technicaltest.question;

import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.model.Town;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Question2 extends Question {

    public Question2() {
        super(2);
    }

    @Override
    public void calculateQuestion() {
        try {
            List<Town> townList = new ArrayList<>();
            townList.add(new Town("A"));
            townList.add(new Town("D"));
            int distance = routeService.getDistanceFromTownList(townList);
            answerText = String.format("%s", distance);
        } catch (
                CustomExeption ce) {
            answerText = ce.getMessage();
        }
        super.printAnswer();
    }

    @Override
    void printAnswer() {
        super.printAnswer();
    }
}
