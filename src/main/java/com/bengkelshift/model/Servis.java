/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.model;

import java.sql.Date;
/**
 *
 * @author Lenovo GK
 */
public class Servis {
    private int idServis;
    private Date tanggalServis;
    private int kilometer;
    private String keluhan;
    private String diagnosis;
    private Date jadwalServisBerikutnya;
    private int idKendaraan;
    private int mekanikIdKaryawan;
    private int idTransaksi;

    public Servis() {
    }

    public Servis(int idServis, Date tanggalServis,
            int kilometer, String keluhan, String diagnosis,
            Date jadwalServisBerikutnya, int idKendaraan,
            int mekanikIdKaryawan, int idTransaksi) {

        this.idServis = idServis;
        this.tanggalServis = tanggalServis;
        this.kilometer = kilometer;
        this.keluhan = keluhan;
        this.diagnosis = diagnosis;
        this.jadwalServisBerikutnya = jadwalServisBerikutnya;
        this.idKendaraan = idKendaraan;
        this.mekanikIdKaryawan = mekanikIdKaryawan;
        this.idTransaksi = idTransaksi;
    }

    public int getIdServis() {
        return idServis;
    }

    public void setIdServis(int idServis) {
        this.idServis = idServis;
    }

    public Date getTanggalServis() {
        return tanggalServis;
    }

    public void setTanggalServis(Date tanggalServis) {
        this.tanggalServis = tanggalServis;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getJadwalServisBerikutnya() {
        return jadwalServisBerikutnya;
    }

    public void setJadwalServisBerikutnya(Date jadwalServisBerikutnya) {
        this.jadwalServisBerikutnya = jadwalServisBerikutnya;
    }

    public int getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public int getMekanikIdKaryawan() {
        return mekanikIdKaryawan;
    }

    public void setMekanikIdKaryawan(int mekanikIdKaryawan) {
        this.mekanikIdKaryawan = mekanikIdKaryawan;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
}
