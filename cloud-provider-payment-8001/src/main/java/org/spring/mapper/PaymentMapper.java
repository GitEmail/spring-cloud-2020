package org.spring.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.bean.Payment;

@Mapper
public interface PaymentMapper {

    Payment getPaymentById(@Param("id") Integer id);

    int create(Payment payment);
}
