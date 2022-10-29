/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.kurp03.adventura.main;



import cz.vse.kurp03.adventura.logika.*;
import cz.vse.kurp03.adventura.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2016/2017
 */
public class Start extends Application
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        if(args.length>0 && args[0].equals("text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            launch();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        stage.setTitle("Adventura");
        stage.show();
    }
}
