package com.foxminded.formula1.output;

import com.foxminded.formula1.Racer;
import com.foxminded.formula1.service.RacerService;
import com.foxminded.formula1.service.RacerDataReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RacerOutput {
    private List<Racer> sortedRacers;

    public String getRacersRating(RacerDataReader dataReader) throws IOException, ParseException {
            RacerService racerService = new RacerService(dataReader);
            sortedRacers = sortRacers(racerService.getRacersList());
            return getRacersOutput();
    }

    private List<Racer> sortRacers(List<Racer> racerList) {
        return racerList.
            stream().
            sorted(Comparator.comparing(Racer::getResultTime)).
            collect(Collectors.toList());
    }

    private String getRacersOutput() {
        StringBuilder result = new StringBuilder();
        int ratingPosition = 1;
        for (Racer racer : sortedRacers) {
            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(racer.getResultTime());
            int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(racer.getResultTime()) % 60);
            long milliseconds = racer.getResultTime() % 1000;
            result.append(String.format("%2d. %-18s |%-25s |%d:%02d.%03d\n",
                                        ratingPosition, racer.getName(), racer.getTeam(),
                                        minutes, seconds, milliseconds));
            if (ratingPosition == 15) {
                result.append(String.join("", Collections.nCopies(59, "-"))).append("\n");
            }
            ratingPosition++;
        }
        return result.toString();
    }
}
