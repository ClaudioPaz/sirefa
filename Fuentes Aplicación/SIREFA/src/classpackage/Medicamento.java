package classpackage;

import classdbpackage.MedicamentoDB;
import java.util.Vector;

public class Medicamento 
{
    private int _idMedicamento;
    private String _nombre;
    private Tipo_medicamento tipoMedicamento;
    private Vector<Medicamento_instancia> _medicamento_instancia = new Vector<Medicamento_instancia>();
    private Vector<Medicamento_prescripcion> _medicamento_prescripcion = new Vector<Medicamento_prescripcion>();
    
    private MedicamentoDB medicamentoDB;

    public Medicamento(int idMedicamento, String nombre, Tipo_medicamento tipoMedicamento)
    {
        this.setIdMedicamento(idMedicamento);
        this.setNombre(nombre);
        this.setIdTipoMedicamento(tipoMedicamento);
        
        this.medicamentoDB = new MedicamentoDB();
    }
    
    public Medicamento(int idMedicamento, String nombre, int idTipoMedicamento)
    {
        this.setIdMedicamento(idMedicamento);
        this.setNombre(nombre);
        
        this.medicamentoDB = new MedicamentoDB();
        this.tipoMedicamento = new Tipo_medicamento(idTipoMedicamento);
    }    
    
    public Medicamento()
    {
        this.medicamentoDB = new MedicamentoDB();
    }    
    
    public Medicamento(String nombreMedicamento, Tipo_medicamento tipoMedicamento)
    {
        this.setNombre(nombreMedicamento);
        this.tipoMedicamento = tipoMedicamento;
        
        this.medicamentoDB = new MedicamentoDB();
    }
    
    /**
     * @return the _idMedicamento
     */
    public int getIdMedicamento() {
        return _idMedicamento;
    }

    /**
     * @param _idMedicamento the _idMedicamento to set
     */
    public void setIdMedicamento(int _idMedicamento) {
        this._idMedicamento = _idMedicamento;
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
     * @return the _idTipoMedicamento
     */
    public Tipo_medicamento getIdTipoMedicamento() {
        return tipoMedicamento;
    }

    /**
     * @param _idTipoMedicamento the _idTipoMedicamento to set
     */
    public void setIdTipoMedicamento(Tipo_medicamento _idTipoMedicamento) {
        this.tipoMedicamento = _idTipoMedicamento;
    }

    /**
     * @return the _medicamento_instancia
     */
    public Vector<Medicamento_instancia> getMedicamento_instancia() {
        return _medicamento_instancia;
    }

    /**
     * @param _medicamento_instancia the _medicamento_instancia to set
     */
    public void setMedicamento_instancia(Vector<Medicamento_instancia> _medicamento_instancia) {
        this._medicamento_instancia = _medicamento_instancia;
    }

    /**
     * @return the _medicamento_prescripcion
     */
    public Vector<Medicamento_prescripcion> getMedicamento_prescripcion() {
        return _medicamento_prescripcion;
    }

    /**
     * @param _medicamento_prescripcion the _medicamento_prescripcion to set
     */
    public void setMedicamento_prescripcion(Vector<Medicamento_prescripcion> _medicamento_prescripcion) {
        this._medicamento_prescripcion = _medicamento_prescripcion;
    }

    /**
     * @return the medicamentoDB
     */
    public MedicamentoDB getMedicamentoDB() {
        return medicamentoDB;
    }

    /**
     * @param medicamentoDB the medicamentoDB to set
     */
    public void setMedicamentoDB(MedicamentoDB medicamentoDB) {
        this.medicamentoDB = medicamentoDB;
    }
}