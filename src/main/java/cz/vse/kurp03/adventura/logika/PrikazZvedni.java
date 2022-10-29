package cz.vse.kurp03.adventura.logika;

/**
 *  Třída PrikazZvedni implementuje pro hru příkaz zvedni.
 *  Tato třída je součástí jednoduché textové hry.
 *  Umožní hráči zvedat předměty
 *@author     Kurek Pavel
 *@version    pro školní rok 2021/2022
 */

public class PrikazZvedni implements IPrikaz{

    private static final String NAZEV = "zvedni";
    private Inventar inventar;
    private SeznamPredmetu seznamPredmetu;

    /**
     *  Konstruktor třídy
     *  @param inventar místo kam se ukládají předměty"
     *  @param seznamPredmetu obsahuje předměty ve hře
     */
    public PrikazZvedni(Inventar inventar, SeznamPredmetu seznamPredmetu) {
        this.inventar = inventar;
        this.seznamPredmetu = seznamPredmetu;
    }


    /**
     *  Provádí příkaz "zvedni". Zkouší zda lze předmět zvednout a zda je dost místa v inventáři.
     *  Pokud ano přidá předmět do inventáře.
     *  Jinak vráti chybovou hlášku (Takový předmět tu není nebo ho neuzvedneš)
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0) {
            return "Nebyl zadán předmět";
        }
        if (inventar.obsah()>=5){
            return "Máš plný batoh, kapsy prostě všechno";
        }
        if (seznamPredmetu.najdiPredmet(parametry[0])==true){
            inventar.vloz(parametry[0]);
            return "Predmet vlozen do inventare";
        }else{
            return "Takový předmět tu není nebo ho neuzvedneš";
        }



    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
