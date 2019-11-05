package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.common.pojo.User;
import com.jt.common.vo.SysResult;

import cn.tedu.server.UserServer;

@RestController
@RequestMapping("user/manage")
public class UserController {

	//1.用户名是否存在校验
	@Autowired
	private UserServer userServer;
	@RequestMapping("checkUserName")
    public SysResult checkUserName(String userName){
	   Boolean exist=userServer.checkUserName(userName);
	   System.out.println(exist);
	   if(exist){
		   return SysResult.build(201, "用户已存在",null);
	   }else{
		   return SysResult.ok();
	   }
   }
	
	//2.注册表单提交
	public SysResult saveUser(User user){
		try{
		userServer.saveUser(user);
		return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "注册失败",null);
		}
	}
}
