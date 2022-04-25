SET sql_mode = '';
SET GLOBAL sql_mode = '';

DROP DATABASE IF EXISTS Hektiks;
CREATE DATABASE Hektiks;
USE Hektiks;

CREATE TABLE Utenti (

    email VARCHAR(320) PRIMARY KEY,
    username VARCHAR(25) NOT NULL,
    password_utente VARCHAR(32) NOT NULL,
    data_registrazione DATE NOT NULL,
    ruolo BOOL NOT NULL, -- TRUE per Admin, FALSE per User
    saldo DECIMAL (10, 2) NOT NULL CHECK (saldo >= 0), 
    biografia TEXT,
    
    UNIQUE (username)
);

CREATE TABLE Generi (

	nome_genere VARCHAR(35) PRIMARY KEY
);

CREATE TABLE Giochi (

	codice_gioco CHAR(6) PRIMARY KEY,
	titolo VARCHAR(35) NOT NULL,
    descrizione TEXT NOT NULL,
    trailer VARCHAR(2048) NOT NULL,
    data_uscita DATE NOT NULL,
    copertina VARCHAR(2048) NOT NULL,
    prezzo DECIMAL (5, 2) NOT NULL CHECK (prezzo > 0), 
	quantita_disponibile INT UNSIGNED NOT NULL,
    numero_vendite INT UNSIGNED NOT NULL DEFAULT 0,
    
    UNIQUE (titolo)
);

CREATE TABLE Carrelli (

	email_utente VARCHAR(320), 
    data_creazione DATE NOT NULL,
    data_modifica DATE,
    
    FOREIGN KEY (email_utente) REFERENCES Utenti (email) ON UPDATE CASCADE ON DELETE NO ACTION,
    PRIMARY KEY (email_utente, data_creazione)
);

CREATE TABLE Appartenere (
	
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

CREATE TABLE Sconti (

	codice_sconto CHAR(6),
    codice_gioco CHAR(6),
    data_creazione DATE NOT NULL,
    percentuale TINYINT NOT NULL,
    data_fine DATE,
    
    FOREIGN KEY (codice_gioco) REFERENCES Giochi (codice_gioco) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (codice_sconto, codice_gioco)
);

CREATE TABLE Recensioni (

	email_utente VARCHAR(320), 
    codice_gioco CHAR(6),
    data_ora_pubblicazione DATETIME,
    percentuale TINYINT NOT NULL,
    descrizione TEXT,
    
	FOREIGN KEY (email_utente) REFERENCES Utenti (email) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (codice_gioco) REFERENCES Giochi (codice_gioco) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (email_utente, codice_gioco, data_ora_pubblicazione)
);

CREATE TABLE GiftCards (

	codice_giftCard CHAR(6) PRIMARY KEY,
	email_utente VARCHAR(320), 
    importo DECIMAL (10, 2) NOT NULL CHECK (importo  > 0), 
	data_utilizzo DATE NOT NULL,
    
    FOREIGN KEY (email_utente) REFERENCES Utenti (email) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE Ordini (

	email_utente VARCHAR(320), 
	codice_ordine CHAR(6),
	data_ora_ordinazione DATETIME NOT NULL,
    prezzo_totale DECIMAL (10, 2) NOT NULL CHECK (prezzo_totale > 0), 
    
	FOREIGN KEY (email_utente) REFERENCES Utenti (email) ON UPDATE CASCADE ON DELETE NO ACTION,
    PRIMARY KEY (email_utente, codice_ordine)
);

CREATE TABLE Pagamenti (

	email_utente VARCHAR(320), 
	codice_ordine CHAR(6),
	data_ora_pagamento DATETIME NOT NULL,
    importo DECIMAL (10, 2) NOT NULL CHECK (importo > 0), 
    
	FOREIGN KEY (email_utente, codice_ordine) REFERENCES Ordini (email_utente, codice_ordine) ON UPDATE CASCADE ON DELETE NO ACTION,
    PRIMARY KEY (email_utente, codice_ordine, data_ora_pagamento)
);

CREATE TABLE ProdottiOrdini (

	email_utente VARCHAR(320), 
	codice_ordine CHAR(6),
	codice_gioco CHAR(6),
    data_ora_creazione DATETIME,
	quantita INT UNSIGNED NOT NULL,
        
	FOREIGN KEY (email_utente, codice_ordine) REFERENCES Ordini (email_utente, codice_ordine) ON UPDATE CASCADE ON DELETE NO ACTION,
	FOREIGN KEY (codice_gioco) REFERENCES Giochi (codice_gioco) ON UPDATE CASCADE ON DELETE NO ACTION,
	PRIMARY KEY (email_utente, codice_ordine, codice_gioco, data_ora_creazione)
);

-- ----------------------------------------------------------------------------------------------------------------------

INSERT INTO Utenti VALUES 
("francescog@gmail.com", "Explosion", "n}c5=SL4B7U@*]eh", "2022-04-26", TRUE, 420.69, "bla... bla... bla..."),
("angelon@gmail.com", "NGLX", "E]pgfP[48u6&wf$z", "2022-04-26", TRUE, 69.42, "bla... bla... bla..."),
("somaxd@gmail.com", "somarello", "wZ?Yg<7f;5B3>Ff{", CURDATE(), FALSE, 0, "bla... bla... bla..."),
("boxxerello@gmail.com", "Boxxo", "~r~c*<\D8q$K5FWu", CURDATE(), FALSE, 92.40, "bla... bla... bla..."),
("mickymouse@gmail.com", "pippo", "zZpippZz", CURDATE(), FALSE, 7.90, "bla... bla... bla..."),
("giampix@gmail.com", "giampix", "password123", CURDATE(), FALSE, 0.90, "bla... bla... bla..."),
("andreachad@outlook.it", "Zindre", "ML8uD+A)'d(y9]*", "2022-04-30", FALSE, 90, "bla... bla... bla..."),
("pepperimborsototale@hotmail.it", "Peppe", "T?vb'[E=5/GYk)n(", "2022-05-04", FALSE, 18, "bla... bla... bla..."),
("hicigog536@topyte.com", "Hici", "ML8uD+A)'d(y9]*", "2022-05-05", FALSE, 90, "bla... bla... bla..."),
("trttjrbxcmq@candassociates.com", "Trttj", ":_~&/3#ZC[{vRgVL", "2022-05-13", FALSE, 90, "bla... bla... bla..."),
("a67b166dc6@catdogmail.live", "Lay", "`eG7j;CA2c}!pHd/", "2022-05-30", FALSE, 90, "bla... bla... bla..."),
("petakim355@funboxcn.com", "PetaKim", "eQAb\r}by85;3TfP", "2022-06-01", FALSE, 90, "bla... bla... bla..."),
("041fcf40b2@catdogmail.live", "____DKHJFDOI____", "ev5qL.v+k9dCwp[", "2022-09-12", FALSE, 90, "bla... bla... bla..."),
("7e097189dc@catdogmail.live", "__BOT__", "QY~9G/XLRp{~Y6B{", CURDATE(), FALSE, 90, "bla... bla... bla...");

INSERT INTO Generi VALUES
("Arcade"),
("Avventura"),
("Azione"),
("FPS"),
("Giocatore singolo"),
("Picchiaduro"),
("RPG"),
("Strategia"),
("VR"),
("Action RPG"),
("Battle Royale"),
("DVD Game"),
("Enigma online"),
("FPS arena"),
("Gacha game"),
("Videogioco gestionale"),
("MMORPG"),
("MOBA"),
("Open world"),
("Quiz"),
("Videogioco rompicapo"),
("Videogioo di guida"),
("Simulatore di vita"),
("Videogioco stealth"),
("Videogioco sportivo"),
("Videogioco di sopravvivenza"),
("Survival"),
("Survival horror"),
("Roguelike");

INSERT INTO Giochi VALUES
(
	"Z38JDu",
	"Tekken 7", 
	"Scopri l'epica conclusione della lunga faida della famiglia Mishima e le ragioni che si celano dietro ogni episodio della loro storia. Sfruttando l'Unreal Engine 4, il leggendario gioco di combattimenti torna con stupefacenti battaglie cinematiche narrative e intensi duelli da vivere con amici e rivali.
	Amore, vendetta, orgoglio. Ognuno ha una ragione per combattere. I valori sono ciò che ci danno una ragione di vivere e ci rendono umani, indipendentemente dalle nostre debolezze o punti di forza. Non ci sono motivazioni sbagliate, solo la strada che decidiamo di percorrere.
	Amplia le tue possibilità di combattente acquistando il Season Pass di TEKKEN 7 separatamente e ottieni l'accesso a incredibili contenuti aggiuntivi.",
	"https://www.youtube.com/watch?v=kKLCwDg2JLA", 
	"2017-06-01", 
    "https://s1.gaming-cdn.com/images/products/1515/616x353/tekken-7-pc-gioco-steam-cover.jpg", 
	5.49, 
	50, 
	8
),
("uUfaYA", "Mortal Kombat XL", "", "", "", "", "", 50, 4),
("1tW4aB", "Sniper Elite 5", "", "", "", "", "", 50, 4),
("zo2ft8", "Street Fighter V", "", "", "", "", "", 50, 4),
("OkIufY", "Battlefield 2042", "", "", "", "", "", 50, 4),
("FNx9td", "Dying Light 2 Stay Human", "", "", "", "", "", 50, 4),
("6PiPPb", "Hitman 3", "", "", "", "", "", 50, 4),
("ACi702", "Elden Ring", "", "", "", "", "", 50, 4),
("oTwPW4", "God of War", "", "", "", "", "", 50, 4),
("fBqTqo", "Postal 4: No Regerts", "", "", "", "", "", 50, 4),
("nOWiwe", "Forgive me Father", "", "", "", "", "", 50, 4),
("hUnUBt", "Call of Duty: Vanguard Ultimate Edition", "", "", "", "", "", 50, 4),
("5ywyXE", "Cyberpunk 2077", "", "", "", "", "", 50, 4),
("Hg1Ssx", "MotoGP 22", "", "", "", "", "", 50, 4),
("KKzHol", "Football Manager 2022", "", "", "", "", "", 50, 4),
("81mzZG", "Terraformers", "", "", "", "", "", 50, 4),
("bgvLci", "WWE 2K22", "", "", "", "", "", 50, 4),
("umPIPT", "WWE 2K22 Deluxe Edition", "", "", "", "", "", 50, 4),
("3cssyj", "For Honor", "", "", "", "", "", 50, 4),
("yHrgCW", "Batman: Arkham Origins", "", "", "", "", "", 50, 4),
("oYxZfy", "Elex II", "", "", "", "", "", 50, 4),
("GrOoDB", "Age of Empires IV", "", "", "", "", "", 50, 4),
("6jGIUW", "Motorcycle Mechanic Simulator 2021", "", "", "", "", "", 50, 4),
("Q6HnRX", "Wanderer", "", "", "", "", "", 50, 4),
("oSzZp3", "Minecraft Java Edition", "", "", "", "", "", 50, 4),
("aKFt3e", "Resident Evil 7 Biohazard", "", "", "", "", "", 50, 4);