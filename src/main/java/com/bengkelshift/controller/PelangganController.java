package com.bengkelshift.controller;

import com.bengkelshift.dao.PelangganDAO;
import com.bengkelshift.model.Pelanggan;
import java.util.ArrayList;

public class PelangganController {

    private final PelangganDAO dao = new PelangganDAO();

    public boolean tambah(Pelanggan data) {
        try {
            validasi(data);
            return dao.tambahPelanggan(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Pelanggan> tampilSemua() {
        try {
            return dao.getAllPelanggan();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(Pelanggan data) {
        try {
            if (data == null || data.getIdPelanggan() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updatePelanggan(data);
        } catch (IllegalArgumentException e) {
              throw e;

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat mengubah data: " + e.getMessage());
            return false;
        }
    }

    public boolean hapus(int id) {
        try {
            if (id <= 0) throw new IllegalArgumentException("ID harus lebih dari 0.");
            return dao.hapusPelanggan(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(Pelanggan data) {
        if (data == null) {
            throw new IllegalArgumentException(
                "Objek data tidak boleh null."
            );
        }

        if (data.getNamaPelanggan() == null
                || data.getNamaPelanggan().isBlank()) {
            throw new IllegalArgumentException(
                "Nama pelanggan wajib diisi."
            );
        }

        if (data.getNoHp() == null
                || data.getNoHp().isBlank()) {
            throw new IllegalArgumentException(
                "Nomor HP wajib diisi."
            );
        }

        if (!data.getNoHp().matches("[0-9]+")) {
            throw new IllegalArgumentException(
                "Nomor HP hanya boleh berisi angka."
            );
        }

        if (data.getNoHp().length() < 10
                || data.getNoHp().length() > 13) {
            throw new IllegalArgumentException(
                "Nomor HP harus terdiri dari 10 sampai 13 angka."
            );
        }
    }
}
