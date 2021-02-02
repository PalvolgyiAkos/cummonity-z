package testautomation.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testautomation.TestRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommunityZSearch extends TestRunner {
    private final int explicitwait = 30;

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS).pageLoadTimeout(10, TimeUnit.SECONDS);
        Dimension dimension = new Dimension(690, 1000);
        driver.manage().window().setSize(dimension);
    }

    @Given("the home page is open")
    public void theHomePageIsOpen() {
        driver.manage().deleteAllCookies();
        driver.get("https://community-z.com/");
        new WebDriverWait(driver, explicitwait).until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("evnt-global-loader"))));
    }

    @And("the COMMUNITIES header is clicked")
    public void theCommunitiesHeaderIsClicked() {
        new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("evnt-responsive-toggle-box")))).click();
        new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("nav-link")))).click();
    }

    @Given("the Cookie disclaimer is closed")
    public void theCookieDisclaimerIsClosed() {
        driver.findElement(By.cssSelector("button.evnt-button.btn.small.sky.reg-button.attend")).click();
    }

    @When("the search field is filled in with {string}")
    public void theSearchByTitleOrTagsIsFilledInWithParameter(String content) {
        //new WebDriverWait(driver, explicitwait).until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("evnt-global-loader"))));
        driver.findElement(By.cssSelector(".evnt-text-fields.form-control.evnt-search")).sendKeys(content);
    }


    //.evnt-search-filter
    @Then("the No Results Found msg should be shown")
    public void theNoResultsFoundErrorMsgShouldBeShown() {
        driver.findElement(By.className("evnt-no-results")).isDisplayed();
    }

    @Then("wait the search and the {string} is should contains the {string}")
    public void theFieldIsContainsTheParameter(String field, String searched) {
        new WebDriverWait(driver, explicitwait).until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("evnt-global-loader"))));
        List<WebElement> foundElements = driver.findElements(By.cssSelector(field));
        for (WebElement webelement : foundElements) {
            Assert.assertTrue(webelement.getText().toLowerCase().contains(searched));
        }
    }

    @And("click on the filters menu")
    public void clickOnTheFiltersMenu() {
        driver.findElement(By.className("evnt-filters-icon")).click();
    }

    @And("click on the {string} drop down")
    public void clickOnTheCategoryDropDown(String options) {
        if (options.equals("Category")) {
            new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("filter_category")))).click();
        } else if (options.equals("Location")) {
            new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("filter_location")))).click();
        }
    }

    @And("the text field is filled in with {string} at {string}")
    public void theTextFieldIsFilledInWithParameter(String content, String options) {
        if (options.equals("Category")) {
            new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(
                    driver.findElement(By.cssSelector(".evnt-filter-menu-search-wrapper .evnt-text-fields.form-control.evnt-search")))).sendKeys(content);
        } else if (options.equals("Location")) {
            new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(
                    driver.findElement(By.cssSelector(".evnt-filter-menu.evnt-dropdown-menu.dropdown-menu.show .evnt-filter-menu-search-wrapper .evnt-text-fields.form-control.evnt-search"/*#modal-app > div > div > div > div.evnt-popup-scroll > div > div:nth-child(2) > div > div > div.evnt-filter-menu.evnt-dropdown-menu.dropdown-menu.show > div.evnt-filter-menu-search-wrapper > input"*/)))).sendKeys(content);
        }
    }

    @And("the first check-box is clicked on {string} at {string}")
    public void theFirstCheckBoxIsClicked(String content, String options) {
        if (options.equals("Category")) {
            new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(
                    driver.findElement(By.cssSelector(".evnt-checkbox [data-value=\""+ content +"\"]")))).click();
        } else if (options.equals("Location")) {
            new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(
                    driver.findElement(By.cssSelector(".evnt-checkbox [data-value=\""+ content +"\"]")))).click();
        }
    }

    @And("the Show results button is clicked")
    public void theShowResultsButtonIsClicked() {
        driver.findElement(By.cssSelector(".evnt-button.grass.small.submit-button.show-results-button")).click();
    }

    @Then("the {string} field is should contains the {string}")
    public void theEvntInfoCellIsContainsTheParameter(String className, String parameter) {
        List<WebElement> foundElements = driver.findElements(By.className(className));
        for (WebElement webelement : foundElements) {
            Assert.assertTrue(webelement.getText().toLowerCase().contains(parameter.toLowerCase()));
        }
    }

    @And("click on the drop down menu")
    public void clickOnTheDropDownMenu() {
        new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"modal-app\"]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div[1]")))).click();
    }

    @Then("^the side drop down menu should (not be|be) shown$")
    public void theSideDropDownMenuShouldBeShown(String parameter) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#collapse_filter_location_0 > div:nth-child(1) > label"))));
        if (parameter.equals("be"))
        {
            Assert.assertTrue(driver.findElement(By.cssSelector("#collapse_filter_location_0 > div:nth-child(1) > label")).isDisplayed());
        }/*else if (parameter.equals("not be")){
            new WebDriverWait(driver, explicitwait).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"modal-app\"]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div[1]")))).click();
            Assert.assertFalse(driver.findElement(By.cssSelector("#collapse_filter_location_0 > div:nth-child(1) > label")).isDisplayed());
        }*/
    }
}