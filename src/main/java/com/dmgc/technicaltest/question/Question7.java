package com.dmgc.technicaltest.question;

import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.model.Town;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Question7 extends Question {

    public Question7() {
        super(7);
    }

    @Override
    public void calculateQuestion() {
        try {
            Town origin = new Town("A");
            Town destiny = new Town("C");
            int routes = routeService.getCountRoutesByExactStops(origin, destiny, 4);
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
