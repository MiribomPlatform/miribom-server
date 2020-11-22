package com.miribom.app.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miribom.app.server.bo.RestaurantBo;
import com.miribom.app.server.controller.model.RestaurantCreateRequest;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author jasonyang, changwoo.son
 */
@RestController
@RequestMapping(value = "/restaurants", produces = { MediaType.APPLICATION_JSON_VALUE })
public class RestaurantController {
    @Autowired
    private RestaurantBo restaurantBo;


    @ApiOperation(value = "레스토랑 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userNo", value = "사용자 No", required = true, type = "query"),
        @ApiImplicitParam(name = "restaurantName", value = "레스토랑 이름", required = true, type = "body"),
        @ApiImplicitParam(name = "address", value = "레스토랑 주소", required = true, type = "body"),
        @ApiImplicitParam(name = "mobile", value = "레스토랑 전화번호", required = true, type = "body"),
        @ApiImplicitParam(name = "restaurantType", value = "레스토랑 타입", type = "body"),
        @ApiImplicitParam(name = "image", value = "대표 이미지", type = "body"),
        @ApiImplicitParam(name = "welcomeMessage", value = "환영 메시지", type = "body"),
    })
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@RequestParam int userNo, @RequestBody RestaurantCreateRequest req) {
        return restaurantBo.create(userNo, req.getRestaurantName(), req.getAddress(), req.getMobile(), req.getRestaurantType(), req.getImage(), req.getWelcomeMessage());
    }

    /**
     * 사용자 운영 식당 목록 조회 API
     * @param userNo
     * @return
     */
    @ApiOperation(value = "소유한 식당 리스트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "사용자 No", required = true, type = "query")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SimpleRestaurantInfo> listByUserNo(@RequestParam(value = "userNo") int userNo) {
        return restaurantBo.getSimpleRestaurantList(userNo);
    }
}
