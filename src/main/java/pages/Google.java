package pages;

import base.BaseDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Google extends BaseDriver {

    public Google(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "q")
    private WebElement searchWindow;

    @FindBy(css = "[value='Google Search']")
    private WebElement googleSearchButton;

    public void enterTheSearchKeyword(String keyword){
        searchWindow.sendKeys(keyword);
    }

    public void clickOnGoogleSearch(){
        googleSearchButton.click();
    }

}
