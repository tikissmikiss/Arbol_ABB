package estructuras_datos.arboles;

public class ArbolNoPadre {
    private ArbolNoPadre dch;
    private ArbolNoPadre izq;
    private Integer valor;
    private int size;

    // Constructor
    public ArbolNoPadre(int valor) {
        this.valor = valor;
        size = 1;
    }

    public ArbolNoPadre() {
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
    private void insertar(ArbolNoPadre n, int v) {
        // v.setPadre(r); // Actualizar el padre del nodo // Se encarga el nodo
        if (v <= n.valor) { // si menor o igual que el valor del nodo
            if (n.izq != null) // Si tiene hijo izquierdo llamada recursiva pasando hijo izquierdo
                insertar(n.izq, v);
            else
                n.izq = new ArbolNoPadre(v);
        } else { // si mayor que el valor del nodo
            if (n.dch != null) // Si tiene hijo derecho llamada recursiva pasando hijo derecho
                insertar(n.dch, v);
            else
                n.dch = new ArbolNoPadre(v);
        }
    }

    public boolean borrar(int v) {
        if (borrar(null, v)) {
            size--;
            return true;
        } else {
            return false;
        }

        // // soy yo el valor?
        // if (this.valor != v) { // No soy yo el valor

        // // Usamos nuestro metodo de borrado y pasamos una referencia a nosotros
        // // borrar(this, v);
        // borrar(null, v);

        // } else { // Si soy yo el valor

        // // TODO pendiente
        // borrar(null, v);
        // // this.setRaiz(raiz);
        // }

        // // if (this != raiz)
        // // this.setRaiz(raiz);

        // return false; // TODO retorno provisional
    }

    private void setRaiz(ArbolNoPadre raiz) {
        dch = raiz.dch;
        izq = raiz.izq;
        valor = raiz.valor;
        size = raiz.size;
    }

    /**
     * <p>
     * Borra el nodo con mismo valor que 'v'. Para poder mantener la propiedad de
     * ordenación se requiere una referencia al padre. <br>
     * Se busca el nodo recursivamente sobre el hijo derecho o izquierdo según
     * corresponda 'v' sea mayor o menor que el valor propio, en la llamada
     * recursiva se la pasa al hijo una referencia nuestra por parámetro.
     * </p>
     * <p>
     * Cuando se alcanza el nodo a borrar se dan 3 posibles opciones. El nodo tiene:
     * <ul>
     * <li>0 hijos: Se elimina del padre la referencia del hijo que apunta al
     * nodo.</li>
     * <li>1 hijos: El hijo Sube a nuestra posición.</li>
     * <li>2 hijos: Se requieren dos acciones.
     * <ol>
     * <li>El nodo con el siguiente valor (sucesor) sube a nuestra posición y nos
     * sustituye.</li>
     * <li>Si sucesor tiene hijo lo adopta su abuelo. Al ser el sucesor el siguiente
     * valor al nuestro, este valor es el menor de los mayores que el nuestro, es
     * decir, el menor valor de nuestro subárbol derecho. Por tanto sucesor no puede
     * tener hijo izquierdo, de tener algún hijo solo puede ser derecho, que sería
     * adoptado por su abuelo, pasaría a ser hijo del padre del sucesor.</li>
     * </ol>
     * </li>
     * </ul>
     * </p>
     * <br>
     * 
     * @param padre referencia al raiz del subarbol que antecede a esta instancia.
     * @param v     Valor que se solicita borrar
     * @return true si borrado satisfactorio.
     */
    private boolean borrar(ArbolNoPadre padre, int v) {

        // El valor, ¿es mayor, menor o soy yo?
        if (v < this.valor) { // Si menor, llamada recursiva del izquierdo

            // izq.borrar(this, v)
            if (izq.borrar(this, v)) {
                size--;
                return true;
            } else {
                return false;
            }

        } else if (v > this.valor) { // Si mayor, llamada recursiva del derecho

            // dch.borrar(this, v);
            if (dch.borrar(this, v)) {
                size--;
                return true;
            } else {
                return false;
            }

        } else if (this.valor == v) { // Si soy yo el valor

            // 3 opciones:
            if (this.dch == null && this.izq == null) { // Si 0 hijos
                // Poner a null el hijo de nuestro padre que nos hace referencia - der o izq,
                // según corresponda.
                padre.dch = (this == padre.dch) ? null : padre.dch;
                padre.izq = (this == padre.izq) ? null : padre.izq;
            } else if (this.dch == null || this.izq == null) { // Si 1 hijos
                // Actualizar hijo de nuestro padre que nos hace referencia (der o izq, según
                // corresponda) para que apunte a nuestro hijo.
                ArbolNoPadre hijo = (this.dch == null) ? this.dch : this.izq;
                padre.dch = (this == padre.dch) ? hijo : padre.dch;
                padre.izq = (this == padre.izq) ? hijo : padre.izq;
            } else { // si 2 hijos
                // 1. El sucesor sube a nuestra posición y nos sustituye.
                // 2. Si sucesor tiene hijo, lo adopta su abuelo.
                // Para no perder las referencias a los posibles hijos de sucesor, primero
                // realizamos el paso 2.

                // 2. Si sucesor tiene hijo, lo adopta su abuelo.
                // Guardamos referencias al sucesor. El menor del subárbol derecho.
                ArbolNoPadre sucesor = dch.minimo();
                // Si el sucesor no es mi hijo derecho, localizamos su padre.
                if (sucesor != this.dch) {
                    // Subimos el subárbol derecho del sucesor - Lo hacemos antes para no perder la
                    // referencia al subárbol izquierdo.
                    ArbolNoPadre padreSucedor = dch.getPadreSucedor(sucesor);
                    if (padreSucedor != null)
                        padreSucedor.izq = sucesor.dch;
                } else {
                    // Si el sucesor es mi hijo derecho adoptamos a nuestro nieto (hijo derecho de
                    // sucesor). Sobrescribiendo la referencia que tenemos a sucesor con la de su
                    // hijo derecho, que si no tuviera, estaríamos poniéndola a null y
                    // convenientemente se borraría sucesor como nuestro hijo derecho.
                    this.dch = sucesor.dch;
                }

                // 1. El sucesor sube a nuestra posición y nos sustituye.
                // Actualizar hijos del sucesor
                // TODO simplificable ...
                // // TODO ... opcion inicial
                // sucesor.dch = this.dch;
                // sucesor.izq = this.izq;
                // // Subimos sucesor nuestra posción.
                // if (padre == null) { // Si soy la raiz (No tengo padre).
                // // Actualizamos nuestros atributos con los de sucesor, que pasa a ser la
                // raiz.
                // this.setRaiz(sucesor);
                // } else {// Si no somos la raiz.
                // // Actualizar hijo de nuestro padre que nos hace referencia (der o izq, según
                // // corresponda) para que apunte sucesor.
                // padre.dch = (this == padre.dch) ? sucesor : padre.dch;
                // padre.izq = (this == padre.izq) ? sucesor : padre.izq;
                // }
                // TODO ... opcion alt
                this.valor = sucesor.valor;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca cual es elemento mas pequeño del arbol retorna su padre.
     * 
     * @param sucesor
     * 
     * @return padre del elemento mas pequeño de arbol. Si ..................
     */
    private ArbolNoPadre getPadreSucedor(ArbolNoPadre sucesor) {
        if (this.izq == null)
            return null; // TODO rellenar javadoc. La entra es igual que this?
        if (sucesor != this.izq)
            return izq.getPadreSucedor(sucesor);
        return this;
        // // Tengo hijo derecho?
        // if (this.dch == null) { // Si no tengo hijo derecho devuelve null
        // return null;
        // } else { // Si tengo hijo derecho, comprueba si es el sucesor (Si tengo nieto
        // izq)
        // if (this.dch.izq == null) {
        // return this;
        // } else {
        // return preSucesor(this.dch);
        // }
        // }
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
    private ArbolNoPadre preSucesor(ArbolNoPadre arbol) {
        if (arbol.izq.izq != null) {
            return preSucesor(arbol.izq);
        } else {
            return arbol;
        }
        // return arbol.izq.izq != null ?
        // preSucesor(arbol.izq) :
        // arbol;
    }

    private ArbolNoPadre minimo() {
        if (izq != null)
            return izq.minimo();
        return this;
    }

    public void printInorder() {
        printInorder(this);
    }

    /**
     * Recorre el árbol e imprime el resultado
     * 
     * @param r = Nodo raíz
     */
    private void printInorder(ArbolNoPadre r) {
        if (r.izq != null)
            printInorder(r.izq);
        System.out.print(r.valor + " ");
        if (r.dch != null)
            printInorder(r.dch);
    }

    public static void main(String[] args) {
        int[] valores = { 8, 4, 12, 2, 6, 10, 17, 1, 3, 5, 7, 9, 11, 15, 18, 13, 16, 19, 14 };
        String str = "";
        str += "                                08                                    \n";
        str += "                  _____________/  \\______________                     \n";
        str += "                04                              12                    \n";
        str += "         ______/  \\______                ______/  \\______             \n";
        str += "        02              06              10              17            \n";
        str += "     __/  \\__        __/  \\__        __/  \\__        __/  \\__         \n";
        str += "    01      03      05      07      09      11      15      18        \n";
        str += "                                                 __/  \\__     \\__     \n";
        str += "                                                13      16      19    \n";
        str += "                                                  \\_                  \n";
        str += "                                                   14                 \n";

        System.out.println(str);

        ArbolNoPadre arbol = new ArbolNoPadre();

        for (int i : valores) {
            arbol.insertar(i);
        }

        arbol.borrar(8);
        arbol.printInorder();
        System.out.println();

        arbol.borrar(12);
        arbol.printInorder();
        System.out.println();

        arbol.borrar(17);
        arbol.printInorder();
        System.out.println();

    }

    @Override
    public String toString() {
        return "   [" + valor + "]    size=" + size + "";
    }

}
