package team.domain;

import team.domain.Ordered;
import team.domain.OrderCanceled;
import team.OrderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Order_table")
@Data

public class Order  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
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

    @PostPersist
    public void onPostPersist(){
        // Get request from Product
        //team.external.Product product =
        //    Application.applicationContext.getBean(team.external.ProductService.class)
        //    .getProduct(/** mapping value needed */);

    }
    @PrePersist
    public void onPrePersist(){


        Ordered ordered = new Ordered(this);
        ordered.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){


        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();

    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }






}
