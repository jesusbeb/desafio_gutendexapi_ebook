package com.aluracursos.gutendexapi_ebook.principal;

import com.aluracursos.gutendexapi_ebook.model.JsonResultsR;
import com.aluracursos.gutendexapi_ebook.model.LibroR;
import com.aluracursos.gutendexapi_ebook.service.ConsumoAPI;
import com.aluracursos.gutendexapi_ebook.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    //variables e instancias
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);


    public void Menu(){

        //Traemos todos los resultados de la API
        var json = consumoApi.obtenerDatosApi(URL_BASE);
        System.out.println("\nJson de la respuesta de la Api: " +json);

        //--Traemos e imprimimos la List que contiene todos los libros que se obtuvieron del
        //json y se almacenan dentro de JsonResultsR y la guardamos en la var listaLibros
        //usamos el metodo JsonToClass de la instancia convierteDatos, y le enviamos el json junto
        //con la clase a la que se convertira.
        var listaLibros = convierteDatos.JsonToClass(json, JsonResultsR.class);
        System.out.println("\nJson convertido a la clase que contiene una lista java: " +listaLibros);

        //--Top 10 de libros mas descargados
        //A la lista de libros le aplicamos stream con operaciones intermediarias
        //map() l para cada libro -> se toma el titulo de cada libro y se convierte en mayusculas
        System.out.println("\nTop 10 de libros mas descargados: ");
        listaLibros.libros().stream()
                .sorted(Comparator.comparing(LibroR::numeroDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);

        //--Estadisticas
        System.out.println("\nEstadisticas de descargas de todos los libros: ");
        DoubleSummaryStatistics est = listaLibros.libros().stream()
                .collect((Collectors.summarizingDouble(LibroR::numeroDescargas)));
        System.out.println("Libro mas descargado: " +est.getMax());
        System.out.println("Libro menos descargado: " +est.getMin());
        System.out.println("Cantidad media de descargas: " +est.getAverage());
        System.out.println("Cantidad de registros evaluados para calcular las estadisticas: " +est.getCount());


        //Buscar libros por nombre
        //Modificamos la URL almacenada en json, ya que permite busquedas usando "?search= "
        //Guardamos los resultados de la busqueda hecha en la API en datosBusqueda
        //Creamos la variable local Optional de tipo LibroR que es el contenedor que almacenara
        //el resultado si es encontrado o no
        //Aplicamos stream a la List libros que esta en la variable datosBusqueda
        System.out.println("\nEscribe el nombre o parte del libro que desea buscar: ");
        var buscarLibro = teclado.nextLine();
        json = consumoApi.obtenerDatosApi(URL_BASE + "?search=" + buscarLibro.replace(" ", "%20"));
        var datosBusqueda = convierteDatos.JsonToClass(json, JsonResultsR.class);
        Optional<LibroR> libroBuscado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(buscarLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado:" +libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado.");
        }

        

    }
}
