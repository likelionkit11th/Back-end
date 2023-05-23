package com.example.orderhw.Controller;
import com.example.orderhw.DTO.OrderProductDTO;
import com.example.orderhw.Service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class OrderProductController {
    private final OrderProductService orderproductService;

    @PostMapping("/orderproduct")
    public ResponseEntity<Long> orderproductCreate(@RequestBody OrderProductDTO orderproductDTO) {
        Long orderproductId = orderproductService.Create(orderproductDTO);
        return ResponseEntity.created(URI.create("/orderproduct/" + orderproductId)).body(orderproductId);
    }
}
