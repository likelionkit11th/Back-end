package likelion.assignment6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor()
public class AddOrderDto {
    private Long memberId;
    private String orderName;
    private Long orderPrice;
    private List<AddOrderProductDto> orderProducts;

}