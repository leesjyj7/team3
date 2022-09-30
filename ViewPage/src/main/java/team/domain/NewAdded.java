package team.domain;

import team.infra.AbstractEvent;
import lombok.Data;
import java.util.*;

@Data
public class NewAdded extends AbstractEvent {

    private Long id;
    private Long productId;
    private String name;
    private String spec;
    private Long qty;
    private Long price;
}
