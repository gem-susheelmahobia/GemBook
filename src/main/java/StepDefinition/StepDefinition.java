package StepDefinition;

import Objects.Locators;
import Objects.Locators_Homepage;
import Utilities.Utility;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Scenario;
import io.cucumber.java.an.E;
import io.cucumber.java.bs.Ali;
import io.cucumber.java.en.*;
import io.cucumber.java.en_old.Ac;
import io.cucumber.java.et.Eeldades;
import io.cucumber.java.mk_latn.No;
import net.jodah.failsafe.internal.util.DelegatingExecutorService;
import oracle.jdbc.proxy.annotation.Pre;
import org.checkerframework.checker.units.qual.C;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;
import javax.xml.stream.events.Comment;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;

import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;


public class StepDefinition {
    Logger logger = LoggerFactory.getLogger(StepDefinition.class);
    public String mainWindowHandle = null;

    @When("^User is on correct url (.+)$")
    public void user_is_on_correct_url_url(String url) {
        try {
            DriverAction.waitSec(3);
            String currenturl = DriverAction.getCurrentURL();
            if (currenturl.equals(url))
                GemTestReporter.addTestStep("Url Validation", "Successful<br>Expected URL: " + url + "<br>Actual URL: " + currenturl, STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Url Validation", "Unsuccessful<br>Expected URL: " + url + "<br>Actual URL: " + currenturl, STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("Title is {string}")
    public void title_is(String title) {
        try {
            DriverAction.waitSec(4);
            String currentTitle = DriverAction.getTitle("https://gembook.geminisolutions.com/#/");
            if (currentTitle.equals(title)) {
                GemTestReporter.addTestStep("Title Validation", "Successful<br>Expected Title: " + title + "<br>Actual Title: " + currentTitle, STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.waitSec(3);
            } else
                GemTestReporter.addTestStep("Title Validation", "Unsuccessful<br>Expected Title: " + title + "<br>Actual Title: " + currentTitle, STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Logo is displayed")
    public void logo_is_displayed() {
        try {
            Boolean bool = DriverAction.getElement(Locators.GemBookLogo).isDisplayed();
            if (bool)
                GemTestReporter.addTestStep("Logo validation", " Logo is Displayed", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Logo validation", " Logo Not Displayed", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("Sign in button is enabled")
    public void signInButtonIsEnabled() {
        try {
            Boolean bool = DriverAction.getElement(Locators.SignUpButton).isEnabled();
            STATUS status;
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Sign In button validation", "Expected : Button is enabled", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User double click on Sign-in button")
    public void user_double_click_on_sign_in_button() {
        try {
            DriverAction.doubleClick(Locators.SignUpButton, "Sign Up Button");
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Login failed pop up will be displayed")
    public void login_failed_pop_up_will_be_displayed() {
        try {

            Boolean bool = DriverAction.getElement(Locators.WarnPopUp).isDisplayed();
            STATUS status;
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Failure Pop Up", "Expected : should be displayed", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on sign-in button")
    public void user_clicks_on_sign_in_button() {
        try {
            DriverAction.click(Locators.SignUpButton, "Sign Up Button");
            DriverAction.waitSec(3);
            mainWindowHandle = DriverAction.getWindowHandle();
            System.out.println(mainWindowHandle);
            DriverAction.waitSec(3);
            Set<String> setOfWindows = DriverAction.getWindowHandles();
            System.out.println(setOfWindows.size());
            Iterator<String> iterator = setOfWindows.iterator();
            while (iterator.hasNext()) {
                String ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                    DriverAction.switchToWindow(ChildWindow);
                    System.out.println("switching to child window");
                }
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User enters gmail {string} and password {string} and click on next button")
    public void user_enters_gmail_gmail_and_password_password_and_click_on_next_button(String gmail, String password) {
        try {
            DriverAction.waitSec(3);
            DriverAction.typeText(Locators.SignInField, gmail, "Gmail");
            DriverAction.waitSec(3);
            DriverAction.click(Locators.SignInNextButton, "Sign in Next Button");
            DriverAction.waitSec(3);
            DriverAction.typeText(Locators.PassWordField, password, "Password Field");
            DriverAction.click(Locators.PassWordNextButton, "Password Next Button");
            DriverAction.waitSec(4);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on No on stay signed in button")
    public void user_clicks_on_no_on_stay_signed_in_button() {
        try {
            DriverAction.getElement(Locators.StaySignedNoButton).click();
            //  DriverAction.click(Locators.StaySignedNoButton, "Stay Signed No Button"); // causing reporting error that's why commented out.
            DriverAction.waitSec(7);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Login will be successful and homepage will be displayed.")
    public void login_will_be_successful_and_homepage_will_be_displayed() {
        try {
            DriverAction.switchToWindow(mainWindowHandle);
            DriverAction.waitSec(7);
            Boolean bool = DriverAction.getElement(Locators_Homepage.HomePageLogo).isDisplayed();
            STATUS status;
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Check for logo ", "Expected : Logo is displayed", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("sidebar will be opened")
    public void sidebar_will_be_opened() {
        try {
            DriverAction.waitSec(3);
            Boolean bool = DriverAction.getElement(Locators_Homepage.HomePageLogo).isDisplayed();
            STATUS status;
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Check for sidebar ", "Expected : sidebar is opened", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("User clicks on toggle sidebar button")
    public void userClicksOnToggleSidebarButton() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.SidebarToggle, "Toggle button");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("sidebar get's closed.")
    public void sidebarGetSClosed() {
        try {
            DriverAction.waitSec(3);
            Boolean bool = DriverAction.getElement(Locators_Homepage.HomePageLogo).isDisplayed();
            STATUS status;
            if (bool)
                status = STATUS.FAIL;
            else
                status = STATUS.PASS;
            GemTestReporter.addTestStep("Check for sidebar ", "Expected : sidebar Should be closed ", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User again clicks on toggle sidebar button")
    public void userAgainClicksOnToggleSidebarButton() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.SidebarToggle, "Toggle button");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on news feeds from sidebar")
    public void userClicksOnNewsFeedsFromSidebar() {
        try {
            DriverAction.waitSec(7);
            DriverAction.click(Locators_Homepage.NewsFeeds, "News Feeds");
            DriverAction.waitSec(7);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("User should be on same screen")
    public void userShouldBeOnSameScreen() {
        try {
            DriverAction.waitSec(7);
            String string = DriverAction.getWindowHandle();
            DriverAction.waitSec(3);
            STATUS status;
            if (string.equals(mainWindowHandle))
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Check on which screen ", "Expected : Should be on same screen", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on Other portals link from sidebar")
    public void userClicksOnOtherPortalsLinkFromSidebar() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.OtherPortals, "Other Portals");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Portal links under other portals will not be displayed")
    public void portalLinksUnderOtherPortalsWillNotBeDisplayed() {
        try {
            DriverAction.waitSec(3);
            Integer count = DriverAction.getElements(Locators_Homepage.CountOfTotalLinksExcludingPortalsInsideOtherPortals).size();
            STATUS status;
            if (count == 2)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Check sidebar ", "Expected : Portals inside other portals will not be shown", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Portal links under other portals will be displayed")
    public void portalLinksUnderOtherPortalsWillBeDisplayed() {
        try {
            DriverAction.waitSec(3);
            Integer count = DriverAction.getElements(Locators_Homepage.CountOfTotalLinksExcludingPortalsInsideOtherPortals).size();
            STATUS status;
            if (count == 12)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Check sidebar ", "Expected : Portals inside other portals will be shown", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("All the links will be navigating to desired application")
    public void allTheLinksWillBeNavigatingToDesiredApplication() {
        try {
            ArrayList<String> Applicationstitle = new ArrayList<>(Arrays.asList("Service Desk - Help Desk Software by Vision Helpdesk",
                    "Contripoint", "Azure DevOps Services | Microsoft Azure", "Gemini MIS", "Jenkins", "greytHR IDP", "Gemini Solutions · GitHub"
                    , "Login | Gemini Wiki", "Athena", "L&D Gemini"));
            //  ArrayList<String> Applicationstitle = new ArrayList<>();
            DriverAction.waitSec(3);
            List<WebElement> Applications = DriverAction.getElements(Locators_Homepage.PortalsUnderOtherPortals);
            Iterator<WebElement> itr = Applications.iterator();
            while (itr.hasNext()) {
                WebElement element = itr.next();
                //  Applicationstitle.add(element.getAttribute("href"));
                element.click();
            }
            Set<String> applicationswindow = DriverAction.getWindowHandles();
            Iterator<String> iterator = applicationswindow.iterator();
            while (iterator.hasNext()) {
                String application = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(application)) {
                    DriverAction.waitSec(7);
                    DriverAction.switchToWindow(application);
                    DriverAction.waitSec(7);
                    String url_of_application = DriverAction.getCurrentURL();
                    System.out.println(url_of_application);
                    String title_of_applcation = DriverAction.getTitle(url_of_application);
                    System.out.println(title_of_applcation);                                 //line to be removed
                    System.out.println(Applicationstitle.contains(title_of_applcation));     //line to be removed
                    Boolean bool = Applicationstitle.contains(title_of_applcation);
                    STATUS status;
                    if (bool)
                        status = STATUS.PASS;
                    else
                        status = STATUS.FAIL;
                    GemTestReporter.addTestStep("Validate link ", "Expected : Directed to " + title_of_applcation + " Aplication", status, DriverAction.takeSnapShot());
                    DriverAction.switchToWindow(mainWindowHandle);
                }
                DriverAction.waitSec(7);
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User hovers over each portal links")
    public void userHoversOverEachPortalLinks() {
        try {
            Actions action = new Actions(DriverManager.getWebDriver());
            DriverAction.waitSec(3);
            List<WebElement> Applications = DriverAction.getElements(Locators_Homepage.PortalsUnderOtherPortals);
            Iterator<WebElement> itr = Applications.iterator();
            while (itr.hasNext()) {
                action.moveToElement(itr.next()).build().perform();
                GemTestReporter.addTestStep("Tool Tip", "Expected :Tool tip is displayed", STATUS.PASS, DriverAction.takeSnapShot());
                DriverAction.waitSec(3);
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks over profiles options")
    public void userClicksOverProfilesOptions() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.ProfileOptions, "Profile Options");
            DriverAction.waitSec(7);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Profiles option gets open.")
    public void profilesOptionGetsOpen() {
        try {
            DriverAction.waitSec(3);
            Boolean bool = DriverAction.getElement(Locators_Homepage.ViewProfiles).isDisplayed();
            STATUS status;
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Validate profiles option ", "Expected : Profile option gets open", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("User Clicks on Logout button.")
    public void userClicksOnLogoutButton() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.LogoutButton, "Logout button");
            DriverAction.waitSec(7);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("User will be logged out of application.")
    public void userWillBeLoggedOutOfApplication() {
        try {
            DriverAction.waitSec(3);
            Boolean bool = DriverAction.getElement(Locators.GemBookLogo).isDisplayed();
            STATUS status;
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep(" Logout check ", "Expected : Logout Successful ", status, DriverAction.takeSnapShot());
            DriverAction.waitSec(7);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on profile photo")
    public void userClicksOnProfilePhoto() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.ProfileImage, "Profile image");
            DriverAction.waitSec(3);

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }

    }

    @Then("^User will be navigated to user profile having (.+) mail id$")
    public void userWillBeNavigatedToUserProfileHavingGmailMailId(String ExpGmail) {
        try {
            DriverAction.waitSec(3);
            String Actgmail = DriverAction.getElement(Locators_Homepage.GmailIdUnderProfiles).getText();
            STATUS status;
            if (ExpGmail.equals(Actgmail))
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Profile check ", "Expected : Navigated to profiles", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("User go back to previous page")
    public void userGoBackToPreviousPage() {
        try {
            DriverAction.waitSec(3);
            DriverAction.navigateBack(true);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("User clicks on view profile option")
    public void userClicksOnViewProfileOption() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.ViewProfiles, "View Profiles");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("^User clicks on (.+) from text editor options$")
    public void userClicksOnTypeFromTextEditorOptions(String Type) {
        try {
            DriverAction.waitSec(10);
            if (Type.equalsIgnoreCase("Bold"))
                DriverAction.click(Locators_Homepage.BoldType, "Bold ");
            else if (Type.equalsIgnoreCase("underline"))
                DriverAction.click(Locators_Homepage.UnderlinedType, "Underline");
            else if (Type.equalsIgnoreCase("italic"))
                DriverAction.click(Locators_Homepage.ItalicType, "Italic");
            else if (Type.equalsIgnoreCase("strikethrough"))
                DriverAction.click(Locators_Homepage.StrikeType, "Strikethrough");
            else
                GemTestReporter.addTestStep("Select text type", "Error in selecting Text type", STATUS.FAIL);

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("^User enters text as (.+)$")
    public void userEntersTextAsContent(String content) {
        try {
            DriverAction.waitSec(3);
            DriverAction.getElement(Locators_Homepage.PostTextBox).sendKeys(content);
            GemTestReporter.addTestStep("Entering text ", "Expected : Entering text successful", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("^entered text is of selected (.+)$")
    public void validateEnteredText(String Type) {
        try {
            DriverAction.waitSec(3);
            String tagname = DriverAction.getElement(Locators_Homepage.Content).getTagName();
            if (tagname.equals("strong") && Type.equalsIgnoreCase("bold"))
                GemTestReporter.addTestStep("Text Type check", "Expected Type :" + Type + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else if (tagname.equals("u") && Type.equalsIgnoreCase("underline"))
                GemTestReporter.addTestStep("Text Type check", "Expected Type :" + Type + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else if (tagname.equals("s") && Type.equalsIgnoreCase("strikethrough"))
                GemTestReporter.addTestStep("Text Type check", "Expected Type " + Type + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else if (tagname.equals("em") && Type.equalsIgnoreCase("italic"))
                GemTestReporter.addTestStep("Text Type check", "Expected Type :" + Type + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Text Type check", "Not Expected Type", STATUS.FAIL);

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("^entered text will be of provided types (.+) and (.+)$")
    public void enteredTextWillBeOfProvidedTypesTypeAndType(String Type1, String Type2) {
        try {
            JSONObject json = new JSONObject();
            json.put("bold", "strong");
            json.put("Bold", "strong");
            json.put("italic", "em");
            json.put("Italic", "em");
            json.put("underline", "u");
            json.put("Underline", "u");
            json.put("strikethrough", "s");
            json.put("Strikethrough", "s");
//            System.out.println(json.get(Type1));
//            System.out.println(json.get(Type2));
            Type1 = json.getString(Type1);
            Type2 = json.getString(Type2);

//            switch (Type1.toLowerCase()) {
//                case "bold":
//                    Type1 = "strong";
//                    break;
//                case "italic":
//                    Type1 = "em";
//                    break;
//                case "underline":
//                    Type1 = "u";
//                    break;
//                case "strikethrough":
//                    Type1 = "s";
//                    break;
//            }
//            switch (Type2.toLowerCase()) {
//                case "bold":
//                    Type2 = "strong";
//                    break;
//                case "italic":
//                    Type2 = "em";
//                    break;
//                case "underline":
//                    Type2 = "u";
//                    break;
//                case "strikethrough":
//                    Type2 = "s";
//                    break;
//            }
            STATUS status;
            if (Type1.equals(Type2)) {
                String tagname = DriverAction.getElement(Locators_Homepage.ContentPtag).getTagName();
                if (tagname.equals("p"))
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("Text Type Check", "Expected : Proper text format", status, DriverAction.takeSnapShot());
                return;
            }
            List<String> list_of_tags_from_file = new ArrayList<>();
            list_of_tags_from_file.add(Type1);
            list_of_tags_from_file.add(Type2);
            DriverAction.waitSec(3);
            List<String> list_of_tags = new ArrayList<>();
            List<WebElement> Textformattingtypes = DriverAction.getElements(Locators_Homepage.Content);
            Iterator<WebElement> Texttypes = Textformattingtypes.iterator();
            while (Texttypes.hasNext()) {
                list_of_tags.add(Texttypes.next().getTagName());
            }
            if (list_of_tags_from_file.containsAll(list_of_tags) && list_of_tags.containsAll(list_of_tags_from_file))
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Text Type Check", "Expected : Proper text format", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("^user click on colour (.+) to change the colour of text$")
    public void userClickOnColoursToChangeTheColourOfText(String Colour) {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.ColourChart, "Colour Chart");
            List<WebElement> AllColours = DriverAction.getElements(Locators_Homepage.ColoursInColourChart);
            Iterator<WebElement> itr = AllColours.iterator();
            while (itr.hasNext()) {
                WebElement element = itr.next();
                String str = element.getAttribute("data-value");
                if (str.equals(Colour)) {
                    element.click();
                    GemTestReporter.addTestStep("Select colour", "Colour selected successfully", STATUS.PASS, DriverAction.takeSnapShot());
                    return;
                }
            }
            DriverAction.waitSec(7);

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("^entered text will be of (.+) colour$")
    public void enteredTextWillBeOfColours(String Colour) {
        try {
            DriverAction.waitSec(3);
            String str = DriverAction.getElement(Locators_Homepage.Content).getCssValue("color");
            String Actual_colour = Color.fromString(str).asHex();
            STATUS status;
            if (Actual_colour.equals(Colour))
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Colour Validation", "Expected Colour", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("User selects the entered test")
    public void userSelectsTheEnteredTest() {
        try {
            DriverAction.waitSec(3);
            DriverAction.getElement(Locators_Homepage.PostTextBox).sendKeys(Keys.CONTROL + "A");
            GemTestReporter.addTestStep("Selecting the text", "Expected : Text should be selected", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.waitSec(3);

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Formatting should be removed")
    public void formattingShouldBeRemoved() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.PostTextBox, "Text Box");
            String tagname = DriverAction.getElement(Locators_Homepage.ContentPtag).getTagName();
            STATUS status;
            if (tagname.equals("p"))
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Text Type Check", "Expected : Proper text format", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("^entered text will be of provided colour's (.+) and (.+)$")
    public void enteredTextWillBeOfProvidedColourSColoursAndColours(String Colour1, String Colour2) {
        try {
            DriverAction.waitSec(3);
            List<String> list_of_colours_from_file = new ArrayList<>();
            list_of_colours_from_file.add(Colour1);
            list_of_colours_from_file.add(Colour2);
            List<WebElement> list_of_colours = DriverAction.getElements(Locators_Homepage.Content);
            Iterator<WebElement> colour_list = list_of_colours.iterator();
            List<String> list_of_colour_obtained = new ArrayList<>();
            while (colour_list.hasNext()) {
                String Actual_color = colour_list.next().getCssValue("color");
                String Act_color = Color.fromString(Actual_color).asHex();
                list_of_colour_obtained.add(Act_color);
            }
            STATUS status;
            if (list_of_colours_from_file.containsAll(list_of_colour_obtained) && list_of_colour_obtained.containsAll(list_of_colours_from_file))
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Text color check", "Expected Text colour", status, DriverAction.takeSnapShot());


        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("User clicks on Text box")
    public void userClicksOnTextBox() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.PostTextBox);

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("^User click on alignment (.+) to change the alignment of text$")
    public void userClickOnAlignmentToChangeTheAlignmentOfText(String Alignment) {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.AlignChart, "Alignment types");
            if (Alignment.equalsIgnoreCase("right"))
                DriverAction.click(Locators_Homepage.RigtAlign, "Right Align");
            else if (Alignment.equalsIgnoreCase("center"))
                DriverAction.click(Locators_Homepage.CenterAlign, "Center Align");
            else if (Alignment.equalsIgnoreCase("justify"))
                DriverAction.click(Locators_Homepage.JustifyAlign, "Justify Align");
            else if (Alignment.equalsIgnoreCase("left"))
                DriverAction.click(Locators_Homepage.LeftAlign, "Left Align");
            else
                GemTestReporter.addTestStep("Select Align type", "Error in selecting Align type", STATUS.FAIL);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("^The entered text will be aligned (.+)$")
    public void theEnteredTextWillBeAlignedAlignment(String ExpAlignment) {
        try {
            String ActualAlignment = DriverAction.getElement(Locators_Homepage.ContentPtag).getCssValue("text-align");
            if (ExpAlignment.equalsIgnoreCase("Right") && ActualAlignment.equalsIgnoreCase("right"))
                GemTestReporter.addTestStep("Text Alignment check", "Expected Type :" + ExpAlignment + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else if (ExpAlignment.equalsIgnoreCase("Left") && ActualAlignment.equalsIgnoreCase("Left"))
                GemTestReporter.addTestStep("Text Alignment check", "Expected Type :" + ExpAlignment + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else if (ExpAlignment.equalsIgnoreCase("Center") && ActualAlignment.equalsIgnoreCase("Center"))
                GemTestReporter.addTestStep("Text Alignment check", "Expected Type :" + ExpAlignment + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else if (ExpAlignment.equalsIgnoreCase("Justify") && ActualAlignment.equalsIgnoreCase("Justify"))
                GemTestReporter.addTestStep("Text Alignment check", "Expected Type :" + ExpAlignment + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Text Alignment check", "Not Expected Type", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User verify default tab in events section")
    public void userVerifyDefaultTabInEventsSection() {
        try {
            DriverAction.waitSec(3);
            String DefaultTab = DriverAction.getElement(Locators_Homepage.DefaultEventTab).getText();
            STATUS status;
            if (DefaultTab.equals("Ongoing&Upcoming"))
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Events Section Default tab", "Expected tab " + DefaultTab + " ", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("^User clicks on (.+) event button$")
    public void userClicksOnPastEventButton(String Type) {
        try {
            DriverAction.waitSec(3);
            if (Type.equalsIgnoreCase("Past"))
                DriverAction.click(Locators_Homepage.PastEvent, "Past event");
            else if (Type.equalsIgnoreCase("Ongoing&Upcoming"))
                DriverAction.click(Locators_Homepage.OngoingEvent, "Ongoing&Upcoming event");
            else
                GemTestReporter.addTestStep("Event select", "Event selection failed", STATUS.FAIL);
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("^(.+) events tab will be opened$")
    public void eventsTabWillBeOpened(String Type) {
        try {
            DriverAction.waitSec(3);
            String Tab = DriverAction.getElement(Locators_Homepage.DefaultEventTab).getText();
            if (Type.equalsIgnoreCase("Ongoing&Upcoming") && Tab.equalsIgnoreCase(Type))
                GemTestReporter.addTestStep("Events Section selected tab", "Selected tab " + Tab + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else if (Type.equalsIgnoreCase("Past") && Tab.equalsIgnoreCase(Type))
                GemTestReporter.addTestStep("Events Section Selected tab", "Selected tab " + Tab + " ", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Events Section Default tab", "Expected tab " + Tab + " ", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("Validate the events in the tab")
    public void validateTheEventsInTheTab() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            List<String> list_of_keys = new ArrayList<>();
            List<String> list_of_pairs = new ArrayList<>();
            Integer NoOfEvents = DriverAction.getElements(Locators_Homepage.EventLists).size();
            STATUS status;
            if (NoOfEvents == 0) {
                Boolean bool = DriverAction.getElement(Locators_Homepage.NoEventsMessage).isDisplayed();
                if (bool)
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("No Events Present", "No events present", status, DriverAction.takeSnapShot());
                return;
            }
            List<WebElement> event_lists = DriverAction.getElements(Locators_Homepage.EventLists);

            List<WebElement> list1 = event_lists.get(0).findElements(By.xpath("li//b"));   // Here , we are preparing a set of keys,i.e name , desc ,end date , start date for a record which will be common to every record.
            //and storing it in a list i.e set of keys.
            for (int i = 0; i < list1.size(); i++) {
                list_of_keys.add(list1.get(i).getText());
            }

            Iterator<WebElement> list_of_events = event_lists.iterator();
            while (list_of_events.hasNext()) {
                WebElement SingleEvent = list_of_events.next();
                js.executeScript("arguments[0].scrollIntoView(true);", SingleEvent);
                js.executeScript("window.scrollTo(0,0)");
                List<WebElement> All_points_Single_Event = SingleEvent.findElements(By.xpath("li//b"));
                for (int i = 0; i < All_points_Single_Event.size(); i++) {               //Here for every event record we are storing there keys to this list
                    list_of_pairs.add(All_points_Single_Event.get(i).getText());
                }
                if (list_of_keys.containsAll(list_of_pairs) && list_of_pairs.containsAll(list_of_keys))   //here we comparing now both the lists if contains same keys or not.
                    GemTestReporter.addTestStep("Check event data", "Coming as Expected", STATUS.PASS, DriverAction.takeSnapShot());
                else
                    GemTestReporter.addTestStep("Check event data", "Not as Expected", STATUS.FAIL, DriverAction.takeSnapShot());
                list_of_pairs.clear();

            }


        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("Validate the events in the tab are in descending order")
    public void validateTheEventsInTheTabAreInDescendingOrder() {
        try {
            int NoOfEvents = DriverAction.getElements(Locators_Homepage.EventLists).size();
            STATUS status;
            if (NoOfEvents == 0) {
                Boolean bool = DriverAction.getElement(Locators_Homepage.NoEventsMessage).isDisplayed();
                if (bool)
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("No Events Present", "No events present", status, DriverAction.takeSnapShot());
                return;
            }
            List<String> list_of_pairs = new ArrayList<>();
            ArrayList<LocalDate> datesList = new ArrayList<LocalDate>();
            List<WebElement> event_lists = DriverAction.getElements(Locators_Homepage.EventLists);
            Iterator<WebElement> list_of_events = event_lists.iterator();
            while (list_of_events.hasNext()) {
                WebElement SingleEvent = list_of_events.next();
                List<WebElement> list_of_all_points = SingleEvent.findElements(By.xpath("li"));
                for (int i = 0; i < list_of_all_points.size(); i++) {
                    if (list_of_all_points.get(i).getText().contains("Event Date"))
                        list_of_pairs.add(list_of_all_points.get(i).getText().split("Date")[1].trim());
                }
            }
            for (int i = 0; i < list_of_pairs.size(); i++) {
                datesList.add(Utility.Dateformatting(list_of_pairs.get(i)));
            }
            ArrayList<LocalDate> sortedDatesList = new ArrayList<>(Utility.sortDateList(datesList));
            if (datesList.equals(sortedDatesList))
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Event End dates order", "Dates are on expected Descending order", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("^User select year (.+) from select year dropdown$")
    public void userSelectYearYearFromSelectYearDropdown(String Year) {
        try {
            DriverAction.waitSec(3);
            Select YearDropdown = new Select(DriverAction.getElement(Locators_Homepage.SelectYearDropdown));
            List<WebElement> ListOfYears = YearDropdown.getOptions();
            Iterator<WebElement> YearsList = ListOfYears.iterator();
            STATUS status;
            int flag = 0;
            if (Year.equalsIgnoreCase("Select Year")) {
                YearDropdown.selectByVisibleText("Select Year");
                GemTestReporter.addTestStep("Select Year", "Expected year " + Year + " ", STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }

            while (YearsList.hasNext()) {
                WebElement singleyear = YearsList.next();
                if (singleyear.getText().equals(Year)) {
                    singleyear.click();
                    flag = 1;
                }
            }
            if (flag == 1)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Select Year", "Expected year " + Year + " ", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("User will get list of events")
    public void userWillGetListOfEvents() {
        try {
            DriverAction.waitSec(3);
            //   DriverAction.click(Locators_Homepage.SelectEventsDropdown,"Select Event");
            Select EventsDropdown = new Select(DriverAction.getElement(Locators_Homepage.SelectEventsDropdown));
            List<WebElement> ListOfEvents = EventsDropdown.getOptions();
            int NoOfEvents = ListOfEvents.size();
            if (NoOfEvents == 1 && ListOfEvents.get(0).getText().equals("Select Event")) {
                GemTestReporter.addTestStep("Select Event", "No events present", STATUS.PASS, DriverAction.takeSnapShot());
                // return;
            }


        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }


    @And("User clicking on to any events will give the names of person who won the awards")
    public void userClickingOnToAnyEventsWillGiveTheNamesOfPersonWhoWonTheAwards() {
        try {
            DriverAction.waitSec(3);
            Select EventsDropdown = new Select(DriverAction.getElement(Locators_Homepage.SelectEventsDropdown));
            List<WebElement> ListOfEvents = EventsDropdown.getOptions();
            STATUS status;
            int NoOfEvents = ListOfEvents.size();
            if (NoOfEvents == 1 && ListOfEvents.get(0).getText().equals("Select Event")) {
                GemTestReporter.addTestStep("Select Event", "No events present to select ", STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                int randomEvent = random.nextInt(NoOfEvents);
                if (randomEvent == 0) {
                    continue;
                }
                System.out.println("index no" + randomEvent);
                EventsDropdown.selectByIndex(randomEvent);
                int No_of_persons = DriverAction.getElements(Locators_Homepage.PersonsWinningAwards).size();
                System.out.println(No_of_persons);
                if (No_of_persons > 0)
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                DriverAction.waitSec(3);
                GemTestReporter.addTestStep("Select Event", "Event selected successfully & names are displayed ", status, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("User clicking on to any events will give the list of persons and clicking on to persons name will open user profile")
    public void userClickingOnToAnyEventsWillGiveTheListOfPersonsAndClickingOnToPersonsNameWillOpenUserProfiles() {
        try {

            DriverAction.waitSec(3);
            Select EventsDropdown = new Select(DriverAction.getElement(Locators_Homepage.SelectEventsDropdown));
            List<WebElement> ListOfEvents = EventsDropdown.getOptions();
            STATUS status;
            int NoOfEvents = ListOfEvents.size();
            if (NoOfEvents == 1 && ListOfEvents.get(0).getText().equals("Select Event")) {
                GemTestReporter.addTestStep("Select Event", "No events present to select ", STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }
            Random rand = new Random();
            int low = 1;
            int high = NoOfEvents;
            int RandomEvent = rand.nextInt(high - low) + low;
            ListOfEvents.get(RandomEvent).click();
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            WebElement personsUl = DriverAction.getElement(Locators_Homepage.PersonsWinningAwardsUl);
            wait.until(ExpectedConditions.visibilityOf(personsUl));
            GemTestReporter.addTestStep("Select Event", "Event Selected Successfully : " + EventsDropdown.getFirstSelectedOption().getText() + "", STATUS.PASS, DriverAction.takeSnapShot());
            List<WebElement> ListOfPersons = DriverAction.getElements(Locators_Homepage.PersonsWinningAwards);
            //Random rand=new Random();
            int randomPersonNo = rand.nextInt(ListOfPersons.size());
            String class_of_employee = ListOfPersons.get(randomPersonNo).getAttribute("class");                 // To check weather the person is existing employee or not
            String Name_of_Person = ListOfPersons.get(randomPersonNo).getText();
            if (class_of_employee.contains("sidebar_existingEmployee__1Cs7F")) {
                GemTestReporter.addTestStep("Click to User", "Expected : " + Name_of_Person + "- User Clicked successfully", STATUS.PASS, DriverAction.takeSnapShot());
                ListOfPersons.get(randomPersonNo).click();
                Boolean bool = Utility.verifyUserProfile(Name_of_Person);
                if (bool)
                    GemTestReporter.addTestStep("Profile Validation", "Expected :" + Name_of_Person + " Valid profile ", STATUS.PASS, DriverAction.takeSnapShot());
                else
                    GemTestReporter.addTestStep("Profile Validation", "Expected :" + Name_of_Person + " Valid profile ", STATUS.FAIL, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Profile Validation", "Expected :" + Name_of_Person + "User is Not existing  ", STATUS.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User verifies the employee birthday list")
    public void UserVerifiesTheEmployeeBirthdayList() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            List<WebElement> Employee_birthday_list = DriverAction.getElements(Locators_Homepage.EmployeeBirthdayList);  //storing the complete list of birthdays
            Iterator<WebElement> employee_birthdays = Employee_birthday_list.iterator();
            STATUS status;
            while (employee_birthdays.hasNext()) {
                WebElement employee_birthday = employee_birthdays.next();                 //getting single birthday from list of birthdays.
                WebElement employee_name_element = employee_birthday.findElement(By.xpath("div[@class='name']"));          //getting name of employee
                WebElement employee_birth_date_element = employee_birthday.findElement(By.xpath("div[@class='date']"));           //getting birthday date of employee
                String employee_name = employee_name_element.getText();
                String employee_birth_date = employee_birth_date_element.getText();
                js.executeScript("arguments[0].scrollIntoView(true);", employee_birthday);
                js.executeScript("window.scrollTo(0,0)");
                if (employee_name_element.isDisplayed() && employee_birth_date_element.isDisplayed())                //for every employee checking that name and birthday date will be displayed
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("Employee Birthday", "Expected : " + employee_name + ": Birth : " + employee_birth_date, status, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User verifies the birth dates in birthday list")
    public void userVerifiesTheBirthDatesInBirthdayList() {
        try {
            DriverAction.waitSec(3);
            List<WebElement> List_of_Birthday_Date = DriverAction.getElements(Locators_Homepage.BirthdayDates);   // storing all the birthday dates in list as web element.
            Iterator<WebElement> Birthdy_dates = List_of_Birthday_Date.iterator();
            ArrayList<String> Birthday_dates_in_format = new ArrayList<>();     // Array to store the converted dates from above array in proper format
            STATUS status;
            while (Birthdy_dates.hasNext()) {
                Birthday_dates_in_format.add(Utility.changeDateFormatting(Birthdy_dates.next().getText()));     //calling the function from utility class and passing the unformatted dates to get the formatted dates via function from utility class.
            }
            Boolean bool = Utility.validateAllDatesPresentIsInRangeOf7daysFromNow(Birthday_dates_in_format);  //passing the formatted dates array to Function in util class to validate that each dates in formatted dates array
            if (bool)                                                                                           //are in range of 7 days from now.
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Birthday Dates Validation", "All birth dates are from current date till next 7 days", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User validates the complete profile details are visible after clicking to employee name in birthday's list")
    public void userValidatesTheCompleteProfileDetailsAreVisibleAfterClickingToEmployeeNameInBirthdayList() {
        try {
            DriverAction.waitSec(3);
            List<WebElement> List_of_Birthday_names = DriverAction.getElements(Locators_Homepage.BirthdayNames);
            for (int i = 0; i < List_of_Birthday_names.size(); i++) {
                List_of_Birthday_names = DriverAction.getElements(Locators_Homepage.BirthdayNames);
                WebElement single_birthday_name = List_of_Birthday_names.get(i);
                String name_single_birthday_name = single_birthday_name.getText();
                DriverAction.click(single_birthday_name, "Employee name");
                Boolean bool = Utility.verifyUserProfile(name_single_birthday_name);
                if (bool)
                    GemTestReporter.addTestStep("Profile Validation", "Expected :" + name_single_birthday_name + " Valid profile ", STATUS.PASS, DriverAction.takeSnapShot());
                else
                    GemTestReporter.addTestStep("Profile Validation", "Expected :" + name_single_birthday_name + " Valid profile ", STATUS.FAIL, DriverAction.takeSnapShot());
                DriverAction.navigateBack(true);
            }

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User verifies the employee anniversary list")
    public void userVerifiesTheEmployeeAnniversaryList() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            List<WebElement> Employee_anniversary_list = DriverAction.getElements(Locators_Homepage.EmployeeAnniversaryList);    // storing the complete list of anniversary's
            Iterator<WebElement> employee_anniversary = Employee_anniversary_list.iterator();
            STATUS status;
            while (employee_anniversary.hasNext())                                                                            // taking the single employee's anniversary from the list of anniversary.
            {
                WebElement single_employee_anni = employee_anniversary.next();
                WebElement single_employee_anni_name_element = single_employee_anni.findElement(By.xpath("div[@class='name']"));    // name of the employee anniversary
                WebElement single_employee_anni_date_element = single_employee_anni.findElement(By.xpath("div[@class='date']"));     // date of the emplyee anniversary
                String single_employee_anni_name = single_employee_anni_name_element.getText();
                String single_employee_anni_date = single_employee_anni_date_element.getText();
                js.executeScript("arguments[0].scrollIntoView(true);", single_employee_anni);
                js.executeScript("window.scrollTo(0,600)");
                if (single_employee_anni_name_element.isDisplayed() && single_employee_anni_date_element.isDisplayed())                //for every employee checking that name and birthday date will be displayed
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("Employee Birthday", "Expected : " + single_employee_anni_name + ": Birth : " + single_employee_anni_date, status, DriverAction.takeSnapShot());

            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User validates the complete profile details are visible after clicking to employee name in anniversary list")
    public void userValidatesTheCompleteProfileDetailsAreVisibleAfterClickingToEmployeeNameInAnniversaryList() {
        try {
            DriverAction.waitSec(3);
            List<WebElement> List_anniversary_names = DriverAction.getElements(Locators_Homepage.AnniversaryNames);
            STATUS status;
            for (int i = 0; i < List_anniversary_names.size(); i++) {
                List_anniversary_names = DriverAction.getElements(Locators_Homepage.AnniversaryNames);
                WebElement single_anniversary_name = List_anniversary_names.get(i);
                String name_single_anniversary_name = single_anniversary_name.getText();
                DriverAction.click(single_anniversary_name, "Employee name");
                Boolean bool = Utility.verifyUserProfile(name_single_anniversary_name);
                if (bool)
                    GemTestReporter.addTestStep("Profile Validation", "Expected :" + name_single_anniversary_name + " Valid profile ", STATUS.PASS, DriverAction.takeSnapShot());
                else
                    GemTestReporter.addTestStep("Profile Validation", "Expected :" + name_single_anniversary_name + " Valid profile ", STATUS.FAIL, DriverAction.takeSnapShot());
                DriverAction.navigateBack(true);
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User verifies the anniversary dates in anniversary list")
    public void userVerifiesTheAnniversaryDatesInAnniversaryList() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            List<WebElement> List_of_Anniversary_Date = DriverAction.getElements(Locators_Homepage.AnniversaryDates);   // storing all the anniversary dates in list as web element.
            js.executeScript("window.scrollTo(0,600)");
            Iterator<WebElement> Anniversary_dates = List_of_Anniversary_Date.iterator();
            ArrayList<String> Anniversary_dates_in_format = new ArrayList<>();     // Array to store the converted dates from above array in proper format
            STATUS status;
            while (Anniversary_dates.hasNext()) {
                Anniversary_dates_in_format.add(Utility.changeDateFormatting(Anniversary_dates.next().getText()));     //calling the function from utility class and passing the unformatted dates to get the formatted dates via function from utility class.
            }
            Boolean bool = Utility.validateAllDatesPresentIsInRangeOf7daysFromNow(Anniversary_dates_in_format);  //passing the formatted dates array to Function in util class to validate that each dates in formatted dates array
            if (bool)                                                                                           //are in range of 7 days from now.
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Anniversary Dates Validation", "All anniversary dates are from current date till next 7 days", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User verifies the new members list")
    public void userVerifiesTheNewMembersList() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            js.executeScript("window.scrollTo(0,600)");
            STATUS status;
            List<WebElement> List_new_members = DriverAction.getElements(Locators_Homepage.NewMembersList);
            Iterator<WebElement> new_members_list = List_new_members.iterator();
            while (new_members_list.hasNext()) {
                WebElement single_new_member = new_members_list.next();
                if (single_new_member.isDisplayed())
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("New Member ", "Expected : " + single_new_member.getText(), status, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User validates the complete profile details are visible after clicking to employee name in new members list")
    public void userValidatesTheCompleteProfileDetailsAreVisibleAfterClickingToEmployeeNameInNewMembersList() {
        try {
            DriverAction.waitSec(3);
            List<WebElement> List_new_members = DriverAction.getElements(Locators_Homepage.NewMembersList);
            STATUS status;
            for (int i = 0; i < List_new_members.size(); i++) {
                List_new_members = DriverAction.getElements(Locators_Homepage.NewMembersList);
                WebElement single_newmember_name = List_new_members.get(i);
                String name_single_newmember_name = single_newmember_name.getText();
                DriverAction.click(single_newmember_name, "Employee name");
                Boolean bool = Utility.verifyUserProfile(name_single_newmember_name);
                if (bool)
                    GemTestReporter.addTestStep("Profile Validation", "Expected :" + name_single_newmember_name + " Valid profile ", STATUS.PASS, DriverAction.takeSnapShot());
                else
                    GemTestReporter.addTestStep("Profile Validation", "Expected :" + name_single_newmember_name + " Valid profile ", STATUS.FAIL, DriverAction.takeSnapShot());
                DriverAction.navigateBack(true);
            }

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on All filters button")
    public void userClicksOnAllFiltersButton() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.AllFiltersButton, "All filters");

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Dropdown containing all the filters will open")
    public void dropdownContainingAllTheFiltersWillOpen() {
        try {
            DriverAction.waitSec(3);
            List<WebElement> All_filters_list = DriverAction.getElements(Locators_Homepage.FiltersInsideAllFilters);
            Iterator<WebElement> All_filters = All_filters_list.iterator();
            STATUS status;
            while (All_filters.hasNext()) {
                WebElement filter = All_filters.next();
                Boolean bool = filter.isDisplayed();
                if (bool)
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("All filters", "Filter inside All filter : " + filter.getText(), status, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("^User verifies the selected filter should be (.+)$")
    public void userVerifiesTheSelectedFilterShouldBe(String filter) {
        try {
            DriverAction.waitSec(1);
            String applied_filter = DriverAction.getElement(Locators_Homepage.AllFiltersButton).getText();
            if (filter.equalsIgnoreCase(applied_filter) && applied_filter.equalsIgnoreCase("All"))
                GemTestReporter.addTestStep("Applied filter", "Applied filter same as selected : " + filter, STATUS.PASS, DriverAction.takeSnapShot());
            else if (filter.equalsIgnoreCase(applied_filter) && applied_filter.equalsIgnoreCase("Achievements"))
                GemTestReporter.addTestStep("Applied filter", "Applied filter same as selected : " + filter, STATUS.PASS, DriverAction.takeSnapShot());
            else if (filter.equalsIgnoreCase(applied_filter) && applied_filter.equalsIgnoreCase("Announcements"))
                GemTestReporter.addTestStep("Applied filter", "Applied filter same as selected : " + filter, STATUS.PASS, DriverAction.takeSnapShot());
            else if (filter.equalsIgnoreCase(applied_filter) && applied_filter.equalsIgnoreCase("Questions"))
                GemTestReporter.addTestStep("Applied filter", "Applied filter same as selected : " + filter, STATUS.PASS, DriverAction.takeSnapShot());
            else if (filter.equalsIgnoreCase(applied_filter) && applied_filter.equalsIgnoreCase("Generic"))
                GemTestReporter.addTestStep("Applied filter", "Applied filter same as selected : " + filter, STATUS.PASS, DriverAction.takeSnapShot());
            else if (filter.equalsIgnoreCase(applied_filter) && applied_filter.equalsIgnoreCase("Ideas"))
                GemTestReporter.addTestStep("Applied filter", "Applied filter same as selected : " + filter, STATUS.PASS, DriverAction.takeSnapShot());
            else if (filter.equalsIgnoreCase(applied_filter) && applied_filter.equalsIgnoreCase("Findings"))
                GemTestReporter.addTestStep("Applied filter", "Applied filter same as selected : " + filter, STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Applied filter", "Applied filter same as selected : " + filter, STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("^User selects the new filter (.+)$")
    public void userSelectsTheNewFilterFilter(String filter) {
        try {
            DriverAction.waitSec(1);
            List<WebElement> All_filters_list = DriverAction.getElements(Locators_Homepage.FiltersInsideAllFilters);
            Iterator<WebElement> All_filters = All_filters_list.iterator();
            STATUS status;
            while (All_filters.hasNext()) {
                WebElement single_filter = All_filters.next();
                String single_filter_name = single_filter.getText();
                if (single_filter_name.equalsIgnoreCase(filter))
                    DriverAction.click(single_filter, "Filter : " + single_filter_name);
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("User clicks on filters button")
    public void userClicksOnFiltersButton() {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators_Homepage.AllFiltersButton, "Filters button");
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("^User validate posts should be of applied filter type (.+)$")
    public void userValidatePostsShouldBeOfAppliedFilterType(String Filter) {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            Utility.scrollToBottomOfThePageDynamically(js);                         //page is dynamic so to get all records first we are scrolling to the end of the page.
            js.executeScript("window.scrollTo(0,0)");                 //Scrolling again to the top of the page .
            List<WebElement> List_of_posts = DriverAction.getElements(Locators_Homepage.TotalPosts);   //Maintaing the list containing all the posts.
            List<String> title_names = new ArrayList<String>();                                  //Maintaining the list to store all the post's title. including duplicates
            List<WebElement> Titles_of_all_posts = DriverAction.getElements(Locators_Homepage.AllPostsTitle);    //  Maintaining the web element list to store all the post's title. including the duplicates.
            Iterator<WebElement> all_posts_titles = Titles_of_all_posts.iterator();
            while (all_posts_titles.hasNext()) {
                title_names.add(all_posts_titles.next().getAttribute("title"));
            }
            List<String> Unique_title_list = title_names.stream().distinct().collect(Collectors.toList());   //List to store the unique titles from the duplicates title from the title names list.
            STATUS status;
            int NoOfPosts = List_of_posts.size();
            if (NoOfPosts == 0) {                                                          // condition to check whether the filter contains no post and hence take this action if the filter has no post.
                if (DriverAction.getElement(Locators_Homepage.NoPostsFoundMessage).isDisplayed())   //in this case checking for No records found message.
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("Posts Validation", "No Posts as per applied filter : " + Filter, status, DriverAction.takeSnapShot());

            } else if (Filter.equalsIgnoreCase("All")) {  // condition to check whether the filter "All" is selected in this case needs to check that all post should come.
                for (int i = 0; i < NoOfPosts; i++) {
                    WebElement single_post = List_of_posts.get(i);
                    js.executeScript("arguments[0].scrollIntoView(true);", single_post);
                    WebElement single_post_title = single_post.findElement(By.xpath("div[@class='postContainer_headerText__2DTR7']//div[@class='postContainer_details__2nzZl']//child::img"));
                    String post_title = single_post_title.getAttribute("title");
                    //   if (single_post.isDisplayed())               //verifying that each post is displayed or not.
                    if (Unique_title_list.contains(post_title))           //verifying that all the posts title should be contained on unique title list created.
                        status = STATUS.PASS;
                    else
                        status = STATUS.FAIL;
                    GemTestReporter.addTestStep("Posts Validation", "Posts as per applied filter : " + Filter, status, DriverAction.takeSnapShot());
                }

            } else {
                for (int i = 0; i < NoOfPosts; i++) {
                    WebElement single_post = List_of_posts.get(i);
                    js.executeScript("arguments[0].scrollIntoView(true);", single_post);
                    WebElement single_post_title = single_post.findElement(By.xpath("div[@class='postContainer_headerText__2DTR7']//div[@class='postContainer_details__2nzZl']//child::img"));       // getting the post title.
                    String post_title = single_post_title.getAttribute("title");
                    if (post_title.equalsIgnoreCase(Filter))                           //verifing the title obtained with provided title after applying filter.
                        status = STATUS.PASS;
                    else
                        status = STATUS.FAIL;
                    GemTestReporter.addTestStep("Posts Validation", "Posts as per applied filter : " + Filter, status, DriverAction.takeSnapShot());
                }


            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("User refresh the page")
    public void userRefreshThePage() {
        DriverAction.refresh(true);
    }

    @When("^User clicks on profile Photo/Name (.+) of the user who has already posted$")
    public void userClicksOnProfilePhotoNameOfTheUserWhoHasAlreadyPosted(String Type) {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            Utility.scrollToBottomOfThePageDynamically(js);
            List<WebElement> List_of_posts = DriverAction.getElements(Locators_Homepage.TotalPosts);
            STATUS status;
            int No_of_posts = List_of_posts.size();
            Random rand = new Random();
            int Random_post_no = rand.nextInt(No_of_posts);
            WebElement Random_post = List_of_posts.get(Random_post_no);
            js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", Random_post);
            WebElement ProfileImageElement = Random_post.findElement(By.xpath("div[@class='postContainer_profilePicture__1zrAi']"));
            WebElement ProfileNameElement = Random_post.findElement(By.xpath("div[@class='postContainer_headerText__2DTR7']//div[@class='postContainer_profileName__3dIQ5']//button[@class='postContainer_click__2nvKy']"));
            String ProfileName = ProfileNameElement.getText();
            if (Type.equalsIgnoreCase("Name"))
                DriverAction.click(ProfileNameElement, "Profile Name");
            else if (Type.equalsIgnoreCase("Image"))
                DriverAction.click(ProfileImageElement, "Profile Image");
            Boolean bool = Utility.verifyUserProfile(ProfileName);
            if (bool)
                GemTestReporter.addTestStep("Profile Validation", "Expected :" + ProfileName + " Valid profile ", STATUS.PASS, DriverAction.takeSnapShot());
            else
                GemTestReporter.addTestStep("Profile Validation", "Expected :" + ProfileName + " Valid profile ", STATUS.FAIL, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on three dots in the post then copy of link option will be displayed")
    public void userClicksOnThreeDotsInThePostThenCopyOfLinkOptionWillBeDisplayed() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            Utility.scrollToBottomOfThePageDynamically(js);
            List<WebElement> List_of_posts = DriverAction.getElements(Locators_Homepage.TotalPosts);
            STATUS status;
            int No_of_posts = List_of_posts.size();
            Random rand = new Random();
            int Random_post_no = rand.nextInt(No_of_posts);
            WebElement Random_post = List_of_posts.get(Random_post_no);
            js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", Random_post);
            WebElement Three_dots = Random_post.findElement(By.xpath("div[@class='postContainer_headerText__2DTR7']//div[@class='postContainer_profileName__3dIQ5']//div[@class='postContainer_dropDown__3OFc6']"));
            DriverAction.click(Three_dots, "Three Dots");
            WebElement Copy_of_link_to_post = Three_dots.findElement(By.xpath("ul//li"));
            Boolean bool = Copy_of_link_to_post.isDisplayed();
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Copy link validation", "Exp : Link is displayed", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("User clicks on copy of link to post option")
    public void userClicksOnCopyOfLinkToPostOption() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.CopyLinkToPostLink, "Copy Link to Post");
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("A copied toast will be displayed")
    public void aCopiedToastWillBeDisplayed() {
        try {
            DriverAction.waitSec(3);
            STATUS status;
            Boolean bool = DriverAction.getElement(Locators_Homepage.CopiedToastMessage).isDisplayed();
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Link copied validation", "Exp : Link is copied", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User go to any random post and click on like button to like the post")
    public void userGoToAnyRandomPostAndClickOnLikeButtonToLikeThePost() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);           // calling function to make control reach to any particular posts out of many posts
            int No_of_likes_before = Utility.getLikesCommentCount(Random_post, "Likes");
            int No_of_likes_after;
//            WebElement Post_likes_before = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_likeCount__1y61T']"));        // web element for likes count in any post by using the particular post element returned by above step.
//            int No_of_likes_before = Integer.parseInt(Post_likes_before.getText());
            WebElement like_btn = Random_post.findElement(By.xpath("div[@class='postContainer_footerBtns__1xAPm']//button[contains(@class,'postContainer_postLikeBtn__ZMVtx')]"));   //genericlike button to like and unlike the posts
            if (like_btn.getAttribute("class").contains("postContainer_active__2rlz4")) {     //condition to check whether the post is already liked and take action accordingly.
                GemTestReporter.addTestStep("Post Like ", "Post already liked", STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }            //below code will be executed if post was not in liked state.
            DriverAction.click(like_btn, "Like button");
            DriverAction.waitSec(8);
//            WebElement Post_likes_After = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_likeCount__1y61T']"));         //again getting the likes count after like
//            int No_of_likes_after = Integer.parseInt(Post_likes_After.getText());
            No_of_likes_after = Utility.getLikesCommentCount(Random_post, "Likes");
            if (No_of_likes_before + 1 == No_of_likes_after)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Post Like ", "Successfully liked the Post", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User go to any random post and click on like button again to unlike the post after liking the post")
    public void userGoToAnyRandomPostAndClickOnLikeButtonAgainToUnlikeThePostAfterLikingThePost() {
        try {

            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            int No_of_likes_before = Utility.getLikesCommentCount(Random_post, "Likes");    // to get the like and comments count for a particular post everytime we have created a fuction in util class , so this time we are trying to fetch likes count.
            int No_of_likes_after;
            WebElement like_btn = Random_post.findElement(By.xpath("div[@class='postContainer_footerBtns__1xAPm']//button[contains(@class,'postContainer_postLikeBtn__ZMVtx')]"));     // like button symbol of the post.
            if (like_btn.getAttribute("class").contains("postContainer_active__2rlz4")) {    //condition to check whether the post is aready liked in that case we are only simply clicking the like button single time to unlike the post..
                int No_of_likes_if_unlike = No_of_likes_before - 1;     // count of like which should be , after we unlike the post.
                DriverAction.click(like_btn, "Unlike Post");
                DriverAction.waitSec(3);
                No_of_likes_after = Utility.getLikesCommentCount(Random_post, "Likes");
                if (No_of_likes_if_unlike == No_of_likes_after)                                            // verifing the count should be same
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("Post Unlike ", "Successfully Unliked the Post", status, DriverAction.takeSnapShot());
                return;
            }                                                                                                  // this block of code will be executed when the post is not liked by default and in this case we first like and then try to dislike the post
            DriverAction.click(like_btn, "Like button");
            DriverAction.waitSec(3);
            DriverAction.click(like_btn, "Unlike Post");
            DriverAction.waitSec(3);
            No_of_likes_after = Utility.getLikesCommentCount(Random_post, "Likes");
            if (No_of_likes_before == No_of_likes_after)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Post Unlike ", "Successfully Unliked the Post", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User hover over the like reaction to get the list of employee")
    public void userHoverOverTheLikeReactionToGetTheListOfEmployee() {
        try {
            Actions actions = new Actions(DriverManager.getWebDriver());
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            int No_of_likes = Utility.getLikesCommentCount(Random_post, "Likes");
            if (No_of_likes == 0) {                                             // checking if there is no likes in post , for that we simply say 0 likes in report and come out of function.
                GemTestReporter.addTestStep("PostsLikes", "Liked by : " + No_of_likes, STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }                                                               // below code will execute if there will be minimum of 1 like.
            WebElement Post_likes = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_likeCount__1y61T']"));   // getting the like count.
            actions.moveToElement(Post_likes).build().perform();                                     // to hover over like reaction symbol
            DriverAction.waitSec(3);                            //After hovering , fetching the list and names of people who liked the post is displayed or not.
            List<WebElement> List_who_liked = Post_likes.findElements(By.xpath("div[@class='postContainer_dropdownContent__29Xvt']//child::div[@class='postContainer_likeName__3OkfG']//div[@class='postContainer_likeBox__1LNyU']//div"));
            for (int i = 1; i < List_who_liked.size(); i++) {
                WebElement person_liked = List_who_liked.get(i);            // taking each single person who liked and printing the reports.
                if (person_liked.isDisplayed())
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("PostsLikes", "Liked by : " + person_liked.getText(), status, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("^User hover over the like reaction to get the list of employee and click on image/name (.+) of user$")
    public void userHoverOverTheLikeReactionToGetTheListOfEmployeeAndClickOnImageNameOfUser(String Type) {
        try {
            Actions actions = new Actions(DriverManager.getWebDriver());
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            int No_of_likes = Utility.getLikesCommentCount(Random_post, "Likes");
            if (No_of_likes == 0) {                                                   // checking if there is no likes in post , for that we simply say 0 likes in report and come out of function.
                GemTestReporter.addTestStep("PostsLikes", "Liked by : " + No_of_likes, STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }
            WebElement Post_likes = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_likeCount__1y61T']"));   // getting the like count.
            actions.moveToElement(Post_likes).build().perform();                    // to hover over like reaction symbol
            GemTestReporter.addTestStep("Posts Likes", "Like reactions", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.waitSec(3);                               //After hovering , fetching the list and names of people who liked the post
            List<WebElement> List_who_liked = Post_likes.findElements(By.xpath("div[@class='postContainer_dropdownContent__29Xvt']//child::div[@class='postContainer_likeName__3OkfG']//div[@class='postContainer_likeBox__1LNyU']//div"));
            Random rand = new Random();
            int low = 1;
            int high = List_who_liked.size();
            int RandomPerson = rand.nextInt(high - low) + low;
            WebElement Random_person = List_who_liked.get(RandomPerson);    //Taking the random person out of the fetched list who liked the post
            String Random_person_name = Random_person.getText();
            if (Type.equalsIgnoreCase("Image")) {
                WebElement Random_persons_image = Random_person.findElement(By.xpath("img"));     // clicking on to image of the random person
                DriverAction.click(Random_persons_image, "Image of person who liked the post");
            } else if (Type.equalsIgnoreCase("Name")) {                                      // clicking on to name of the random person
                WebElement Random_persons_name = Random_person.findElement(By.xpath("span"));
                DriverAction.click(Random_persons_name, "Name of person who liked the post");

            }
            Boolean bool = Utility.verifyUserProfile(Random_person_name);              // after clicking and navigating to the user profile, verifying the profile and generating the report accordingly.
            if (bool)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Profile Validation", "Expected :" + Random_person_name + " Valid profile ", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on comment button")
    public void userClicksOnCommentButton() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement comment_btn = Random_post.findElement(By.xpath("div[@class='postContainer_footerBtns__1xAPm']//button[contains(@class,'postContainer_postCommentBtn_')]"));           //Getting the comment button of the particular random post.
            DriverAction.click(comment_btn, "Comment Button");
            DriverAction.waitSec(3);
            WebElement Comment_box = Random_post.findElement(By.xpath("div[@class='postContainer_addComment__2Mmg1']"));             // comment box of the post appeared after clicking to "Comment".
            if (Comment_box.isDisplayed())                              // checking if comment box is displayed after clicking in to comment button.
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Comment box", "Expected :Comment box is displayed", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }

    }

    @When("^User enters the text or emoji (.+) into comment box (.+) and press enter button to comment$")
    public void userEntersTheTextEmojiIntoCommentBoxAndPressEnterButtonToComment(String Type, String Content) {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement comment_btn = Random_post.findElement(By.xpath("div[@class='postContainer_footerBtns__1xAPm']//button[contains(@class,'postContainer_postCommentBtn_')]"));
            DriverAction.click(comment_btn, "Comment Button");
            DriverAction.waitSec(3);
            WebElement Comment_box = Random_post.findElement(By.xpath("div[@class='postContainer_addComment__2Mmg1']"));    // comment box , after comment button is clicked.
            WebElement Comment_field = Comment_box.findElement(By.name("commentContent"));                 // comment Field text box , inside comment box.
            WebElement Emoji_box = Comment_box.findElement(By.xpath("div[@class='postContainer_inputGroup__u9FRq input-group']//div[@class='input-group-append']//div[@id='button-addon1'and @class='p-1 btn btn-link text-primary']"));
            DriverAction.waitSec(3);
            int Count_comment_before = Utility.getLikesCommentCount(Random_post, "comment");         // declaring variables to verify the comment is added after adding comment.
            int Count_comment_after;
            DriverAction.click(Comment_field, "Comment field");
            if (Type.equalsIgnoreCase("Text")) {                                 // if choice of only text will be sent from feature file then this will run.
                DriverAction.typeText(Comment_field, Content, "Comment field");
                Comment_field.sendKeys(Keys.ENTER);
            } else if (Type.equalsIgnoreCase("Emoji")) {                        // if choice of only emoji will be sent from feature file then this will run.
                for (int i = 1; i < 6; i++) {
                    DriverAction.click(Emoji_box, "Emoji's");
                    Emoji_box.findElement(By.xpath("(//section[@class='emoji-scroll-wrapper']//child::ul//li)[" + i + "]")).click();

                }
                Comment_field.sendKeys(Keys.ENTER);
            } else if (Type.equalsIgnoreCase("Both")) {                         // if choice of both will be sent from feature file then this will run.
                DriverAction.typeText(Comment_field, Content, "Comment field");
                for (int i = 1; i < 6; i++) {
                    DriverAction.click(Emoji_box, "Emoji's");
                    Emoji_box.findElement(By.xpath("(//section[@class='emoji-scroll-wrapper']//child::ul//li)[" + i + "]")).click();

                }
                Comment_field.sendKeys(Keys.ENTER);
            }
            DriverAction.waitSec(3);
            Count_comment_after = Utility.getLikesCommentCount(Random_post, "Comment");
            if (Count_comment_before + 1 == Count_comment_after)                                  // verifying that comment count should be increased.
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Comment Validation", "Expected :Comment is added", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks on Green symbol of count of comment")
    public void userClicksOnGreenSymbolOfCountOfComment() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement View_more_comment;
            WebElement View_less_comment;
            List<WebElement> List_of_comments = null;
            int No_of_comments = Utility.getLikesCommentCount(Random_post, "Comments");     // getting the total count of the comments
            if (No_of_comments == 0) {                                                             // if the no of counts will be 0 then we simply go exit of the function.
                GemTestReporter.addTestStep("Posts Comments", "Commented by : " + No_of_comments, STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }                                                                            // getting the green coloured total comments count icon.
            WebElement Post_comments = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_commentCount__1F4g6']"));
            DriverAction.click(Post_comments, "Comments Green Icon");
            if (No_of_comments == 1) {                            // if the post contains single comment then this block will run.
                List_of_comments = Random_post.findElements(By.xpath("div[@class='px-3 w-100  postContainer_commentContainer__FbWj1']//child::div[@class='d-flex py-2']"));    // it will fetch the all comments into the list .
                Iterator<WebElement> Comments_list = List_of_comments.iterator();
                while (Comments_list.hasNext()) {
                    WebElement Comment = Comments_list.next();                                      // out of the all the comments , getting the single comment every time.
                    String Commenter_name = Comment.findElement(By.xpath("//child::div[@class='postContainer_commentBox__33iPA']//child::button[@class='postContainer_click__2nvKy']")).getAttribute("innerHTML");     // fetching the commentator name for reports.
                    js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", Comment);           // setting the screen focus to the particular comment of the post.
                    if (Comment.isDisplayed())                            // verifying whether the comment is displayed or not.
                        status = STATUS.PASS;
                    else
                        status = STATUS.FAIL;
                    GemTestReporter.addTestStep("Posts Comments", "Commented by :" + Commenter_name, status, DriverAction.takeSnapShot());
                }
            } else if (No_of_comments > 1) {                                   // same as above if block but this block will run if there will be more then 1 comments in the post.
                View_more_comment = Random_post.findElement(By.xpath("//b[contains(text(),'View More Comments')]"));     //This time we need to click on the view more comments option to complete full list of comment.
                DriverAction.click(View_more_comment, "View More Comments");
                List_of_comments = Random_post.findElements(By.xpath("div[@class='px-3 w-100  postContainer_commentContainer__FbWj1']//child::div[@class='d-flex py-2']"));
                Iterator<WebElement> Comments_list = List_of_comments.iterator();
                while (Comments_list.hasNext()) {
                    WebElement Comment = Comments_list.next();
                    String Commenter_name = Comment.findElement(By.xpath("//child::div[@class='postContainer_commentBox__33iPA']//child::button[@class='postContainer_click__2nvKy']")).getAttribute("innerHTML");
                    js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", Comment);
                    if (Comment.isDisplayed())
                        status = STATUS.PASS;
                    else
                        status = STATUS.FAIL;
                    GemTestReporter.addTestStep("Posts Comments", "Commented by :" + Commenter_name, status, DriverAction.takeSnapShot());

                }
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User checks the post having more then one comment")
    public void userChecksThePostHavingMoreThenOneComment() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement View_more_comment;
            WebElement Post_comments;
            int No_of_comments = Utility.getLikesCommentCount(Random_post, "Comments");
            if (No_of_comments == 0) {                                    // if post has no comment then simply exit from function.
                GemTestReporter.addTestStep("View More Comments link Validation ", "No Comment is present", STATUS.PASS, DriverAction.takeSnapShot());

            } else if (No_of_comments == 1) {                    // if post has only single comment then weare showing only report with single comment and link not present.
                Post_comments = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_commentCount__1F4g6']"));
                DriverAction.click(Post_comments, "Comments Green Icon");
                GemTestReporter.addTestStep("View More Comments link", "Exp : Link not present , Total Comments : " + No_of_comments, STATUS.PASS, DriverAction.takeSnapShot());
            } else {                                             //if the post has more then one comment then we are validatin that view more comment link would be visible
                Post_comments = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_commentCount__1F4g6']"));
                DriverAction.click(Post_comments, "Comments Green Icon");
                View_more_comment = Random_post.findElement(By.xpath("//b[contains(text(),'View More Comments')]"));
                if (View_more_comment.isDisplayed())
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                GemTestReporter.addTestStep("View More Comments link", "Exp : Link is present , Total Comments : " + No_of_comments, status, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @And("Click on View More comment link")
    public void clickOnViewMoreCommentLink() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement View_more_comment;
            WebElement View_less_comment;
            WebElement Post_comments;
            List<WebElement> List_of_comments;
            int No_of_comments = Utility.getLikesCommentCount(Random_post, "Comments");
            if (No_of_comments <= 1) {                         // if the post has 0 or 1 comment then there will be no view more comment option so , simply exiting from function.
                GemTestReporter.addTestStep("View More Comments link", "Exp : Link not present , Total Comments : " + No_of_comments, STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }                   // if the post has more then one comment then below statements will be executed.
            Post_comments = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_commentCount__1F4g6']"));
            DriverAction.click(Post_comments, "Comments Green Icon");
            View_more_comment = Random_post.findElement(By.xpath("//b[contains(text(),'View More Comments')]"));
            DriverAction.click(View_more_comment, "View More Comment");                            // clicking on view more comment link to fetch the complete list of comment and also to get count of comment and view less comment list.
            List_of_comments = Random_post.findElements(By.xpath("div[@class='px-3 w-100  postContainer_commentContainer__FbWj1']//child::div[@class='d-flex py-2']"));        // fetching complete list of comments after clicking on view more commet option.
            View_less_comment = Random_post.findElement(By.xpath("//b[contains(text(),'View Less Comments')]"));        // getting the view less comment element.
            if (No_of_comments == List_of_comments.size() && View_less_comment.isDisplayed())                  // validating the comment count should be same after clicking on view more comment and also view less link should present.
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Working of View More comment", "Exp : All comments are displayed with view less option", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("Click on View Less comment link")
    public void clickOnViewLessCommentLink() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement View_more_comment;
            WebElement View_less_comment;
            WebElement Post_comments;
            List<WebElement> List_of_comments;
            int No_of_comments = Utility.getLikesCommentCount(Random_post, "Comments");
            if (No_of_comments <= 1) {                                     // if there will be 0 or 1 comment in post then there will be no more or less ink will be there .
                GemTestReporter.addTestStep("View More & less Comments link ", "Exp : Link not present , Total Comments : " + No_of_comments, STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }                   // for the post having more then one comment below code will be executed.
            Post_comments = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_commentCount__1F4g6']"));
            DriverAction.click(Post_comments, "Comments Green Icon");             // clicking on to green icon to get the comments of the post.
            View_more_comment = Random_post.findElement(By.xpath("//b[contains(text(),'View More Comments')]"));
            DriverAction.click(View_more_comment, "View More Comment");           // clicking to view more comment link to get all the comments of the post.
            View_less_comment = Random_post.findElement(By.xpath("//b[contains(text(),'View Less Comments')]"));
            DriverAction.click(View_less_comment, "View Less Comment");  // clicking to view lss comment link after clicking to view more comment.
            List_of_comments = Random_post.findElements(By.xpath("div[@class='px-3 w-100  postContainer_commentContainer__FbWj1']//child::div[@class='d-flex py-2']"));             // fetching again the list of comments after clicking to view less comment.
            View_more_comment = Random_post.findElement(By.xpath("//b[contains(text(),'View More Comments')]"));          // getting the view more comment link in this state.
            if (List_of_comments.size() == 1 && View_more_comment.isDisplayed())        // validating that after clicking to view less comment there should be only one comment displayed with view more comment list.
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Working of View Less comment", "Exp : Showing only one comment with view more comment option", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks the like button of the comment then on right hand side like symbol should be displayed")
    public void userClicksTheLikeButtonOfTheCommentThenOnRightHandSideLikeSymbolShouldBeDisplayed() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement Post_comments;
            WebElement Comment;
            WebElement Comment_like_btn;
            WebElement Comment_likes;
            int No_of_comments = Utility.getLikesCommentCount(Random_post, "Comments");
            if (No_of_comments == 0) {
                GemTestReporter.addTestStep("Comment like ", "No comments present", STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }
            Post_comments = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_commentCount__1F4g6']"));
            DriverAction.click(Post_comments, "Comments Green Icon");                       // clicking to get the comments of the post and then performing our test case with single comment.
            Comment = Random_post.findElement(By.xpath("div[@class='px-3 w-100  postContainer_commentContainer__FbWj1']//child::div[@class='d-flex py-2']"));                // getting the single comment which will be displayed .
            List<WebElement> list_of_elem_in_comment = Comment.findElements(By.xpath("//child::div[@class='postContainer_commentBox__33iPA']//child::div[contains(@class,'postContainer')]"));
            Iterator<WebElement> list_in_comment = list_of_elem_in_comment.iterator();                      // line 1929 - 1937 to check that particular comment is already liked by logged in user it will set already liked as true if it was liked by logged in user
            Boolean already_liked = false;                                                                     // otherwise by default false will be assigned.
            while (list_in_comment.hasNext()) {
                if (list_in_comment.next().getAttribute("class").contains("postContainer_likeCounter")) {
                    System.out.println("Already liked");
                    already_liked = true;
                }
            }
            if (already_liked) {
                Comment_likes = Comment.findElement(By.xpath("//child::div[@class='postContainer_commentBox__33iPA']//child::div[@class='postContainer_likeCounter__2s8Oo']"));    // comment like is a symbol that will be displayed if the comment is liked.
                if (Comment_likes.isDisplayed())                                                                            // if the comment is already liked y the user then directly validting that symbol should be displayed.
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                DriverAction.waitSec(4);
                GemTestReporter.addTestStep("Comment like ", "comment already liked", status, DriverAction.takeSnapShot());
            } else {                                                                                                 // if the post is not already liked by the user then firstly like the comment and validate the like symbol.
                Comment_like_btn = Comment.findElement(By.xpath("//child::div[@class='postContainer_commentAction__J3ata']//span[contains(@class,'commentLikeBtn')]"));
                DriverAction.click(Comment_like_btn, "Comment like");
                Comment_likes = Comment.findElement(By.xpath("//child::div[@class='postContainer_commentBox__33iPA']//child::div[@class='postContainer_likeCounter__2s8Oo']"));
                if (Comment_likes.isDisplayed())
                    status = STATUS.PASS;
                else
                    status = STATUS.FAIL;
                DriverAction.waitSec(4);
                GemTestReporter.addTestStep("Comment like ", "comment liked", status, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User clicks the like button of the comment and hovers over the like reaction symbol")
    public void userClicksTheLikeButtonOfTheCommentAndHoversOverTheLikeReactionSymbol() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            Actions act = new Actions(DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement Post_comments;
            WebElement Comment;
            WebElement Comment_like_btn;
            WebElement Comment_likes;
            List<WebElement> Persons_who_liked_comment;
            Iterator<WebElement> Persons_liked_comment;
            int No_of_comments = Utility.getLikesCommentCount(Random_post, "Comments");
            if (No_of_comments == 0) {
                GemTestReporter.addTestStep("Comment like ", "No comments present", STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }
            Post_comments = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_commentCount__1F4g6']"));
            DriverAction.click(Post_comments, "Comments Green Icon");                          // clicking to get the comments of the post and then performing our test case with single comment.
            Comment = Random_post.findElement(By.xpath("div[@class='px-3 w-100  postContainer_commentContainer__FbWj1']//child::div[@class='d-flex py-2']"));
            List<WebElement> list_of_elem_in_comment = Comment.findElements(By.xpath("//child::div[@class='postContainer_commentBox__33iPA']//child::div[contains(@class,'postContainer')]"));
            Iterator<WebElement> list_in_comment = list_of_elem_in_comment.iterator();                       // line 1929 - 1937 to check that particular comment is already liked by logged in user it will set already liked as true if it was liked by logged in user
            Boolean already_liked = false;                                                                       // otherwise by default false will be assigned.
            while (list_in_comment.hasNext()) {
                if (list_in_comment.next().getAttribute("class").contains("postContainer_likeCounter")) {
                    System.out.println("Already liked");
                    already_liked = true;
                }
            }
            if (already_liked) {                                                                        // if the post is already liked by the logged in user then hovering directly to like symbol
                Comment_likes = Comment.findElement(By.xpath("//child::div[@class='postContainer_commentBox__33iPA']//child::div[@class='postContainer_likeCounter__2s8Oo']"));
                act.moveToElement(Comment_likes).build().perform();
                Persons_who_liked_comment = Comment_likes.findElements(By.xpath("div[@class='postContainer_dropdownContent__29Xvt']//child::div[@class='postContainer_likeName__3OkfG']//child::div[@class='postContainer_likeBox__1LNyU']//child::div[@class='postContainer_content__3Htqs']"));
                Persons_liked_comment = Persons_who_liked_comment.iterator();                         // getting the list of persons who liked the particular comment  after hovering over the likes symbol in comment
                while (Persons_liked_comment.hasNext()) {
                    WebElement Person_name_liked_comment = Persons_liked_comment.next();                    // after getting the list , fetching the particular person from the liked list.
                    if (Person_name_liked_comment.isDisplayed())                                           // verifying that particular person who liked the comment is displayed or not.
                        status = STATUS.PASS;
                    else
                        status = STATUS.FAIL;
                    GemTestReporter.addTestStep("Comment Likes List", "Liked by : " + Person_name_liked_comment.getText(), status, DriverAction.takeSnapShot());
                }
            } else {                                     // if the post is not already liked by the logged in user then firstly click on like button of the comment and then hovering over like symbol
                Comment_like_btn = Comment.findElement(By.xpath("//child::div[@class='postContainer_commentAction__J3ata']//span[contains(@class,'commentLikeBtn')]"));
                DriverAction.click(Comment_like_btn, "Comment like");
                Comment_likes = Comment.findElement(By.xpath("//child::div[@class='postContainer_commentBox__33iPA']//child::div[@class='postContainer_likeCounter__2s8Oo']"));
                act.moveToElement(Comment_likes).build().perform();
                Persons_who_liked_comment = Comment_likes.findElements(By.xpath("div[@class='postContainer_dropdownContent__29Xvt']//child::div[@class='postContainer_likeName__3OkfG']//child::div[@class='postContainer_likeBox__1LNyU']//child::div[@class='postContainer_content__3Htqs']"));
                Persons_liked_comment = Persons_who_liked_comment.iterator();                                     // getting the list of persons who liked the particular comment  after hovering over the likes symbol in comment
                while (Persons_liked_comment.hasNext()) {
                    WebElement Person_name_liked_comment = Persons_liked_comment.next();                                // after getting the list , fetching the particular person from the liked list.
                    if (Person_name_liked_comment.isDisplayed())                                                          // verifying that particular person who liked the comment is displayed or not.
                        status = STATUS.PASS;
                    else
                        status = STATUS.FAIL;
                    GemTestReporter.addTestStep("Comment Likes List", "Liked by : " + Person_name_liked_comment.getText(), status, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User click on delete comment button the comment should be deleted")
    public void userClickOnDeleteCommentButtonTheCommentShouldBeDeleted() {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            STATUS status;
            WebElement Random_post = Utility.goToParticularPostFooter(js);
            WebElement Post_comments;
            WebElement Comment_delete_btn;
            WebElement View_more_comment;
            List<WebElement> List_of_comments;
            Iterator<WebElement> Comments_list;
            Random rand = new Random();
            int No_random_comment;
            int No_of_comments = Utility.getLikesCommentCount(Random_post, "Comments");
            int No_of_comments_before = No_of_comments;
            int No_of_comments_after;
            if (No_of_comments == 0) {
                GemTestReporter.addTestStep("Comment like ", "No comments present", STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }
            Post_comments = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//div[@class='postContainer_commentCount__1F4g6']"));
            DriverAction.click(Post_comments, "Comments Green Icon");
            if (No_of_comments == 1) {                            // if the post contains single comment then this block will run.
                List_of_comments = Random_post.findElements(By.xpath("div[@class='px-3 w-100  postContainer_commentContainer__FbWj1']//child::div[@class='d-flex py-2']"));    // it will fetch the  comment into the list .
                Comment_delete_btn = List_of_comments.get(0).findElement(By.xpath("//child::div[@class='postContainer_commentAction__J3ata']//span[contains(@class,'commentDeleteBtn')]"));  // as there will be single comment then getting the delete button of the particular comment.
                DriverAction.click(Comment_delete_btn, "Comment delete");
                DriverAction.waitSec(3);

            } else if (No_of_comments > 1) {                                   // same as above if block but this block will run if there will be more then 1 comments in the post.
                View_more_comment = Random_post.findElement(By.xpath("//b[contains(text(),'View More Comments')]"));     //This time we need to click on the view more comments option to complete full list of comment.
                DriverAction.click(View_more_comment, "View More Comments");
                List_of_comments = Random_post.findElements(By.xpath("div[@class='px-3 w-100  postContainer_commentContainer__FbWj1']//child::div[@class='d-flex py-2']"));   // fetching all the comments present and storing in the list.
                No_random_comment = rand.nextInt(List_of_comments.size());             // taking the random copmment
                Comment_delete_btn = List_of_comments.get(No_random_comment).findElement(By.xpath("//child::div[@class='postContainer_commentAction__J3ata']//span[contains(@class,'commentDeleteBtn')]"));  // then getting the delete button of the particular random comment.
                DriverAction.click(Comment_delete_btn, "Comment delete");
                DriverAction.waitSec(3);
            }
            No_of_comments_after = Utility.getLikesCommentCount(Random_post, "Comments");            // now fetching the total comment count after deleting the comment.
            if (No_of_comments_before - 1 == No_of_comments_after)              //validating the comment should be deleted.
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Comment delete", "Comment deleted successfully", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @When("User click on posts image")
    public void userClickOnPostsImage() {
        DriverAction.waitSec(3);
        JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
        Utility.scrollToBottomOfThePageDynamically(js);                         //page is dynamic so to get all records first we are scrolling to the end of the page.
        js.executeScript("window.scrollTo(0,0)");                 //Scrolling again to the top of the page .
        List<WebElement> All_posts_image_container = DriverAction.getElements(Locators_Homepage.AllPostsImageContainerBody);
        Iterator<WebElement> All_posts_image_container_list = All_posts_image_container.iterator();
        List<WebElement> All_posts_list_having_image = new ArrayList<WebElement>();
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
        Random rand = new Random();
        STATUS status;
        int No_random_post_having_image;
        while (All_posts_image_container_list.hasNext()) {
            WebElement single_post = All_posts_image_container_list.next();
            int child = Integer.parseInt(single_post.getAttribute("childElementCount"));
            if (child > 0) {
                All_posts_list_having_image.add(single_post);
            }
        }
        if (All_posts_list_having_image.size() == 0) {
            GemTestReporter.addTestStep("Image pop up test", "No posts have image as attachment", STATUS.PASS, DriverAction.takeSnapShot());
            return;
        }
        No_random_post_having_image = rand.nextInt(All_posts_list_having_image.size());
        WebElement random_post_having_image = All_posts_list_having_image.get(No_random_post_having_image);
        js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", random_post_having_image);
        WebElement image = random_post_having_image.findElement(By.xpath("div[@class='active carousel-item']//child::img"));
        String SrcImageClicked = image.getAttribute("src");
        DriverAction.click(image, "image of the post");
        WebElement image_pop_up = DriverAction.getElement(Locators_Homepage.ImagePopUp);
        wait.until(ExpectedConditions.visibilityOf(image_pop_up));
        String SrcImageInPopup = image_pop_up.getAttribute("src");
        if (SrcImageClicked.equals(SrcImageInPopup) && image_pop_up.isDisplayed())
            status = STATUS.PASS;
        else
            status = STATUS.FAIL;
        DriverAction.waitSec(5);
        GemTestReporter.addTestStep("Image Pop up Test", "Image pop up should open", status, DriverAction.takeSnapShot());
    }

    @And("User clicks on image pop up close button")
    public void userClicksOnImagePopUpCloseButton() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators_Homepage.ImagePopUpClose, "Image pop up close");
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }

    @Then("Image pop up which was opened on clicking should be closed")
    public void imagePopUpWhichWasOpenedOnClickingShouldBeClosed() {
        try {
            DriverAction.waitSec(3);
            STATUS status;
            if (DriverAction.getElements(Locators_Homepage.ImagePopUp).size() == 0)
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Image Pop up Test", "Image pop up should be closed", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }

    }

    @When("User checks the post having multiple photos")
    public void userChecksThePostHavingMultiplePhotos() {
        DriverAction.waitSec(3);
        JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
        Utility.scrollToBottomOfThePageDynamically(js);                         //page is dynamic so to get all records first we are scrolling to the end of the page.
        js.executeScript("window.scrollTo(0,0)");                 //Scrolling again to the top of the page .
        List<WebElement> All_posts_image_container = DriverAction.getElements(Locators_Homepage.AllPostsImageContainerBody);
        Iterator<WebElement> All_posts_image_container_list = All_posts_image_container.iterator();
        List<WebElement> All_posts_list_having_multiple_image = new ArrayList<WebElement>();
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
        Random rand = new Random();
        STATUS status;
        int No_random_post_having_multiple_image;
        while (All_posts_image_container_list.hasNext()) {
            WebElement single_post = All_posts_image_container_list.next();
            int child = Integer.parseInt(single_post.getAttribute("childElementCount"));
            if (child > 1) {
                All_posts_list_having_multiple_image.add(single_post);
            }
        }
        if (All_posts_list_having_multiple_image.size() == 0) {
            GemTestReporter.addTestStep("Image pop up test", "No posts have image as attachment", STATUS.PASS, DriverAction.takeSnapShot());
            return;
        }
        No_random_post_having_multiple_image = rand.nextInt(All_posts_list_having_multiple_image.size());
        WebElement random_post_having_multiple_image = All_posts_list_having_multiple_image.get(No_random_post_having_multiple_image);
        js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", random_post_having_multiple_image);
        DriverAction.waitSec(3);
        WebElement Next_button = random_post_having_multiple_image.findElement(By.xpath("following-sibling::a[@class='carousel-control-next']"));
        WebElement Prev_button = random_post_having_multiple_image.findElement(By.xpath("following-sibling::a[@class='carousel-control-prev']"));
        if (Next_button.isEnabled() && Prev_button.isEnabled())
            status = STATUS.PASS;
        else
            status = STATUS.FAIL;
        GemTestReporter.addTestStep("SS", "SS", status, DriverAction.takeSnapShot());
    }

    @When("^User clicks on navigation key (.+) of the post having multiple photos$")
    public void userClicksOnNavigationKeyDirectionOfThePostHavingMultiplePhotos(String Directions) {
        DriverAction.waitSec(3);
        JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
        Utility.scrollToBottomOfThePageDynamically(js);                         //page is dynamic so to get all records first we are scrolling to the end of the page.
        js.executeScript("window.scrollTo(0,0)");                 //Scrolling again to the top of the page .
        List<WebElement> All_posts_image_container = DriverAction.getElements(Locators_Homepage.AllPostsImageContainerBody);
        Iterator<WebElement> All_posts_image_container_list = All_posts_image_container.iterator();
        List<WebElement> All_posts_list_having_multiple_image = new ArrayList<WebElement>();
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
        WebElement image;
        Random rand = new Random();
        STATUS status;
        String SrcOpenedImage;
        String SrcNextImage;
        WebElement image_pop_up;
        String SrcImageInPopup;
        WebElement Prev_button;
        WebElement Next_button;
        int No_random_post_having_multiple_image;
        while (All_posts_image_container_list.hasNext()) {
            WebElement single_post = All_posts_image_container_list.next();
            int child = Integer.parseInt(single_post.getAttribute("childElementCount"));
            if (child > 1) {
                All_posts_list_having_multiple_image.add(single_post);
            }
        }
        if (All_posts_list_having_multiple_image.size() == 0) {
            GemTestReporter.addTestStep("Image pop up test", "No posts have multiple image as attachment", STATUS.PASS, DriverAction.takeSnapShot());
            return;
        }
        No_random_post_having_multiple_image = rand.nextInt(All_posts_list_having_multiple_image.size());
        WebElement random_post_having_multiple_image = All_posts_list_having_multiple_image.get(No_random_post_having_multiple_image);
        js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", random_post_having_multiple_image);
        DriverAction.waitSec(3);
        image = random_post_having_multiple_image.findElement(By.xpath("div[@class='active carousel-item']//child::img"));
        wait.until(ExpectedConditions.visibilityOf(image));
        DriverAction.waitSec(3);
        SrcOpenedImage = image.getAttribute("src");
        if (Directions.equalsIgnoreCase("Previous")) {
            Prev_button = random_post_having_multiple_image.findElement(By.xpath("following-sibling::a[@class='carousel-control-prev']"));
            DriverAction.click(Prev_button, "Previous button");
        } else if (Directions.equalsIgnoreCase("Next")) {
            Next_button = random_post_having_multiple_image.findElement(By.xpath("following-sibling::a[@class='carousel-control-next']"));
            DriverAction.click(Next_button, "Next button");
        }

        image = random_post_having_multiple_image.findElement(By.xpath("div[@class='active carousel-item']//child::img"));
        wait.until(ExpectedConditions.visibilityOf(image));
        SrcNextImage = image.getAttribute("src");
        if (!SrcOpenedImage.equals(SrcNextImage))
            status = STATUS.PASS;
        else
            status = STATUS.FAIL;
        GemTestReporter.addTestStep("Working of Navigation button", "Exp : Click on " + Directions + "", status, DriverAction.takeSnapShot());
    }

    @When("^User clicks on navigation keys to parse the complete list of photo in direction (.+)$")
    public void userClicksOnNavigationKeysToParseTheCompleteListOfPhotoInDirection(String Direction) {
        try {
            DriverAction.waitSec(3);
            JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getWebDriver());
            Utility.scrollToBottomOfThePageDynamically(js);                         //page is dynamic so to get all records first we are scrolling to the end of the page.
            js.executeScript("window.scrollTo(0,0)");                 //Scrolling again to the top of the page .
            List<WebElement> All_posts_image_container = DriverAction.getElements(Locators_Homepage.AllPostsImageContainerBody);
            Iterator<WebElement> All_posts_image_container_list = All_posts_image_container.iterator();
            List<WebElement> All_posts_list_having_multiple_image = new ArrayList<WebElement>();
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
            Random rand = new Random();
            STATUS status;
            WebElement MainImage;
            WebElement NextImage;
            String MainImageSrc;
            String NextImageSrc;
            int No_random_post_having_multiple_image;
            WebElement Next_button;
            WebElement Prev_button;
            List<String> Maintained_list = new ArrayList<>();
            while (All_posts_image_container_list.hasNext()) {
                WebElement single_post = All_posts_image_container_list.next();
                int child = Integer.parseInt(single_post.getAttribute("childElementCount"));
                if (child > 1) {
                    All_posts_list_having_multiple_image.add(single_post);
                }
            }
            if (All_posts_list_having_multiple_image.size() == 0) {
                GemTestReporter.addTestStep("Image pop up test", "No posts have multiple image as attachment", STATUS.PASS, DriverAction.takeSnapShot());
                return;
            }
            No_random_post_having_multiple_image = rand.nextInt(All_posts_list_having_multiple_image.size());
            WebElement random_post_having_multiple_image = All_posts_list_having_multiple_image.get(No_random_post_having_multiple_image);
            js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", random_post_having_multiple_image);
            DriverAction.waitSec(3);
            MainImage = random_post_having_multiple_image.findElement(By.xpath("div[@class='active carousel-item']//child::img"));
            wait.until(ExpectedConditions.visibilityOf(MainImage));
            MainImageSrc = MainImage.getAttribute("src");
            Maintained_list.add(MainImageSrc);
            String countofImages = random_post_having_multiple_image.getAttribute("childElementCount");
            int TotalcountOfImages = Integer.parseInt(countofImages);
            for (int i = 0; i < TotalcountOfImages; i++) {
                if (Direction.equalsIgnoreCase("Next")) {
                    Next_button = random_post_having_multiple_image.findElement(By.xpath("following-sibling::a[@class='carousel-control-next']"));
                    DriverAction.click(Next_button, "Next button");
                } else if (Direction.equalsIgnoreCase("Previous")) {
                    Prev_button = random_post_having_multiple_image.findElement(By.xpath("following-sibling::a[@class='carousel-control-prev']"));
                    DriverAction.click(Prev_button, "Previous button");
                }
                NextImage = random_post_having_multiple_image.findElement(By.xpath("div[@class='active carousel-item']//child::img"));
                wait.until(ExpectedConditions.visibilityOf(NextImage));
                NextImageSrc = NextImage.getAttribute("src");
                if (!Maintained_list.contains(NextImageSrc))
                    Maintained_list.add(NextImageSrc);
            }
            if (TotalcountOfImages == Maintained_list.size())
                status = STATUS.PASS;
            else
                status = STATUS.FAIL;
            GemTestReporter.addTestStep("Check navigation to all images", "Exp : Able to navigate all images <br> Total images : " + TotalcountOfImages + "<br> Navigated images : " + Maintained_list.size() + "", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occured !", e);
            GemTestReporter.addTestStep("Execution Failed", "Some Error Occurred", STATUS.FAIL);
        }
    }
}