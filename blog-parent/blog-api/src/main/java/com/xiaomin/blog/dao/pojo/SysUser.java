package com.xiaomin.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysUser {

  //@TableId(type = IdType.ASSIGN_UUID)//Ĭ��id����
  //�Ժ��û�����֮��,Ҫ���зֱ����,id����Ҫ�ֲ�ʽid��
  //@TableId(type = IdType.AUTO) ���ݿ�����
  private Long id;

  private String account;

  private Integer admin;

  private String avatar;

  private Long createDate;

  private Integer deleted;

  private String email;

  private Long lastLogin;

  private String mobilePhoneNumber;

  private String nickname;

  private String password;

  private String salt;

  private String status;
}
