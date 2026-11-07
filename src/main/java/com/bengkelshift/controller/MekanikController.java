package com.bengkelshift.controller;

import com.bengkelshift.dao.MekanikDAO;
import com.bengkelshift.model.Mekanik;
import java.util.ArrayList;

public class MekanikController {

    private final MekanikDAO dao = new MekanikDAO();

    public boolean tambah(Mekanik data) {
        try {
            validasi(data);
            return dao.tambahMekanik(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Mekanik> tampilSemua() {
        try {
            return dao.getAllMekanik();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(Mekanik data) {
        try {
            if (data == null || data.getIdKaryawan() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateMekanik(data);
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
            return dao.hapusMekanik(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(Mekanik data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getNamaKaryawan() == null || data.getNamaKaryawan().isBlank()) throw new IllegalArgumentException("Nama mekanik wajib diisi.");
        if (data.getSpesialisasi() == null || data.getSpesialisasi().isBlank()) throw new IllegalArgumentException("Spesialisasi wajib diisi.");
    }
}
