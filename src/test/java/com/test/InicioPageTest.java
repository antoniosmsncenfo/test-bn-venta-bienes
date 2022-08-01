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

//    public void ReClicDropdowns()

    @Test()
    public void ObtienePropiedadesTest() {
        ArrayList<String> listaResultados = new ArrayList<>();
        ArrayList<String> provinciasVisitadas = new ArrayList<>();
        ArrayList<String> cantonesVisitados = new ArrayList<>();
        ArrayList<String> distritosVisitados = new ArrayList<>();

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

                provinciasVisitadas.add(provincia.getText());

                System.out.println("Provincia -> " + provincia.getText());

                waitVisibilityOf(10, provincia);
                provincia.click();
                waitVisibilityOf(10, inicioPage.selectCanton);
                inicioPage.selectCanton.click();
                for (WebElement canton : inicioPage.listaCantones) {
                    if (!canton.getText().equals("Cantón")) {
                        System.out.println("canton ->" + canton.getText());
                        waitVisibilityOf(10, canton);
                        canton.click();
                        waitVisibilityOf(10, inicioPage.selectDistrito);
                        inicioPage.selectDistrito.click();
                        for (WebElement distrito : inicioPage.listaDistritos) {
                            //waitVisibilityOf(10, distrito);
                            //distrito.click();
                            if (!distrito.getText().equals("Distrito")) {
                                System.out.println("distrito ->" + distrito.getText());
                                waitVisibilityOf(10, distrito);
                                distrito.click();
                                waitElementToBeClickable(10, inicioPage.botonBuscar);
                                //inicioPage.botonBuscar.click();
                                waitVisibilityOf(10, inicioPage.resultadoPropiedades);
                                List<WebElement> resultados = inicioPage.listaResultadoPropiedades;
                                System.out.println(resultados.size());
                                String viviendasEncontradas =
                                        "Provincia -> " + provincia + " cantón -> " + canton + " distrito -> " + distrito;
                                if (resultados.size() > 0) {
//                                    for (WebElement resultado : resultados) {
//                                        System.out.println(resultado.getText());
//                                    }
                                } else {
                                    viviendasEncontradas += "\n Sin resultados";
                                }
                                listaResultados.add(viviendasEncontradas);

                                waitVisibilityOf(10, inicioPage.selectProvincia);
                                System.out.println(inicioPage.selectProvincia.getText());

                                waitVisibilityOf(10, inicioPage.selectProvincia);
                                inicioPage.selectProvincia.click();

                                waitVisibilityOf(10, inicioPage.selectCanton);
                                inicioPage.selectCanton.click();

                                waitVisibilityOf(10, inicioPage.selectDistrito);
                                inicioPage.selectDistrito.click();


                            }
                        }
                    }
                }
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new Date().toString()));
            for (String linea : listaResultados) {
                writer.write(linea);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        //waitVisibilityOf(10, inicioPage.precioDesde);
        //inicioPage.precioDesde.sendKeys("0");
        //inicioPage.precioHasta.sendKeys("100000000");

        //waitVisibilityOf(10, inicioPage.selectTipoPropiedad);
        //inicioPage.selectTipoPropiedad.click();
        //inicioPage.tipoPropiedadVivienda.click();

        waitVisibilityOf(10, inicioPage.selectProvincia);
        inicioPage.selectProvincia.click();

        while (ultimaProvincia < inicioPage.listaProvincias.size()-1) {
            ultimaProvincia += 1;

            WebElement provincia = inicioPage.listaProvincias.get(ultimaProvincia);
            //waitVisibilityOf(10, provincia);
            provincia.click();

            provinciaNombre = provincia.getText();
            if (!provinciaNombre.equals("Provincia")) {

                //waitVisibilityOf(10, inicioPage.selectCanton);
                inicioPage.selectCanton.click();

                while (ultimoCanton < inicioPage.listaCantones.size()-1) {
                    ultimoCanton += 1;

                    WebElement canton = inicioPage.listaCantones.get(ultimoCanton);
                    //waitVisibilityOf(10, canton);
                    canton.click();
                    cantonNombre = canton.getText();

                    if (!cantonNombre.equals("Cantón")) {

                        //waitVisibilityOf(10, inicioPage.selectDistrito);
                        inicioPage.selectDistrito.click();

                        while (ultimoDistrito < inicioPage.listaDistritos.size()-1) {
                            ultimoDistrito += 1;

                            inicioPage.selectProvincia.click();
                            WebElement distrito = inicioPage.listaDistritos.get(ultimoDistrito);
                            //waitVisibilityOf(10, distrito);
                            inicioPage.selectDistrito.click();
                            distrito.click();
                            distritoNombre = distrito.getText();

                            System.out.println(distritoNombre);
                            if (!distritoNombre.equals("Distrito")) {

                                //waitVisibilityOf(10, inicioPage.botonBuscar);
                                inicioPage.botonBuscar.submit();

                                //waitVisibilityOf(10, inicioPage.resultadoPropiedades);
                                int cantidadPropiedadesEncontradas =0;
                                if(!inicioPage.listaResultadoPropiedades.isEmpty()) {
                                    cantidadPropiedadesEncontradas = inicioPage.listaResultadoPropiedades.size();
                                }
                                System.out.println("Propiedades encontradas: " + cantidadPropiedadesEncontradas);
                                String viviendasEncontradas =
                                        "Provincia -> " + provinciaNombre + ", cantón -> " + cantonNombre + ", distrito -> " + distritoNombre;
                                if (cantidadPropiedadesEncontradas > 0) {
                                    for (WebElement resultado : inicioPage.listaResultadoPropiedades) {
                                        //System.out.println(resultado.findElements(By.xpath("./child::*")));
                                        String nombre = resultado.findElement(By.xpath("//div[2]/div/strong")).getText();
                                        String precio = resultado.findElement(By.xpath("//div[2]/div[5]/div/strong")).getText();
                                        String link = resultado.getAttribute("href");
                                        viviendasEncontradas += "\n" +  "nombre -> " + nombre + ", precio -> " + precio + ", link -> " + link;
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(new Date().toString()));
            for (String linea : listaResultados) {
                writer.write(linea);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
