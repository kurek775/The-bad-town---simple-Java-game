package cz.vse.kurp03.adventura.logika;
/**
 * Trida Osoba - popisuje jednotlivé osoby
 * Tato třída je součástí jednoduché textové hry.
 * Osoby pomáhají nebo brání hráči
 * @author  Kurek Pavel
 * @version pro školní rok 2021/2022
 */
public class Osoba {
    private String jmeno;
    private String popis;
    private String mistnost;
private Boolean utocnost;


    /**
     * Konstruktor
     */
    public Osoba(String jmeno, String popis, String mistnost, Boolean utocnost) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.mistnost = mistnost;
        this.utocnost = utocnost;


    }
    /**
     * Vrací jmeno osoby
     * @return jmeno osoby
     */
    public String getJmeno() {
        return jmeno;
    }
    /**
     * Vrací popis osoby
     * @return popis osoby
     */
    public String getPopis() {
        return popis;
    }
    /**
     * Vrací místnost,kde se osoba nalézá
     * @return  místnost
     */
    public String getMistnost() {
        return mistnost;
    }

    /**
     * Vrací útočnost postavy
     * @return  místnost
     */
    public Boolean getUtocnost() {
        return utocnost;
    }

    /**
     * Nastavuje útočnost osoby
     * @param  utocnost
     */
    public void setUtocnost(Boolean utocnost) {
        this.utocnost = utocnost;
    }




    /**
     * Nastavuje popis osoby
     * @param  popis
     */
    public void setPopis(String popis) {
        this.popis = popis;
    }
}
