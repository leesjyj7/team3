# team3 - 꽃배달서비스

조원 - 이상준 / 이병수 / 남지연 / 황호준 / 박아름

1. 서비스 및 시나리오

 꽃배달 서비스 상점 운영 
 
 - 고객이 상품을 조회하여 선택/주문한다.
 - 주문이 되면 결제시스템으로 정보가 전달된다. 
 - 결제가 완료되면 배달이 시작되고 상품 재고량이 주문한만큼 감소되고 결재가 완료됨을 고객에게 알린다.
 - 배송이 완료되면 상태정보를 고객에게 알린다. 
 - 고객이 주문 리스트를 조회하여 취소하면 결제 및 배송이 취소되며 상품 재고량이 조정된다. 
 - 결재/배송/취소 등의 메세지를 고객에게 알린다. 
 - 현장방문 및 판매는 지원하지 않으며 온라인 전용 서비스이다. 
 
2. EventStorming 결과

 - order / payment / delivery / inventory / notification 총 5개의 서비스와 실시간 정보 조회를 위한 view page, 총 6개의 bounded context로 구분
 - 불필요하거나 자체적으로 구현이 불가한 스티커는 제거 
 - Saga(Pub/Sub), CQRS, Compensation, Req/Res, Circuit Brake 기능 적용을 위한 관계 표현

![image](https://user-images.githubusercontent.com/110503179/193733359-7f088b37-6928-43d3-a792-caed78c4ddce.png)


3. 

4.

5. 

6. 




