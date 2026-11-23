package com.produk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProdukTest {

     private Produk produk;

    @BeforeEach
    void setUp() {
        produk = new Produk("A10001", "Laptop", 15000000, 10);
    }

    @Test
    void buatProdukDataValid() {
        assertEquals("A10001", produk.getId());
        assertEquals("Laptop", produk.getNama());
        assertEquals(15000000, produk.getHarga());
        assertEquals(10, produk.getStok());
    }

    @Test
    void ubahHargaMenjadiNilaiValid() {
        produk.setHarga(12000000);
        assertEquals(12000000, produk.getHarga());
    }

    @Test
    void hargaBatasMinimum() {
        produk.setHarga(1);
        assertEquals(1, produk.getHarga());
    }

    @Test
    void stokBatasNol() {
        produk.setStok(0);
        assertEquals(0, produk.getStok());
    }

    @Test
    void hargaNegatifMelemparException() {
        assertThrows(IllegalArgumentException.class, () -> produk.setHarga(-12000000));
    }

    @Test
    void stokNegatifMelemparException() {
        assertThrows(IllegalArgumentException.class, () -> produk.setStok(-5));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 99.5, 500000, 12000000})
    void berbagaiHargaValidDiterima(double hargaBaru) {
        produk.setHarga(hargaBaru);
        assertEquals(hargaBaru, produk.getHarga());
    }
}
