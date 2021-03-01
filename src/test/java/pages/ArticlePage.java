package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePage {
    private final By TITLE = By.tagName("h1");
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'article-share__item--comments article-share__item-with-count')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[@class='article-share__item article-share__item--comments article-share__item-with-count']//span[@class='article-share__item--count']");
    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        LOGGER.info("*** Заголовок на странице статьи: " + baseFunc.getText(TITLE));
        return baseFunc.getText(TITLE);
    }


    //Открываем страницу комментариев
    public CommentsPage openCommentsPage(){
        LOGGER.info("Opening comments page");
        WebElement iconToClick = baseFunc.findElement(COMMENTS);
        baseFunc.click(iconToClick);
        return new CommentsPage(baseFunc);
    }

    //Получаем количество комментариев
    public int getCommentsCount(){
        LOGGER.info("Getting comments count on article page");
        String articlePageComments = baseFunc.findElement(ARTICLE_PAGE_COMMENTS).getText();
        LOGGER.info("*** Число комментариев на странице комментариев: " + articlePageComments);
        return Integer.parseInt(articlePageComments);
    }
}
