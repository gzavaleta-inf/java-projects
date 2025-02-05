package pe.com.bcp.domain.dao;

import org.springframework.data.repository.CrudRepository;

import pe.com.bcp.domain.Persona;

public interface IPersistence extends CrudRepository<Persona, Integer>{

}
