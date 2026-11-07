/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.model;

import java.math.BigDecimal;
import java.sql.Date;
/**
 *
 * @author Lenovo GK
 */
public class UnitKendaraan {
    private int idUnit;
    private String tipeKendaraan;
    private String warna;
    private String noRangka;
    private String noMesin;
    private int tahunProduksi;
    private String statusUnit;
    private Date tanggalMasuk;
    private BigDecimal harga;
    private int idTransaksi;

    public UnitKendaraan() {
    }

    public UnitKendaraan(int idUnit, String tipeKendaraan,
            String warna, String noRangka, String noMesin,
            int tahunProduksi, String statusUnit,
            Date tanggalMasuk, BigDecimal harga, int idTransaksi) {

        this.idUnit = idUnit;
        this.tipeKendaraan = tipeKendaraan;
        this.warna = warna;
        this.noRangka = noRangka;
        this.noMesin = noMesin;
        this.tahunProduksi = tahunProduksi;
        this.statusUnit = statusUnit;
        this.tanggalMasuk = tanggalMasuk;
        this.harga = harga;
        this.idTransaksi = idTransaksi;
    }

    public int getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }

    public String getTipeKendaraan() {
        return tipeKendaraan;
    }

    public void setTipeKendaraan(String tipeKendaraan) {
        this.tipeKendaraan = tipeKendaraan;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getNoRangka() {
        return noRangka;
    }

    public void setNoRangka(String noRangka) {
        this.noRangka = noRangka;
    }

    public String getNoMesin() {
        return noMesin;
    }

    public void setNoMesin(String noMesin) {
        this.noMesin = noMesin;
    }

    public int getTahunProduksi() {
        return tahunProduksi;
    }

    public void setTahunProduksi(int tahunProduksi) {
        this.tahunProduksi = tahunProduksi;
    }

    public String getStatusUnit() {
        return statusUnit;
    }

    public void setStatusUnit(String statusUnit) {
        this.statusUnit = statusUnit;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
}
