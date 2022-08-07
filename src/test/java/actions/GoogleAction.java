package actions;

import base.BaseDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import pages.Google;
import pages.GoogleSearchResults;

import java.util.ArrayList;
import java.util.List;

public class GoogleAction extends BaseDriver {
    Google google = new Google();
    GoogleSearchResults googleSearchResults = new GoogleSearchResults();
    BaseDriver base = new BaseDriver();
    int count = 0;
    public static String KEYWORD = null;
    public List<String> searchResultsKeyWord = new ArrayList<>();
    public List<String> searchResultsWithoutKeyWord = new ArrayList<>();

    public void userIsOnGoogle() {
        base.navigateToGoogleUrl();
    }

    public void userIsSearchingTheKeyWord(String keyword) {
        KEYWORD = keyword;
        google.enterTheSearchKeyword(KEYWORD);
        google.clickOnGoogleSearch();
        logger.info("User is searching " + KEYWORD + " on google search engine");
    }

    public List<String> getGoogleSearchResults() {
        List<String> searchResult = new ArrayList<>();
        searchResult = googleSearchResults.getTopResults();
        for (int i = 0; i < searchResult.size() - 1; i++) {
            if (searchResult.get(i).contains("People also ask") || searchResult.get(i).contains("Videos") || searchResult.get(i).contains("Top stories") ||searchResult.get(i).contains("Local results")) {
                continue;
            }
            else {
                if (StringUtils.containsIgnoreCase(searchResult.get(i), KEYWORD)) {
                    count++;
                    searchResultsKeyWord.add(driver.findElement(By.xpath("(//div[@class='v7W49e']/div/div)[" + (i + 1) + "]//h3")).getText());
                } else {
                    searchResultsWithoutKeyWord.add(driver.findElement(By.xpath("(//div[@class='v7W49e']/div/div)[" + (i + 1) + "]//h3")).getText());
                }
            }
            if (count == 10)
                break;
        }
        logger.info("Google Search list without keyword: " + searchResultsWithoutKeyWord);
        logger.info("Google list is added: " + searchResultsKeyWord);
        return searchResultsKeyWord;
    }

    public Integer googleResultsCount() {
        return count;
    }

    public List<String> parseNextPageResults() {
        googleSearchResults.clickOnNextPage();
        return getGoogleSearchResults();
    }
}
