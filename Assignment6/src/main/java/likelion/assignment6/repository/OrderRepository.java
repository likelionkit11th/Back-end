package likelion.assignment6.repository;

import likelion.assignment6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.orderName like %?1%")
    Optional<List<Order>> findByOrderName(String orderName);

    @Query("select o From Order o where o.orderPrice >= ?1 and o.orderPrice <= ?2")
    Optional<List<Order>> findByPrices(Long small, Long big);

    @Query(value="select * from orders where order_name = ?1 and order_price = ?2", nativeQuery = true)
    Optional<Order> findByOrderNameAndOrderPrice(String orderName, Long price);

}
