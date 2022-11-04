package cz.vse.kurp03.adventura.main;

import cz.vse.kurp03.adventura.logika.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.*;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class HomeController implements Pozorovatel {

    @FXML
    private ImageView hrac;
    @FXML
    private Button proved;
    @FXML
    private Button napoved;

    @FXML
    private Button novahra;
    @FXML
    private ListView<Prostor> panelVychodu;

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
        HerniPlan hp = hra.getHerniPlan();

        souradcniceProstoru.put(hp.getProstor("venku"), new Point2D(14, 74));
        souradcniceProstoru.put(hp.getProstor("hospoda"), new Point2D(87, 35));
        souradcniceProstoru.put(hp.getProstor("koloniál"), new Point2D(154, 80));
        souradcniceProstoru.put(hp.getProstor("věznice"), new Point2D(220, 35));
        souradcniceProstoru.put(hp.getProstor("zbrojnice"), new Point2D(152, 160));

        souradcniceProstoru.put(hp.getProstor("dům_desperáda"), new Point2D(270, 235));
        souradcniceProstoru.put(hp.getProstor("opuštěný_dům"), new Point2D(0, 60));

        souradcniceProstoru.put(hp.getProstor("kostel"), new Point2D(102, 250));

        panelVychodu.setCellFactory(prostorListView -> new ListCell<>() {
            @Override
            protected void updateItem(Prostor prostor, boolean empty) {
                super.updateItem(prostor, empty);
                if (!empty) {
                    setText(prostor.getNazev());
                    ImageView iw = new ImageView(getClass().getResource("les.jpg").toString());
                    iw.setFitHeight(50);
                    iw.setPreserveRatio(true);

                    setGraphic(iw);
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

                    Button add = new Button("+");
                    add.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String pr = "zvedni" + " " + predmet.getNazev();
                            zpracujPrikaz(pr);
                            aktualizuj(hra.getHerniPlan());
                        }
                    });
                    ImageView iw = new ImageView(getClass().getResource("hrac.png").toString());
                    iw.setFitHeight(50);
                    iw.setPreserveRatio(true);


                    setText(predmet.getNazev());
                    setGraphic(new HBox(add, iw));
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

                    Button interakce = new Button("T");
                    interakce.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String pr = "interakce" + " " + osoba.getJmeno();
                            zpracujPrikaz(pr);
                            aktualizuj(hra.getHerniPlan());
                        }
                    });
                    Button kdeDcera = new Button("kde");
                    kdeDcera.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String pr = "kde_dcera" + " " + osoba.getJmeno();
                            zpracujPrikaz(pr);
                            aktualizuj(hra.getHerniPlan());
                        }
                    });
                    ImageView iw = new ImageView(getClass().getResource("hrac.png").toString());
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

    @FXML
    private void zpracujVstup() {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    @FXML
    private void zpracujNapovedu() {
        String prikaz = "nápověda";

        zpracujPrikaz(prikaz);
    }

    @FXML
    private void zpracujNovouHru() throws Exception {
        Start st = new Start();

        Stage stage;
        stage = (Stage) napoved.getScene().getWindow();
        st.start(stage);

    }

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
        }
        vstup.requestFocus();
    }

    private void naplnPanelVychodu() {
        panelVychodu.getItems().clear();
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        panelVychodu.getItems().addAll(vychody);
    }


    private void naplnPanelPredmetu() {

        panelPredmetu.getItems().clear();
        Collection<Predmet> predmety = hra.getSeznamPredmetu();
        panelPredmetu.getItems().addAll(predmety);
    }

    private void naplnPanelOsob() {

        panelOsob.getItems().clear();
        Collection<Osoba> osoby = hra.getSeznamOsob();
        panelOsob.getItems().addAll(osoby);
    }

    @Override
    public void aktualizuj(PredmetPozorovani predmetPozorovani) {
        naplnPanelVychodu();
        naplnPanelPredmetu();
        naplnPanelOsob();
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

