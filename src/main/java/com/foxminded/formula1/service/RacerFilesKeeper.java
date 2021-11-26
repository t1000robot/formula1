package com.foxminded.formula1.service;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RacerFilesKeeper {
    private final Path abbreviation;
    private final Path start;
    private final Path end;

    public RacerFilesKeeper() {
        abbreviation = Paths.get("src/main/resources/abbreviations.txt");
        start = Paths.get("src/main/resources/start.log");
        end = Paths.get("src/main/resources/end.log");
    }

    public Path getAbbreviation() {
        return abbreviation;
    }

    public Path getStart() {
        return start;
    }

    public Path getEnd() {
        return end;
    }
}
