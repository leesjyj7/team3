package team.domain;

import team.infra.AbstractEvent;
import lombok.Data;
import java.util.*;

@Data
public class Shipped extends AbstractEvent {

    private Long id;
    private Long qty;
    private String address;
    private String status;
    private Date startDate;
    private Date expectDate;
    private Long orderId;
    private Date shipcompletedate;
    private String phoneNumber;
    private Long productId;
}
