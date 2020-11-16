import org.junit.jupiter.api.Test;

import java.net.SocketTimeoutException;

public class ThirdTaskDistance {

//координаты
    double x1 = 3.2;
    double y1 = 4.8;
    double x2 = 5.1;
    double y2 = 2.7;

    @Test
    public void distanceCalc() {
//метод при помощи Math.sqrt
        double distance = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        System.out.println(distance);

//метод при помощи Math.hypot
        double ac = Math.abs(y2 - y1);
        double ab = Math.abs(x2 - x1);
        double distance2 = Math.hypot(ac, ab);
        System.out.println(distance2);
        }
}
