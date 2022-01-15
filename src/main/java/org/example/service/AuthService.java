package org.example.service;

import org.example.pojo.Admin;

public interface AuthService {
    Admin authentication(String username, String password);

    String register(String username, String password) throws Exception;
}
