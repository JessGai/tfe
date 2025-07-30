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
Postgre  
lancer Ubuntu, le postgre en ligne  
  
dans application.properties:  
si je veux creer des table: spring.jpa.hibernate.ddl-auto=create  
si je veux rien faire: spring.jpa.hibernate.ddl-auto=update
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

## Connection a stage_desc
GET: http://localhost:8081/api/stagedesc?page=0&value=20  
GET: http://localhost:8081/api/stagedesc/id  
POST: http://localhost:8081/api/stagedesc  
{  
"titre": "Stage de Robotnic",  
"theme": "science",  
"description": "Rejoint le professeur Robotnic",  
"ageMin": 6,  
"ageMax": 8  
}  

## Connection a stage_inst
GET: http://localhost:8081/api/stageinst  
GET: http://localhost:8081/api/stageinst/id  
POST: http://localhost:8081/api/stageinst  
{  
"prix": 120,  
"dateDebut": "2025-07-15",  
"nbrParticipant": 20,  
"nbrInscrit": 5,  
"statut": true,  
"idStageDesc": 1  
}  
PUT: http://localhost:8081/api/stageinst/id  
DELETE: http://localhost:8081/api/stageinst/id  
GET: http://localhost:8081/api/stageinst/byTheme/{theme}  
Get: http://localhost:8081/api/stageinst/cards  

##Connection a transaction
POST: http://localhost:8081/api/transactions
{
"idTransaction": 2,
"montant": 0.0,
"dateTransaction": null,
"statut": "EN_ATTENTE",
"stripeSessionId": null,
"emailPayeur": null,
"idParent": 0,
"montantFinal": 0.0,
"tauxReduction": 0.0,
"dateCreation": null,
"lastUpdate": null
}