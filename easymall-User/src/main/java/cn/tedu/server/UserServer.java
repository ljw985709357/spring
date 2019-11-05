package cn.tedu.server;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.pojo.User;

import cn.tedu.mapper.UserMapper;
//@Service
//public class UserService {
//	@Autowired
//	private UserMapper userMapper;
//	public Boolean checkUserName(String userName) {
//		int result=userMapper
//				.selectUserCountByUserName(userName);
//		//result不是1就是0
//		return result==1;
//	}
@Service
public class UserServer {
    @Autowired
    private UserMapper userMapper;
	public Boolean checkUserName(String userName) {
		// TODO Auto-generated method stub
		int result=userMapper.queryUserName(userName);
		System.out.println(result);
		return result==1;
	}
	public void saveUser(User user) {
		//封装userid
		String userId=UUID.randomUUID().toString();
		System.out.println(userId);
		user.setUserId(userId);
		//密码加密
		String md5password=DigestUtils.md5Hex(user.getUserPassword());
		System.out.println("密码密文"+md5password);
		user.setUserPassword(md5password);
		userMapper.insertUser(user);
		
		
		
	}
     
}
