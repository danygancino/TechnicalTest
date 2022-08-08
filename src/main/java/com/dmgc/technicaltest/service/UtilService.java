package com.dmgc.technicaltest.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UtilService {

    public void printConsole(String text){
        System.out.println(text);
    }

    public String readConsoleLine()  {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String name = "ERROR";
        try {
            name = reader.readLine();
        } catch (IOException exception){
            exception.printStackTrace();
        }
        return name;
    }
}
