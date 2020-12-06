package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;


public class c_sales extends Thread {

    @FXML
    private Button btn_showTable;

    @FXML
    public TableView<t_sales> gridSales;

    @FXML
    private TableColumn<t_sales, Integer> sale_id;

    @FXML
    private TableColumn<t_sales, String> sale_number;

    @FXML
    private TableColumn<t_sales, Date> sale_date;

    @FXML
    private TableColumn<t_sales, Double> sale_koeff;

    @FXML
    private TableColumn<t_sales, Integer> product_id;



    @FXML
    private TableColumn<t_sales, Boolean> sold;


    ObservableList <t_sales> row = FXCollections.observableArrayList();// коллекция

    //важно, из данного ObservableList будут подтягиваться данные в t_sales
   static ObservableList<t_products> comboValues = FXCollections.observableArrayList();

    @FXML
    void initialize() {
       gridSales.getSelectionModel().setSelectionMode(
              SelectionMode.MULTIPLE
       );
        gridSales.getSelectionModel().setCellSelectionEnabled(true);
product_id.setEditable(true);

        btn_showTable.setOnAction(
                act -> {
                    Service<Void> service = new Service<Void>() {
                        @Override
                        protected Task<Void> createTask() {
                            return new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    showSales();
                                    return null;
                                }
                            };
                        }
                    };
                    service.start();
                });
    }


public  void showSales(){
    gridSales.refresh();
    Statement st;
    ResultSet rs;

    Statement stCombo;
    ResultSet rsCombo;


    if(MySqlCon.con!=null){
        try {
// в первую очередь загружаем справочник для комбобокса
            stCombo = MySqlCon.con.createStatement();
            String  recordQueryCombo = ("SELECT * FROM t_products");
            rsCombo = stCombo.executeQuery(recordQueryCombo);

            while (rsCombo.next()) {
                comboValues.add(new t_products(rsCombo.getInt("product_id"), rsCombo.getString("product_name")));
            }

// во вторую очередь загружаем рабочую таблицу t_sales
            st = MySqlCon.con.createStatement();
            String recordQuery = ("SELECT * FROM t_sales LIMIT 200000");
            rs = st.executeQuery(recordQuery);
            while (rs.next()) {
                row.add(new t_sales(rs.getInt("sale_id"),
                        rs.getString("sale_number"),
                         rs.getDate("sale_date"),
                          rs.getDouble("sale_koeff"),
                            rs.getInt("product_id"),
                        rs.getBoolean("sold")));
            }



            gridSales.setItems(row);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sale_number.setCellFactory(TextFieldTableCell.forTableColumn());
        product_id.setCellFactory(ComboBoxTableCell.forTableColumn());
       //sold.setCellFactory(CheckBoxTableCell.forTableColumn(sold));


        // привести дату в российский формат
        sale_date.setCellFactory(column -> {
            TableCell<t_sales, Date> cell = new TableCell<t_sales, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        if(item != null)
                            this.setText(format.format(item));
                    }
                }
            };

            return cell;
        });
for (t_products products: comboValues){
    System.out.println(products.getProduct_id());
}




        sale_id.setCellValueFactory(new PropertyValueFactory<>("sale_id"));
        sale_number.setCellValueFactory(new PropertyValueFactory<>("sale_number"));
        sale_date.setCellValueFactory(new PropertyValueFactory<>("sale_date"));
        sale_koeff.setCellValueFactory(new PropertyValueFactory<>("sale_koeff"));
        product_id.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        sold.setCellValueFactory(new PropertyValueFactory<>("sold"));


        gridSales.setItems(row);
    }
}

}



