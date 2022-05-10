package com.example.mom4;

import com.example.mom4.Model.DatabaseHandler;
import com.example.mom4.Model.Good;
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

public class GoodsWindowController {
    ArrayList<Good> goods;
    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML
    TextField brandTextField;
    @FXML
    TextField modelTextField;
    @FXML
    TextField quantityTextField;
    @FXML
    TextField sellPriceTextField;
    @FXML
    TextField _7daysPriceTextField;
    @FXML
    TextField _14daysPriceTextField;
    @FXML
    TextField monthTextField;
    @FXML
    TextField _2monthTextField;
    @FXML
    TextField _3monthTextField;

    @FXML
    TableView<Good> goodsTable;
    @FXML
    TableColumn<Good,Integer> idColumn;
    @FXML
    TableColumn<Good,String> brandColumn;
    @FXML
    TableColumn<Good,String> modelColumn;
    @FXML
    TableColumn<Good,Integer> quantityColumn;
    @FXML
    TableColumn<Good,Integer> sellPriceColumn;
    @FXML
    TableColumn<Good,Integer> _7daysPriceColumn;
    @FXML
    TableColumn<Good,Integer> _14daysPriceColumn;
    @FXML
    TableColumn<Good,Integer> monthPriceColumn;
    @FXML
    TableColumn<Good,Integer> _2monthPriceColumn;
    @FXML
    TableColumn<Good,Integer> _3monthPriceColumn;

    public void init(ArrayList<Good> goods){
        this.goods = goods;
        goodsTable.setItems(FXCollections.observableArrayList(goods));
        idColumn.setCellValueFactory(new PropertyValueFactory<Good,Integer>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<Good,String>("brand"));
        brandColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        brandColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, String> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setBrand(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
        modelColumn.setCellValueFactory(new PropertyValueFactory<Good,String>("model"));
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, String> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setModel(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Good,Integer>("quantity"));
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Integer> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setQuantity(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<Good,Integer>("sellPrice"));
        sellPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sellPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Integer> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setSellPrice(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
        _7daysPriceColumn.setCellValueFactory(new PropertyValueFactory<Good,Integer>("_7daysPrice"));
        _7daysPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        _7daysPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Integer> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.set_7daysPrice(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
        _14daysPriceColumn.setCellValueFactory(new PropertyValueFactory<Good,Integer>("_14daysPrice"));
        _14daysPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        _14daysPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Integer> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.set_14daysPrice(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
        monthPriceColumn.setCellValueFactory(new PropertyValueFactory<Good,Integer>("monthPrice"));
        monthPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        monthPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Integer> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.setMonthPrice(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
        _2monthPriceColumn.setCellValueFactory(new PropertyValueFactory<Good,Integer>("_2monthPrice"));
        _2monthPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        _2monthPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Integer> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.set_2monthPrice(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
        _3monthPriceColumn.setCellValueFactory(new PropertyValueFactory<Good,Integer>("_3monthPrice"));
        _3monthPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        _3monthPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Good, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Good, Integer> event) {
                Good temp = (Good) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                );
                temp.set_3monthPrice(event.getNewValue());
                databaseHandler.updateGoods(temp);
            }
        });
    }
    public void addGood(ActionEvent event){
        String brand = brandTextField.getText();
        String model = modelTextField.getText();
        int quantity = Integer.parseInt(quantityTextField.getText());
        int sellPrice = Integer.parseInt(sellPriceTextField.getText());
        int _7daysPrice = Integer.parseInt(_7daysPriceTextField.getText());
        int _14daysPrice = Integer.parseInt(_14daysPriceTextField.getText());
        int monthPrice = Integer.parseInt(monthTextField.getText());
        int _2monthPrice = Integer.parseInt(_2monthTextField.getText());
        int _3monthPrice = Integer.parseInt(_3monthTextField.getText());
        int id = goods.size()+1;
        Good temp = new Good(id,brand,model,quantity,
                sellPrice,_7daysPrice,_14daysPrice,
                monthPrice,_2monthPrice,_3monthPrice);
        goods.add(temp);
        goodsTable.setItems(FXCollections.observableArrayList(goods));
        databaseHandler.writeGood(temp);
    }
}
