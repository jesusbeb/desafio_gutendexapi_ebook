package com.aluracursos.gutendexapi_ebook.service;

public interface IConvierteDatos {

    <T> T JsonToClass(String json, Class<T> clase);
}
