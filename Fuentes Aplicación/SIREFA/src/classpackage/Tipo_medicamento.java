package classpackage;

import classdbpackage.TipoMedicamentoDB_;
import java.util.Vector;

public class Tipo_medicamento 
{
    private int idTipoMedicamento;
    private String _descripcion;
    private Vector<Medicamento> _medicamento = new Vector<Medicamento>();
    
    private TipoMedicamentoDB_ tipoMedicamentoDB;

    public Tipo_medicamento()
    {
        this.tipoMedicamentoDB = new TipoMedicamentoDB_();
    }
    
    public Tipo_medicamento(int idTipoMedicamento)
    {
        this.setIdTipo_Medicamento(idTipoMedicamento);
    }
    
    public Tipo_medicamento(int idTipoMedicamento, String descripcion)
    {
        this.setIdTipo_Medicamento(idTipoMedicamento);
        this.setDescripcion(descripcion);
    }    
    
    /**
     * @return the _idTipo_Medicamento
     */
    public int getIdTipo_Medicamento() {
        return idTipoMedicamento;
    }

    /**
     * @param _idTipo_Medicamento the _idTipo_Medicamento to set
     */
    public void setIdTipo_Medicamento(int _idTipo_Medicamento) {
        this.idTipoMedicamento = _idTipo_Medicamento;
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
     * @return the _medicamento
     */
    public Vector<Medicamento> getMedicamento() {
        return _medicamento;
    }

    /**
     * @param _medicamento the _medicamento to set
     */
    public void setMedicamento(Vector<Medicamento> _medicamento) {
        this._medicamento = _medicamento;
    }

    /**
     * @return the tipoMedicamentoDB
     */
    public TipoMedicamentoDB_ getTipoMedicamentoDB() {
        return tipoMedicamentoDB;
    }

    /**
     * @param tipoMedicamentoDB the tipoMedicamentoDB to set
     */
    public void setTipoMedicamentoDB(TipoMedicamentoDB_ tipoMedicamentoDB) {
        this.tipoMedicamentoDB = tipoMedicamentoDB;
    }
    
    @Override
    public String toString()
    {
        return this.getDescripcion();
    }
}