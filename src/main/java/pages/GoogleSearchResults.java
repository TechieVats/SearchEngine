package pages;

import base.BaseDriver;
import org.checkerframework.checker.signature.qual.FieldDescriptor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.TestingUtility;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchResults extends BaseDriver {
    TestingUtility utility = new TestingUtility();
    public GoogleSearchResults() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='v7W49e']/div/div")
    private List<WebElement> topResults;
    @FindBy(xpath = "//h2[text()='Local results']")
    private WebElement searchLocalResults;
    @FindBy(xpath = "//span[text()='Next']")
    private WebElement nextPage;

    public List<String> getTopResults() {
        List<String> results = new ArrayList<>();
        for (WebElement e : topResults) {
            results.add(e.getText());
        }
        return results;
    }

    public void clickOnNextPage() {
        utility.click(nextPage,"google next page");
    }

    public boolean isLocalResultsArePresent(){
        return searchLocalResults.isDisplayed();
    }
}
