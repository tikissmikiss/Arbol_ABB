package test;

import arbol.ArbolABB;
import arbol.ArbolABB.PrintMode;
import lab1.lista.Lista;

public class Test {
    public static void main(String[] args) throws Exception {
        ArbolABB arbol = new ArbolABB();

        testInsertarValoresDiferentes(arbol);

        testImprimir(arbol);

        testBorrar(arbol);

        arbol.clear();

        testInsertarValoresRepetidos();

    }

    private static void testInsertarValoresDiferentes(ArbolABB arbol) throws Exception {
        System.out.println("********************************************************************");
        System.out.println("*                  Test introducción de valores                    *");
        System.out.println("********************************************************************");
        System.out.println("Introducimos los siguientes valores diferentes en este orden:");
        System.out.println("8, 4, 12, 2, 6, 10, 17, 1, 3, 5, 7, 9, 11, 15, 18, 13, 16, 19, 14");
        int[] valores = { 8, 4, 12, 2, 6, 10, 17, 1, 3, 5, 7, 9, 11, 15, 18, 13, 16, 19, 14 };
        System.out.println("Esto genera el siguiente arbol:");
        String str = "";
        str += "                                 8                                    \n";
        str += "                 ______________/  \\______________                     \n";
        str += "                 4                              12                    \n";
        str += "         ______/  \\______                ______/  \\______             \n";
        str += "         2               6              10              17            \n";
        str += "     __/  \\__        __/  \\__        __/  \\__        __/  \\__         \n";
        str += "     1       3       5       7       9      11      15      18        \n";
        str += "                                                 __/  \\__     \\__     \n";
        str += "                                                13      16      19    \n";
        str += "                                                  \\_                  \n";
        str += "                                                   14                 \n";
        System.out.println(str);

        arbol = insertarValores(valores);

        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("8 4 2 1 3 6 5 7 12 10 9 11 17 15 13 14 16 18 19");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 3 2 5 7 6 4 9 11 10 14 13 16 15 19 18 17 12 8");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);

        System.out.println("********************************************************************");
        System.out.println("*                       Test devolución lista                      *");
        System.out.println("********************************************************************");
        System.out.println("En vez de imprimir usando el metodo de impresion recuperamos los\nvalores en listas:");
        Lista<Integer> lista = new Lista<>();
        System.out.println("Recuperar lista con los valores del árbol ordenado en pre-order");
        lista = arbol.preOrder();
        System.out.println("Imprimir la lista:");
        lista.print();
        lista.clear();
        System.out.println();
        System.out.println("Recuperar lista con los valores del árbol ordenado en in-order");
        lista = arbol.inOrder();
        System.out.println("Imprimir la lista:");
        lista.print();
        lista.clear();
        System.out.println();
        System.out.println("Recuperar lista con los valores del árbol ordenado en post-order");
        lista = arbol.postOrder();
        System.out.println("Imprimir la lista:");
        lista.print();
        lista.clear();
        System.out.println();
    }

    private static void testInsertarValoresRepetidos() {
        ArbolABB arbol;
        System.out.println("********************************************************************");
        System.out.println("*                  Test introducción de valores                    *");
        System.out.println("********************************************************************");
        System.out.println("Se permite introducir valores repetidos.");
        System.out.println("Introducimos los siguientes valores en este orden:");
        System.out.println("3, 2, 4, 1, 3, 4, 5, 1, 2, 4, 5");
        System.out.println("Esto genera el siguiente arbol:");
        String str = "";
        str += "                                3                           \n";
        str += "                 ______________/ \\______________           \n";
        str += "                2                               4           \n";
        str += "         ______/ \\______                 ______/ \\______  \n";
        str += "        1               3               4               5   \n";
        str += "     __/ \\__                         __/             __/  \n";
        str += "    1       2                       4               5       \n";
        System.out.println(str);
        int[] valores = { 3, 2, 4, 1, 3, 4, 5, 1, 2, 4, 5 };
        arbol = insertarValores(valores);
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("3 2 1 1 2 3 4 4 4 5 5");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 1 2 2 3 3 4 4 4 5 5");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 2 1 3 2 4 4 5 5 4 3");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);

        System.out.println("********************************************************************");
        System.out.println("*                  Test borrado de valores                         *");
        System.out.println("********************************************************************");
        System.out.println("Borrar elemento 3. Deberia quedar el siguiente arbol:");
        str = "                                4                           \n";
        str += "                 ______________/ \\______________           \n";
        str += "                2                               4           \n";
        str += "         ______/ \\______                 ______/ \\______  \n";
        str += "        1               3               4               5   \n";
        str += "     __/ \\__                                         __/  \n";
        str += "    1       2                                       5       \n";
        System.out.println(str);
        arbol.borrar(3);
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("4 2 1 1 2 3 4 4 5 5");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 1 2 2 3 4 4 4 5 5");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 2 1 3 2 4 5 5 4 4");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);

        System.out.println("Si volvemos a insertar un elemento 3. Deberia quedar el siguiente arbol:");
        str = "                                4                           \n";
        str += "                 ______________/ \\______________           \n";
        str += "                2                               4           \n";
        str += "         ______/ \\______                 ______/ \\______  \n";
        str += "        1               3               4               5   \n";
        str += "     __/ \\__         __/                             __/  \n";
        str += "    1       2       3                               5       \n";
        System.out.println(str);
        arbol.insertar(3);
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("4 2 1 1 2 3 3 4 4 5 5");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 1 2 2 3 3 4 4 4 5 5");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 2 1 3 3 2 4 5 5 4 4");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
    }

    private static void testBorrar(ArbolABB arbol) {
        System.out.println("Borrar 8");
        if (arbol.borrar(8))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");
        arbol.print(PrintMode.INORDER);
        System.out.println();

        System.out.println("Borrar 12");
        if (arbol.borrar(12))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");
        arbol.print();
        System.out.println();

        System.out.println("Borrar 17");
        if (arbol.borrar(17))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");
        arbol.print();
        System.out.println();

        System.out.println("Borrar 20");
        if (arbol.borrar(20))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");
        arbol.print();
        System.out.println();
    }

    private static void testImprimir(ArbolABB arbol) throws Exception {
    }

    private static ArbolABB insertarValores(int[] valores) {
        ArbolABB arbol = new ArbolABB();

        for (int i : valores) {
            arbol.insertar(i);
        }
        return arbol;
    }
}
