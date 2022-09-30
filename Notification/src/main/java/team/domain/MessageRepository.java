package team.domain;

import team.domain.*;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="messages", path="messages")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>{
    Optional<Message> findByOrderId(Long orderid);  //select * from delivery where orderId=?
}
