Feature: To perform search function on various Search Engine

  Scenario Outline: User is parsing the 10 search results from the engine

#    Given that the user is on google search engine
#    When the user search the "<keyword>" in the search engine
#    Then user is able to parse top 10 search results for google search engine
    Given that the user is on bing search engine
    When the user is searching "<keyword>" in the bing search engine
    Then user is able to parse top 10 search results for bing search engine
  #  And  user is able to fetch the common results from both of the search engines

    Examples:
     | keyword  |
     | selenium |
    # | python|