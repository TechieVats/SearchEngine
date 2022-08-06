package stepdefinition;

import actions.BingAction;
import actions.CommonAction;
import actions.GoogleAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    GoogleAction googleAction = new GoogleAction();
    BingAction bingAction = new BingAction();
    CommonAction commonAction = new CommonAction();
    Logger logger = LogManager.getLogger(SearchEngine.class);
    public static List<String> GOOGLE_LIST = new ArrayList<>();
    public static List<String> BING_LIST = new ArrayList<>();

    @Given("that the user is browsing on {string}")

    @When("the user search the {string} in the search engine")
    public void theUserSearchTheInTheSearchEngine(String keyword) {
        googleAction.userIsSearchingTheKeyWord(keyword);
    }

    @Then("user is able to parse top 10 search results for google search engine")
    public void userIsAbleToParseTopSearchResultsAsPerTheRequirements() {
        //  logger.info(searchAction.getSearchResults());
        GOOGLE_LIST = googleAction.getGoogleSearchResults();
//        for (int i = 0; i < googleList.size(); i++) {
//            // logger.info(search.get(i));
//        }
            if(googleAction.googleResultsCount()<10){
                GOOGLE_LIST=googleAction.parseNextPageResults();
            }

    }

    @Given("that the user is on google search engine")
    public void thatTheUserIsOnGoogleSearchEngine() {
        googleAction.userIsOnGoogle();
    }

    @Given("that the user is on bing search engine")
    public void thatTheUserIsOnBingSearchEngine() {
        bingAction.userIsOnBingSearch();
    }

    @When("the user is searching {string} in the bing search engine")
    public void theUserIsSearchingInTheBingSearchEngine(String keyword) {
        bingAction.userIsSearchingTheKeyWord(keyword);
    }

    @Then("user is able to parse top 10 search results for bing search engine")
    public void userIsAbleToParseTopSearchResultsForBingSearchEngine() {
        BING_LIST = bingAction.bingSearchResultSWithKeyword();
//        for (int i = 0; i < bingList.size(); i++) {
//            // logger.info(search.get(i));
//        }
        if(bingAction.bingResultsCount()<10){
            BING_LIST=bingAction.parseNextPageResults();
        }
    }

    @And("user is able to fetch the common results from both of the search engines")
    public void userIsAbleToFetchTheCommonResultsFromBothOfTheSearchEngines() {
        logger.info("Common Result: " + commonAction.commonResults(GOOGLE_LIST, BING_LIST));

    }
}
