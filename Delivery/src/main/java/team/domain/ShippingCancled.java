package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ShippingCancled extends AbstractEvent {

    private Long id;
    private Long qty;
    private String address;
    private String status;
    private Date shipcompletedate;
    private Date startDate;
    private Date expectDate;
    private Long orderId;
    private String phoneNumber;
    private Long productId;

    public ShippingCancled(Delivery aggregate){
        super(aggregate);
    }
    public ShippingCancled(){
        super();
    }
}
