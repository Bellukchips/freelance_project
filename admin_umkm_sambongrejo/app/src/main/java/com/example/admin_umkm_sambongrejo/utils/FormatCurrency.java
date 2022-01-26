package com.example.admin_umkm_sambongrejo.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatCurrency {
    private final NumberFormat formatRupiah;
    private final int price;

    public FormatCurrency(int price) {
        this.price = price;
        Locale locale = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(locale);

    }

    public String getFormat() {
        return formatRupiah.format((double) this.price);
    }

}
