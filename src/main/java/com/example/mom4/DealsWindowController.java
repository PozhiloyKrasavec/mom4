package com.example.mom4;

import com.example.mom4.Model.Client;
import com.example.mom4.Model.DatabaseHandler;
import com.example.mom4.Model.Deal;
import com.example.mom4.Model.Good;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class DealsWindowController {
    ArrayList<Client> clients;
    ArrayList<Good> goods;
    ArrayList<Deal> deals;
    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML
    TableView<Deal> dealTable;
    @FXML
    TableColumn<Deal,Integer> idColumn;
    @FXML
    TableColumn<Deal,Integer> clientColumn;
    @FXML
    TableColumn<Deal,Integer> goodColumn;
    @FXML
    TableColumn<Deal,Integer> timeColumn;
    @FXML
    TableColumn<Deal,Integer> deliveryColumn;
    @FXML
    TableColumn<Deal,Integer> finalPriceColumn;
    @FXML
    TableColumn<Deal,Integer> depositeColumn;
    @FXML
    TableColumn<Deal,Date> giveDateColumn;
    @FXML
    TableColumn<Deal,String> receiveDateColumn;
    @FXML
    TableColumn<Deal,Boolean> docsColumn;
    @FXML
    TableColumn<Deal,String> contractNumColumn;

    @FXML
    ChoiceBox<String> clientsField;
    @FXML
    ChoiceBox<String> goodField;
    @FXML
    ChoiceBox<Integer> timeField;
    @FXML
    TextField deliveryField;
    @FXML
    TextField depositField;
    @FXML
    DatePicker datePickerGiveDate;
    @FXML
    TextField docsField;
    @FXML
    TextField contractsNumField;

    public void init(ArrayList<Client> clients,ArrayList<Good> goods, ArrayList<Deal> deals){
        this.clients = clients;
        this.goods = goods;
        this.deals = deals;
        ArrayList<String> tempClients = new ArrayList<>();
        this.clients.forEach(client -> {
            tempClients.add(client.getFIO());
        });
        clientsField.getItems().addAll(tempClients);
        ArrayList<String> tempGoods = new ArrayList<>();
        this.goods.forEach(good -> {
            tempGoods.add(good.getBrand()+" "+good.getModel());
        });
        goodField.getItems().addAll(tempGoods);
        timeField.getItems().addAll(7,14,30,60,90);
        dealTable.setItems(FXCollections.observableArrayList(deals));
        idColumn.setCellValueFactory(new PropertyValueFactory<Deal,Integer>("id"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Deal,Integer>("clientId"));
        clientColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        clientColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Deal, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Deal, Integer> event) {
                Deal temp = (Deal) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setClientId(event.getNewValue());
                databaseHandler.updateDeal(temp);
            }
        });
        goodColumn.setCellValueFactory(new PropertyValueFactory<Deal,Integer>("goodId"));
        goodColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        goodColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Deal, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Deal, Integer> event) {
                Deal temp = (Deal) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setGoodId(event.getNewValue());
                databaseHandler.updateDeal(temp);
            }
        });
        timeColumn.setCellValueFactory(new PropertyValueFactory<Deal,Integer>("time"));
        timeColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(FXCollections.observableArrayList(7,14,30,60,90)));
        timeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Deal, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Deal, Integer> event) {
                Deal temp = (Deal) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setTime(event.getNewValue());
                databaseHandler.updateDeal(temp);
            }
        });
        deliveryColumn.setCellValueFactory(new PropertyValueFactory<Deal,Integer>("delivery"));
        deliveryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        deliveryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Deal, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Deal, Integer> event) {
                Deal temp = (Deal) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setDelivery(event.getNewValue());
                databaseHandler.updateDeal(temp);
            }
        });
        finalPriceColumn.setCellValueFactory(param -> {
            int goodId = param.getValue().getGoodId();
            int currentTime = param.getValue().getTime();
            Good good = goods.get(goodId-1);
            if (currentTime == 7) return new SimpleIntegerProperty(good.get_7daysPrice()).asObject();
            else if (currentTime == 14) return new SimpleIntegerProperty(good.get_14daysPrice()).asObject();
            else if (currentTime == 30) return new SimpleIntegerProperty(good.getMonthPrice()).asObject();
            else if (currentTime == 60) return new SimpleIntegerProperty(good.get_2monthPrice()).asObject();
            else if (currentTime == 90) return new SimpleIntegerProperty(good.get_3monthPrice()).asObject();
            return null;
        });
        depositeColumn.setCellValueFactory(new PropertyValueFactory<Deal,Integer>("deposit"));
        depositeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        depositeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Deal, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Deal, Integer> event) {
                Deal temp = (Deal) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setDeposit(event.getNewValue());
                databaseHandler.updateDeal(temp);
            }
        });
        giveDateColumn.setCellValueFactory(new PropertyValueFactory<Deal,Date>("giveDate"));
        giveDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        giveDateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Deal, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Deal, Date> event) {
                Deal temp = (Deal) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setGiveDate(event.getNewValue());
                databaseHandler.updateDeal(temp);
            }
        });
        receiveDateColumn.setCellValueFactory(param -> {
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
            Deal temp = param.getValue();
            LocalDate tempDate = Instant.ofEpochMilli(
                    temp.getGiveDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate().plusDays(temp.getTime());
            String date = dateFormat.format(Date.from(tempDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            return new SimpleStringProperty(date);
        });
        docsColumn.setCellValueFactory(new PropertyValueFactory<Deal,Boolean>("docs"));
        docsColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(FXCollections.observableArrayList(true,false)));
        docsColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Deal, Boolean>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Deal, Boolean> event) {
                Deal temp = (Deal) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setDocs(event.getNewValue());
                databaseHandler.updateDeal(temp);
            }
        });
        contractNumColumn.setCellValueFactory(new PropertyValueFactory<Deal,String>("contractNum"));
        contractNumColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        contractNumColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Deal, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Deal, String> event) {
                Deal temp = (Deal) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setContractNum(event.getNewValue());
                databaseHandler.updateDeal(temp);
            }
        });
    }
    public void  addDeal(ActionEvent event){
        int clientId = 0;
        for (Client client : clients){
            if (client.getFIO().equals(clientsField.getValue())) clientId = client.getId();
        }
        int time = timeField.getValue();
        int delivery = Integer.parseInt(deliveryField.getText());
        int deposit = Integer.parseInt(depositField.getText());
        int goodId = 0;
        int finalPrice=delivery;
        for (Good good : goods){
            String temp = good.getBrand() + " " + good.getModel();
            if (temp.equals(goodField.getValue())){
                goodId=good.getId();
                if (time == 7) finalPrice += good.get_7daysPrice();
                else if(time == 14) finalPrice += good.get_14daysPrice();
                else if(time == 30) finalPrice += good.getMonthPrice();
                else if(time == 60) finalPrice +=good.get_2monthPrice();
                else if(time == 90) finalPrice +=good.get_3monthPrice();
            }
        }
        LocalDate giveDate = datePickerGiveDate.getValue();
        LocalDate receiveDate = giveDate.plusDays(time);
        boolean docs = docsField.getText().equals("+");
        String contractNum = contractsNumField.getText();
        int id = deals.size()+1;
        Deal temp = new Deal(id,clientId,goodId,
                time,delivery,finalPrice,
                deposit,Date.from(giveDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(receiveDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),docs,contractNum);
        deals.add(temp);
        dealTable.setItems(FXCollections.observableArrayList(deals));
        databaseHandler.writeDeal(temp);
    }
}
