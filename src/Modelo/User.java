package Modelo;

import java.util.Objects;

public class User {
    int id;
    String nombre;
    public User(int id, String nombre){
        this.id = Objects.requireNonNull(id, "id es nulo");
        this.nombre = Objects.requireNonNull(nombre, "nombre es nulo");
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
