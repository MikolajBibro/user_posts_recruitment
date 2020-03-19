package com.bibro.statistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceTest {

    @Test
    public void givenSpecifiedCoordinates_whenCallingCalculate_return310() {
        //given
        double lng1 = 11, lat1 = 12, lng2 = 13, lat2 = 14;

        //when
        int result = (int) Distance.calculate(lng1, lat1, lng2, lat2);

        //then
        assertEquals(310, result);
    }
}
