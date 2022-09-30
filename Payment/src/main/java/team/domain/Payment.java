package team.domain;

import team.domain.PaymentApproved;
import team.domain.PaymentCanceled;
import team.PaymentApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Payment_table")
@Data

public class Payment  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
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

    @PostPersist
    public void onPostPersist(){


        PaymentApproved paymentApproved = new PaymentApproved(this);
        paymentApproved.setStatus("PaymentApproved");
        paymentApproved.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){


        PaymentCanceled paymentCanceled = new PaymentCanceled(this);
        paymentCanceled.setStatus("PaymentCanceled");
        paymentCanceled.publishAfterCommit();

    }

    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }




    public static void paymentCancel(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process   */
        
        repository().findByOrderid(orderCanceled.getId()).ifPresent(payment->{
            final Date nowDate = new Date();
            payment.setStatus("CANCELLED"); // do something
            payment.setProductId(orderCanceled.getProductId());
            payment.setQty(orderCanceled.getQty());

            System.out.println("paymentCancel : " + nowDate);

            payment.setCancelDate(nowDate);
            repository().save(payment);

         });

        
    }
    public static void payment(Ordered ordered){

        /** Example 1:  new item  */
        Payment payment = new Payment();
        final Date nowDate = new Date();

        System.out.println("payment : " + ordered.getId());

        payment.setOrderid(ordered.getId());
        payment.setStatus("SUCCESS");
        payment.setProductId(ordered.getProductId());
        payment.setQty(ordered.getQty());
        payment.setAddress(ordered.getAddress());
        payment.setPhoneNumber(ordered.getPhoneNumber());
        payment.setApproveDate(nowDate);
        
        repository().save(payment);


        /** Example 2:  finding and process
        
        repository().findById(ordered.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

        
    }


}
