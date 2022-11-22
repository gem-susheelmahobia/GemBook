package Utilities;

import Objects.Locators_Homepage;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utility {
    public static Boolean validateAllDatesPresentIsInRangeOf7daysFromNow(ArrayList<String> dates) throws ParseException { // function to validate that all datespresent in array
        int flag = 1;                                                                                                        // are in between the required range or not and return the boolean value after checking all the elements of the array.
        for (int i = 0; i < dates.size(); i++) {
            Boolean bool = dateIsInCertainRange(dates.get(i));
            if (bool)
                continue;
            flag = 0;
        }
        if (flag == 1)
            return true;
        else
            return false;
    }

    public static Boolean dateIsInCertainRange(String dateToValidate) throws ParseException {   // function to check the certain date is in range of 7 days from now or not.
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        Date date = sdf.parse(dateToValidate);
        Calendar currentDateAfter7Days = Calendar.getInstance();
        currentDateAfter7Days.add(Calendar.DAY_OF_WEEK, 7);
        if (date.before(currentDateAfter7Days.getTime()))
            return true;
        else
            return false;
    }

    public static String changeDateFormatting(String Date)       // function to change the formatting from "dd-month" to "dd-MM-yyyy"
    {
        String str[] = Date.split(" ");

        String day = str[0];
        String month = str[1];
        LocalDate localDate = LocalDate.now();
        int curr_year = localDate.getYear();
        HashMap<String, String> obj = new HashMap<String, String>();
        obj.put("January", "01");
        obj.put("February", "02");
        obj.put("March", "03");
        obj.put("April", "04");
        obj.put("May", "05");
        obj.put("June", "06");
        obj.put("July", "07");
        obj.put("August", "08");
        obj.put("September", "09");
        obj.put("October", "10");
        obj.put("November", "11");
        obj.put("December", "12");
        String monthnum = obj.get(month);
        String date = day + "-" + monthnum + "-" + curr_year;
        return date;

    }

    public static LocalDate Dateformatting(String Date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localdate;
        String str[] = Date.split(" ");
        String day = str[0];
        String month = str[1].replace(",", "");
        String year = str[2].trim();
        HashMap<String, String> obj = new HashMap<String, String>();
        obj.put("January", "01");
        obj.put("February", "02");
        obj.put("March", "03");
        obj.put("April", "04");
        obj.put("May", "05");
        obj.put("June", "06");
        obj.put("July", "07");
        obj.put("August", "08");
        obj.put("September", "09");
        obj.put("October", "10");
        obj.put("November", "11");
        obj.put("December", "12");
        String monthnum = obj.get(month);
        String FinalDate = day + "-" + monthnum + "-" + year;
        localdate = LocalDate.parse(FinalDate, formatter);
        return localdate;
    }

    public static ArrayList<LocalDate> sortDateList(ArrayList<LocalDate> obtainedarray) {
        Collections.sort(obtainedarray, Collections.reverseOrder());
        return obtainedarray;
    }

    public static Boolean verifyUserProfile(String ExpectedName) {
        DriverAction.waitSec(7);
        String ActualName = DriverAction.getElement(Locators_Homepage.PersonsProfileName).getText();
        Boolean Designation = DriverAction.getElement(Locators_Homepage.DesignationUnderProfile).isDisplayed();
        Boolean Gmail = DriverAction.getElement(Locators_Homepage.GmailIdUnderProfiles).isDisplayed();
        Boolean Contact = DriverAction.getElement(Locators_Homepage.ContactUnderProfile).isDisplayed();
        List<WebElement> BirthdayAndJoiningDate = DriverAction.getElements(Locators_Homepage.BirthdayAndJoiningDateUnderProfile);
        Boolean ReportingManager = DriverAction.getElement(Locators_Homepage.ReportingManagerNameUnderProfiles).isDisplayed();
        Iterator<WebElement> birthandjoiningdates = BirthdayAndJoiningDate.iterator();
        Boolean flag = true;
        while (birthandjoiningdates.hasNext()) {
            if (birthandjoiningdates.next().isDisplayed())
                continue;
            flag = false;
        }
        if (ActualName.equals(ExpectedName) && Designation && Gmail && Contact && ReportingManager && flag)
            return true;
        else
            return false;
    }

    public static void scrollToBottomOfThePageDynamically(JavascriptExecutor js) {
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

        while (true) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            DriverAction.waitSec(3);
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
        }

    }

    public static WebElement goToParticularPostFooter(JavascriptExecutor js) {
        List<WebElement> List_of_posts = DriverAction.getElements(Locators_Homepage.AllPostsLikeComment);
        int No_of_posts = List_of_posts.size();
        Random rand = new Random();
        int Random_post_no = rand.nextInt(No_of_posts);
        WebElement Random_post = List_of_posts.get(0);
        js.executeScript("arguments[0].scrollIntoView({block: \"center\", inline: \"nearest\"});", Random_post);
        DriverAction.waitSec(3);
        return Random_post;
    }

    public static int getLikesCommentCount(WebElement Random_post, String Type) {
        List<WebElement> list_like_comm = null;
        WebElement elem_1;
        String likes_count = "0";
        String comment_count = "0";
        int No_of_likes;
        int No_of_comment;
        String Count = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']")).getAttribute("childElementCount");
        int Count_likes_comm = Integer.parseInt(Count);
        if (Count_likes_comm == 0)
        {
            System.out.println("No likes and comment");

        }
        else if (Count_likes_comm == 1)
        {
            elem_1 = Random_post.findElement(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//child::div[contains(@class,'Count')]"));
            if (elem_1.getAttribute("class").contains("likeCount"))
                likes_count = elem_1.getText();
            else if (elem_1.getAttribute("class").contains("commentCount"))
                comment_count = elem_1.getText();
        }
        else if (Count_likes_comm == 2)
        {
            list_like_comm = Random_post.findElements(By.xpath("div[@class='postContainer_likeCommentWrapper__3eUjQ']//child::div[contains(@class,'Count')]"));
            for (int i = 0; i < 2; i++) {
                if (list_like_comm.get(i).getAttribute("class").contains("likeCount"))
                    likes_count = list_like_comm.get(i).getText();
                else if (list_like_comm.get(i).getAttribute("class").contains("commentCount"))
                    comment_count = list_like_comm.get(i).getText();
            }
        }
        No_of_likes = Integer.parseInt(likes_count);
        No_of_comment = Integer.parseInt(comment_count);
        if (Type.equalsIgnoreCase("likes"))
            return No_of_likes;
        else
            return No_of_comment;

    }
}
