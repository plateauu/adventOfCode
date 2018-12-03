package day2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class ChecksumCounter {

    private static final Logger log = LoggerFactory.getLogger(ChecksumCounter.class.getSimpleName());
    private long iterationCounter = 0;

    public long count(List<String> input) {
        var resultMap = new HashMap<Integer, Integer>();
        log.info("PART 1");
        input.forEach(i -> {
            var result = getCharacterMap(i);
            var maxApperance = getMaxRepetitivnes(result);
            IntStream.rangeClosed(2, maxApperance)
                    .forEach(occurence -> result.entrySet().stream()
                            .filter(e -> e.getValue().equals(occurence))
                            .findFirst()
                            .ifPresent(e -> {
                                var actulal = resultMap.getOrDefault(occurence, 0);
                                resultMap.put(occurence, ++actulal);
                            }));

        });
        var checksum = multiplyGreaterThanOne(resultMap);
        log.info("Prepared checksum: {}", checksum);
        return checksum;
    }

    private int multiplyGreaterThanOne(HashMap<Integer, Integer> resultMap) {
        return resultMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .mapToInt(Map.Entry::getValue)
                .reduce(Math::multiplyExact)
                .orElse(0);
    }

    private Map<Character, Integer> getCharacterMap(String i) {
        var result = new HashMap<Character, Integer>();
        char[] chars = i.toCharArray();
        for (char c : chars) {
            var charApperance = result.getOrDefault(c, 0);
            result.put(c, ++charApperance);
        }
        return result;
    }

    private int getMaxRepetitivnes(Map<Character, Integer> result) {
        return result.entrySet().stream()
                .mapToInt(Map.Entry::getValue).max().orElse(1);
    }

    public String findCommonLettersFromCorrectBoxId(List<String> input) {
        log.info("PART 2");
        long startTime = System.currentTimeMillis();
        Optional<String> result;
        for (String i : input) {
            if ((result = test(i, input)).isPresent()) {
                long stopTime = System.currentTimeMillis();
                log.info("Common letters from correct box id are: {}", result.get());
                log.info("Time spent: {} ms", (stopTime - startTime));
                log.info("Iteration made: {} ", iterationCounter);
                return result.get();
            }
        }
        return null;
    }

    private Optional<String> test(String tested, List<String> input) {
        for (String i : input) {
            iterationCounter++;
            if (tested.equals(i)) {
                continue;
            }
            int testedLenght;
            if ((testedLenght = tested.length()) != i.length()) {
                continue;
            }
            Optional<String> result;
            if ((result = checkChars(tested, i, testedLenght)).isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    private Optional<String> checkChars(String tested, String next, int length) {
        int indicator = 0;
        char differentiator = ' ';
        char[] testedChars = tested.toCharArray();
        char[] nextChars = next.toCharArray();
        StringBuilder equals = new StringBuilder();
        for (int k = 0; k < length; k++) {
            char testChar = testedChars[k];
            char nextChar = nextChars[k];
            if (testChar == nextChar) {
                equals.append(testChar);
                continue;
            }
            indicator++;
        }
        if (indicator == 1) {
            log.info("Paired IDs: {} | {}", tested, next);
            log.info("Differ letter is: {}", differentiator);
            return Optional.of(equals.toString());
        }
        return Optional.empty();
    }
}
