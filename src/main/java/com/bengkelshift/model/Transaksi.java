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
public class Transaksi {
    private int idTransaksi;
    private Date tanggalTransaksi;
    private String jenisTransaksi;
    private BigDecimal diskon;
    private BigDecimal totalBayar;
    private String metodePembayaran;
    private String statusPembayaran;
    private int salesIdKaryawan;
    private int idPelanggan;

    public Transaksi() {
    }

    public Transaksi(int idTransaksi, Date tanggalTransaksi,
            String jenisTransaksi, BigDecimal diskon,
            BigDecimal totalBayar, String metodePembayaran,
            String statusPembayaran, int salesIdKaryawan,
            int idPelanggan) {

        this.idTransaksi = idTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.jenisTransaksi = jenisTransaksi;
        this.diskon = diskon;
        this.totalBayar = totalBayar;
        this.metodePembayaran = metodePembayaran;
        this.statusPembayaran = statusPembayaran;
        this.salesIdKaryawan = salesIdKaryawan;
        this.idPelanggan = idPelanggan;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public BigDecimal getDiskon() {
        return diskon;
    }

    public void setDiskon(BigDecimal diskon) {
        this.diskon = diskon;
    }

    public BigDecimal getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(BigDecimal totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public int getSalesIdKaryawan() {
        return salesIdKaryawan;
    }

    public void setSalesIdKaryawan(int salesIdKaryawan) {
        this.salesIdKaryawan = salesIdKaryawan;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }
}
