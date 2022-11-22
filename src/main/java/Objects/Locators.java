package Objects;

import org.openqa.selenium.By;

public class Locators {
    public static By GemBookLogo = By.xpath("//div[@class='logo d-flex justify-content-center pt-5']//img");
    public static By SignUpButton = By.xpath("//button[@class='auth_signin__3ILKi btn btn-secondary']");
    public static By WarnPopUp = By.xpath("//div[@role='alert' and contains(text(),'Login Failed')]");
    public static By SignInHeader= By.xpath("//div[@role='heading']");
    public static By SignInField = By.xpath("//input[@id='i0116']");
    public static By SignInNextButton = By.xpath("//input[@id='idSIButton9']");
    public static By PassWordField = By.xpath("//input[@id='i0118']");
    public static By PassWordNextButton= By.xpath("//input[@id='idSIButton9']");
    public static By StaySignedNoButton = By.xpath("//input[@id='idBtn_Back']");
}
