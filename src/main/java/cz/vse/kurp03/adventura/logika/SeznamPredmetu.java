package cz.vse.kurp03.adventura.logika;

import java.util.ArrayList;
import java.util.List;

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
    private List<Predmet>predmet=new ArrayList<>();
    private HerniPlan plan;

    /**
     * Konstruktor
     */
    public SeznamPredmetu(HerniPlan plan) {
        this.plan = plan;
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
    public Boolean najdiPredmet(String nazev){

        for (Predmet pr:predmet) {
            if (pr.getNazev().equals(nazev)&&pr.getMistnost().equals(plan.getAktualniProstor().getNazev())&&pr.getLzeZvednout().equals(true)&&pr.getSebrano()==false){
                pr.setSebrano(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Vypíše předměty na dané lokaci a kontroluje zda již nebyly zvednuty
     */
    public void Vypis( ){
        for (Predmet pr:predmet) {
            if (pr.getMistnost().equals(plan.getAktualniProstor().getNazev())){
                if (pr.getSebrano().equals(false)){
                    System.out.println(pr.getNazev()+" "+pr.getPopis());
                }
            }
        }
    }


}
