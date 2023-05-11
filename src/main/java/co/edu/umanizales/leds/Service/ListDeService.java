package co.edu.umanizales.leds.Service;

import co.edu.umanizales.leds.Model.ListDe;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDeService {
private ListDe leds;

public ListDeService(){leds=new ListDe();}

}
