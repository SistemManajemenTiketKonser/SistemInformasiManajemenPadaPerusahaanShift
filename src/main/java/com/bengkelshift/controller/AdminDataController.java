package com.bengkelshift.controller;

import com.bengkelshift.dao.AdminDataDAO;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/** Controller untuk menyediakan data tabel pada GUI Admin. */
public class AdminDataController {
    private final AdminDataDAO dao = new AdminDataDAO();
    public DefaultTableModel tampilkan(String halaman) throws SQLException {
        return dao.getTabel(halaman);
    }
}
