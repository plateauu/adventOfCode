package day1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FrequencySumatorTest {

    @Test
    void should_return_frequency_as_sum_started_from_zero(){
        var input = List.of(+1L, -2L, +3L, +1L);
        var sumator = new FrequencySumator();
        var result = sumator.sum(input);
        Assertions.assertEquals(result, 3);
    }

    @Test
    void should_return_frequency_duplicate(){
        var input = List.of(+1L, -2L, +3L, +1L);
        var sumator = new FrequencySumator();
        var result = sumator.findDuplicate(input);
        Assertions.assertEquals(result, 2);
    }

}