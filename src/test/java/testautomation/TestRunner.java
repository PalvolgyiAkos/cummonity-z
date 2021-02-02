package testautomation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/feature-files",
        glue = "testautomation.stepdef"
)
public class TestRunner {
    protected static WebDriver driver;

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
