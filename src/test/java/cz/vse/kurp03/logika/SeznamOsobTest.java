package cz.vse.kurp03.logika;
/*******************************************************************************
 * Testovací třída SeznamOsobTest slouží k otestování funkcí dané třídy
 * @author    Kurek Pavel
 * @version   pro skolní rok 2021/2022
 */
import cz.vse.kurp03.adventura.logika.Osoba;
import cz.vse.kurp03.adventura.logika.SeznamOsob;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeznamOsobTest {

    private SeznamOsob seznamOsob;
    @Test
    public void vlozOsobu() {
        SeznamOsob seznamOsob = new SeznamOsob();
        seznamOsob.vlozOsobu(new Osoba("Juan","- naše hlavní postava","kostel",false));
    }

    @Test
    public void najdiOsobu() {
        SeznamOsob seznamOsob = new SeznamOsob();
        seznamOsob.vlozOsobu(new Osoba("Juan","- naše hlavní postava","kostel",false));
        assertEquals("Juan",seznamOsob.najdiOsobu("Juan").getJmeno());
    }
}