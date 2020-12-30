package estructuras_datos.arboles;

public class Arbol {
    private Arbol padre;
    private Arbol dch;
    private Arbol izq;
    private Integer valor;
    private int size;

    // Constructor
    public Arbol(int valor) {
        this.valor = valor;
        size = 1;
    }

    public Arbol() {
    }

    /**
     * Crea un nuevo nodo con el valor dado y lo inserta manteniendo la propiedad de
     * ordenación.
     * <p>
     * {@code hijoIzq} <= {@code valor} <= {@code hijoDch}
     * </p>
     * <p>
     * De este modo:
     * </p>
     * <p>
     * {@code hijoIzq} <= {@code valor} < {@code hijoDch}
     * </p>
     * 
     * @param v
     */
    public void insertar(int v) {
        if (this.valor == null) // Si el árbol esta vacío establecer como raíz
            this.valor = v;
        else
            insertar(this, v);
        size++;
    }

    /**
     * Inserta el valor {@code v} manteniendo la propiedad de ordenación.
     * <p>
     * {@code izquierdo} <= {@code valor} <= {@code derecho}
     * </p>
     * <p>
     * De este modo:
     * </p>
     * <p>
     * {@code izquierdo} <= {@code valor} < {@code derecho}
     * </p>
     * 
     * @param n raíz del sub/árbol en donde insertar
     * @param v valor a insertar
     */
    private void insertar(Arbol n, int v) {
        // v.setPadre(r); // Actualizar el padre del nodo // Se encarga el nodo
        if (v <= n.valor) { // si menor o igual que el valor del nodo
            if (n.izq != null) // Si tiene hijo izquierdo llamada recursiva pasando hijo izquierdo
                insertar(n.izq, v);
            else
                n.izq = new Arbol(v);
        } else { // si mayor que el valor del nodo
            if (n.dch != null) // Si tiene hijo derecho llamada recursiva pasando hijo derecho
                insertar(n.dch, v);
            else
                n.dch = new Arbol(v);
        }
    }

    public boolean borrar(int v) {

        // soy yo el valor? // Es necesario consultar para la raiz, el resto de veces
        // será redundante, puesto que se consulta antes de la llamada recursiva
        if (this.valor != v) {
            // No soy yo el valor

            // el valor es mayor o menor que yo?
            if (v < this.valor) {
                // si valor menor que yo
                borrar(this.izq, v);
            } else {
                // si valor mayor que yo
                borrar(this.dch, v);
            }

        } else { // Si soy yo el valor
            // TODO pendiente
            borrar(this, v);
        }

        return false; // TODO retorno provisional
    }

    /**
     * <p>
     * Si hijo no es el valor a borrar se llama a su metodo borrar.
     * </p>
     * <p>
     * Si hijo es el valor - 3 opciones:
     * <ul>
     * <li>0 nietos: Se elimina la referencia al hijo.</li>
     * <li>1 nietos: El nieto sustituye al hijo.</li>
     * <li>2 nietos: 2 pasos
     * <ul>
     * <li>El sucesor del hijo sustituye al hijo.</li>
     * <li>Si el sucesor tiene hijo (Solo puede ser derecho, si tuviera hijo izq él
     * sería el sucesor por ser menor) este pasa a su posicion, es decir, su abuejo
     * (padre del sucesor) lo adopta</li>
     * </ul>
     * </li>
     * </ul>
     * </p>
     * <br>
     * 
     * @param hijo
     * @param v
     */
    private void borrar(Arbol hijo, int v) {
        // es hijo el valor?
        if (hijo.valor == v) { // hijo es el valor
            // 3 opciones:
            if (hijo.dch == null && hijo.izq == null) {
                // si 0 nietos: borrar mi referencia al hijo - der o izq, segun sea.
                this.dch = (hijo == this.dch) ? null : this.dch;
                this.izq = (hijo == this.izq) ? null : this.izq;
                // if (hijo == this.derecho) this.derecho = null;
                // if (hijo == this.izquierdo) this.izquierdo = null;
            } else if (hijo.dch == null || hijo.izq == null) {
                // si 1 nieto: El nieto pasa a ser el hijo - der o izq, segun sea.
                Arbol nieto = (hijo.dch == null) ? hijo.dch : hijo.izq;
                this.dch = (hijo == this.dch) ? nieto : this.dch;
                this.izq = (hijo == this.izq) ? nieto : this.izq;
            } else {
                // si 2 nietos:
                // - El sucesor del hijo sustituye al hijo.
                // - El hijo derecho del sucesor es adoptado por su abuelo, es decir, el hijo
                // derecho del sucesor (puede ser null) pasa a ser hijo izquierdo del que es
                // padre del sucesor.
                // Nota:
                // - El sucesor es menor del subarbol, por tanto, no puede tener hijo izq.
                // - El hijo derceho del sucesor pasa a ser el hijo izquierdo del padre del
                // sucesor. No hay problema si no tiene hijo hijo derecho, simplemente se le
                // asignará null.

                // Guardamos referencias al sucesor y a su padre.
                Arbol padreSucedor = hijo.padreSucedor();
                Arbol sucesor = padreSucedor.izq;

                // Subimos el subarbol derecho del sucesor - Lo hacemos antes para no perder la
                // referencia al subarbol izq
                padreSucedor.izq = sucesor.dch;

                // Intercambiamos hijo con su sucesor
                // 1 - actualizar hijos del sucesor
                sucesor.dch = hijo.dch;
                sucesor.izq = hijo.izq;
                // 2 - Subir sucesor a la posicion del hijo - der o izq, segun sea.
                this.dch = (hijo == this.dch) ? sucesor : this.dch;
                this.izq = (hijo == this.izq) ? sucesor : this.izq;
            }
        } else {
            // hijo no es el valor
            hijo.borrar(v); // Llamada recursiva
        }
    }

    /**
     * Busca cual es elemento mas pequeño del subarbol derecho y retorna su padre.
     * 
     * @return padre del elemento mas pequeño de subarbol derecho.
     */
    private Arbol padreSucedor() {
        // Tengo hijo derecho?
        if (dch == null) { // Si no tengo hijo derecho devuelve null
            return null;
        } else { // Si tengo hijo derecho, comprueba si es el sucesor (Si tengo nieto izq)
            if (dch.izq == null) {
                return this;
            } else {
                return preSucesor(dch);
            }
        }
    }

    /**
     * <p>
     * Devuelve el padre del sucesor.
     * </p>
     * <p>
     * Comprueba si si su nieto izquierdo es null. Esto es dos niveles de
     * profundidad.
     * </p>
     * <p>
     * <b>Precondicion</b>: El arbol que se pase por parametro siempre debe tener
     * hijo izquierdo
     * </p>
     * 
     * @param arbol
     * @return
     */
    private Arbol preSucesor(Arbol arbol) {
        if (arbol.izq.izq != null) {
            return preSucesor(arbol.izq);
        } else {
            return arbol;
        }
        // return arbol.izq.izq != null ?
        // preSucesor(arbol.izq) :
        // arbol;
    }

    public static void main(String[] args) {
        int[] valores = { 8, 4, 12, 2, 6, 10, 17, 1, 3, 5, 7, 9, 11, 15, 18, 13, 16, 14 };

        Arbol arbol = new Arbol();

        for (int i : valores) {
            arbol.insertar(i);
        }

        // arbol.borrar(8);
        // arbol.borrar(12);
        arbol.borrar(17);
    }

    @Override
    public String toString() {
        return "valor=" + valor + " size=" + size + "";
    }

}
