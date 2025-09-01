# API-incident
Backend de l'application de gestion des incidents
# Gestion d'Incidents GBS&S

Application web pour gérer les incidents techniques avec différents rôles utilisateurs.

## Installation

### Prérequis
- Java 17+
- Node.js 18+  
- MySQL

### 1. Base de données
```sql
CREATE DATABASE gestion_incidents;
```

### 2. Backend
```bash
cd backend/
mvn spring-boot:run
```

### 3. Frontend
```bash
cd frontend/
npm install
ng serve
```

## Accès
- Application : http://localhost:4200

## Configuration
Modifiez `application.yml` avec vos paramètres MySQL.

## Rôles
- ADMIN : Gestion complète
- DIRECTEUR : Vue d'ensemble  
- RESPONSABLE : Gestion incidents secteur
- TECHNICIEN : Résolution technique
- ATTENTE : En attente validation
