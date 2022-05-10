package com.example.mom4;

import com.example.mom4.Model.Client;
import com.example.mom4.Model.DatabaseHandler;
import com.example.mom4.Model.Deal;
import com.example.mom4.Model.Good;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    ArrayList<Client> clients;
    ArrayList <Good> goods;
    ArrayList <Deal> deals;

    public void openDealsWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dealsWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("formomrent");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("favicon.png"))));
        stage.setScene(scene);
        DealsWindowController dealsWindowController = loader.getController();
        dealsWindowController.init(clients,goods,deals);
        stage.show();
    }
    public void openGoodsWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("goodsWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("formomrent");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("favicon.png"))));
        stage.setScene(scene);
        GoodsWindowController goodsWindowController = loader.getController();
        goodsWindowController.init(goods);
        stage.show();
    }
    public void opensClientsWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("formomrent");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("favicon.png"))));
        stage.setScene(scene);
        ClientsWindowController userController = loader.getController();
        userController.init(clients);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        clients = databaseHandler.getClient();
        goods = databaseHandler.getGoods();
        deals = databaseHandler.getDeals();
    }
}