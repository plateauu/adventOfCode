package day1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencySumator {

    private static final Logger log = LoggerFactory.getLogger(FrequencySumator.class.getSimpleName());
    private static final Map<Long, Integer> map = new HashMap<>();
    private static final String INPUT = "src/main/resources/day1/input.txt";

    private long result = 0;

    public static void main(String[] args) throws IOException {
        List<Long> input = Files.lines(Paths.get(INPUT))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        var sumator = new FrequencySumator();
        sumator.sum(input);
        sumator.findDuplicate(input);
    }

    public long sum(List<Long> input) {
        log.info(String.format("PART 1"));
        long sum = input.stream().mapToLong(l -> l).sum();
        log.info(String.format("Finished frequency: %d", sum));
        return sum;
    }

    public long findDuplicate(List<Long> input) {
        log.info(String.format("PART 2"));
        Long duplicated;
        while (true) {
            readFrequencies(input);
            if ((duplicated = getDuplicatedFrequenecies()) != null) {
                break;
            }
        }
        log.info(String.format("Frequency first reached twice is: %d", duplicated));
        return duplicated;
    }

    private void readFrequencies(List<Long> input) {
        for (var i : input) {
            result += i;
            Integer freq = map.getOrDefault(result, 0);
            map.put(result, ++freq);
            if (map.get(result) > 1) {
                return;
            }
        }
    }

    private Long getDuplicatedFrequenecies() {
        return map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
