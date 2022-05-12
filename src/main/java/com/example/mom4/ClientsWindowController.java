package com.example.mom4;

import com.example.mom4.Model.Client;
import com.example.mom4.Model.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;

public class ClientsWindowController {
    public ArrayList<Client> clients;
    DatabaseHandler databaseHandler = new DatabaseHandler();
    @FXML
    TextField FIOtextField;
    @FXML
    TextField platformTextField;
    @FXML
    TextField addressTextField;
    @FXML
    TextField telephoneTextField;
    @FXML
    TableView<Client> clientsTable = new TableView<Client>();
    @FXML
    TableColumn<Client,Integer> idColumn;
    @FXML
    TableColumn<Client,String> FioColumn;
    @FXML
    TableColumn<Client,String> platformColumn;
    @FXML
    TableColumn<Client,String> addressColumn;
    @FXML
    TableColumn<Client,String> telephoneColumn;

    public void init(ArrayList<Client> clients){
        this.clients = clients;
        clientsTable.setItems(FXCollections.observableArrayList(clients));
        idColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        FioColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("FIO"));
        FioColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        FioColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Client, String> event) {
                Client temp = (Client) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setFIO(event.getNewValue());
                databaseHandler.updateClient(temp);
            }
        });
        platformColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("platform"));
        platformColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        platformColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Client, String> event) {
                Client temp = (Client) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setPlatform(event.getNewValue());
                databaseHandler.updateClient(temp);
            }
        });
        addressColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("address"));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Client, String> event) {
                Client temp = (Client) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setAddress(event.getNewValue());
                databaseHandler.updateClient(temp);
            }
        });
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Client, String> event) {
                Client temp = (Client) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setTelephone(event.getNewValue());
                databaseHandler.updateClient(temp);
            }
        });
    }
    public void addClient(ActionEvent event){
        String FIO = FIOtextField.getText();
        String platform = platformTextField.getText();
        String address = addressTextField.getText();
        String telephone = telephoneTextField.getText();
        int id = clients.size()+1;
        Client client = new Client(id,FIO,platform,address,telephone);
        clients.add(client);
        clientsTable.setItems(FXCollections.observableArrayList(clients));
        try {
            databaseHandler.writeClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
