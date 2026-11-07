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
public class JasaServis {
    private int idJasa;
    private String namaJasa;
    private BigDecimal hargaJasa;
    private String deskripsi;
    private int estimasiMenit;

    public JasaServis() {
    }

    public JasaServis(int idJasa, String namaJasa,
            BigDecimal hargaJasa, String deskripsi,
            int estimasiMenit) {

        this.idJasa = idJasa;
        this.namaJasa = namaJasa;
        this.hargaJasa = hargaJasa;
        this.deskripsi = deskripsi;
        this.estimasiMenit = estimasiMenit;
    }

    public int getIdJasa() {
        return idJasa;
    }

    public void setIdJasa(int idJasa) {
        this.idJasa = idJasa;
    }

    public String getNamaJasa() {
        return namaJasa;
    }

    public void setNamaJasa(String namaJasa) {
        this.namaJasa = namaJasa;
    }

    public BigDecimal getHargaJasa() {
        return hargaJasa;
    }

    public void setHargaJasa(BigDecimal hargaJasa) {
        this.hargaJasa = hargaJasa;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getEstimasiMenit() {
        return estimasiMenit;
    }

    public void setEstimasiMenit(int estimasiMenit) {
        this.estimasiMenit = estimasiMenit;
    }
}
