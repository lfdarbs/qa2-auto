import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MyFirstTest {
    private int globalVar = 10;
    Integer age = 18;
//        BigDecimal price =

/*
comment
here
 */

    @Test
    public void firstTest() {
        System.out.println("Hello, Corona!");
        int sum = sumTwoDigits(15,20);
//        sumTwoDigits(sum, 15);
        System.out.println("And the sum is: " + sum + " result");
        System.out.println(sumTwoDigits(3,8));
        age = 19;
    }

    private int sumTwoDigits(int a, int b) {
        int c = a + b;
        return c;
    }
}
