package StepDefinition;

import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;

public class Hook {
    @Before
    public static void hook()
    {
        DriverManager.setUpBrowser();
    }

}
