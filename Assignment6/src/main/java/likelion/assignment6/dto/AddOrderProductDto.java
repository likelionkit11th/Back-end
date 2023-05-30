package likelion.assignment6.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import likelion.assignment6.entity.CancellationDetail;
import likelion.assignment6.entity.OrderProduct;

@NoArgsConstructor
@Getter
public class AddOrderProductDto {
    private CancellationDetail cancellationDetail;
    private String productName;
    private Long productPrice;
    private Long productAmount;

    public OrderProduct toEntity(){
        return OrderProduct.newOrderProduct(
                null,
                this.productName,
                this.productPrice,
                this.productAmount
        );
    }
}

