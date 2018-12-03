package day3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
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
        int startPosition = position(rect.x, rect.y);
        takeFabric(startPosition, rect.width, rect.height);
    }

    private void takeFabric(int startPosition, int width, int height) {
        int end = startPosition + width;
        for (int k = startPosition + 1; k <= end; k++) {
            for (int i = 1; i <= height; i++) {
                int position = position(k, i);
                mark(position);
            }
        }
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
                .map(i -> i.substring(i.indexOf("@") + 2))
                .map(FabricRectangle::new)
                .collect(Collectors.toList());
    }

    private class FabricRectangle {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        FabricRectangle(String id) {
            int colonIdx = id.indexOf(":");
            int commaIdx = id.indexOf(",");
            int xIdx = id.indexOf("x");
            this.x = Integer.valueOf(id.substring(0, commaIdx));
            this.y = Integer.valueOf(id.substring(commaIdx + 1, colonIdx));
            this.width = Integer.valueOf(id.substring(colonIdx + 2, xIdx));
            this.height = Integer.valueOf(id.substring(xIdx + 1));
        }
    }
}
