package aplicacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectorBD {

    private String bd = "test";
    private String login = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost/" + bd;
    private Connection conn = null;

    /**
     * Constructor de DbConnection
     */
    public ConectorBD() {
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos la conexiÃ³n
            conn = DriverManager.getConnection(url, login, password);
            if (conn != null) {
                System.out.println("Coneccion a base de datos " + bd + ". lista");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Permite retornar la conexión
     */
    public Connection getConnection() {
        return conn;
    }

    public void desconectar() {
        conn = null;
        System.out.println("La conexion a la  base de datos " + bd + " ha terminado");
    }

    public boolean insertarPersona(Persona p) {

        String nombre = p.getNomPerso();
        int id = p.getId();
        int numDep = p.getNumDep();

        String consulta = "INSERT INTO persona(nombrePersona,identificador,numDepartamento)  VALUES('"
                + nombre + "','" + id + "','" + numDep + "')";
        boolean flag = false;

        try {
            //Creamos el objeto Statement para enviar la orden de inserciÃ³n de la nueva
            //persona.
            Statement orden = conn.createStatement();

            orden.executeUpdate(consulta);
            flag = true;

            orden.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public boolean buscarDp(int numDep) {

        String instruccion = "SELECT * FROM departamento WHERE numDepto =" + numDep;

        try {
            Statement orden = conn.createStatement(); //EJECUTO LA CONSULTA
            ResultSet res;
            res = orden.executeQuery(instruccion);

            while (res.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    //Actualizamos una persona dado su nombre
    public boolean actualizarPersona(Persona p, String nom) {

        boolean flag = false;
        String nombre = p.getNomPerso();
        int numDep = p.getNumDep();

        String consulta = "UPDATE persona SET nombrePersona = ? " + " ,"
                + " numDepartamento = ? "
                + " WHERE nombrePersona = ?";

        try {
            //Creamos el objeto Statement para enviar la orden de actualizacion de la
            //persona.
            PreparedStatement pr = null;
            pr = conn.prepareStatement(consulta);

            pr.setString(1, nombre);
            pr.setInt(2, numDep);
            pr.setString(3, nom);

            if (pr.executeUpdate() > 0) {

                flag = true;

            }

            pr.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    //Borramos una persona por su id
    public boolean borrarPersona(Persona p) {
        String nombre = p.getNomPerso();
        boolean flag = false;
        try {
            //Creamos el objeto Statement para enviar la orden de borrado de la
            //persona.
            Statement orden = conn.createStatement();
            orden.executeUpdate("DELETE FROM persona WHERE nombrePersona ='" + nombre + "'");
            orden.close();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public boolean borrarDepartamento(Departamento d) {
        int numDep = d.getNumDep();
        boolean flag = false;
        String consulta = "SELECT COUNT(numDepto) FROM departamento WHERE departamento.numDepto= " + numDep;
        try {
            Statement orden = conn.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarDepartamento(Departamento d) {

        String nombre = d.getNomDep();
        String direccion = d.getDireccion();
        int numDepto = d.getNumDep();
        String consulta = "INSERT INTO departamento(nombre,direccion,numDepto) VALUES('" + nombre + "','" + direccion + "','" + numDepto + "')";
        boolean flag = false;

        try {
            Statement orden = conn.createStatement();
            orden.execute(consulta);
            orden.close();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;

    }

    //Buscamos una persona por su nombre y apellidos
    public boolean buscarPersona(Persona p) {
        String consulta = "SELECT * FROM persona WHERE nombrePersona = ?";
        int id = p.getId();
        String nombre = p.getNomPerso();
        boolean flag = false;
        try {

            PreparedStatement pr = null;
            pr = conn.prepareStatement(consulta);

            pr.setString(1, nombre);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {

                flag = true;

            }
            pr.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public boolean personasDep(int dep) {
        String consulta = "SELECT * FROM persona WHERE numDepartamento=" + dep;
        boolean flag = false;

        try {
            Statement orden = conn.createStatement();
            ResultSet res;
            res = orden.executeQuery(consulta);
            if (res.next()) {
                flag = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;

    }

    public ArrayList obtenerDepartamentos() {

        ArrayList<Departamento> data = new ArrayList();
        //realizamos la consulta sql y llenamos los datos en el ArrayList de objetos Persona
        try {
            Statement orden = conn.createStatement();
            ResultSet res = orden.executeQuery("SELECT "
                    + " nombre, direccion,numDepto "
                    + " FROM departamento"
                    + " ORDER BY nombre ");
            while (res.next()) {
                //Creamos el objeto persona en el que vamos a leer los datos del
                //registro actual.
                Departamento d = new Departamento();
                //Damos al objeto Persona los valores de los campos del registro-
                d.setNomDep(res.getString("nombre"));
                d.setDireccion(res.getString("direccion"));
                d.setNumDep(res.getInt("numDepto"));

                //Añadimos el objeto al ArrayList en el que capturamos los datos
                //del ResulSet para su posterior tratamiento.
                data.add(d);
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        //devolvemos el array creado anteriormente como un ArrayList
        return data;
    }

    public boolean actualizarDep(Departamento d, int numDep) {
        String nom = d.getNomDep();
        boolean flag = false;
        String direccion = d.getDireccion();

        String consulta
                = "UPDATE departamento "
                + "SET nombre = '" + nom + "',"
                + "direccion = '" + direccion + "'"
                + "WHERE numDepto=" + numDep;

        try {
            //Creamos el objeto Statement para enviar la orden de actualizacion de la
            //persona.
            Statement orden = conn.createStatement();

            if (orden.executeUpdate(consulta) > 0) {
                flag = true;

            }

            orden.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public ArrayList obtenerPersonas() {

        ArrayList<Persona> personas = new ArrayList<>();

        String consulta = "SELECT nombrePersona , identificador , numDepartamento FROM persona ORDER BY nombrePersona";

        try {
            Statement orden = conn.createStatement();

            ResultSet rs = orden.executeQuery(consulta);

            while (rs.next()) {

                Persona p = new Persona();

                p.setNomPerso(rs.getString("nombrePersona"));
                p.setId(rs.getInt("identificador"));
                p.setNumDep(rs.getInt("numDepartamento"));
                personas.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personas;
    }

}
