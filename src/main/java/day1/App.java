package day1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class.getSimpleName());
    private static final Map<Long, Integer> map = new HashMap<>();
    private static final String INPUT = "src/main/resources/day1/input.txt";

    private static long result = 0;

    public static void main(String[] args) throws IOException {
        log.info(String.format("PART 1"));
        long sum = Files.lines(Paths.get(INPUT))
                .mapToLong(Long::valueOf)
                .sum();

        log.info(String.format("Finished frequency: %d", sum));

        log.info(String.format("PART 2"));
        while (true) {
            proceed();
            if (getResult() != null) {
                break;
            }
        }

        log.info(String.format("Frequency first reached twice is: %d", getResult()));
    }

    private static void proceed() throws IOException {
        FileInputStream is = new FileInputStream(new File(INPUT));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            result += Long.valueOf(line);
            Integer freq = map.getOrDefault(result, 0);
            map.put(result, ++freq);
            if (map.get(result) > 1) {
                return;
            }
        }
    }

    private static Long getResult() {
        return map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

}
