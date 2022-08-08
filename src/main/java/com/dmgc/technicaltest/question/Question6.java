package com.dmgc.technicaltest.question;

import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.model.Town;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Question6 extends Question {

    public Question6() {
        super(6);
    }

    @Override
    public void calculateQuestion() {
        try {
            Town origin = new Town("C");
            Town destiny = new Town("C");
            int routes = (int) routeService.getCountRoutesByMaxStops(origin, destiny, 3);
            answerText = String.format("%s",routes);

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
