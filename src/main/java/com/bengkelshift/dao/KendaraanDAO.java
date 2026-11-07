/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Kendaraan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class KendaraanDAO {
    public boolean tambahKendaraan(Kendaraan kendaraan) {

        String sql = "INSERT INTO kendaraan "
                + "(id_kendaraan, no_polisi, tipe_kendaraan, "
                + "no_rangka, no_mesin, tahun_produksi, id_pelanggan) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, kendaraan.getIdKendaraan());
            ps.setString(2, kendaraan.getNoPolisi());
            ps.setString(3, kendaraan.getTipeKendaraan());
            ps.setString(4, kendaraan.getNoRangka());
            ps.setString(5, kendaraan.getNoMesin());
            ps.setInt(6, kendaraan.getTahunProduksi());
            ps.setInt(7, kendaraan.getIdPelanggan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan kendaraan: "
                    + e.getMessage());
            return false;
        }
    }
   public ArrayList<Kendaraan> getAllKendaraan() {
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

        String sql = "SELECT * FROM kendaraan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Kendaraan kendaraan = new Kendaraan();

                kendaraan.setIdKendaraan(rs.getInt("id_kendaraan"));
                kendaraan.setNoPolisi(rs.getString("no_polisi"));
                kendaraan.setTipeKendaraan(rs.getString("tipe_kendaraan"));
                kendaraan.setNoRangka(rs.getString("no_rangka"));
                kendaraan.setNoMesin(rs.getString("no_mesin"));
                kendaraan.setTahunProduksi(rs.getInt("tahun_produksi"));
                kendaraan.setIdPelanggan(rs.getInt("id_pelanggan"));

                daftarKendaraan.add(kendaraan);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data kendaraan: "
                    + e.getMessage());
        }

        return daftarKendaraan;
    }
    public boolean updateKendaraan(Kendaraan kendaraan) {

    String sql = "UPDATE kendaraan SET "
            + "no_polisi = ?, "
            + "tipe_kendaraan = ?, "
            + "no_rangka = ?, "
            + "no_mesin = ?, "
            + "tahun_produksi = ?, "
            + "id_pelanggan = ? "
            + "WHERE id_kendaraan = ?";

    try (Connection conn = Koneksi.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, kendaraan.getNoPolisi());
        ps.setString(2, kendaraan.getTipeKendaraan());
        ps.setString(3, kendaraan.getNoRangka());
        ps.setString(4, kendaraan.getNoMesin());
        ps.setInt(5, kendaraan.getTahunProduksi());
        ps.setInt(6, kendaraan.getIdPelanggan());
        ps.setInt(7, kendaraan.getIdKendaraan());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Gagal mengubah data kendaraan: "
                + e.getMessage());
        return false;
    }
    }
    public boolean hapusKendaraan(int idKendaraan) {

    String sql = "DELETE FROM kendaraan WHERE id_kendaraan = ?";

    try (Connection conn = Koneksi.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idKendaraan);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Gagal menghapus data kendaraan: "
                + e.getMessage());
        return false;
    }
    }
}
