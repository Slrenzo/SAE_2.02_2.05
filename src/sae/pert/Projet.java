/*
 * Projet.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */
package sae.pert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * Un projet est un ensemble de taches que l'on doit ordonnee pour arriver a
 * un objectif.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Projet {

    /* Nom du projet */
    private String nom;

    /* Description du projet */
    private String description;

    /* Listes des taches a effectuer dans pour ce projet */
    private ArrayList<Tache> taches;

    /* Unite de temps utilise pour parler des durees des taches */
    private String uniteTemps;

    /* Date au plus tot du projet */
    private double dateAuPlusTotProjet;

    /* Date au plus tard du projet */
    private double dateAuPlusTardProjet;    


    /**
     * Tableau d'unite de temps possible 
     */
    public final static String[] UNITE_TEMPS = { 
                    "Minute(s)",
                    "Heure(s)",
                    "Jour(s)",
                    "Semaine(s)",
                    "Mois",
                    "Annee(s)"
    };

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
                if (entree.hasNextInt()) {
                    choix = entree.nextInt();
                } else {
                    choix = 0;
                }
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
                if (entree.hasNextInt()) {
                    choix = entree.nextInt();
                } else {
                    choix = 0;
                }
                entree.nextLine();
                switch (choix) {
                case 1: 
                    System.out.println();
                    System.out.println(projet.toString());
                    System.out.println();
                    break;
                case 2:
                    try {
                        projet.ajouterTache(creerTache());
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
                            projet.ajouterTachePrealable();
                        } else if (choix == 2) {
                            projet.enleverTachePrealable();
                        } else {
                            System.out.println("Vous n'avez pas saisie une "
                                            + "valeur corect");
                        }
                    } else {
                        System.out.println("Vous n'avez pas saisie une valeur "
                                        + "corect");
                    }
                    entree.nextLine();
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

    /**
     * D finition d'un projet qui possede un nom 
     * et qui possede une unite de temps
     * @param nom le nom du projet
     * @param description description du projet
     * @param uniteTemps l'unite de temps d fini pour ce projet
     * @throws IllegalArgumentException si le nom est vide
     * @throws IllegalArgumentException si la description est vide
     * @throws IllegalArgumentException si l'unite de temps est invalide
     */
    public Projet(String nom, String description, String uniteTemps) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est vide");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        boolean uniteTempsOk = false;
        for (int i = 0; !uniteTempsOk && i < UNITE_TEMPS.length; i++) {
            uniteTempsOk = uniteTemps.equals(UNITE_TEMPS[i]);
        }
        if (!uniteTempsOk) {
            throw new IllegalArgumentException("L'unite de temps est invalide");
        }
        this.nom = nom;
        this.description = description;
        this.uniteTemps = uniteTemps;
        this.taches = new ArrayList<Tache>();
        this.dateAuPlusTotProjet = 0.0;
        this.dateAuPlusTardProjet = 0.0;
    }

    /**
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return description de ce projet
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description modifie la description de ce projet
     * @throws IllegalArgumentException si la description est vide
     */
    public void setDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        this.description = description;
    }

    /**
     * @return uniteTemps du projet 
     */
    public String getUniteTemps() {
        return uniteTemps;
    }

    /**
     * @return dateAuPlusTotProjet de ce projet
     */
    public double getDateAuPlusTotProjet() {
        return dateAuPlusTotProjet;
    }

    /**
     * @return dateAuPlusTardProjet de ce projet
     */
    public double getDateAuPlusTardProjet() {
        return dateAuPlusTardProjet;
    }

    /**
     * @return taches les taches de ce projet
     */
    public ArrayList<Tache> getTaches() {
        ArrayList<Tache> cloneTaches = new ArrayList<Tache>(0);
        Tache cloneTache;
        ArrayList<Tache> cloneTachesPrealables;
        for (int i = 0; i < this.taches.size(); i++) {
            cloneTache = new Tache(this.taches.get(i).getNom(),
                                   this.taches.get(i).getDescription(),
                                   this.taches.get(i).getDuree());
            cloneTache.setDateAuPlusTot(this.taches.get(i).getDateAuPlusTot());
            cloneTache.setDateAuPlusTard(this.taches.get(i)
                                         .getDateAuPlusTard());
            cloneTachesPrealables = this.taches.get(i).getTachesPrealables();
            for (int j = 0; j < cloneTachesPrealables.size(); j++) {
                cloneTache.ajouterTachePrealable(cloneTachesPrealables.get(j));
            }
            cloneTaches.add(cloneTache);
        }
        return cloneTaches;
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
            if (entree.hasNextDouble()) {
                duree = entree.nextDouble();
            } else {
                duree = -1.0;
            }
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

    /** 
     * Ajoute une tache a ce projet
     * @param tacheAAjouter la tache a ajouter au projet
     * @throws IllegalArgumentException si la tache est deja dans le projet
     */
    public void ajouterTache(Tache tacheAAjouter) {
        for (int i = 0; i < this.taches.size(); i++) {
            if (tacheAAjouter.getNom().equals(this.taches.get(i).getNom())) {
                throw new IllegalArgumentException("Cette tache est deja "
                                + "presente");
            }
        }
        this.taches.add(tacheAAjouter);
    }

    /** 
     * Enleve une tache a ce projet
     * @param tacheAEnlever la tache a enlever au projet
     * @throws IllegalArgumentException si la tache n'est pas dans le projet
     */
    public void enleverTache(Tache tacheAEnlever) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.taches.size(); i++) {
            estPresent = this.taches.get(i).equals(tacheAEnlever);
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tache n'est pas dans "
                            + "le projet");
        }
        this.taches.remove(tacheAEnlever);
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
                                    if (uniteTempsChoisieFaux) {
                                        System.out.println("Veuillez entrer un nombre "
                                                        + "entre un 1 et 6 : ");                        
                                    }
                } else {
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
     * Sauvegarde dans un fichier les informations du fichier
     */
    public void sauvegarder() {
     
        File file = new File("sauv.txt");
        FileWriter fichier;
        
        try {
            fichier = new FileWriter(file);
            fichier.write( taches.size() + "\n" + this.nom + "\n"
                    + this.description + "\n" 
                    + this.uniteTemps + "\n" 
                    + this.dateAuPlusTotProjet + "\n" 
                    + this.dateAuPlusTardProjet + "\n" 
                    + "\n" + tacheToString()
                    + "\n" + tachePrealableToString()
                    );

            fichier.close();
        } catch (IOException erreur) {
            System.out.println("erreur");
        }
    }

    /** 
     *  Récupere le nom des taches préalables a aux differentes taches du projet
     */
    private String tachePrealableToString() {
        String nomTachesPrealable = "";
        for (int i = 0; i < taches.size(); i++) {
            
            nomTachesPrealable += taches.get(i).getNom() + "\n"
                                  + taches.get(i).getTachesPrealables().size()
                                  + "\n";
            for (int index = 0; 
                 index < taches.get(i).getTachesPrealables().size(); 
                 index++) {
                nomTachesPrealable += taches.get(i).getTachesPrealables()
                                .get(index).getNom() + "\n";
            }
        }
        return nomTachesPrealable;
        
    }
    
    /**
     * Récupere toutes les informations d'une tâche et les mets en forme
     */
    private String tacheToString() {
        String listeTaches = "";
        for (int i = 0; i < taches.size(); i++) {
            listeTaches += taches.get(i).getNom() + "\n" 
                            + taches.get(i).getDescription() + "\n"
                            + taches.get(i).getDuree() + "\n"
                            + taches.get(i).getDateAuPlusTot() + "\n"
                            + taches.get(i).getDateAuPlusTard() + "\n";
        }
        return listeTaches;
    }
    
    /**
     * importer dans un fichier les informations du fichier
     * @return projet
     */
    public static Projet importer() {

        String[] projetInfo = new String[6];
        String nomTache ="";
        String nomTachePrea ="";
        int indexTache = 0;
        int nbTachesPrealable = 0;
        boolean trouve;  
        Projet projet = new Projet("nom","desc","Mois");
        Tache tache = new Tache("nom", "desc", 2.0);
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("sauv.txt"));
            String line = reader.readLine();
            /** info du projet */
            for (int index = 0; index < 6; index++) {
                projetInfo[index] = line;
                line = reader.readLine();
            }
            projet = new Projet (projetInfo[1], projetInfo[2], projetInfo[3]);
            projet.dateAuPlusTotProjet = Double.parseDouble(projetInfo[4]);
            projet.dateAuPlusTardProjet = Double.parseDouble(projetInfo[5]);
            // Taches du projets
            for (int i = 0; i < Integer.parseInt(projetInfo[0]); i++) {
                tache = new Tache(reader.readLine(),reader.readLine(),
                                Double.parseDouble(reader.readLine()));
                tache.setDateAuPlusTot(Double.parseDouble(reader.readLine()));
                tache.setDateAuPlusTard(Double.parseDouble(reader.readLine()));
                projet.ajouterTache(tache);
            }
            reader.readLine(); // saut de ligne
            
            for (int i = 0; i < Integer.parseInt(projetInfo[0]); i++) {
                // Tache à ajouter les prealables
                nomTache = reader.readLine();
    
                trouve = false;
                for (indexTache = 0; 
                     !trouve && indexTache < projet.taches.size(); indexTache++) {
                    trouve = projet.taches.get(indexTache).getNom().equals(nomTache);
                }
                if (trouve) {
                    tache = projet.taches.get(indexTache - 1);
                }
                                      
                nbTachesPrealable = Integer.parseInt(reader.readLine());
                for (int index = 0; index < nbTachesPrealable; index++) {
                    nomTachePrea = reader.readLine();
                    trouve = false;
                    for (indexTache = 0; 
                              !trouve && indexTache < projet.taches.size(); indexTache++) {
                        trouve = projet.taches.get(indexTache).getNom().equals(nomTachePrea);
                    }
                    if (trouve) {
                        tache.ajouterTachePrealable(projet.taches.get(indexTache - 1));
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("erreur");
        }

        return projet;
    }
    
    /** 
     * Permet d'ajouter les taches prealables du projet
     */
    public void ajouterTachePrealable() {
        boolean retour = false;
        boolean ok = false;
        String nomTache;
        String nomTacheAAjouter;
        Tache tache = new Tache("nom", "description", 0.0);
        Tache tacheAAjouter = new Tache("nom", "description", 0.0);
        Scanner entree = new Scanner(System.in);
        do {
            System.out.println("\nTapez 0 pour revenir au menu du projet,"
                            + " ou choisissez une tache a configurer :");
            for (int i = 0; i < this.taches.size(); i++) {
                System.out.println(this.taches.get(i).getNom());
            }
            System.out.print("Choisissez une tache : ");
            nomTache = entree.nextLine();
            ok = false;
            for (int i = 0; !ok && i < this.taches.size(); i++) {
                ok = nomTache.equals(this.taches.get(i).getNom());
                if (ok) {
                    tache = this.taches.get(i);
                }
            }
            if (nomTache.startsWith("0")) {
                System.out.println("Retour au projet\n");
                retour = true;
            } else if (!ok) {
                System.out.println("Cette tache n'est pas dans le projet");
            } else {
                System.out.println("Choisissez la tache a ajouter au tache "
                                + "prealable de " + tache.getNom());
                for (int i = 0; i < this.taches.size(); i++) {
                    if (!nomTache.equals(this.taches.get(i).getNom())
                                    || !tache.getTachesPrealables()
                                    .contains(this.taches.get(i))) {
                        System.out.println(this.taches.get(i).getNom());
                    }
                }
                System.out.print("Choisissez une tache : ");
                nomTacheAAjouter = entree.nextLine();
                ok = false;
                for (int i = 0; !ok && i < this.taches.size(); i++) {
                    ok = nomTacheAAjouter.equals(this.taches.get(i).getNom());
                    if (ok) {
                        tacheAAjouter = this.taches.get(i);
                        try {
                            tache.ajouterTachePrealable(tacheAAjouter);
                            if (this.aUnCircuit()) {
                                System.out.println("Cette tache ne peut pas "
                                                + "etre ajouter aux taches "
                                                + "prealables de " 
                                                + nomTache
                                                + " car il y aura des "
                                                + "circuits");
                                tache.enleverTachePrealable(tacheAAjouter);
                            } else {
                                System.out.println("Cette tache a ete ajouter");
                            }
                        } catch (IllegalArgumentException erreurDeSaisie) {
                            System.out.println(erreurDeSaisie.getMessage());
                        }
                    }
                }
                if (!ok) {
                    System.out.println("Cette tache n'existe pas");
                }
            }
        } while (!retour);
    }

    /** 
     * Permet d'enlever les taches prealables du projet
     */
    public void enleverTachePrealable() {
        boolean retour = false;
        boolean ok = false;
        String nomTache;
        String nomTacheAEnlever;
        Tache tache = new Tache("nom", "description", 0.0);
        Tache tacheAEnlever = new Tache("nom", "description", 0.0);
        Scanner entree = new Scanner(System.in);
        do {
            System.out.println("\nTapez 0 pour revenir au menu du projet,"
                            + " ou choisissez une tache a configurer :");
            for (int i = 0; i < this.taches.size(); i++) {
                System.out.println(this.taches.get(i).getNom());
            }
            System.out.print("Choisissez une tache : ");
            nomTache = entree.nextLine();
            for (int i = 0; !ok && i < this.taches.size(); i++) {
                ok = nomTache.equals(this.taches.get(i).getNom());
                if (ok) {
                    tache = this.taches.get(i);
                }
            }
            if (nomTache.startsWith("0")) {
                System.out.println("Retour au projet\n");
                retour = true;
            } else if (!ok) {
                System.out.println("Cette tache n'est pas dans le projet");
            } else if (tache.getTachesPrealables().size() == 0) {
                System.out.println("Cette tache n'a pas de tache prealable");
            } else {
                System.out.println("Choisissez une tache prealable "
                                + "a enlever :");
                for (int i = 0; i < tache.getTachesPrealables().size(); i++) {
                    System.out.println(tache.getTachesPrealables()
                                    .get(i).getNom());
                }
                System.out.println("Choisissez une tache :");
                nomTacheAEnlever = entree.nextLine();
                ok = false;
                for (int i = 0; !ok && i < tache.getTachesPrealables().size()
                                ; i++) {
                    ok = nomTacheAEnlever.equals(
                                    tache.getTachesPrealables().get(i).getNom()
                                    );
                    if (ok) {
                        tacheAEnlever = tache.getTachesPrealables().get(i);
                        try {
                            tache.enleverTachePrealable(tacheAEnlever);
                        } catch (IllegalArgumentException erreurDeSaisie) {
                            System.out.println(erreurDeSaisie.getMessage());
                        }
                    }
                }
                if (!ok) {
                    System.out.println("Ceci n'est pas une tache prealable");
                }
            }
        } while (!retour);
    }

    /** 
     * teste si le projet possede un circuit
     * @return true s'il y a un circuit
     */
    public boolean aUnCircuit() {
        // TODO coder l'algo qui teste s'il y a un circuit
        return false; //stub
    }

    @Override
    public String toString() {
        String taches = this.taches.size() == 0 ? "Ce projet ne contient pas "
                        + "encore de tache" : "Ses taches sont : \n";
        for (int i = 0; i < this.taches.size(); i++) {
            taches += this.taches.get(i) + "\n";
        }
        return "Ce projet est nomme : " + this.nom + "\n" 
        + "Sa description est : " + this.description + "\n"
        + "Son unite de temps est : " + this.uniteTemps + "\n" 
        + taches + "\n"
        + "Sa date au plus tot est : " + this.dateAuPlusTotProjet + "\n" 
        + "Sa date au plus tard est : " + this.dateAuPlusTardProjet;
    }

}
