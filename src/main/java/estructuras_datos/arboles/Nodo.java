package estructuras_datos.arboles;

/**
 * Objeto nodo de un árbol binario. Contiene un atributo de valor y 3 punteros,
 * uno apuntando a su nodo padre (parent) y dos apuntando a sus nodos hijos,
 * izquierdo y derecho respectivamente.
 * 
 * @param valor
 * @param padre
 * @param izq
 * @param dch
 * @author José Herce
 * @version 0.1 - 27/07/2020
 */
public class Nodo {
    // Parámetros
    private Nodo padre;
    private int valor;
    private Nodo izq;
    private Nodo dch;

    // Constructor
    public Nodo(int v) {
        this.padre = null;
        this.valor = v;
        this.izq = null;
        this.dch = null;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getDerecho() {
        return dch;
    }

    public void setDerecho(Nodo n) {
        n.padre = this;
        this.dch = n;
    }

    public Nodo getIzquierdo() {
        return izq;
    }

    public void setIzquierdo(Nodo n) {
        n.padre = this;
        this.izq = n;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int value) {
        this.valor = value;
    }

    /**
     * Inserta hijo derecho
     * 
     * @param v = Valor del nodo hijo
     * @throws Exception Ya existe un hijo derecho
     */
    void setDerecho(int v) throws Exception { // Rigth
        if (this.dch == null)
            this.dch = new Nodo(v);
        else
            throw new Exception("Ya existe un hijo derecho");
    }

    /**
     * Inserta hijo izquierdo
     * 
     * @param v = Valor del nodo hijo
     * @throws Exception Ya existe un hijo izquierdo
     */
    void setIzquierdo(int v) throws Exception { // Left
        if (this.izq == null)
            this.izq = new Nodo(v);
        else
            throw new Exception("ERROR interno de la clase Nodo: Ya existe un hijo izquierdo");
    }

    /**
     * Elimina hijo derecho
     * 
     * @throws Exception Intento de borrar nodo inexistente
     */
    public void borrarDerecho() throws Exception {
        if (this.dch != null)
            this.dch = null;
        else
            throw new Exception("Intento de borrar nodo inexistente");
    }

    /**
     * Elimina hijo izquierdo
     * 
     * @throws Exception Intento de borrar nodo inexistente
     */
    public void borrarIzquierdo() throws Exception {
        if (this.izq != null)
            this.izq = null;
        else
            throw new Exception("Intento de borrar nodo inexistente");
    }
}