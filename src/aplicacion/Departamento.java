package aplicacion;

public class Departamento {

    private String nomDep;              // Nombre del departamento
    private String direccion;           // Nombre ubicacion departamento
    private int numDep;                 // Número de departamento

    public String getNomDep() {
        return nomDep;
    }

    public void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumDep() {
        return numDep;
    }

    public void setNumDep(int numDep) {
        this.numDep = numDep;
    }

    public Departamento(String nomDep, String direccion, int numDep) {
        this.nomDep = nomDep;
        this.direccion = direccion;
        this.numDep = numDep;
    }

    public Departamento() {
    }

    @Override
    public String toString() {

       String cadena="";
       
       cadena+=Utilidades.formatoColumna(""+numDep,50);
       cadena+=Utilidades.formatoColumna(direccion,50); 
       cadena+=Utilidades.formatoColumna(nomDep,50);

        return cadena;
    }

}
