/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo GK
 */
public class PelangganDAO {
    public boolean tambahPelanggan(Pelanggan pelanggan) {

        String sql = "INSERT INTO pelanggan (id_pelanggan, nama_pelanggan, no_hp) VALUES (?, ?, ?)";

        try (Connection conn = Koneksi.getConnection()) {
            conn.setAutoCommit(false);
            try {
                int nextId;
                try (PreparedStatement idQuery = conn.prepareStatement("SELECT COALESCE(MAX(id_pelanggan),0)+1 FROM pelanggan");
                     ResultSet rs = idQuery.executeQuery()) {
                    rs.next(); nextId = rs.getInt(1);
                }
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, nextId);
                    ps.setString(2, pelanggan.getNamaPelanggan());
                    ps.setString(3, pelanggan.getNoHp());
                    boolean ok = ps.executeUpdate() > 0;
                    conn.commit();
                    return ok;
                }
            } catch (SQLException ex) { conn.rollback(); throw ex; }
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan pelanggan: "
                    + e.getMessage());
            return false;
        }
    }
    public ArrayList<Pelanggan> getAllPelanggan() {
        ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();

        String sql = "SELECT * FROM pelanggan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Pelanggan pelanggan = new Pelanggan();

                pelanggan.setIdPelanggan(rs.getInt("id_pelanggan"));
                pelanggan.setNamaPelanggan(rs.getString("nama_pelanggan"));
                pelanggan.setNoHp(rs.getString("no_hp"));

                daftarPelanggan.add(pelanggan);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pelanggan: "
                    + e.getMessage());
        }

        return daftarPelanggan;
    }
    public boolean updatePelanggan(Pelanggan pelanggan) {

    String sql = "UPDATE pelanggan SET nama_pelanggan = ?, no_hp = ? "
            + "WHERE id_pelanggan = ?";

    try (Connection conn = Koneksi.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, pelanggan.getNamaPelanggan());
        ps.setString(2, pelanggan.getNoHp());
        ps.setInt(3, pelanggan.getIdPelanggan());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Gagal mengubah data pelanggan: "
                + e.getMessage());
        return false;
    }
    }
    public boolean hapusPelanggan(int idPelanggan) {

    String sql = "DELETE FROM pelanggan WHERE id_pelanggan = ?";

    try (Connection conn = Koneksi.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idPelanggan);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Gagal menghapus data pelanggan: "
                + e.getMessage());
        return false;
    }
    }
}