/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.JasaServis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class JasaServisDAO {
    public boolean tambahJasaServis(JasaServis jasa) {
        String sql = "INSERT INTO jasa_servis "
                + "(id_jasa, nama_jasa, harga_jasa, deskripsi, estimasi_menit) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jasa.getIdJasa());
            ps.setString(2, jasa.getNamaJasa());
            ps.setBigDecimal(3, jasa.getHargaJasa());
            ps.setString(4, jasa.getDeskripsi());
            ps.setInt(5, jasa.getEstimasiMenit());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan jasa servis: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<JasaServis> getAllJasaServis() {
        ArrayList<JasaServis> daftarJasa = new ArrayList<>();
        String sql = "SELECT * FROM jasa_servis";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                JasaServis jasa = new JasaServis();

                jasa.setIdJasa(rs.getInt("id_jasa"));
                jasa.setNamaJasa(rs.getString("nama_jasa"));
                jasa.setHargaJasa(rs.getBigDecimal("harga_jasa"));
                jasa.setDeskripsi(rs.getString("deskripsi"));
                jasa.setEstimasiMenit(rs.getInt("estimasi_menit"));

                daftarJasa.add(jasa);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data jasa servis: " + e.getMessage());
        }

        return daftarJasa;
    }
    public boolean updateJasaServis(JasaServis jasa) {
        String sql = "UPDATE jasa_servis SET "
                + "nama_jasa = ?, harga_jasa = ?, deskripsi = ?, "
                + "estimasi_menit = ? WHERE id_jasa = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jasa.getNamaJasa());
            ps.setBigDecimal(2, jasa.getHargaJasa());
            ps.setString(3, jasa.getDeskripsi());
            ps.setInt(4, jasa.getEstimasiMenit());
            ps.setInt(5, jasa.getIdJasa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data jasa servis: " + e.getMessage());
            return false;
        }
    }
    public boolean hapusJasaServis(int idJasa) {
        String sql = "DELETE FROM jasa_servis WHERE id_jasa = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idJasa);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus jasa servis: " + e.getMessage());
            return false;
        }
    }
}
