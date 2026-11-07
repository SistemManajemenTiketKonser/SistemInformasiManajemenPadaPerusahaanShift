/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Sparepart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class SparepartDAO {
     public boolean tambahSparepart(Sparepart sparepart) {
        String sql = "INSERT INTO sparepart "
                + "(id_sparepart, nama_sparepart, harga, satuan, "
                + "id_servis, id_transaksi) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sparepart.getIdSparepart());
            ps.setString(2, sparepart.getNamaSparepart());
            ps.setBigDecimal(3, sparepart.getHarga());
            ps.setString(4, sparepart.getSatuan());
            ps.setInt(5, sparepart.getIdServis());
            ps.setInt(6, sparepart.getIdTransaksi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan sparepart: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Sparepart> getAllSparepart() {
        ArrayList<Sparepart> daftarSparepart = new ArrayList<>();
        String sql = "SELECT * FROM sparepart";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sparepart sparepart = new Sparepart();

                sparepart.setIdSparepart(rs.getInt("id_sparepart"));
                sparepart.setNamaSparepart(rs.getString("nama_sparepart"));
                sparepart.setHarga(rs.getBigDecimal("harga"));
                sparepart.setSatuan(rs.getString("satuan"));
                sparepart.setIdServis(rs.getInt("id_servis"));
                sparepart.setIdTransaksi(rs.getInt("id_transaksi"));

                daftarSparepart.add(sparepart);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data sparepart: " + e.getMessage());
        }

        return daftarSparepart;
    }
    public boolean updateSparepart(Sparepart sparepart) {
        String sql = "UPDATE sparepart SET "
                + "nama_sparepart = ?, harga = ?, satuan = ?, "
                + "id_servis = ?, id_transaksi = ? "
                + "WHERE id_sparepart = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sparepart.getNamaSparepart());
            ps.setBigDecimal(2, sparepart.getHarga());
            ps.setString(3, sparepart.getSatuan());
            ps.setInt(4, sparepart.getIdServis());
            ps.setInt(5, sparepart.getIdTransaksi());
            ps.setInt(6, sparepart.getIdSparepart());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data sparepart: " + e.getMessage());
            return false;
        }
    }
    public boolean hapusSparepart(int idSparepart) {
        String sql = "DELETE FROM sparepart WHERE id_sparepart = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSparepart);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus sparepart: " + e.getMessage());
            return false;
        }
    }
}
