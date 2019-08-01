package demo.jwt.simple.model;


import lombok.Data;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class ResponseResult  implements Serializable {

    private String code;
    private HttpStatus status;
    private Object data;


    public static ResponseResult error(Object data){
        ResponseResult result = new ResponseResult();
        result.setCode("500");
        result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        result.setData(data);
        return result;

    }

    public static ResponseResult ok(Object data){
        ResponseResult result = new ResponseResult();
        result.setCode("200");
        result.setStatus(HttpStatus.OK);
        result.setData(data);
        return result;

    }

}
