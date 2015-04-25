/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classpackage;

import java.sql.*;
import java.sql.PreparedStatement;

/**
 *
 * @author Luis Manuel
 */
public class Conexion 
{
    //Credenciales 
   public Connection conn = null;
   public CallableStatement callStatement = null;
   public PreparedStatement prepStatement = null;
   public ResultSet reader = null;
    
    //Driver de conexion a la base de datos
    private static String DRIVER = "com.mysql.jdbc.Driver";  
    
    //URL de conexion a la base datos 
    private static String HOSTURL = "jdbc:mysql://localhost:3306/sirefa";
    private static String HOSTURL_CLAUDIO = "jdbc:mysql://127.0.0.1:8889/sirefa?zeroDateTimeBehavior=convertToNull";
    
    //El usuario de la base de datos
    private static String USER = "root";
    
    //La clave del usuario de la base de datos
    static final String PASS = "";
    static final String PASS_CLAUDIO = "root";
    
   public Conexion()
   {
       try
       {
           Class.forName(DRIVER);
           this.conn = DriverManager.getConnection(HOSTURL, USER, PASS);
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
   }    
    
    //Metodo para obtener la conexion con la base de datos
    public static synchronized Connection getConexion() {
        Connection cn = null;
        try {
            //Cargamos el driver y le decimos que vamos a usar
            //una conexion con mysql
            Class.forName(DRIVER);
            
            //Obtenemos la conexion
            cn = DriverManager.getConnection(HOSTURL_CLAUDIO, USER, PASS);
            
        } catch (Exception e) {
            cn = null;
        } finally {
            return cn;
        }
   
    }
    
    public static synchronized void cerrarCall(CallableStatement cl) {
        try{
            cl.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    //Metodo utilizado para cerrar el resulset de datos
    public static synchronized void cerrarConexion(ResultSet rs) {
        try{
            rs.close();
        } catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    //Metodo utilizado para cerrar la conexion
    public static synchronized void cerrarConexion(Connection cn) {
        try{
            cn.close();
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    //Metodo utilizado para deshacer los cambios en la base de datos
    public static synchronized void deshacerCambios(Connection cn) {
        try{
            cn.rollback();
        }catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }

   //Metoodos generados por el ORM
   
    /**
     * @return the DRIVER
     */
    public static String getDRIVER() {
        return DRIVER;
    }

    /**
     * @param aDRIVER the DRIVER to set
     */
    public static void setDRIVER(String aDRIVER) {
        DRIVER = aDRIVER;
    }

    /**
     * @return the HOSTURL
     */
    public static String getHOSTURL() {
        return HOSTURL;
    }

    /**
     * @param aHOSTURL the HOSTURL to set
     */
    public static void setHOSTURL(String aHOSTURL) {
        HOSTURL = aHOSTURL;
    }

    /**
     * @return the USER
     */
    public static String getUSER() {
        return USER;
    }

    /**
     * @param aUSER the USER to set
     */
    public static void setUSER(String aUSER) {
        USER = aUSER;
    }
    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @return the callStatement
     */
    public CallableStatement getCallStatement() {
        return callStatement;
    }

    /**
     * @param callStatement the callStatement to set
     */
    public void setCallStatement(CallableStatement callStatement) {
        this.callStatement = callStatement;
    }

    /**
     * @return the prepStatement
     */
    public PreparedStatement getPrepStatement() {
        return prepStatement;
    }

    /**
     * @param prepStatement the prepStatement to set
     */
    public void setPrepStatement(PreparedStatement prepStatement) {
        this.prepStatement = prepStatement;
    }

    /**
     * @return the reader
     */
    public ResultSet getReader() {
        return reader;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(ResultSet reader) {
        this.reader = reader;
    }
   
}
