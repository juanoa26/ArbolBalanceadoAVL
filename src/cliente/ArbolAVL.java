package cliente;

import javax.swing.JFrame;

/**
 *
 * @author juseb
 */
public class ArbolAVL {

    private NodoArbolAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public NodoArbolAVL getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbolAVL raiz) {
        this.raiz = raiz;
    }

    //OBTENER FACTOR DE EQUILIBRIO
    public int obtenerFE(NodoArbolAVL r) {
        if (r == null) {
            return -1;
        } else {
            return r.fe;
        }
    }

    //ROTACION SIMPLE A LA IZQUIERDA
    public NodoArbolAVL rotarIzquierda(NodoArbolAVL r) {
        NodoArbolAVL auxiliar = r.izq;
        r.izq = auxiliar.der;
        auxiliar.der = r;
        r.fe = Math.max(obtenerFE(r.der), obtenerFE(r.izq)) + 1;
        auxiliar.fe = Math.max(obtenerFE(r.der), obtenerFE(r.izq)) + 1;
        return auxiliar;
    }

    //ROTACION SIMPLE A LA DERECHA
    public NodoArbolAVL rotarDerecha(NodoArbolAVL r) {
        NodoArbolAVL auxiliar = r.der;
        r.der = auxiliar.izq;
        auxiliar.izq = r;
        r.fe = Math.max(obtenerFE(r.izq), obtenerFE(r.der)) + 1;
        auxiliar.fe = Math.max(obtenerFE(r.izq), obtenerFE(r.der)) + 1;
        return auxiliar;
    }

    //ROTACION DOBLE A LA IZQUIERDA
    public NodoArbolAVL rotarIzqDer(NodoArbolAVL r) {
        NodoArbolAVL temp;
        r.izq = rotarDerecha(r.izq);
        temp = rotarIzquierda(r);
        return temp;
    }

    //ROTACION DOBLE A LA DERECHA
    public NodoArbolAVL rotarDerIzq(NodoArbolAVL r) {
        NodoArbolAVL temp;
        r.der = rotarIzquierda(r.der);
        temp = rotarDerecha(r);
        return temp;
    }

    //METODO PARA INSERTAR
    public void insertar(int dato) {
        NodoArbolAVL nuevo = new NodoArbolAVL(dato);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarAVL(nuevo, raiz);
        }
    }

    public NodoArbolAVL insertarAVL(NodoArbolAVL nuevo, NodoArbolAVL subArb) {
        NodoArbolAVL nuevoPadre = subArb;
        if (nuevo.dato < subArb.dato) {
            if (subArb.izq == null) {
                subArb.izq = nuevo;
            } else {
                subArb.izq = insertarAVL(nuevo, subArb.izq);
                if ((obtenerFE(subArb.izq) - obtenerFE(subArb.der)) == 2) {
                    if (nuevo.dato < subArb.izq.dato) {
                        nuevoPadre = this.rotarIzquierda(subArb);
                        System.out.println("Rotacion simple a la izquierda: " + nuevoPadre.dato);
                    } else {
                        nuevoPadre = this.rotarIzqDer(subArb);
                        System.out.println("Rotacion doble a la izquierda: " + nuevoPadre.dato);
                    }
                }
            }
        } else if (nuevo.dato > subArb.dato) {
            if (subArb.der == null) {
                subArb.der = nuevo;
            } else {
                subArb.der = insertarAVL(nuevo, subArb.der);
                if ((obtenerFE(subArb.der) - obtenerFE(subArb.izq)) == 2) {
                    if (nuevo.dato > subArb.der.dato) {
                        nuevoPadre = this.rotarDerecha(subArb);
                        System.out.println("Rotacion simple a la derecha: " + nuevoPadre.dato);
                    } else {
                        nuevoPadre = this.rotarDerIzq(subArb);
                        System.out.println("Rotacion doble a la derecha: " + nuevoPadre.dato);
                    }
                }
            }
        } else {
            System.out.println("Nodo duplicado" + nuevo.dato);
        }

        //ACTUALIZAR LA ALTURA
        if ((subArb.izq == null) && (subArb.der != null)) {
            subArb.fe = subArb.der.fe + 1;
        } else if ((subArb.izq == null) && (subArb.der != null)) {
            subArb.fe = subArb.izq.fe + 1;
        } else {
            subArb.fe = Math.max(obtenerFE(subArb.izq), obtenerFE(subArb.der)) + 1;
        }
        return nuevoPadre;
    }
    
    //NODOS COMPLETOS
    public int nodosCompletos(NodoArbolAVL r) {
        if(r == null)
            return 0;
        else {
            if(r.getIzq() != null && r.getDer() != null) {
                return nodosCompletos(r.getIzq()) + nodosCompletos(r.getDer()) +1;
            }
            return nodosCompletos(r.getIzq()) + nodosCompletos(r.getDer());
        }
    }
    
    public void graficar(String titulo) {
        GraficaArbol MiGrafico = new GraficaArbol(this);
        JFrame ventana = new JFrame("Arbol binario de busqueda");
        ventana.getContentPane().add(MiGrafico);
        ventana.setDefaultCloseOperation(3);
        ventana.setSize(800, 600);
        ventana.setVisible(true);
        
    }
}
