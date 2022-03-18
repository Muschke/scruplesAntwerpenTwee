package com.example.scruplesantwerpen.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

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

    @Test
    void findById() {
        assertThat(productRepository.findById(idVanTestProduct()))
                .hasValueSatisfying(product -> assertThat(product.getVerkoopprijs())
                        .isEqualTo(25));
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
}