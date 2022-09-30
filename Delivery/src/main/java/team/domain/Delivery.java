package team.domain;

import team.domain.Shipped;
import team.domain.ShippingCancled;
import team.DeliveryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Delivery_table")
@Data

public class Delivery  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
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

    @PostPersist
    public void onPostPersist(){


        Shipped shipped = new Shipped(this);
        shipped.setStatus("Shipped");
        shipped.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){


        ShippingCancled shippingCancled = new ShippingCancled(this);
        shippingCancled.setStatus("ShippingCanceled");
        shippingCancled.publishAfterCommit();

    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }




    public static void shipping(PaymentApproved paymentApproved){

        /** Example 1:  new item      */
        Delivery delivery = new Delivery();
        delivery.setOrderId(paymentApproved.getOrderid());
        delivery.setAddress(paymentApproved.getAddress());
        
        repository().save(delivery);

   

        /** Example 2:  finding and process
        
        repository().findById(paymentApproved.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

        
    }
    public static void shippingCancle(PaymentCanceled paymentCanceled){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process   */
        
        repository().findByOrderId(paymentCanceled.getOrderid()).ifPresent(delivery->{
            
            delivery.setStatus("CANCELLED"); // do something
            repository().save(delivery);


         });

        
    }


}
