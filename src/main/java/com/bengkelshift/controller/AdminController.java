package com.bengkelshift.controller;

import com.bengkelshift.dao.AdminDAO;
import com.bengkelshift.model.Admin;
import java.util.ArrayList;

public class AdminController {
    private final AdminDAO dao = new AdminDAO();

    public boolean tambah(Admin data) {
        if (data == null || data.getIdKaryawan() <= 0 || data.getTanggalMulaiBertugas() == null) {
            System.out.println("Data admin tidak valid.");
            return false;
        }
        return dao.tambahAdmin(data);
    }

    public ArrayList<Admin> tampilSemua() {
        return dao.getAllAdmin();
    }

    public boolean ubah(Admin data) {
        if (data == null || data.getIdKaryawan() <= 0 || data.getTanggalMulaiBertugas() == null) {
            System.out.println("Data admin tidak valid.");
            return false;
        }
        return dao.updateAdmin(data);
    }

    public boolean hapus(int idKaryawan) {
        if (idKaryawan <= 0) {
            System.out.println("ID karyawan harus lebih dari 0.");
            return false;
        }
        return dao.hapusAdmin(idKaryawan);
    }
}
