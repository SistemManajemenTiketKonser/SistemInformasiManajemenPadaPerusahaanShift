package com.bengkelshift.controller;

import com.bengkelshift.dao.TransaksiDAO;
import com.bengkelshift.model.Transaksi;
import java.util.ArrayList;

public class TransaksiController {

    private final TransaksiDAO dao = new TransaksiDAO();

    public boolean tambah(Transaksi data) {
        try {
            validasi(data);
            return dao.tambahTransaksi(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Transaksi> tampilSemua() {
        try {
            return dao.getAllTransaksi();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(Transaksi data) {
        try {
            if (data == null || data.getIdTransaksi() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateTransaksi(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat mengubah data: " + e.getMessage());
            return false;
        }
    }

    public boolean hapus(int id) {
        try {
            if (id <= 0) throw new IllegalArgumentException("ID harus lebih dari 0.");
            return dao.hapusTransaksi(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(Transaksi data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getTanggalTransaksi() == null) throw new IllegalArgumentException("Tanggal transaksi wajib diisi.");
        if (data.getTotalBayar() == null || data.getTotalBayar().signum() < 0) throw new IllegalArgumentException("Total bayar tidak valid.");
        if (data.getMetodePembayaran() == null || data.getMetodePembayaran().isBlank()) throw new IllegalArgumentException("Metode pembayaran wajib diisi.");
        if (data.getIdPelanggan() <= 0) throw new IllegalArgumentException("ID pelanggan harus valid.");
    }
}
