package cz.vse.kurp03.adventura.logika;

import java.util.Collection;
import java.util.Collections;

/**
 * Trida Predmet - popisuje jednotlivé předměty
 * Tato třída je součástí jednoduché textové hry.
 * Předměty umožňují hráči posunout se dál
 * @author  Kurek Pavel
 * @version pro školní rok 2021/2022
 */

public class Predmet {

    private String nazev;
    private String popis;
    private String mistnost;


    private Boolean lzeZvednout;
    private Boolean sebrano;


    /**
     * Konstruktor
     */
    public Predmet(String nazev, String popis, String mistnost, Boolean lzeZvednout, Boolean sebrano) {
        this.nazev = nazev;
        this.popis = popis;
        this.mistnost = mistnost;
        this.lzeZvednout = lzeZvednout;
        this.sebrano=sebrano;

    }
    /**
     * Vrací název předmětu
     * @return nazev předmětu
     */
    public String getNazev() {
        return nazev;
    }
    /**
     * Vrací popis předmětu
     * @return popis předmětu
     */

    public String getPopis() {
        return popis;
    }
    /**
     * Vrací místnost kde se předmět nachází
     * @return místnost
     */
    public String getMistnost() {
        return mistnost;
    }

    /**
     * Vrací zda-li lze předmět zvednout
     * @return ano/ne
     */
    public Boolean getLzeZvednout() {
        return lzeZvednout;
    }
    /**
     * Vrací zda byl předmět již zvednut
     * @return ano/ne
     */

    public Boolean getSebrano() {
        return sebrano;
    }
    /**
     * Mění zda-li byl předmět sebrán
     * @param sebrano
     */
    public String toString() {
        return getNazev();
    }

    public void setSebrano(Boolean sebrano) {
        this.sebrano = sebrano;
    }
}
