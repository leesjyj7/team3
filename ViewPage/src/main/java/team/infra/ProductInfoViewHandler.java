package team.infra;

import team.domain.*;
import team.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoViewHandler {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenNewAdded_then_CREATE_1 (@Payload NewAdded newAdded) {
        try {

            if (!newAdded.validate()) return;

            // view 객체 생성
            ProductInfo productInfo = new ProductInfo();
            // view 객체에 이벤트의 Value 를 set 함
            productInfo.setProductId(newAdded.getProductId());
            productInfo.setName(newAdded.getName());
            productInfo.setSpec(newAdded.getSpec());
            productInfo.setQty(newAdded.getQty());
            productInfo.setPrice(newAdded.getPrice());
            // view 레파지 토리에 save
            productInfoRepository.save(productInfo);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenQtyChanged_then_UPDATE_1(@Payload QtyChanged qtyChanged) {
        try {
            if (!qtyChanged.validate()) return;
                // view 객체 조회
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(qtyChanged.getId());

            if( productInfoOptional.isPresent()) {
                 ProductInfo productInfo = productInfoOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                productInfo.setQty(qtyChanged.getQty());    
                // view 레파지 토리에 save
                 productInfoRepository.save(productInfo);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

