package main;

import java.util.Random;

import arbol.ArbolABB;
import arbol.ArbolABB.PrintMode;
import lab1.lista.Lista;

public class Test {
    public static void main(String[] args) throws Exception {
        testInsertarValoresDiferentes();

        testInsertarValoresRepetidos();

        testComplejidad();
    }

    private static void testComplejidad() {
        ArbolABB arbol = new ArbolABB();

        System.out.println("********************************************************************");
        System.out.println("*                    Test coste computacional                      *");
        System.out.println("********************************************************************");
        System.out.println(" - CASO PEOR - ");
        System.out.println("El coste computacional es igual a la altura del árbol");
        System.out.println("Si insertamos los valores ordenados sería el caso peor:");
        System.out.println("1, 2, 3, 4, 5");
        int[] ordenados = { 1, 2, 3, 4, 5 };
        System.out.println("Tendríamos el siguiente árbol:");
        String str = "";
        str += "  1                    \n";
        str += "   \\__                 \n";
        str += "      2                \n";
        str += "       \\__             \n";
        str += "          3            \n";
        str += "           \\__         \n";
        str += "              4        \n";
        str += "               \\__     \n";
        str += "                  5    \n";
        System.out.println(str);
        arbol = insertarValores(ordenados);
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println();
        System.out.println(" - CASO MEJOR - ");
        System.out.println("El coste computacional es igual a la altura del árbol");
        System.out.println("Si insertamos los siguientes valores obtendremos un arbol perfecto, completo y llenos, el caso mejor");
        System.out.println("25, 20, 28, 16, 22, 26, 29, 10, 16, 20, 24, 25, 26, 28, 29");
        System.out.println("Esto debería generar el siguiente árbol:");
         str = "";
        str += "                               25                                  \n";
        str += "                ______________/  \\______________                  \n";
        str += "               20                              28                  \n";
        str += "        ______/  \\______                ______/  \\______         \n";
        str += "       16              22              26              29          \n";
        str += "    __/  \\__        __/  \\__        __/  \\__        __/  \\__   \n";
        str += "   10      16      20      24      25      26      28      29      \n";
        System.out.println(str);
        int[] perfecto = { 25, 20, 28, 16, 22, 26, 29, 10, 16, 20, 24, 25, 26, 28, 29 };
        System.out.println("Comprobemos sus recorridos teóricos:");
        arbol.clear();
        arbol = insertarValores(perfecto);
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println();
        System.out.println(" - CASO MEDIO - ");
        System.out.println("Si insertamos 1.000.000 valores aleatorios obtendremos un árbol ");
        System.out.println("similar a uno real. Lo que sería aproximadamente el caso medio.");
        int elementos = (int) Math.pow(10, 5);
        Random random = new Random(System.currentTimeMillis());
        arbol.clear();
        for (int i = 0; i < elementos; i++) {
            int nRandom = random.nextInt(25000);
            arbol.insertar(nRandom);
        }
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

    }

    private static void testInsertarValoresDiferentes() {
        ArbolABB arbol = new ArbolABB();

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
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nValor máximo: " + arbol.maximo());
        System.out.println("Valor mínimo: " + arbol.minimo());
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("********************************************************************");
        System.out.println("*                       Test devolución lista                      *");
        System.out.println("********************************************************************");
        System.out.println("En vez de imprimir usando el método de impresión recuperamos los\nvalores en listas:");
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
        System.out.println();

        System.out.println("********************************************************************");
        System.out.println("*                           Test borrado                           *");
        System.out.println("********************************************************************");
        System.out.println("Borrar 8");
        if (arbol.borrar(8))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");

        System.out.println();
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("9 4 2 1 3 6 5 7 12 10 11 17 15 13 14 16 18 19");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 2 3 4 5 6 7 9 10 11 12 13 14 15 16 17 18 19");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 3 2 5 7 6 4 11 10 14 13 16 15 19 18 17 12 9");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("Borrar 12");
        if (arbol.borrar(12))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");

        System.out.println();
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("9 4 2 1 3 6 5 7 13 10 11 17 15 14 16 18 19");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 2 3 4 5 6 7 9 10 11 13 14 15 16 17 18 19");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 3 2 5 7 6 4 11 10 14 16 15 19 18 17 13 9");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("Borrar 17");
        if (arbol.borrar(17))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");

        System.out.println();
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("9 4 2 1 3 6 5 7 13 10 11 18 15 14 16 19");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 2 3 4 5 6 7 9 10 11 13 14 15 16 18 19");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 3 2 5 7 6 4 11 10 14 16 15 19 18 13 9");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("Borrar 16");
        if (arbol.borrar(16))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");

        System.out.println();
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("9 4 2 1 3 6 5 7 13 10 11 18 15 14 19");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 2 3 4 5 6 7 9 10 11 13 14 15 18 19");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 3 2 5 7 6 4 11 10 14 15 19 18 13 9");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("Borrar 15");
        if (arbol.borrar(15))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");

        System.out.println();
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("9 4 2 1 3 6 5 7 13 10 11 18 14 19");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("1 2 3 4 5 6 7 9 10 11 13 14 18 19");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("1 3 2 5 7 6 4 11 10 14 19 18 13 9");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("Borrar 20");
        if (arbol.borrar(20))
            System.out.println("Elemento eliminado");
        else
            System.out.println("Elemento no encontrado");
        arbol.print();
        System.out.println();
    }

    private static void testInsertarValoresRepetidos() {
        ArbolABB arbol = new ArbolABB();
        System.out.println("********************************************************************");
        System.out.println("*                  Test introducción de valores                    *");
        System.out.println("********************************************************************");
        System.out.println("Se permite introducir valores repetidos.");
        System.out.println("Introducimos los siguientes valores en este orden:");
        System.out.println("25, 20, 28, 16, 22, 26, 29, 10, 16, 20, 24, 25, 26, 28, 29");
        System.out.println("Esto debería generar el siguiente árbol:");
        String str = "";
        str += "                               25                                  \n";
        str += "                ______________/  \\______________                  \n";
        str += "               20                              28                  \n";
        str += "        ______/  \\______                ______/  \\______         \n";
        str += "       16              22              26              29          \n";
        str += "    __/  \\__        __/  \\__        __/  \\__        __/  \\__   \n";
        str += "   10      16      20      24      25      26      28      29      \n";
        System.out.println(str);
        int[] valores = { 25, 20, 28, 16, 22, 26, 29, 10, 16, 20, 24, 25, 26, 28, 29 };
        System.out.println("Comprobemos sus recorridos teóricos:");
        arbol = insertarValores(valores);
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("25 20 16 10 16 22 20 24 28 26 25 26 29 28 29");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("10 16 16 20 20 22 24 25 25 26 26 28 28 29 29");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("10 16 16 20 24 22 20 25 26 26 28 29 29 28 25");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nValor máximo: " + arbol.maximo());
        System.out.println("Valor mínimo: " + arbol.minimo());
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("********************************************************************");
        System.out.println("*                  Test borrado de valores                         *");
        System.out.println("********************************************************************");
        System.out.println("Borrar el raíz, elemento 25. Debería quedar el siguiente árbol:");
        str = "";
        str += "                               25                                 \n";
        str += "                ______________/  \\______________                 \n";
        str += "               20                              28                 \n";
        str += "        ______/  \\______                ______/  \\______        \n";
        str += "       16              22              26              29         \n";
        str += "    __/  \\__        __/  \\__             \\__        __/  \\__  \n";
        str += "   10      16      20      24              26      28      29     \n";
        System.out.println(str);
        arbol.borrar(25);
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("25 20 16 10 16 22 20 24 28 26 26 29 28 29");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("10 16 16 20 20 22 24 25 26 26 28 28 29 29");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("10 16 16 20 24 22 20 26 26 28 29 29 28 25");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("Borrar de nuevo el raíz, elemento 25. Debería quedar el siguiente árbol:");
        str = "";
        str += "                               26                                 \n";
        str += "                ______________/  \\______________                 \n";
        str += "               20                              28                 \n";
        str += "        ______/  \\______                ______/  \\______        \n";
        str += "       16              22              26              29         \n";
        str += "    __/  \\__        __/  \\__                        __/  \\__  \n";
        str += "   10      16      20      24                      28      29     \n";
        System.out.println(str);
        arbol.borrar(25);
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("26 20 16 10 16 22 20 24 28 26 29 28 29");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("10 16 16 20 20 22 24 26 26 28 28 29 29");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("10 16 16 20 24 22 20 26 28 29 29 28 26");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("Si volvemos a insertar un elemento 25 este ya no quedaría como raíz.");
        System.out.println("Debería quedar el siguiente árbol:");
        str = "";
        str += "                               26                                \n";
        str += "                ______________/  \\______________                \n";
        str += "               20                              28                \n";
        str += "        ______/  \\______                ______/  \\______       \n";
        str += "       16              22              26              29        \n";
        str += "    __/  \\__        __/  \\__                        __/  \\__  \n";
        str += "   10      16      20      24                      28      29    \n";
        str += "                             \\__                                \n";
        str += "                               25                                \n";
        System.out.println(str);
        arbol.insertar(25);
        System.out.println("\nRecorrido pre-order previsto:");
        System.out.println("26 20 16 10 16 22 20 24 25 28 26 29 28 29");
        System.out.println("Recorrido pre-order impreso por método:");
        arbol.print(PrintMode.PREORDER);
        System.out.println("\nRecorrido in-order previsto:");
        System.out.println("10 16 16 20 20 22 24 25 26 26 28 28 29 29");
        System.out.println("Recorrido in-order impreso por método:");
        arbol.print(PrintMode.INORDER);
        System.out.println("\nRecorrido post-order previsto:");
        System.out.println("10 16 16 20 25 24 22 20 26 28 29 29 28 26");
        System.out.println("Recorrido post-order impreso por método:");
        arbol.print(PrintMode.POSTORDER);
        System.out.println("\nRecorrido en achura:");
        arbol.breadthWalk();
        System.out.println("\nImprimir en arbol:");
        arbol.print(PrintMode.TREE);
        System.out.println("------------------------------------");
        System.out.println("Elementos en el arbol: " + arbol.getSize());
        System.out.println("Altura del arbol: " + arbol.altura());
    }

    private static ArbolABB insertarValores(int[] valores) {
        ArbolABB arbol = new ArbolABB();

        for (int i : valores) {
            arbol.insertar(i);
        }
        return arbol;
    }
}
