import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TvnetTest {
    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.xpath(".//span[@itemprop='headline name']");
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment section-font-color')]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[@itemprop='headline name']");
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//div[@class='button cookie-notif__content--button']");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[@class='article-share__item article-share__item--comments article-share__item-with-count']//span[@class='article-share__item--count']");
    private final By COMMENTS_PAGE_TITLE = By.xpath(".//h1[@class='article-headline']");
    private final By COMMENTS_PAGE_COMMENTS = By.xpath(".//span[@class='flex flex--align-items-center flex--justify-content-center article-comments-heading__count section-bg-color']");
    private final By GO_TO_COMMENTS_PAGE = By.xpath("//a[contains(@class, 'article-share__item article-share__item--comments')]");
    private final By ACTUAL_COMMENT = By.xpath(".//div[@class='article-comment-content']");

    @Test
    public void tvnetTest() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://tvnet.lv");


        //Жду появление кнопки кукиз и кликаю по нему
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ACCEPT_COOKIE_BTN));

        WebElement acceptBtn = driver.findElement(ACCEPT_COOKIE_BTN);
        acceptBtn.click();


        //Нахожу указанную по счету статью
        wait.until(ExpectedConditions.visibilityOfElementLocated(ARTICLE));
        WebElement currentArticle = driver.findElements(ARTICLE).get(7);


        //Нахожу и сохраняю заголовок
        String titleToCompare = currentArticle.findElement(TITLE).getText();
        System.out.println("Заголовок на домашней странице = " + titleToCompare);


        //Нахожу и сохраняю количество комментариев
        //Проверяю есть ли на странице элемент с коментариями
        //Если нет, то число комментариев = 0
        //Если есть, то String перевожу в int, отбрасывая первый и последний символ и сохраняю кол-во в переменной
        int commentsCounter = 0;

        if (currentArticle.findElements(COMMENT_COUNT).isEmpty()){
            System.out.println("Число комментариев в заголовке на домашней странице = " + commentsCounter);
        } else {
            WebElement element = currentArticle.findElement(COMMENT_COUNT);
            String stringToParse = element.getText();
            stringToParse = stringToParse.substring(1, stringToParse.length() -1);
            commentsCounter = Integer.parseInt(stringToParse);
            System.out.println("Число комментариев в заголовке на домашней странице = " + commentsCounter);
        }


        //Тут я считаю количество символов в элементе комментариев
        //Например "(12)" = 4 символа, "(9)" = 3 символа
        //Добавляю один символ стобы потом отнять от заголовка домашней страницы
        // Потому что у домашней страницы есть пробел между загловком и счетчиком комментариев :)
        int commentsLengthFull = 0;

        if (currentArticle.findElements(COMMENT_COUNT).isEmpty()){
            System.out.println("Число символов в коментарии = " + commentsLengthFull);
        } else {
            WebElement commentCountHomePage = currentArticle.findElement(COMMENT_COUNT);
            String stringToParse = commentCountHomePage.getText();
            commentsLengthFull = stringToParse.length() + 1;
            System.out.println("Число символов в коментарии = " + commentsLengthFull);
        }


        //Проверяю есть ли комментарии на домашней странице в заголовке статьи
        //Создаю бульнь true/false для дальнейших проверок
        boolean commentsPresentOnHomePage = true;

        if (currentArticle.findElements(COMMENT_COUNT).isEmpty()){
            System.out.println("Нет комментариев на домашней странице");
            commentsPresentOnHomePage = false;
        }else{
            System.out.println("Есть комментарии на домашней странице");
        }


        //Перехожу на статью
        currentArticle.findElement(TITLE).click();


        //Нахожу и сравниваю заголовки
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();
        titleToCompare = titleToCompare.substring(0, titleToCompare.length() - commentsLengthFull);
        System.out.println("Загаловок на домашней странице = " + titleToCompare);
        System.out.println("Заголовок на странице статьи   = " + articlePageTitle);


        //НА TVNET ЧАСТО ОТЛИЧАЮТСЯ ЗАГОЛВКИ НА СТРАНИЦАХ
        //ЭТОТ ТЕСТ ЧАСТО ПАДАЕТ
        Assertions.assertEquals(titleToCompare, articlePageTitle, "Wrong title on article page");


        //Тут я сравниваю комментарии на домашней странице и на странице статьи
        //Если на странице статьи найден счетчкик с комментариями (ARTICLE_PAGE_COMMENTS).isDisplayed,
        //То делаем проверку по количеству комментариев
        //---
        ///Если счеткчик комментариев не найден, то проверям, что оба булиня = false
        boolean commentsPresentOnArticlePage = true;
        int commentsCounterOnArticlePage = 0;
        try
        {
            if (driver.findElement(ARTICLE_PAGE_COMMENTS).isDisplayed())
            {
                String articlePageComments = driver.findElement(ARTICLE_PAGE_COMMENTS).getText();
                commentsCounterOnArticlePage = Integer.parseInt(articlePageComments);
                System.out.println("articlePageComments String = " + articlePageComments);
                System.out.println("Количество комментариев на домашней = " + commentsCounter);
                System.out.println("Количество комментариев на статье   = " + commentsCounterOnArticlePage);
                Assertions.assertEquals(commentsCounter, commentsCounterOnArticlePage, "Wrong comment count on article page and homepage");
                System.out.println("ARTICLE_PAGE_COMMENTS IS DISPLAYED");
            }
        }
            catch(Exception e)
            {
                commentsPresentOnArticlePage = false;
                System.out.println("ARTICLE_PAGE_COMMENTS NOT DISPLAYED");
                System.out.println("commentsPresentOnHomePage = " + commentsPresentOnHomePage);
                System.out.println("commentsPresentOnArticlePage = " + commentsPresentOnArticlePage);
                Assertions.assertEquals(commentsPresentOnHomePage, commentsPresentOnArticlePage, "Comments are not present on one of the pages");
            }




        //Перехожу на страницу коментариев и жду появления заголовка COMMENTS_PAGE_TITLE
        driver.findElement(GO_TO_COMMENTS_PAGE).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMMENTS_PAGE_TITLE));

        //Сравнивая заголовок домашней и заголовок страницы с комментариями
        String commentsPageTitle = driver.findElement(COMMENTS_PAGE_TITLE).getText();
        System.out.println("Заголовк на страницы комментариев = " + commentsPageTitle);

        //НА TVNET ЧАСТО ОТЛИЧАЮТСЯ ЗАГОЛВКИ НА СТРАНИЦАХ
        //ЭТОТ ТЕСТ ЧАСТО ПАДАЕТ
        Assertions.assertEquals(titleToCompare, commentsPageTitle, "Wrong title on comments page");


        //Сравниваю количество комментариев в заголвке домашней страницы и в счетчике на странице с комментариями
        //Если счетчик найден (COMMENTS_PAGE_COMMENTS).isDisplayed, то сравниваю количество комментариев
        //Если счетчик не найден, то сравниваю, что оба булиня = false
        boolean commentsPresentOnCommentsPage = true;
        int commentsCountOnArticlePage = 0;
        try
        {
            if(driver.findElement(COMMENTS_PAGE_COMMENTS).isDisplayed());
            {
                String commentsPageCommnetsCount = driver.findElement(COMMENTS_PAGE_COMMENTS).getText();
                commentsCountOnArticlePage = Integer.parseInt(commentsPageCommnetsCount);
                System.out.println("COMMENTS_PAGE_COMMENTS IS DISPLAYED");
                System.out.println("Количество комментариев на странице с комментариями = " + commentsCountOnArticlePage);
                Assertions.assertEquals(commentsCounter, commentsCountOnArticlePage, "Wrong comments count on comments page");
            }
        }
        catch (Exception b)
        {
            System.out.println("COMMENTS_PAGE_COMMENTS IS NOT DISPLAYED");
            commentsPresentOnCommentsPage = false;
            Assertions.assertEquals(commentsPresentOnHomePage, commentsPresentOnCommentsPage, "Comments are not present on one of the pages");
        }


        //Считаю реальные комментарии
        //--
        //Если на домашней странице нет комментариев в заголовке (commentsCounter == 0), но на странице с комментариями нашлись реальные комментарии (ACTUAL_COMMENT).isDisplayed
        //То считаем их, сравниваем с 0 - тест не пройден. Потому что на домашней было указано, что комментов нет, а они есть :)
        //Если реальные комментарии не найдены, то проверка не требуется
        //--
        //Если на домашней странице в заголовке указаны комментарии (commentsCounter !== 0)
        //То ищем на странице реальные комментарии driver.findElements(ACTUAL_COMMENT)
        //Если не удалось найти  - Тест не пройден
        //Если удалось найти, делаем список, считаем количество и сравниваем со счетчиком на домашней странице
        //Совпало - Тест прошел, Не совпало - Тест не прошел
        if (commentsCounter == 0){
            try{
                if (driver.findElement(ACTUAL_COMMENT).isDisplayed());
                {
                    List<WebElement> commentsActual = driver.findElements(ACTUAL_COMMENT);
                    int commentsOnCommentsPage = commentsActual.size();
                    System.out.println("Количество реальных комменатриев = " + commentsOnCommentsPage);
                    Assertions.assertEquals(commentsCounter, commentsOnCommentsPage, "Actual comments count doesn't match number in the page title");
                }
            }
            catch (Exception c)
            {
                System.out.println("ALL GOOD - END OF THE TEST");
            }
        } else {
            List<WebElement> commentsActual = driver.findElements(ACTUAL_COMMENT);
            int commentsOnCommentsPage = commentsActual.size();
            System.out.println("Количество реальных комменатриев = " + commentsOnCommentsPage);
            Assertions.assertEquals(commentsCounter, commentsOnCommentsPage, "Actual comments count doesn't match number in the page title");
        }
        driver.close();
    }
}
