package com.tirthongo.controller;

import com.tirthongo.dao.AdminDao;
import com.tirthongo.dao.userdoc;

public class SignUpController {

    public static boolean signUpWithEmailAndPasswordUser(String email, String password) {
        return userdoc.signUpUser(email, password);
    }

     public static boolean signUpAdminWithEmailAndPasswordAdmin(String email, String password) {
        return AdminDao.signUpAdminWithEmailAndPasswordAdmin(email, password);
    }
}
