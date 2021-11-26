package com.foxminded.formula1.service;

import com.foxminded.formula1.exception.InvalidFileDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RacerDataReaderTest {
    private RacerFilesKeeper filesKeeper;
    private RacerDataReader dataReader;

    private Path abbreviationFile;
    private Path startFile;
    private Path endFile;

    private Path emptyAbbreviationFile;
    private Path emptyStartFile;
    private Path emptyEndFile;

    private Path invalidAbbreviationFile;
    private Path invalidNameFile;
    private Path invalidTeamFile;
    private Path invalidTimeFile;

    @BeforeEach
    public void initializeObjects() {
        abbreviationFile = Paths.get("src/main/resources/abbreviations.txt");
        startFile = Paths.get("src/main/resources/start.log");
        endFile = Paths.get("src/main/resources/end.log");

        emptyAbbreviationFile = Paths.get("src/test/resources/test_empty_abbreviations.txt");
        emptyStartFile = Paths.get("src/test/resources/test_empty_start.log");
        emptyEndFile = Paths.get("src/test/resources/test_empty_end.log");

        invalidAbbreviationFile = Paths.get("src/test/resources/test_invalid_abbreviations_data.txt");
        invalidNameFile = Paths.get("src/test/resources/test_invalid_name_data.txt");
        invalidTeamFile = Paths.get("src/test/resources/test_invalid_team_data.txt");
        invalidTimeFile = Paths.get("src/test/resources/test_invalid_time.log");

        filesKeeper = mock(RacerFilesKeeper.class);
    }

    @Test
    void getAbbreviationData_shouldThrowException_whenAbbreviationFileFileIsEmpty() {
        when(filesKeeper.getAbbreviation()).thenReturn(emptyAbbreviationFile);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(IOException.class, () -> dataReader.getAbbreviationData());
    }

    @Test
    void getStartData_shouldThrowException_whenStartFileFileIsEmpty() {
        when(filesKeeper.getAbbreviation()).thenReturn(abbreviationFile);
        when(filesKeeper.getStart()).thenReturn(emptyStartFile);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(IOException.class, () -> dataReader.getStartData());
    }

    @Test
    void getEndData_shouldThrowException_whenEndFileFileIsEmpty(){
        when(filesKeeper.getAbbreviation()).thenReturn(abbreviationFile);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(emptyEndFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(IOException.class, () -> dataReader.getEndData());
    }

    @Test
    void getAbbreviationData_shouldThrowException_whenAbbreviationFileIsNull() {
        when(filesKeeper.getAbbreviation()).thenReturn(null);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(NullPointerException.class, dataReader::getAbbreviationData);
    }

    @Test
    void getStartData_shouldThrowException_whenStartFileIsNull() {
        when(filesKeeper.getAbbreviation()).thenReturn(abbreviationFile);
        when(filesKeeper.getStart()).thenReturn(null);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(NullPointerException.class, dataReader::getStartData);
    }

    @Test
    void getEndData_shouldThrowException_whenEndFileIsNull() {
        when(filesKeeper.getAbbreviation()).thenReturn(abbreviationFile);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(null);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(NullPointerException.class, dataReader::getEndData);
    }

    @Test
    void getAbbreviationData_shouldThrowException_whenAbbreviationFileIsAbsent() {
        when(filesKeeper.getAbbreviation()).thenReturn(Paths.get("wrong_directory"));
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(NoSuchFileException.class, dataReader::getAbbreviationData);
    }

    @Test
    void getStartData_shouldThrowException_whenStartFileIsAbsent() {
        when(filesKeeper.getAbbreviation()).thenReturn(abbreviationFile);
        when(filesKeeper.getStart()).thenReturn(Paths.get("wrong_directory"));
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(NoSuchFileException.class, dataReader::getStartData);
    }

    @Test
    void getEndData_shouldThrowException_whenEndFileIsAbsent() {
        when(filesKeeper.getAbbreviation()).thenReturn(abbreviationFile);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(Paths.get("wrong_directory"));
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(NoSuchFileException.class, dataReader::getEndData);
    }

    @Test
    void getAbbreviationData_shouldThrowException_whenAbbreviationDataIsInvalid() {
        when(filesKeeper.getAbbreviation()).thenReturn(invalidAbbreviationFile);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(InvalidFileDataException.class, () -> dataReader.getAbbreviationData());
    }

    @Test
    void getAbbreviationData_shouldThrowException_whenNameDataIsInvalid() {
        when(filesKeeper.getAbbreviation()).thenReturn(invalidNameFile);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(InvalidFileDataException.class, () -> dataReader.getAbbreviationData());
    }

    @Test
    void getAbbreviationData_shouldThrowException_whenTeamDataIsInvalid() {
        when(filesKeeper.getAbbreviation()).thenReturn(invalidTeamFile);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(InvalidFileDataException.class, () -> dataReader.getAbbreviationData());
    }

    @Test
    void getStartData_shouldThrowException_whenTimeDataIsInvalid(){
        when(filesKeeper.getAbbreviation()).thenReturn(abbreviationFile);
        when(filesKeeper.getStart()).thenReturn(invalidTimeFile);
        when(filesKeeper.getEnd()).thenReturn(endFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(InvalidFileDataException.class, () -> dataReader.getStartData());
    }

    @Test
    void getEndData_shouldThrowException_whenTimeDataIsInvalid(){
        when(filesKeeper.getAbbreviation()).thenReturn(abbreviationFile);
        when(filesKeeper.getStart()).thenReturn(startFile);
        when(filesKeeper.getEnd()).thenReturn(invalidTimeFile);
        dataReader = new RacerDataReader(filesKeeper);
        assertThrows(InvalidFileDataException.class, () -> dataReader.getEndData());
    }
}
