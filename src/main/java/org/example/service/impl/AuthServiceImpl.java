package org.example.service.impl;

import org.example.mapper.AdminMapper;
import org.example.pojo.Admin;
import org.example.pojo.AdminExample;
import org.example.service.AuthService;
import org.example.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service is used for creating objects managed by Spring applicationContext
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin authentication(String name, String pwd) {

        //create an AdminExample object to encapsulate query conditions
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andANameEqualTo(name); // select * from admin where a_name = 'name'

        //authentication
        List<Admin> listAdmin = adminMapper.selectByExample(adminExample);
        if (listAdmin.size() > 0){
            Admin admin = listAdmin.get(0);
            if (MD5Util.getMD5(pwd).equals(admin.getaPass())){
                return admin;
            }
        }

        return null;
    }

    @Override
    public String register(String username, String password) throws Exception {
        String msg = null;

        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andANameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins != null){
            throw new Exception("Same user registration");
        }

        password = MD5Util.getMD5(password);
        Admin admin = new Admin();
        admin.setaName(username);
        admin.setaPass(password);

        Integer num = -1;
        try {
            num = adminMapper.insert(admin);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (num == 1){
                msg = "Register succeeds!";
            } else {
                msg = "Register fails!";
            }
        }

        return msg;
    }
}
