package day3;

import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FabricOverlapsTest {

    @Test
    void should_return_overlaped_inches_count() {
        var input = List.of("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2");
        var finder = new OverlapFinder();
        var result = finder.findOverlappedInches(input);
        var expceted = 4;
        Assertions.assertEquals(expceted, result);
    }

    @Test
    void should_return_position_for_fabric(int x, int y, int expected) {
        var finder = new OverlapFinder();
        var result = finder.position(x, y);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void should_return_postions() {
        should_return_position_for_fabric(1, 1, 0);
        should_return_position_for_fabric(100, 1, 99);
        should_return_position_for_fabric(1, 100, 99000);
        should_return_position_for_fabric(150, 100, 99149);
    }

    @Test
    void should_return_not_overlapped_claim() {
        var input = List.of("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2");
        var finder = new OverlapFinder();
        var result = finder.findNotOverlappedId(input);
        var expceted = "3";
        Assertions.assertEquals(expceted, result);
    }

}
