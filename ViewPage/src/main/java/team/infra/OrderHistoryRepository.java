package team.infra;

import team.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="orderHistories", path="orderHistories")
public interface OrderHistoryRepository extends PagingAndSortingRepository<OrderHistory, Long> {

    List<OrderHistory> findByStatus(String status);
List<OrderHistory> findByShippedStatus(String shippedStatus);
List<OrderHistory> findByApproveStatus(String approveStatus);


    
}
