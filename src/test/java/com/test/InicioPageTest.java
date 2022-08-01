package com.test;

import com.pages.InicioPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    @Test()
    public void ObtienePropiedadesTestV2() {
        List<String> listaResultados = new ArrayList<>();

        int ultimaProvincia = -1;
        int ultimoCanton = -1;
        int ultimoDistrito = -1;
        String provinciaNombre = "";
        String cantonNombre = "";
        String distritoNombre = "";

        inicioPage.precioDesde.sendKeys("0");
        inicioPage.precioHasta.sendKeys("300000000");

        //inicioPage.selectTipoPropiedad.click();
        //inicioPage.tipoPropiedadVivienda.click();

        waitVisibilityOf(10, inicioPage.selectProvincia);
        inicioPage.selectProvincia.click();

        while (ultimaProvincia < inicioPage.listaProvincias.size() - 1) {
            ultimaProvincia += 1;

            WebElement provincia = inicioPage.listaProvincias.get(ultimaProvincia);
            provincia.click();

            provinciaNombre = provincia.getText();
            if (!provinciaNombre.equals("Provincia")) {

                inicioPage.selectCanton.click();

                while (ultimoCanton < inicioPage.listaCantones.size() - 1) {
                    ultimoCanton += 1;

                    WebElement canton = inicioPage.listaCantones.get(ultimoCanton);
                    canton.click();
                    cantonNombre = canton.getText();

                    if (!cantonNombre.equals("Cantón")) {

                        inicioPage.selectDistrito.click();

                        while (ultimoDistrito < inicioPage.listaDistritos.size() - 1) {
                            ultimoDistrito += 1;

                            inicioPage.selectProvincia.click();
                            WebElement distrito = inicioPage.listaDistritos.get(ultimoDistrito);

                            inicioPage.selectDistrito.click();
                            distrito.click();
                            distritoNombre = distrito.getText();

                            System.out.println(distritoNombre);
                            if (!distritoNombre.equals("Distrito")) {

                                inicioPage.botonBuscar.submit();

                                int cantidadPropiedadesEncontradas = 0;

                                if (!inicioPage.listaResultadoPropiedades.isEmpty()) {
                                    cantidadPropiedadesEncontradas = inicioPage.listaResultadoPropiedades.size();
                                }

                                System.out.println("Propiedades encontradas: " + cantidadPropiedadesEncontradas);

                                String viviendasEncontradas =
                                        "\nProvincia -> " + provinciaNombre + ", cantón -> " + cantonNombre + ", distrito -> " + distritoNombre;

                                if (cantidadPropiedadesEncontradas > 0) {
                                    for (WebElement resultado : inicioPage.listaResultadoPropiedades) {
                                        String nombre = resultado.findElement(By.xpath("//div[2]/div/strong")).getText();
                                        String precio = resultado.findElement(By.xpath("//div[2]/div[5]/div/strong")).getText();
                                        String link = resultado.getAttribute("href");
                                        viviendasEncontradas += ("\n" + "nombre -> " + nombre + ", precio -> " + precio + ", link -> " + link);
                                    }
                                } else {
                                    viviendasEncontradas += "\n Sin resultados";
                                }
                                listaResultados.add(viviendasEncontradas);
                            }
                            inicioPage.selectDistrito.click();
                        }
                        ultimoDistrito = -1;
                    }
                    inicioPage.selectCanton.click();
                }
                ultimoCanton = -1;
            }
            inicioPage.selectProvincia.click();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new Date().toString().replace(':', '_').replace(' ', '_')+".txt"));
            for (String linea : listaResultados) {
                writer.write(linea);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
