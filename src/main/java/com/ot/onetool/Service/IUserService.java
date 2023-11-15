package com.ot.onetool.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ot.onetool.POJO.UserPO;
import com.ot.onetool.POJO.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlr
 * @since 2023-11-12
 */
public interface IUserService extends IService<UserPO> {

    UserVO loginCheck(String account,String password);
}
