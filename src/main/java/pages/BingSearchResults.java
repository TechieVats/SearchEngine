package pages;

import base.BaseDriver;
import io.cucumber.java.bs.A;
import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.TestingUtility;

import java.util.ArrayList;
import java.util.List;

public class BingSearchResults extends BaseDriver {
    TestingUtility utility = new TestingUtility();
    public BingSearchResults() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[contains(@class,'b_algo')]//h2")
    private List<WebElement> topResults;
    @FindBy(css = "[title='Next page']")
    private WebElement nextPage;
    @FindBy(css = "[class='sb_count']")
    private WebElement pageResultCount;

    public void clickOnNextPage() {
        try {
            TestingUtility.wait(2);
            utility.click(wait.until(ExpectedConditions.elementToBeClickable(nextPage)),"bing next page");
        } catch (ElementClickInterceptedException e) {
            utility.click(wait.until(ExpectedConditions.elementToBeClickable(nextPage)),"bing next page again");
        }
    }
    // below function is fetching the titles from the search results
    public List<String> getSearchTitles() {
        List<String> searchResults = new ArrayList<>();
        for (WebElement e : topResults) {
            searchResults.add(e.getText());
        }
        return searchResults;
    }

    // below function is to validate the next page is loaded or not
    public boolean nextPageValidation() {
        return pageResultCount.getText().contains("Of");
    }
}
