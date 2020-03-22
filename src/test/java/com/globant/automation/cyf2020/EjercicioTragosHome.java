package com.globant.automation.cyf2020;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globant.automation.cyf2020.infrastructure.BasePage;

public class EjercicioTragosHome extends BasePage{
	
	public EjercicioTragosHome(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id = "download-button")
	private WebElement recomendarBtn;
	
	public String obtenerNombre() {
		click(recomendarBtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement nombrePrimerTrago = getElement(By.id("tit1"));
		return getText(nombrePrimerTrago);
	}

}