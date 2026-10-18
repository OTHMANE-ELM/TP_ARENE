import java.util.Scanner;
public class Main {
    public static Personnage creerPersonnage(Scanner sc) {
        System.out.print("Nom : ");
        String nom = sc.nextLine().trim();
        // Tant que le nom est vide ou trop court, on redemande
        while (nom.length() < 2) {
            System.out.println("Erreur : le nom doit contenir au moins 2 caractères.");
            System.out.print("Nom : ");
            nom = sc.nextLine().trim();
        }
        System.out.print("Univers : ");
        String univers = sc.nextLine().trim();

        System.out.print("Attaque spéciale : ");
        String attaque = sc.nextLine().trim();

        System.out.print("Points de vie (1 à 500) : ");
        int pv = sc.nextInt();
        while (pv <= 0 || pv > 500) {
            System.out.println("Erreur : les points de vie doivent être entre 1 et 500.");
            System.out.print("Points de vie (1 à 500) : ");
            pv = sc.nextInt();
        }
        sc.nextLine(); // vide le retour à la ligne laissé par nextInt()
      return new Personnage(nom, univers, attaque, pv);
    }

    int tour = 0; // compteur de tours joués
    boolean niveauUtilise1 = false; // joueur 1 : niveau déjà monté ? boolean niveauUtilise2 = false; // joueur 2 : idem
    Personnage abandonneur = null; // null tant que personne n'abandonne


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //System.in = clavier

        System.out.println("=== Joueur 1 ===");
        Personnage p1 = creerPersonnage(sc);
        System.out.println("=== Joueur 2 ===");
        Personnage p2 = creerPersonnage(sc);

        // Annonce du combat en MAJUSCULES
        System.out.println(">>> " + p1.nom.toUpperCase() + " VS " + p2.nom.toUpperCase()
                + " : QUE LE MEILLEUR GAGNE ! <<<");

        // Variables du combat
        int tour = 0;                      // compteur de tours joués
        boolean niveauUtilise1 = false;    // joueur 1 : niveau déjà monté ?
        boolean niveauUtilise2 = false;    // joueur 2 : idem
        Personnage abandonneur = null;     // null tant que personne n'abandonne


        // Qui joue ce tour-ci ?
        boolean tourDuJoueur1 = (tour % 2 == 0);
        Personnage attaquant;
        Personnage defenseur;
        if (tourDuJoueur1) {
            attaquant = p1;
            defenseur = p2;
        } else {
            attaquant = p2;
            defenseur = p1;
        }

        // Menu
        System.out.println();
        System.out.println("=== Tour de " + attaquant.nom + " ===");
        System.out.println("1. Attaquer");
        System.out.println("2. Monter de niveau");
        System.out.println("3. Afficher l'état des combattants");
        System.out.println("4. Abandonner");
        System.out.print("Votre choix : ");
        int choix = sc.nextInt();
        sc.nextLine();

        boolean tourJoue = false; // true si l'action consomme le tour

        switch (choix) {
            case 1:
                attaquant.attaquer(defenseur);
                tourJoue = true;
                break;
            case 2:
                boolean dejaUtilise;
                if (tourDuJoueur1) {
                    dejaUtilise = niveauUtilise1;
                } else {
                    dejaUtilise = niveauUtilise2;
                }

                if (dejaUtilise) {
                    System.out.println("Vous avez déjà monté de niveau, action refusée.");
                } else {
                    attaquant.monterDeNiveau();
                    if (tourDuJoueur1) {
                        niveauUtilise1 = true;
                    } else {
                        niveauUtilise2 = true;
                    }
                    tourJoue = true;
                }
                break;
            case 3:
                p1.afficherEtat();
                p2.afficherEtat();
                break;
            case 4:
                abandonneur = attaquant;
                break;
            default:
                System.out.println("Choix invalide, rejouez.");
        }

        if (tourJoue) {
            tour++;
        }

        // Annonce du résultat
        System.out.println();
        System.out.println("=== FIN DU COMBAT ===");
        Personnage vainqueur;
        if (abandonneur != null) {
            System.out.println(abandonneur.nom + " abandonne.");
            if (abandonneur == p1) {
                vainqueur = p2;
            } else {
                vainqueur = p1;
            }
        } else if (p1.estVivant()) {
            vainqueur = p1;
        } else {
            vainqueur = p2;
        }

        System.out.println("Vainqueur : " + vainqueur.nom);
        System.out.println(p1.nom + " : niveau " + p1.niveau);
        System.out.println(p2.nom + " : niveau " + p2.niveau);
        System.out.println("Nombre de tours joués : " + tour);

        sc.close();
    }
}


































     /*       // Création de deux objets (instances) de la classe Personnage
        Personnage goku = new Personnage("GOKU", "Dragon Ball", "Kamehameha", 100);
        Personnage naruto = new Personnage("NARUTO", "Naruto", "Rasengan", 90);
        Personnage lufy = new Personnage("LUFY", "ONE PEICE", "GOMO GOMO NO", 80);
        // État initial des deux personnages
        goku.afficherEtat();
        naruto.afficherEtat();
        // Combat : chacun attaque l'autre une fois
        goku.attaquer(naruto);
        naruto.attaquer(goku);
        // État après le combat
        goku.afficherEtat();
        naruto.afficherEtat();

        // Attribut static : on passe par la classe, pas par un objet
        System.out.println("Personnages crées : " + Personnage.nombrePersonnages);

        // --- 2. Modification directe d'un attribut public ---
        goku.pv = 5;
        goku.afficherEtat();

        // --- 3. Accès à un attribut static via un objet (avertissement) ---
        System.out.println(goku.nombrePersonnages);
        //un avertissement Un attribut static appartient à la classe

        // --- 4. Goku attaque Naruto 10 fois ---
        for (int i = 0; i < 10; i++) {
            goku.attaquer(naruto);
        }

        // --- 5. Naruto est KO ---
        naruto.afficherEtat();
        naruto.attaquer(goku); // un KO ne peut pas attaquer

        // --- 6. Montée de niveau puis attaque de niveau 3 ---
        goku.monterDeNiveau(); // niveau 2
        goku.monterDeNiveau(); // niveau 3
        goku.attaquer(lufy); // Naruto est KO, on attaque donc Vegeta
        lufy.afficherEtat();
        */



