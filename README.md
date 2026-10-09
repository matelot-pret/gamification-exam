# Brio

Application web personnelle pour organiser la préparation des examens comme un jeu de rôle. Projet en cours de développement : l'analyse est terminée, le backend est en construction.

## L'idée

Une session d'examens devient une quête. Chaque matière est un boss, et chaque tâche de révision, calibrée pour durer 25 à 35 minutes, rapporte de l'expérience. Des jauges montrent en temps réel le niveau de préparation de chaque matière et de la session entière.

Le but : découper les objectifs en petites tâches concrètes et voir sa progression, au lieu de tout garder en tête.

## Règles métier (résumé)

* **Structure** : une session contient des matières (crédits, date d'évaluation), et chaque matière contient des tâches de théorie ou de pratique.
* **Expérience** : une tâche principale rapporte une expérience fixe, proportionnelle aux crédits de la matière. Les tâches secondaires rapportent un pourcentage du palier de niveau en cours.
* **Niveau** : calculé à partir de l'expérience totale, selon une courbe quadratique.
* **Jauges** : la progression d'une matière se mesure au nombre de tâches terminées. Une session est signalée comme déséquilibrée quand certaines matières sont très en retard sur d'autres.
* **États d'une tâche** : pas faite, en cours, terminée. Un minuteur Pomodoro de 25 minutes est proposé, sans être obligatoire.
* **Bienveillance** : l'application informe sans punir. Si beaucoup de tâches sont cochées en très peu de temps, un message le signale, sans retirer d'expérience.
* **Découpage assisté** : un modèle de langage peut proposer un découpage d'un cours en tâches, que l'utilisateur corrige et valide.

## Analyse

Avant d'écrire le code :

* règles métier rédigées (expérience, niveaux, jauges, états, bonus et malus) ;
* diagramme de classes UML, réalisé avec Visual Paradigm ;
* modèle relationnel, avec un héritage JPA en stratégie `JOINED` pour les deux types de tâches ;
* plan de construction en tranches verticales.

## Stack technique

* Java 21, Spring Boot 4.1, Spring Data JPA ;
* PostgreSQL ;
* `spring-security-crypto` pour le hachage des mots de passe avec BCrypt ;
* front prévu en React ou Vue, une fois l'API REST terminée.

Le code suit une architecture en couches : modèles, repositories, services, contrôleurs, DTO et exceptions. Les dépendances sont injectées par constructeur.

## Méthode de travail

L'application est construite **par tranches verticales** : chaque fonctionnalité traverse toutes les couches (entité, repository, DTO, service, contrôleur) avant de passer à la suivante. Chaque étape a sa propre branche et sa pull request (`feat/1-user_repository`, `feat/2-user_dto`, `feat/3-user_service`).

## Avancement

| Tranche | Contenu | État |
| ------- | ------- | ---- |
| 0 | Squelette Spring Boot connecté à PostgreSQL | Terminée |
| 1 | Utilisateur : entité, repository, DTO, service, contrôleur | En cours |
| 2 | Profil et paramètres | À faire |
| 3 | Session | À faire |
| 4 | Matière | À faire |
| 5 | Tâches principales et secondaires | À faire |
| 6 | Logique transverse : expérience, niveaux, jauges | À faire |
| 7 | Découpage assisté par un modèle de langage | À faire |
| — | Front | À faire |

## Lancer le projet

### Prérequis

* JDK 21 ;
* une base PostgreSQL (par exemple dans un conteneur Docker) avec une base `brio_dev`.

### Configuration

Le fichier `src/main/resources/application.properties` n'est pas versionné. Il faut le créer :

```properties
spring.application.name=brio
spring.datasource.url=jdbc:postgresql://localhost:5432/brio_dev
spring.datasource.username=XXXX
spring.datasource.password=XXXX
spring.jpa.hibernate.ddl-auto=update
```

### Démarrage

```bash
./mvnw spring-boot:run
```

## Projet

Projet personnel, commencé en juin 2026.
