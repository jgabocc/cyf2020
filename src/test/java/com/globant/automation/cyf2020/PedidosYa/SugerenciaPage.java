package com.globant.automation.cyf2020.PedidosYa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.globant.automation.cyf2020.infrastructure.BasePage;

public class SugerenciaPage extends BasePage {
	
	public SugerenciaPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	String precioInicial = "";
	String precioFinalProd = "";
	
	@FindBy(xpath = "//div[@id=\"footerOpen\"]//a[@class=\"button\"]//i")
	WebElement agregarPedidoBtn;
	
	@FindBy( css= "div[class=price total-price]")
	private WebElement precioFinal;

	public void AgregarPedido() {
		
		WebElement agregarPedidoBtn = getElement(By.xpath("//div[@class=\"tbox\"]//div[@class=\"tinner\"]//div[@class=\"tcontent\"]//div[@class=\"menuModal \"]//footer//a//i"), EXTENDED_TIMEOUT);
		click(agregarPedidoBtn);
		precioFinalProd = getText(precioFinal, EXTENDED_TIMEOUT);
		System.out.println("El precio final es: " + precioFinalProd);
	
	}
	
	public String getPrecioFinalProd() {
		return this.precioFinalProd;
	}
}
