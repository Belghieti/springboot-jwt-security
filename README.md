# Spring Boot Security - JWT Authentication & Role Management

Ce projet est une application de démonstration qui utilise **Spring Boot** pour gérer l’authentification avec **JWT** et un système de **rôles dynamiques** (admin, membre...).

---

## 🔐 Fonctionnalités

- Authentification par email et mot de passe
- Génération de token JWT sécurisé
- Attribution de rôles aux utilisateurs
- Vérification d’autorisation via Spring Security
- Création, suppression et affichage dynamique des rôles

---

## 🧱 Technologies utilisées

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (jjwt)
- H2 (en mémoire) ou MySQL
- Lombok

---

## 🚀 Démarrer le projet

### 1. Cloner le dépôt

```bash
git clone https://github.com/ton-username/ton-depot.git
cd ton-depot
```
### 2. lancer le projet
```bash
./mvnw spring-boot:run
```
### 3. endpoint principaux
| Méthode | URL               | Description                   |
| ------- | ----------------- | ----------------------------- |
| POST    | `/api/auth/login` | Authentifier un utilisateur   |
| POST    | `/api/roles`      | Créer un nouveau rôle (admin) |
| GET     | `/api/roles`      | Lister tous les rôles         |
| DELETE  | `/api/roles/{id}` | Supprimer un rôle             |
### 4. Test Via Postman
#### 4.1 Se connecter via /api/auth/login
 → récupérer le token
#### 4.2 Utiliser ce token dans l’en-tête Authorization: Bearer <token>
 pour tester les autres routes sécurisées
 ### 5. 📝 Exemple de Token JWT
{

"roles": "ROLE_USER",

"sub": "mohamed@gmail.com",

"iat": 1710000000,

"exp": 1710040000

}
#### 5.1 reponse :
🙋‍♂️ Auteur

Mohamed Belghieti

Étudiant en ingénierie informatique