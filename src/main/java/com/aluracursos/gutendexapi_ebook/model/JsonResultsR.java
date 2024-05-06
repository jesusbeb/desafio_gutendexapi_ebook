package com.aluracursos.gutendexapi_ebook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//Creamos una clase record para obtener primero los datos de la clave "results" del json, ya que dentro de
//esta clave se encuentran otras claves con los datos que nos interesan. Estas claves que estan
//dentro de results se guardaran en una lista de tipo LibroR. Y se creara la clase LibroR para que
//contenga las claves que estan dentro de "results"

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonResultsR(
        @JsonAlias("results") List<LibroR> libros
){

}
