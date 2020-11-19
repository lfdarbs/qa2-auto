import org.junit.jupiter.api.Test;

public class SecondTaskSymbolsCounter {

//Тестовая фраза
    String str = ("Сколько тут символов?");

    @Test
    public void symbolCounter() {
//подсчет символов включая пробел
        int count = 0;
        for(int i = 0; i<str.length(); i++) {
            count++;}

//подсчет слов
        int blockCount = str.split(" ").length;

//вывод результатов
        System.out.println("Тестовая фраза: " + str);
        System.out.println("Количество символов = " + count);
        System.out.println("Количество слов = " + blockCount);
    }
}
