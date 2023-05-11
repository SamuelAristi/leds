package co.edu.umanizales.leds.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Led {
      private int id;
    private  boolean state;
    private LocalDateTime dateOn;
    private LocalDateTime dateOff;
}
