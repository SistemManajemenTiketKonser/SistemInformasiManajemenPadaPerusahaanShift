package com.bengkelshift.controller;

import com.bengkelshift.dao.ServisDAO;
import com.bengkelshift.model.Servis;
import java.util.ArrayList;

public class ServisController {

    private final ServisDAO dao = new ServisDAO();

    public boolean tambah(Servis data) {
        try {
            validasi(data);
            return dao.tambahServis(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Servis> tampilSemua() {
        try {
            return dao.getAllServis();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(Servis data) {
        try {
            if (data == null || data.getIdServis() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateServis(data);
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
            return dao.hapusServis(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(Servis data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getTanggalServis() == null) throw new IllegalArgumentException("Tanggal servis wajib diisi.");
        if (data.getKilometer() < 0) throw new IllegalArgumentException("Kilometer tidak boleh negatif.");
        if (data.getKeluhan() == null || data.getKeluhan().isBlank()) throw new IllegalArgumentException("Keluhan wajib diisi.");
        if (data.getIdKendaraan() <= 0 || data.getMekanikIdKaryawan() <= 0) throw new IllegalArgumentException("ID kendaraan dan mekanik harus valid.");
    }
}
