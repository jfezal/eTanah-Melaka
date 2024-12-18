package com.theta.kutipanoffline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.theta.kutipanoffline.model.entities.Pengguna;
import com.theta.kutipanoffline.model.entities.PerananPengguna;
import com.theta.kutipanoffline.model.repository.PenggunaRepository;
import com.theta.kutipanoffline.model.repository.PerananPenggunaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private PenggunaRepository penggunaRepository;
 
    @Autowired
    private PerananPenggunaRepository perananPenggunaRepository;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Pengguna pengguna = this.penggunaRepository.findByIdPengguna(userName);
 
        if (pengguna == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        System.out.println("Found User: " + pengguna);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<PerananPengguna> listPeranan = perananPenggunaRepository.findByPengguna(pengguna);
        List<String> roleNames = new ArrayList<String>();
        for(PerananPengguna pp:listPeranan) {
        	String a = pp.getKodPeranan().getKod();
        	roleNames.add(a);
        }
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(pengguna.getIdPengguna(), //
        		pengguna.getKatalaluan(), grantList);
 
        return userDetails;
    }
 
}
