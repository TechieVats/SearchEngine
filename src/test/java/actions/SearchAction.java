package actions;

import base.BaseDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pages.GoogleSearchResults;

import java.util.ArrayList;
import java.util.List;

public class SearchAction extends BaseDriver {

    GoogleSearchResults googleSearchResults = new GoogleSearchResults();
    List<String> searchResultsKeyWord = new ArrayList<>();
    List<String> searchResultsHref = new ArrayList<>();
    Logger logger = LogManager.getLogger(SearchAction.class);

    public List<String> getGoogleSearchResults() {
        List<String> searchResult = new ArrayList<>();
        searchResult = googleSearchResults.getTopResults();
        int count = 0;
        logger.info("size of the list: " + searchResult.size());
        logger.info("print the item of the list: " + searchResult);
        for (int i = 0; i < searchResult.size(); i++) {
            if (searchResult.get(i).contains("People also ask") || searchResult.get(i).contains("Videos") || searchResult.get(i).contains("Top stories")) {

            } else {
                count++;
                logger.info("i+1:  "+i +"  "+ "(//div[@class='v7W49e']/div/div)[" + (i + 1) + "]//h3");
                searchResultsHref.add(driver.findElement(By.xpath("(//div[@class='v7W49e']/div/div)[" + (i + 1) + "]//h3")).getText());
                searchResultsKeyWord.add(searchResult.get(i));
            }
        }
        logger.info("Count: " + count);
        return searchResultsHref;
    }
}
