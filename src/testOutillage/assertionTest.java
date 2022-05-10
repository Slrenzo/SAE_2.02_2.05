/*
 * assertionTest.java                                  27 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package testOutillage;

/** Assertions permettant de tester diff�rentes m�thodes
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class assertionTest {
    
    
    /** m�thodes qui va tester si les arguments de type String sont �gaux
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
}
