# Genie_Logiciel_Projet_2022

Le but de ce projet est de suivre et de gérer l'occupation d'un parc de bornes de recharge pour véhicules électriques et de permettre aux clients de trouver et de réserver les bornes disponibles. Les objectifs du projet sont de mettre en pratique ce que nous avons vu en cours.

## Mise en place de la base de données

Pour se connecter à la base de données PostGreSQL, il faut avoir un fichier de configuration au même niveau que Launcher.java :
bbd.ini. C'est-à-dire dans src/main/java/fr.ul.miage.Genie_Logiciel_Projet_2022/bdd.ini

Le contenu du fichier bdd.ini :

>bdd.ini
> 
>[database]\
>user=identifiant du propriétaire de la base de données\
>password=mot de passe du propriétaire de la base de données\
>port=port de PostGreSQL

> exemple :
> 
>[database]\
>user=root\
>password=root\
>port=5432

Le nom de la base de données doit obligatoirement être localhost

