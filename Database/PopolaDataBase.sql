USE Hektiks;

INSERT INTO Utenti VALUES 
("francescoadmin@gmail.com", "Francesco", "Granozio", "Explosion", sha1("Admin.123"), "2022-04-26", TRUE, 1299.45, "/Explosion/profile_pic.png"),
("francescouser@gmail.com", "Francesco", "Granozio", "//AFG", sha1("User.123"), "2022-04-26", FALSE, 1299.45, NULL),
("angeloadmin@gmail.com", "Angelo", "Nazzaro",  "NGLX", sha1("Admin.123"), "2022-04-26", TRUE, 1069.42, "/NGLX/profile_pic.jpg"),
("angelouser@gmail.com", "Angelo", "Nazzaro",  "Boxxo's Lover", sha1("User.123"), "2022-04-26", FALSE, 1069.42, NULL),
("somaxd@gmail.com", "Carmine", "Nardo", "somarello", sha1("soma.8dnddòj32ìr_°éç"), CURDATE(), FALSE, 0, NULL),
("boxxerello@gmail.com", "Francesco", "Bosso", "Boxxo", sha1("~r~c*<\D8q$K5FWu"), CURDATE(), FALSE, 92.40, NULL),
("mickymouse@gmail.com", "Giuseppe", "La Voglia", "pippo", sha1("zZpippZz"), CURDATE(), FALSE, 7.90, NULL),
("giampix@gmail.com", "Giampaolo", "Joestar", "giampix", sha1("password123"), CURDATE(), FALSE, 0.90, NULL),
("andreachad@outlook.it", "Andrea Chad", "Vitolo", "Zindre", sha1("ML8uD+A)'d(y9]*"), "2022-04-30", FALSE, 90, NULL),
("pepperimborsototale@hotmail.it", "Giuseppe", "D'Ambrosio", "Peppe", sha1("T?vb'[E=5/GYk)n("), "2022-05-04", FALSE, 18, NULL),
("hicigog536@topyte.com", "Ichigo", "Kurosaki", "Hici", sha1("ML8uD+A)'d(y9]*"), "2022-05-05", FALSE, 90, NULL),
("trttjrbxcmq@candassociates.com", "Trixy", "Pan", "Trttj", sha1(":_~&/3#ZC[{vRgVL"), "2022-05-13", FALSE, 90, NULL),
("a67b166dc6@catdogmail.live", "Quentin", "Tarantino", "Lay", sha1("`eG7j;CA2c}!pHd/"), "2022-05-30", FALSE, 90, NULL),
("petakim355@funboxcn.com", "Gerardo", "Della Sala", "PetaKim", sha1("eQAb\r}by85;3TfP"), "2022-06-01", FALSE, 90, NULL),
("041fcf40b2@catdogmail.live", "John", "Travolta", "____DKHJFDOI____", sha1("ev5qL.v+k9dCwp["), "2022-09-12", FALSE, 90, NULL),
("7e097189dc@catdogmail.live", "Marco", "Antonio", "__BOT__", sha1("QY~9G/XLRp{~Y6B{"), CURDATE(), FALSE, 90, NULL);

INSERT INTO Generi VALUES
("Arcade"),
("Avventura"),
("Azione"),
("FPS"),
("Singleplayer"),
("Multiplayer"),
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
("Gestionale"),
("MMORPG"),
("MOBA"),
("Open world"),
("Quiz"),
("Rompicapo"),
("Videogioo di guida"),
("Simulatore di vita"),
("Stealth"),
("Sportivo"),
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
	"https://www.youtube.com/embed/kKLCwDg2JLA", 
	"2017-06-01", 
    "https://s1.gaming-cdn.com/images/products/1515/616x353/tekken-7-pc-gioco-steam-cover.jpg", 
	5.49, 
	50, 
	8,
    20
),
(
	"uUfaYA", 
    "Mortal Kombat XL", 
    "Avanti il prossimo! Assapora la nuova generazione della saga di combattimento numero 1.
	Mortal Kombat X combina animazioni cinematografiche uniche a un nuovo stile di gioco. Per la prima volta, potrai scegliere tra diverse varianti di ogni personaggio, che influenzeranno strategie e stili di combattimento.
	Il leggendario Goro viene offerto come bonus per i preordini.", 
    "https://www.youtube.com/embed/jSi2LDkyKmI", 
    "2015-04-14", 
    "https://s3.gaming-cdn.com/images/products/2420/616x353/mortal-kombat-xl-xl-pc-gioco-steam-cover.jpg", 
    3.90, 
    70, 
    1,
    5
),
(
	"1tW4aB", 
    "Sniper Elite 5", 
    "Il nuovo capitolo della pluripremiata serie, Sniper Elite 5, offre cecchinaggio senza precedenti, combattimenti tattici in terza persona e una telecamera uccisione migliorata. Fatti strada su mappe più coinvolgenti che mai, con molti luoghi del mondo reale catturati con dettagli sorprendenti e un sistema di attraversamento migliorato, che consente di esplorare ancora di più.
	Francia, 1944 – Chiamato a svolgere un'operazione segreta dei Ranger statunitensi per indebolire le fortificazioni del Vallo Atlantico lungo la costa della Bretagna, il tiratore scelto Karl Fairburne entra in contatto con la Resistenza francese. Ben presto si viene a conoscenza di un progetto nazista segreto che minaccia di porre fine alla guerra prima che gli Alleati possano invadere l'Europa: l'operazione Kraken.", 
    "https://www.youtube.com/embed/h5HLwAo5gnw", 
    "2022-05-26", 
    "https://s3.gaming-cdn.com/images/products/9578/616x353/sniper-elite-5-pc-gioco-steam-europe-cover.jpg", 
    37.98, 
    0, 
    0,
    60
),
(
	"zo2ft8", 
    "Street Fighter V",
    "Vivi l'emozione di un combattimento testa a testa in Street Fighter V! Scegli tra 16 personaggi iconici, ciascuno con la propria storia personale e sfide di allenamento uniche, poi lotta contro gli amici online e offline grazie a un'ampia varietà di opzioni di combattimento.
	Guadagna Fight Money nei match classifica, gioca per puro svago in quelli casuali oppure invita gli amici in una sala dei combattimenti per stabilire chi è il miglior lottatore! Grazie alla compatibilità cross-play, i giocatori su PlayStation 4 possono gareggiare contro quelli su Steam!
	Questa versione di Street Fighter V è detta “Arcade Edition” e include l'Arcade Mode, la Team Battle Mode e l'Extra Battle Mode, giocabile anche online, in cui è possibile guadagnare ricompense, punti esperienza e Fight Money da usare per acquistare personaggi aggiuntivi, costumi, livelli e molto altro!
	Scarica oggi stesso e GRATUITAMENTE il contenuto animato dal titolo “A Shadow Falls""! Il folle M. Bison riesce a mettere in orbita sette lune nere ottenendo un potere inimmaginabile e piombando la Terra nell'oscurità.
	Street Fighter V: Champion Edition è il pacchetto per eccellenza e include tutti i contenuti (tranne i costumi Fighting Chance, quelli delle collaborazioni speciali e i DLC di Capcom Pro Tour) sia della versione originale che di Street Fighter V: Arcade Edition. Inoltre, contiene tutti i personaggi, scenari e costumi rilasciati dopo l'Arcade Edition ossia 40 personaggi, 34 livelli e oltre 200 costumi!", 
    "https://www.youtube.com/embed/0nFd7Iylj5A", 
    "2016-02-16", 
    "https://s3.gaming-cdn.com/images/products/671/616x353/street-fighter-v-pc-gioco-steam-cover.jpg", 
    4.54, 
    150, 
    12,
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
    "https://www.youtube.com/embed/ASzOzrB-a9E", 
    "2021-11-19", 
    "https://s1.gaming-cdn.com/images/products/6690/616x353/battlefield-2042-pc-gioco-origin-cover.jpg", 
    21.39, 
    25, 
    6,
    25
),
(	"oSzZp3", 
	"Minecraft Java Edition", 
	"Minecraft per PC è il videogioco più venduto di tutti i tempi. Questo da solo dovrebbe essere sufficiente per farlo comprare, 
	ma ecco qualche informazione in più sul perché dovresti iniziare a giocare ora. 
	È un gioco sandbox in cui i giocatori devono scavare, costruire e creare il loro mondo ideale", 
	"https://www.youtube.com/embed/MmB9b5njVbA", 
	"2011-11-19", 
	"https://www.instant-gaming.com/images/products/442/380x218/442.jpg", 
	19.99, 
	50, 
	4,
    15
),
(
	"FNx9td", 
	"Hitman 3", 
	"Hitman 3 per PC è l'ottavo della serie Hitman e l'episodio finale della trilogia di World of Assassination, 
	composta da Hitman, Hitman 2 e ora Hitman 3. La trilogia segue un arco narrativo che si conclude in questo gioco 
	giocando la modalità giocatore singolo. In questo gioco, il sicario geneticamente modificato, l'Agente 47, 
	ha il compito di trovare ed eliminare i leader della cabala segreta, la Provvidenza, che controlla tutti gli affari mondiali.", 
	"https://www.youtube.com/embed/Z29ORu6_p34", 
	"2022-01-20", 
	"https://s3.gaming-cdn.com/images/products/6851/616x353/hitman-3-pc-gioco-epic-games-europe-cover.jpg?v=1644802754", 
	60.00, 
	50, 
	13,
    30
),
(
	"5ywyXE", 
	"Cyberpunk 2077", 
	"Cyberpunk 2077 per PC è un gioco sparatutto in prima persona, ma con una differenza. 
    É ambientato in uno stato distopico della California, in cui le regole della nazione e dello stato non si applicano più. Invece, 
     giocando come un mercenario di nome V, il giocatore deve muoversi per la città, 
    raggiungere i propri obiettivi e nel frattempo combattere i nemici.", 
	"https://www.youtube.com/embed/JFf8I_8Ubv4", 
	"2020-12-10", 
	"https://s1.gaming-cdn.com/images/products/840/616x353/cyberpunk-2077-pc-gioco-gog-com-cover.jpg?v=1650474174", 
	59.00, 
	89, 
	47,
    10
),
(
	"FeS7d3", 
	"Batman: Arkham Origins", 
	"Batman: Arkham Origins is the next installment in the blockbuster Batman: Arkham videogame franchise. 
    Developed by WB Games Montréal, the game features an expanded Gotham City and introduces an original prequel storyline set 
    several years before the events of Batman: Arkham Asylum and Batman: Arkham City, 
    the first two critically acclaimed games of the franchise. Taking place before the rise of Gotham City’s most dangerous 
    criminals, the game showcases a young and unrefined Batman as he faces a defining moment in his early career as a 
    crime fighter that sets his path to becoming the Dark Knight.", 
	"https://www.youtube.com/embed/9pnK8akbd2M", 
	"2013-10-25", 
	"https://s2.gaming-cdn.com/images/products/184/616x353/batman-arkham-origins-pc-gioco-steam-cover.jpg?v=1649085257", 
	20.00, 
	45, 
	39,
    5
),
(
	"3cssyj", 
	"Tom Clancy's Rainbow Six Siege - Europe", 
	"Rainbow Six Siege per PC è un gioco sparatutto tattico che si fa online. 
     C'è una forte enfasi sulla distruzione ambientale e sulla cooperazione tra i giocatori, con ogni giocatore che assume 
     il controllo di un personaggio da difendere o attaccare in ogni diverso scenario. 
     Tra le attività da svolgere: il salvataggio di ostaggi, il disinnesco di bombe e la cattura o la difesa di un punto di controllo.", 
	"https://www.youtube.com/embed/weqnfymIU2w", 
	"2015-11-26", 
	"https://s2.gaming-cdn.com/images/products/406/616x353/tom-clancy-s-rainbow-six-siege-pc-gioco-ubisoft-connect-europe-cover.jpg?v=1649421231", 
	20.00, 
	70, 
	150,
    15
),
(
	"umP3PT", 
	"Tiny Tina's Wonderlands: Chaotic Great Edition - Europe", 
	"Vivi un'avventura epica piena di armi uniche, stravaganti e prodigiose! Tuffati nel vortice di proiettili, 
     lame e magie del caotico mondo fantasy partorito dall'imprevedibile mente di Tiny Tina.
     Crea il tuo eroe multiclasse, affronta mostri bizzarri e saccheggia dungeon pieni di bottini. Spara, 
     fendi, lancia incantesimi: la tua missione è fermare il malvagio Signore dei draghi. 
     Chiunque può unirsi al gruppo e partire all'avventura. La regola è una sola: dimostrare di essere caotici veri!", 
	"https://www.youtube.com/embed/Ikqs6B4ozM0", 
	"2022-03-25", 
	"https://s1.gaming-cdn.com/images/products/10386/616x353/tiny-tina-s-wonderlands-chaotic-great-edition-chaotic-great-edition-pc-gioco-epic-games-europe-cover.jpg?v=1648139448", 
	80.00, 
	70, 
	150,
    40
);

INSERT INTO Giochi (codice_gioco, titolo, descrizione, trailer, data_uscita, copertina, prezzo, quantita_disponibile, numero_vendite) VALUES
(
	"Q6HnRX", 
	"Counter-Strike: Global Offensive", 
	"Counter-Strike: Global Offensive (CS:GO) amplia il gameplay d'azione a squadre presentato per la prima volta 19 anni fa.
     CS:GO include nuove mappe, personaggi, armi e modalità di gioco e offre versioni aggiornate del classico contenuto di Counter-Strike (de_dust2, ecc.).", 
	"https://www.youtube.com/embed/edYCtaNueQY", 
	"2012-08-21", 
	"https://s1.gaming-cdn.com/images/products/9459/616x353/counter-strike-global-offensive-pc-mac-gioco-steam-cover.jpg?v=1644770250", 
	0.00, 
	200, 
	134
),
(
	"8cxsej", 
	"Assassin's Creed Valhalla: L'alba del Ragnarok - Europe", 
	"In Assassin's Creed Valhalla: L'alba del Ragnarok, l'espansione più ambiziosa nella storia della serie, 
    Eivor affronterà il proprio destino nei panni di Odino, il dio norreno della guerra e della saggezza. 
    Scatena i tuoi nuovi poteri divini in una disperata missione che ti porterà ad attraversare un universo mozzafiato.", 
	"https://www.youtube.com/embed/mlxNLhZ-mG8", 
	"2022-03-10", 
	"https://s1.gaming-cdn.com/images/products/9891/616x353/assassin-s-creed-valhalla-l-alba-del-ragnarok-pc-gioco-ubisoft-connect-europe-cover.jpg?v=1649333093", 
	40.00, 
	30, 
	15
),
(
	"81mzZG", 
	"Valorant", 
	"VALORANT è un FPS competitivo 5 vs 5 basato sui personaggi di Riot. Disponibile ovunque. 
    Padroneggia decine di armi e abilità diverse, e sfoggia le tue capacità", 
	"https://www.youtube.com/embed/e_E9W2vsRbQ", 
	"2020-06-02", 
	"https://s2.gaming-cdn.com/images/products/6568/616x353/valorant-pc-gioco-steam-cover.jpg?v=1646313124", 
	 0.00, 
	 80, 
	 120
),
(	
	"oTwPW4", 
	"God of War", 
	"Lasciatosi alle spalle la sua sete di vendetta verso gli dèi dell'Olimpo, Kratos ora vive nella terra delle divinità e dei mostri norreni. 
	È in questo mondo ostile e spietato che dovrà combattere per la sopravvivenza e insegnare a suo figlio a fare lo stesso.", 
	"https://www.youtube.com/embed/RQK_40a0XUY", 
	"2022-01-14", 
	"https://www.instant-gaming.com/images/products/7325/380x218/7325.jpg", 
	50.00, 
	50, 
	200
),
(	
	"ACi702", 
	"Elden Ring", 
	"Elden Ring per PC è un gioco di ruolo d'azione (ARPG) scritto dalle superstar George RR Martin 
	(l'autore della serie di libri Le Cronache del Ghiaccio e del Fuoco che ha dato origine alla serie televisiva Game of Thrones) e 
	Hidetaka Miyazake (famoso per molti popolari videogiochi: dalla serie Souls, a Bloodborne, a Sekiro).", 
	"https://www.youtube.com/embed/AKXiKBnzpBQ", 
	"2022-02-25", 
	"https://www.instant-gaming.com/images/products/4824/380x218/4824.jpg", 
	60.00, 
	75, 
	130
);

INSERT INTO Giochi_Genere VALUES 
("oSzZp3", "Multiplayer"),
("oSzZp3", "Action RPG"),
("oSzZp3", "MOBA"),
("oSzZp3", "Gestionale"),
("ACi702", "Azione"),
("ACi702", "Action RPG"),
("ACi702", "Singleplayer"),
("oTwPW4", "Singleplayer"),
("oTwPW4", "Action RPG"),
("oTwPW4", "Avventura"),
("oTwPW4", "Azione"),
("Z38JDu", "Azione"),
("Z38JDu", "Picchiaduro"),
("Z38JDu", "Arcade"),
("Z38JDu", "Singleplayer"),
("uUfaYA", "Azione"),
("uUfaYA", "Picchiaduro"),
("uUfaYA", "Singleplayer"),
("1tW4aB", "Azione"),
("1tW4aB", "Avventura"),
("1tW4aB", "Singleplayer"),
("zo2ft8", "Picchiaduro"),
("zo2ft8", "Azione"),
("zo2ft8", "Arcade"),
("zo2ft8", "Singleplayer"),
("OkIufY", "Azione"),
("OkIufY", "Avventura"),
("OkIufY", "FPS"),
("8cxsej", "Azione"),
("8cxsej", "Avventura"),
("umP3PT", "Azione"),
("umP3PT", "Avventura"),
("umP3PT", "Action RPG"),
("umP3PT", "FPS"),
("3cssyj", "Azione"),
("3cssyj", "FPS"),
("81mzZG", "Azione"),
("81mzZG", "FPS"),
("81mzZG", "Multiplayer"),
("Q6HnRX", "Azione"),
("Q6HnRX", "FPS"),
("Q6HnRX", "Multiplayer"),
("FeS7d3", "Azione"),
("FeS7d3", "Picchiaduro"),
("FeS7d3", "Arcade"),
("FeS7d3", "Singleplayer"),
("5ywyXE", "Azione"),
("5ywyXE", "FPS"),
("5ywyXE", "Singleplayer"),
("FNx9td", "Azione"),
("FNx9td", "Avventura"), 
("FNx9td", "Singleplayer");

INSERT INTO GiftCards VALUES 
("y0g63d", NULL, 30.50, NOW(), NULL),
("iIDRLE", NULL, 10.00, NOW(), NULL),
("g6dPCu", NULL, 7.50, NOW(), NULL),
("VlMGDc", NULL, 125.00, NOW(), NULL),
("c1AihV", NULL, 50.00, NOW(), NULL),
("Wj1sVv", NULL, 75.00, NOW(), NULL),
("jbFmXq", NULL, 5.00, NOW(), NULL),
("nIuLQH", NULL, 10.00, NOW(), NULL),
("vy37bg", NULL, 20.00, NOW(), NULL),
("wyc37d", NULL, 200.00, NOW(), NULL),
("XlZD5x", NULL, 1.00, NOW(), NULL),
("vCUyqj", NULL, 2.00, NOW(), NULL),
("CYBMsB", NULL, 125.00, NOW(), NULL),
("2lj16n", NULL, 30.00, NOW(), NULL),
("J2gSsr", NULL, 20.00, NOW(), NULL);


