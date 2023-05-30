package likelion.assignment6.repository;

import likelion.assignment6.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{
}
