package com.bolsadeideas.springbootjpa.models.dao;


import com.bolsadeideas.springbootjpa.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IClienteDao extends JpaRepository<Cliente, Long> {




}
