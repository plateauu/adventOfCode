package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class App {

    private static final String INPUT = "src/main/resources/day2/input.txt";

    public static void main(String[] args) throws IOException {
        var input = Files.lines(Paths.get(INPUT))
                .collect(Collectors.toList());
        var checksumCounter = new ChecksumCounter();
        checksumCounter.count(input);
        checksumCounter.findCommonLettersFromCorrectBoxId(input);
    }

}
