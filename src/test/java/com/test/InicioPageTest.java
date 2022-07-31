package com.test;

import com.pages.InicioPage;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Listeners(ListenerTest.class)
public class InicioPageTest extends TestBase {
    private WebDriver driver;
    private InicioPage inicioPage;

    @BeforeClass
    public void SetUp() {
        //Get driver
        driver = getDriver();
        inicioPage = new InicioPage(driver);
    }


    @Severity(SeverityLevel.NORMAL)
    @Epic("Módulo Ventana ubicaciones")
    @Description("Verifica que la página de ubicaciones muestre las provincias de las sucursales.")
    @Story("CP17 - Verificar las provincias de las sucusarles")
    @Test(priority = 0)
    public void VerificaLasProvinciasDeSucursalesTest() {
        ArrayList<String> listaResultados = new ArrayList<>();
        waitVisibilityOf(10, inicioPage.precioDesde);
        inicioPage.precioDesde.sendKeys("0");
        inicioPage.precioHasta.sendKeys("100000000");

        waitVisibilityOf(10, inicioPage.selectTipoPropiedad);
        inicioPage.selectTipoPropiedad.click();
        inicioPage.tipoPropiedadVivienda.click();

        waitVisibilityOf(10, inicioPage.selectProvincia);
        inicioPage.selectProvincia.click();

        for (WebElement provincia : inicioPage.listaProvincias) {
            if (!provincia.getText().equals("Provincia")) {
                System.out.println(provincia.getText());
                waitVisibilityOf(10, provincia);
                provincia.click();
                inicioPage.selectCanton.click();
                for (WebElement canton : inicioPage.listaCantones) {
                    if (!canton.getText().equals("Cantón")) {
                        waitVisibilityOf(10, canton);
                        canton.click();
                        inicioPage.selectDistrito.click();
                        for (WebElement distrito : inicioPage.listaDistritos) {
                            if (!distrito.getText().equals("Distrito")) {
                                waitVisibilityOf(10, distrito);
                                distrito.click();
                                waitVisibilityOf(10, inicioPage.botonBuscar);
                                inicioPage.botonBuscar.submit();
                                waitVisibilityOf(10, inicioPage.resultadoPropiedades);
                                List<WebElement> resultados = inicioPage.listaResultadoPropiedades;
                                if (resultados != null && resultados.size() > 0) {
                                    for (WebElement resultado : resultados) {
                                        System.out.println(resultado.getText());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


    }


}
