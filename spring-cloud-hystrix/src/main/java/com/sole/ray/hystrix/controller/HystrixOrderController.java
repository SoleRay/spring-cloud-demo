package com.sole.ray.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.bean.result.ResultCode;
import com.sole.ray.internal.common.entity.Order;
import com.sole.ray.internal.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ResponseResult
@RestController
@RequestMapping("/hystrix-order")
public class HystrixOrderController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getOrderFailed")
    @PostMapping("/get")
    public Result getOrder() {
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/get", null, Result.class);
        return responseEntity.getBody();
    }

    /**
     * 注意事项
     * 1.此时不建议将方法设置为void
     * 因为要求fallback的方法和当前方法一致。
     * 如果当前方法为void，fallback也只能设置为void。
     * 因为ResponseResultHandler的存在,我们不需要为每个方法调用Result
     * 但这同样是个限制，因为方法正常执行，都是返回sucess。
     * fallback的方法也是如此，但实际上，我们需要让fallback方法返回error
     * 所以建议这里返回值都统一设置为Result
     */
    @HystrixCommand(fallbackMethod = "addOrderFailed", commandProperties = {
            @HystrixProperty(name = "fallback.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true")})
    @PostMapping("/add")
    public Result addOrder(@RequestBody Order order) {
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/add", order, Result.class);
        return responseEntity.getBody();
    }

    /**
     * 出现异常也会走断路器
     */
    @HystrixCommand(fallbackMethod = "modifyOrderFailed")
    @PostMapping("/modify")
    public Result modifyOrder() {
        int i = 1 / 0;

        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/modify", null, Result.class);
        return responseEntity.getBody();
    }

    /**
     * 让某些异常不走断路器
     */
    @HystrixCommand(fallbackMethod = "delOrderFailed",
            ignoreExceptions = BusinessException.class)
    @PostMapping("/del")
    public Result delOrder(int id) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new BusinessException(ResultCode.SIGN_ERROR);
        }

        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/del", null, Result.class);
        return responseEntity.getBody();
    }

    @HystrixCommand(fallbackMethod = "changeOrderFailed")
    @PostMapping("/change")
    public Result changeOrder(int id) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new HystrixBadRequestException(e.getMessage(),e);
        }

        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/change", null, Result.class);
        return responseEntity.getBody();
    }


    /**
     * 要求，这个方法的参数列表和返回值，必须和主方法一致。
     * 关于fallback的方法，可以都指定同一个，也可以指定不同的
     * 如果方法参数和返回值都相同，可以指定同一个
     * 但通常不同的方法，参数和返回值总有差别，所以通常都是每个方法指定对应的fallback方法。
     */
    private Result getOrderFailed() {
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    private Result addOrderFailed(Order order, Throwable e) {
        log.error("add order failed:" + e.getMessage());
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    private Result modifyOrderFailed(Throwable e) {
        log.error("modify order failed:" + e.getMessage());
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    private Result delOrderFailed(int id, Throwable e) {
        log.error("del order failed:" + e.getMessage());
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    private Result changeOrderFailed(int id, Throwable e) {
        log.error("del order failed:" + e.getMessage());
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }
}
