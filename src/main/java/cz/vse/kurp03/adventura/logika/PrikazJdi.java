package cz.vse.kurp03.adventura.logika;

import java.util.Random;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jarmila Pavlickova, Luboš Pavlíček, Kurek Pavel
 * @version pro školní rok 2021/2022
 */
public class PrikazJdi implements IPrikaz {
    public static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Inventar inventar;
    private SeznamOsob seznamOsob;
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazJdi(HerniPlan plan, Inventar inventar, SeznamOsob seznamOsob, Hra hra) {
        this.plan = plan;
        this.inventar = inventar;
        this.seznamOsob = seznamOsob;
        this.hra = hra;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {

            return "Kam mám jít? Musíš napsat přesně kam.";
        }
        String smer = parametry[0];
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        if (sousedniProstor == null) {
            return "Špatně zadaný název místa.";
        }
        if (plan.getAktualniProstor().getNazev().equals(smer)) {

            return "Tam právě teď jsi.";
        }


        if (plan.getAktualniProstor().getNazev().equals("dům_desperáda") && sousedniProstor.getNazev().equals("věznice")
                || plan.getAktualniProstor().getNazev().equals("dům_desperáda") && sousedniProstor.getNazev().equals("opuštěný_dům")
                || plan.getAktualniProstor().getNazev().equals("opuštěný_dům") && sousedniProstor.getNazev().equals("věznice")
                || plan.getAktualniProstor().getNazev().equals("opuštěný_dům") && sousedniProstor.getNazev().equals("dům_desperáda")
                || plan.getAktualniProstor().getNazev().equals("věznice") && sousedniProstor.getNazev().equals("dům_desperáda")
                || plan.getAktualniProstor().getNazev().equals("věznice") && sousedniProstor.getNazev().equals("opuštěný_dům")) {

            plan.setAktualniProstor(sousedniProstor);

            return "Díky tajnému tunelovému systému jsi se do " + sousedniProstor.getNazev() + " dostal bez použití klíče." + overNebezpecnost();
        } else if (sousedniProstor.getZamceno().equals(true) && inventar.najdi("páčidlo").equals(true) || sousedniProstor.getNazev().equals("kostel") && inventar.najdi("klíč_ke_kostelu").equals(true) || sousedniProstor.getNazev().equals("dům_desperáda") && inventar.najdi("klíč_k_desperadovu_domu").equals(true)) {

            if (inventar.najdi("páčidlo").equals(true)) {

                plan.setAktualniProstor(sousedniProstor);
                plan.getAktualniProstor().setZamceno(false);
                return "Dveře byly zamčené, ale podařilo se ti je vypáčit páčidlem. " + overNebezpecnost();

            } else {

                sousedniProstor.setZamceno(false);
                plan.setAktualniProstor(sousedniProstor);

                return "Dveře byly zamčené, ale díky získanému klíči se ti je podařilo odemknout. " + overNebezpecnost();

            }


        } else if (sousedniProstor.getZamceno().equals(false)) {

            plan.setAktualniProstor(sousedniProstor);

            return overNebezpecnost();
        } else {
            return "Dveře jsou zamčené";
        }


    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }


    /**
     * Metoda nám ověřujeme zda vkračujeme na nebezpečné místo. Pokud ano proběhne zde konfrontace
     *
     * @ return zpráva
     */


    public String overNebezpecnost() {
        if (seznamOsob.najdiNebezpecnouOsobuPodleMista(plan.getAktualniProstor().getNazev()) != null) {
            String postava = konfrontace();

            if (hra.konecHry()) {
                if ((inventar.najdi("pistole").equals(true) || inventar.najdi("puška").equals(true)) && inventar.najdi("náboje").equals(true)) {

                    return "Právě jsi vešel na nepřátelské území\n a útočí na tebe " + postava +

                            " Bohužel jsi pálil až jako druhý a druhé místo nikoho nezajímá.\n ZEMŘEL JSI V BOJI TŘEBA BUDEŠ MÍT PŘÍŠTĚ VÍC ŠTĚSTÍ!!";
                } else {
                    return "Právě jsi vešel na nepřátelské území\n a útočí na tebe " + postava +

                            " Zatoulal ses někam, kde si neměl co pohledávat, protože\n na divokém západě čeká smrt na každém rohu !! \nBOHUŽEL TOHLE JE TEN ROH A TY JSI U SEBE ANI NEMĚL\n NIC ČÍM BY SES UBRÁNIL";

                }

            } else {
                Prostor prostor = plan.getAktualniProstor();
                plan.setAktualniProstor(prostor);
                seznamOsob.najdiOsobu(postava).setPopis("- mrtvý");
                return "Právě si vešel na nepřátelské území a útočí na tebe " + postava +
                        "\n Vyhrál si souboj s " + postava + "\n" + plan.getAktualniProstor().dlouhyPopis();

            }

        } else {
            return plan.getAktualniProstor().dlouhyPopis();
        }


    }


    /**
     * Metoda nám zjistí výsledek konfrontace
     *
     * @ return nebezpečná postava, která na nás útočí
     */


    public String konfrontace() {


        String postava = seznamOsob.najdiNebezpecnouOsobuPodleMista(plan.getAktualniProstor().getNazev()).getJmeno();
        if ((inventar.najdi("pistole").equals(true) || inventar.najdi("puška").equals(true)) && inventar.najdi("náboje").equals(true)) {
            Random rand = new Random();

            int cislo_hrace = rand.nextInt(6);
            int cislo_postavy = rand.nextInt(6);
            while (cislo_hrace == cislo_postavy) {
                cislo_hrace = rand.nextInt(6);
                cislo_postavy = rand.nextInt(6);
            }
            if (cislo_hrace > cislo_postavy || inventar.najdi("totem")) {

                seznamOsob.najdiOsobu(postava).setPopis("- mrtvý");


            } else if (cislo_hrace < cislo_postavy) {

                hra.setKonecHry(true);

            }

        } else {

            hra.setKonecHry(true);

        }
        return postava;
    }


}





