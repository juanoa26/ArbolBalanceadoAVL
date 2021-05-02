package cliente;

/**
 *
 * @author juseb
 */
public class Test {

    public static void main(String[] args) {
        ArbolAVL arbolAVL = new ArbolAVL();
        
        //INSERTAR LOS NODOS
        arbolAVL.insertar(10);
        arbolAVL.insertar(5);
        arbolAVL.insertar(13);
        arbolAVL.insertar(1);
        arbolAVL.insertar(6);
        arbolAVL.insertar(17);
        arbolAVL.graficar("Arbol Binario");
        arbolAVL.insertar(16);
        arbolAVL.insertar(20);
        arbolAVL.insertar(25);
    }
    
}
