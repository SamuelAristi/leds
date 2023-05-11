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
    private NodeDe tail;
    private int size;

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
    public void addLedStar(Led newLed) {
        NodeDe newNode = new NodeDe(newLed);
        if (head != null) {
            newNode.setNext(head);
            head.setPrev(newNode);
        }
        head = newNode;
        size++;
    }


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

    public List<Led> getLedPrev() {
        leds = new ArrayList<>();
        NodeDe temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        leds.add(temp.getData());
        while (temp.getPrev() != null) {
            temp = temp.getPrev();
            leds.add(temp.getData());
        }
        return leds;
    }

    public void turnOnAllLeds() throws InterruptedException {
        NodeDe temp = head;
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.ofSeconds(0);
        while (temp != null) {
            TimeUnit.SECONDS.sleep(1);
            temp.getData().setState(true);
            LocalDateTime dateOn = now.plus(duration);
            temp.getData().setDateOn(dateOn);
            duration = duration.plusSeconds(1);
            temp = temp.getNext();
        }
    }

    public void turnOffAllLeds() throws InterruptedException {
        NodeDe temp = head;
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.ofSeconds(0);
        while (temp != null) {
            TimeUnit.SECONDS.sleep(1); // espera un segundo antes de apagar el LED
            temp.getData().setState(false);  // apaga el LED actual
            LocalDateTime dateOff = now.plus(duration);
            temp.getData().setDateOff(dateOff); // establece la hora en que se apagó
            duration = duration.plusSeconds(1);
            temp = temp.getNext();  // pasa al siguiente nodo
        }
    }



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







