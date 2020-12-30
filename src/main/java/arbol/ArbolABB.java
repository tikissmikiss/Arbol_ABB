package arbol;

import lab1.cola.Cola;
import lab1.lista.Lista;

public class ArbolABB {
    private ArbolABB dch;
    private ArbolABB izq;
    private Integer valor;
    private int size;

    public enum PrintMode {
        PREORDER, INORDER, POSTORDER, TREE
    }

    // Constructor
    public ArbolABB(int valor) {
        clear();
        this.valor = valor;
        size = 1;
    }

    public ArbolABB() {
        clear();
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
     * @param valor
     */
    public void insertar(int valor) {
        if (this.valor == null) { // Si el árbol esta vacío establecer como raíz
            size++;
            this.valor = valor;
        } else
            insertar(new ArbolABB(valor));
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
    private void insertar(ArbolABB a) {
        if (a.valor <= valor) { // si menor o igual que el valor del nodo
            if (izq != null) // Si tiene hijo izquierdo llamada recursiva pasando hijo izquierdo
                izq.insertar(a);
            else
                izq = a;
        } else { // si mayor que el valor del nodo
            if (dch != null) // Si tiene hijo derecho llamada recursiva pasando hijo derecho
                dch.insertar(a);
            else
                dch = a;
        }
        size++;
    }

    public boolean borrar(int v) {

        return borrar(null, v);

    }

    /**
     * Busca un elemento por valor
     * 
     * @param v = Valor buscado
     * @return la referencia al nodo si existe.
     *         <li>{@code null} si no existe.</li>
     */
    // public ArbolNoPadre buscar(int v) {
    // return busacar(raiz, v);
    // }

    /**
     * Búsqueda recursiva de un nodo con valor {@code v} desde el nodo {@code n}
     * 
     * @param v valor buscado
     * @return el primer nodo con el valor buscado.
     *         <li>{@code null} si el valor no es encontrado.</li>
     */
    private ArbolABB buscar(int v) {
        if (valor == v)
            return this;
        else if (v < valor)
            return izq.buscar(v);
        else if (v > valor)
            return dch.buscar(v);
        return null;
    }

    private void setRaiz(ArbolABB raiz) {
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
    private boolean borrar(ArbolABB padre, int v) {
        boolean exito;

        // El valor, ¿es mayor, menor o soy yo?
        if (this.izq != null && v < this.valor) { // Si menor, llamada recursiva del izquierdo

            exito = izq.borrar(this, v);

        } else if (this.dch != null && v > this.valor) { // Si mayor, llamada recursiva del derecho

            exito = dch.borrar(this, v);

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
                ArbolABB hijo = (this.dch == null) ? this.dch : this.izq;
                padre.dch = (this == padre.dch) ? hijo : padre.dch;
                padre.izq = (this == padre.izq) ? hijo : padre.izq;
            } else { // si 2 hijos
                // 1. El sucesor sube a nuestra posición y nos sustituye.
                // 2. Si sucesor tiene hijo, lo adopta su abuelo.
                // Para no perder las referencias a los posibles hijos de sucesor, primero
                // realizamos el paso 2.

                // 2. Si sucesor tiene hijo, lo adopta su abuelo.
                // Guardamos referencias al sucesor. El menor del subárbol derecho.
                ArbolABB sucesor = dch.minimo();
                // Si el sucesor no es mi hijo derecho, localizamos su padre.
                if (sucesor != this.dch) {
                    // Subimos el subárbol derecho del sucesor - Lo hacemos antes para no perder la
                    // referencia al subárbol izquierdo.
                    ArbolABB padreSucedor = dch.getPadreSucedor(sucesor);
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
                this.valor = sucesor.valor;
            }
            exito = true;
        } else {
            exito = false;
        }

        if (exito)
            size--;
        return exito;
    }

    /**
     * Busca cual es elemento mas pequeño del arbol retorna su padre.
     * 
     * @param sucesor
     * 
     * @return padre del elemento mas pequeño de arbol. Si ..................
     */
    private ArbolABB getPadreSucedor(ArbolABB sucesor) {
        if (this.izq == null)
            return null; // TODO rellenar javadoc. La entra es igual que this?
        if (sucesor != this.izq)
            return izq.getPadreSucedor(sucesor);
        return this;
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
    private ArbolABB preSucesor(ArbolABB arbol) {
        if (arbol.izq.izq != null) {
            return preSucesor(arbol.izq);
        } else {
            return arbol;
        }
        // return arbol.izq.izq != null ?
        // preSucesor(arbol.izq) :
        // arbol;
    }

    public ArbolABB minimo() {
        if (izq != null)
            return izq.minimo();
        return this;
    }

    public ArbolABB maximo() {
        if (dch != null)
            return dch.maximo();
        return this;
    }

    /**
     * Recorre el árbol e imprime el resultado
     */
    private void inOrderPrint() {
        if (izq != null)
            izq.inOrderPrint();
        System.out.print(valor + " ");
        if (dch != null)
            dch.inOrderPrint();
    }

    public Lista<Integer> breadthWalk() {
        // 1. encolar nodo raíz
        // 2. loop mientras cola no vacía
        // 2.1. sacar de la cola un elemento
        // 2.2. encolar hijo izq del elemento si existe
        // 2.3. encolar hijo dch del elemento si existe
        // 2.4. añadir elemento a la lista
        // 3. repetir loop con siguiente en cola
        Lista<Integer> lista = new Lista<>();
        Cola<ArbolABB> c = new Cola<ArbolABB>();
        c.queue(this);
        while (!c.isEmpty()) {
            ArbolABB a = c.dequeue();
            if (a.izq != null)
                c.queue(a.izq);
            if (a.dch != null)
                c.queue(a.dch);
            lista.add(a.valor);
            System.out.print(a.valor + " ");
        }
        return lista;
    }

    private void breadthWalk(Lista<Integer> lista) {
        // 1. encolar nodo raíz
        // 2. loop mientras cola no vacía
        // 2.1. sacar de la cola un elemento
        // 2.2. encolar hijo izq del elemento si existe
        // 2.3. encolar hijo dch del elemento si existe
        // 2.4. añadir elemento a la lista
        // 3. repetir loop con siguiente en cola
        Cola<ArbolABB> c = new Cola<ArbolABB>();
        c.queue(this);
        while (!c.isEmpty()) {
            ArbolABB a = c.dequeue();
            if (a.izq != null)
                c.queue(a.izq);
            if (a.dch != null)
                c.queue(a.dch);
            lista.add(a.valor);
        }
    }

    public Lista<Integer> preOrder() {
        return preOrder(new Lista<Integer>());
    }

    private Lista<Integer> preOrder(Lista<Integer> lista) {
        lista.add(this.valor);
        if (izq != null)
            izq.preOrder(lista);
        if (dch != null)
            dch.preOrder(lista);
        return lista;
    }

    public Lista<Integer> postOrder() {
        return postOder(new Lista<Integer>());
    }

    private Lista<Integer> postOder(Lista<Integer> lista) {
        if (izq != null)
            izq.postOder(lista);
        if (dch != null)
            dch.postOder(lista);
        lista.add(this.valor);
        return lista;
    }

    public Lista<Integer> inOrder() {
        return inOrder(new Lista<>());
    }

    private Lista<Integer> inOrder(Lista<Integer> lista) {
        if (izq != null)
            izq.inOrder(lista);
        lista.add(this.valor);
        if (dch != null)
            dch.inOrder(lista);
        return lista;
    }

    /**
     * Recorre el árbol e imprime el resultado
     * 
     * @param r = Nodo raíz
     */
    private void postOderPrint() {
        if (izq != null)
            izq.postOderPrint();
        if (dch != null)
            dch.postOderPrint();
        System.out.print(valor + " ");
    }

    /**
     * Recorre el árbol e imprime el resultado
     * 
     * @param r = Nodo raíz
     */
    private void preOrderPrint() {
        System.out.print(valor + " ");
        if (izq != null)
            izq.preOrderPrint();
        if (dch != null)
            dch.preOrderPrint();
    }

    private void printTree() {
        printTree("\nR:", 0);
    }

    private void printTree(String str, int altura) {
        String s = "";
        for (int i = 0; i < altura; i++) {
            s += "\t";
        }
        altura++;
        System.out.println(s + str + valor);
        if (dch != null || izq != null) {
            if (dch != null) {
                dch.printTree("d:", altura);
            } else {
                System.out.println("\t" + s + "d:-");
            }
            if (izq != null) {
                izq.printTree("i:", altura);
            } else {
                System.out.println("\t" + s + "i:-");
            }
        }
    }

    public void print() {
        inOrderPrint();
        System.out.println();
    }

    public void print(PrintMode modo) {
        if (modo == PrintMode.INORDER)
            inOrderPrint();
        else if (modo == PrintMode.PREORDER)
            preOrderPrint();
        else if (modo == PrintMode.POSTORDER)
            postOderPrint();
        else if (modo == PrintMode.TREE)
            printTree();
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "   [valor:" + valor + ", size=" + size + "]";
    }

    public void clear() {
        dch = null;
        izq = null;
        valor = null;
        size = 0;
    }

}
