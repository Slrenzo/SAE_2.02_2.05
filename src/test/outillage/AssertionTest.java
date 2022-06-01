/*
 * assertionTest.java                                  27 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package test.outillage;

/** Assertions permettant de tester diff�rentes m�thodes
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class AssertionTest {
    
    
    /** m�thodes qui va tester si les arguments de type String sont égaux
     * @param attendu
     * @param obtenu
     * @return true si obtenu �quivalent � attendu, false sinon.
     */
    public static boolean assurerEgalite(String attendu, String obtenu) {
        return attendu.equals(obtenu);
    }
    
    /** m�thodes qui va tester si les arguments de type double sont �gaux
     * @param attendu
     * @param obtenu
     * @return true si obtenu �quivalent � attendu, false sinon.
     */
    public static boolean assurerEgaliteDouble(double attendu, double obtenu) {
        if(attendu == obtenu) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * méthodes qui va tester si les argument de type String ne sont pas égaux
     * @param attendu
     * @param obtenu
     * @return true si obtenu différents d'attendu, false sinon
     */
    public static boolean assurerNonEgalite(String attendu, String obtenu) {
        if(attendu != obtenu) {
            return true;
        } else {
            return false;
        }
        
    }
    
    /**
     * méthodes qui va tester si les argument de type double ne sont pas égaux
     * @param attendu
     * @param obtenu
     * @return true si obtenu différents d'attendu, false sinon
     */
    public static boolean assurerNonEgaliteDouble(double attendu, double obtenu) {
        if(attendu != obtenu) {
            return true;
        } else {
            return false;
        }
        
    }
}