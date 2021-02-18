import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sound.midi.Soundbank;
import java.util.List;

public class DelfiTests {
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode='primary']");
    private final By ARTICLE_TITLE = By.xpath(".//span[@itemprop = 'headline name']");

    private final Logger LOGGER = LogManager.getLogger(DelfiTests.class);

    @Test
    public void firstDelfiTest() {
        LOGGER.info("This test will check accept cookie btn");

        LOGGER.info("Setting up driver path");
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");

        LOGGER.info("Opening browser window");
        WebDriver driver = new ChromeDriver();

        LOGGER.info("Maximazing window");
        driver.manage().window().maximize();

        LOGGER.info("Open homepage");
        driver.get("http://delfi.lv");

        LOGGER.info("Waitng for accept cookies modal window");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ACCEPT_COOKIE_BTN));

        LOGGER.info("Pressing accept btn");
        WebElement acceptBtn = driver.findElement(ACCEPT_COOKIE_BTN);
        acceptBtn.click();
    }
}


/*
    @Test
    public void tvnetTest()  {
        String articleToOpen = "Eiropas Komisija sūdzēs tiesā Bulgāriju un Grieķiju";

        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http:/tvnet.lv");
        List<WebElement> articles = driver.findElements(ARTICLE_TITLE);

        for (int i = 0; i < articles.size(); i++) {
            String currentTitle = articles.get(i).getText();
            if (currentTitle.startsWith(articleToOpen)) {
                articles.get(i).click();
                break;
            }
//            System.out.println(i + " " + articles.get(i).getText());
//            articles.get(3).click();
        }

        //------------------------------------------------------
        //-------------------foreach cycle----------------------
        //------------------------------------------------------
        for (WebElement we:
             articles) {
            if (we.getText().startsWith(articleToOpen)){
                we.click();
                break;
            }
        }

         driver.close();
    }
}

/*
tvnet.lv

1) прокликать элементы так чтобы оказаться на стринце самой статьи и потом оказаться на странице комментариев

2)* перейти на указанную по счету статью (используя списки)

3)** перейти на статью по заголовку (цикл)

4)*** сделать проверку что название статьи совпадает на всех страницах
(главная, страница комментариев, страница статьи)

5)**** проверять кол-во комментариев (используя инты) что оно совпадает

6)***** проверять кол-во комментариев не только по счетчику, но и посчитать комменты

*/

//System.out.println(we.getText());