package com.bolsadeideas.springbootjpa.models.dao;


import com.bolsadeideas.springbootjpa.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente,Long> {




}
