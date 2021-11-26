package com.formula1;

import com.formula1.output.RacerOutput;
import com.formula1.service.RacerDataReader;
import com.formula1.service.RacerFilesKeeper;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        RacerDataReader dataReader = new RacerDataReader(new RacerFilesKeeper());
        System.out.println(new RacerOutput().getRacersRating(dataReader));
    }
}
