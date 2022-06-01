/*
 * Menu.java                                  1 juin 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package sae.gui;

import java.util.Scanner;

import sae.pert.Projet;
import sae.pert.Tache;

import static sae.pert.Projet.UNITE_TEMPS;;

/** 
 * Menu permettant de gerer et ordonnancer un projet
 * avec la methode pert
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Menu {

    /** 
     * Un menu pour creer et gerer des projets et qui permet de calculer
     * les dates au plus tot et date au plus tard de chaque tache
     * ainsi que la date au plus tot et la date au plus tard de chaque projet
     * @param entree
     */
    public static void menuPrincipal(Scanner entree) {

        int choix = 0;
        boolean repet = true;

        Projet projet = new Projet("initialisation", "initialisation pour la "
                        + "compilation", UNITE_TEMPS[0]);

        while (repet) {
            System.out.println("\n---------- Nom du logiciel ----------\n");
            System.out.println("Menu principale : \n");
            System.out.println("1 - Creer un projet\n"
                            + "2 - Importer un projet\n"
                            + "3 - Quitter le logiciel\n");
            System.out.print("Entrez le chiffre de votre choix : ");
            choix = entree.hasNextInt() ? entree.nextInt() : 0;
            entree.nextLine();
            switch (choix) {
            case 1: 
                projet = creerProjet();
                menu(projet, entree);
                repet = false;
                break;
            case 2:
                System.out.println("Entrez le chemin du fichier : ");
                projet = Projet.importer(entree.nextLine());
                projet.calculerDateAuPlusTot();
                projet.calculerDateAuPlusTotFinDeProjet();
                projet.calculerDateAuPlusTard();
                menu(projet, entree);
                repet = false;
                break;
            case 3:
                System.out.println("\nAu revoir");
                repet = false;
                break;
            default:
                System.out.println("\nVeuillez choisir un nombre entre "
                                + "1 et 3");
                break;
            }
        }
    }

    /**
     * Menu du projet
     * @param projet
     * @param entree
     */
    public static void menu(Projet projet, Scanner entree) {

        int choix = 0;
        boolean repet = true;
        String nomDeTache;

        while (repet) {
            System.out.println("\n---------- " + projet.getNom() 
            + " ----------\n");
            System.out.println("1 - Afficher le projet\n"
                            + "2 - Créer une tache\n"
                            + "3 - Sélectionner une tâche pour la modifier\n"
                            + "4 - Sauvegarder\n"
                            + "5 - Retourner au menu (sans sauvegarder)\n"
                            );
            System.out.print("Entrez le chiffre de votre choix : ");
            choix = entree.hasNextInt() ? entree.nextInt() : 0;
            entree.nextLine();
            switch (choix) {
            case 1: 
                System.out.println("\n" + projet.toString() + "\n");
                break;
            case 2:
                try {
                    projet.ajouterTache(creerTache());
                    projet.calculerDateAuPlusTot();
                    projet.calculerDateAuPlusTotFinDeProjet();
                    projet.calculerDateAuPlusTard();
                } catch (IllegalArgumentException erreurDeSaisie) {
                    System.out.println(erreurDeSaisie.getMessage());
                }
                break;
            case 3:
                System.out.println("Voici les taches de ce projet :");
                for (int i = 0; i < Projet.nombreTaches(projet); i++) {
                    System.out.println(Projet.tacheProjet(projet,i).getNom());
                }
                System.out.print("Veuillez entrer le nom de la tache"
                                + " à selectionner : ");               
                nomDeTache = entree.nextLine();
                boolean ok = false;
                int index;
                for (index = 0; !ok && index < Projet.nombreTaches(projet); index++) {
                    ok = Projet.tacheProjet(projet,index).getNom().equals(nomDeTache);
                }
                if (ok) {
                    menuTache(projet, entree, Projet.tacheProjet(projet,index - 1));
                    repet = false;
                } else {
                    System.out.println("La tâche que vous avez renseignée n'existe pas");
                }

                break;
            case 4:
                System.out.println("Choisissez un chemin de sauvegarde : ");
                projet.sauvegarder(entree.nextLine());
                break;
            case 5:
                menuPrincipal(entree);
                repet = false;
                break;
            default:
                System.out.println("\nVeuillez choisir un nombre entre "
                                + "1 et 5");
                break;
            }
        }
    }

    /**
     * 
     * @param projet
     * @param entree
     * @param tacheSelect
     */
    public static void menuTache(Projet projet, Scanner entree, Tache tacheSelect) {
        int choix = 0;
        boolean repet = true;
        
        while (repet) {
            System.out.println("\n---------- " + tacheSelect.getNom() 
            + " ----------\n");
            System.out.println("1 - Modifier la tache\n"
                               + "2 - Supprimer la tache\n"
                               + "3 - Ajouter une tache préalable\n"
                               + "4 - Enlever une tache préalable\n"
                               + "5 - Retourner au menu et valider\n"
                            );
            System.out.print("Entrez le chiffre de votre choix : ");
            choix = entree.hasNextInt() ? entree.nextInt() : 0;
            entree.nextLine();
            
            switch (choix) {
            case 1:
                break;
            case 2:
                projet.enleverTache(tacheSelect);
                System.out.println("Cette tâche a été supprimé");
                projet.calculerDateAuPlusTot();
                projet.calculerDateAuPlusTotFinDeProjet();
                projet.calculerDateAuPlusTard();
                repet = false;
                menu(projet, entree);
                break;
            case 3:
                break;
            case 4:
//                System.out.println("\n1. Ajouter une tache"
//                                + "\n2. Enlever une tache");
//                System.out.print("Votre choix : ");
//                if (entree.hasNextInt()) {
//                    choix = entree.nextInt();
//                    if (choix == 1) {
//                        //ajouterTachePrealable();
//                    } else if (choix == 2) {
//                        //enleverTachePrealable();
//                    } else {
//                        System.out.println("Vous n'avez pas saisie une "
//                                        + "valeur correct");
//                    }
//                } else {
//                    System.out.println("Vous n'avez pas saisie une valeur "
//                                    + "correct");
//                }
//                entree.nextLine();
                break;
            case 5:
                projet.calculerDateAuPlusTot();
                projet.calculerDateAuPlusTotFinDeProjet();
                projet.calculerDateAuPlusTard();
                menu(projet, entree);
                repet = false;
                break;
            default:
                System.out.println("\nVeuillez choisir un nombre entre "
                                + "1 et 5");
                break;
            }
        }
    }


    /** 
     * Permet en interrogeant l'utilisateur de creer un projet
     * @return projet Projet que l'on creer
     */
    public static Projet creerProjet() {
        Scanner entree = new Scanner(System.in);
        String nom;
        String description;
        String uniteTemps;
        int uniteTempsChoisie;
        boolean uniteTempsChoisieFaux;
        boolean saisieOk = false;
        Projet projet = null;
        while (!saisieOk) {
            System.out.print("Veuillez entrer le nom de votre projet : ");
            nom = entree.nextLine();
            System.out.print("Veuillez decrire votre projet : ");
            description = entree.nextLine();
            System.out.println("Veuillez saisir un chiffre pour choisir une unite"
                            + " de temps pour votre projet : ");
            for (int i = 0; i < UNITE_TEMPS.length; i++) {
                System.out.println((i + 1) + ". " + UNITE_TEMPS[i]);
            }
            System.out.print("Votre choix : ");
            uniteTempsChoisie = 0;
            uniteTempsChoisieFaux = true;
            while (uniteTempsChoisieFaux) {
                if (entree.hasNextInt()) {
                    uniteTempsChoisie = entree.nextInt();
                    uniteTempsChoisieFaux = uniteTempsChoisie <= 0 
                                    || uniteTempsChoisie >= 7;
                } else {
                    uniteTempsChoisieFaux = true;
                }
                if (uniteTempsChoisieFaux) {
                    System.out.println("Veuillez entrer un nombre "
                                    + "entre un 1 et 6 : ");
                }
                entree.nextLine();
            }
            uniteTemps = UNITE_TEMPS[uniteTempsChoisie - 1];
            try {
                projet = new Projet(nom, description, uniteTemps);
                saisieOk = true;
            } catch (IllegalArgumentException erreurConstructeur) {
                System.out.println(erreurConstructeur.getMessage());
                System.out.println("Veuillez recommencer");
            }
        }
        return projet;
    }

    /** 
     * Permet de creer une tache en interrogeant l'utilisateur
     * @return tache que l'on creer
     */
    public static Tache creerTache() {
        Scanner entree = new Scanner(System.in);
        String nom;
        String description;
        double duree;
        Tache tache = null;
        boolean saisieOk= false;
        while (!saisieOk) {
            System.out.print("Veuillez entrer le nom de la tache : ");
            nom = entree.nextLine();
            System.out.print("Veuillez entrer la description de la tache : ");
            description = entree.nextLine();
            System.out.print("Veuillez entrer la duree de la tache : ");
            duree = entree.hasNextDouble() ? entree.nextDouble() : -1.0; 
            entree.nextLine();
            try {
                tache = new Tache(nom, description, duree);
                saisieOk = true;
            } catch (IllegalArgumentException erreurConstructeur) {
                System.out.println(erreurConstructeur.getMessage());
                System.out.println("Veuillez recommencer");
            }
        }
        return tache;
    }



    //  /** 
    //  * Permet d'ajouter les taches prealables du projet
    //  */
    // public static void ajouterTachePrealable() {
    //     boolean retour = false;
    //     boolean ok = false;
    //     String nomTache;
    //     String nomTacheAAjouter;
    //     Tache tache = new Tache("nom", "description", 0.0);
    //     Tache tacheAAjouter = new Tache("nom", "description", 0.0);
    //     Scanner entree = new Scanner(System.in);
    //     do {
    //         System.out.println("\nTapez 0 pour revenir au menu du projet,"
    //                         + " ou choisissez une tache a configurer :");
    //         for (int i = 0; i < this.taches.size(); i++) {
    //             System.out.println(this.taches.get(i).getNom());
    //         }
    //         System.out.print("Choisissez une tache : ");
    //         nomTache = entree.nextLine();
    //         ok = false;
    //         for (int i = 0; !ok && i < this.taches.size(); i++) {
    //             ok = nomTache.equals(this.taches.get(i).getNom());
    //             if (ok) {
    //                 tache = this.taches.get(i);
    //             }
    //         }
    //         if (nomTache.startsWith("0")) {
    //             System.out.println("Retour au projet\n");
    //             retour = true;
    //         } else if (!ok) {
    //             System.out.println("Cette tache n'est pas dans le projet");
    //         } else {
    //             System.out.println("Choisissez la tache a ajouter au tache "
    //                             + "prealable de " + tache.getNom());
    //             for (int i = 0; i < this.taches.size(); i++) {
    //                 System.out.println(this.taches.get(i).getNom());
    //             }
    //             System.out.print("Choisissez une tache : ");
    //             nomTacheAAjouter = entree.nextLine();
    //             ok = false;
    //             for (int i = 0; !ok && i < this.taches.size(); i++) {
    //                 ok = nomTacheAAjouter.equals(this.taches.get(i).getNom());
    //                 if (ok) {
    //                     tacheAAjouter = this.taches.get(i);
    //                     try {
    //                         tache.ajouterTachePrealable(tacheAAjouter);
    //                         if (this.aUnCircuit()) {
    //                             System.out.println("Cette tache ne peut pas "
    //                                             + "etre ajouter aux taches "
    //                                             + "prealables de " 
    //                                             + nomTache
    //                                             + " car il y aura des "
    //                                             + "circuits");
    //                             tache.enleverTachePrealable(tacheAAjouter);
    //                         } else {
    //                             System.out.println("Cette tache a ete ajouter");
    //                         }
    //                     } catch (IllegalArgumentException erreurDeSaisie) {
    //                         System.out.println(erreurDeSaisie.getMessage());
    //                     }
    //                 }
    //             }
    //             if (!ok) {
    //                 System.out.println("Cette tache n'existe pas");
    //             }
    //         }
    //     } while (!retour);
    // }
    // 
    // /** 
    //  * Permet d'enlever les taches prealables du projet
    //  */
    // public static void enleverTachePrealable() {
    //     boolean retour = false;
    //     boolean ok = false;
    //     String nomTache;
    //     String nomTacheAEnlever;
    //     Tache tache = new Tache("nom", "description", 0.0);
    //     Tache tacheAEnlever = new Tache("nom", "description", 0.0);
    //     Scanner entree = new Scanner(System.in);
    //     do {
    //         System.out.println("\nTapez 0 pour revenir au menu du projet,"
    //                         + " ou choisissez une tache a configurer :");
    //         for (int i = 0; i < this.taches.size(); i++) {
    //             System.out.println(this.taches.get(i).getNom());
    //         }
    //         System.out.print("Choisissez une tache : ");
    //         nomTache = entree.nextLine();
    //         for (int i = 0; !ok && i < this.taches.size(); i++) {
    //             ok = nomTache.equals(this.taches.get(i).getNom());
    //             if (ok) {
    //                 tache = this.taches.get(i);
    //             }
    //         }
    //         if (nomTache.startsWith("0")) {
    //             System.out.println("Retour au projet\n");
    //             retour = true;
    //         } else if (!ok) {
    //             System.out.println("Cette tache n'est pas dans le projet");
    //         } else if (tache.getTachesPrealables().size() == 0) {
    //             System.out.println("Cette tache n'a pas de tache prealable");
    //         } else {
    //             System.out.println("Choisissez une tache prealable "
    //                             + "a enlever :");
    //             for (int i = 0; i < tache.getTachesPrealables().size(); i++) {
    //                 System.out.println(tache.getTachesPrealables()
    //                                 .get(i).getNom());
    //             }
    //             System.out.println("Choisissez une tache :");
    //             nomTacheAEnlever = entree.nextLine();
    //             ok = false;
    //             for (int i = 0; !ok && i < tache.getTachesPrealables().size()
    //                             ; i++) {
    //                 ok = nomTacheAEnlever.equals(
    //                                 tache.getTachesPrealables().get(i).getNom()
    //                                 );
    //                 if (ok) {
    //                     tacheAEnlever = tache.getTachesPrealables().get(i);
    //                     try {
    //                         tache.enleverTachePrealable(tacheAEnlever);
    //                     } catch (IllegalArgumentException erreurDeSaisie) {
    //                         System.out.println(erreurDeSaisie.getMessage());
    //                     }
    //                 }
    //             }
    //             if (!ok) {
    //                 System.out.println("Ceci n'est pas une tache prealable");
    //             }
    //         }
    //     } while (!retour);
    // }

}




