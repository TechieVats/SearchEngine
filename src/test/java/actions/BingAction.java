package actions;

import base.BaseDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.Bing;
import pages.BingSearchResults;

import java.util.ArrayList;
import java.util.List;

public class BingAction {

    Bing bing = new Bing();
    BingSearchResults bingSearchResults = new BingSearchResults();
    BaseDriver base = new BaseDriver();
    public static String KEYWORD = null;
    private static Logger Log = LogManager.getLogger(BingAction.class.getName());
    int count =0;
    List<String> resultsWithKeyword = new ArrayList<>();
    public void userIsOnBingSearch() {
        base.navigateToBingUrl();
    }

    public void userIsSearchingTheKeyWord(String keyword) {
        KEYWORD = keyword;
        bing.enterTheKeywordToSearch(keyword);
        bing.clickOnSearchButton();
        Log.info("User is searching " + keyword + " on bing search engine");
    }

    public List<String> bingSearchResultSWithKeyword() {
        for (String s : bingSearchResults.getSearchTitles()) {
            if (StringUtils.containsIgnoreCase(s,KEYWORD)) {
                resultsWithKeyword.add(s);
                count++;
            }
            if(count==10)
                break;
        }


     //   System.out.println("Bing search results all: "+  bingSearchResults.getSearchTitles());
        return resultsWithKeyword;
    }
    public Integer bingResultsCount(){
        return count;
    }

    public List<String> parseNextPageResults(){
        bingSearchResults.clickOnNextPage();
        return bingSearchResultSWithKeyword();
    }
}
