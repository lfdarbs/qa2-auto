package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentsPage {
    private final By TITLE = By.xpath(".//h1[@itemprop='headline name']");
    private final By COMMENTS_PAGE_COMMENTS = By.xpath(".//span[contains(@class, 'comments-heading__count section-bg-color')]");
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public CommentsPage(BaseFunc baseFunc){
        this.baseFunc = baseFunc;
    }

    //Получаем количество комментариев на странице комментариев
    public String getCommentsTitle(){
        LOGGER.info("Getting comments page title");
        LOGGER.info("*** Заголовок на странице комментариев " + baseFunc.getText(TITLE));
        return baseFunc.getText(TITLE);
    }

    //Получаем количество комментариев из значка (иконки)
    public int getCommentsCount() {
        LOGGER.info("Getting comments count on comments page");
        String commentsPageCommnetsCount = baseFunc.findElement(COMMENTS_PAGE_COMMENTS).getText();
        LOGGER.info("*** Число комментариев на странице комментариев = " + commentsPageCommnetsCount);
        return Integer.parseInt(commentsPageCommnetsCount);
    }
}
