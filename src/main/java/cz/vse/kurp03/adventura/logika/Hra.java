package cz.vse.kurp03.adventura.logika;


/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Kurek Pavel
 *@version    pro školní rok 2021/2022
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private Inventar inventar;
    private SeznamPredmetu seznamPredmetu;
    private SeznamOsob seznamOsob;

    private boolean konecHry = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů + předměty a postavy
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        inventar = new Inventar();
        seznamOsob=new SeznamOsob();
        seznamPredmetu=new SeznamPredmetu(herniPlan);

        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan,inventar,seznamOsob,this));
        platnePrikazy.vlozPrikaz(new PrikazKdedcera(herniPlan,seznamOsob,this,inventar));
        platnePrikazy.vlozPrikaz(new PrikazZvedni(inventar,seznamPredmetu));
        platnePrikazy.vlozPrikaz(new PrikazZahod(inventar,seznamPredmetu));
        platnePrikazy.vlozPrikaz(new PrikazInventar(inventar));
        platnePrikazy.vlozPrikaz(new PrikazRozhlednout(seznamPredmetu,herniPlan,seznamOsob));
        platnePrikazy.vlozPrikaz(new PrikazInterakce(herniPlan,seznamOsob,this));


        platnePrikazy.vlozPrikaz(new PrikazZautoc(herniPlan,inventar,seznamOsob,this));

        platnePrikazy.vlozPrikaz(new PrikazKonec(this));

        seznamPredmetu.vlozPredmet(new Predmet("náboje","- vhodné pro střelbu","zbrojnice",true,false));
        seznamPredmetu.vlozPredmet(new Predmet("puška","- tahle puška se moc líbí zdejšímu pistolníkovi","zbrojnice",true,false));
        seznamPredmetu.vlozPredmet(new Predmet("pistole","- vhodné pro střelbu","zbrojnice",true,false));
        seznamPredmetu.vlozPredmet(new Predmet("páčidlo","- vhodné pro otvírání všeho možného","koloniál",true,false));

        seznamPredmetu.vlozPredmet(new Predmet("skříň","- pěkná skřín","koloniál",false,false));
        seznamPredmetu.vlozPredmet(new Predmet("skříň druhá","- ošklivá skřín","koloniál",false,false));
        seznamPredmetu.vlozPredmet(new Predmet("truhla","- prázdná truhla","hospoda",false,false));
        seznamPredmetu.vlozPredmet(new Predmet("odpadky","- staré oblečení","venku",true,false));
        seznamPredmetu.vlozPredmet(new Predmet("sud","- prázdný sud","venku",false,false));
        seznamPredmetu.vlozPredmet(new Predmet("sud s pivem","- plný sud","venku",false,false));
        seznamPredmetu.vlozPredmet(new Predmet("kůň","- divoký kůň","venku",false,false));

        seznamPredmetu.vlozPredmet(new Predmet("totem","- indiánský totem, který může nositeli přinést štěstí","věznice",true,false));
        seznamPredmetu.vlozPredmet(new Predmet("klíč_k_diegovu_domu","klíč, který sehnal pistolník","hospoda",false,false));
        seznamPredmetu.vlozPredmet(new Predmet("klíč_ke_kostelu","klíč, který u sebe měl diego","opuštěný_dům",true,false));





        seznamOsob.vlozOsobu(new Osoba("Hospodský_Willy","- hospodský pro, kterého je nejdůležitější jeho prospěch","hospoda",false));
        seznamOsob.vlozOsobu(new Osoba("Šerif_James_Freeman","- zkažený šerif","hospoda",false));
        seznamOsob.vlozOsobu(new Osoba("Rváč_Billy_Red","- hloupý už od pohledu","hospoda",false));
        seznamOsob.vlozOsobu(new Osoba("Lovec_lidí_Jon_Red","- křivák od pohledu","venku",false));
        seznamOsob.vlozOsobu(new Osoba("Gauner_Rodrigo_Desperado","- známý bandita, který má všechny podplacené","opuštěný_dům",true));
        seznamOsob.vlozOsobu(new Osoba("Farář_Jan_Sienkiewicz","- zvrácený farář","kostel",true));
        seznamOsob.vlozOsobu(new Osoba("Pistolník_Diego_Sanchez","- férový pistolník, který něco ví","hospoda",false));
        seznamOsob.vlozOsobu(new Osoba("Dcera_náčelníka","- přivázaná k oltáři","kostel",false));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "\n" +
                "Vítej v Bad Town Juane!\n" +
               "Aby ti náčelník kmene apačů dal mapu k pokladu, musíš \n" +
                "najít jeho dceru, kterou unesl někdo v Bad Town, kde jí i skrývá.\n" +
               "Napiš 'nápověda', pokud si nevíš rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dík, že jste si zahráli.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

