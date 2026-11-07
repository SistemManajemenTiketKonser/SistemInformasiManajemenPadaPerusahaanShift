/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.model;

/**
 *
 * @author Lenovo GK
 */
public class Mekanik extends Karyawan{
    private String spesialisasi;

    public Mekanik() {
    }

    public Mekanik(int idKaryawan, String namaKaryawan,
            String noHp, String alamat, String username,
            String password, String statusAktif,
            String spesialisasi) {

        super(idKaryawan, namaKaryawan, noHp, alamat,
                username, password, statusAktif);

        this.spesialisasi = spesialisasi;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }
    @Override
    public String getPeran(){
        return "Mekanik";
    }
}
