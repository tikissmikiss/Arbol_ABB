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

    private void insertar(ArbolABB nuevo) {
        if (nuevo.valor < valor) { // si menor o igual que el valor del nodo
            if (izq != null) // Si tiene hijo izquierdo llamada recursiva pasando hijo izquierdo
                izq.insertar(nuevo);
            else
                izq = nuevo;
        } else { // si mayor que el valor del nodo
            if (dch != null) // Si tiene hijo derecho llamada recursiva pasando hijo derecho
                dch.insertar(nuevo);
            else
                dch = nuevo;
        }
        size++;
    }

    public boolean borrar(int v) {
        return borrar(null, v);
    }

    private boolean borrar(ArbolABB padre, int v) {
        boolean exito;
        // El valor, ¿es mayor, menorn igual o no existe?
        if (this.izq != null && v < this.valor) { // Si menor, llamada recursiva del izquierdo
            exito = izq.borrar(this, v);
        } else if (this.dch != null && v > this.valor) { // Si mayor, llamada recursiva del derecho
            exito = dch.borrar(this, v);
        } else if (this.valor != null && this.valor == v) { // Si tengo valor y es igual. Soy el nodo a eliminar
            // 3 opciones:
            if (this.dch == null && this.izq == null) { // Si 0 hijos
                // Poner a null el hijo de nuestro padre que nos hace referencia según corresponda.
                padre.dch = (this == padre.dch) ? null : padre.dch;
                padre.izq = (this == padre.izq) ? null : padre.izq;
            } else if (this.dch == null || this.izq == null) { // Si 1 hijos
                // Actualizar hijo de nuestro padre que nos hace referencia (der o izq, según
                // corresponda) para que apunte a nuestro hijo.
                ArbolABB hijo = (this.dch != null) ? this.dch : this.izq;
                padre.dch = (this == padre.dch) ? hijo : padre.dch;
                padre.izq = (this == padre.izq) ? hijo : padre.izq;
            } else { // si 2 hijos
                // buscamos al sucesor. El menor del subárbol derecho.
                ArbolABB sucesor = dch.sucesor();
                if (sucesor != this.dch) { // Si el sucesor no es mi hijo derecho.
                    // Localizamos el padre de sucesor
                    ArbolABB padreSucesor = dch.padreSucesor(sucesor);
                    // El padre de sucesor adopta a su nieto.
                    if (padreSucesor != null)
                        padreSucesor.izq = sucesor.dch;
                } else {// Si el sucesor es mi hijo derecho
                    // Adoptamos a nuestro nieto.
                    this.dch = sucesor.dch;
                }
                // Sucesor sube a nuestra posición.
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

    private ArbolABB sucesor() {
        if (izq != null)
            return izq.sucesor();
        return this;
    }

    private ArbolABB padreSucesor(ArbolABB sucesor) {
        if (this.izq == null)
            return null;
        if (sucesor != this.izq)
            return izq.padreSucesor(sucesor);
        return this;
    }

    /**
     * Busca un elemento por valor
     * 
     * @param v = Valor buscado
     * @return {@code true} si el valor existe en el árbol.
     *         <li>{@code false} si no existe.</li>
     */
    public boolean buscar(int v) {
        if (valor == v)
            return true;
        else if (v < valor)
            return izq.buscar(v);
        else if (v > valor)
            return dch.buscar(v);
        return false;
    }

    public int maximo() {
        if (dch != null)
            return dch.maximo();
        return valor;
    }

    public int minimo() {
        if (izq != null)
            return izq.minimo();
        return valor;
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

    public void breadthWalkPrint() {
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
            System.out.print(a.valor + " ");
        }
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

    public int altura() {
        return altura(0, 0);
    }

    private int altura(int altura, int alturaMax) {
        altura++;
        if (dch != null || izq != null) {
            if (dch != null)
                alturaMax = dch.altura(altura, alturaMax > altura ? alturaMax : altura);
            if (izq != null)
                alturaMax = izq.altura(altura, alturaMax > altura ? alturaMax : altura);
        }
        alturaMax = alturaMax > altura ? alturaMax : altura;
        return alturaMax;
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

    public void clear() {
        dch = null;
        izq = null;
        valor = null;
        size = 0;
    }

    @Override
    public String toString() {
        return "   [valor:" + valor + ", size=" + size + "]";
    }

}
