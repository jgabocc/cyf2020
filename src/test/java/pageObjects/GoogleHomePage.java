package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globant.automation.cyf2020.infrastructure.BasePage;

public class GoogleHomePage extends BasePage {

	public GoogleHomePage(WebDriver driver) {
		super(driver);
		driver.get("http://www.google.com/");
		// TODO Auto-generated constructor stub
	}

	@FindBy(name="q")
	private WebElement searchName;
	
	@FindBy (name="btnK")
	private WebElement searchBtn;
	
	public void googleSearch (String gWord) {
	 type(searchName,gWord);
		click(searchBtn);
	}
	
	

}