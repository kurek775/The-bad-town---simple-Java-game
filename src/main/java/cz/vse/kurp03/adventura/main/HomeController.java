package cz.vse.kurp03.adventura.main;

import cz.vse.kurp03.adventura.logika.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.*;

import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class HomeController implements Pozorovatel {

    @FXML
    private ImageView hrac;
    @FXML
    private Button proved;
    @FXML
    private Button napoved;


    @FXML
    private ListView<Prostor> panelVychodu;
    @FXML
    private ListView<String> panelBatoh;

    @FXML
    private ListView<Predmet> panelPredmetu;
    @FXML
    private ListView<Osoba> panelOsob;

    private IHra hra = new Hra();

    @FXML
    private TextArea vystup;
    @FXML
    private TextField vstup;

    private Map<Prostor, Point2D> souradcniceProstoru = new HashMap<>();


    @FXML
    private void initialize() {

        hra.getHerniPlan().registruj(this);
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        naplnPanelVychodu();
        naplnPanelPredmetu();
        naplnPanelOsob();
        naplnPanelBatoh();
        HerniPlan hp = hra.getHerniPlan();

        souradcniceProstoru.put(hp.getProstor("venku"), new Point2D(189, 138));
        souradcniceProstoru.put(hp.getProstor("koloniál"), new Point2D(327, 63));
        souradcniceProstoru.put(hp.getProstor("hospoda"), new Point2D(327, 137));
        souradcniceProstoru.put(hp.getProstor("věznice"), new Point2D(327, 114));
        souradcniceProstoru.put(hp.getProstor("zbrojnice"), new Point2D(210, 63));

        souradcniceProstoru.put(hp.getProstor("dům_desperáda"), new Point2D(136, 214));
        souradcniceProstoru.put(hp.getProstor("opuštěný_dům"), new Point2D(46, 137));

        souradcniceProstoru.put(hp.getProstor("kostel"), new Point2D(47, 63));

        panelVychodu.setCellFactory(prostorListView -> new ListCell<>() {
            @Override
            protected void updateItem(Prostor prostor, boolean empty) {
                super.updateItem(prostor, empty);
                if (!empty) {
                    setText(prostor.getNazev());
                    ImageView iw;
                    if (prostor.getNazev().equals("venku")) {
                        iw = new ImageView(getClass().getResource("venku.jpg").toString());
                    } else if (prostor.getNazev().equals("zbrojnice")) {
                        iw = new ImageView(getClass().getResource("zbrojnice.jpg").toString());
                    } else if (prostor.getNazev().equals("věznice")) {
                        iw = new ImageView(getClass().getResource("veznice.jpg").toString());
                    } else if (prostor.getNazev().equals("kostel")) {
                        iw = new ImageView(getClass().getResource("kostel.jpg").toString());
                    } else if (prostor.getNazev().equals("dům_desperáda")) {
                        iw = new ImageView(getClass().getResource("dumdesperada.jpg").toString());
                    } else if (prostor.getNazev().equals("opuštěný_dům")) {
                        iw = new ImageView(getClass().getResource("opustenydum.jpg").toString());
                    } else if (prostor.getNazev().equals("hospoda")) {
                        iw = new ImageView(getClass().getResource("hospoda.jpg").toString());
                    } else if (prostor.getNazev().equals("koloniál")) {
                        iw = new ImageView(getClass().getResource("kolonial.jpg").toString());
                    }
                    else{
                        iw = new ImageView();
                    }
                    iw.setFitHeight(40);
                    iw.setPreserveRatio(true);

                    setGraphic(iw);
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        panelBatoh.setCellFactory(batohListView -> new ListCell<>() {
            @Override
            protected void updateItem(String predmet, boolean empty) {
                super.updateItem(predmet, empty);
                if (!empty) {

                    Button zahodit = new Button("-");
                    zahodit.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String pr = "zahod" + " " + predmet;
                            zpracujPrikaz(pr);
                            aktualizuj(hra.getHerniPlan());
                        }
                    });
                    ImageView iw;
                    if (predmet.equals("puška")) {
                        iw = new ImageView(getClass().getResource("puska.png").toString());
                    } else if (predmet.equals("náboje")) {
                        iw = new ImageView(getClass().getResource("naboje.png").toString());
                    } else if (predmet.equals("pistole")) {
                        iw = new ImageView(getClass().getResource("pistole.png").toString());
                    } else if (predmet.equals("páčidlo")) {
                        iw = new ImageView(getClass().getResource("pacidlo.png").toString());
                    } else if (predmet.equals("skříň")) {
                        iw = new ImageView(getClass().getResource("skrin.png").toString());
                    } else if (predmet.equals("postel")) {
                        iw = new ImageView(getClass().getResource("postel.png").toString());
                    } else if (predmet.equals("truhla")) {
                        iw = new ImageView(getClass().getResource("truhla.png").toString());
                    } else if (predmet.equals("sud")) {
                        iw = new ImageView(getClass().getResource("sud.png").toString());
                    } else if (predmet.equals("sud s pivem")) {
                        iw = new ImageView(getClass().getResource("sudspivem.png").toString());
                    } else if (predmet.equals("kůň")) {
                        iw = new ImageView(getClass().getResource("kun.png").toString());
                    } else if (predmet.equals("klíč_k_diegovu_domu")) {
                        iw = new ImageView(getClass().getResource("klicdiego.png").toString());
                    } else if (predmet.equals("klíč_ke_kostelu")) {
                        iw = new ImageView(getClass().getResource("klickostel.png").toString());
                    } else if (predmet.equals("totem")) {
                        iw = new ImageView(getClass().getResource("totem.png").toString());
                    }   else if (predmet.equals("odpadky")) {
                        iw = new ImageView(getClass().getResource("odpadky.png").toString());
                    }
                    else {
                        iw = new ImageView();
                    }
                    iw.setFitHeight(40);
                    iw.setPreserveRatio(true);
                    setText(predmet);
                    setGraphic(new HBox(zahodit, iw));
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        panelPredmetu.setCellFactory(predmetListView -> new ListCell<>() {
            @Override
            protected void updateItem(Predmet predmet, boolean empty) {
                super.updateItem(predmet, empty);
                if (!empty) {
                    ImageView iw;

                    if (predmet.getNazev().equals("puška")) {
                        iw = new ImageView(getClass().getResource("puska.png").toString());
                    } else if (predmet.getNazev().equals("náboje")) {
                        iw = new ImageView(getClass().getResource("naboje.png").toString());
                    } else if (predmet.getNazev().equals("pistole")) {
                        iw = new ImageView(getClass().getResource("pistole.png").toString());
                    } else if (predmet.getNazev().equals("páčidlo")) {
                        iw = new ImageView(getClass().getResource("pacidlo.png").toString());
                    } else if (predmet.getNazev().equals("skříň")) {
                        iw = new ImageView(getClass().getResource("skrin.png").toString());
                    } else if (predmet.getNazev().equals("postel")) {
                        iw = new ImageView(getClass().getResource("postel.png").toString());
                    } else if (predmet.getNazev().equals("truhla")) {
                        iw = new ImageView(getClass().getResource("truhla.png").toString());
                    } else if (predmet.getNazev().equals("sud")) {
                        iw = new ImageView(getClass().getResource("sud.png").toString());
                    } else if (predmet.getNazev().equals("sud s pivem")) {
                        iw = new ImageView(getClass().getResource("sudspivem.png").toString());
                    } else if (predmet.getNazev().equals("kůň")) {
                        iw = new ImageView(getClass().getResource("kun.png").toString());
                    } else if (predmet.getNazev().equals("klíč_k_diegovu_domu")) {
                        iw = new ImageView(getClass().getResource("klicdiego.png").toString());
                    } else if (predmet.getNazev().equals("klíč_ke_kostelu")) {
                        iw = new ImageView(getClass().getResource("klickostel.png").toString());
                    } else if (predmet.getNazev().equals("totem")) {
                        iw = new ImageView(getClass().getResource("totem.png").toString());
                    }   else if (predmet.getNazev().equals("odpadky")) {
                        iw = new ImageView(getClass().getResource("odpadky.png").toString());
                    }
                    else {
                        iw = new ImageView();
                    }
                    iw.setFitHeight(40);
                    iw.setPreserveRatio(true);
                    if (predmet.getLzeZvednout().equals(true)) {
                        Button add = new Button("+");
                        add.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                String pr = "zvedni" + " " + predmet.getNazev();
                                zpracujPrikaz(pr);
                                aktualizuj(hra.getHerniPlan());
                            }
                        });



                        setText(predmet.getNazev());
                        setGraphic(new HBox(add, iw));
                    } else {



                        setText(predmet.getNazev());
                        setGraphic(new HBox(iw));
                    }


                } else {
                    setText(null);
                    setGraphic(null);

                }
            }
        });
        panelOsob.setCellFactory(osobyListView -> new ListCell<>() {
            @Override
            protected void updateItem(Osoba osoba, boolean empty) {
                super.updateItem(osoba, empty);
                if (!empty) {

                    Button duel = new Button("duel");
                    duel.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String pr = "zaútoč" + " " + osoba.getJmeno();
                            zpracujPrikaz(pr);
                            aktualizuj(hra.getHerniPlan());
                        }
                    });

                    Button interakce = new Button("interakce");
                    interakce.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String pr = "interakce" + " " + osoba.getJmeno();
                            zpracujPrikaz(pr);
                            aktualizuj(hra.getHerniPlan());
                        }
                    });
                    Button kdeDcera = new Button("kde je dcera");
                    kdeDcera.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String pr = "kde_dcera" + " " + osoba.getJmeno();
                            zpracujPrikaz(pr);
                            aktualizuj(hra.getHerniPlan());
                        }
                    });
                    ImageView iw;
                    if (osoba.getJmeno().contains("Jan")) {
                        iw = new ImageView(getClass().getResource("farar.jpg").toString());
                    } else if (osoba.getJmeno().contains("Lovec")) {
                        iw = new ImageView(getClass().getResource("lovec.jpg").toString());
                    } else if (osoba.getJmeno().contains("Willy")) {
                        iw = new ImageView(getClass().getResource("hospodsky.jpg").toString());
                    } else if (osoba.getJmeno().contains("Dcera")) {
                        iw = new ImageView(getClass().getResource("dcera.jpg").toString());
                    } else if (osoba.getJmeno().contains("Diego")) {
                        iw = new ImageView(getClass().getResource("pistolnik.jpg").toString());
                    } else if (osoba.getJmeno().contains("Gauner")) {
                        iw = new ImageView(getClass().getResource("gauner.jpeg").toString());
                    } else if (osoba.getJmeno().contains("Billy")) {
                        iw = new ImageView(getClass().getResource("rvac.jpg").toString());
                    } else if (osoba.getJmeno().contains("James")) {
                        iw = new ImageView(getClass().getResource("serif.jpg").toString());
                    } else {
                        iw = new ImageView();
                    }
                    iw.setFitHeight(50);
                    iw.setPreserveRatio(true);
                    setText(osoba.getJmeno());
                    setGraphic(new HBox(duel, interakce, kdeDcera, iw));
                } else {
                    setText(null);
                    setGraphic(null);

                }
            }
        });
    }

    /** Zpracování vstupu a následné zpracování daného příkazu
     *
     */
    @FXML
    private void zpracujVstup() {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    /** Po kliknutí na tlačítko "Nápověda"
     * Zpracování nového okna s HTML nápovědou pro hru
     */
    @FXML
    private void zpracujNapovedu() {
        Stage stage = new Stage();
        WebView webView = new WebView();
        String url = getClass().getResource("help.html").toExternalForm();
        webView.getEngine().load(url);
        Scene scene = new Scene(webView);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @throws Exception
     *  Po kliknutí na tlačítko "Nová hra"
     *  Spuštění nové hry v novém okně
     */
    @FXML
    private void zpracujNovouHru() throws Exception {
        Start st = new Start();
        Stage stage;
        stage = (Stage) napoved.getScene().getWindow();
        st.start(stage);

    }

    /**
     * @param prikaz
     *  Spuštění příkazu vypsání do výstupu + kontrola zda nenastal konec hry
     *  Následný focus na vstup
     */
    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> " + prikaz + "\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek + "\n\n");

        if (hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            proved.setDisable(true);
            panelVychodu.setDisable(true);
            panelPredmetu.setDisable(true);
            panelOsob.setDisable(true);
            panelBatoh.setDisable(true);
        }
        vstup.requestFocus();
    }

    /**
     * Naplnění panelu východů
     */
    private void naplnPanelVychodu() {
        panelVychodu.getItems().clear();
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        panelVychodu.getItems().addAll(vychody);
    }

    /**
     * Naplnění našeho inventáře
     */
    private void naplnPanelBatoh() {
        panelBatoh.getItems().clear();
        List<String> inv = hra.getInventar();
        panelBatoh.getItems().addAll(inv);
    }


    /**
     * Naplnění předmětů v okolí
     */
    private void naplnPanelPredmetu() {

        panelPredmetu.getItems().clear();
        Collection<Predmet> predmety = hra.getSeznamPredmetu();
        panelPredmetu.getItems().addAll(predmety);
    }

    /**
     * Naplnění panelu osob v okolí
     */
    private void naplnPanelOsob() {

        panelOsob.getItems().clear();
        Collection<Osoba> osoby = hra.getSeznamOsob();
        panelOsob.getItems().addAll(osoby);
    }

    /**
     * @param predmetPozorovani
     * Aktualizování panelů pomocí naplnění
     *
     */
    @Override
    public void aktualizuj(PredmetPozorovani predmetPozorovani) {
        naplnPanelVychodu();
        naplnPanelPredmetu();
        naplnPanelOsob();
        naplnPanelBatoh();
        aktualizujPoziciHrace();

    }

    private void aktualizujPoziciHrace() {
        Prostor aktualniProstor = hra.getHerniPlan().getAktualniProstor();
        Point2D souradnice = souradcniceProstoru.get(aktualniProstor);
        hrac.setLayoutX(souradnice.getX());
        hrac.setLayoutY(souradnice.getY());
    }

    public void klikPanelVychodu(MouseEvent mouseEvent) {
        Prostor cil = panelVychodu.getSelectionModel().getSelectedItem();
        if (cil == null) return;
        zpracujPrikaz(PrikazJdi.NAZEV + " " + cil);
    }


}

