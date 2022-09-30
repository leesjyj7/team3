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


    public static MessageRepository repository(){
        MessageRepository messageRepository = NotificationApplication.applicationContext.getBean(MessageRepository.class);
        return messageRepository;
    }




    public static void notify(Shipped shipped){

        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        
        repository().findById(shipped.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

        
    }
    public static void notify(ShippingCancled shippingCancled){

        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        
        repository().findById(shippingCancled.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

        
    }
    public static void notify(PaymentApproved paymentApproved){

        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentApproved.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

        
    }
    public static void notify(PaymentCanceled paymentCanceled){

        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

        
    }


}
