# TP 4

Antoine Prieur & Yvane Bonnet

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
