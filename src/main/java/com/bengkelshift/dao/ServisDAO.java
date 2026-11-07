/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Servis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class ServisDAO {
    public boolean tambahServis(Servis servis) {
        String sql = "INSERT INTO servis "
                + "(id_servis, tanggal_servis, kilometer, keluhan, diagnosis, "
                + "jadwal_servis_berikutnya, id_kendaraan, "
                + "mekanik_id_karyawan, id_transaksi) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, servis.getIdServis());
            ps.setDate(2, servis.getTanggalServis());
            ps.setInt(3, servis.getKilometer());
            ps.setString(4, servis.getKeluhan());
            ps.setString(5, servis.getDiagnosis());
            ps.setDate(6, servis.getJadwalServisBerikutnya());
            ps.setInt(7, servis.getIdKendaraan());
            ps.setInt(8, servis.getMekanikIdKaryawan());
            ps.setInt(9, servis.getIdTransaksi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data servis: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Servis> getAllServis() {
        ArrayList<Servis> daftarServis = new ArrayList<>();
        String sql = "SELECT * FROM servis";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Servis servis = new Servis();

                servis.setIdServis(rs.getInt("id_servis"));
                servis.setTanggalServis(rs.getDate("tanggal_servis"));
                servis.setKilometer(rs.getInt("kilometer"));
                servis.setKeluhan(rs.getString("keluhan"));
                servis.setDiagnosis(rs.getString("diagnosis"));
                servis.setJadwalServisBerikutnya(
                        rs.getDate("jadwal_servis_berikutnya"));
                servis.setIdKendaraan(rs.getInt("id_kendaraan"));
                servis.setMekanikIdKaryawan(
                        rs.getInt("mekanik_id_karyawan"));
                servis.setIdTransaksi(rs.getInt("id_transaksi"));

                daftarServis.add(servis);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data servis: " + e.getMessage());
        }

        return daftarServis;
    }
    public boolean updateServis(Servis servis) {
        String sql = "UPDATE servis SET "
                + "tanggal_servis = ?, kilometer = ?, keluhan = ?, "
                + "diagnosis = ?, jadwal_servis_berikutnya = ?, "
                + "id_kendaraan = ?, mekanik_id_karyawan = ?, "
                + "id_transaksi = ? WHERE id_servis = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, servis.getTanggalServis());
            ps.setInt(2, servis.getKilometer());
            ps.setString(3, servis.getKeluhan());
            ps.setString(4, servis.getDiagnosis());
            ps.setDate(5, servis.getJadwalServisBerikutnya());
            ps.setInt(6, servis.getIdKendaraan());
            ps.setInt(7, servis.getMekanikIdKaryawan());
            ps.setInt(8, servis.getIdTransaksi());
            ps.setInt(9, servis.getIdServis());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data servis: " + e.getMessage());
            return false;
        }
    }
    public boolean hapusServis(int idServis) {
        String sql = "DELETE FROM servis WHERE id_servis = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idServis);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus data servis: " + e.getMessage());
            return false;
        }
    }
}
