package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InicioPage  {
    @FindBy(xpath = "//*[@id=\"PropertyTypeId\"]")
    public WebElement selectTipoPropiedad;

    @FindBy(xpath = "//*[@id=\"PropertyTypeId\"]/option")
    public List<WebElement> listaTipoPropiedades;

    @FindBy(xpath = "//*[@id=\"PropertyTypeId\"]/option[. = \"Vivienda\"]")
    public WebElement tipoPropiedadVivienda;

    @FindBy(xpath = "//*[@id=\"MinPrice\"]")
    public WebElement precioDesde;

    @FindBy(xpath = "//*[@id=\"MaxPrice\"]")
    public WebElement precioHasta;

    @FindBy(xpath = "//*[@id=\"ProvinceId\"]")
    public WebElement selectProvincia;

    @FindBy(xpath = "//*[@id=\"ProvinceId\"]/option")
    public List<WebElement> listaProvincias;

    @FindBy(xpath = "//*[@id=\"CantonId\"]")
    public WebElement selectCanton;

    @FindBy(xpath = "//*[@id=\"CantonId\"]/option")
    public List<WebElement> listaCantones;

    @FindBy(xpath = "//*[@id=\"DistrictId\"]")
    public WebElement selectDistrito;

    @FindBy(xpath = "//*[@id=\"DistrictId\"]/option")
    public List<WebElement> listaDistritos;

    @FindBy(xpath = "//*[@id=\"property-index-search-form\"]/div[7]/button")
    public WebElement botonBuscar;

    @FindBy(xpath = "//*[@id=\"property-index\"]/div/div[2]/div[1]")
    public WebElement resultadoPropiedades;

    @FindBy(xpath = "//*[@id=\"property-index\"]/div/div[2]/div[1]/a")
    public List<WebElement> listaResultadoPropiedades;

    public InicioPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
}
