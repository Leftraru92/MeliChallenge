package com.leftraru.melichallenge.repository.retrofit.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ItemsModel {

    private String id;
    private String title;
    private double price;
    private String thumbnail;
    private Shipping shipping;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public String getPriceDecimal() {
        DecimalFormat format = new DecimalFormat("#.00");
        String result = format.format(price);
        int indexOfDecimal = result.indexOf(".");
        return result.substring(indexOfDecimal + 1);
    }

    public String getPriceFormated() {
        DecimalFormat formatea = new DecimalFormat("$ ###,###", DecimalFormatSymbols.getInstance(Locale.ITALY));
        return formatea.format(price);
    }

    public class Shipping {
        private boolean free_shipping;

        public boolean isFree_shipping() {
            return free_shipping;
        }
    }
}
