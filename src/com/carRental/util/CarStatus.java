package com.carRental.util;

public enum  CarStatus {
    ADA("Ada"), SEDANG_DISEWA("Sedang Di Sewa");

    CarStatus(String text) {
        this.text = text;
    }

    private String text;

    public String getText() {
        return text;
    }
}
