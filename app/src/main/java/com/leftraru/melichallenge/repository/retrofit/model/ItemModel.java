package com.leftraru.melichallenge.repository.retrofit.model;

import java.util.List;

public class ItemModel extends ItemsModel{

    private int available_quantity;
    private int sold_quantity;
    private List<Pictures> pictures;
    private List<Attributes> attributes;

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public int getSold_quantity() {
        return sold_quantity;
    }

    public List<Pictures> getPrictures() {
        return pictures;
    }

    public List<Attributes> getAttributes() {
        return attributes;
    }

    public String getStockFormatted() {
        int stock = getAvailable_quantity();
        String result = (stock == 1) ? "(" + stock + " disponible)" : "(" + stock + " disponibles)";
        return result;
    }

    public class Pictures{
        private String url;

        public String getUrl() {
            return url;
        }
    }

    public class Attributes{
        private String name;
        private String value_name;

        public String getName() {
            return name;
        }

        public String getValue_name() {
            return value_name;
        }
    }
}
