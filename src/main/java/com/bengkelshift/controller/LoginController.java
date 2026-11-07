package com.bengkelshift.controller;

import com.bengkelshift.dao.AdminDAO;
import com.bengkelshift.dao.KaryawanDAO;
import com.bengkelshift.model.Admin;
import com.bengkelshift.model.Karyawan;

public class LoginController {

    private final KaryawanDAO karyawanDAO = new KaryawanDAO();
    private final AdminDAO adminDAO = new AdminDAO();

    public Karyawan login(String username, String password) {

        if (username == null || username.trim().isEmpty()
                || password == null || password.isEmpty()) {
            return null;
        }

        return karyawanDAO.login(username.trim(), password);
    }

    public boolean isAdmin(Karyawan pengguna) {

        if (pengguna == null) {
            return false;
        }

        for (Admin admin : adminDAO.getAllAdmin()) {
            if (admin.getIdKaryawan() == pengguna.getIdKaryawan()) {
                return true;
            }
        }

        return false;
    }
}