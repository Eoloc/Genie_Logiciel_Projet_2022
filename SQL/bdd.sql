CREATE TABLE Variable (
	prixDemiHeure decimal(10,2) NOT NULL,
	prixDemiHeureSurtaxe decimal(10,2) NOT NULL,
	quantiteBorne integer NOT NULL
);

CREATE TABLE Compte (
	idCompte serial PRIMARY KEY,
	email varchar(255) UNIQUE NOT NULL,
	mdp varchar(255) UNIQUE NOT NULL,
	nom varchar(50) NOT NULL,
	prenom varchar(50) NOT NULL,
	age integer NOT NULL,
	estClient bool NOT NULL,
	estGerant bool NOT NULL,
	estAdministrateur bool NOT NULL
);

CREATE TABLE Vehicule (
	immatriculation varchar(20) PRIMARY KEY,
	marque varchar(50) NOT NULL,
	modele varchar(50) NOT NULL,
	couleur varchar(50) NOT NULL
);

CREATE TABLE VehiculeAssociation (
	immatriculation varchar(20),
	idcompte serial,
	statusAssociation varchar(50),
	PRIMARY KEY (immatriculation, idcompte),
	FOREIGN KEY (immatriculation) REFERENCES Vehicule (immatriculation),
	FOREIGN KEY (idCompte) REFERENCES Compte (idCompte)
);

CREATE TABLE Borne (
	idBorne serial PRIMARY KEY,
	etatBorne varchar(50) NOT NULL
);


CREATE TABLE Reservation (
	idReservation serial PRIMARY KEY,
	dateDebut timestamp NOT NULL,
	dateFin timestamp NOT NULL,
	etatReservation varchar(50) NOT NULL,
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

INSERT INTO Variable VALUES (20.00, 45.00, 20);

INSERT INTO Compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur) VALUES ('alexandre@gmail.com', '123', 'Sousa', 'Alexandre', 21, TRUE, FALSE, FALSE);
INSERT INTO Compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur) VALUES ('jean@gmail.com', '456', 'Mathieu', 'Jean', 21, TRUE, FALSE, FALSE);
INSERT INTO Compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur) VALUES ('admin@gmail.com', '123456', 'Drommer', 'Fabien', 21, FALSE, FALSE, TRUE);
INSERT INTO Compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur) VALUES ('gerant@gmail.com', '1', 'Zarrad', 'Youssef', 21, FALSE, TRUE, FALSE);

INSERT INTO Vehicule VALUES ('AA123AA', 'Mercedes Benz', 'CLA', 'Noir');
INSERT INTO Vehicule VALUES ('BB456BB', 'Nissan', 'Micra', 'Vert');

INSERT INTO VehiculeAssociation VALUES ('AA123AA', 1, 'Permanent');
INSERT INTO VehiculeAssociation VALUES ('BB456BB', 1, 'Permanent');
INSERT INTO VehiculeAssociation VALUES ('BB456BB', 2, 'Temporaire');

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

INSERT INTO Reservation (dateDebut, dateFin, etatReservation, idCompte, idBorne, estPermanente) VALUES ('2022-05-22 15:00:00', '2022-05-22 16:00:00', 'En attente', 1, 1, FALSE);