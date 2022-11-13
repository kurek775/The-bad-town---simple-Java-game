package cz.vse.kurp03.adventura.logika;

/**
 * Třída PrikazZahod implementuje pro hru příkaz zahod.
 * Tato třída je součástí jednoduché textové hry.
 * Umožní hráči vyhodit předmět z inventáře
 *
 * @author Kurek Pavel
 * @version pro školní rok 2021/2022
 */

public class PrikazZahod implements IPrikaz {

    private static final String NAZEV = "zahod";
    private Inventar inventar;
    private SeznamPredmetu seznamPredmetu;

    /**
     * Konstruktor třídy
     *
     * @param inventar       místo kam se ukládají předměty"
     * @param seznamPredmetu obsahuje předměty ve hře
     */
    public PrikazZahod(Inventar inventar, SeznamPredmetu seznamPredmetu) {
        this.inventar = inventar;
        this.seznamPredmetu = seznamPredmetu;
    }


    /**
     * Provádí příkaz "zahod". Zkouší zda předmět máme v inventáři
     * pokud ano zahodí ho
     * Jinak vráti chybovou hlášku (Takový předmět tu není )
     *
     * @param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0) {
            return "Nebyl zadán předmět";
        }

        if (inventar.najdi(parametry[0]) == true) {
            inventar.vymaz(parametry[0]);
            seznamPredmetu.dropItem(parametry[0]);
            return "Predmět jsi zahodil";
        } else {
            return "Takový předmět tu není ";
        }


    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
