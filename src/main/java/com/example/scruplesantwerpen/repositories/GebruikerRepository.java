package com.example.scruplesantwerpen.repositories;

import com.example.scruplesantwerpen.domain.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Long> {
}
