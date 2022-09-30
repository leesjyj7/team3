package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long productId;
    private Long qty;
    private String address;
    private Date orderDate;
    private Date cancelDate;
    private Long cost;
    private String customerName;
    private String phoneNumber;
    private String status;
}


