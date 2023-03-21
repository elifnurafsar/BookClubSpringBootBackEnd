package com.BookClub.BookClub.Services.Util;

import com.BookClub.BookClub.DTO.Util.OrderDTO;
import com.BookClub.BookClub.Entities.User.User;
import com.BookClub.BookClub.Entities.Util.Location;
import com.BookClub.BookClub.Entities.Util.Order;
import com.BookClub.BookClub.Repositories.User.UserRepository;
import com.BookClub.BookClub.Repositories.Util.LocationRepository;
import com.BookClub.BookClub.Repositories.Util.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final LocationRepository locationRepository;
    private final UserRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository _orderRepository, LocationRepository _locationRepository, UserRepository _customerRepository) {
        this.orderRepository = _orderRepository;
        this.locationRepository=_locationRepository;
        this.customerRepository = _customerRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO){
        Optional<String> username = Optional.ofNullable(orderDTO.getUsername());
        Optional<Long> location = Optional.ofNullable(orderDTO.getAreacode());
        if(location.isPresent() && username.isPresent()){
            Optional<Location> _location = locationRepository.findById(location.get());
            Optional<User> _customer = customerRepository.getByEmail(username.get());
            if(_location.isPresent() && _customer.isPresent()){
                int a = orderRepository.insertOrder(orderDTO);
                if(a>0){return orderDTO;}
                else return null;
            }
        }
        return null;
    }

    public Order getOrderByID(UUID id){
        Order a = orderRepository.findById(id).orElse(null);
        return a;
    }

    public List<Order> getOrderByCustomerEMail(String e_mail){
        Optional<User> _customer = customerRepository.getByEmail(e_mail);
        if(_customer.isPresent()){
            List<Order> customer_orders = orderRepository.getOrderByCustomerName(e_mail);
            return customer_orders;
        }
        return null;
    }

    public List<Order> getAllOrders(){
        return  orderRepository.getOrdersByTime();
    }

    public List<Order> getOrdersAtSpecifiedLocation(long area_code) throws Exception{
        Optional<Location> location = locationRepository.findById(area_code);
        if(location.isPresent()){
            List<Order> orders_at_specified_location = orderRepository.getOrdersAtSpecifiedLocationByTime(area_code);
            if(orders_at_specified_location.size() >= 1 ){
                return orders_at_specified_location;
            }
        }
        throw new Exception("We cannot find any order requested from this location");
    }

    public boolean deleteOrderById(UUID id) throws Exception{
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            Order _order = order.get();
            if(!_order.isCheckval())
                return false;

            //else
            orderRepository.deleteById(id);
            if(orderRepository.findById(id).isEmpty())
                return true;
        }
        //cannot find the specified order
        throw new Exception("Cannot find the specified order with this id!");
    }

    public Order setCheck(UUID id, boolean b){
        //take current local admin's location info and compare it with this order's location info
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            Order _order = order.get();
            long area_code = _order.getAreacode();
            _order.setCheckval(b);
            orderRepository.save(_order);
            return _order;
        }
        return null;
    }


}
