package Marathon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class PvrTicketBooking {

	public static void main(String[] args) throws InterruptedException {
	
		//launch the browser
		ChromeDriver driver = new ChromeDriver();
		//load url
		driver.get("https://www.pvrcinemas.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//span[text()='Cinema']")).click();
	
		//handling dropdown
		driver.findElement(By.xpath("//span[text()='Select Cinema']")).click();
	 driver.findElement(By.xpath("//span[text()='PVR Sathyam Royapettah Chennai']")).click();
	
	 Thread.sleep(3000);
	//select date
	 driver.findElement(By.xpath("//span[text()='Tomorrow']")).click();
	 Thread.sleep(1000);
	 

	 //select movie
	 driver.findElement(By.xpath("//span[text()='THE FANTASTIC FOUR: FIRST STEPS']")).click();

     Thread.sleep(1000);

	//select time
    driver.findElement(By.xpath("//span[contains(text(),'10:30 AM')]")).click();
    
	Thread.sleep(1000);
    
	//CLICK BOOK BUTTON
	driver.findElement(By.xpath("(//span[text()='Book'])[4]")).click();
	
	Thread.sleep(1000);
	
	driver.findElement(By.xpath("//button[text()='Accept']")).click();
	
	//select seat
	driver.findElement(By.xpath("//span[@id='BU.BUDGET|N:12']")).click();
	String seatInfo = driver.findElement(By.xpath("//div[@class='select-seat-number']")).getText();
	System.out.println("Seatinfo:" +seatInfo);
	
	//print grand total
	String grandTotal = driver.findElement(By.xpath("//div[@class='grand-prices']")).getText();
	System.out.println("Grandtotal:" +grandTotal);
	driver.findElement(By.xpath("//button[text()='Proceed']")).click();
	
	//Close the pop-up
	Thread.sleep(5000);
	driver.findElement(By.xpath("//h6[text()='SKIP']")).click();
	
	//print the current page title
	String pageTitle = driver.getTitle();
	System.out.println("Current page Title:" +pageTitle);
	
	//close the browser
	Thread.sleep(2000);
	driver.close();
	Thread.sleep(1000);
		
	

		 
		 
	}

}
