package cz.vse.kurp03.adventura.logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *  Class SeznamOsob - eviduje seznam postav ve hře .
 *  Umožňuje do hry vložit novou osobu, vyhledat konkrétní osobu a
 *  vypsat osoby
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Kurek Pavel
 *@version    pro školní rok 2021/2022
 */
public class SeznamOsob {
    private List<Osoba>osoby=new ArrayList<>();

    /**
     * Vkládá novou postavu
     *@param  osoba  instance nové postavy
     */
    public void vlozOsobu(Osoba osoba){
        osoby.add(osoba);
    }

    /**
     * Vypisuje postavy podle místa
     *@param  misto  určuje jaké postavy se vypíší
     */
    public Collection<Osoba> Vypis(){



        return Collections.unmodifiableCollection(osoby);
    }

    /**
     * Nalezne konkrétní postavu podle jmena
     *@param  jmeno  určuje hledanou postavu
     * @return hledaná postava
     */
    public Osoba najdiOsobu(String jmeno){

        for (Osoba osoba:osoby) {
            if (osoba.getJmeno().equals(jmeno)){
                return osoba;
            }
        }
        return null;
    }

    /**
     * Nalezne nebezpečnou postavu podle místa
     *@param  misto určuje hledanou postavu
     * @return hledaná postava
     */
    public Osoba najdiNebezpecnouOsobuPodleMista(String misto){

        for (Osoba osoba:osoby) {
            if (osoba.getMistnost().equals(misto)&&osoba.getUtocnost().equals(true)&&osoba.getPopis()!="- mrtvý"){
                return osoba;
            }
        }
        return null;
    }



}
