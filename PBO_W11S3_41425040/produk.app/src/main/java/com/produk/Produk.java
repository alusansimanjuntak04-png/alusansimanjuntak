package com.produk;

public class Produk {

    private String id;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String id, String nama, double harga, int stok) {
        setId(id);
        this.nama = nama;
        setHarga(harga);
        setStok(stok);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id tidak boleh kosong");
        }
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (!(harga > 0)) {
            throw new IllegalArgumentException("Harga harus lebih dari 0");
        }
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }
        this.stok = stok;
    }
}
