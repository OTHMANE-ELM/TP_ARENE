
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        // Création de deux objets (instances) de la classe Personnage
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
    }
}


