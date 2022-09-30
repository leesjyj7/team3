package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PaymentApproved extends AbstractEvent {

    private Long id;
    private Long orderid;
    private String status;
    private Long cost;
    private Date approveDate;
    private Date cancelDate;
    private String address;
    private Long productId;
    private String phoneNumber;
    private Long qty;

    public PaymentApproved(Payment aggregate){
        super(aggregate);
    }
    public PaymentApproved(){
        super();
    }
}
