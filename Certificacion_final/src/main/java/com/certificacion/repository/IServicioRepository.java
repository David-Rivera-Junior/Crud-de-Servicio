package com.certificacion.repository;

import org.springframework.data.repository.CrudRepository;

import com.certificacion.model.Servicio;
//Entidad Primaria
public interface IServicioRepository extends CrudRepository<Servicio, Long> {

}
