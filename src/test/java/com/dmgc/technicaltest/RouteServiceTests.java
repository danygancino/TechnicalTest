package com.dmgc.technicaltest;

import com.dmgc.technicaltest.exception.CustomExeption;
import com.dmgc.technicaltest.model.Route;
import com.dmgc.technicaltest.model.Town;
import com.dmgc.technicaltest.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

@SpringBootTest
class RouteServiceTests {


    @Autowired
    RouteService routeService;


    /**
     * Prueba la creación de la matriz de adyacencia y que se haya llenado correctamente según los datos del ejercicio
     */
    @Test
    void getDistanceTest() {

        //given
        Town origin = new Town("A");
        Town destiny = new Town("B");
        List<Town> townList = new ArrayList<>();
        townList.add(origin);
        townList.add(destiny);

        List<Route> routeList = new ArrayList<>();
        Route r8 = new Route(new Town("E"), new Town("B"), 3);
        Route r3 = new Route(new Town("C"), new Town("D"), 8);
        Route r2 = new Route(new Town("B"), new Town("C"), 4);
        Route r5 = new Route(new Town("D"), new Town("E"), 6);
        Route r1 = new Route(new Town("A"), new Town("B"), 5);
        Route r4 = new Route(new Town("D"), new Town("C"), 8);
        Route r7 = new Route(new Town("C"), new Town("E"), 2);
        Route r6 = new Route(new Town("A"), new Town("D"), 5);
        Route r9 = new Route(new Town("A"), new Town("E"), 7);
        routeList.add(r1);
        routeList.add(r2);
        routeList.add(r3);
        routeList.add(r4);
        routeList.add(r5);
        routeList.add(r6);
        routeList.add(r7);
        routeList.add(r8);
        routeList.add(r9);


        //when
        routeService.initData(routeList);
        int distance = routeService.getDistanceFromTownList(townList);

        //then
        assertEquals(distance,5);
    }

    @Test
    void addRouteTest() {
        //given
        String message = "";
        Route r8 = new Route(new Town("E"), new Town("B"), 3);
        Route r9 = new Route(new Town("E"), new Town("B"), 3);

        //when
        try {
            routeService.addRoute(r8);
            routeService.addRoute(r9);
        }catch (CustomExeption ce){
            message = ce.getMessage();
        }

        //then
        assertEquals(message,"ROUTE ALREADY EXISTS");
    }


    }
