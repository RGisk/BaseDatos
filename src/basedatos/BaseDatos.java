/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

/**
 *
 * @author jquesadaabeijon
 */
public class BaseDatos{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Metodos met = new Metodos();
//        met.crearBase();
        met.conectar();
        met.crearTabla();
        Ventana base = new Ventana();
        base.setVisible(true);
    }
    
}
