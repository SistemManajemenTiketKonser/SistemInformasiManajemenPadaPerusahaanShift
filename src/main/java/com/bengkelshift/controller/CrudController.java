package com.bengkelshift.controller;

import com.bengkelshift.dao.CrudDAO;
import java.sql.SQLException;
import java.util.Map;

/** Controller validasi dan orkestrasi CRUD. */
public class CrudController {
    private final CrudDAO dao = new CrudDAO();
    public CrudDAO.Entity entity(String key){return dao.entity(key);}
    public Map<String,Object> get(String key,int id)throws SQLException{return dao.get(key,id);}
    public boolean tambah(String key,Map<String,Object> values)throws SQLException{validate(key,values);return dao.insert(key,values);}
    public boolean ubah(String key,int id,Map<String,Object> values)throws SQLException{if(id<=0)throw new IllegalArgumentException("Pilih data yang akan diubah.");validate(key,values);return dao.update(key,id,values);}
    public boolean hapus(String key,int id)throws SQLException{if(id<=0)throw new IllegalArgumentException("Pilih data yang akan dihapus.");return dao.delete(key,id);}
    private void validate(String key,Map<String,Object> v){for(CrudDAO.Field f:entity(key).fields()){Object x=v.get(f.name());if(x==null&&!f.nullable())throw new IllegalArgumentException(f.label()+" wajib diisi.");if(x instanceof String s&&s.isBlank()&&!f.nullable())throw new IllegalArgumentException(f.label()+" wajib diisi.");}}
}
