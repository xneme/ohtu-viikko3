package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    }
    
    @Given("^command new user is selected$")
    public void register_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();          
    }
    
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_with_username_with_password_is_successfully_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        registerWith(username, password, password);
        pageHasContent("Welcome to Ohtu Application!");
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_with_username_and_password_is_tried_to_be_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        registerWith(username, password, password);
        element = driver.findElement(By.linkText("back to home"));
        element.click();
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^nonexistant username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_nonexistant_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_password_are_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @When("^too short username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void short_username_and_valid_password_are_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and non-matching password confirmation \"([^\"]*)\" are entered$")
    public void valid_username_and_mismatching_passwords_are_given(String username, String password, String pwdConfirmation) throws Throwable {
        registerWith(username, password, pwdConfirmation);
    }
    
    @When("^a valid username \"([^\"]*)\" and short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_short_password_are_given(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_message_is_given(String error) throws Throwable {
        pageHasContent(error);
        pageHasContent("Create username and give password");
    }
    
    @Then("^a new user is created$")
    public void user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
    
    private void registerWith(String username, String password, String pwdConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pwdConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    }
}
