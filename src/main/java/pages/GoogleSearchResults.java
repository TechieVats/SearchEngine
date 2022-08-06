package pages;

import base.BaseDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchResults extends BaseDriver {

    public GoogleSearchResults() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='v7W49e']/div/div")
    private List<WebElement> topResults;

    @FindBy(xpath = "//span[text()='Next']")
    private WebElement page2;

    public List<String> getTopResults() {
        List<String> results = new ArrayList<>();
        for (WebElement e : topResults) {
            results.add(e.getText());
        }
        return results;
    }

    public void clickOnPage2() {
        page2.click();
    }

}
