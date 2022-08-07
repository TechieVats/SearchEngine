package actions;

import base.BaseDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import pages.Bing;
import pages.BingSearchResults;

import java.util.ArrayList;
import java.util.List;

public class BingAction extends BaseDriver {

    Bing bing = new Bing();
    BingSearchResults bingSearchResults = new BingSearchResults();
    BaseDriver base = new BaseDriver();
    public static String KEYWORD = null;
    int count = 0;
    List<String> resultsWithKeyword = new ArrayList<>();
    List<String> resultsWithoutKeyword = new ArrayList<>();

    public void userIsOnBingSearch() {
        base.navigateToBingUrl();
    }

    public void userIsSearchingTheKeyWord(String keyword) {
        KEYWORD = keyword;
        bing.enterTheKeywordToSearch(keyword);
        bing.clickOnSearchButton();
        logger.info("User is searching " + keyword + " on bing search engine");
    }

    /*
    Below function is to return the list with the matching results with the search keyword
     */

    public List<String> bingSearchResultSWithKeyword() {
        for (String s : bingSearchResults.getSearchTitles()) {
            if (StringUtils.containsIgnoreCase(s, KEYWORD)) {
                resultsWithKeyword.add(s);
                count++;
            }
            else{
                resultsWithoutKeyword.add(s);
            }
            if (count == 10)
                break;
        }
        logger.info("Bing list without keyword: "+ resultsWithoutKeyword);
        logger.info("Bing list is added: " + resultsWithKeyword);
        return resultsWithKeyword;
    }

    public Integer bingResultsCount() {
        return count;
    }

    public List<String> parseNextPageResults() {
        bingSearchResults.clickOnNextPage();
        Assert.assertTrue(bingSearchResults.nextPageValidation());
        return bingSearchResultSWithKeyword();
    }
}
