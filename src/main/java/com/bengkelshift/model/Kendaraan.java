/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.model;

/**
 *
 * @author Lenovo GK
 */
public class Kendaraan {
    private int idKendaraan;
    private String noPolisi;
    private String tipeKendaraan;
    private String noRangka;
    private String noMesin;
    private int tahunProduksi;
    private int idPelanggan;
    
    public Kendaraan() {
    }

    public Kendaraan(int idKendaraan, String noPolisi,
            String tipeKendaraan, String noRangka,
            String noMesin, int tahunProduksi, int idPelanggan) {

        this.idKendaraan = idKendaraan;
        this.noPolisi = noPolisi;
        this.tipeKendaraan = tipeKendaraan;
        this.noRangka = noRangka;
        this.noMesin = noMesin;
        this.tahunProduksi = tahunProduksi;
        this.idPelanggan = idPelanggan;
    }

    public int getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getTipeKendaraan() {
        return tipeKendaraan;
    }

    public void setTipeKendaraan(String tipeKendaraan) {
        this.tipeKendaraan = tipeKendaraan;
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

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }
}
