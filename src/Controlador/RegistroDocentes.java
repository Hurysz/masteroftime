package Controlador;

import Modelo.ConsultasRegistro;

public class RegistroDocentes {

    public static Object get(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    int id;
    String facultad;
    String carrera;
    String nombre;
    String fecha;
    String hora;
    String correo;
    ConsultasRegistro rg = new ConsultasRegistro();

    public RegistroDocentes() {
    }

    public RegistroDocentes(int id, String facultad, String carrera, String nombredocente, String fecharegistrada, String horaregistrada, String correo) {
        this.id = id;
        this.facultad = facultad;
        this.carrera = carrera;
        this.nombre = nombredocente;
        this.fecha = fecharegistrada;
        this.hora = horaregistrada;
        this.correo = correo;
    }

    public RegistroDocentes(String facultad, String carrera, String nombre, String fecha, String hora, String correo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNombredocente() {
        return nombre;
    }

    public void setNombredocente(String nombredocente) {
        this.nombre = nombredocente;
    }

    public String getFecharegistrada() {
        return fecha;
    }

    public void setFecharegistrada(String fecharegistrada) {
        this.fecha = fecharegistrada;
    }

    public String getHoraregistrada() {
        return hora;
    }

    public void setHoraregistrada(String horaregistrada) {
        this.hora = horaregistrada;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void sync() {
        rg.modificar(this);
    }

    @Override
    public String toString() {
        return "RegistroDocentes Id=" + id
                + "\\nFacultad=" + facultad
                + "\\nCarrera=" + carrera
                + "\\nNombreDocente=" + nombre
                + "\\nFecharegistrada=" + fecha
                + "\\nHora Registrada=" + hora
                + "\\nCorreo Registrada=" + correo+'}';
    }

}
