package classpackage;

import classdbpackage.RolDB;
import java.util.Vector;

public class Roles 
{
    private int _idRol;
    private String _nombre;
    private String _descripcion;
    private Vector<Privilegios> _idPrivilegio = new Vector<Privilegios>();
    private Vector<Empleado> _idUsuario = new Vector<Empleado>();
    
    private RolDB rolDB;
        
    public Roles()
    {
        this.rolDB = new RolDB();
    }
    
    public Roles(int idRol, String nombre, String descripcion)
    {
        this.setIdRol(idRol);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        
        this.rolDB = new RolDB();
    }  
    
    public Roles(int idRol, String nombre)
    {
        this.setIdRol(idRol);
        this.setNombre(nombre);
        
        this.rolDB = new RolDB();
    }       
     

    /**
     * @return the _idRol
     */
    public int getIdRol() {
        return _idRol;
    }

    /**
     * @param _idRol the _idRol to set
     */
    public void setIdRol(int _idRol) {
        this._idRol = _idRol;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param _nombre the _nombre to set
     */
    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    /**
     * @return the _descripcion
     */
    public String getDescripcion() {
        return _descripcion;
    }

    /**
     * @param _descripcion the _descripcion to set
     */
    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    /**
     * @return the _idPrivilegio
     */
    public Vector<Privilegios> getIdPrivilegio() {
        return _idPrivilegio;
    }

    /**
     * @param _idPrivilegio the _idPrivilegio to set
     */
    public void setIdPrivilegio(Vector<Privilegios> _idPrivilegio) {
        this._idPrivilegio = _idPrivilegio;
    }

    /**
     * @return the _idUsuario
     */
    public Vector<Empleado> getIdUsuario() {
        return _idUsuario;
    }

    /**
     * @param _idUsuario the _idUsuario to set
     */
    public void setIdUsuario(Vector<Empleado> _idUsuario) {
        this._idUsuario = _idUsuario;
    }
    
    @Override
    public String toString()
    {
        return this.getNombre();
    }
}