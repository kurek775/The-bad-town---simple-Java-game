package cz.vse.kurp03.adventura.logika;
/**
 *  Třída PrikazInterakce implementuje pro hru příkaz interakce.
 *  Tato třída je součástí jednoduché textové hry.
 *  Umožní hráči komunikovat s postavami
 *@author     Kurek Pavel
 *@version    pro školní rok 2021/2022
 */
public class PrikazInterakce implements IPrikaz{
    private static final String NAZEV = "interakce";
    private HerniPlan plan;
    private SeznamOsob seznamOsob;
    private Hra hra;

    /**
     *  Konstruktor třídy
     * @param plan herní plán, ve kterém se bude ve hře "chodit
     */
    public PrikazInterakce(HerniPlan plan, SeznamOsob seznamOsob,Hra hra) {
        this.plan = plan;
        this.seznamOsob= seznamOsob;
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "interakce". Umožní získat informace od dané postavy
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
        if (plan.getAktualniProstor().getNazev().equals("hospoda")&& promena.equals("Rváč_Billy_Red")){
            return "Hled si svého nebo tě zrychtuju\n";

        }
        if (plan.getAktualniProstor().getNazev().equals("hospoda")&& promena.equals("Šerif_James_Freeman")){
            return "V mém městě si zjednám pořádek. Způsobem, který uznám za vhodné\n";

        }
        if (plan.getAktualniProstor().getNazev().equals("hospoda")&& promena.equals("Pistolník_Diego_Sanchez")){
            return "Jsi tu novej ? Asi jo ještě jsem tě tu neviděl.\n";

        }
        if (plan.getAktualniProstor().getNazev().equals("venku")&& promena.equals("Lovec_lidí_Jon_Red")){
            return "Ztratil ses ? \n";

        }
        if (plan.getAktualniProstor().getNazev().equals("hospoda")&& promena.equals("Hospodský_Willy")){
            return "Vypadáš divně vodejdi z mojí putiky !!!!\n";

        }
        if (plan.getAktualniProstor().getNazev().equals("kostel") && promena.equals("Dcera_náčelníka")){

      hra.setKonecHry(true);
            return "Zachránil jsi náčelníkovu dceru. VÝHRAAA";
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
}
