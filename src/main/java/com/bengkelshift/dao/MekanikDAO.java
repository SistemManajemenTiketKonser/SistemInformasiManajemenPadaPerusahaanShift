/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Mekanik;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class MekanikDAO {
    public boolean tambahMekanik(Mekanik mekanik) {

        String sql = "INSERT INTO mekanik "
                + "(mekanik_id_karyawan, spesialisasi) "
                + "VALUES (?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, mekanik.getIdKaryawan());
            ps.setString(2, mekanik.getSpesialisasi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan mekanik: "
                    + e.getMessage());
            return false;
        }
    }
    public ArrayList<Mekanik> getAllMekanik() {
        ArrayList<Mekanik> daftarMekanik = new ArrayList<>();
        String sql = "SELECT k.*, m.spesialisasi "
                + "FROM karyawan k "
                + "JOIN mekanik m "
                + "ON k.id_karyawan = m.mekanik_id_karyawan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Mekanik mekanik = new Mekanik();

                mekanik.setIdKaryawan(rs.getInt("id_karyawan"));
                mekanik.setNamaKaryawan(rs.getString("nama_karyawan"));
                mekanik.setNoHp(rs.getString("no_hp"));
                mekanik.setAlamat(rs.getString("alamat"));
                mekanik.setUsername(rs.getString("username"));
                mekanik.setPassword(rs.getString("password"));
                mekanik.setStatusAktif(rs.getString("status_aktif"));
                mekanik.setSpesialisasi(rs.getString("spesialisasi"));

                daftarMekanik.add(mekanik);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data mekanik: "
                    + e.getMessage());
        }

        return daftarMekanik;
    }
    public boolean updateMekanik(Mekanik mekanik) {

        String sql = "UPDATE mekanik SET spesialisasi = ? "
                + "WHERE mekanik_id_karyawan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mekanik.getSpesialisasi());
            ps.setInt(2, mekanik.getIdKaryawan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data mekanik: "
                    + e.getMessage());
            return false;
        }
    }
    public boolean hapusMekanik(int idKaryawan) {

        String sql = "DELETE FROM mekanik "
                + "WHERE mekanik_id_karyawan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idKaryawan);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus data mekanik: "
                    + e.getMessage());
            return false;
        }
    }
}
