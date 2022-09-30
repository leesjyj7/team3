package team.domain;

import team.NotificationApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Message_table")
@Data

public class Message  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
   
    private Long messageId;  
    private Long orderId;
    private Long productId;
    private Long qty;
    private Date approveDate;  
    private String phoneNumber;
    private String status;
    private Date startDate;
    private Date cancelDate;

    //kakao연계?
    @PostPersist
    public void onPostPersist(){

        Message message = new Message();
        message.setStatus("Sent");
        message.publishAfterCommit();
    }

    private void publishAfterCommit() {
    }

    public static MessageRepository repository(){
        MessageRepository messageRepository = NotificationApplication.applicationContext.getBean(MessageRepository.class);
        return messageRepository;
    }

    public static void notify(Shipped shipped){

        /** Example 1:  new item */
        Message message = new Message();
        message.setOrderId(shipped.getOrderId());
        // message.setStatus(shipped.getStatus());
        message.setStatus("Shippment has Started");
        message.setProductId(shipped.getProductId());
        message.setQty(shipped.getQty());
        message.setPhoneNumber(shipped.getPhoneNumber());
        message.setStartDate(shipped.getStartDate());
        repository().save(message);

        
    }


    public static void notify(ShippingCancled shippingCancled){

        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process*/
        
        repository().findByOrderId(shippingCancled.getOrderId()).ifPresent(message->{
            
            message.setStatus("Shipping has been Canceled"); 
            repository().save(message);
         });
        
        

        
    }
    public static void notify(PaymentApproved paymentApproved){

        /** Example 1:  new item  */
        Message message = new Message();
        message.setOrderId(paymentApproved.getOrderid());
        // message.setStatus(paymentApproved.getStatus());
        message.setStatus("Payment has been Completed");
        message.setPhoneNumber(paymentApproved.getPhoneNumber());
        message.setProductId(paymentApproved.getProductId());  
        message.setQty(paymentApproved.getQty());
        message.setApproveDate(paymentApproved.getApproveDate());
        repository().save(message);
     
    }

    public static void notify(PaymentCanceled paymentCanceled){
        /** Example 2:  finding and process*/
        
         repository().findByOrderId(paymentCanceled.getOrderid()).ifPresent(message->{
            
            message.setStatus("Payment was Canceled"); 
            message.setCancelDate(paymentCanceled.getCancelDate()); 
            repository().save(message);
         });
        

        
    }


}
