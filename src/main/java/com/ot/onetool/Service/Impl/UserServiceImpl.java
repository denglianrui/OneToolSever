package com.ot.onetool.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ot.onetool.Mapper.UserMapper;
import com.ot.onetool.POJO.UserPO;
import com.ot.onetool.POJO.UserVO;
import com.ot.onetool.Service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dlr
 * @since 2023-11-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {

    @Override
    public UserVO loginCheck(String account, String password) {
        LambdaQueryWrapper<UserPO> lambdaQueryWrapper=new LambdaQueryWrapper<UserPO>().eq(UserPO::getUsername,account).eq(UserPO::getPassword,password);
        UserPO one = this.getOne(lambdaQueryWrapper);

        return BeanUtil.copyProperties(one, UserVO.class);
    }
}
