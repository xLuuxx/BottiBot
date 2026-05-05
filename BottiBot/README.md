# BottiBot

BottiBot est un bot Discord développé en Java avec **JDA**. Il propose plusieurs commandes simples pour apprendre la gestion d'événements, les appels API, les embeds, les réactions Discord.

## Fonctionnalités

Le bot inclut des commandes préfixées en `!` pour les usages classiques, et une commande slash pour le mode interactif plus avancé.

### Commandes disponibles

| Commande               | Type | Description |
|------------------------|---|---|
| `!ping`                | Préfixée | Répond `Pong !` |
| `!joke`                | Préfixée | Récupère une blague aléatoire depuis une API |
| `!poll`                 | Préfixée | Crée un sondage avec deux réactions |
| `!help`                | Préfixée | Affiche la liste des commandes disponibles |
| `/rps adversaire:@user` | Slash command | Lance un pierre-feuille-ciseaux avec boutons privés |

## Technologies

Le projet repose sur Java et **JDA**, la librairie Java Discord API qui permet de gérer les événements, les commandes, les interactions et les composants comme les boutons.

Technologies principales :
- Java
- JDA
- Gson
- Discord Developer Portal pour la configuration du bot et des intents.

## Installation

### 1. Cloner le projet

```bash
git clone https://github.com/xLuuxx/BottiBot.git
cd BottiBot
```

### 2. Configurer le bot Discord

Créer une application et un bot dans le [Discord Developer Portal](https://discord.com/developers/applications), puis récupérer le token du bot et l'ajouter à la configuration du projet.

### 3. Activer les intents nécessaires

Dans le portail Discord, aller dans l'onglet **Bot** puis activer :
- **Message Content Intent**, nécessaire pour lire le contenu des messages et faire fonctionner les commandes préfixées comme `!ping` ou `!help`.

Dans le portail Discord, aller dans l'onglet **OAuth2 > URL Generator** et sélectionner les scopes et permissions suivants pour inviter le bot sur un serveur de test :
- **Send Message** et **Read Message History** pour permettre au bot d'envoyer des messages et de lire les messages précédents si besoin.

### 4. Configurer le projet Java

Renseigner dans un .env placé à la racine ou un fichier de configuration les éléments suivants :
- le token du bot sous DISCORD_TOKEN;
- le préfixe des commandes, sous BOT_PREFIX (ici `!`);
- l'URL de l'API de blagues si elle est configurable, sous JOKE_API_URL (ici https://official-joke-api.appspot.com/random_joke).

### 5. Lancer le projet

Exécuter la classe principale `Main.java`.   
JDA permet ensuite d'initialiser les listeners, d'attendre que le bot soit prêt et d'enregistrer la slash command `rps` avec `upsertCommand(...)`.

## Structure du projet

```text
src/main/java/fr/ynov/bottibot/
├── Main.java
├── BotConfig.java
├── listener/
│   └── CommandListener.java
├── util/
│   └── EmbedHelper.java
└── command/
    ├── ICommand.java
    ├── CommandManager.java
    ├── PingCommand.java
    ├── JokeCommand.java
    ├── PollCommand.java
    ├── HelpCommand.java
    ├── RpsGameManager.java
    ├── RpsSlashCommandListener.java
    └── RpsButtonListener.java
```

Cette organisation sépare clairement la gestion des commandes préfixées et la logique des interactions Discord modernes comme les slash commands et les boutons.

## Détail des commandes

### `!ping`

Commande de test simple pour vérifier que le bot répond correctement.

### `!joke`

Effectue une requête HTTP vers une API publique, lit la réponse JSON, puis envoie la blague dans le salon Discord.

### `!poll`

Crée un sondage avec une question et deux réponses, puis ajoute automatiquement deux réactions Unicode pour voter.

### `!help`

Affiche un embed listant les commandes disponibles avec leur description. `EmbedBuilder` permet de construire proprement ce type de message enrichi dans JDA.[2][8]

### `/rps`

Commande slash interactive qui permet de défier un autre membre à pierre-feuille-ciseaux avec des boutons. Chaque joueur valide son choix via interaction, et le choix est confirmé en réponse éphémère pour rester privé.

## Branche dédiée à RPS

La fonctionnalité **RPS** peut être placée dans une branche séparée du projet afin de distinguer le socle principal du bot et l'ajout plus avancé basé sur les slash commands et les boutons Discord.

```bash
git checkout feat/rps
```

Cela permet de s'assurer de la bonne mise en place et que les possibles bugs liés au développement n'impacte pas les fonctionnalitées principales.


## Gestion des commandes

Les commandes en `!` sont gérées via un `CommandManager` qui :
- ignore les messages envoyés par les bots ;
- vérifie le préfixe ;
- découpe les arguments ;
- cherche la commande correspondante ;
- envoie un message d'erreur si la commande n'existe pas.

Cette architecture correspond bien à une approche simple de bot orienté commandes texte avec JDA et événements de messages.

## Robustesse

Le projet intègre plusieurs éléments de robustesse utiles :
- gestion des commandes inconnues ;
- vérification des arguments manquants ;
- ignorance des messages envoyés par d'autres bots ;

## Utilisation

Exemples :

```text
!ping
!joke
!poll Pizza ce soir ? | Oui | Non
!help
/rps adversaire:@Pseudo
```

## Améliorations possibles

- Intégration du rps à la branche principale du projet pour unifier les fonctionnalités,
- Ajout de commandes supplémentaires,
- Transformation des prefixes ! en slash commands.
## Auteur

Made By Luu.