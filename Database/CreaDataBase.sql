DROP DATABASE IF EXISTS Hektiks;
CREATE DATABASE Hektiks;
USE Hektiks;
SET sql_mode = "";
SET GLOBAL sql_mode = "";

CREATE TABLE Utenti (

    email VARCHAR(320) PRIMARY KEY,
    nome VARCHAR(35) NOT NULL,
    cognome VARCHAR(35) NOT NULL,
    username VARCHAR(25) NOT NULL,
    password_utente VARCHAR(40) NOT NULL,
    data_registrazione DATE NOT NULL,
    ruolo BOOL NOT NULL DEFAULT FALSE, -- TRUE per Admin, FALSE per User
    saldo DECIMAL (10, 2) NOT NULL CHECK (saldo >= 0) DEFAULT 0, 
    profile_pic TEXT,
    
    UNIQUE (username)
);

CREATE TABLE Generi (

	nome_genere VARCHAR(35) PRIMARY KEY
);

CREATE TABLE Giochi (

	codice_gioco CHAR(6) PRIMARY KEY,
	titolo VARCHAR(55) NOT NULL,
    descrizione TEXT NOT NULL,
    trailer VARCHAR(2048) NOT NULL,
    data_uscita DATE NOT NULL,
    copertina VARCHAR(2048) NOT NULL,
    prezzo DECIMAL (5, 2) NOT NULL CHECK (prezzo >= 0), 
	quantita_disponibile INT UNSIGNED NOT NULL,
    numero_vendite INT UNSIGNED NOT NULL DEFAULT 0,
    percentuale_sconto DECIMAL(5,2),
    
    UNIQUE (titolo)
);

CREATE TABLE GiftCards (

	codice_giftCard CHAR(6) PRIMARY KEY,
	email_utente VARCHAR(320), 
    importo DECIMAL (10, 2) NOT NULL CHECK (importo  > 0), 
    data_ora_creazione DATETIME NOT NULL,
	data_ora_utilizzo DATETIME,
    
    FOREIGN KEY (email_utente) REFERENCES Utenti (email) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE Carrelli (

	email_utente VARCHAR(320), 
    data_creazione DATE NOT NULL,
    data_modifica DATE,
    
    FOREIGN KEY (email_utente) REFERENCES Utenti (email) ON UPDATE CASCADE ON DELETE NO ACTION,
    PRIMARY KEY (email_utente, data_creazione)
);

CREATE TABLE Giochi_Genere (
	
    codice_gioco CHAR(6),
    nome_genere VARCHAR(35),
    
    FOREIGN KEY (codice_gioco) REFERENCES Giochi (codice_gioco) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nome_genere) REFERENCES Generi (nome_genere) ON UPDATE CASCADE ON DELETE NO ACTION,
    PRIMARY KEY (codice_gioco, nome_genere)
);

CREATE TABLE Prodotti (

	email_utente VARCHAR(320), 
    codice_gioco CHAR(6),
	quantita_disponibile INT UNSIGNED NOT NULL,
    
	FOREIGN KEY (email_utente) REFERENCES Carrelli (email_utente) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (codice_gioco) REFERENCES Giochi (codice_gioco) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (email_utente, codice_gioco) 
);

CREATE TABLE Recensioni (

	email_utente VARCHAR(320), 
    codice_gioco CHAR(6),
    data_ora_pubblicazione DATETIME,
    voto DOUBLE NOT NULL CHECK (voto >= 0 AND voto <= 5),
    descrizione TEXT,
    
	FOREIGN KEY (email_utente) REFERENCES Utenti (email) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (codice_gioco) REFERENCES Giochi (codice_gioco) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (email_utente, codice_gioco)
);

CREATE TABLE Ordini (

	email_utente VARCHAR(320), 
	codice_ordine CHAR(6),
	data_ora_ordinazione DATETIME NOT NULL,
    prezzo_totale DECIMAL (10, 2) NOT NULL CHECK (prezzo_totale >= 0), 
    
	FOREIGN KEY (email_utente) REFERENCES Utenti (email) ON UPDATE CASCADE ON DELETE NO ACTION,
    PRIMARY KEY (email_utente, codice_ordine)
);

CREATE TABLE Pagamenti (

	email_utente VARCHAR(320), 
	codice_ordine CHAR(6),
	data_ora_pagamento DATETIME NOT NULL,
    importo DECIMAL (10, 2) NOT NULL CHECK (importo >= 0), 
    
	FOREIGN KEY (email_utente, codice_ordine) REFERENCES Ordini (email_utente, codice_ordine) ON UPDATE CASCADE ON DELETE NO ACTION,
    PRIMARY KEY (email_utente, codice_ordine, data_ora_pagamento)
);

CREATE TABLE Prodotti_Ordini (

	email_utente VARCHAR(320), 
	codice_ordine CHAR(6),
	codice_gioco CHAR(6),
    data_ora_creazione DATETIME,
    prezzo DECIMAL(5, 2) NOT NULL CHECK (prezzo >= 0),
	quantita INT UNSIGNED NOT NULL,
        
	FOREIGN KEY (email_utente, codice_ordine) REFERENCES Ordini (email_utente, codice_ordine) ON UPDATE CASCADE ON DELETE NO ACTION,
	FOREIGN KEY (codice_gioco) REFERENCES Giochi (codice_gioco) ON UPDATE CASCADE ON DELETE NO ACTION,
	PRIMARY KEY (email_utente, codice_ordine, codice_gioco, data_ora_creazione)
);
