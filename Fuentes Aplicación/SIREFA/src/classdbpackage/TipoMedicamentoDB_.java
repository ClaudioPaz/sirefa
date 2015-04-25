/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classdbpackage;

import classpackage.Conexion;
import classpackage.Medicamento;
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
public class TipoMedicamentoDB_ 
{
    private Conexion conexion;
    private Tipo_medicamento tipoMedicamento;
    private ResultSet resultSet;
    
    public TipoMedicamentoDB_ ()
    {
        this.conexion = new Conexion();
    }
    
    public ArrayList<Tipo_medicamento> obtenerTiposDeMedicamento()
    {
        try
        {
            ArrayList<Tipo_medicamento> listaTipoMedicamentos = new ArrayList<Tipo_medicamento>();
            String mensajeError = null;

            CallableStatement callStatement = this.conexion.getCallStatement();

            callStatement = this.conexion.getConn().prepareCall("{CALL SP_OBTENER_TIPOS_MEDICAMENTO(?) }");
            
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
                int idTipoMedicamento = this.resultSet.getInt("idTipo_Medicamento");
                String descripcionTipoMedicamento = this.resultSet.getString("descripcion");
                
                Tipo_medicamento tipoMedicamento =  new Tipo_medicamento(idTipoMedicamento, descripcionTipoMedicamento);
                
                listaTipoMedicamentos.add(tipoMedicamento);
            }
           
            return listaTipoMedicamentos;

        }
        catch(SQLException e)
        {
            return null;
        }     
    }
}
