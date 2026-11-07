/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.DetailJasa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class DetailJasaDAO {
     public boolean tambahDetailJasa(DetailJasa detail) {
        String sql = "INSERT INTO detail_jasa_servis "
                + "(id_detail, harga_jasa_saat_servis, id_servis, id_jasa) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detail.getIdDetail());
            ps.setBigDecimal(2, detail.getHargaJasaSaatServis());
            ps.setInt(3, detail.getIdServis());
            ps.setInt(4, detail.getIdJasa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan detail jasa: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<DetailJasa> getAllDetailJasa() {
        ArrayList<DetailJasa> daftarDetail = new ArrayList<>();
        String sql = "SELECT * FROM detail_jasa_servis";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DetailJasa detail = new DetailJasa();

                detail.setIdDetail(rs.getInt("id_detail"));
                detail.setHargaJasaSaatServis(
                        rs.getBigDecimal("harga_jasa_saat_servis"));
                detail.setIdServis(rs.getInt("id_servis"));
                detail.setIdJasa(rs.getInt("id_jasa"));

                daftarDetail.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data detail jasa: " + e.getMessage());
        }

        return daftarDetail;
    }
    public boolean updateDetailJasa(DetailJasa detail) {
        String sql = "UPDATE detail_jasa_servis SET "
                + "harga_jasa_saat_servis = ?, id_servis = ?, id_jasa = ? "
                + "WHERE id_detail = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, detail.getHargaJasaSaatServis());
            ps.setInt(2, detail.getIdServis());
            ps.setInt(3, detail.getIdJasa());
            ps.setInt(4, detail.getIdDetail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah detail jasa: " + e.getMessage());
            return false;
        }
    }
    public boolean hapusDetailJasa(int idDetail) {
        String sql = "DELETE FROM detail_jasa_servis WHERE id_detail = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDetail);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus detail jasa: " + e.getMessage());
            return false;
        }
    }
}
