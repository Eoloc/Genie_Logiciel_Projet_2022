CREATE TABLE Variable (
	prixDemiHeure decimal(10,2) NOT NULL,
	prixDemiHeureSurtaxe decimal(10,2) NOT NULL,
	quantiteBorne integer NOT NULL
);

INSERT INTO Variable VALUES (20.00, 45.00, 20);

CREATE TABLE Vehicule (
	immatriculation varchar(20) PRIMARY KEY,
	marque varchar(50) NOT NULL,
	modele varchar(50) NOT NULL,
	couleur varchar(50) NOT NULL
);

INSERT INTO Vehicule VALUES ('AA123AA', 'Mercedes Benz', 'CLA', 'Noir');
INSERT INTO Vehicule VALUES ('BB456BB', 'Nissan', 'Micra', 'Vert');

CREATE TABLE Compte (
	idCompte serial PRIMARY KEY,
	email varchar(255) UNIQUE NOT NULL,
	mdp varchar(255) UNIQUE NOT NULL,
	nom varchar(50) NOT NULL,
	prenom varchar(50) NOT NULL,
	age integer NOT NULL,
	estClient bool NOT NULL,
	estGerant bool NOT NULL,
	estAdministrateur bool NOT NULL,
	immatriculation varchar(20),
	FOREIGN KEY (immatriculation) REFERENCES Vehicule (immatriculation)
);

INSERT INTO Compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur, immatriculation) VALUES ('alexandre@gmail.com', '123', 'Sousa', 'Alexandre', 21, TRUE, FALSE, FALSE, 'AA123AA');
INSERT INTO Compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur, immatriculation) VALUES ('jean@gmail.com', '456', 'Mathieu', 'Jean', 21, TRUE, FALSE, FALSE, NULL);
INSERT INTO Compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur, immatriculation) VALUES ('admin@gmail.com', '123456', 'Drommer', 'Fabien', 21, FALSE, FALSE, TRUE, NULL);
INSERT INTO Compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur, immatriculation) VALUES ('gerant@gmail.com', '1', 'Zarrad', 'Youssef', 21, FALSE, TRUE, FALSE, NULL);

CREATE TABLE AssociationTemporaire (
	idCompte serial,
	immatriculation varchar(20),
	FOREIGN KEY (idCompte) REFERENCES Compte (idCompte),
	FOREIGN KEY (immatriculation) REFERENCES Vehicule (immatriculation),
	PRIMARY KEY(idCompte, immatriculation)
);

INSERT INTO AssociationTemporaire VALUES (2, 'BB456BB');

CREATE TABLE Borne (
	idBorne serial PRIMARY KEY,
	etatBorne varchar(50) NOT NULL
);

INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('Disponible');
INSERT INTO Borne (etatBorne) VALUES ('En panne');
INSERT INTO Borne (etatBorne) VALUES ('En panne');
INSERT INTO Borne (etatBorne) VALUES ('En panne');


CREATE TABLE Reservation (
	idReservation serial PRIMARY KEY,
	dateDebut date NOT NULL,
	dateFin date NOT NULL,
	etatReservation varchar(50) NOT NULL,
	prixTotal decimal(10,2) NOT NULL,
	idCompte serial NOT NULL,
	idBorne serial NOT NULL,
	estPermanente bool NOT NULL,
	FOREIGN KEY (idCompte) REFERENCES Compte (idCompte),
	FOREIGN KEY (idBorne) REFERENCES Borne (idBorne)
);

CREATE TABLE HistoriqueDepense (
	idCompte serial,
	idReservation serial,
	montant decimal,
	FOREIGN KEY (idCompte) REFERENCES Compte (idCompte),
	FOREIGN KEY (idReservation) REFERENCES Reservation (idReservation),
	PRIMARY KEY(idCompte, idReservation)
);