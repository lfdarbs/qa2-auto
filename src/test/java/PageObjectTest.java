import helpers.CommentsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ArticlePage;
import pages.BaseFunc;
import pages.CommentsPage;
import pages.HomePage;

public class PageObjectTest {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final int ARTICLE_ID = 2;

    @Test
    public void tvnetPageObjectTest() {

//        LOGGER.info("blaA BDF   " + (ARTICLE_ID + 1) + "  text");
        LOGGER.info("This test is checking titles and comment count");

        BaseFunc baseFunc = new BaseFunc();
        baseFunc.openURL("http://tvnet.lv");

        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCookies();

        //Get 4th article title on Home Page
        String homePageTitle = homePage.getArticleTitleById(ARTICLE_ID);

//        int comments = homePage.getCommentsCountById(ARTICLE_ID);
//        System.out.println(comments);

        //Получаю количество комментариев из выбранной по Id статьи
        int realComments = homePage.getRealComments(ARTICLE_ID);

        //Open article page by clicking on title
        ArticlePage articlePage = homePage.openArticleById(ARTICLE_ID);

//        ArticlePage articlePage = new ArticlePage(baseFunc);

        //Get article title on Article Page
        String articlePageTitle = articlePage.getTitle();

        //Compare article titles
//        Assertions.assertEquals(homePageTitle, articlePageTitle, "Titles are not the same");
        Assertions.assertTrue(homePageTitle.startsWith(articlePageTitle), "Titles are not the same");

        //Получить количество комментариев на странице статьи
        int commentCountOnArticlePage = articlePage.getCommentsCount();

        //Сравнить количество комментариев на странице статьи и домашней странице
        Assertions.assertEquals(realComments, commentCountOnArticlePage, "Comments count is not the same on Home page and Article page");

        //Open article page
        CommentsPage commentsPage = articlePage.openCommentsPage();

        //Get article title on Comments page
        String commentPageTitle = commentsPage.getCommentsTitle();

        //Compare titles on all pages
        Assertions.assertTrue(homePageTitle.startsWith(commentPageTitle), "Titles are not the same on Home page and Comments page");
        Assertions.assertTrue(articlePageTitle.startsWith(commentPageTitle),"Titles are not the same on Article page and Comments page");

        //Get comment count on comments page
        int commentsCountOnCommentsPage = commentsPage.getCommentsCount();

        //Compare comments on all page
        Assertions.assertEquals(realComments, commentsCountOnCommentsPage, "Comments count is not the same on Home page and Comments page");
        Assertions.assertEquals(commentCountOnArticlePage, commentsCountOnCommentsPage, "Comments count is not the same on Article page and Comments page");
    }
}