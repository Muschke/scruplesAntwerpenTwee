package com.example.scruplesantwerpen.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = false)
@Sql("/insertGebruiker.sql")
class GebruikerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GEBRUIKERS = "gebruikers";
    private final GebruikerRepository repository;

    public GebruikerRepositoryTest(GebruikerRepository repository) {
        this.repository = repository;
    }

    private long idVanTestgebruiker() {
        return jdbcTemplate.queryForObject(
                "select idgebruiker from gebruikers where naam = 'testNaam'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestgebruiker()))
                .hasValueSatisfying(
                        gebruiker -> assertThat(gebruiker.getNaam()).isEqualTo("testNaam"));
    }

    @Test
    void gebruikersNaamCorrect() {
        assertThat(repository.findById(idVanTestgebruiker()))
                .hasValueSatisfying(gebruiker ->
                        assertThat(gebruiker.getGebruikersnaam()).isEqualTo("testNaam.testVoornaam"));
    }

    @Test
    void voornaamCorrect() {
        assertThat(repository.findById(idVanTestgebruiker()))
                .hasValueSatisfying(gebruiker ->
                        assertThat(gebruiker.getVoornaam()).isEqualTo("testVoornaam"));
    }
    @Test
    void naamCorrect() {
        assertThat(repository.findById(idVanTestgebruiker()))
                .hasValueSatisfying(gebruiker ->
                        assertThat(gebruiker.getNaam()).isEqualTo("testNaam"));
    }
    @Test
    void mailCorrect() {
        assertThat(repository.findById(idVanTestgebruiker()))
                .hasValueSatisfying(gebruiker ->
                        assertThat(gebruiker.getMail()).isEqualTo("test@hotmail.be"));
    }
    @Test
    void telefoonCorrect() {
        assertThat(repository.findById(idVanTestgebruiker()))
                .hasValueSatisfying(gebruiker ->
                        assertThat(gebruiker.getTelefoonNr()).isEqualTo("0486705824"));
    }

    @Test
    void passwoordCorrect() {
        assertThat(repository.findById(idVanTestgebruiker()))
                .hasValueSatisfying(gebruiker ->
                        assertThat(gebruiker.getPass()).isEqualTo("testNaamtestVoornaam"));
    }
}