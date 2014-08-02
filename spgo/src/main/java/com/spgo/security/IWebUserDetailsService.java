package com.spgo.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IWebUserDetailsService extends UserDetailsService {
    /**
     * 
     * @param userName
     */
    public void reloadUserByUsername(String userName);
}