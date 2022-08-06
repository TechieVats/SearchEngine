package actions;

import base.BaseDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pages.Google;
import pages.GoogleSearchResults;
import java.util.ArrayList;
import java.util.List;

public class GoogleAction extends BaseDriver {
    Google google = new Google();
    GoogleSearchResults googleSearchResults = new GoogleSearchResults();
    BaseDriver base = new BaseDriver();
    int count=0;
    public static List<String> searchResultsHref = new ArrayList<>();
    private static Logger logger = LogManager.getLogger(GoogleAction.class.getName());

    public void userIsOnGoogle() {
        base.navigateToGoogleUrl();
    }

    public void userIsSearchingTheKeyWord(String keyword) {
        google.enterTheSearchKeyword(keyword);
        google.clickOnGoogleSearch();
        logger.info("User is searching "+keyword+" on google search engine");
    }

    public List<String> getGoogleSearchResults() {
        List<String> searchResult = new ArrayList<>();
        searchResult = googleSearchResults.getTopResults();
        logger.info("size of the list: " + searchResult.size());
        logger.info("print the item of the list: " + searchResult);
        for (int i = 0; i < searchResult.size()-1; i++) {
            if (searchResult.get(i).contains("People also ask") || searchResult.get(i).contains("Videos") || searchResult.get(i).contains("Top stories")) {

            } else {
                count++;
                searchResultsHref.add(driver.findElement(By.xpath("(//div[@class='v7W49e']/div/div)[" + (i + 1) + "]//h3")).getText());
            }
            if(count==10)
                break;
        }
        return searchResultsHref;
    }

    public Integer googleResultsCount(){
        return count;
    }

    public List<String> parseNextPageResults(){
        googleSearchResults.clickOnPage2();
       return getGoogleSearchResults();
    }
}
