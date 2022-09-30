package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class QtyChanged extends AbstractEvent {

    private Long id;
    private Long productId;
    private String name;
    private String spec;
    private Long qty;
    private Long price;

    public QtyChanged(Product aggregate){
        super(aggregate);
    }
    public QtyChanged(){
        super();
    }
}
