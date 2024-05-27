# Application de Suivi des Rappels de Produits

## Introduction

Bienvenue dans l'application de suivi des rappels de produits. Cette application permet de rechercher des rappels de produits, de consulter les détails des rappels, de gérer une liste de favoris et de configurer des préférences pour une expérience utilisateur personnalisée.

## Fonctionnalités Principales

### Recherche des Rappels de Produits

- **Barre de Recherche** : Effectuez des recherches de produits rappelés en utilisant une barre de recherche intuitive.
- **Liste des Résultats** : Les résultats de recherche sont affichés sous forme de cartes avec une image du produit, son nom, et des informations supplémentaires.
- **Détails du Rappel** : Cliquez sur une carte pour accéder aux détails complets du rappel, y compris les raisons du rappel et les actions recommandées.

### Gestion des Favoris

- **Ajout aux Favoris** : Ajoutez des produits rappelés à votre liste de favoris depuis la page de recherche ou de détails.
- **Liste des Favoris** : Gérez vos favoris sur une page dédiée, affichant chaque produit avec des informations détaillées.
- **Suppression et Copie** : Supprimez des produits de la liste des favoris ou copiez le lien de l'offre dans le presse-papiers.

### Configuration

- **Personnalisation** : Configurez l'application selon vos préférences, notamment en fixant des critères de recherche par défaut et en limitant le nombre de résultats.
- **Mode Sombre** : Activez ou désactivez le mode sombre pour une meilleure ergonomie.

## Architecture de l'Application

- **Fragments et Activités** : L'application utilise une activité principale avec plusieurs fragments pour gérer les différentes sections (recherche, favoris, configuration).
- **ViewModel et LiveData** : Ces composants d'architecture assurent une gestion efficace des données et une séparation claire de la logique d'affaires et de l'interface utilisateur.
- **Services** : Les services en arrière-plan gèrent les appels API et les notifications sans bloquer l'interface utilisateur.

## Technologies Utilisées

- **Retrofit** : Pour effectuer les appels API à l'API de Rappel Conso et gérer les interactions réseau.
- **Moshi** : Pour la sérialisation et la désérialisation des données JSON.
- **Room** : Pour la gestion de la base de données locale des favoris, assurant des opérations de persistance performantes et sécurisées.
- **NotificationCompat** : Pour assurer la compatibilité des notifications avec différentes versions d'Android.

## Répartition du Travail

- **Genti Anthony** : Architecture générale, intégration API, gestion de la base de données, interface utilisateur pour la recherche et les détails des rappels.
- **Boudount Youssef** : Gestion des favoris, page de configuration, ajout des notifications et du mode sombre.

## Défis et Solutions

- **Intégration API** : Gestion des erreurs de sérialisation JSON et des changements de configuration avec ViewModel et LiveData.
- **Gestion des Favoris** : Optimisation des requêtes SQL et mise en place de la pagination pour améliorer les performances.
- **Notifications et Mode Sombre** : Utilisation des API de compatibilité pour assurer une expérience utilisateur cohérente sur toutes les versions d'Android.

## Installation

1. Clonez le dépôt GitHub :

   ```bash
   git clone https://github.com/CERI-L3-20232024-AndroidProjet/projetrappelconso-boudount_genti.git
   ```
2. Ouvrez le projet dans Android Studio.
3. Synchronisez les dépendances Gradle.
4. Exécutez l'application sur un émulateur ou un appareil Android.

## Remerciements

Merci à tous ceux qui ont contribué à ce projet, y compris les enseignants et les camarades de classe pour leur soutien et leurs retours constructifs. Un merci tout particulier à notre professeur de TP, MERLIN Eric, pour son aide et ses conseils précieux tout au long du développement de ce projet.
