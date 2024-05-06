package com.aluracursos.gutendexapi_ebook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//Creamos clase record de libro para obtener los datos que estan dentro de la clave results del json
//authors lo almacenamos en una lista, ya que en el json esta igual en formato de lista. La lista sera
//de tipo AutorR y se creara la clase, ya que se mapearan sus datos contenidos
//languages tambien esta en una lista, pero se creara de String, ya que no se mapearan sus datos

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroR (
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<AutorR> autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDescargas
){
}
