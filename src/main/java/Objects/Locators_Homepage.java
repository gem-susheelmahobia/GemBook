package Objects;

import org.openqa.selenium.By;

public class Locators_Homepage {
    public static By HomePageLogo = By.xpath("//span[@class='logo_headerTextHelper__1Z6Zl']");
    public static By SidebarToggle = By.xpath("//img[@src='/static/media/menu.93def42e.svg']");
    public static By NewsFeeds = By.xpath("//span[contains(text(),'News Feeds')]");
    public static By OtherPortals = By.xpath("//span[contains(text(),'Other Portals')]");
    public static By CountOfTotalLinksExcludingPortalsInsideOtherPortals = By.xpath("//ul[@class='sidebar_menu__3B2HB']//li");
    public static By PortalsUnderOtherPortals = By.xpath("//a[@class='sidebar_toolLink__3CZTD']");
    public static By ProfileOptions = By.xpath("//img[@class='ml-2 mr-4']");
    public static By LogoutButton = By.xpath("//span[contains(text(),'Logout')]");
    public static By ViewProfiles = By.xpath("//div[contains(text(),'View Profile')]");
    public static By ProfileImage = By.xpath("//img[@class='navbar_img__5LehJ']");
    public static By GmailIdUnderProfiles = By.xpath("//a[@class='leftWrapper_contact__1RFF9' and contains(@title,'Click To Send Mail')]");
    public static By ContactUnderProfile = By.xpath("//a[@class='leftWrapper_contact__1RFF9' and contains(@title,'Click To Call')]");
    public static By DesignationUnderProfile = By.xpath("//div[@class='leftWrapper_employeeName__1chtp']//child::div[@class='leftWrapper_designation__3RhyP']");
    public static By BirthdayAndJoiningDateUnderProfile = By.xpath("//div[@class='leftWrapper_rightContent__3P8M5']//child::div[contains(@class,'leftWrapper_detail') and not(contains(text(),'Hobbies')) and not(contains(text(),'-'))]");
    public static By ReportingManagerNameUnderProfiles = By.xpath("//div[@class='rightWrapper_managerDetail__tg0lq']//child::h5");
    public static By PostTextBox = By.xpath("//div[@data-placeholder='Write a Post...']");
    public static By ItalicType = By.xpath("//button[contains(@class,'ql-italic')]//*[name()='svg']");
    public static By BoldType = By.xpath("//button[contains(@class,'ql-bold')]//*[name()='svg']");
    public static By Content = By.xpath("//div[@data-placeholder='Write a Post...']//p//child::*");
    public static By ContentPtag = By.xpath("//div[@data-placeholder='Write a Post...']//child::*");
    public static By UnderlinedType = By.xpath("//button[contains(@class,'ql-underline')]//*[name()='svg']");
    public static By StrikeType = By.xpath("//button[contains(@class,'ql-strike')]//*[name()='svg']");
    public static By ColourChart = By.xpath("//span[@class='ql-color ql-picker ql-color-picker']");
    public static By ColoursInColourChart = By.xpath("//span[@id='ql-picker-options-1']//span[contains(@style,'background')]");
    public static By AlignChart = By.xpath("//span[@class='ql-align ql-picker ql-icon-picker']");
    public static By LeftAlign = By.xpath("(//span[@id='ql-picker-options-0']//child::span)[1]");
    public static By RigtAlign = By.xpath("//span[@id='ql-picker-options-0']//child::span[@data-value='right']");
    public static By CenterAlign = By.xpath("//span[@id='ql-picker-options-0']//child::span[@data-value='center']");
    public static By JustifyAlign = By.xpath("//span[@id='ql-picker-options-0']//child::span[@data-value='justify']");
    public static By DefaultEventTab = By.xpath("//div[@class='sidebar_eventTabs__2hGpW']//child::div[@class='sidebar_activeTab__hOSnz']");
    public static By PastEvent = By.xpath("//div[@class='sidebar_eventTabs__2hGpW']//child::div[contains(text(),'Past')]");
    public static By OngoingEvent = By.xpath("//div[@class='sidebar_eventTabs__2hGpW']//child::div[contains(text(),'Ongoing&Upcoming')]");
    public static By EventLists = By.xpath("//div[@class='container event-list']//child::ul[@class='event-ul p-2']");
    public static By NoEventsMessage = By.xpath("//p[contains(text(),'No ongoing or upcoming events available')]");
    public static By SelectYearDropdown = By.xpath("//select[@name='year']");
    public static By SelectEventsDropdown = By.xpath("//select[@name='award']");
    public static By PersonsWinningAwards = By.xpath("//div[@class='sidebar_description__1XG1m']//li");
    public static By PersonsWinningAwardsUl = By.xpath("//div[@class='sidebar_description__1XG1m']//ul");
    public static By PersonsProfileName = By.xpath("//div[@class='leftWrapper_employeeName__1chtp']//h4");
    public static By BirthdayDates = By.xpath("//div[@class='birthday-body']//child::div[@class='birthday-body-inner']//div[@class='date']");
    public static By BirthdayNames = By.xpath("//div[@class='birthday-body']//child::div[@class='birthday-body-inner']//div[@class='name']");
    public static By EmployeeBirthdayList = By.xpath("//div[@class='birthday-body']//child::div[@class='birthday-body-inner']");
    public static By EmployeeAnniversaryList = By.xpath("//div[@class='anniversary-body']//child::div[@class='anniversary-body-inner']");
    public static By AnniversaryNames = By.xpath("//div[@class='anniversary-body']//child::div[@class='anniversary-body-inner']//div[@class='name']");
    public static By AnniversaryDates = By.xpath("//div[@class='anniversary-body']//child::div[@class='anniversary-body-inner']//div[@class='date']");
    public static By NewMembersList = By.xpath("//div[@class='member-body']//child::div[@class='member-body-inner']//div[@class='member-info']");

    /////////////////////////////////////////////////////////
    public static By AllFiltersButton = By.xpath("//button[@class='postContainer_filterButton__19jIT']");
    public static By FiltersInsideAllFilters = By.xpath("//div[@class='postContainer_dropDown__3OFc6 postContainer_filterDiv__2lCU- postContainer_active__2rlz4']//child::ul//li");
    public static By TotalPosts = By.xpath("//div[@class='postContainer_post__17V6S']//child::div[@class='postContainer_postBody__90jsv']//div[@class='postContainer_postHeader__3aJRO']");
    public static By AllPostsTitle = By.xpath("//div[@class='postContainer_post__17V6S']//child::div[@class='postContainer_postBody__90jsv']//div[@class='postContainer_postHeader__3aJRO']//div[@class='postContainer_headerText__2DTR7']//div[@class='postContainer_details__2nzZl']//img");
    public static By NoPostsFoundMessage = By.xpath("//div[contains(text(),'No Post Found')]");
    public static By CopyLinkToPostLink = By.xpath("//div[@class='postContainer_dropDown__3OFc6 postContainer_active__2rlz4']//li[contains(text(),'Copy link to Post')]");
    public static By CopiedToastMessage = By.xpath("//div[@class='Toastify__toast-body' and @role='alert']");
    public static By AllPostsLikeComment = By.xpath("//div[@class='postContainer_post__17V6S']//child::div[@class='postContainer_postBody__90jsv']//child::div[@class='postContainer_postFooter__1am3d']");
    public static By LikeButton = By.xpath("//div[@class='postContainer_post__17V6S']//child::div[@class='postContainer_postBody__90jsv']//child::div[@class='postContainer_postFooter__1am3d']//div[@class='postContainer_footerBtns__1xAPm']//button[@class='postContainer_postLikeBtn__ZMVtx']");
    public static By CommentButton = By.xpath("//button[@class='postContainer_postCommentBtn__V06q0' and contains(text(),'Comment')]");
    public static By AllPostsImageContainerBody = By.xpath("//div[@class='postContainer_post__17V6S']//div[@class='postContainer_postBody__90jsv']//child::div[@class='postContainer_postContent__9eiPM']//div[@class='postContainer_imageContainer__kZNaT']//child::div[@class='carousel-inner']");
    public static By ImagePopUp = By.xpath("//div[@class='postContainer_post__17V6S']//div[@class='postContainer_postBody__90jsv']//div[@class='ImageModal_modalWrapper__3bEjR']//child::div[@class='ImageModal_imageWrapper__22a58']//img");
    public static By ImagePopUpClose = By.xpath("//div[@class='postContainer_post__17V6S']//div[@class='postContainer_postBody__90jsv']//div[@class='ImageModal_modalWrapper__3bEjR']//child::button");
    public static By ImagePrevButton = By.xpath("//div[@class='postContainer_post__17V6S']//div[@class='postContainer_postBody__90jsv']//child::div[@class='postContainer_postContent__9eiPM']//div[@class='postContainer_imageContainer__kZNaT']//child::a[@class='carousel-control-prev']");
    public static By ImageNextButton=By.xpath("//div[@class='postContainer_post__17V6S']//div[@class='postContainer_postBody__90jsv']//child::div[@class='postContainer_postContent__9eiPM']//div[@class='postContainer_imageContainer__kZNaT']//child::a[@class='carousel-control-next']");

}
