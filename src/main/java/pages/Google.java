package pages;

import base.BaseDriver;
import util.TestingUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Google extends BaseDriver {
    TestingUtility utility = new TestingUtility();
    public Google(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "q")
    private WebElement searchWindow;
    @FindBy(css = "[value='Google Search']")
    private WebElement googleSearchButton;

    public void enterTheSearchKeyword(String keyword){
        utility.enterTheText(searchWindow,keyword,"google search window");
    }

    public void clickOnGoogleSearch(){
        utility.click(googleSearchButton,"google search button");
    }

}
