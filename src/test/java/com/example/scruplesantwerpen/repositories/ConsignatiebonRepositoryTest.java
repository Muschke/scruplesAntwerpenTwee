package com.example.scruplesantwerpen.repositories;

import com.example.scruplesantwerpen.domain.Consignatiebon;
import com.example.scruplesantwerpen.domain.Gebruiker;
import com.example.scruplesantwerpen.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertGebruiker.sql", "/insertConsignatiebon.sql"})
class ConsignatiebonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String CONSIGNATIEBONNEN = "consignatieBonnen";
    private final GebruikerRepository gebruikerRepository;
    private final ConsignatiebonRepository consignatiebonRepository;
    public ConsignatiebonRepositoryTest(GebruikerRepository gebruikerRepository, ConsignatiebonRepository consignatiebonRepository) {
        this.gebruikerRepository = gebruikerRepository;
        this.consignatiebonRepository = consignatiebonRepository;
    }
    private byte[] testFotoByte;
    private Gebruiker gebruiker;
    private Consignatiebon bon;

    @BeforeEach
    void beforeEach() {
        try {
            testFotoByte = readBytesFromFile("/home/musscheyannick/Pictures/test.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        gebruiker = gebruikerRepository.findById(idVanTestgebruiker()).get();
        bon = new Consignatiebon(gebruiker, LocalDate.now(), testFotoByte);
    }

    @Test
    void nietGetekendFunctieWerkt() {
        assertThat(consignatiebonRepository.findById(idVanTestconsignatiebon()))
                .hasValueSatisfying(
                        bon -> assertThat(bon.getIsGetekend()).isFalse());
    }
    @Test
    void welGetekendFunctieWerkt() {
        assertThat(bon.getIsGetekend()).isTrue();
    }
    @Test
    void datumUitSettenWerkt() {
        assertThat(consignatiebonRepository.findById(idVanTestconsignatiebon()))
                .hasValueSatisfying(bon -> assertThat(bon.getDatumUit()).isEqualTo(LocalDate.of(2022,7,17)));
    }

    @Test
    void findById() {
        assertThat(consignatiebonRepository.findById(idVanTestconsignatiebon()))
                .hasValueSatisfying(
                        bon -> assertThat(bon.getDatumIn()).isEqualTo(LocalDate.of(2022, 1, 17)));
    }

    private long idVanTestgebruiker() {
        return jdbcTemplate.queryForObject(
                "select idgebruiker from gebruikers where naam = 'testNaam'", Long.class);
    }

    private long idVanTestconsignatiebon() {
        return jdbcTemplate.queryForObject(
                "select idConsignatiebon from consignatieBonnen where gebruikerId = " +
                        "(select idgebruiker from gebruikers where naam = 'testNaam')", Long.class);
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


