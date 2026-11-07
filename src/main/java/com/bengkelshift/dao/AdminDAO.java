/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class AdminDAO {
    public boolean tambahAdmin(Admin admin) {

        String sql = "INSERT INTO admin "
                + "(admin_id_karyawan, tanggal_mulai_bertugas) "
                + "VALUES (?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, admin.getIdKaryawan());
            ps.setDate(2, admin.getTanggalMulaiBertugas());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan admin: "
                    + e.getMessage());
            return false;
        }
    }
    public ArrayList<Admin> getAllAdmin() {
        ArrayList<Admin> daftarAdmin = new ArrayList<>();
        String sql = "SELECT k.*, a.tanggal_mulai_bertugas "
                + "FROM karyawan k "
                + "JOIN admin a "
                + "ON k.id_karyawan = a.admin_id_karyawan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Admin admin = new Admin();

                admin.setIdKaryawan(rs.getInt("id_karyawan"));
                admin.setNamaKaryawan(rs.getString("nama_karyawan"));
                admin.setNoHp(rs.getString("no_hp"));
                admin.setAlamat(rs.getString("alamat"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setStatusAktif(rs.getString("status_aktif"));
                admin.setTanggalMulaiBertugas(
                        rs.getDate("tanggal_mulai_bertugas"));

                daftarAdmin.add(admin);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data admin: "
                    + e.getMessage());
        }

        return daftarAdmin;
    }
    public boolean updateAdmin(Admin admin) {

        String sql = "UPDATE admin SET tanggal_mulai_bertugas = ? "
                + "WHERE admin_id_karyawan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, admin.getTanggalMulaiBertugas());
            ps.setInt(2, admin.getIdKaryawan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data admin: "
                    + e.getMessage());
            return false;
        }
    }
    public boolean hapusAdmin(int idKaryawan) {

        String sql = "DELETE FROM admin "
                + "WHERE admin_id_karyawan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idKaryawan);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus data admin: "
                    + e.getMessage());
            return false;
        }
    }
}
