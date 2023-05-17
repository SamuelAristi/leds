package co.edu.umanizales.leds.Model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Data
public class ListDe {

    private List<Led> leds;
    private NodeDe head;

    private int size;

    /*
    * Se verifica si la lista está vacía. Si la cabeza es nula, significa que no hay nodos en la lista y el
    * nuevo nodo se establecerá como la cabeza de la lista.

    Si la lista no está vacía, se inicializa un nodo temporal temp con el valor de la cabeza.

    Se recorre la lista hasta encontrar el último nodo. Esto se hace iterando mientras el nodo siguiente  del nodo temporal temp
    no sea nulo. En cada iteración, se actualiza el nodo temporal temp con el siguiente nodo de la lista.

    Una vez que se encuentra el último nodo de la lista, se establece el siguiente nodo  como el nuevo nodo n

    Luego, se establece el nodo anterior  del nuevo nodo newNode como el último nodo de la lista, que es el nodo temporal temp.

    Por último, se incrementa el tamaño de la lista en 1
    * */
    public void addLed(Led newLed) {
        NodeDe newNode = new NodeDe(newLed);
        if (head == null) {  // si la lista está vacía, el nuevo nodo será la cabeza
            head = newNode;
        } else {
            NodeDe temp = head;
            while (temp.getNext() != null) {  // busca el último nodo de la lista
                temp = temp.getNext();
            }
            temp.setNext(newNode);  // agrega el nuevo nodo al final de la lista

            newNode.setPrev(temp);  // establece el nodo anterior del nuevo nodo como el último nodo de la lista
        }

        size++;
    }
    /*
    Se verifica si la cabeza  no es nula, lo que significa que la lista no está vacía.

    Si la lista no está vacía, se establece el siguiente nodo  del nuevo nodo newNode como la cabeza actual de la lista.
     Esto significa que el nuevo nodo se coloca antes de la cabeza actual.

    A continuación, se establece el nodo anterior  de la cabeza actual de la lista como el nuevo nodo newNode.
     Esto asegura que el enlace anterior del nodo cabeza apunte al nuevo nodo.

    Después de actualizar los enlaces del nuevo nodo y la cabeza actual, se actualiza la cabeza  de la lista para que
    sea el nuevo nodo newNode. Ahora el nuevo nodo se convierte en la nueva cabeza de la lista.

    Por último, se incrementa el tamaño de la lista en 1.
*/
    public void addLedStar(Led newLed) {
        NodeDe newNode = new NodeDe(newLed);
        if (head != null) {
            newNode.setNext(head);
            head.setPrev(newNode);
        }
        head = newNode;
        size++;
    }


    /*
    Se inicializa un nodo temporal temp con el valor de la cabeza  de la lista.

    recorremios la lista mientras el nodo temporal temp no sea nulo, es decir,
    mientras no se haya llegado al final de la lista.

    se verifica si el identificador  del nodo actual ) es igual al identificador buscado

    Si  coinciden, se devuelve true, lo que indica que se ha encontrado un nodo con el identificador buscado.

    Si no coinciden, se actualiza el nodo temporal tcon el siguiente nodo de la lista  y se repite el proceso en el siguiente nodo.

    Si  se completa sin encontrar un nodo con el identificador buscado, se devuelve false, lo que indica que no
    se encontró ningún nodo con ese identificador en la lista*/
    public boolean checkLedById(int id) {
        NodeDe temp = head;
        while (temp != null) {
            if (temp.getData().getId() == id) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /*
    Se crea una nueva lista llamada leds, que se utilizará para almacenar los nodos de la lista enlazada.

    Se inicializa un nodo temporal temp con el valor de la cabeza  de la lista.

    Se agrega el dato  del nodo actual  a la lista leds utilizando el método añadir

    recorremos  mientras el nodo siguiente  del nodo temporal temp no sea nulo, es decir, mientras no se haya llegado al final de la lista.

    actualizamos el nodo temporal con el siguiente nodo de la lista  y se agrega su dato  a la lista leds utilizando el método add.

    E se repite hasta que se haya recorrido toda la lista y no haya más nodos siguientes.

    Una vez completado el bucle, se devuelve la lista leds que contiene todos los datos de los nodos de la lista enlazada.*/
    public List<Led> getLedNext() {
        leds = new ArrayList<>();
        NodeDe temp = head;
        leds.add(temp.getData());
        while (temp.getNext() != null) {
            temp = temp.getNext();
            leds.add(temp.getData());
        }
        return leds;
    }


    /*
    Se inicializan dos nodos temporales, uno siguiente y otro previo con el valor de la cabeza (head) de la lista.

    Se verifica si el tamaño de la lista es igual a 1. En ese caso, se establece el estado del LED (encendido), se establece la
     fecha de encendido del LED en el momento actual y se sale del método.

    Si el tamaño de la lista es mayor que 1, se recorre la mitad de la lista. El nodo temporal se actualiza para apuntar
     al nodo que está en la posición de la mitad de la lista.

    Se verifica si el tamaño de la lista es par o impar. Si es par, el nodo siguiente se establece
     como el siguiente nodo del previo. Si es impar, el nodo previo se actualiza para apuntar al siguiente nodo de previo,
     y siguiente se establece como igual al previo, parado en lamisma posicion

    recorremos mientras previo no sea nulo.

    se establece el estado de los LEDs en previo y siguiente  (encendido), se establece la fecha de encendido de los LEDs en el
     momento actual.

    Si previo tiene un nodo anterior , se espera durante 1 segundo utilizando
    Luego, se establece el estado de los LEDs en temporal y siguiente en  (apagado), y se establece la fecha de
    apagado de los LEDs en el momento actual.

    Se espera durante 1 segundo nuevamente.

    Se actualiza previo para apuntar al nodo anterior de previo, y siguiente se actualiza para apuntar al siguiente nodo de siguiente.

    esto se repite hasta que se hayan recorrido todos los nodos de la lista
*/
    public void offOnLeds() throws InterruptedException {
        NodeDe tempNext= head;
        NodeDe temPrev=head;
        if(size==1){
            temPrev.getData().setState(true);
            temPrev.getData().setDateOn(LocalDateTime.now());
            return;
        }
        for(int i=1; i< size/2;i++){
            temPrev=temPrev.getNext();
        }
        if (size%2==0){
            tempNext = temPrev.getNext();
        }
        else {
            temPrev=temPrev.getNext();
            tempNext=temPrev;
        }
        while (temPrev != null){
            temPrev.getData().setState(true);
            tempNext.getData().setState(true);
            temPrev.getData().setDateOn(LocalDateTime.now());
            tempNext.getData().setDateOn(LocalDateTime.now());
            if(temPrev.getPrev()!=null) {
                TimeUnit.SECONDS.sleep(1);
                temPrev.getData().setState(false);
                tempNext.getData().setState(false);
                temPrev.getData().setDateOff(LocalDateTime.now());
                tempNext.getData().setDateOff(LocalDateTime.now());
                TimeUnit.SECONDS.sleep(1);
            }

            temPrev = temPrev.getPrev();
            tempNext =tempNext.getNext();


        }

    }

}







