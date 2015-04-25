/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classdbpackage;

import classpackage.Conexion;
import classpackage.Empleado;
import classpackage.Roles;
import classpackage.Tipo_medicamento;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Luis Manuel
 */
public class RolDB 
{
    private Conexion conexion;
    private ResultSet resultSet;
    private Roles rol;
    private Empleado empleado;
    
    public RolDB()
    {
        this.conexion = new Conexion();
    }
    
    public RolDB(Empleado empleado)
    {
        this.conexion = new Conexion();
        this.empleado = empleado;
    }    
    
    public ArrayList<Roles> obtenerRoles()
    {
        try
        {
            ArrayList<Roles> listaDeRoles = new ArrayList<Roles>();
            String mensajeError = null;

            CallableStatement callStatement = this.conexion.getCallStatement();

            callStatement = this.conexion.getConn().prepareCall("{CALL SP_OBTENER_ROLES(?) }");
            
            //Registrar parámetro de salida
            callStatement.registerOutParameter("pcMensajeError", Types.VARCHAR);

            //Ejecutar PL
            callStatement.execute();
            
            //Obtener parámetro de salida
            mensajeError = callStatement.getString("pcMensajeError");
            
            if(mensajeError != null)
            {
                System.out.println(mensajeError);
                return null;
            }
            
            //Obtener select (result set)
            this.resultSet = callStatement.getResultSet();
            
            while(this.resultSet.next())
            {
                int idRol = this.resultSet.getInt("idRol");
                String nombre = this.resultSet.getString("Nombre");
                String descripcion = this.resultSet.getString("Descripcion");
            
                Roles rol = new Roles(idRol, nombre, descripcion);
                listaDeRoles.add(rol);
            }
           
            return listaDeRoles;

        }
        catch(SQLException e)
        {
            return null;
        }      
    }
}
