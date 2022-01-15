package pe.com.softtek.domain.dao;

import org.springframework.data.repository.CrudRepository;

import pe.com.softtek.domain.Persona;

public interface IPersistence extends CrudRepository<Persona, Integer>{

}
