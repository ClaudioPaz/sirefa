/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classdbpackage;

import classpackage.Conexion;
import classpackage.Empleado;
import classpackage.Persona;
import classpackage.Roles;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Luis Manuel
 */
public class EmpleadoDB 
{
    private Empleado empleado;
    private ResultSet resultSet;
    private Conexion conexion;
    
    public EmpleadoDB(Empleado empleado)
    {
        this.setEmpleado(empleado);
        this.conexion = new Conexion();
    }
    
    public EmpleadoDB()
    {
        this.conexion = new Conexion();
    }
    
    public String registrarEmpleado(Empleado empleado)
    {
        Persona persona = empleado.getIdPersona();
        
        String mensajeError = null;
        
        try
        {
            CallableStatement callStatement = this.conexion.getCallStatement();

            callStatement = this.conexion.getConn().prepareCall("{ CALL SP_GESTIONAR_USUARIOS(?,?,?,?,?,?,?, ?,?,?) }");

            //Registrar parámetros de entrada
            callStatement.setString("pcPrimerNombre", persona.getPrimerNombre());
            callStatement.setString("pcSegundoNombre", persona.getSegundoNombre());
            callStatement.setString("pcPrimerApellido", persona.getPrimerApellido());
            callStatement.setString("pcSegundoApellido", persona.getSegundoApellido());
            callStatement.setDate("pdFechaNacimiento", persona.getFechaSQL());
            callStatement.setString("pcDireccion", persona.getDireccion());
            callStatement.setString("pcTelefono", persona.getNumeroTelefono());
            callStatement.setString("pcNombreUsuario", empleado.getNombreDeUsuario());
            callStatement.setString("pcClave", empleado.getClave());
            
            //Registrar parámetro de salida
            callStatement.registerOutParameter("pcMensajeError", Types.VARCHAR);
            
            //Ejecutar PL
            callStatement.execute();
            
            //Obtener parámetro de salida
            mensajeError = callStatement.getString("pcMensajeError");
            
            return mensajeError;
        }
        catch(SQLException e)
        {
            return e.getMessage();
        }
        
    }
    
    public ArrayList<Roles> obtenerRoles()
    {
        try
        {
            ArrayList<Roles> listaDeRoles = new ArrayList<Roles>();
            String mensajeError = null;

            CallableStatement callStatement = this.conexion.getCallStatement();

            callStatement = this.conexion.getConn().prepareCall("{CALL SP_OBTENER_ROLES_USUARIO(?,?) }");
            
            //Registrar parámetro de entrada
            callStatement.setInt("pnCodigoEmpleado", this.empleado.getIdPersona().getIdPersona());
            
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
            
                Roles rol = new Roles(idRol, nombre);
                
                this.empleado.getIdRol().add(rol);
                listaDeRoles.add(rol);
            }
           
            return listaDeRoles;

        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }              
    }
    
    public ArrayList<Empleado> obtenerEmpleados()
    {
        try
        {
            ArrayList<Empleado> listaDeEmpleados = new ArrayList<Empleado>();
            String mensajeError = null;

            CallableStatement callStatement = this.conexion.getCallStatement();

            callStatement = this.conexion.getConn().prepareCall("{CALL SP_OBTENER_EMPLEADOS(?) }");
            
            //Registrar parámetro de salida
            callStatement.registerOutParameter("pcMensajeError", Types.VARCHAR);

            //Ejecutar PL
            callStatement.execute();
            
            //Obtener parámetro de salida
            mensajeError = callStatement.getString("pcMensajeError");
            
            if(mensajeError != null)
            {
                return null;
            }
            
            //Obtener select (result set)
            this.resultSet = callStatement.getResultSet();
            
            while(this.resultSet.next())
            {
                String primerNombre = this.resultSet.getString("primerNombre");
                String segundoNombre = this.resultSet.getString("segundoNombre");
                String primerApellido = this.resultSet.getString("primerApellido");
                String segundoApellido = this.resultSet.getString("segundoApellido");
                String direccion = this.resultSet.getString("direccion");
                Date fechaNacimiento = this.resultSet.getDate("fechaDeNacimiento");
                
                int idEmpleado = this.resultSet.getInt("idPersona");
                
                Empleado empleado = new Empleado(idEmpleado, primerNombre, segundoNombre, primerApellido, segundoApellido,
                                    direccion, fechaNacimiento);
                                    
                listaDeEmpleados.add(empleado);
            }
           
            return listaDeEmpleados;

        }
        catch(SQLException e)
        {
            return null;
        }                  
    }
    
    public String asignarRol(Roles rol)
    {
        int accionAgregar = 1;
        String mensajeError = null;
        
        try
        {
            CallableStatement callStatement = this.conexion.getCallStatement();

            callStatement = this.conexion.getConn().prepareCall("{ CALL SP_ASIGNAR_ROL_USUARIO(?,?,?,?) }");

            //Registrar parámetros de entrada
            callStatement.setInt("pnCodigoRol", rol.getIdRol());
            callStatement.setInt("pnCodigoUsuario", this.empleado.getIdPersona().getIdPersona());
            callStatement.setInt("pnAccion", accionAgregar);
            
            //Registrar parámetro de salida
            callStatement.registerOutParameter("pcMensajeError", Types.VARCHAR);
            
            //Ejecutar PL
            callStatement.execute();
            
            //Obtener parámetro de salida
            mensajeError = callStatement.getString("pcMensajeError");
            
            return mensajeError;
        }
        catch(SQLException e)
        {
            return e.getMessage();
        }        
    }
    
    public String eliminarRol(Roles rol)
    {
        int accionAgregar = 2;
        String mensajeError = null;
        
        try
        {
            CallableStatement callStatement = this.conexion.getCallStatement();

            callStatement = this.conexion.getConn().prepareCall("{ CALL SP_ASIGNAR_ROL_USUARIO(?,?,?,?) }");

            //Registrar parámetros de entrada
            callStatement.setInt("pnCodigoRol", rol.getIdRol());
            callStatement.setInt("pnCodigoUsuario", this.empleado.getIdPersona().getIdPersona());
            callStatement.setInt("pnAccion", accionAgregar);
            
            //Registrar parámetro de salida
            callStatement.registerOutParameter("pcMensajeError", Types.VARCHAR);
            
            //Ejecutar PL
            callStatement.execute();
            
            //Obtener parámetro de salida
            mensajeError = callStatement.getString("pcMensajeError");
            
            return mensajeError;
        }
        catch(SQLException e)
        {
            return e.getMessage();
        }        
    }    
    
    public String iniciarSesion()
    {
        try
        {
            String mensajeError = null;
            int codigoUsuario = -1;

            CallableStatement callStatement = this.conexion.getCallStatement();

            callStatement = this.conexion.getConn().prepareCall("{CALL SP_LOGIN(?,?,?,?) }");

            //Registrar parámetros de entrada
            callStatement.setString("pcNombreUsuario", this.empleado.getNombreDeUsuario());
            callStatement.setString("pcClave", this.empleado.getClave());
            
            //Registrar parámetro de salida
            callStatement.registerOutParameter("pnCodigoUsuario", Types.INTEGER);
            callStatement.registerOutParameter("pcMensajeError", Types.VARCHAR);

            //Ejecutar PL
            callStatement.execute();

            //Obtener parámetro de salida
            mensajeError = callStatement.getString("pcMensajeError");
            codigoUsuario = callStatement.getInt("pnCodigoUsuario");
            
            this.empleado.getIdPersona().setIdPersona(codigoUsuario);

            return mensajeError;
        }
        catch(SQLException e)
        {
            return e.getMessage();
        }        
    }    

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
}
