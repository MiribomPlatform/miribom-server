package com.miribom.app.server.controller;

import com.miribom.app.server.bo.RestaurantBo;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jasonyang
 */
@RestController
@RequestMapping(value = "/restaurants", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestaurantController {

    @Autowired
    private RestaurantBo restaurantBo;


    /**
     * 사용자 운영 식당 목록 조회 API
     * @param userNo
     * @return
     */

    @ApiOperation(value = "사용자 No -> 소유 식당 리스트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "사용자 No", required = true)
    })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SimpleRestaurantInfo> listByUserNo(@RequestParam(value = "userNo") int userNo){
        List <SimpleRestaurantInfo> restList = restaurantBo.getRestList(userNo);
        return restList;
    }

}
