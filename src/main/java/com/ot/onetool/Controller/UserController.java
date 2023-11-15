package com.ot.onetool.Controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ot.onetool.Json.Result;
import com.ot.onetool.Mapper.UserMapper;
import com.ot.onetool.POJO.UserPO;
import com.ot.onetool.POJO.UserVO;
import com.ot.onetool.Service.IUserService;
import com.ot.onetool.Util.TokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dlr
 * @since 2023-11-12
 */
@RestController
@RequestMapping("/user")
@Api("用户接口管理")

@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final IUserService userService;

    @PostMapping("/register")
    @ApiOperation("新增用户，注册功能")
    public Result<Map> insertUser( @ApiParam(value = "此处要求参数全传",required = true) UserPO userPO){
        boolean save=false;
        Result<Map> result=new Result();
        if (userService.getOne(new LambdaQueryWrapper<UserPO>().eq(UserPO::getUsername,userPO.getUsername()))==null){
            save = userService.save(userPO);
        }

        if (save){
            result.setState(1);
            result.setMsg("保存成功");

            String sign = TokenUtil.sign(userPO);
            Map<Object,Object> data=new HashMap<>();
            data.put("userVO",BeanUtil.toBean(userPO, UserVO.class));
            data.put("token",sign);
            result.setData(data);

        }else {
            result.setState(0);
            result.setMsg("用户已存在，请换个用户名");
        }

        return result;
    }

    @ApiOperation("登录接口管理")
    @PostMapping("/login")
    public Result<Map<Object,Object>> login(@ApiParam(value = "传入账号",required = true) @RequestParam("acc") String account,
                             @ApiParam(value = "传入密码",required = true) @RequestParam("pas") String password){
        UserVO userVO = userService.loginCheck(account, password);
        UserPO userPO = BeanUtil.copyProperties(userVO, UserPO.class);

        Map<Object,Object> map=new HashMap<>();

        Result<Map<Object,Object>> result= new Result<>();
        if(userVO!=null){
            String sign = TokenUtil.sign(userPO);
            result.setMsg("success");
            result.setState(1);
            map.put("token",sign);
            result.setData(map);
        }else {
            result.setMsg("登录失败，请检查用户名或密码");
            result.setState(0);
        }
        return result;
    }
}
