package com.unab.camerica.data;

import com.unab.camerica.constants.Cons;
import com.unab.camerica.models.Equipo;

import java.util.List;

/**
 * Capa data para acceso a países
 * Utiliza herencia para abstraer conexión y manejo de datos
 */
public class CountriesData extends Connection {

    private String nodeName = Cons.FB_COUNTRIES;

    // Constructor
    public CountriesData() {
        super();
    }

    /**
     * Obtiene todos los países (equipos) desde Firebase
     *
     */
    public List<Equipo> getAllCountries() {
        // Utiliza doble cast para convertir la lista de objetos a una lista de objeto conocido
        List<Equipo> countries = (List<Equipo>)(Object)getElements(this.nodeName);
        return countries;
    }
}
