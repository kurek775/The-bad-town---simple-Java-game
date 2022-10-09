package cz.vse.kurp03.logika;

import cz.vse.kurp03.adventura.logika.Inventar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InventarTest {


    @Test
    public void vloz() {
        Inventar inventar = new Inventar();
        inventar.vloz("pistole");
        inventar.vloz("pistole2");
        inventar.vloz("pistole3");
        inventar.vloz("pistole4");
        inventar.vloz("pistole5");
        assertEquals("Věc byla přidána",inventar.vloz("pistole6"));
        assertEquals("Máte plný batoh",inventar.vloz("pistole7"));
    }

    @Test
    public void najdi() {
        Inventar inventar = new Inventar();
        inventar.vloz("pistole");

        assertEquals(true,inventar.najdi("pistole"));
    }

}