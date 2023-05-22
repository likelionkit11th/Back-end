package com.seoljy.hw5tablemapping.controller;

import com.seoljy.hw5tablemapping.dto.MemberDTO;
import com.seoljy.hw5tablemapping.dto.OrderProductDTO;
import com.seoljy.hw5tablemapping.service.OrderProductService;
import com.seoljy.hw5tablemapping.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderProductRestController {
    private final OrderProductService orderProductService;

    @PostMapping("/product")
    public ResponseEntity<Void> creatOrderProduct(@RequestBody OrderProductDTO orderProductDTO) {
        orderProductService.createOrderProduct(orderProductDTO);
        log.info("[CREATE]");
        return ResponseEntity.created(URI.create("/product")).build();
    }

}
