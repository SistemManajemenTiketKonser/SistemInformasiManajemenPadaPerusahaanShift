/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Karyawan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class KaryawanDAO {
    public boolean tambahKaryawan(Karyawan karyawan) {

        String sql = "INSERT INTO karyawan "
                + "(id_karyawan, nama_karyawan, no_hp, alamat, "
                + "username, password, status_aktif) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, karyawan.getIdKaryawan());
            ps.setString(2, karyawan.getNamaKaryawan());
            ps.setString(3, karyawan.getNoHp());
            ps.setString(4, karyawan.getAlamat());
            ps.setString(5, karyawan.getUsername());
            ps.setString(6, karyawan.getPassword());
            ps.setString(7, karyawan.getStatusAktif());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan karyawan: "
                    + e.getMessage());
            return false;
        }
    }
   public ArrayList<Karyawan> getAllKaryawan() {
        ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();
        String sql = "SELECT * FROM karyawan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Karyawan karyawan = new Karyawan();

                karyawan.setIdKaryawan(rs.getInt("id_karyawan"));
                karyawan.setNamaKaryawan(rs.getString("nama_karyawan"));
                karyawan.setNoHp(rs.getString("no_hp"));
                karyawan.setAlamat(rs.getString("alamat"));
                karyawan.setUsername(rs.getString("username"));
                karyawan.setPassword(rs.getString("password"));
                karyawan.setStatusAktif(rs.getString("status_aktif"));

                daftarKaryawan.add(karyawan);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data karyawan: "
                    + e.getMessage());
        }

        return daftarKaryawan;
    }
    public boolean updateKaryawan(Karyawan karyawan) {

        String sql = "UPDATE karyawan SET "
                + "nama_karyawan = ?, no_hp = ?, alamat = ?, "
                + "username = ?, password = ?, status_aktif = ? "
                + "WHERE id_karyawan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, karyawan.getNamaKaryawan());
            ps.setString(2, karyawan.getNoHp());
            ps.setString(3, karyawan.getAlamat());
            ps.setString(4, karyawan.getUsername());
            ps.setString(5, karyawan.getPassword());
            ps.setString(6, karyawan.getStatusAktif());
            ps.setInt(7, karyawan.getIdKaryawan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data karyawan: "
                    + e.getMessage());
            return false;
        }
    }
    public boolean hapusKaryawan(int idKaryawan) {

        String sql = "DELETE FROM karyawan WHERE id_karyawan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idKaryawan);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus data karyawan: "
                    + e.getMessage());
            return false;
        }
    }
    public Karyawan login(String username, String password) {

        String sql = "SELECT * FROM karyawan "
                + "WHERE username = ? AND password = ? "
                + "AND status_aktif = 'aktif'";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Karyawan karyawan = new Karyawan();

                    karyawan.setIdKaryawan(rs.getInt("id_karyawan"));
                    karyawan.setNamaKaryawan(rs.getString("nama_karyawan"));
                    karyawan.setNoHp(rs.getString("no_hp"));
                    karyawan.setAlamat(rs.getString("alamat"));
                    karyawan.setUsername(rs.getString("username"));
                    karyawan.setPassword(rs.getString("password"));
                    karyawan.setStatusAktif(rs.getString("status_aktif"));

                    return karyawan;
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal melakukan login: "
                    + e.getMessage());
        }

        return null;
    }
}
