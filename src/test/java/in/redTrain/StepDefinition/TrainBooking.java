package in.redTrain.StepDefinition;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TrainBooking {
	public static WebDriver driver;
	
	@Given("Launch the browser and appilication")
	public void launch_the_browser_and_appilication() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();		
		options.addArguments("disable-notifications");
		options.addArguments("disable-popups");
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		String url = "https://www.redbus.in";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
	}
	
	@When("User clicks on redRail")
	public void user_clicks_on_red_rail() {		
		//WebElement travelChoice = driver.findElement(By.xpath("//a[text()='redRail']"));
		WebElement travelChoice = driver.findElement(By.xpath("//span[text()='Train Tickets']"));		
		travelChoice.click();
	}


	@When("User enters the value in from place")
	public void user_enters_the_value_in_from_place() {
		String fromPlace = "Chennai";
		//WebElement from = driver.findElement(By.xpath("//label[text()='From']"));
		//WebElement from = driver.findElement(By.xpath("//div[@class='search-box search-box-src form-control']"));
		WebElement from = driver.findElement(By.xpath("//input[@id='src']"));		
		from.sendKeys(fromPlace);		
		WebElement frompl = driver.findElement(By.xpath("//div[text()='Chennai - All Stations']"));		
		frompl.click();
	}

	@When("User enters the value in to place")
	public void user_enters_the_value_in_to_place() throws InterruptedException {
		String toPlace = "Trichy";		
		//input[@id='src']
		WebElement to = driver.findElement(By.xpath("//input[@id='dst']"));		
		to.sendKeys(toPlace);		
		WebElement topl = driver.findElement(By.xpath("//div[text()='Tiruchchirapali']"));
		topl.click();
		Thread.sleep(6000);
	}

	@When("User selects a data  in the Date DropDown")
	public void user_selects_a_data_in_the_date_drop_down() throws InterruptedException {		
		//WebElement date = driver.findElement(By.xpath("//span[text()='4' and contains(@class,'dkWAbH')]"));		
		//WebElement date = driver.findElement(By.xpath("//div[@class='date-text']"));
	    //WebElement date = driver.findElement(By.xpath("//img[@class='train-icon']"));
		//WebElement date = driver.findElement(By.xpath("//span[@class='sc-htoDjs cvnjgw' and text()='14']"));
		//WebElement date = driver.findElement(By.xpath("//div[@class='home_calendar']"));
		//WebElement date = driver.findElement(By.xpath("//img[@class='train-icon']"));
		//WebElement date = driver.findElement(By.xpath("//div[@class='home_calendar']"));
//		Thread.sleep(6000);
//		WebElement date = driver.findElement(By.xpath("//div[@class='home_date_wrap']"));
		LocalDate date = LocalDate.now();
		int day = date.getDayOfMonth();
		System.out.println("Today date:"+day);
		day++;
		String s1 = "//span[text()="+day+" "+"]";
		System.out.println(s1);
		driver.findElement(By.xpath("//img[@alt='calendar_icon']")).click();
		driver.findElement(By.xpath(s1)).click();
		
//		Actions act = new Actions(driver);
//		act.click(date);
//		
//		date.click();
//		Thread.sleep(6000);
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click", date);
		
	}
//
	@When("User selects a free cancellation")
	public void user_selects_a_free_cancellation() {
		//Actions key = new Actions(driver);
		driver.findElement(By.xpath("//div[@class='checkbox_wrap']")).click();
	}

	@When("User clicks on search button")
	public void user_clicks_on_search_button() {
		//WebElement searchButton = driver.findElement(By.xpath("//button[contains(text(),'SEARCH')]"));
		WebElement searchButton = driver.findElement(By.xpath("//button[text()='search trains']"));
		searchButton.click();
	}

	@Then("Validate the train displayed in the UI")
	public void validate_the_train_displayed_in_the_ui() {
		List<WebElement> trains= driver.findElements(By.xpath("//span[@class='srp_train_name']"));
		List<WebElement> deptime = driver.findElements(By.xpath("//span[@class='srp_departure_time']"));
		List<WebElement> arrtime = driver.findElements(By.xpath("//span[@class='srp_arrival_time']"));
		List<WebElement> farerate = driver.findElements(By.xpath("//div[@class='srp_timimngs_wrap srp_src_dst_stations']"));
		for (int i=0;i<trains.size();i++) {
			if(i==trains.size()-1) {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollTo(0,document.body.scrollHeight)",trains.get(i));
				trains= driver.findElements(By.xpath("//span[@class='srp_train_name']"));
				deptime = driver.findElements(By.xpath("//span[@class='srp_departure_time']"));
				arrtime = driver.findElements(By.xpath("//span[@class='srp_arrival_time']"));
				farerate = driver.findElements(By.xpath("//div[@class='srp_timimngs_wrap srp_src_dst_stations']"));
			}
			else {
				String t1 = trains.get(i).getText();
				String t2 = deptime.get(i).getText();
				String t3 = arrtime.get(i).getText();
				String t4 = farerate.get(i).getText();
				System.out.println("trian name: "+t1+"Departure Time:"+t2+"Arrival Time:"+t3+"place:"+t4);
				//boolean src1 = t4.toLowerCase();
				
			}
		}
	}

	@Then("validate the trains displayed as per given value")
	public void validate_the_trains_displayed_as_per_given_value() {
		System.out.println("\n"+"List of Available Trains from Chennai to Trichy: "+"\n");
		 List<WebElement> trainName = driver.findElements(By.xpath("//span[@class='srp_train_name']"));
		 List<WebElement> depPlace = driver.findElements(By.xpath("//div[text()='Chennai - All Stations']"));
		 List<WebElement> arrPlace = driver.findElements(By.xpath("//div[text()='Tiruchchirapali']"));
             for (int i = 0; i < trainName.size(); i++) {
          String actualDepLocation = depPlace.get(i).getText();
          String actualArrLocation = depPlace.get(i).getText();
          System.out.println(trainName);
          System.out.println(actualDepLocation);
          System.out.println(actualArrLocation);
          String from = "Chennai";
	       String to = "Tiruchchirapali";
if (from.contains(actualDepLocation) && to.contains(actualArrLocation)) {
	 System.out.println("Available Trains: " + trainName.get(i).getText());
}else {
	   System.out.println("Non Available Trains: " + trainName.get(i).getText());
}
          }
	}


}
