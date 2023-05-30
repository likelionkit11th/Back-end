package com.example.orderhw.Controller;

import com.example.orderhw.DTO.MemberDto;
import com.example.orderhw.DTO.PaymentDto;
import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Member;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Repository.CancelRepository;
import com.example.orderhw.Service.CancelService;
import com.example.orderhw.Service.MemberService;
import com.example.orderhw.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cancel/")
public class CancelController {
    private final CancelService cancelService;

    @GetMapping("cancellist/{cancelId}")
    public ResponseEntity<CancelLog> findOneCancel(@PathVariable Long cancelId) throws Exception {
        CancelLog cancelLog = cancelService.findOneCancelLog(cancelId);
        return ResponseEntity.ok().body(cancelLog);
    }

    @GetMapping("cancellist")
    public ResponseEntity<List<CancelLog>> findAllCancels(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<CancelLog> cancelLogs = cancelService.getCancelPage(pageable);
        return ResponseEntity.ok().body(cancelLogs);
    }

}
