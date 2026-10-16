
public class Personnage {

    // Attribut de classe : un seul exemplaire partagé par tous les objets
    public static int nombrePersonnages = 0;

    // Attributs d'instance : chaque objet possède les siens
    public String nom;
    public String univers;
    public String attaqueSpeciale;
    public int pv;
    public int niveau;


     // Construit un personnage. Le niveau vaut toujours 1 à la création.

    public Personnage(String nom, String univers, String attaqueSpeciale, int pv) {
        this.nom = nom; // this.nom = l'attribut, nom = le paramètre
        this.univers = univers;
        this.attaqueSpeciale = attaqueSpeciale;
        this.pv = pv;
        this.niveau = 1;       // niveau initial fixé à 1
        nombrePersonnages++;   // compte le nombre d'objets créés
    }


    public boolean estVivant() {
        return pv > 0; // la condition est déjà un booléen
    }


    public void afficherEtat() {
        System.out.print("--- " + nom + " (" + univers + ") ---");
        // Si le personnage est KO, on l'indique sur la ligne du titre
        if (!estVivant()) {
            System.out.print(" [KO]");
        }
        System.out.println(); // passage à la ligne
        System.out.println("PV : " + pv);
        System.out.println("Niveau : " + niveau);
        System.out.println("Attaque : " + attaqueSpeciale);
    }


    public void monterDeNiveau() {
        niveau++;
        pv = pv + 20;
        System.out.println(nom + " passe au niveau " + niveau + " ! (+20 pv, total : " + pv + ").");
    }


    public void attaquer(Personnage cible) {
        // Règle 1 : l'attaquant est-il KO ?
        if (!this.estVivant()) {
            System.out.println(nom + " est KO et ne peut pas attaquer");
            return; // return seul = on quitte la méthode
        }
        // Règle 2 : la cible est-elle déjà KO ?
        if (!cible.estVivant()) {
            System.out.println(cible.nom + " est déjà KO");
            return;
        }
        // Règle 3 : dégâts de base
        int degats = 10 + niveau * 5;
        // Règle 4 : attaque spéciale tous les 3 niveaux (% = reste de la division)
        boolean special = (niveau % 3 == 0);
        if (special) {
            degats = degats * 2; // dégâts doublés
        }
        // Règle 5 : appliquer les dégâts sans descendre sous 0
        cible.pv = cible.pv - degats;
        if (cible.pv < 0) {
            cible.pv = 0;
        }
        // Règle 6 : affichage selon le type d'attaque
        if (special) {
            System.out.println(nom + " attaque " + cible.nom + " avec " + attaqueSpeciale + " : -" + degats + " pv");
        } else {
            System.out.println(nom + " attaque " + cible.nom + " : -" + degats + " pv");
        }
    }
}