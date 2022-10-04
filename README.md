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


   4-1. Saga (Pub/Sub)

      - 서비스간 Saga Pattern 구현 (kafka topic 발췌)


![image](https://user-images.githubusercontent.com/110503179/193747966-57723925-85f1-406d-b5db-030764fb01ef.png)

 
   4-2. CQRS - read model
   
      - 주문정보 및 상품정보 대상 CQRS 구현
   
![image](https://user-images.githubusercontent.com/110503179/193744102-1419cecd-37f1-42a2-ae12-0a0dbd52bba5.png)
![image](https://user-images.githubusercontent.com/110503179/193744505-3667082e-cfdf-47e7-abdf-70401e11e90a.png)
   
 
   4-3. compensation (취소모델)
 
   4-4. request/response - 동기호출 
   
      - 코드 구현 / Order --> Product(inventory) 

![image](https://user-images.githubusercontent.com/110503179/193743687-2da46cfd-7be2-4c5e-8f43-c8be24ba7f9c.png)

![image](https://user-images.githubusercontent.com/110503179/193743729-6e42e385-bf83-44cc-8fb7-3f327795ab92.png)

 
   4-5. circuit breaker 
   
      - 재고량 보다 많은 제품 주문 시 에러 발생 

![image](https://user-images.githubusercontent.com/110503179/193743296-246cd4b2-1692-4680-8da5-305b8d8fd206.png)
![image](https://user-images.githubusercontent.com/110503179/193743339-312b389a-4999-4058-a43c-bcc0a3412f22.png)

 
   4-6. Gateway/Ingress 
   
     - nginx 기반 Ingress 설정
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
 
     - order 서비스 접속 예시
     
 ![image](https://user-images.githubusercontent.com/110503179/193743511-a1a0ef53-0546-46e3-85e5-247ed8974eac.png)

 
   4-7. Deploy/Pipeline
 
   4-8. Autoscale (HPA)
   
    - Auto Scaling 설정 (resource-CPU 사용량 기준)
  
 ![image](https://user-images.githubusercontent.com/110503179/193752328-875ba3cd-4759-40ca-877a-cc9d9b01cfa1.png)

 ![inven2](https://user-images.githubusercontent.com/110503179/193752035-705e84c1-e6a8-40a7-be5e-4ed54ac039ba.JPG)

![inven3](https://user-images.githubusercontent.com/110503179/193752046-ba66a267-6a82-4c7d-b7f6-31e917561042.JPG)


    - 부하발생
    
![image](https://user-images.githubusercontent.com/110503179/193752502-f44110a4-1c64-40e9-94f5-09d5ccd8954f.png)


![inven_노드확장상태](https://user-images.githubusercontent.com/110503179/193752054-6817f78f-9567-4924-a402-2b7c470be1d4.JPG)

    - resource 사용량이 늘어 node 가 작동 확장됨을 확인


4-9. Zero-downtime deploy (readiness probe)
   
    - readiness probe 설정전

![image](https://user-images.githubusercontent.com/110503179/193746797-e45129c9-6ba1-42b4-8208-d847f17ac9fb.png)

    - readiness probe 설정후
    
![image](https://user-images.githubusercontent.com/110503179/193746734-1e4bbb36-ae0e-4933-bbf5-920e1421eab9.png)
   
 
   4-10. Persistence Volumn
    - Mysql DB 설정
    - Mysql pod 생성 및 service 제공
    - secret 적용
    - 각 서비스별 database 생성 (MSA 사상 접목)
    
    - application.yaml 파일 설정 (orders)
    
![image](https://user-images.githubusercontent.com/110503179/193744895-fcf17d63-a1fe-4844-9d39-315c68895621.png)

    
    - application.yaml 파일 설정 (notifications)

![image](https://user-images.githubusercontent.com/110503179/193746378-cb3528bd-5fef-4bcb-ba99-f9d2fd7df78b.png)

    - application.yaml 파일 설정 (inventories/products)

![image](https://user-images.githubusercontent.com/110503179/193746475-27335e40-9815-4bfe-b45b-6f5241ea7970.png)

    
    - deployment.yaml 파일 설정 (orders)    
    
![image](https://user-images.githubusercontent.com/110503179/193744759-e39a3766-5ebd-4a1d-aee5-5b3b8cca9f66.png)
    
    - secret 설정    
    
![image](https://user-images.githubusercontent.com/110503179/193744963-d4753fb6-6858-40a5-8ee3-0ead22ef51b8.png)
    

   4-11. Polyglot
 
   4-12. Self-healting (liveness probe)
 
 








