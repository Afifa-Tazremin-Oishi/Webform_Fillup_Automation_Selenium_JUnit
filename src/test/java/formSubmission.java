import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class formSubmission {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @DisplayName("Web form fill-up and submission by visiting the website also File upload and assert the expected message")
    @Test
    public void formFillUp() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-reject-all-handler")).click();

        driver.findElement(By.id("edit-name")).sendKeys("Oishi");
        driver.findElement(By.id("edit-number")).sendKeys("01770884444");
        driver.findElement(By.xpath("//label[contains(text(),'20-30')]")).click();

        driver.findElement(By.id("edit-date")).click();
        DateFormat todayDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date currentDate = new Date();
        String todayDate = todayDateFormat.format(currentDate);
        driver.findElement(By.id("edit-date")).sendKeys(todayDate, Keys.ENTER);

        driver.findElement(By.id("edit-email")).sendKeys("afifatazreminoishi@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Hey! " +
                "I have completed my B.Sc. in CSE from AIUB and now I am a student of Road To SDET " +
                "I want to be a " + "Fullstack SQA Engineer.");


        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        //uploadElement.sendKeys("C:\\SQA\\SQA.jpg");
//        String text = driver.findElement(By.id("uploadedFilePath")).getText();
//        Assertions.assertTrue(text.contains("SQA.jpg"));
        File document = new File(".\\src\\test\\resources\\SQA.jpg");
        uploadElement.sendKeys(document.getAbsolutePath());


        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();

        driver.switchTo().alert().accept();

        String actual_message = driver.findElement(By.className("page-title")).getText();
        String expected_message = "Thank you for your submission!";
        Assertions.assertEquals(actual_message, expected_message);


    }

    @AfterAll
    public void closeDriver(){
        // driver.quit();
    }
}

