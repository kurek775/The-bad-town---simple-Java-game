package cz.vse.kurp03.adventura.logika;
import java.util.Random;
/**
 *  Třída PrikazZautoc implementuje pro hru příkaz zaútoč.
 *  Tato třída je součástí jednoduché textové hry.
 *  Umožní hráči zaútočit na libovolnou postavu
 *  Útok však může mít nedozírné následky jako hopodskou šarvátku, kde může naše postava zemřít
 *@author     Kurek Pavel
 *@version    pro školní rok 2021/2022
 */

public class PrikazZautoc implements IPrikaz{
    private static final String NAZEV = "zaútoč";
    private HerniPlan plan;
    private Inventar inventar;
    private SeznamOsob seznamOsob;
    private Hra hra;

    /**
     *  Konstruktor třídy
     *  @param inventar místo kam se ukládají předměty"
     *  @param plan obsahuje mistnosti hry
     *  @param seznamOsob obsahuje postavy hry
     *  @param hra vytváří hru
     */
    public PrikazZautoc(HerniPlan plan,Inventar inventar,SeznamOsob seznamOsob,Hra hra) {
        this.plan = plan;
        this.inventar=inventar;
        this.seznamOsob=seznamOsob;
        this.hra=hra;
    }


    /**
     *  Provádí příkaz "zaútoč". Pokud má hráč zbraň a náboje tak probíhá útok generováním náhodných čísel a hráč může prohrát i vyhrát.
     *  Pokud má hráč navíc i totem tak automaticky vyhrává.
     *  Pokud hráč nemá zbraň a náboje tak okamžitě podlehne
     *@param parametry - jako  parametr obsahuje cíl útoku
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {


        if (parametry.length==0){
            return "Neplatný příkaz";
        }
        String promena = parametry[0];
        if(seznamOsob.najdiOsobu(promena)==null){
            return "Špatně zadané jméno nebo je osoba jinde. Zkus se rozhlédnout";
        }
        if (seznamOsob.najdiOsobu(promena).getPopis().equals("- mrtvý")){
            return "Mrtvolu už asi nezabiješ.\n Co myslíš ?";

        }


            if ((inventar.najdi("pistole").equals(true) ||inventar.najdi("puška").equals(true))&&inventar.najdi("náboje").equals(true)){
Random rand = new Random();
                    int cislo_hrace=rand.nextInt(6);
               int cislo_postavy= rand.nextInt(6);

        while (cislo_hrace == cislo_postavy){
             cislo_hrace= rand.nextInt(6);
             cislo_postavy= rand.nextInt(6);
        }
        if (cislo_hrace > cislo_postavy||inventar.najdi("totem").equals(true)){
            seznamOsob.najdiOsobu(promena).setPopis("- mrtvý");


            if (plan.getAktualniProstor().getNazev().equals("hospoda")){
            return   hospodskaSarvatka();

        }
else{
    return "Zabil si " + promena;
            }
        }
        if(cislo_hrace < cislo_postavy){
            hra.setKonecHry(true);
            return "Prohrál si férový duel ať je ti země lehká kovboji !!";
        }

            }
           else{
                hra.setKonecHry(true);
                return " Duel ani nezačal byl jsi ztrestán na místě. \n Nedělej problémy dokud si nenajdeš zbraň a náboje !!";
            }

  return "----";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     *  Metoda je volána pokud duel proběhne v hospodě
     *  Následuje šarvátka, kde může náš hrdina přežít nebo zemřít.
     *  @ return nazev prikazu
     */
    public String hospodskaSarvatka() {


            Random rand = new Random();

          int cislo_hrace=rand.nextInt(10);
                int cislo_postavy= rand.nextInt(10);
             while (cislo_hrace == cislo_postavy){
                cislo_hrace= rand.nextInt(10);
                 cislo_postavy= rand.nextInt(10);
           }
            if (cislo_hrace > cislo_postavy||inventar.najdi("totem").equals(true)){

                seznamOsob.najdiOsobu("Pistolník_Diego_Sanchez").setPopis("- mrtvý");
                seznamOsob.najdiOsobu("Hospodský_Willy").setPopis("- mrtvý");
                seznamOsob.najdiOsobu("Šerif_James_Freeman").setPopis("- mrtvý");
                seznamOsob.najdiOsobu("Rváč_Billy_Red").setPopis("- mrtvý");
inventar.vloz("klíč_k_desperadovu_domu");
return "Váš duel,který si vyhrál vyvolal hospodskou bitku, kterou jsi přežil \n Pár postav zemřelo \n Také jsi získal klíč k desperádovu domu.";

            }
     else{
                hra.setKonecHry(true);
                return "Váš duel, který si vyhrál vyvolal hospodskou bitku, kterou si nepřežil " ;

            }



            }





        }
