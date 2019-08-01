package demo.jwt.simple.contorller;


import demo.jwt.simple.model.ResponseResult;
import demo.jwt.simple.model.User;
import demo.jwt.simple.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@RequestBody User user){
        System.out.println("12121");
        System.out.println(user);
        System.out.println("jjjjj");
        if(Objects.isNull(user)){
            return ResponseResult.error("server error");
        }
        if("wyy".equals(user.getUsername())){
            // 登陆成功
            String token = jwtUtils.createToken(user);
            user.setToken(token);
            return ResponseResult.ok(user);
        }else{
            return ResponseResult.error("login fail");
        }
    }



    @RequestMapping(value = "profile",method = RequestMethod.GET)
    public ResponseResult parseToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if(StringUtils.isNotBlank(token)){
            Claims claims = jwtUtils.parseToken(token);
            String id = claims.getId();
            String username = (String) claims.get("username");

            User user = new User(id,username);

            return ResponseResult.ok(user);
        }
         return ResponseResult.error("token is null");

    }


}
