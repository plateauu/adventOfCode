package day3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class OverlapFinder {

    private static final Logger log = LoggerFactory.getLogger(OverlapFinder.class.getSimpleName());

    private static final int X_WIDTH = 1_000;
    private static final int Y_WIDTH = 1_000;

    private final Integer[] fabric = new Integer[1_000_000];
    private final Set<Integer> result = new HashSet<>();

    long findOverlappedInches(List<String> input) {
        log.info("PART 1");
        parse(input).forEach(this::fill);
        log.info("Overlapped inches: {}", result.size());
        return result.size();
    }

    private void fill(FabricRectangle rect) {
        rect.positions.forEach(this::mark);
    }

    private void mark(int position) {
        if (fabric[position] != null) {
            result.add(position);
        }
        fabric[position] = 1;
    }

    int position(int x, int y) {
        int yPos = y * Y_WIDTH;
        return yPos + x;
    }

    private List<FabricRectangle> parse(List<String> input) {
        return input.stream()
                .map(FabricRectangle::new)
                .collect(Collectors.toList());
    }

    public String findNotOverlappedId(List<String> input) {
        log.info("PART 2");
        long start = System.currentTimeMillis();
        List<FabricRectangle> rectangles = parse(input);
        rectangles.forEach(this::fill);

        String noOverlapped = rectangles.stream()
                .collect(Collectors.toMap(r -> r.id, r -> r.positions))
                .entrySet()
                .stream()
                .filter(e -> !e.getValue().stream().anyMatch(result::contains))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("jklj");

        log.info("No overlapped id #: {}", noOverlapped);
        log.debug("Spent time: {} ms", System.currentTimeMillis() - start);
        return noOverlapped;
    }

    private class FabricRectangle {
        private final String id;
        private final int x;
        private final int y;
        private final int width;
        private final int height;
        private final Set<Integer> positions;

        FabricRectangle(String input) {
            String[] split = input.split("@");
            String templ = split[1].trim();
            int colonIdx = templ.indexOf(":");
            int commaIdx = templ.indexOf(",");
            int xIdx = templ.indexOf("x");
            this.id = split[0].trim().substring(1);
            this.x = Integer.valueOf(templ.substring(0, commaIdx));
            this.y = Integer.valueOf(templ.substring(commaIdx + 1, colonIdx));
            this.width = Integer.valueOf(templ.substring(colonIdx + 2, xIdx));
            this.height = Integer.valueOf(templ.substring(xIdx + 1));
            this.positions = getPositions(x, y, width, height);
        }

        private Set<Integer> getPositions(int x, int y, int width, int height) {
            int start = position(x, y);
            int end = start + width;
            Set<Integer> positions = new HashSet<>();
            for (int k = start + 1; k <= end; k++) {
                for (int i = 1; i <= height; i++) {
                    positions.add(position(k, i));
                }
            }
            return positions;
        }
    }
}
