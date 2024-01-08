Feature: Assignment UI

  Scenario: User_launches_React_legacy_page_checks_Main_Concepts
    Given User hits the react legacy page
    When User clicks on "Docs" tab
    Then User can see "Main Concepts" menu in the right hand panel
    When User clicks on the "Main Concepts" menu
    Then User can see and highlight the options of "Main Concepts" menu
    And User writes the menu of "Main Concepts" to a file
    When User clicks on the "Main Concepts" menu

  Scenario: User Clicks on Advanced Concepts
    Given User hits the react legacy page
    When User clicks on "Docs" tab
    Given User can see "Advanced Guides" menu in the right hand panel
    When User clicks on the "Advanced Guides" menu
    Then User can see and highlight the options of "Advanced Guides" menu
    And User writes the menu of "Advanced Guides" to a file
    When User clicks on the "Advanced Guides" menu

  Scenario: React_JS_Scroll_highlighted_content
    Given User hits the react legacy page
    And User clicks on "Tutorial" tab
    When User scroll the page respective content at the right hand panel is highlighted
    
