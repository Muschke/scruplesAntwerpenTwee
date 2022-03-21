package com.example.scruplesantwerpen.repositories;

import com.example.scruplesantwerpen.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = false)
@Sql({"/insertGebruiker.sql", "/insertConsignatiebon.sql", "/insertEigenschap.sql", "/insertKleur.sql",
"/insertMaat.sql", "/insertMerk.sql", "/insertProduct.sql"})
class ProductRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String PRODUCTEN = "producten";
    private final ProductRepository productRepository;
    private final GebruikerRepository gebruikerRepository;
    private final ConsignatiebonRepository consignatiebonRepository;

    public ProductRepositoryTest(ProductRepository productRepository, GebruikerRepository gebruikerRepository, ConsignatiebonRepository consignatiebonRepository) {
        this.productRepository = productRepository;
        this.gebruikerRepository = gebruikerRepository;
        this.consignatiebonRepository = consignatiebonRepository;
    }

    private byte[] testBytes;
    private Gebruiker gebruiker;
    private Consignatiebon bon;
    private Product product;
    private Eigenschap eigenschap;
    private Kleur kleur;
    private Merk merk;
    private Maat maat;

    @BeforeEach
    void beforeEach() {
        try {
            testBytes = readBytesFromFile("/home/musscheyannick/Pictures/test.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        gebruiker = gebruikerRepository.findById(idVanTestgebruiker()).get();
        bon = new Consignatiebon(gebruiker, LocalDate.now(), testBytes);
        eigenschap = new Eigenschap("trui", "dikke trui");
        kleur = new Kleur("rood");
        merk = new Merk("Chanel");
        maat = new Maat("M");
        product = new Product(gebruiker, bon, eigenschap, kleur, merk, maat, Soort.HEREN, null, "testbeschrijving",
                BigDecimal.TEN, Status.TEKOOP, false, true);
    }

    @Test
    void barcodeSettenWerkt() {
        assertThat(product.getBarcode()).isNull();
        try {
            product.generateAndSetEAN13BarcodeImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(product.getBarcode()).isNotNull();
    }

    @Test
    void gestolenSetterWerkt() {
        assertThat(product.getGestolen()).isFalse();
        product.setGestolen(true);
        assertThat(product.getGestolen()).isTrue();
    }

    @Test
    void findById() {
        assertThat(productRepository.findById(idVanTestProduct()))
                .hasValueSatisfying(product -> assertThat(product.getAankoopprijs())
                        .isEqualByComparingTo("10"));

    }

    @Test
    void verkoopprijsIs2komma5Keeraankoop() {
        assertThat(productRepository.findById(idVanTestProduct()))
                .hasValueSatisfying(product -> assertThat(product.getVerkoopprijs())
                        .isEqualByComparingTo("25"));
    }


    private long idVanTestProduct() {
        return jdbcTemplate.queryForObject("select productId from producten where consignatiebonId = " +
                "(select idConsignatiebon from consignatieBonnen where gebruikerId = (select idgebruiker from gebruikers where naam = 'testNaam'))", Long.class);
    }

    private long idVanTestconsignatiebon() {
        return jdbcTemplate.queryForObject(
                "select idConsignatiebon from consignatieBonnen where gebruikerId = " +
                        "(select idgebruiker from gebruikers where naam = 'testNaam')", Long.class);
    }

    private long idVanTestgebruiker() {
        return jdbcTemplate.queryForObject(
                "select idgebruiker from gebruikers where naam = 'testNaam'", Long.class);
    }

    /*functie om bytes te genereren ter test*/
    private static byte[] readBytesFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();
        return fileBytes;
    }
}