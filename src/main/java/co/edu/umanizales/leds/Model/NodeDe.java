package co.edu.umanizales.leds.Model;

import lombok.Data;

@Data
public class NodeDe {
    private Led data;
    private NodeDe next;
    private NodeDe prev;

    public NodeDe(Led data){
        this.data=data;
    }

}
