# TP 4

Antoine Prieur & Ivane Bonnet

---

# Question 1

## Situation 1 — Recherche avec une saisie incohérente

**Action :** Saisir `6544` comme fragment de nom.

**Comportement observé :** Le programme accepte la saisie et affiche « Aucun collaborateur trouvé ».

**Comportement souhaité :** Refuser la saisie car un nom doit contenir des caractères alphabétiques et demander une nouvelle saisie.


---

## Situation 2 — Recherche avec une saisie incohérente

**Action :** Lors d'une recherche par nom, saisir `6544`.

**Comportement observé :** Le programme accepte la saisie mais ne trouve aucun collaborateur.

**Comportement souhaité :** Le programme devrait détecter que la saisie est invalide et demander à l'utilisateur de saisir un nom valide.


---

### À réfléchir

Non, ce sont deux problèmes différents.

* **Erreur de saisie** : elle doit être traitée lors de la validation des données saisies par l'utilisateur.
* **Règle métier violée** : elle doit être traitée dans la logique métier de l'application.

---

# Question 2

Rien à signaler

---

# Question 3

Déclarer une bibliothèque dans pom.xml permet à Maven de gérer automatiquement les dépendances et leurs versions.

Lorsqu'un camarade récupère le projet, il peut simplement utiliser Maven pour télécharger les dépendances nécessaires, sans avoir besoin de récupérer manuellement des fichiers .jar.

---

# Question 4

Dans le debuger concernant l'augmentation négative de salaire:

"

Pile d'appels (Frames) : 

augmenterSalaire (Collaborateur.java:45) 

    pourcentage  = -50.0

    this.salaire = 42000.0 

main (HelloEfrei.java:87)

"

la ligne 45-47 dans Collaborateur  ignore en silence une valeur interdite, corriger l'affichage du menu ne sert à rien, il faudrait mettre un try/catch qui affiche le message. 

En ce qui concerne les logs, nous avons ajouté un log de chaque type (info, warn, debug, error). L'enjeu a été de les ajoutés dans des classes qui ne communique pas directement avec l'user (HelloEfrei). C'est comme cela que nous avons ajouté des logs à "ajouter" dans Annuaire ainsi que dans "aumenterSalaire" dans Collaborateur.

---

# Question 5
## 5.4
On choisit une seule table pour toute la hiérarchie : la recherche « salaire > seuil » porte sur un attribut commun et devient un simple SELECT … WHERE, alors qu'une table par classe obligerait à interroger chaque table et à fusionner les résultats.    

## 5.5
la stack trace sert au développeur pour diagnostiquer, alors que l'utilisateur a besoin d'un message simple qui lui dit quoi faire. Le code doit intercepter l'erreur pour donner les deux.


---

## 🛠️ Environnement

| Élément | Valeur                      |
| ------- | --------------------------- |
| Système | Windows / Linux / macOS     |
| Langage | [Java / C / Python / etc.]  |
| IDE     | [VS Code / IntelliJ / etc.] |
| Version | [Version]                   |
| Git     | [Version]                   |

## 📁 Structure du projet

```text
TP4_LSI2_Bonnet_Prieur/
├── README.md
├── src/
│   └── ...
├── ...
└── ...
```
