package Steps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class Assignment1 {
	WebDriver driver=null;
	String projectPath=System.getProperty("user.dir");
	
	@Before()
	public void BrowserSetUp(){
		System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/Driver/chromedriver.exe" );
		driver=new ChromeDriver();	
		driver.manage().window().maximize();
	}
	
	
	@Given("User hits the react legacy page")
	public void LaunchReactJsPage() {
		driver.get("https://legacy.reactjs.org/ ");
		System.out.print("Title of the page " +driver.getTitle());
	}
	@When("User clicks on {string} tab")
	public void clickDocsTab(String Tab) throws InterruptedException {
		WebElement Doc=driver.findElement(By.xpath("//a[contains(text(),'"+Tab+"')]"));
		Doc.click();
	}
	@Given("User can see {string} menu in the right hand panel")
	public void MenuVisibilityCheck(String menu) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+menu+"')]")));
		WebElement mainConceptRef=driver.findElement(By.xpath("//div[contains(text(),'"+menu+"')]"));
		mainConceptRef.isDisplayed();
				
	}
	@When("User clicks on the {string} menu")
	public void ExpandMenu(String menu) {
		WebElement ref=driver.findElement(By.xpath("//div[contains(text(),'"+menu+"')]"));
		ref.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}
	@Then("User can see and highlight the options of {string} menu")
	public void highlightOptions(String menu) {
		List<WebElement> options=driver.findElements(By.xpath("//button[@aria-expanded=\"true\"]/following-sibling::ul/li"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		for(int i=0;i<options.size();i++) {
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", options.get(i));
			js.executeScript("arguments[0].setAttribute('style', 'background: white;');", options.get(i));
		}
	}
	
	@And("User writes the menu of {string} to a file")
	public void writeMenuToFiles(String menu) throws IOException {
		List<WebElement> options=driver.findElements(By.xpath("//button[@aria-expanded=\"true\"]/following-sibling::ul/li"));
		
				if(menu.equalsIgnoreCase("Main Concepts")) {
					File fi=new File(projectPath+"/src/main/resources/File/Menu.txt");
					FileWriter fw=new FileWriter(fi.getAbsoluteFile());
					BufferedWriter bw=new BufferedWriter(fw);
					bw.write("Main Concepts subMenu");
					bw.newLine();
					for(int i=0 ; i<options.size();i++)
			        {	
			                bw.write(((WebElement) options.get(i)).getText());
			                bw.newLine();
			                System.out.println(((WebElement) options.get(i)).getText());
			            }
			            bw.close();
				}
				else if(menu.equalsIgnoreCase("Advanced Guides")) {
					File fi=new File(projectPath+"/src/main/resources/File/Advance.txt");
					FileWriter fw=new FileWriter(fi.getAbsoluteFile());
					BufferedWriter bw=new BufferedWriter(fw);
					bw.write("Advanced Guides subMenu");
					bw.newLine();
					for(int i=0 ; i<options.size();i++)
			        {	
			                bw.write(((WebElement) options.get(i)).getText());
			                bw.newLine();
			                System.out.println(((WebElement) options.get(i)).getText());
			        }
			            bw.close();
				}
			
		}
	
	
	//Assignment 2 - During scroll highlight the content [Heading content]
	
	@When("User scroll the page respective content at the right hand panel is highlighted")
	public void scroll() throws InterruptedException {
		
	Thread.sleep(5000);
		List<WebElement> heading=driver.findElements(By.xpath("//div[contains(text(),'Tutorial')]/parent::button/following-sibling::ul/li/a"));
		for(int i=0;i<heading.size();i++) {
			String headingText=heading.get(i).getText();
			System.out.println("Header : "+headingText+"");
			String beforeValue=heading.get(i).getCssValue("font-weight");
			System.out.println("Before Scroll font weight "+beforeValue+"");
			scrollToTheElement(headingText,heading.get(i));
		}
	}
	public void scrollToTheElement(String text,WebElement link) throws InterruptedException {
		WebElement scrollTarget=driver.findElement(By.xpath("//h2[contains(text(),'"+text+"')]"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",scrollTarget);
		Thread.sleep(4000);
		checkBold(link);
		checkColor(link);
	}
	public void checkBold(WebElement rightElement) {
		String AfterScrollBold=rightElement.getCssValue("font-weight");
		System.out.println("After Scroll font weight "+AfterScrollBold+"");	
	}
	public void checkColor(WebElement rightElement) {
		WebElement newHighlighter=rightElement.findElement(By.tagName("span"));
		String AfterScrollColor=newHighlighter.getCssValue("border-left");
		System.out.println("After Scroll color"+AfterScrollColor+"");	
	}	
}
