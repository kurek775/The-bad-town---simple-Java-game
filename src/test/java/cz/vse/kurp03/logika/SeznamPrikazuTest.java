package cz.vse.kurp03.logika;

import cz.vse.kurp03.adventura.logika.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček, Kurek Pavel
 * @version   pro školní rok 2021/2022
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazZvedni prikazZvedni;
    private Inventar inventar;
    private SeznamOsob seznamOsob;
    private SeznamPredmetu seznamPredmetu;
    private PrikazInterakce prikazInterakce;


    @BeforeEach
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan(),inventar,seznamOsob,hra);
        prikazZvedni=new PrikazZvedni(inventar,seznamPredmetu);
        inventar=new Inventar();
        seznamPredmetu=new SeznamPredmetu(hra.getHerniPlan());
        prikazInterakce = new PrikazInterakce(hra.getHerniPlan(),seznamOsob, hra);

    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prikazInterakce);

        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(null, seznPrikazu.vratPrikaz("nápověda"));
        assertEquals(prikazInterakce, seznPrikazu.vratPrikaz("interakce"));


    }
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prikazZvedni);
        seznPrikazu.vlozPrikaz(prikazInterakce);

        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
        assertEquals(true,seznPrikazu.jePlatnyPrikaz("zvedni"));
        assertEquals(true,seznPrikazu.jePlatnyPrikaz("interakce"));

    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prikazInterakce);

        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
        assertEquals(true, nazvy.contains("interakce"));
        assertEquals(false, nazvy.contains("obchod"));
    }
    
}
