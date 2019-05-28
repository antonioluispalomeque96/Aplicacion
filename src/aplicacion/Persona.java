package aplicacion;

public class Persona {

    private String nomPerso;
    private int id;
    private int numDep;

    public Persona(String nomPerso, int id, int numDep) {
        this.nomPerso = nomPerso;
        this.id = id;
        this.numDep = numDep;
    }

    public Persona() {
    }

    public String getNomPerso() {
        return nomPerso;
    }

    public void setNomPerso(String nomPerso) {
        this.nomPerso = nomPerso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumDep() {
        return numDep;
    }

    public void setNumDep(int numDep) {
        this.numDep = numDep;
    }

    @Override
    public String toString() {

        String cadena = "";

        cadena += Utilidades.formatoColumna("" + id, 50);
        cadena += Utilidades.formatoColumna(nomPerso, 50);
        cadena += Utilidades.formatoColumna("" + numDep, 50);

        return cadena;
    }

}
