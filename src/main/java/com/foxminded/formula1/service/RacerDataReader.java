package com.foxminded.formula1.service;
import com.foxminded.formula1.exception.InvalidFileDataException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacerDataReader {
    private final Path abbreviation;
    private final Path start;
    private final Path end;

    public RacerDataReader(RacerFilesKeeper filesKeeper)  {
        this.abbreviation = filesKeeper.getAbbreviation();
        this.start = filesKeeper.getStart();
        this.end = filesKeeper.getEnd();
    }

    public List<String> getAbbreviationData() throws IOException, InvalidFileDataException {
        List<String> abbreviationFileData = Files.lines(abbreviation).
            flatMap((line -> Arrays.stream(line.split("_")))).
            collect(Collectors.toList());
        if (abbreviationFileData.isEmpty()) {
            throw new IOException("abbreviations.txt file is empty!");
        }

        int listIndex = 0;
        for (int passesQuantity = abbreviationFileData.size() / 3; passesQuantity > 0; passesQuantity--) {
            if (!abbreviationFileData.get(listIndex).matches("[A-Z]{3}")) {
                throw new InvalidFileDataException("Abbreviation data is invalid!");
            }
            listIndex++;
            if (!abbreviationFileData.get(listIndex).matches("\\D+\\s\\D+")) {
                throw new InvalidFileDataException("Racer name data is invalid!");
            }
            listIndex++;
            if (!abbreviationFileData.get(listIndex).matches("\\D+")) {
                throw new InvalidFileDataException("Team data is invalid!");
            }
            listIndex++;
        }
        return abbreviationFileData;
    }

    public List<String> getStartData() throws IOException {
        List<String> startFileData = Files.readAllLines(start);
        if (startFileData.isEmpty()) {
            throw new IOException("start.log file is empty!");
        }
        if (startFileData.size() != getAbbreviationData().size()/3) {
            throw new InvalidFileDataException("Start data is invalid");
        }
        return startFileData;
    }

    public List<String> getEndData() throws IOException {
        List<String> endFileData = Files.readAllLines(end);
        if (endFileData.isEmpty()) {
            throw new IOException("end.log file is empty!");
        }
        if (endFileData.size() != getAbbreviationData().size()/3) {
            throw new InvalidFileDataException("End data is invalid");
        }
        return endFileData;
    }
}
