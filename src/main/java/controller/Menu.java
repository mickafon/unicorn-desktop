package controller;

import PluginManager.PluginLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import model.Main;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Micka on 07/03/2017.
 */
public class Menu implements Initializable {
    protected Main main;
    public static Menu menu;

    @FXML
    protected Label title;

    @FXML
    protected Label home;

    @FXML
    protected Label users;

    @FXML
    protected Label admins;

    @FXML
    protected Label sites;

    @FXML
    protected Label status;

    @FXML
    protected Label vehicles;

    @FXML
    protected Label report;

    @FXML
    protected Label param;

    @FXML
    protected Label offline;

    @FXML
    protected Rectangle rect;

    @FXML
    protected BorderPane pane;

    @FXML
    protected Pane pane2;

    @FXML
    protected SplitPane split;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        menu = this;
        home();
        PluginLoader loader = new PluginLoader();
        loader.LoadPlugins();
    }

    public void home(){
        new Loader("/view/Home.fxml", "PARTAGE TA CAISSE");
    }

    @FXML
    private void labelHome(MouseEvent event) {
        home();
    }

    @FXML
    private void labelUsers(MouseEvent event) {
        User_menu user_menu = User_menu.user_menu;
        Loader loader = new Loader("/view/User.fxml","GESTION DES UTILISATEURS");
        User_menu user_m = loader.getLoader().getController();

        if (user_menu != null){
            if (user_menu.getButtons() != null) {
                for (Button button : user_menu.getButtons()) {
                    user_m.addButton(button);
                }
            }
        }
    }

    @FXML
    private void labelAdmins(MouseEvent event) {
        if (Connection.account.getStatus().getId() == 3) {
            new Loader("/view/AddAdmin.fxml", "GESTION DES ADMINISTRATEURS");
        }
        else{
            new Message("Vous n'avez pas les droits nécessaires");
        }
    }

    @FXML
    private void labelSites(MouseEvent event) {
        new Loader("/view/Site.fxml", "GESTION DES SITES");
    }

    @FXML
    private void labelStatus(MouseEvent event) {
        new Loader("/view/Status.fxml", "GESTION DES STATUS");
    }

    @FXML
    private void labelVehicles(MouseEvent event) {
        new Loader("/view/table_vehicle.fxml", "GESTION DES VEHICULES");
    }

    @FXML
    private void labelReport(MouseEvent event) {
        new Loader("/view/table_report.fxml", "SIGNALEMENTS DES UTILISATEURS");
    }

    @FXML
    private void labelPlugins(MouseEvent event) {
        new Loader("/view/plugins.fxml", "GESTION DES PLUGINS");
    }

    @FXML
    private void labelParam(MouseEvent event) {
        new Loader("/view/Logins.fxml", "PARAMÈTRE");
    }

    @FXML
    private void labelOff(MouseEvent event) {
        logout();
    }

    public void logout(){
        main.connection();
        Connection.account = null;
    }

    public void setMain(Main main){
        this.main = main;
    }

    public void fillPane(Group group, String titre) {
        pane.getChildren().clear();
        title.setText(titre);
        StackPane root = new StackPane(rect, group);
        pane.setTop(title);
        pane.setCenter(root);
    }
}
