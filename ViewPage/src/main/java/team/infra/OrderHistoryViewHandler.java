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
public class OrderHistoryViewHandler {


    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {

            if (!ordered.validate()) return;

            // view 객체 생성
            OrderHistory orderHistory = new OrderHistory();
            // view 객체에 이벤트의 Value 를 set 함
            orderHistory.setOrderId(ordered.getId());
            orderHistory.setQty(ordered.getQty());
            // view 레파지 토리에 save
            orderHistoryRepository.save(orderHistory);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_UPDATE_1(@Payload OrderCanceled orderCanceled) {
        try {
            if (!orderCanceled.validate()) return;
                // view 객체 조회

                List<OrderHistory> orderHistoryList = orderHistoryRepository.findByStatus(orderCanceled.getStatus());
                for(OrderHistory orderHistory : orderHistoryList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderHistory.setOrderStatus("OrderCanceled");
                // view 레파지 토리에 save
                orderHistoryRepository.save(orderHistory);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_UPDATE_2(@Payload Ordered ordered) {
        try {
            if (!ordered.validate()) return;
                // view 객체 조회

                List<OrderHistory> orderHistoryList = orderHistoryRepository.findByStatus(ordered.getStatus());
                for(OrderHistory orderHistory : orderHistoryList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderHistory.setOrderStatus("Ordered");
                // view 레파지 토리에 save
                orderHistoryRepository.save(orderHistory);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenShipped_then_UPDATE_3(@Payload Shipped shipped) {
        try {
            if (!shipped.validate()) return;
                // view 객체 조회

                List<OrderHistory> orderHistoryList = orderHistoryRepository.findByShippedStatus(shipped.getStatus());
                for(OrderHistory orderHistory : orderHistoryList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderHistory.setShippedStatus("Shipped");
                // view 레파지 토리에 save
                orderHistoryRepository.save(orderHistory);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenShippingCancled_then_UPDATE_4(@Payload ShippingCancled shippingCancled) {
        try {
            if (!shippingCancled.validate()) return;
                // view 객체 조회

                List<OrderHistory> orderHistoryList = orderHistoryRepository.findByShippedStatus(shippingCancled.getStatus());
                for(OrderHistory orderHistory : orderHistoryList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderHistory.setShippedStatus("ShippingCanceled");
                // view 레파지 토리에 save
                orderHistoryRepository.save(orderHistory);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_5(@Payload PaymentApproved paymentApproved) {
        try {
            if (!paymentApproved.validate()) return;
                // view 객체 조회

                List<OrderHistory> orderHistoryList = orderHistoryRepository.findByApproveStatus(paymentApproved.getStatus());
                for(OrderHistory orderHistory : orderHistoryList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                // view 레파지 토리에 save
                orderHistoryRepository.save(orderHistory);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCanceled_then_UPDATE_6(@Payload PaymentCanceled paymentCanceled) {
        try {
            if (!paymentCanceled.validate()) return;
                // view 객체 조회

                List<OrderHistory> orderHistoryList = orderHistoryRepository.findByApproveStatus(paymentCanceled.getStatus());
                for(OrderHistory orderHistory : orderHistoryList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderHistory.setApproveStatus("PaymentCanceled");
                // view 레파지 토리에 save
                orderHistoryRepository.save(orderHistory);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_DELETE_1(@Payload OrderCanceled orderCanceled) {
        try {
            if (!orderCanceled.validate()) return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

