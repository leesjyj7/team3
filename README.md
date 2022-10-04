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




3. 구현

  - 분석/설계 단계에서 정리된 6개의 bounded context 를 기준으로 스프링부트로 구현
  - 각 서비스의 서비스 포트는 다음과 같다. 
    - order : 8081
    - delivery : 8082 
    - inventory : 8083
    - notification : 8084
    - payment : 8085
    - viewpage : 8086
  - 디렉토리 구조는 다음과 같다. (1 level)
  
  ![image](https://user-images.githubusercontent.com/110503179/193734762-1815a9bd-8c26-4421-95ce-9e586601f513.png)

- 서비스별 코드 구현 후에 kubernetes 에 배포 / 서비스 노출 

![image](https://user-images.githubusercontent.com/110503179/193735584-77f520fc-2282-441b-9a13-12f139c6312a.png)






4. 시나리오 & 기능 TEST 


   4-1. Saga (pub/sub)
 
   4-2. CQRS - read model
 
   4-3. compensation (취소모델)
 
   4-4. request/response - 동기호출 
 
   4-5. circuit breaker 
 
   4-6. Gateway/Ingress 
   
     - Ingress 설정
     - nginx 기반 
     - status 및 External IP 정보

![image](https://user-images.githubusercontent.com/110503179/193736212-5cb09976-da5f-4580-a3e3-9b7cc1e04423.png)

 
     - ingress를 통한 서비스별 접속 URL
       http://a82ab914de40041ddbc7aab4309c836c-1405241384.eu-west-3.elb.amazonaws.com/orders
       http://a82ab914de40041ddbc7aab4309c836c-1405241384.eu-west-3.elb.amazonaws.com/payments
       http://a82ab914de40041ddbc7aab4309c836c-1405241384.eu-west-3.elb.amazonaws.com/orderHistories
       http://a82ab914de40041ddbc7aab4309c836c-1405241384.eu-west-3.elb.amazonaws.com/productInfos
       http://a82ab914de40041ddbc7aab4309c836c-1405241384.eu-west-3.elb.amazonaws.com/deliveries
       http://a82ab914de40041ddbc7aab4309c836c-1405241384.eu-west-3.elb.amazonaws.com/products
       http://a82ab914de40041ddbc7aab4309c836c-1405241384.eu-west-3.elb.amazonaws.com/messages
 
 
   4-7. Deploy/Pipeline
 
   4-8. Autoscale (HPA)
 
   4-9. Zero-downtime deploy (readiness probe)
 
   4-10. Persistence Volumn

    - Mysql DB 설정
    - Mysql pod 생성 및 service 제공
    - secret 적용
    - 각 서비스별 database 생성 (MSA 사상 접목)

   4-11. Polyglot
 
   4-12. Self-healting (liveness probe)
 
 








