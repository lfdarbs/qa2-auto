import org.junit.jupiter.api.Test;

public class FirtsTaskCredit {

// сумма займа
    double amount1 = 80000;
// процентная ставка
    double procent = 7.21;


    @Test
    public void sumOfTheCredit() {
        double prcntAmount = amount1 / 100 * procent;
        double total = amount1 + prcntAmount;
        System.out.println("Сумма займа " + amount1);
        System.out.println("Процентная ставка " + procent);
        System.out.println("Выплата по процентам " + prcntAmount);
        System.out.println("Итого " + total);
    }
}
