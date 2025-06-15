/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Modelo;

/**
 *
 * @author Angelo
 */
public interface SujetoObservado {
    void registrarObservador(Observador o);
    void eliminarObservador(Observador o);
    void notificarObservadores(String mensaje);
}
