package uz.tokhir.junittesting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyMathTest {

    private MyMath math = new MyMath();

    @Test
    void calculateSum_ThreeMemberArray() {
        assertEquals(6,  math.calculateSum(new int[] {1,2,3}));
    }


    // {} => 0
    @Test
    void calculateSum_ZeroLengthArray() {
        assertEquals(0,  math.calculateSum(new int[] {}));
    }
}
