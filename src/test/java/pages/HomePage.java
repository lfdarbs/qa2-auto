package pages;

import helpers.CommentsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.plaf.basic.BasicButtonUI;

public class HomePage {
    private final By ACCEPT_BTN = By.xpath(".//button[@mode='primary']");
    private final By TITLE = By.xpath(".//span[@itemprop = 'headline name']");
    private final By COMMENTS_COUNT = By.xpath(".//span[contains(@class, '__comment')]");
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;


    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void acceptCookies() {
        LOGGER.info("Accepting cookies");
        baseFunc.click(ACCEPT_BTN);
    }

    public ArticlePage openArticleById(int id) {
        LOGGER.info("Opening article Nr. " + (id + 1));
        WebElement titleToClick = baseFunc.findElements(TITLE).get(id);
        baseFunc.click(titleToClick);
        return new ArticlePage(baseFunc);
    }

    public String getArticleTitleById(int id){
        LOGGER.info("Getting article Nr. " + (id + 1) + " title");
        LOGGER.info("*** Заголовок домашней страницы: " + baseFunc.getText(TITLE, id));
        return baseFunc.getText(TITLE, id);
    }

    public int getCommentsCountById(int id) {
        WebElement commentsCount = baseFunc.findElements(COMMENTS_COUNT).get(id);

        CommentsHelper helper = new CommentsHelper();
        return helper.getCommentCount(commentsCount);
    }

    //Количество комментариев берется из статью которая была задана по Id
    public int getRealComments(int id){
        WebElement titleToClick = baseFunc.findElements(TITLE).get(id);
        String stringToParse = titleToClick.findElement(COMMENTS_COUNT).getText();
        stringToParse = stringToParse.substring(1, stringToParse.length() -1);
        LOGGER.info("*** Количество комментариев на домашней странице: " + stringToParse);
        return Integer.parseInt(stringToParse);
    }
}