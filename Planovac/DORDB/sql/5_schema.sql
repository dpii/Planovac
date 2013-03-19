CREATE DATABASE IF NOT EXISTS planovac;

USE planovac;

CREATE TABLE IF NOT EXISTS UZIVATELE (
  idUzivatele INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(30) NOT NULL,
  jmeno VARCHAR(30),
  prijmeni VARCHAR(30),
  email VARCHAR(30) NOT NULL,
  heslo_hash VARCHAR(30) NOT NULL,
  povolen tinyint(1) NOT NULL,
  role VARCHAR(45) NOT NULL,
  datumZalozeni DATE,
  datumPosledniPrihlaseni DATE,
  adresa VARCHAR(30),
  mesto VARCHAR(30),
  telefon VARCHAR(20),
  INDEX(prijmeni)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS SKUPINY (
  idSkupiny INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nazev VARCHAR(30),
  idVedouciho INT(4) UNSIGNED NOT NULL,
  popis VARCHAR(255),
  verejna BOOLEAN,
  INDEX(nazev),
  FOREIGN KEY (idVedouciho) REFERENCES UZIVATELE(idUzivatele)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS UDALOSTI (
  idUdalosti INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nazev VARCHAR(30),
  verejna BOOLEAN,
  idVlastnikaSk INT(4) UNSIGNED,
  idVlastnikaUz INT(4) UNSIGNED,
  obrazek VARCHAR(30),
  zacatek DATETIME,
  konec DATETIME,
  popis VARCHAR(255),
  INDEX(nazev),
  FOREIGN KEY (idVlastnikaSk) REFERENCES SKUPINY(idSkupiny),
  FOREIGN KEY (idVlastnikaUz) REFERENCES UZIVATELE(idUzivatele)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS SKUPINY_UZIVATELU (
  idSkupiny INT(4) UNSIGNED NOT NULL,
  idUzivatele INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (idSkupiny) REFERENCES SKUPINY(idSkupiny),
  FOREIGN KEY (idUzivatele) REFERENCES UZIVATELE(idUzivatele),
  UNIQUE (idSkupiny,idUzivatele)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS UDALOSTI_UZIVATELU (
  idUdalosti INT(4) UNSIGNED NOT NULL,
  idUzivatele INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (idUdalosti) REFERENCES UDALOSTI(idUdalosti),
  FOREIGN KEY (idUzivatele) REFERENCES UZIVATELE(idUzivatele),
  UNIQUE (idUdalosti,idUzivatele)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS PRIPOMINKY (
  idPripominky INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  idUdalosti INT(4) UNSIGNED NOT NULL,
  idUzivatele INT(4) UNSIGNED NOT NULL,
  typ_pripominky INT(4) UNSIGNED,
  cas_pred_udalosti DATETIME,
  opakovani_po_intervalu INT(4) UNSIGNED,
  aktivni BOOLEAN,
  FOREIGN KEY (idUdalosti) REFERENCES UDALOSTI(idUdalosti),
  FOREIGN KEY (idUzivatele) REFERENCES UZIVATELE(idUzivatele),
  UNIQUE (idPripominky)
) engine=InnoDB;

