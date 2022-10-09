package cz.vse.kurp03.logika;

import cz.vse.kurp03.adventura.logika.Hra;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Kurek Pavel
 * @version  pro školní rok 2021/2022
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {

        assertEquals("venku", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi hospoda");
        hra1.zpracujPrikaz("jdi venku");

        assertEquals("Dveře jsou zamčené",  hra1.zpracujPrikaz("jdi věznice"));
        hra1.zpracujPrikaz("jdi koloniál");

        assertEquals("Predmet vlozen do inventare",    hra1.zpracujPrikaz("zvedni páčidlo"));
        assertEquals("koloniál", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi venku");


        assertEquals("Dveře byly zamčené, ale podařilo se ti je vypáčit páčidlem. Nacházíš se ve zbrojnici, kde je spoustu zbraní..\n" +
                "můžete jít: venku, ", hra1.zpracujPrikaz("jdi zbrojnice"));
        hra1.zpracujPrikaz("zvedni pistole");
        hra1.zpracujPrikaz("zvedni náboje");

        hra1.zpracujPrikaz("jdi venku");

        assertEquals("Dveře byly zamčené, ale podařilo se ti je vypáčit páčidlem. Nacházíš se ve věznici, která je úplně prázdná. Jsou tu nějaké tajné dveře. Hmm.\n" +
                "můžete jít: venku, dům_desperáda, ",  hra1.zpracujPrikaz("jdi věznice"));
        hra1.zpracujPrikaz("zvedni totem");
        hra1.zpracujPrikaz("jdi venku");

        assertEquals("Dveře byly zamčené, ale podařilo se ti je vypáčit páčidlem. Právě si vešel na nepřátelské území a útočí na tebe Farář_Jan_Sienkiewicz\n" +
                " Vyhrál si souboj s Farář_Jan_Sienkiewicz\n" +
                "Nacházíš se vkrásném dřevěném kostele, kde je k oltáři přivázaná osůbka, která volá o pomoc..\n" +
                "můžete jít: venku, ",  hra1.zpracujPrikaz("jdi kostel"));
        assertEquals("Zachránil jsi náčelníkovu dceru. VÝHRAAA",  hra1.zpracujPrikaz("interakce Dcera_náčelníka"));



    }
}
