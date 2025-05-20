/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista;

/**
 *
 * @author LAB-USR-LNORTE
 */
public interface IRunnableParams<T> extends Runnable {
    void vrun(T args);
}
