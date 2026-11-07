/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Sales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class SalesDAO {
    public boolean tambahSales(Sales sales) {

        String sql = "INSERT INTO sales "
                + "(sales_id_karyawan, jumlah_unit_terjual) "
                + "VALUES (?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sales.getIdKaryawan());
            ps.setInt(2, sales.getJumlahUnitTerjual());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan sales: "
                    + e.getMessage());
            return false;
        }
    }
    public ArrayList<Sales> getAllSales() {
        ArrayList<Sales> daftarSales = new ArrayList<>();
        String sql = "SELECT k.*, s.jumlah_unit_terjual "
                + "FROM karyawan k "
                + "JOIN sales s "
                + "ON k.id_karyawan = s.sales_id_karyawan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Sales sales = new Sales();

                sales.setIdKaryawan(rs.getInt("id_karyawan"));
                sales.setNamaKaryawan(rs.getString("nama_karyawan"));
                sales.setNoHp(rs.getString("no_hp"));
                sales.setAlamat(rs.getString("alamat"));
                sales.setUsername(rs.getString("username"));
                sales.setPassword(rs.getString("password"));
                sales.setStatusAktif(rs.getString("status_aktif"));
                sales.setJumlahUnitTerjual(
                        rs.getInt("jumlah_unit_terjual"));

                daftarSales.add(sales);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data sales: "
                    + e.getMessage());
        }

        return daftarSales;
    }
    public boolean updateSales(Sales sales) {

        String sql = "UPDATE sales SET jumlah_unit_terjual = ? "
                + "WHERE sales_id_karyawan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sales.getJumlahUnitTerjual());
            ps.setInt(2, sales.getIdKaryawan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data sales: "
                    + e.getMessage());
            return false;
        }
    }
    public boolean hapusSales(int idKaryawan) {

        String sql = "DELETE FROM sales "
                + "WHERE sales_id_karyawan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idKaryawan);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus data sales: "
                    + e.getMessage());
            return false;
        }
    }
}
