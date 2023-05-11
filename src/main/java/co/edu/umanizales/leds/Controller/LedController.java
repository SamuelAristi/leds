package co.edu.umanizales.leds.Controller;

import co.edu.umanizales.leds.Controller.dto.ErrorDTO;
import co.edu.umanizales.leds.Controller.dto.LedDTO;
import co.edu.umanizales.leds.Controller.dto.ResponseDTO;
import co.edu.umanizales.leds.Model.Led;
import co.edu.umanizales.leds.Service.ListDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.nimbus.State;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/leds")
public class LedController {
    @Autowired
    private ListDeService listDeService;

    @GetMapping(path = "/getlednext")
    public ResponseEntity<ResponseDTO> getLedNext(){
        return new ResponseEntity<>(new ResponseDTO(
                200, listDeService.getLeds().getLedNext(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/getledprev")
    public ResponseEntity<ResponseDTO> getLedPrev(){
        return new ResponseEntity<>(new ResponseDTO(
                200, listDeService.getLeds().getLedPrev(), null), HttpStatus.OK);
    }
    @PostMapping(path ="/addled")
    public ResponseEntity<ResponseDTO> addLed(@RequestBody LedDTO ledDTO) {
        // Verificar si el bombillo ya existe
        boolean isLedRegistered = listDeService.getLeds().checkLedById(ledDTO.getId());
        if (isLedRegistered) {
            return new ResponseEntity<>(new ResponseDTO(400, "Ya hay un bombillo con el mismo id", null),
                    HttpStatus.BAD_REQUEST);
        }

        // Crear el objeto Led y agregarlo a la lista
        Led newLed = new Led(ledDTO.getId(), false, null, null);
        listDeService.getLeds().addLed(newLed);

        return new ResponseEntity<>(new ResponseDTO(200, "El bombillo se ha agregado", null),
                HttpStatus.OK);
    }
    @PostMapping("/addledstar")
    public ResponseEntity<ResponseDTO> addLedStar(@RequestBody LedDTO ledDTO) {
        // Verificar si el bombillo ya existe
        boolean isLedRegistered = listDeService.getLeds().checkLedById(ledDTO.getId());
        if (isLedRegistered) {
            return new ResponseEntity<>(new ResponseDTO(400, "Ya hay un bombillo con el mismo id", null),
                    HttpStatus.BAD_REQUEST);
        }

        // Crear el objeto Led y agregarlo al inicio de la lista
        Led newLed = new Led(ledDTO.getId(), false, null, null);
        listDeService.getLeds().addLedStar(newLed);

        return new ResponseEntity<>(new ResponseDTO(200, "El bombillo se ha agregado al inicio de la lista", null),
                HttpStatus.OK);
    }

    @GetMapping(path ="/turnonleds")
    public ResponseEntity<ResponseDTO> turnOnAllLeds() throws InterruptedException {
        listDeService.getLeds().turnOnAllLeds();// llamada al método que enciende todos los LEDs
        return new ResponseEntity<>(new ResponseDTO(200, "Los bombillos se han encendido", null), HttpStatus.OK);
    }

    @GetMapping(path ="/turnoffallleds")
    public ResponseEntity<ResponseDTO> turnOffAllLeds() throws InterruptedException {
        listDeService.getLeds().turnOffAllLeds();
        return new ResponseEntity<>(new ResponseDTO(200, "Todos los LEDs se han apagado", null), HttpStatus.OK);
    }
    @GetMapping(path="turnoftrunon")
    public ResponseEntity<ResponseDTO> offOnLeds() throws  InterruptedException{
        listDeService.getLeds().offOnLeds();
        return new ResponseEntity<>(new ResponseDTO(200, "se ha ejecutado el metodo", null), HttpStatus.OK);
    }


}

