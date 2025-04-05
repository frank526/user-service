package com.prueba.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.common_library.entity.Usuario;

@Repository
public interface UserRepository  extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsByPassword(String password);

    Usuario findByPassword(String password);
  
    
}
