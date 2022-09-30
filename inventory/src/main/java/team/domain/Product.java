package team.domain;

import team.domain.NewAdded;
import team.domain.QtyChanged;
import team.InventoryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Product_table")
@Data

public class Product  {
    
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
     
    private Long productId;
    private String name;
    private String spec;
    private Long qty;
    private Long price;

    @PostPersist
    public void onPostPersist(){


        NewAdded newAdded = new NewAdded(this);
        newAdded.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){


        QtyChanged qtyChanged = new QtyChanged(this);
        qtyChanged.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
    }

    public static ProductRepository repository(){
        ProductRepository productRepository = InventoryApplication.applicationContext.getBean(ProductRepository.class);
        return productRepository;
    }




    public static void qtyDecrease(PaymentApproved paymentApproved){

        /** Example 1:  new item 
        Product product = new Product();
        repository().save(product);

        */

        /** Example 2:  finding and process **/

        repository().findById(paymentApproved.getProductId()).ifPresent(product->{
            
            // product.qty - paymentapproved.qty

            product.qty = product.getQty() - paymentApproved.getQty();
            
            repository().save(product);

         });

        
    }
    public static void qtyIncrease(PaymentCanceled paymentCanceled){

        /** Example 1:  new item 
        Product product = new Product();
        repository().save(product);

        */

        /** Example 2:  finding and process */
        
        repository().findById(paymentCanceled.getProductId()).ifPresent(product->{
            
            // product.qty + paymentapproved.qty

            product.qty = product.getQty() + paymentCanceled.getQty();

            repository().save(product);


         });

        
    }


}
