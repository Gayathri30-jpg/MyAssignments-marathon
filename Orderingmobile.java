package Marathon;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class Orderingmobile {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev209663.service-now.com");
		driver.manage().window().maximize();
		// Added an implicit wait to ensure the webpage elements are fully loaded
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("qy/Q6A=vOM3x");
		driver.findElement(By.id("sysverb_login")).click();
		Shadow s = new Shadow(driver);
		s.setImplicitWait(30);
		// to click on all
		// s.findElementByXPath("//div[@id='d6e462a5c3533010cbd77096e940dd8c']").click();
		// //div[contains(text(),'All')]
		s.findElementByXPath("//div[contains(text(),'All')]").click(); // to click on filters tab and send keys
		s.findElementByXPath("//input[@id='filter']").sendKeys("Service Catalog");
		// click service catalog
		s.findElementByXPath("//mark[contains(text(),'Service Catalog')]").click();

		Thread.sleep(3000);
		// to click on mobiles need to switch to frame by frame element
		// iframe[@id="gsft_main"]
		WebElement frame1 = s.findElementByXPath("//iframe[@id='gsft_main']");
		// WebElement frame1 =
		// s.findElementByXPath("//iframe[contains(@title,'global_search')]");
		driver.switchTo().frame(frame1);
		// driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();
		s.findElementByXPath("//h2[contains(text(),'Mobiles')]").click();
		s.findElementByXPath("//td[contains(text(),'Request for Apple iPhone 13 pro')]").click();
		s.findElementByXPath("//label[@class='radio-label' and contains(text(),'Yes')]").click();
		// //input[contains(@class,'content-pad form-control')]
		s.findElementByXPath("//input[contains(@class,'content-pad form-control')] ").sendKeys("99");
		WebElement dropdown = s.findElementByXPath("//select[@name='IO:ff1f478e9747011021983d1e6253af68']");
		Select data = new Select(dropdown);
		data.selectByValue("unlimited");
		s.findElementByXPath("//label[@class='radio-label' and contains(text(),'Sierra Blue')]").click();
		s.findElementByXPath("//button[@id='oi_order_now_button']").click();
		String orderstatus = s.findElementByXPath("//div[@class='notification notification-success']").getText();
		System.out.println("OrderStatus : " + orderstatus);
		String reqid = s.findElementByXPath("//a[@id='requesturl']").getText();
		System.out.println("Request ID : " + reqid);
		// Step1: Taking the Screenshot
		File sorurce = driver.getScreenshotAs(OutputType.FILE);

		// Step2: Setup the destination
		File destination = new File("./Screenshot/mobileordering.png");

		// Step3: Store the screenshot file in the destination
		FileUtils.copyFile(sorurce, destination);
	}

}