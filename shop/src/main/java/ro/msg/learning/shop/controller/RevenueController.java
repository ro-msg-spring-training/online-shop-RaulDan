package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.RevenueDto;
import ro.msg.learning.shop.mappers.RevenueMapper;
import ro.msg.learning.shop.service.impl.RevenueServiceImpl;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class RevenueController {

    private final RevenueMapper revenueMapper;
    private final RevenueServiceImpl revenueService;

    @RequestMapping("/getRevenue")
    public ResponseEntity<RevenueDto> getRevenuesForASpecificDate(String date){
        return new ResponseEntity<>(revenueMapper.toRevenueDtoFromRevenue(revenueService.findByDate(date)), HttpStatus.OK);
    }
}
