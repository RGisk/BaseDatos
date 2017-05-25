/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jquesadaabeijon
 */
public class Metodos{
    static Connection con = null;
    static Statement s = null;
    public static String db = "base1.db";
    public static String url = "jdbc:sqlite:" +db;
//    public static String user = "jesus";
//    public static String pass = "1234";
    
//    public static void crearBase(){
//        
//        String url = "jdbc:sqlite:" +db;
//        try (Connection conn = DriverManager.getConnection(url)) {
//            if (conn != null) {
//                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("The driver name is " + meta.getDriverName());
//                System.out.println("A new database has been created.");
//            }
// 
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }       
//    }
    
    /**
     * Método conectar que sirve conectar con las base de datos.
     *
     * @exception SQLException
     */
    
    public Connection conectar(){
//        String url = "jdbc:sqlite:" +db;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("El driver es " + meta.getDriverName());
                System.out.println("Se ha creado una nueva base de datos.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }       
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Estás conectado");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
    
    public void crearTabla(){
//        String url = "jdbc:sqlite:" +db;
        String sql = "CREATE TABLE IF NOT EXISTS Alumnos (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	nombre text NOT NULL,\n"
                + "	apellidos text NOT NULL,\n"
                + "     edad CHAR(2)\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Se ha creado una tabla alumnos");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo de borrado
     *
     * @exception Exception e
     */
    public static void borrar() {
        try {
            s = con.createStatement();
            String nombre = JOptionPane.showInputDialog("nombre del alumno que vas a borrar");
            s.execute("delete from Alumnos where Nombre='" + nombre + "';");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Metodo para modificar un registro de la tabla
     *
     * @exception Exception
     */
    public static void modificar() {
        try {
            s = con.createStatement();
            String nom = JOptionPane.showInputDialog("Nombre del registro que quieres modificar");
            String ape = JOptionPane.showInputDialog("Apellido nuevo");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad nueva"));

            s.execute("update Alumnos set Edad=" + edad + ",Apellidos='" + ape + "'where Nombre='" + nom + "';");

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    /**
     * Metodo para insertar registros en la base de datos
     *
     * @param edad
     * @param nombre
     * @param apellidos
     */
    public static void insertar(int edad, String nombre, String apellidos) {
        try {

            s = con.createStatement();

            s.executeUpdate("insert into Alumnos values('" + nombre + "','" + apellidos + "'," + edad + ");");
            JOptionPane.showMessageDialog(null, "Insercion realizada");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para visualizar todos lo campos de la tabla
     *
     * @exception
     */
    public static void visualizar() {
        try {
            s = con.createStatement();
            ResultSet r = s.executeQuery("select * from Alumnos");

            while (r.next()) {
                //System.out.println(r.getString("Nombre") + ", " + r.getString("Apellidos") + " " + r.getString("Edad"));
                JOptionPane.showMessageDialog(null, r.getString("Nombre") + ", " + r.getString("Apellidos") + " " + r.getString("Edad"));
            }
            r.close();

        } catch (Exception e) {
            System.out.println("ERROR ---> " + e);
        }
    }
}
