package cz.vse.kurp03.adventura.logika;
/**
 *  Třída PrikazKdedcera implementuje pro hru příkaz kde_dcera.
 *  Tato třída je součástí jednoduché textové hry.
 *  Umožní hráči zeptat se postav jestli mu neporadí, kde je dcera.
 *  Či zda mu, alespon nenapoví.
 *  Příkaz povede k několika podúkolům.
 *@author     Kurek Pavel
 *@version    pro školní rok 2021/2022
 */
public class PrikazKdedcera implements IPrikaz{
    private static final String NAZEV = "kde_dcera";
    private HerniPlan plan;
    private SeznamOsob seznamOsob;

    private Hra hra;
    private Inventar inventar;

    /**
     *  Konstruktor třídy
     * @param plan herní plán, ve kterém se bude ve hře "chodit
     */
    public PrikazKdedcera(HerniPlan plan, SeznamOsob seznamOsob, Hra hra, Inventar inventar) {
        this.plan = plan;
        this.seznamOsob= seznamOsob;
        this.hra = hra;
        this.inventar = inventar;
    }

    /**
     *  Provádí příkaz "kde_dcera". Umožní získat informace od postav z hry
     *@param parametry určuje postavu
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
            return "S mrtvolou si toho moc nepovíš\n";

        }
        if (plan.getAktualniProstor().getNazev().equals("hospoda")&&promena.equals("Rváč_Billy_Red")){
            if(seznamOsob.najdiOsobu(promena).getPopis().equals("- hodně naštvanej")){

                hra.setKonecHry(true);
                return "Tak fajn poslední kapka. Teď tě čeká rychta\n Rváč tě umlátil k smrti.";
            }
            else{
                seznamOsob.najdiOsobu(promena).setPopis("- hodně naštvanej");
                return "Ptáš se na hloupé otázky. Dej si vodchod jinak to skončí špatně\n";

            }

        }

        if (plan.getAktualniProstor().getNazev().equals("hospoda")&&promena.equals("Hospodský_Willy")){


            return "Nic nevím a být tebou tak bych se pakoval. Jdi někam jinam třeba do koloniálu";

        }
        if (plan.getAktualniProstor().getNazev().equals("hospoda")&&promena.equals("Šerif_James_Freeman")){


            return "V mém městě indiánka ? TO NENÍ MOŽNÉ";

        }
        if (plan.getAktualniProstor().getNazev().equals("venku")&&promena.equals("Lovec_lidí_Jon_Red")){


            return "V tom má ruce Gauner_Rodrigo_Desperado ten má spadeno na indiány";

        }
        if (plan.getAktualniProstor().getNazev().equals("kostel")&&promena.equals("Dcera_náčelníka")){


            return "No asi se mě nemusíš ptát, kde jsem radši mě pojď odvázat !!!!!";

        }


        if (plan.getAktualniProstor().getNazev().equals("hospoda")&&promena.equals("Pistolník_Diego_Sanchez")){
          if(seznamOsob.najdiOsobu(promena).getPopis().equals("- zadal nám úkol")&&inventar.najdi("puška").equals(true))
          {

              inventar.vymaz("puška");
              inventar.vloz("klíč_k_desperadovu_domu");
              seznamOsob.najdiOsobu(promena).setPopis("- splnili jsme úkol už nám nemá co říct");
              return "Děkuji za pušku snad ti ten klíč bude k něčemu.";
          }
          else  if(seznamOsob.najdiOsobu(promena).getPopis().equals("- zadal nám úkol")&&inventar.najdi("puška").equals(false))
            {
                return "Své jsem ti už řekl. Tak mi sežeň tu pušku.";
            }
          else if(seznamOsob.najdiOsobu(promena).getPopis().equals("- férový pistolník, který něco ví")){


              seznamOsob.najdiOsobu(promena).setPopis("- zadal nám úkol");
              return "No jako nic moc nevím, ale mám u sebe klíč k domu Desperada.\n a ten s tím něco společného má.\n Dám ti ho, když pro mě ukradneš pušku ze zbrojnice.";



          }

        }

        return "-----------------";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
