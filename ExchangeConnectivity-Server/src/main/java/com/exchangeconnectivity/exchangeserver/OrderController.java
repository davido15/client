package com.exchangeconnectivity.exchangeserver;

import org.springframework.web.bind.annotation.*;
import com.exchangeconnectivity.exchangeserver.resourceclasses.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@RestController
public class OrderController {

    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange.matraining.com").build();

    @RequestMapping(value = "/api/createorder", method=RequestMethod.POST,produces = "application/json",headers = "Accept=*/*", consumes="application/json")
    @ResponseBody
    public String createOrder(@RequestBody Order newOrder) {
        APIInterfaces.CreateOrderService service = retrofit.create(APIInterfaces.CreateOrderService.class);
        Call<String> req = service.createOrder(newOrder);
        try {
            Response<String> res = req.execute();
            return res.body();
        }catch(java.io.IOException e){}
        catch(java.lang.IllegalArgumentException e){e.getMessage();}
        return "couldn't create order!";
    }

    @RequestMapping(value="/api/getorder", method=RequestMethod.GET, produces = "application/json",headers="Accept=*/*")
    @ResponseBody
    public String getOrderStatus(@RequestParam int ID){
        return String.format("the Order ID is %d",ID);
    }


    @RequestMapping(value = "/api/cancelorder",produces = "application/json", method=RequestMethod.DELETE, headers = "Accept=*/*")
    @ResponseBody
    public String cancelOrder(@RequestParam int ID) {

        return "true";
    }


    @RequestMapping(value = "/api/editorder", method=RequestMethod.PUT, produces = "application/json", consumes = "application/json", headers = "Accept=*/*")
    @ResponseBody
    public String modifyOrder(@RequestParam int ID, @RequestBody Order o) {
        return "true";
    }
}
