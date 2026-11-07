package com.bengkelshift.controller;

import com.bengkelshift.dao.KaryawanDAO;
import com.bengkelshift.model.Karyawan;
import java.util.ArrayList;

public class KaryawanController {

    private final KaryawanDAO dao = new KaryawanDAO();

    public boolean tambah(Karyawan data) {
        try {
            validasi(data);
            return dao.tambahKaryawan(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Karyawan> tampilSemua() {
        try {
            return dao.getAllKaryawan();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(Karyawan data) {
        try {
            if (data == null || data.getIdKaryawan() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateKaryawan(data);
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
            return dao.hapusKaryawan(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(Karyawan data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getNamaKaryawan() == null || data.getNamaKaryawan().isBlank()) throw new IllegalArgumentException("Nama karyawan wajib diisi.");
        if (data.getUsername() == null || data.getUsername().isBlank()) throw new IllegalArgumentException("Username wajib diisi.");
        if (data.getPassword() == null || data.getPassword().isBlank()) throw new IllegalArgumentException("Password wajib diisi.");
    }
}
