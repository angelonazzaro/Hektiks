DROP DATABASE IF EXISTS Hektiks;
CREATE DATABASE Hektiks;
USE Hektiks;
SET sql_mode = "";
SET GLOBAL sql_mode = "";

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
("VR");
/*("Action RPG"),
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
("Picchiaduro"),
("Roguelike");*/

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
(
	"uUfaYA", 
    "Mortal Kombat XL", 
    "Avanti il prossimo! Assapora la nuova generazione della saga di combattimento numero 1.
	Mortal Kombat X combina animazioni cinematografiche uniche a un nuovo stile di gioco. Per la prima volta, potrai scegliere tra diverse varianti di ogni personaggio, che influenzeranno strategie e stili di combattimento.
	Il leggendario Goro viene offerto come bonus per i preordini.", 
    "https://www.youtube.com/watch?v=jSi2LDkyKmI", 
    "2015-04-14", 
    "https://s3.gaming-cdn.com/images/products/2420/616x353/mortal-kombat-xl-xl-pc-gioco-steam-cover.jpg", 
    3.90, 
    70, 
    1
),
(
	"1tW4aB", 
    "Sniper Elite 5", 
    "Il nuovo capitolo della pluripremiata serie, Sniper Elite 5, offre cecchinaggio senza precedenti, combattimenti tattici in terza persona e una telecamera uccisione migliorata. Fatti strada su mappe più coinvolgenti che mai, con molti luoghi del mondo reale catturati con dettagli sorprendenti e un sistema di attraversamento migliorato, che consente di esplorare ancora di più.
	Francia, 1944 – Chiamato a svolgere un'operazione segreta dei Ranger statunitensi per indebolire le fortificazioni del Vallo Atlantico lungo la costa della Bretagna, il tiratore scelto Karl Fairburne entra in contatto con la Resistenza francese. Ben presto si viene a conoscenza di un progetto nazista segreto che minaccia di porre fine alla guerra prima che gli Alleati possano invadere l'Europa: l'operazione Kraken.", 
    "https://www.youtube.com/watch?v=h5HLwAo5gnw", 
    "2022-05-26", 
    "https://s3.gaming-cdn.com/images/products/9578/616x353/sniper-elite-5-pc-gioco-steam-europe-cover.jpg", 
    37.98, 
    0, 
    0
),
(
	"zo2ft8", 
    "Street Fighter V",
    "Vivi l'emozione di un combattimento testa a testa in Street Fighter V! Scegli tra 16 personaggi iconici, ciascuno con la propria storia personale e sfide di allenamento uniche, poi lotta contro gli amici online e offline grazie a un'ampia varietà di opzioni di combattimento.
	Guadagna Fight Money nei match classifica, gioca per puro svago in quelli casuali oppure invita gli amici in una sala dei combattimenti per stabilire chi è il miglior lottatore! Grazie alla compatibilità cross-play, i giocatori su PlayStation 4 possono gareggiare contro quelli su Steam!
	Questa versione di Street Fighter V è detta “Arcade Edition” e include l'Arcade Mode, la Team Battle Mode e l'Extra Battle Mode, giocabile anche online, in cui è possibile guadagnare ricompense, punti esperienza e Fight Money da usare per acquistare personaggi aggiuntivi, costumi, livelli e molto altro!
	Scarica oggi stesso e GRATUITAMENTE il contenuto animato dal titolo “A Shadow Falls""! Il folle M. Bison riesce a mettere in orbita sette lune nere ottenendo un potere inimmaginabile e piombando la Terra nell'oscurità.
	Street Fighter V: Champion Edition è il pacchetto per eccellenza e include tutti i contenuti (tranne i costumi Fighting Chance, quelli delle collaborazioni speciali e i DLC di Capcom Pro Tour) sia della versione originale che di Street Fighter V: Arcade Edition. Inoltre, contiene tutti i personaggi, scenari e costumi rilasciati dopo l'Arcade Edition ossia 40 personaggi, 34 livelli e oltre 200 costumi!", 
    "https://www.youtube.com/watch?v=0nFd7Iylj5A", 
    "2016-02-16", 
    "https://s3.gaming-cdn.com/images/products/671/616x353/street-fighter-v-pc-gioco-steam-cover.jpg", 
    4.54, 
    150, 
    12
),
(
	"OkIufY", 
    "Battlefield 2042", 
    "Battlefield 2042 per PC è un videogioco sparatutto in prima persona con un forte focus multiplayer. Il gioco, il dodicesimo della serie (e che vanta anche oltre quaranta pacchetti di espansione in tutto il franchise), è ambientato in un futuro abbastanza prossimo (come evidenziato dal nome), il che significa che ci sono un sacco di armi e gadget high-tech e futuristici – come droni schierabili, torrette e cani robot pronti ad aspettarti!
	Informazioni sul gioco
	Come accennato in precedenza, il clima del mondo è scivolato in condizioni estreme, costringendo un gran numero di persone a fuggire dalle loro case ormai insostenibili. Questi rifugiati climatici sono chiamati No-Pats, nel senso che sono apolidi e non hanno \"paese\". La crisi climatica è drammaticamente peggiorata, causando il collasso dell'UE e aumentando le tensioni tra Stati Uniti e Russia fino alla guerra.
	Gli eventi sulla Terra arrivano al culmine quando si verifica un evento della Sindrome di Kessler* nel 2040, che mette fuori gioco il settanta percento dei satelliti per le comunicazioni internazionali. Già tesi, Stati Uniti e Russia sono sull'orlo della guerra.
	Secondo la scienza, la sindrome di Kessler si verichiferà con la presenza di una reazione a catena di collisioni all'interno della spessa cintura di spazzatura spaziale che sfreccia intorno al pianeta (detriti provenienti da missioni spaziali, persi o gettati via per qualsiasi motivo), influenzando quelle cose in orbita su cui facciamo affidamento lavoro quotidiano come i satelliti accuratamente posizionati che gestiscono i sistemi telefonici, le connessioni Internet, le comunicazioni radiofoniche e televisive.
	La guerra, infine, scoppia nel 2042, giusto in tempo perché salvare la situazione.
	Cosa c'è di nuovo e cosa rimane lo stesso?
	Il sistema Plus è nuovo e migliorato: i giocatori possono ora aggiornare le proprie armi al volo, mentre il sistema di classe è sostanzialmente lo stesso ma con una revisione massiccia tanto quanta necessaria. Le classi ora sono chiamate \"specializzazioni\" e puoi esplorare qualsiasi albero delle abilità, invece di essere limitato alla tua classe come prima. I nomi rimangono gli stessi, Assalto, Ingegnere, Medico, Ricognitore ecc, quindi se hai un preferito, puoi trovare rapidamente quello che stai cercando.
	Gli effetti meteorologici estremi hanno fatto il salto dal gioco precedente della serie e, dato che c'è un'emergenza climatica, non sarai sorpreso di apprendere che i tornado possono risucchiarti e farti passare dei momenti difficili prima di sputarti di nuovo - si spera! – mentre le tempeste di sabbia riducono notevolmente la tua visibilità. E questo può essere un problema nel bel mezzo del campo di battaglia.
	Invece della modalità giocatore singolo, ci sono tre modalità di gioco principali: All-out Warfare – che comprende: Breakthrough (in cui cerchi di eliminare i punti di controllo dell'altra squadra) e Conquest (in cui due squadre combattono per un singolo controllo punto). Hazard Zone è la zona cooperativa multiplayer e il posto dove stare! C'è il supporto per un massimo di 128 giocatori in due squadre di 64.
	Infine, nella colonna \"Cosa c'è di nuovo\", ci sono veicoli autonomi che i giocatori possono requisire quando e dove ne hanno bisogno. Se non ci sono auto a portata di mano, i giocatori possono anche richiedere che un veicolo venga paracadutato da loro: perché non pensare fuori dalla scatola e far cadere un carro armato su un cecchino nemico e rendere la vita un po' più facile per te stesso?
	Dov'è ambientato il gioco?
	Il gioco copre in modo abbastanza completo il mondo, con 7 mappe disponibili fin dall'inizio:
	Orbital – in cima al Sud America, ambientato nel Centro Spaziale della Guiana vicino a Kourou
	Hourglass – trovata nel vicino oriente: Doha, Qatar, sulla costa del Golfo Persico
	Kaleidoscope – Nel nord del Giappone si trova il distretto commerciale internazionale di Songdo in Corea del Sud
	Manifest: a metà strada tra l'India e l'Australia si trova l'isola di Pulau Brani, al largo della costa meridionale di Singapore
	Discarded – Alang, in alto sulla costa occidentale dell'India, le cui spiagge ospitano un immensità di navi abbandonate
	Breakaway – Ti porta nella Queen Maud Land nel Circolo Polare Artico, un territorio della Norvegia
	Revival – Forse visitato dai faraoni in passato, il Deserto Orientale, la parte più orientale del Sahara, dove confina con il Nilo, è un buon posto da consultare.", 
    "https://www.youtube.com/watch?v=ASzOzrB-a9E", 
    "2021-11-19", 
    "https://s1.gaming-cdn.com/images/products/6690/616x353/battlefield-2042-pc-gioco-origin-cover.jpg", 
    21.39, 
    25, 
    6
);
/*("FNx9td", "Dying Light 2 Stay Human", "", "", "", "", "", 50, 4),
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
("aKFt3e", "Resident Evil 7 Biohazard", "", "", "", "", "", 50, 4);*/