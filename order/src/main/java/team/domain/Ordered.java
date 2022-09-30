package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Ordered extends AbstractEvent {

    private Long id;
    private Long productId;
    private Long qty;
    private String address;
    private Date orderDate;
    private Long cost;
    private String customerName;
    private String phoneNumber;
    private String status;

    public Ordered(Order aggregate){
        super(aggregate);
    }
    public Ordered(){
        super();
    }
}
