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
 * Menu permettant de gérer et ordonnancer un projet
 * avec la méthode PERT
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Menu {

    /** 
     * Un menu pour créer et gérer des projets et qui permet de calculer
     * les dates au plus tôt et date au plus tard de chaque tache
     * ainsi que la date au plus tôt et la date au plus tard de chaque projet
     * @param entree analyseur de l'entrée texte
     */
    public static void menuPrincipal(Scanner entree) {

        int choix = 0;
        boolean repet = true;

        Projet projet = new Projet("initialisation", "initialisation pour la "
                                   + "compilation", UNITE_TEMPS[0]);

        while (repet) {
            System.out.println("\n---------- Menu principal ----------\n");
            System.out.println("Choix : \n");
            System.out.println("1 - Créer un projet\n"
                               + "2 - Importer un projet\n"
                               + "3 - Quitter le logiciel\n");
            System.out.print("Entrez le chiffre de votre choix : ");
            choix = entree.hasNextInt() ? entree.nextInt() : 0;
            entree.nextLine();
            switch (choix) {
            case 1: 
                effacerConsole();
                projet = creerProjet(entree);
                menu(projet, entree);
                repet = false;
                break;
            case 2:
                effacerConsole();
                while (choix != 0) {
                    System.out.println("\n---------- Importation ----------\n");
                    System.out.print("Entrez le chemin du fichier "
                                       + "ou tapez 0 pour retourner au menu"
                                       + "\nChemin du fichier : ");
                    String lien = entree.nextLine();
                    if (!lien.equals("0")) {
                        try {
                            projet = Projet.importer(lien);
                            projet.ordonnancement();
                            menu(projet, entree);
                            repet = false;
                            choix = 0;
                        } catch (IllegalArgumentException erreurFichier) {
                            effacerConsole();
                            System.out.println("\nERREUR : Le fichier n'existe pas "
                                               + "ou est corrompu\n");
                            choix = 2;
                        }
                    }else if (lien.equals("0")) {
                        choix = 0;
                        effacerConsole();
                    }
                }
                break;
            case 3:
                effacerConsole();
                System.out.println("\nAu revoir");
                repet = false;       
                break;
            default:
                effacerConsole();
                System.out.println("\nERREUR : Veuillez choisir un nombre entre "
                                   + "1 et 3\n");
                break;
            }
        }
    }

    /**
     * Menu du projet
     * @param projet Projet actuelle
     * @param entree analyseur d'entrée texte
     */
    public static void menu(Projet projet, Scanner entree) {

        int choix = 0;
        boolean repet = true;
        effacerConsole();
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
                effacerConsole();
                System.out.println("\n-------- Affichage du projet -------- \n\n" 
                                   + projet.toString() + "\n");
                break;
            case 2:
                try {
                    effacerConsole();
                    projet.ajouterTache(creerTache(entree));
                    projet.ordonnancement();
                    effacerConsole();
                } catch (IllegalArgumentException erreurDeSaisie) {
                    effacerConsole();
                    System.out.println(erreurDeSaisie.getMessage());
                }
                break;
            case 3:
                if (Projet.nombreTaches(projet) < 1) {
                    effacerConsole();
                    System.out.println("\nERREUR : Ce projet n'a pas de tâche\n");
                }else {
                    effacerConsole();
                    while  ( choix != 0) {
                        System.out.println("\n------ Choix de la tâche"
                                           + " à modifier ------\n"
                                           + "\nTapez 0 pour retourner en arrière\n");
                        int indexTache = afficheTaches(projet, entree);
                        if (indexTache != -1 && indexTache != -2) {
                            menuTache(projet, entree, projet.avoirTache(indexTache));
                            choix = 0;
                            repet = false;
                        } else if (indexTache == -2) {
                            effacerConsole();
                            choix = 0;
                        }else {
                            effacerConsole();
                            System.out.println("\nERREUR : La tâche que vous avez "
                                               + "renseignée n'existe pas.\n");
                        }
                        
                    }
                }
                break;
            case 4:
                effacerConsole();
                System.out.print("Tapez 0 pour revenir en arriére."
                                 + "\nChoisissez un chemin de sauvegarde : ");
                String chemin = entree.nextLine();
                if (chemin.equals("0")) {
                    choix = 0;
                    effacerConsole();
                }else {
                    projet.sauvegarder(chemin);
                    effacerConsole();
                }
                break;
            case 5:
                effacerConsole();
                menuPrincipal(entree);
                repet = false;
                break;
            default:
                effacerConsole();
                System.out.println("\nERREUR : Veuillez choisir un nombre entre "
                                   + "1 et 5\n");
                break;
            }
        }
    }

    /**
     * Menu de modification des tâches
     * @param projet projet sur lequel on travail
     * @param entree analyseur de l'entree texte
     * @param tacheSelect tâche pour laquelle on affiche le menu
     */
    public static void menuTache(Projet projet, Scanner entree, Tache tacheSelect) {
        int choix = 0;
        boolean repet = true;
        effacerConsole();
        while (repet) {
            System.out.println("\n---------- Menu de " + tacheSelect.getNom() 
                               + " ----------\n");
            System.out.println("1 - Modifier la tâche\n"
                               + "2 - Supprimer la tâche\n"
                               + "3 - Ajouter une tâche préalable\n"
                               + "4 - Enlever une tâche préalable\n"
                               + "5 - Retourner au menu et valider\n"
                               );
            System.out.print("Entrez le chiffre de votre choix : ");
            choix = entree.hasNextInt() ? entree.nextInt() : 0;
            entree.nextLine();

            switch (choix) {
            case 1:
                effacerConsole();
                modifTache(projet, tacheSelect, entree);
                repet = false;
                break;
            case 2:
                effacerConsole();
                try {
                    projet.enleverTache(tacheSelect);
                    System.out.println("Cette tâche a été supprimée avec succés");
                    System.out.print("\nAppuyer sur entrée pour continuer");
                    entree.nextLine();
                    } catch (IllegalArgumentException erreur) {
                    System.out.println("\nERREUR : Cette tâche n'a pas pu être supprimée : "
                                       + erreur.getMessage() + "\n");
                    System.out.print("\nAppuyer sur entrée pour continuer");
                    entree.nextLine();
                }
                if (Projet.nombreTaches(projet) > 0) {
                    projet.ordonnancement();
                }
                repet = false;
                menu(projet, entree);
                break;
            case 3:
                effacerConsole();
                System.out.println("\n------ Ajout de tâche Préalable -----");
                if (Projet.nombreTaches(projet) < 2) {
                    System.out.println("\nERREUR : Ce projet n'a pas "
                                       + "assez de tâche.\n");
                }else {
                    while (choix != 0) {
                        System.out.println("Voici les taches préalables "
                                           + "de la tâche "
                                           + tacheSelect.getNom()
                                           + " tapez 0 pour revenir en arrière.");
                        for (int index = 0; 
                                        index < tacheSelect.nombreTachesPrealables(); 
                                        index++) {
                            System.out.print(tacheSelect.getTachesPrealables()
                                                        .get(index).getNom() 
                                             + " | ");
                        }
                        System.out.println("\n");
                        int indexTache = afficheTaches(projet, entree);
                        if (indexTache != -1 && indexTache != -2) {
                            try {
                                tacheSelect.ajouterTachePrealable(
                                                projet.avoirTache(indexTache));
                                if (projet.aUnCircuit(tacheSelect)) {
                                    effacerConsole();
                                    System.out.println("\nERREUR : Cette tâche ne peut pas "
                                                       + "être ajoutée aux tâches "
                                                       + "préalables de " 
                                                       + tacheSelect.getNom()
                                                       + " ,car il y aura des "
                                                       + "circuits.\n");
                                    tacheSelect.enleverTachePrealable(
                                                    projet.avoirTache(indexTache));
                                } else {
                                    effacerConsole();
                                    System.out.println("Cette tâche à été ajoutée.");
                                    choix = 0;
                                }
                            } catch (IllegalArgumentException erreurDeSaisie) {
                                System.out.println("\nERREUR :" 
                                                   + erreurDeSaisie.getMessage()
                                                   + "\n");
                            }
        
                        } else if (indexTache == -2){
                            effacerConsole();
                            choix = 0;
                        }else {
                            effacerConsole();
                            System.out.println("\nERREUR : La tâche que vous "
                                               + "avez renseignée "
                                               + "n'existe pas\n");
                        }
                    }
                }
                break;
            case 4:
                String nomDeTache;
                effacerConsole();
                System.out.println("\n------ Retrait de tache Prealable -----");
                if (tacheSelect.nombreTachesPrealables() < 1) {
                    System.out.println("\nERREUR : Cette tâche n'a "
                                       + "pas de tâche préalable\n");
                }else {
                    while (choix != 0) {
                        System.out.println("Voici les tâches préalables "
                                           + "de la tâche "
                                           + tacheSelect.getNom());
                        for (int index = 0; 
                                        index < tacheSelect.nombreTachesPrealables(); 
                                        index++) {
                            System.out.print(tacheSelect.getTachesPrealables()
                                                        .get(index).getNom() 
                                             + " | ");
                        }
                        System.out.print("\n\nVeuillez entrer le nom de la tâche"
                                        + " à supprimer : ");               
                        nomDeTache = entree.nextLine();
                        
                        if (nomDeTache.equals("0")) {
                            effacerConsole();
                            choix = 0;
                        }else {
        
                            boolean ok = false;
                            int index;
                            for (index = 0; !ok && index < tacheSelect.nombreTachesPrealables()
                                                         ; index++) {
                                ok = tacheSelect.getTachesPrealables()
                                                .get(index)
                                                .getNom() 
                                                .equals(nomDeTache);
                            }
                            if (ok) {
                                Tache tacheAEnlever = tacheSelect
                                                .avoirTachePrealable(index - 1);
                                try {
                                    tacheSelect.enleverTachePrealable(tacheAEnlever);
                                    effacerConsole();
                                    System.out.print("Tâche supprimée avec succés\n");
                                    choix = 0;
                                } catch (IllegalArgumentException erreurDeSaisie) {
                                    System.out.println(erreurDeSaisie.getMessage());
                                }
                            } else {
                                effacerConsole();
                                System.out.println("\nERREUR : Ceci n'est pas une"
                                                   + " tâche préalable\n");
                            }
                        }
                    }
                }
                break;
            case 5:
                if (Projet.nombreTaches(projet) > 0) {
                    projet.ordonnancement();
                }
                effacerConsole();
                menu(projet, entree);
                repet = false;
                break;
            default:
                System.out.println("\nERREUR : Veuillez choisir un nombre entre "
                                   + "1 et 5\n");
                break;
            }
        }
    }


    /** 
     * Permet en interrogeant l'utilisateur de créer un projet
     * @param entree analyseur de l'entree texte
     * @return projet Projet que l'on créer
     */
    public static Projet creerProjet(Scanner entree) {
        String nom;
        String description;
        String uniteTemps;
        int uniteTempsChoisie;
        boolean uniteTempsChoisieFaux;
        boolean saisieOk = false;
        Projet projet = null;
        
        while (!saisieOk) {
            System.out.println("\n---------- Création du projet ----------\n");     
            System.out.print("Veuillez entrer le nom de votre projet : ");
            nom = entree.nextLine();
            System.out.print("Veuillez décrire votre projet : ");
            description = entree.nextLine();
            System.out.println("Veuillez saisir un chiffre pour choisir une unité"
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
                    System.out.println("\nERREUR : Veuillez entrer un nombre "
                                       + "entre un 1 et 6 : \n");
                }
                entree.nextLine();
            }
            uniteTemps = UNITE_TEMPS[uniteTempsChoisie - 1];
            try {
                projet = new Projet(nom, description, uniteTemps);
                saisieOk = true;
            } catch (IllegalArgumentException erreurConstructeur) {
                System.out.println(erreurConstructeur.getMessage());
                System.out.println("\nERREUR : Veuillez recommencer\n");
            }
        }
        return projet;
    }

    /** 
     * Permet de créer une tache en interrogeant l'utilisateur
     * @param entree analyseur de l'entree texte
     * @return tache que l'on créer
     */
    public static Tache creerTache(Scanner entree) {
        String nom = "";         //Valeur invalide
        String description = ""; //Valeur invalide
        double duree = -1.0;     //Valeur invalide
        Tache tache = null;
        boolean saisieOk = false;
        System.out.println("\n---------- Création d'une tache ----------\n");
        while (!saisieOk) {
            System.out.print("Veuillez entrer le nom de la tâche : ");
            nom = entree.nextLine();
            if (nom.isEmpty()) {
                System.out.println("\nERREUR : Le nom est vide. "
                                   + "Veuillez recommencer\n");
            }else {
                saisieOk = true;
            }
        }
        saisieOk = false;
        while (!saisieOk) {
            System.out.print("Veuillez entrer la description de la tache : ");
            description = entree.nextLine();
            if (description.isEmpty()) {
                System.out.println("\nERREUR : La description est vide. "
                                   + "Veuillez recommencer\n");
            }else {
                saisieOk = true;
            }
        }
        saisieOk = false;
        while (!saisieOk) {
            System.out.print("Veuillez entrer la durée de la tache : ");
            duree = entree.hasNextDouble() ? entree.nextDouble() : -1.0;
            if (duree == -1.0) {
                System.out.println("\nERREUR : La durée est invalide. "
                                   + "Veuillez recommencer.\n");
            }else {
                saisieOk = true;
            }
            entree.nextLine();
        }
            try {
                tache = new Tache(nom, description, duree);
                saisieOk = true;
            } catch (IllegalArgumentException erreurConstructeur) {
                System.out.println(erreurConstructeur.getMessage());
                System.out.println("\nErreur : Veuillez recommencer\n");
            }
        return tache;
    }

    /**
     * Menu de modification d'une tache
     * @param projet projet sur lequel on travaille
     * @param tache tache sur laquelle on faut agir
     * @param entree analyseur d'éntrée texte
     */
    public static void modifTache(Projet projet, Tache tache, Scanner entree) {

        int choix = 0;
        boolean repet = true;

        while (repet) {
            effacerConsole();
            System.out.println("\n---------- Modification de tache "
                               + "----------\n");
            System.out.println("---------- " + tache.getNom() 
                               + " ----------\n");
            System.out.println("1 - Modifier la description\n"
                               + "2 - Modifier la durée\n"
                               + "3 - Quitter\n");
            System.out.print("Entrez le chiffre de votre choix : ");
            choix = entree.hasNextInt() ? entree.nextInt() : 0;
            entree.nextLine();

            switch (choix) {
            case 1:
                effacerConsole();
                while (choix != 0) {
                    System.out.println("\n------ Modification de la description "
                                       + "-----");
                    System.out.print("\nVeuillez entrer la description "
                                     + "de la tâche : ");
                    if (entree.hasNextLine()) {
                        try {
                            tache.setDescription(entree.nextLine());
                            choix = 0;
                        }catch (IllegalArgumentException descVide) {
                            System.out.println("\nERREUR : Il y a eu une erreur lors "
                                               + "de la saisie\n");
                        }
                    } else {
                        System.out.println("\nERREUR : Il y a eu une erreur lors "
                                           + "de la saisie\n");
                    }
                }
                break;
            case 2:
                effacerConsole();
                while ( choix != 0) {
                    System.out.println("\n------ Modification de la durée -----");
                    System.out.print("\nVeuillez entrer la durée de la tache : ");
                    if (entree.hasNextDouble()) {
                        try {
                            tache.setDuree(entree.nextDouble());
                            choix = 0;
                        }catch (IllegalArgumentException dureeInvalide) {
                            System.out.println("\nERREUR : Il y a eu une erreur "
                                            + "lors de la saisie\n");
                        }
                    } else {
                        System.out.println("\nERREUR : Il y a eu une erreur "
                                           + "lors de la saisie\n");
                    }
                    entree.nextLine();
                }
                break;
            case 3:
                menuTache(projet, entree, tache);
                repet = false;
                break;
            default:
                System.out.println("\nERREUR : Veuillez choisir un nombre entre "
                                + "1 et 3\n");
                break;
            }
        }
    }


    /** 
     * Affiche toutes les taches du projet et renvoi l'index de la tâche
     * recherché par l'utilisateur
     * @param projet projet sur lequel on agit
     * @param entree analyseur de l'entrée texte
     * @return index de la tache ou -1 si aucune tache n'a été trouvé
     */
    public static int afficheTaches(Projet projet, Scanner entree) {

        String nomDeTache;
        System.out.println("Voici les taches de ce projet :");
        for (int i = 0; i < Projet.nombreTaches(projet); i++) {
            System.out.println("Nom : " + projet.avoirTache(i).getNom() 
                               + " | Description : "
                               + projet.avoirTache(i).getDescription());
        }
        System.out.print("\nVeuillez entrer le nom de la tâche"
                        + " à selectionner : ");               
        nomDeTache = entree.nextLine();

        boolean ok = false;
        int index;
        index = 0;
        if (!nomDeTache.equals("0")) {
            for (index = 0; !ok && index < Projet.nombreTaches(projet); index++) {
                ok = projet.avoirTache(index).getNom().equals(nomDeTache);
            } 
        }

        return ok ? index - 1 : nomDeTache.equals("0") ? -2 : -1;
    }

    /**
     * Efface la console
     */
    private static void effacerConsole() {
        try{
            String systemExploitation = System.getProperty("os.name");
              
            if(systemExploitation.contains("Windows")){        
                new ProcessBuilder("cmd", "/c", "cls").inheritIO()
                                                      .start()
                                                      .waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } 
        }catch(Exception erreurEffacer){
            System.out.println(erreurEffacer);
        }
    }
}





