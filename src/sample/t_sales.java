package sample;

import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class t_sales {
      int sale_id;
      String sale_number;
       Date sale_date ;
      double sale_koeff;
      int product_id;
     boolean sold;

    public t_sales(int sale_id, String sale_number, Date sale_date, double sale_koeff, int product_id, boolean sold){
        this.sale_id=sale_id;
        this.sale_number=sale_number;
        this.sale_date=sale_date;
        this.sale_koeff=sale_koeff;
        this.product_id=product_id;
        this.sold=sold;


    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public String getSale_number() {
        return sale_number;
    }

    public void setSale_number(String sale_number) {
        this.sale_number = sale_number;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public double getSale_koeff() {
        return sale_koeff;
    }

    public void setSale_koeff(double sale_koeff) {
        this.sale_koeff = sale_koeff;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }


    }
