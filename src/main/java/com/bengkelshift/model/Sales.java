/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.model;

/**
 *
 * @author Lenovo GK
 */
public class Sales extends Karyawan{
    private int jumlahUnitTerjual;

    public Sales() {
    }

    public Sales(int idKaryawan, String namaKaryawan,
            String noHp, String alamat, String username,
            String password, String statusAktif,
            int jumlahUnitTerjual) {

        super(idKaryawan, namaKaryawan, noHp, alamat,
              username, password, statusAktif);

        this.jumlahUnitTerjual = jumlahUnitTerjual;
    }

    public int getJumlahUnitTerjual() {
        return jumlahUnitTerjual;
    }

    public void setJumlahUnitTerjual(int jumlahUnitTerjual) {
        this.jumlahUnitTerjual = jumlahUnitTerjual;
    }
    @Override
    public String getPeran(){
        return "Sales";
    }
}
