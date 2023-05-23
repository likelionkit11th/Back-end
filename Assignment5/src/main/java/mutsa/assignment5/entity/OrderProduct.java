package mutsa.assignment5.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@Table(name="ordered_product")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancel_id")
    private CancellationDetail cancellationDetail;

    private String productName;
    private Long productPrice;
    private Long productAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus productStatus;

    public static OrderProduct newOrderProduct(CancellationDetail cancellationDetail, String productName, Long productPrice, Long productAmount){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCancellationDetail(cancellationDetail);
        orderProduct.setProductName(productName);
        orderProduct.setProductPrice(productPrice);
        orderProduct.setProductAmount(productAmount);
        orderProduct.setProductStatus(OrderStatus.NONE);
        return orderProduct;
    }


}
