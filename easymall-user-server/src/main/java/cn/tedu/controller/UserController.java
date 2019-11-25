package cn.tedu.controller;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.service.UserService;

import com.jt.common.pojo.User;
import com.jt.common.utils.CookieUtils;
import com.jt.common.vo.SysResult;
/**
 * 功能：1.用户名校验
 *      2.表单提交
 * @author 98570
 *
 */
@RestController
@RequestMapping("user/manage")
public class UserController {
   @Autowired
   private UserService userService;
   
   //1.注册
   @RequestMapping("checkUserName")
   public SysResult checkName(String userName){
	   Boolean exist=userService.checkNameByUserName(userName);
	   if(exist){
		   return SysResult.build(201, "用户名已存在", null);
	   }else{
		   return SysResult.ok();
	   }
   }
   
   //表单提交
   @RequestMapping("save")
   public SysResult saveList(User user){
	   try{
		   userService.saveUser(user);
		   return SysResult.ok();
	   }catch(Exception e){
		   e.printStackTrace();
		   return SysResult.build(201, "注册失败", null);
	   }
   }
   //2.登录
   @RequestMapping("login")
   public SysResult doLogin(User user,HttpServletRequest request,HttpServletResponse response){
	   String ticket=userService.doLogin(user);
//	   if(ticket==null||"".equals(ticket))
	   if(StringUtils.isNotEmpty(ticket)){
		   CookieUtils.setCookie(request, response, "EM_TICKET", ticket);
		   return SysResult.ok();
	   }else{
		   return SysResult.build(201, "登录失败", null);
	   }
   }
   
   //获取登录状态
   @RequestMapping("/query/{ticket}")
   public SysResult checkLoginUserDate(@PathVariable String ticket){
	   String userJson=userService.checkLoginUserDate(ticket);
	   if(StringUtils.isNotEmpty(userJson)){
		   return SysResult.build(200, "登录正常", userJson);
	   }else{
		   return SysResult.build(201, "登录失效", null);
	   }
   }
}
