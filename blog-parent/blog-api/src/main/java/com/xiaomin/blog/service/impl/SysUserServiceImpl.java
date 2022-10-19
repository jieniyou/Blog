package com.xiaomin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaomin.blog.dao.mapper.SysUserMapper;
import com.xiaomin.blog.dao.pojo.SysUser;
import com.xiaomin.blog.service.LoginService;
import com.xiaomin.blog.service.SysUserService;
import com.xiaomin.blog.vo.ErrorCode;
import com.xiaomin.blog.vo.LoginUserVo;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 20:18
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private LoginService loginService;
    @Override
    public UserVo findUserVoById(Long id) {

        System.err.println("id==>"+id);
        SysUser sysUser = sysUserMapper.selectById(id);
        System.err.println("sysUser===>"+sysUser);
        if (sysUser == null) {
            sysUser=new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("解你忧");
        }
        UserVo userVo=new UserVo();
        //userVo.setId(sysUser.getId());
        //userVo.setNickname(sysUser.getNickname());
        //userVo.setAvatar(sysUser.getAvatar());

        BeanUtils.copyProperties(sysUser,userVo);
        userVo.setId(String.valueOf(sysUser.getId()));
        return userVo;
    }
    @Override
    public SysUser findUserById(Long id) {

        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            sysUser=new SysUser();
            sysUser.setNickname("解你忧");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        //LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",account);
        queryWrapper.eq("password",password);
        queryWrapper.select("account","id","avatar", "nickname");
        queryWrapper.last("limit 1");

        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1.token合法性校验
         *  是否为空,解析是否成功 redis是否存在
         * 2.如果校验失败 返回错误
         * 3.如果成功,返回对应的结果 LoginUserVo
         */
        SysUser sysUser=loginService.checkToken(token);
        if (sysUser==null){
            Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo=new LoginUserVo();
        loginUserVo.setId(String.valueOf(sysUser.getId()));
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAccount(sysUser.getAccount());
        loginUserVo.setAvatar(sysUser.getAvatar());
        return Result.success(loginUserVo);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",account);
        queryWrapper.last("limit 1");
        return this.sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        //保存用户这id会自动生成
        //这个地方默认生成的id是 分布式id 雪花算法
        //mybatis-plus
        this.sysUserMapper.insert(sysUser);
    }
}
