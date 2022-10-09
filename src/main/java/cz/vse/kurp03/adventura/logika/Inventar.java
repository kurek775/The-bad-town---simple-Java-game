package cz.vse.kurp03.adventura.logika;

/**
 * Trida Inventar - uchovává oředměty
 * Tato třída je součástí jednoduché textové hry.
 * @author  Kurek Pavel
 * @version pro školní rok 2021/2022
 */
import java.util.ArrayList;
import java.util.List;

public class Inventar {
    private   List<String> list;
    public Inventar(){this.list=new ArrayList<>();}


    /**
     * vkládá do inventáře
     * @param  vec je vlozena
     */
    public String vloz(String vec){
        if(list.size()>5){
            return "Máte plný batoh";
        }
        else{
            list.add(vec);
            return "Věc byla přidána";
        }

    }
    /**
     * vypisuje inventář
     */
    public void vypis(){
        for (String s:list) {
            System.out.println(s);
        }
    }
    /**
     * vyhledává v inventáři na základě popisu
     * @param  popis
     */
    public Boolean najdi(String popis){
        for (String s:list) {
            if (s.equals(popis)){
                return true;
            }
        }
        return false;
    }
    /**
     * vrací počet instancí v listu
     * @return   velikost inventáře
     */
    public Integer obsah(){
        return list.size();
    }
    /**
     * maže z inventáře
     * @param  co je odstraněn
     */
    public void vymaz(String co){
       for (int i=0;i<=list.size();i++){
           if (list.get(i).equals(co)){
               list.remove(i);

               break;
           }
       }
    }
}
