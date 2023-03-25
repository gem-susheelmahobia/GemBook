Feature: GemBook Homepage Features

  Background: These steps are common for every scenario
    When User clicks on sign-in button
    And User enters gmail "" and password "" and click on next button
    And User clicks on No on stay signed in button
    Then Login will be successful and homepage will be displayed.

  Scenario: Check if sidebar is opened by default
    Then sidebar will be opened

  Scenario: Check the toggle functionality of sidebar
    When sidebar will be opened
    And User clicks on toggle sidebar button
    Then sidebar get's closed.
    When User again clicks on toggle sidebar button
    Then sidebar will be opened

  Scenario: Check By default News feeds should be selected from sidebar
    When User clicks on news feeds from sidebar
    Then User should be on same screen

     #one change to discuss

  Scenario: Check the Behaviour after clicking to "Other portals" link from sidebar
    When User clicks on Other portals link from sidebar
    Then Portal links under other portals will not be displayed
    When User clicks on Other portals link from sidebar
    Then Portal links under other portals will be displayed

  Scenario: Check the Proper navigation of links under other portals
    Then All the links will be navigating to desired application
#
  Scenario: Check that mouse hover over each portal links under other portals is showing proper tool tip or not.
    When User hovers over each portal links


  Scenario: Check for working of profiles option
    When User clicks over profiles options
    Then Profiles option gets open.

  Scenario: Check for working of profiles option with logout option
    When User clicks over profiles options
    And User Clicks on Logout button.
    Then User will be logged out of application.

  Scenario Outline: Check the navigation to user profiles
    When User clicks on profile photo
    Then User will be navigated to user profile having <Gmail> mail id
    And User go back to previous page
    When User clicks over profiles options
    And User clicks on view profile option
    Then User will be navigated to user profile having <Gmail> mail id
    Examples:
      | Gmail                               |
      | susheel.mahobia@geminisolutions.com |


  Scenario Outline: Check functionality of Bold, Italic, Underlined, Strikethrough options one at a time.
    When User clicks on post text box
    When User clicks on <Type> from text editor options
    And User enters text as <Content>
    Then entered text is of selected <Type>
    Examples:

      | Type          | Content                          |
      | bold          | Hello everyone                   |
      | italic        | Is everyting going good?         |
      | underline     | This is the test post in gembook |
      | strikethrough | Hope its all clear               |

  Scenario Outline: Check functionality of Bold , Italic, Strikethrough options more then one at a time.
    When User clicks on post text box
    When User clicks on <Type-1> from text editor options
    When User clicks on <Type-2> from text editor options
    And User enters text as <Content>
    Then entered text will be of provided types <Type-1> and <Type-2>
    Examples:
      | Type-1        | Type-2        | Content        |
      | bold          | bold          | Hello everyone |
      | bold          | italic        | Hello everyone |
      | italic        | underline     | Hello everyone |
      | italic        | bold          | Hello everyone |
      | underline     | strikethrough | Hello everyone |
      | strikethrough | bold          | Hello everyone |

  Scenario Outline: Check functionality of Bold , Italic , Strikethrough options by selecting and applying formatting option.
    When User clicks on post text box
    When User enters text as <Content>
    And User selects the entered text
    And User clicks on <Type> from text editor options
    Then entered text is of selected <Type>
    Examples:
      | Type   | Content        |
      | bold   | Hello everyone |
      | italic | Hellowww man ! |

  Scenario Outline: Check functionality of Bold , Italic , Strikethrough options by selecting and applying and removing formatting option.
    When User clicks on post text box
    When User enters text as <Content>
    And User selects the entered text
    And User clicks on <Type> from text editor options
    Then entered text is of selected <Type>
    And User selects the entered text
    And User clicks on <Type> from text editor options
    Then Formatting should be removed
    Examples:
      | Type   | Content        |
      | bold   | Hello everyone |
      | italic | Hellowww man ! |

  Scenario Outline: Check functionality of Bold , Italic , Strikethrough options by selecting and applying multiple formatting options.
    When User clicks on post text box
    When User enters text as <Content>
    And User selects the entered text
    And User clicks on <Type-1> from text editor options
    And User selects the entered text
    And User clicks on <Type-2> from text editor options
    Then entered text will be of provided types <Type-1> and <Type-2>
    Examples:
      | Type-1 | Type-2        | Content        |
      | bold   | italic        | Hello everyone |
      | italic | strikethrough | Hello world    |

  Scenario Outline: Check by changing and validating the font color of text.
    When User clicks on post text box
    When user click on colour <Colours> to change the colour of text
    And User enters text as <Content>
    Then entered text will be of <Colours> colour
    Examples:
      | Colours | Content        |
      | #e60000 | Hello everyone |
      | #ff9900 | Hello everyone |
      | #ffffff | Hello everyone |
      | #0066cc | Hello everyone |
      | #9933ff | Hello everyone |

  Scenario Outline: Check by using changing and validating the font color by changing two times while entering the text.
    When User clicks on post text box
    When user click on colour <Colours-1> to change the colour of text
    And User enters text as <Content-1>
    And user click on colour <Colours-2> to change the colour of text
    And User enters text as <Content-2>
    Then entered text will be of provided colour's <Colours-1> and <Colours-2>
    Examples:
      | Colours-1 | Colours-2 | Content-1 | Content-2 |
      | #e60000   | #ff9900   | Hello     | world !   |
      | #e60000   | #e60000   | Hello     | Everyone  |
      | #9933ff   | #ffffff   | Hello     | Everyone  |

  Scenario Outline: Check functionality by simultaneously applying colour and text formatting options.
    When User clicks on post text box
    When User enters text as <Content>
    And User selects the entered text
    And user click on colour <Colour> to change the colour of text
    Then entered text will be of <Colour> colour
    And User selects the entered text
    And User clicks on <Type> from text editor options
    Then entered text is of selected <Type>
    And entered text will be of <Colour> colour
    Examples:
      | Colour  | Type   | Content               |
      | #ffff66 | bold   | Hello everyone        |
      | #e60000 | italic | Everything going good |

  Scenario Outline: check Alignment fuctionality
    When User clicks on post text box
    When User enters text as <Content>
    And User click on alignment <Alignment> to change the alignment of text
    Then The entered text will be aligned <Alignment>
    Examples:
      | Content        | Alignment |
      | Hello everyone | Right     |
      | Hello everyone | Justify   |
      | Hello everyone | Left      |
      | Hello everyone | Center    |

  Scenario: Check by default ongoing & upcoming events tab should be selected on Events section
    When User verify default tab in events section

  Scenario Outline: Check user is able to switch between past and ongoing & upcoming events.
    When User clicks on <Type> event button
    Then <Type> events tab will be opened
    And User clicks on <Type-2> event button
    Then <Type-2> events tab will be opened
    Examples:
      | Type               | Type-2             |
      | Past               | Ongoing & Upcoming |
      | Ongoing & Upcoming | Past               |

  Scenario Outline: Check for events list in Both the tabs (Ongoing & Upcoming ,Past ) events.
    When User clicks on <Type> event button
    Then <Type> events tab will be opened
    And Validate the events in the tab
    Examples:
      | Type               |
      | Past               |
      | Ongoing & Upcoming |

  Scenario Outline: Check for event list in Both the tabs (Ongoing & Upcoming , Past ) events are on Descending order or not.
    When User clicks on <Type> event button
    Then <Type> events tab will be opened
    And Validate the events in the tab are in descending order
    Examples:
      | Type               |
      | Past               |
      | Ongoing & Upcoming |

  Scenario Outline: Check the working of year dropdown by selecting the year
    When User select year <Year> from select year dropdown
    Examples:
      | Year        |
      | 2017        |
      | 345         |
      | Select year |

  Scenario Outline: Check the working of events dropdown
    When User select year <Year> from select year dropdown
    Then User will get list of events
    And User clicking on to any events will give the names of person who won the awards
    Examples:
      | Year |
      | 2012 |
      | 45   |
      | 2017 |

  Scenario Outline: Check the validity of profiles option in events dropdown.
    When User select year <Year> from select year dropdown
    Then User clicking on to any events will give the list of persons and clicking on to persons name will open user profile
    Examples:
      | Year |
      | 2021 |
      | 2017 |
      | 2016 |
      | 2012 |
      | 6754 |

  Scenario: Check List employee name and date followed by month is displayed in Birthdays or not
    When User verifies the employee birthday list

  Scenario: Check List should contain birth dates from current date till next 7 days
    When User verifies the birth dates in birthday list

  Scenario: Check the complete employee details ,his/her name, contact, team, birthday, reporting manager etc should be displayed from birthdays
    When User validates the complete profile details are visible after clicking to employee name in birthday's list

  Scenario: Check List employee name and date followed by month is displayed in Anniversary or not
    When User verifies the employee anniversary list

  Scenario: Check the complete employee details ,his/her name, contact, team, birthday, reporting manager etc should be displayed from anniversary
    When User validates the complete profile details are visible after clicking to employee name in anniversary list

  Scenario: Check List should contain anniversary dates from current date till next 7 days
    When User verifies the anniversary dates in anniversary list

  Scenario: Check the List of employees joined recently  should be displayed
    When User verifies the new members list

  Scenario: Check the complete employee details ,his/her name, contact, team, birthday, reporting manager etc should be displayed in new members
    When User validates the complete profile details are visible after clicking to employee name in new members list

#     need to discuss the click to profile name to open profile of the employee.

  Scenario: Check the presence of all filters in dropdown after clicking on to All filters button
    When User clicks on All filters button
    Then Dropdown containing all the filters will open

  Scenario Outline: Check that by default all should be selected inside all filers
    When User verifies the selected filter should be <Filter>
    Examples:
      | Filter |
      | All    |


  Scenario Outline: Check and verify the selected filter by selecting the different filters inside all filter
    When User verifies the selected filter should be <Filter>
    And User clicks on filters button
    And User selects the new filter <Filter-1>
    And User clicks on filters button
    Then User verifies the selected filter should be <Filter-1>
    Examples:
      | Filter | Filter-1      |
      | All    | Announcements |
      | All    | Ideas         |
      | All    | Questions     |
      | All    | Findings      |
      | All    | Achievements  |
      | All    | Generic       |

  Scenario Outline: Check and verify the selected filter is removed or not after again clicking to all filters
    When User verifies the selected filter should be <Filter>
    And User clicks on filters button
    And User selects the new filter <Filter-1>
    And User clicks on filters button
    Then User verifies the selected filter should be <Filter-1>
    And User selects the new filter <Filter>
    And User clicks on filters button
    Then User verifies the selected filter should be <Filter>
    Examples:
      | Filter | Filter-1      |
      | All    | Announcements |
      | All    | Ideas         |
      | All    | Questions     |
      | All    | Findings      |
      | All    | Achievements  |
      | All    | Generic       |

  Scenario Outline: Check and verify by selecting filter and changing the applied filter
    When User clicks on filters button
    And User selects the new filter <Filter-1>
    And User clicks on filters button
    Then User verifies the selected filter should be <Filter-1>
    And User selects the new filter <Filter-2>
    And User clicks on filters button
    Then User verifies the selected filter should be <Filter-2>
    And User selects the new filter <Filter-3>
    And User clicks on filters button
    Then User verifies the selected filter should be <Filter-3>
    Examples:
      | Filter-1      | Filter-2 | Filter-3      |
      | Announcements | Ideas    | All           |
      | Achievements  | Findings | Announcements |

  Scenario Outline:   Validate that after applying filters in Post,Post as per sorting options should be displayed.
    When User clicks on filters button
    And User selects the new filter <Filter>
    And User clicks on filters button
    Then User verifies the selected filter should be <Filter>
    And User validate posts should be of applied filter type <Filter>
    Examples:
      | Filter        |
      | Announcements |
      | Ideas         |
      | Questions     |
      | acHievements  |
      | Generic       |
      | All           |

  Scenario Outline: Validate that after repeating applying filters in Post,Post as per sorting options should be displayed.
    When User clicks on filters button
    And User selects the new filter <Filter-1>
    And User clicks on filters button
    Then User validate posts should be of applied filter type <Filter-1>
    And User refresh the page
    And User clicks on filters button
    And User selects the new filter <Filter-2>
    Then User validate posts should be of applied filter type <Filter-2>
    Examples:
      | Filter-1     | Filter-2      |
      | Ideas        | Announcements |
      | Achievements | Generic       |

  Scenario Outline: Check that clicking on profile photo of user who have already posted is navigating to user profile or not
    When User clicks on profile Photo/Name <Type> of the user who has already posted
    Examples:
      | Type  |
      | Image |
      | Name  |

  Scenario: Check and validate that Copy link to post option should be displayed or not when user click on three dots in post
    When User clicks on three dots in the post then copy of link option will be displayed

  Scenario: Check and validate that clicking in Copy link to post option after is able to copy the link or not.
    When User clicks on three dots in the post then copy of link option will be displayed
    And User clicks on copy of link to post option
    Then A copied toast will be displayed

  Scenario: Check the Like post functionality
    When User go to any random post and click on like button to like the post

  Scenario: Check the Unlike post fuctionality to unlike the post
    When User go to any random post and click on like button again to unlike the post after liking the post

  Scenario: Check on hovering over like reaction symbol the list of employees should be displayed
    When User hover over the like reaction to get the list of employee

  Scenario Outline: Check that clicking on profile photo of user who have liked the post is navigating to user profile or not
    When User hover over the like reaction to get the list of employee and click on image/name <Type> of user
    Examples:
      | Type  |
      | Image |
      | Name  |

  Scenario: Check that comment box should open on Clicking to Comment button
    When User clicks on comment button

  Scenario Outline: Check the user is able to write the contents in the comment box and to post the comment and comment count should be increased
    When User enters the text or emoji <Type> into comment box <Content> and press enter button to comment
    Examples:
      | Type  | Content          |
      | Text  | Hello everyone   |
      | Emoji | None             |
      | Both  | Hello Geminian's |

  Scenario: Check that View Less comments link should be displayed if post has more then one comment
    When User checks that post having more then one comment

  Scenario: Check that clicking on View Less Comments link is showing single comment with view More comment option
    When Click on View Less comment link

  Scenario: Check that clicking on View More Comments link after view Less Comment link is showing all the comments
    When Click on View More comment link after View Less comment

#    Doubt to discuss need to assign view more comment variable once again in above scenario ..........why ?
  Scenario: Check that all the comments of the post is displayed on clicking Green symbol of count of comment
    When User clicks on Green symbol of count of comment

  Scenario: Check the comment like functionality
    When User clicks the like button of the comment then on right hand side like symbol should be displayed

  Scenario: Check that Hovering over comment like reaction symbol the list of employees should be displayed
    When User clicks the like button of the comment and hovers over the like reaction symbol

  Scenario: Check the comment delete functionality
    When User click on delete comment button the comment should be deleted

  Scenario: Check that clicking on to photo of the post should open image in a pop up
    When User click on posts image

  Scenario: Check the image pop up close functionality after opening the image
    When User click on posts image
    And User clicks on image pop up close button
    Then Image pop up which was opened on clicking should be closed

  Scenario: Check when multiple photos are in the post next and previous button should be present
    When User checks the post having multiple photos

  Scenario Outline: Check the working of previous and next buttons of the post having multiple photos
    When User clicks on navigation key <Direction> of the post having multiple photos
    Examples:
      | Direction |
      | Previous  |
      | Next      |

  Scenario Outline: Check that after navigating to next image user is able to open image pop up or not
    When User clicks on navigation key <Direction> of the post having multiple photos and click on image
    And User clicks on image pop up close button
    Then Image pop up which was opened on clicking should be closed
    Examples:
      | Direction |
      | Previous  |
      | Next      |

#  Scenario Outline: Check the working of previous and next buttons of the post having multiple photos and get the complete list to make sure able to navigate to all photos of post
#    When User clicks on navigation keys to parse the complete list of photo in direction <Direction>
#    Examples:
#      | Direction |
#      | Next      |
#      | Previous  |
  Scenario Outline: Check if Name, designation, email Id and mobile number should displayed with Profile photo in self profile
    When User clicks on profile photo
    Then User will be navigated to user profile having <Gmail> mail id
    And All the details of user self profiles is displayed
    Examples:
      | Gmail                               |
      | susheel.mahobia@geminisolutions.com |

  Scenario: Check if Manager section contains photo, Manager's name followed by designation in self profile
    When User clicks on profile photo
    Then All the details of Manager of user self profile is displayed

  Scenario: Check if Details section display Birth date,joining date and Hobbies in self profile
    When User clicks on profile photo
    Then Birthday , Joining date, Hobbies of self profiles is displayed
#
  Scenario: Check Skills, Certification, Acheivement,Training , Gemtalk,Projects these fields are present on screen
    When User clicks on profile photo
    Then Check for presence of all fields

  Scenario: Check that text box should be displayed after Clicking on 'click to add' option
    When User clicks on profile photo
    And Click on add new button to open text box

  Scenario Outline: Check if able to save the text in textbox with the Save button
    When User clicks on profile photo
    And Click on add new button to open text box
    And User enters the text <Text> in text box and click on save button
    Examples:
      | Text |
      | Java |

  Scenario Outline: Check if able to edit the text in textbox after saving with the help of edit button
    When User clicks on profile photo
    And Click on add new button to open text box
    And User enters the text <Text> in text box and click on save button
    And User clicks on edit button to make the textbox editable and enters the text <EditText>
    Examples:
      | Text | EditText |
      | Java | Editing  |

  Scenario Outline: For All the text fields check the modifications are saved or not after using edit functionality
    When User clicks on profile photo
    And User clicks on add new button of <Field> to open text box
    And User enters the text <Text> in field <Field> and click on save button
    Then User validate the  <Text> should be saved in <Field>
    And User clicks on edit button of the field <Field> to edit <Edit-Text> and click on save button
    Then User validates the edited text after editing <Text> with <Edit-Text> in <Field>
    Examples:
      | Field       | Text     | Edit-Text |
      | Skills      | Java     | Developer |
      | Achievement | Python   | Engineer  |
      | Trainings   | TestNG   | Cucumber  |
      | Gemtalk     | Cucumber | TestNG    |

  Scenario Outline: For All the text fields check the modifications should not be saved  after using edit and cancel functionality
    When User clicks on profile photo
    And User clicks on add new button of <Field> to open text box
    And User enters the text <Text> in field <Field> and click on save button
    Then User validate the  <Text> should be saved in <Field>
    And User clicks on edit button of the field <Field> to edit <Edit-Text> and click on cancel button
    Then User validate the  <Text> should be saved in <Field>
    Examples:
      | Field       | Text   | Edit-Text |
      | Skills      | Java   | Developer |
      | Achievement | Python | Engineer  |
      | Trainings   | TestNG   | Cucumber  |
      | Gemtalk     | Cucumber | TestNG    |
