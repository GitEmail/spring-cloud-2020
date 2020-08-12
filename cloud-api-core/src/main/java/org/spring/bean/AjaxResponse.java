package org.spring.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class AjaxResponse<T> implements Serializable {

    private Integer Status;
    private String message;
    private T data;

    public AjaxResponse(Integer status, String message) {
        this(status,message,null);
    }
}
