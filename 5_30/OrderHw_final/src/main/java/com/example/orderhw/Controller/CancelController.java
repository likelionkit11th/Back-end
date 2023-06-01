package com.example.orderhw.Controller;

import com.example.orderhw.DTO.ToBody.CancelOutDto;
import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Service.CancelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cancel/")
public class CancelController {
    private final CancelService cancelService;

    @GetMapping("cancellist/{cancelId}")
    public ResponseEntity<CancelOutDto> findOneCancel(@PathVariable Long cancelId) throws Exception {
        CancelOutDto cancelOutDto = cancelService.findOneCancelLog(cancelId);
        return ResponseEntity.ok().body(cancelOutDto);
    }

    @GetMapping("cancellist")
    public ResponseEntity<List<CancelOutDto>> findAllCancels(
            @RequestParam(value = "pageNum",required = false, defaultValue = "0") int pagenum,
            @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pagesize){
        Pageable pageable = PageRequest.of(pagenum,pagesize);
        List<CancelOutDto> cancelLogs = cancelService.getCancelPage(pageable);
        return ResponseEntity.ok().body(cancelLogs);
    }

}
