/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.model;

import java.math.BigDecimal;
/**
 *
 * @author Lenovo GK
 */
public class Sparepart {
    private int idSparepart;
    private String namaSparepart;
    private BigDecimal harga;
    private String satuan;
    private int idServis;
    private int idTransaksi;

    public Sparepart() {
    }

    public Sparepart(int idSparepart, String namaSparepart,
            BigDecimal harga, String satuan,
            int idServis, int idTransaksi) {

        this.idSparepart = idSparepart;
        this.namaSparepart = namaSparepart;
        this.harga = harga;
        this.satuan = satuan;
        this.idServis = idServis;
        this.idTransaksi = idTransaksi;
    }

    public int getIdSparepart() {
        return idSparepart;
    }

    public void setIdSparepart(int idSparepart) {
        this.idSparepart = idSparepart;
    }

    public String getNamaSparepart() {
        return namaSparepart;
    }

    public void setNamaSparepart(String namaSparepart) {
        this.namaSparepart = namaSparepart;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public int getIdServis() {
        return idServis;
    }

    public void setIdServis(int idServis) {
        this.idServis = idServis;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
}
