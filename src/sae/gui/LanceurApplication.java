/*
 * LanceurApplication.java                                  1 juin 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package sae.gui;

import static sae.pert.Projet.UNITE_TEMPS;

import java.util.Scanner;

import sae.pert.Projet;

/** 
 * Lance une application
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class LanceurApplication {
    
    /** 
     * Un menu pour creer et gerer des projets et qui permet de calculer
     * les dates au plus tot et date au plus tard de chaque tache
     * ainsi que la date au plus tot et la date au plus tard de chaque projet
     * @param args non utlisee
     */
    public static void main(String[] args) {
        Scanner entree = new Scanner(System.in);
        int choix = 0;
        Projet projet = new Projet("initialisation", "initialisation pour la "
                        + "compilation", UNITE_TEMPS[0]);
        boolean projetCharge = false;
        while (choix != -1) {
            if (!projetCharge) {
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
                    projetCharge = true;
                    break;
                case 2:
                    projet = importer();
                    projetCharge = true;
                    break;
                case 3:
                    System.out.println("\nBye");
                    choix = -1;
                    break;
                default:
                    System.out.println("\nVeuillez choisir un nombre entre "
                                    + "1 et 3");
                    choix = 0;
                    break;
                }
            } else {
                System.out.println("\n---------- " + projet.getNom() 
                + " ----------\n");
                System.out.println("1 - Afficher le projet\n"
                                + "2 - Ajouter une tache\n"
                                + "3 - Enlever une tache\n"
                                + "4 - Configurer les taches prealables\n"
                                + "5 - Sauvegarder et retourner au menu\n");
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
                    String nomDeTache;
                    boolean ok = false;
                    System.out.println("Voici les taches de ce projet :");
                    for (int i = 0; i < projet.taches.size(); i++) {
                        System.out.println(projet.taches.get(i).getNom());
                    }
                    System.out.print("Veuillez entrer le nom de la tache que"
                                    + " vous souhaitez enlever : ");
                    nomDeTache = entree.nextLine();
                    for (int i = 0; !ok && i < projet.taches.size(); i++) {
                        ok = projet.taches.get(i).getNom().equals(nomDeTache);
                        if (ok) {
                            projet.enleverTache(projet.taches.get(i));
                            System.out.println("Cette tache a ete supprimer");
                            projet.calculerDateAuPlusTot();
                            projet.calculerDateAuPlusTotFinDeProjet();
                            projet.calculerDateAuPlusTard();
                        }
                    }
                    if (!ok) {
                        System.out.println("Cette tache n'est pas dans "
                                        + "le projet");
                    }
                    break;
                case 4:
                    System.out.println("\n1. Ajouter une tache"
                                    + "\n2. Enlever une tache");
                    System.out.print("Votre choix : ");
                    if (entree.hasNextInt()) {
                        choix = entree.nextInt();
                        if (choix == 1) {
                            ajouterTachePrealable();
                        } else if (choix == 2) {
                            enleverTachePrealable();
                        } else {
                            System.out.println("Vous n'avez pas saisie une "
                                            + "valeur correct");
                        }
                    } else {
                        System.out.println("Vous n'avez pas saisie une valeur "
                                        + "correct");
                    }
                    entree.nextLine();
                    projet.calculerDateAuPlusTot();
                    projet.calculerDateAuPlusTotFinDeProjet();
                    projet.calculerDateAuPlusTard();
                    break;
                case 5:
                    projet.sauvegarder();
                    projetCharge = false;
                    break;
                default:
                    System.out.println("\nVeuillez choisir un nombre entre "
                                    + "1 et 5");
                    choix = 0;
                    break;
                }
            }
        }
    }
}
