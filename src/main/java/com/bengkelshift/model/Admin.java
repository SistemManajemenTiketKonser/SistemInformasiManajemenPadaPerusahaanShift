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
public class Admin extends Karyawan{
    private Date tanggalMulaiBertugas;

    public Admin() {
    }

    public Admin(int idKaryawan, String namaKaryawan,
            String noHp, String alamat, String username,
            String password, String statusAktif,
            Date tanggalMulaiBertugas) {

        super(idKaryawan, namaKaryawan, noHp, alamat,
                username, password, statusAktif);

        this.tanggalMulaiBertugas = tanggalMulaiBertugas;
    }

    public Date getTanggalMulaiBertugas() {
        return tanggalMulaiBertugas;
    }

    public void setTanggalMulaiBertugas(Date tanggalMulaiBertugas) {
        this.tanggalMulaiBertugas = tanggalMulaiBertugas;
    }
    @Override
    public String getPeran(){
        return "Administrator";
    }
}
