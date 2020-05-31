package com.ze.market.form;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
/**
 * @program: market
 * @description: order info
 * @author: Ze QIN
 * @create: 2020-05-30 23:59
 **/
@Data
public class OrderForm {

    @NotEmpty(message="name could not empty")
    private String name;

    @NotEmpty(message="phone number could not empty")
    private String phone;

    @NotEmpty(message="address could not empty")
    private String address;

    @NotEmpty(message="webchat number could not empty")
    private String webchat;

    @NotEmpty(message="items could not empty")
    private String items;

}
