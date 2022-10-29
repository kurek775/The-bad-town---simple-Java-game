package cz.vse.kurp03.adventura.logika;
/**
 *  Třída PrikazInventar implementuje pro hru příkaz inventář.
 *  Tato třída je součástí jednoduché textové hry.
 *  Umožní hráči manipulovat s předměty
 *@author     Kurek Pavel
 *@version    pro školní rok 2021/2022
 */
public class PrikazInventar implements IPrikaz{
    private static final String NAZEV = "inventář";
    private Inventar inventar;

    /**
     *  Konstruktor třídy
     *  @param inventar místo kam se ukládají předměty"
     */
    public PrikazInventar(Inventar inventar) {
        this.inventar = inventar;
    }

    /**
     *  Provádí příkaz "inventář". Zavolá metodu na vypsání inventáře
     *@param parametry
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        inventar.vypis();
        return "------------------------------------";
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
