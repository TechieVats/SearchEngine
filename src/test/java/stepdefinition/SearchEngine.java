package stepdefinition;

import actions.BingAction;
import actions.CommonAction;
import actions.GoogleAction;
import base.BaseDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine extends BaseDriver {
    GoogleAction googleAction = new GoogleAction();
    BingAction bingAction = new BingAction();
    CommonAction commonAction = new CommonAction();
    public static List<String> GOOGLE_LIST = new ArrayList<>();
    public static List<String> BING_LIST = new ArrayList<>();

    @When("the user search the {string} in the search engine")
    public void theUserSearchTheInTheSearchEngine(String keyword) {
        googleAction.userIsSearchingTheKeyWord(keyword);
    }

    @Then("user is able to parse top 10 search results for google search engine")
    public void userIsAbleToParseTopSearchResultsAsPerTheRequirements() {
        //saving the google search results into a global list variable
        GOOGLE_LIST = googleAction.getGoogleSearchResults();

        //validating if 10 search items present in the list or not
        if (googleAction.googleResultsCount() < 10) {
            //updating the list with next page results
            GOOGLE_LIST = googleAction.parseNextPageResults();
        }
        logger.info("Google final search results: "+ GOOGLE_LIST);
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
        //saving the bing search results into a global variable
        BING_LIST = bingAction.bingSearchResultSWithKeyword();

        //validating if 10 search items present in the list or not
        if (bingAction.bingResultsCount() < 10) {
            //updating the list with next page results
            BING_LIST = bingAction.parseNextPageResults();
        }
        logger.info("Bing final search results: "+ BING_LIST);
    }

    @And("user is able to fetch the common results from both of the search engines")
    public void userIsAbleToFetchTheCommonResultsFromBothOfTheSearchEngines() {
        //logging the common results of the both search engine results
        logger.info("Common Result: " + commonAction.commonResults(GOOGLE_LIST, BING_LIST));

    }
}
