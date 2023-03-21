package com.BookClub.BookClub.Controllers.Util;

import com.BookClub.BookClub.DTO.Util.OrderDTO;
import com.BookClub.BookClub.Entities.Util.Order;
import com.BookClub.BookClub.Exceptions.ExceptionResponse;
import com.BookClub.BookClub.Services.Util.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService _orderService) {
        this.orderService = _orderService;
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Object getOrderById(@PathVariable("id") UUID id){
        Optional<Order> o = Optional.ofNullable(orderService.getOrderByID(id));
        if(o.isEmpty()){
            return new ExceptionResponse("No element found with this id");
        }
        return o;
    }

    @GetMapping("/get-by-e_mail/{e_mail}")
    public List<Order> getOrdersByCustomerId(@PathVariable("e_mail") String e_mail){
        return orderService.getOrderByCustomerEMail(e_mail);
    }

    @GetMapping("/get-by-code/{areacode}")
    public List<Order> getOrdersAtSpecifiedLocation(@PathVariable("areacode") long area_code) throws Exception{
        return orderService.getOrdersAtSpecifiedLocation(area_code);
    }

    @PostMapping
    public Object createOrder(@RequestBody OrderDTO new_order){
        OrderDTO x = orderService.createOrder(new_order);
        if(x==null){
            return new ExceptionResponse("Cannot create order with these credentials.");
        }else return x;
    }

    @DeleteMapping("/{id}")
    public Object DeleteOrder(@PathVariable("id") UUID id){
        try {
            boolean isDeleted = orderService.deleteOrderById(id);
            if(isDeleted){
                return true;
            }
            else{
                return new ResponseEntity<>("Cannot delete an order which is not submitted!", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ExceptionResponse(e.getMessage());
        }
    }

    @PutMapping("/set-check/{id}")
    public Object checkOrder(@PathVariable("id") UUID id, @RequestBody boolean b){
        Order res = orderService.setCheck(id, b);
        if(res != null)
            return res;
        else
            return new ExceptionResponse("Cannot find order with this id");
    }
}
