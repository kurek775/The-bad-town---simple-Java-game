package cz.vse.kurp03.adventura.logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Kurek Pavel
 *@version    pro školní rok 2021/2022
 *
 */
class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "nápověda";
    private SeznamPrikazu platnePrikazy;


    /**
     *  Konstruktor třídy
     *
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli.
     */
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je najít dceru náčelníka\n"
                + "Pokud nevíš jak začít, zkus si promluvit pomocí příkazu kde_dcera s někým v hospodě\n"
                + "\n"
                + "Pokud už vidíš náčelníkovu dceru stačí zadat příkaz interakce Dcera_náčelníka\n"
                + "Můžeš zadat tyto příkazy:\n"
                + platnePrikazy.vratNazvyPrikazu();
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}

