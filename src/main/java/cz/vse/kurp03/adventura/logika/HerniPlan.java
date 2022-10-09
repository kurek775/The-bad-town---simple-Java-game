package cz.vse.kurp03.adventura.logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Kurek Pavel
 *@version    pro školní rok 2021/2022
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;

    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory

        Prostor venku = new Prostor("venku","uprostřed městečka",false);

        Prostor zbrojnice = new Prostor("zbrojnice","ve zbrojnici, kde je spoustu zbraní.",true);
        Prostor veznice = new Prostor("věznice", "ve věznici, která je úplně prázdná. Jsou tu nějaké tajné dveře. Hmm",true);
        Prostor kostel = new Prostor("kostel","vkrásném dřevěném kostele, kde je k oltáři přivázaná osůbka, která volá o pomoc.",true);

        Prostor dumdesperada = new Prostor("dům_desperáda","v krásné vilce jižanského stylu. Je tu vystaveno spousta indiánského oblečení.",true);

        Prostor opustenydum = new Prostor("opuštěný_dům","v starém rozbitém domě, kde se děla zvěrstva ",true);
        Prostor hospoda = new Prostor("hospoda","v klasické texaské putice",false);
        Prostor kolonial = new Prostor("koloniál","v obchodě, kde není žádná obsluha možná se tu dá něco najít.",false);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        venku.setVychod(veznice);
        venku.setVychod(zbrojnice);
        venku.setVychod(kostel);
        venku.setVychod(dumdesperada);
        venku.setVychod(opustenydum);
        venku.setVychod(kolonial);
        venku.setVychod(hospoda);



        veznice.setVychod(venku);
        veznice.setVychod(dumdesperada);
       zbrojnice.setVychod(venku);
        kostel.setVychod(venku);
        dumdesperada.setVychod(venku);
        dumdesperada.setVychod(opustenydum);
        dumdesperada.setVychod(veznice);
        opustenydum.setVychod(venku);
        opustenydum.setVychod(dumdesperada);
        hospoda.setVychod(venku);
        kolonial.setVychod(venku);
                
        aktualniProstor = venku;  // hra začíná venku
    }


    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {


        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }



}
