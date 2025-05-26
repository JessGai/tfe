# Backend KidsCamp

Ce projet est le backend de l’application KidsCamp.

---
## info du projet
Java 21
SpringBoot 3.4.5
Maven

## lancement du projet:
run TfeBackendApplication
(si la sécurité pose problème il faut @SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) et commenter la classe SecurityConfig.java)

## DB
Mysql
Ajouter login/mot de passe dans Edit Configuration, variable environnement

## Commandes Git utiles
synchronisation avec le main:
git pull
ajout des fichiers modifiés:
git add .
Faire un commit avec message
git commit -m "Description courte de la modification"
Envoyer les commits vers GitHub
git push origin main

### Synchroniser avec GitHub

git pull origin main

## Json pour les post
### post stagedesc http://localhost:8080/api/stagedesc
    {
        "titre": "Stage de robotniv",
        "theme": "science",
        "description": "stagr",
        "ageMin": 6,
        "ageMax": 8

    }
### post stageInst http://localhost:8080/api/stageinst
{
"prix": 120,
"dateDebut": "2025-07-15",
"nbrParticipant": 20,
"nbrInscrit": 5,
"statut": true,
"idStageDesc": 1
}