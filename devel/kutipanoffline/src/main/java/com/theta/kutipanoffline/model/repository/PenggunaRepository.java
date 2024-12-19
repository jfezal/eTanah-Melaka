package com.theta.kutipanoffline.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theta.kutipanoffline.model.entities.Pengguna;

public interface PenggunaRepository extends JpaRepository<Pengguna, String>{
Pengguna findByIdPengguna(String idPengguna);
}
