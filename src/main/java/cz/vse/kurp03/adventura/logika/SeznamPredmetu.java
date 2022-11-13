package cz.vse.kurp03.adventura.logika;

import java.util.*;

/**
 *  Class SeznamPredmetu - eviduje seznam předmětů adventury.
 *  Umožňuje do hry vložit nový předmět. Nalétz konkrétní předmět a vypsat předměty.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Kurek Pavel
 *@version    pro školní rok 2021/2022
 */
public class SeznamPredmetu {
    private Set<Predmet> predmet;
    private HerniPlan plan;

    /**
     * Konstruktor
     */
    public SeznamPredmetu(HerniPlan plan) {
        this.plan = plan;
        predmet = new HashSet<>();
    }


    /**
     * Vkládá nový předmět.
     *
     *@param  pr  Instance nově vytvořeného předmětu
     */
    public void vlozPredmet(Predmet pr){
        predmet.add(pr);
    }

    /**
     * Hledá konkrétní předmět
     *
     *@param  nazev  parametr, podle kterého metoda hledá předmět
     * @return zda-li lze předmět zvednout
     */
    public Predmet najdiPredmet(String nazev){

        for (Predmet pr:predmet) {
            if (pr.getNazev().equals(nazev)&&pr.getMistnost().equals(plan.getAktualniProstor().getNazev())){

                return pr;
            }
        }
        return null;
    }
    public void dropItem(String nazev){

        for (Predmet pr:predmet) {
            if (pr.getNazev().equals(nazev)){
                pr.setSebrano(false);
                pr.setMistnost(plan.getAktualniProstor().getNazev());

            }
        }

    }
    /**
     * Vypíše předměty na dané lokaci a kontroluje zda již nebyly zvednuty
     */

    public Collection<Predmet> Vypis(){

        return Collections.unmodifiableCollection(predmet);
    }

}
