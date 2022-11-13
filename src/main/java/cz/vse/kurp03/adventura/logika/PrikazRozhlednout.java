package cz.vse.kurp03.adventura.logika;

import java.util.Collection;

/**
 * Třída PrikazRozhlednout implementuje pro hru příkaz rozhlédnout.
 * Tato třída je součástí jednoduché textové hry.
 * Umožní hráči zjistit co a kde se nachází v jeho okolí
 *
 * @author Kurek Pavel
 * @version pro školní rok 2021/2022
 */
public class PrikazRozhlednout implements IPrikaz {

    public static final String NAZEV = "rozhlédnout";

    private SeznamPredmetu seznamPredmetu;
    private HerniPlan plan;
    private SeznamOsob seznamOsob;


    /**
     * Konstruktor třídy
     *
     * @param seznamPredmetu obsahuje předměty hry
     * @param plan           obsahuje mistnosti hry
     * @param seznamOsob     obsahuje postavy hry
     */
    public PrikazRozhlednout(SeznamPredmetu seznamPredmetu, HerniPlan plan, SeznamOsob seznamOsob) {
        this.seznamPredmetu = seznamPredmetu;
        this.plan = plan;
        this.seznamOsob = seznamOsob;
    }


    /**
     * Provádí příkaz "rozhlédnout". Určí v jaké místnosti se hráč nachází a podle toho zobrazí předměty a postavy
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        String seznam = seznamOsob.Vypis().toString() + seznamPredmetu.Vypis().toString();

        return seznam;

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

    public String toString() {
        return getNazev();
    }


}
