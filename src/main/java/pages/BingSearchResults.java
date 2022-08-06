package pages;

import base.BaseDriver;
import io.cucumber.java.bs.A;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class BingSearchResults extends BaseDriver {

    public BingSearchResults(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//li[contains(@class,'b_algo')]//h2")
    private List<WebElement> topResults;

    @FindBy(css="[aria-label='Page 2']")
    private WebElement nextPage;

    public void clickOnNextPage(){

            Actions actions = new Actions(driver);
        actions.doubleClick(nextPage).click().perform();
    }

    public List<String> getSearchTitles(){
        List<String> searchResults = new ArrayList<>();
        for(WebElement e: topResults){
            searchResults.add(e.getText());
        }
        return searchResults;
    }
}
