package team.domain;

import java.util.*;
import lombok.Data;
import team.infra.AbstractEvent;

@Data
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
}
