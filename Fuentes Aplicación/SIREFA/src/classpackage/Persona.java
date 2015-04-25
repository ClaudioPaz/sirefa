package classpackage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Persona 
{
	private int _idPersona;
	private String _primerNombre;
	private String _segundoNombre;
	private String _primerApellido;
	private String _segundoApellido;
	private Date _fechaDeNacimiento;
	private String _direccion;
	private String _numeroTelefono;
	private Empleado _empleado;
	private Paciente _paciente;
        
    public Persona(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                   String direccion, Date fechaNacimiento)
    {
        this.setPrimerNombre(primerNombre);
        this.setSegundoNombre(segundoNombre);
        this.setPrimerApellido(primerApellido);
        this.setSegundoApellido(segundoApellido);
        this.setDireccion(direccion);
        this.setFechaDeNacimiento(fechaNacimiento);
    }
    
    public Persona(int idPersona, String primerNombre, String segundoNombre, 
                        String primerApellido, String segundoApellido)
    {
        this.setIdPersona(idPersona);
        this.setPrimerNombre(primerNombre);
        this.setSegundoNombre(segundoNombre);
        this.setPrimerApellido(primerApellido);
        this.setSegundoApellido(segundoApellido);
    }    
    
    public Persona(){}

    /**
     * @return the _idPersona
     */
    public int getIdPersona() {
        return _idPersona;
    }

    /**
     * @param _idPersona the _idPersona to set
     */
    public void setIdPersona(int _idPersona) {
        this._idPersona = _idPersona;
    }

    /**
     * @return the _primerNombre
     */
    public String getPrimerNombre() {
        return _primerNombre;
    }

    /**
     * @param _primerNombre the _primerNombre to set
     */
    public void setPrimerNombre(String _primerNombre) {
        this._primerNombre = _primerNombre;
    }

    /**
     * @return the _segundoNombre
     */
    public String getSegundoNombre() {
        return _segundoNombre;
    }

    /**
     * @param _segundoNombre the _segundoNombre to set
     */
    public void setSegundoNombre(String _segundoNombre) {
        this._segundoNombre = _segundoNombre;
    }

    /**
     * @return the _primerApellido
     */
    public String getPrimerApellido() {
        return _primerApellido;
    }

    /**
     * @param _primerApellido the _primerApellido to set
     */
    public void setPrimerApellido(String _primerApellido) {
        this._primerApellido = _primerApellido;
    }

    /**
     * @return the _segundoApellido
     */
    public String getSegundoApellido() {
        return _segundoApellido;
    }

    /**
     * @param _segundoApellido the _segundoApellido to set
     */
    public void setSegundoApellido(String _segundoApellido) {
        this._segundoApellido = _segundoApellido;
    }

    /**
     * @return the _fechaDeNacimiento
     */
    public Date getFechaDeNacimiento() {
        return _fechaDeNacimiento;
    }

    /**
     * @param _fechaDeNacimiento the _fechaDeNacimiento to set
     */
    public void setFechaDeNacimiento(Date _fechaDeNacimiento) {
        this._fechaDeNacimiento = _fechaDeNacimiento;
    }

    /**
     * @return the _direccion
     */
    public String getDireccion() {
        return _direccion;
    }

    /**
     * @param _direccion the _direccion to set
     */
    public void setDireccion(String _direccion) {
        this._direccion = _direccion;
    }

    /**
     * @return the _numeroTelefono
     */
    public String getNumeroTelefono() {
        return _numeroTelefono;
    }

    /**
     * @param _numeroTelefono the _numeroTelefono to set
     */
    public void setNumeroTelefono(String _numeroTelefono) {
        this._numeroTelefono = _numeroTelefono;
    }

    /**
     * @return the _empleado
     */
    public Empleado getEmpleado() {
        return _empleado;
    }

    /**
     * @param _empleado the _empleado to set
     */
    public void setEmpleado(Empleado _empleado) {
        this._empleado = _empleado;
    }

    /**
     * @return the _paciente
     */
    public Paciente getPaciente() {
        return _paciente;
    }

    /**
     * @param _paciente the _paciente to set
     */
    public void setPaciente(Paciente _paciente) {
        this._paciente = _paciente;
    }
    
    public java.sql.Date getFechaSQL() 
    {
        try
        {
            java.sql.Date sql = new java.sql.Date(this.getFechaDeNacimiento().getTime());
            return sql;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}