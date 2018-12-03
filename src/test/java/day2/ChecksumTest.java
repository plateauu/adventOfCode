package day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ChecksumTest {

    @Test
    void should_return_checksum_for_ids() {
        var input = List.of("abcdef", "bababc", "abbcde", "abcccde", "aabcdd", "abcdee", "ababab");
        var counter = new ChecksumCounter();
        var result = counter.count(input);
        var expceted = 12;
        Assertions.assertEquals(expceted, result);
    }

    @Test
    void should_return_common_letters_between_two_correct_box_id(){
        var input = List.of("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz");
        var counter = new ChecksumCounter();
        var result = counter.findCommonLettersFromCorrectBoxId(input);
        var expected = "fgij";
        Assertions.assertEquals(expected, result);
    }
}
