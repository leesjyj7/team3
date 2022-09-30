package team.domain;

import team.infra.AbstractEvent;
import lombok.Data;
import java.util.*;

@Data
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
}
