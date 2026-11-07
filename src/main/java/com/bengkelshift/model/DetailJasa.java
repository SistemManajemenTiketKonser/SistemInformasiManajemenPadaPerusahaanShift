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
public class DetailJasa {
    private int idDetail;
    private BigDecimal hargaJasaSaatServis;
    private int idServis;
    private int idJasa;

    public DetailJasa() {
    }

    public DetailJasa(int idDetail,
            BigDecimal hargaJasaSaatServis,
            int idServis, int idJasa) {

        this.idDetail = idDetail;
        this.hargaJasaSaatServis = hargaJasaSaatServis;
        this.idServis = idServis;
        this.idJasa = idJasa;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public BigDecimal getHargaJasaSaatServis() {
        return hargaJasaSaatServis;
    }

    public void setHargaJasaSaatServis(BigDecimal hargaJasaSaatServis) {
        this.hargaJasaSaatServis = hargaJasaSaatServis;
    }

    public int getIdServis() {
        return idServis;
    }

    public void setIdServis(int idServis) {
        this.idServis = idServis;
    }

    public int getIdJasa() {
        return idJasa;
    }

    public void setIdJasa(int idJasa) {
        this.idJasa = idJasa;
    }
}
