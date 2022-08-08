package com.dmgc.technicaltest.question;

import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.model.Town;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Question10 extends Question {

    public Question10() {
        super(10);
    }

    @Override
    public void calculateQuestion() {
        try {
            Town origin = new Town("C");
            Town destiny = new Town("C");
            int count = routeService.getCountRoutesByMaxDistance(origin, destiny, 30);
            answerText = String.format("%s",count);

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
