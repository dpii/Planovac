package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.uhk.planovac.Uzivatel;

public interface UzivatelRepository extends JpaRepository<Uzivatel, Long> {

	Uzivatel findByLogin(String login);
}
