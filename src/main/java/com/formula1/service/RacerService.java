package com.formula1.service;

import com.formula1.Racer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RacerService {
    private List<String> abbreviationFileData;
    private List<String> startFileData;
    private List<String> endFileData;
    private List<Racer> racers = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    private Date date;

    public RacerService(RacerDataReader dataReader) throws IOException {
        this.abbreviationFileData = dataReader.getAbbreviationData();
        this.startFileData = dataReader.getStartData();
        this.endFileData = dataReader.getEndData();
    }

    public List<Racer> getRacersList() throws ParseException {
        setAbbreviationFileData();
        setStartFileData();
        setEndFileData();
        setResultTime();
        return racers;
    }

    private void setAbbreviationFileData() {
        int listIndex = 0;
        for (int passesQuantity = abbreviationFileData.size() / 3; passesQuantity > 0; passesQuantity--) {
            Racer racer = new Racer();
            racer.setAbbreviation(abbreviationFileData.get(listIndex++));
            racer.setName(abbreviationFileData.get(listIndex++));
            racer.setTeam(abbreviationFileData.get(listIndex++));
            racers.add(racer);
        }
    }

    private void setStartFileData() throws ParseException {
        for (String line : startFileData) {
            String abbreviation = line.substring(0, 3);
            String timeString = line.substring(14);
            date = dateFormat.parse(timeString);
            long timeInMillis = date.getTime();
                racers.stream().
                    filter(racer -> racer.getAbbreviation().equals(abbreviation)).
                    forEach(racer -> racer.setStartTime(timeInMillis));
        }
    }

    private void setEndFileData() throws ParseException {
        for (String line : endFileData) {
            String abbreviation = line.substring(0, 3);
            String timeString = line.substring(14);
            date = dateFormat.parse(timeString);
            long timeInMillis = date.getTime();
                racers.stream().
                    filter(racer -> racer.getAbbreviation().equals(abbreviation)).
                    forEach(racer -> racer.setEndTime(timeInMillis));
        }
    }

    private void setResultTime() {
        racers.forEach(racer -> racer.setResultTime(racer.getEndTime() - racer.getStartTime()));
    }
}
