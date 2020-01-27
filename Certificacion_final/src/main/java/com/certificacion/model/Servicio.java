package com.certificacion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

//Notacion de la identidad//
@Entity
//se declara el nombre de la tabla//
@Table(name = "servicio")
public class Servicio {
//Se declara las propiedades de la clase entidad
	@Id
	//Codgio//
	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private Long codigo;

	@NotNull
	//Nombre//
	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String nombre;
	//Fecha//
	@Column(columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm")
	private Date fechaCreacion;

	@NotNull
	//Descripcion
	@Column(nullable = false, columnDefinition = "VARCHAR(200)")
	private String descripcion;

	@NotNull
	//Categoria//
	@Column(nullable = false, columnDefinition = "VARCHAR(50)")
	private String categoria;
	//Contructor Vacio
	public Servicio() {

	}
	// constructores con variables inicializadas con llave primaria//
	public Servicio(Long codigo, String nombre, Date fechaCreacion, String descripcion, String categoria) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.categoria = categoria;
	}
	// constructores con variables inicializadas sin llave primaria//
	public Servicio(Long codigo, String nombre, String descripcion, String categoria) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
	}

	public Long getCodigo() {
		return codigo;
	}
	//Generation the Getters y Setters
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
