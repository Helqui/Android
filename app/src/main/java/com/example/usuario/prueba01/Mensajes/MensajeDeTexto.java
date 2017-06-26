package com.example.usuario.prueba01.Mensajes;

/**
 * Created by Usuario on 26/05/2017.
 */

public class MensajeDeTexto {
    private String id;
    private String mensaje;
    private int tipoMensaje;
    private String horaDelMensaje;

    public MensajeDeTexto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getHoraDelMensaje() {
        return horaDelMensaje;
    }

    public void setHoraDelMensaje(String horaDelMensaje) {
        this.horaDelMensaje = horaDelMensaje;
    }
}
