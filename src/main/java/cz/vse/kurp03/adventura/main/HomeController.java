package cz.vse.kurp03.adventura.main;

import cz.vse.kurp03.adventura.logika.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HomeController implements Pozorovatel {

    @FXML
    private ImageView hrac;
    @FXML
    private Button proved;
    @FXML
    private ListView<Prostor> panelVychodu;
    private IHra hra = new Hra();

    @FXML
    private TextArea vystup;
    @FXML
    private TextField vstup;

    private Map<Prostor, Point2D> souradcniceProstoru = new HashMap<>();

    @FXML
    private void initialize() {
        hra.getHerniPlan().registruj(this);
        vystup.appendText(hra.vratUvitani()+"\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        naplnPanelVychodu();
        HerniPlan hp = hra.getHerniPlan();

        souradcniceProstoru.put(hp.getProstor("domeček"), new Point2D(14,74));
        souradcniceProstoru.put(hp.getProstor("les"), new Point2D(87, 35));
        souradcniceProstoru.put(hp.getProstor("hluboký_les"),  new Point2D(154, 80));
        souradcniceProstoru.put(hp.getProstor("chaloupka"), new Point2D(220, 35));
        souradcniceProstoru.put(hp.getProstor("jeskyně"), new Point2D(152, 160));

        panelVychodu.setCellFactory(prostorListView -> new ListCell<>() {
            @Override
            protected void updateItem(Prostor prostor, boolean empty) {
                super.updateItem(prostor, empty);
                if(!empty) {
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
    }

    @FXML
    private void zpracujVstup() {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> "+prikaz+"\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek+"\n\n");

        if(hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            proved.setDisable(true);
            panelVychodu.setDisable(true);
        }
        vstup.requestFocus();
    }

    private void naplnPanelVychodu() {
        panelVychodu.getItems().clear();
        Collection<Prostor> vychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        panelVychodu.getItems().addAll(vychody);
    }

    @Override
    public void aktualizuj(PredmetPozorovani predmetPozorovani) {
        naplnPanelVychodu();
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
        if(cil==null) return;
        zpracujPrikaz(PrikazJdi.NAZEV+" "+cil);
    }
}
