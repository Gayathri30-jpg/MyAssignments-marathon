package Marathon;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Tatacliq {

	public static void main(String[] args) throws InterruptedException, IOException {


		// Launch the browser
		ChromeDriver driver = new ChromeDriver();
		
		//Load the URL
		driver.get("https://www.tatacliq.com/");
		
		// Maximize the browser
		driver.manage().window().maximize();
		
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("moe-dontallow_button")).click();
		
		//MouseHOver on Brands
		Actions act = new Actions(driver);
		WebElement mousehoverEle = driver.findElement(By.xpath("//div[text()='Brands']"));
		act.moveToElement(mousehoverEle).perform();
		//mousehoverEle.click();
		
		//Select Watches & Accessories
		driver.findElement(By.xpath("//div[text()='Watches & Accessories']")).click();
		
		//Select first Brand
		driver.findElement(By.xpath("(//div[@class='DesktopHeader__featureBrands'])/div[2]")).click();
		
		//Sort BY Newest Arrivals
		WebElement sortByElement = driver.findElement(By.xpath("//select[@class='SelectBoxDesktop__hideSelect']"));
		Select options=new Select(sortByElement);
		options.selectByValue("isProductNew");
		
		//Choose Men from Category
		driver.findElement(By.xpath("(//div[text()='Men'])[1]")).click();
		
		//Print all prices of Watches
		Thread.sleep(5000);
		
		List<WebElement> priceElement = driver.findElements(By.xpath("//div[@class='ProductDescription__priceHolder']//h3"));
		String productprice = priceElement.get(0).getText();
		System.out.println("The Price of first Product is " + productprice);
		for(int i = 0; i < priceElement.size(); i++)
		{
			String price = priceElement.get(i).getText();
			System.out.println("The Price of All Product is " + price);
		}
		
		//Click on the first resulting Watch
		driver.findElement(By.className("ProductModule__dummyDiv")).click();
		
		Set<String> allAddress = driver.getWindowHandles();
		List<String> address = new ArrayList<String>(allAddress);
		
		//SwitchTo Child Window 
		driver.switchTo().window(address.get(1));
		System.out.println(driver.getTitle());
		
		String mrpPriceChild = driver.findElement(By.xpath("//div[@class='ProductDetailsMainCard__price']")).getText();		
		System.out.println("The Price of the Product in New Window is " + mrpPriceChild);
		
		//Compare two price are similar
		if (mrpPriceChild.contains(productprice))
		{
			System.out.println("Product Price in Child Window and Parent Window are similar!");
			
		}
		else {
		System.out.println("Product Price in Child Window and Parent Window are different...!");
		}
		
		//click Add to cart and get count from the cart icon.
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		
		String cartCountEle= driver.findElement(By.xpath("//span[@class='DesktopHeader__cartCount']")).getText();
		System.out.println("Items in Cart: " + cartCountEle);
		
		//Click on the cart
		driver.findElement(By.xpath("//span[@class='DesktopHeader__cartCount']")).click();
		
		//Take a snap of the resulting page. 
		File snapshot = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(snapshot, new File("screenshot.png"));
		
		//Close All the opened windows one by one.
		driver.close();
		driver.switchTo().window(address.get(0));
		driver.close();
		
	}
}