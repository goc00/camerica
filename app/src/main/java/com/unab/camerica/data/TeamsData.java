package com.unab.camerica.data;

import com.unab.camerica.constants.Cons;

/**
 * Capa data para acceso a equipos
 * Utiliza herencia para abstraer conexión y manejo de datos
 */
public class TeamsData extends Connection {

    private String nodeName = Cons.FB_COUNTRIES;

    // Constructor
    public TeamsData() {
        super();
    }

}
