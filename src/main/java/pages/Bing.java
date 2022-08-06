package pages;

import base.BaseDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Bing extends BaseDriver {

    public Bing(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "sb_form_q")
    private WebElement bingSearchWindow;

    @FindBy(id = "search_icon")
    private WebElement bingSearchBtn;

    public void enterTheKeywordToSearch(String keyword){
        wait.until(ExpectedConditions.elementToBeClickable(bingSearchWindow)).sendKeys(keyword);
    }

    public void clickOnSearchButton(){
        try{
            Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(bingSearchBtn)).click();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
