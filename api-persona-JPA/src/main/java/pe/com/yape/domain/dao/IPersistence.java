package pe.com.yape.domain.dao;

import org.springframework.data.repository.CrudRepository;

import pe.com.yape.domain.Persona;

public interface IPersistence extends CrudRepository<Persona, Integer>{

}
