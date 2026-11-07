package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/** DAO pembacaan data untuk seluruh halaman administrasi. */
public class AdminDataDAO {
    private static final String[] QUERIES = new String[8];
    static {
        QUERIES[0] = "SELECT k.id_karyawan AS ID, k.nama_karyawan AS Nama, k.no_hp AS `No. Telepon`, k.alamat AS Alamat, k.username AS Username, '••••••' AS Password, k.status_aktif AS `Status Aktif`, CASE WHEN a.admin_id_karyawan IS NOT NULL THEN 'Admin' WHEN m.mekanik_id_karyawan IS NOT NULL THEN CONCAT('Mekanik - ',m.spesialisasi) WHEN s.sales_id_karyawan IS NOT NULL THEN 'Sales' ELSE 'Pegawai' END AS Peran FROM karyawan k LEFT JOIN admin a ON a.admin_id_karyawan=k.id_karyawan LEFT JOIN mekanik m ON m.mekanik_id_karyawan=k.id_karyawan LEFT JOIN sales s ON s.sales_id_karyawan=k.id_karyawan ORDER BY k.id_karyawan";
        QUERIES[1] = "SELECT kn.id_kendaraan AS ID, kn.no_polisi AS `Nomor Plat`, kn.tipe_kendaraan AS Tipe, kn.no_mesin AS `Nomor Mesin`, kn.no_rangka AS `Nomor Rangka`, p.nama_pelanggan AS Pemilik, kn.tahun_produksi AS `Tahun Produksi` FROM kendaraan kn LEFT JOIN pelanggan p ON p.id_pelanggan=kn.id_pelanggan ORDER BY kn.id_kendaraan";
        QUERIES[2] = "SELECT u.id_unit AS ID, u.tipe_kendaraan AS Tipe, u.warna AS Warna, u.tanggal_masuk AS `Tanggal Masuk`, u.status_unit AS Status, u.no_mesin AS `Nomor Mesin`, u.no_rangka AS `Nomor Rangka`, CONCAT('Transaksi #',u.id_transaksi) AS Referensi, u.tahun_produksi AS `Tahun Produksi` FROM unit_kendaraan u ORDER BY u.id_unit";
        QUERIES[3] = "SELECT id_jasa AS ID, nama_jasa AS `Nama Jasa`, harga_jasa AS Tarif, deskripsi AS Deskripsi, estimasi_menit AS `Estimasi Waktu (menit)` FROM jasa_servis ORDER BY id_jasa";
        QUERIES[4] = "SELECT id_sparepart AS ID, nama_sparepart AS Nama, harga AS Harga, satuan AS Satuan, id_servis AS `No. Servis`, id_transaksi AS `No. Transaksi` FROM sparepart ORDER BY id_sparepart";
        QUERIES[5] = "SELECT s.id_servis AS ID, s.tanggal_servis AS `Tanggal Servis`, kn.no_polisi AS `No. Plat`, s.kilometer AS Kilometer, s.keluhan AS Keluhan, k.nama_karyawan AS Mekanik, s.jadwal_servis_berikutnya AS Rekomendasi, s.diagnosis AS Diagnosis, kn.tipe_kendaraan AS `Tipe Kendaraan`, s.id_transaksi AS `No. Transaksi` FROM servis s LEFT JOIN kendaraan kn ON kn.id_kendaraan=s.id_kendaraan LEFT JOIN karyawan k ON k.id_karyawan=s.mekanik_id_karyawan ORDER BY s.id_servis";
        QUERIES[6] = "SELECT t.id_transaksi AS ID, t.tanggal_transaksi AS Tanggal, p.nama_pelanggan AS Pelanggan, k.nama_karyawan AS Sales, t.jenis_transaksi AS Jenis, t.diskon AS Diskon, t.total_bayar AS Total, t.metode_pembayaran AS Metode, t.status_pembayaran AS Status FROM transaksi t LEFT JOIN pelanggan p ON p.id_pelanggan=t.id_pelanggan LEFT JOIN karyawan k ON k.id_karyawan=t.sales_id_karyawan ORDER BY t.id_transaksi";
    }

    public DefaultTableModel getTabel(String halaman) throws SQLException {
        int index = switch (halaman) {
            case "pegawai" -> 0; case "kendaraan" -> 1; case "stok" -> 2;
            case "tarif" -> 3; case "sparepart" -> 4; case "servis" -> 5;
            case "transaksi" -> 6;
            default -> throw new IllegalArgumentException("Halaman tidak dikenal: " + halaman);
        };
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(QUERIES[index]);
             ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();
            String[] columns = new String[md.getColumnCount()];
            for (int i=0; i<columns.length; i++) columns[i] = md.getColumnLabel(i+1);
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override public boolean isCellEditable(int row, int column) { return false; }
            };
            while (rs.next()) {
                Object[] row = new Object[columns.length];
                for (int i=0; i<row.length; i++) row[i] = rs.getObject(i+1);
                model.addRow(row);
            }
            return model;
        }
    }
}
