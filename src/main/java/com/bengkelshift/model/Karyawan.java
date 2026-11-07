/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.model;

/**
 *
 * @author Lenovo GK
 */
public class Karyawan extends pengguna{
    private int idKaryawan;
    private String namaKaryawan;
    private String noHp;
    private String alamat;
    private String username;
    private String password;
    private String statusAktif;

    public Karyawan() {
    }

    public Karyawan(int idKaryawan, String namaKaryawan,
            String noHp, String alamat, String username,
            String password, String statusAktif) {

        this.idKaryawan = idKaryawan;
        this.namaKaryawan = namaKaryawan;
        this.noHp = noHp;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
        this.statusAktif = statusAktif;
    }

    public int getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(int idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatusAktif() {
        return statusAktif;
    }

    public void setStatusAktif(String statusAktif) {
        this.statusAktif = statusAktif;
    }
    @Override
    public String getPeran(){
        return "Karyawan";
    }
}
