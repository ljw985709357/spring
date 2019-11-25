package cn.tedu.service;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import cn.tedu.mapper.UserMapper;

import com.jt.common.pojo.User;
import com.jt.common.utils.MapperUtil;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
	public Boolean checkNameByUserName(String userName) {
	   int result =userMapper.selectCountByUserName(userName);
	   return result==1;
	}
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		String userId=UUID.randomUUID().toString();
		user.setUserId(userId);
		
		String md5Pssword=DigestUtils.md2Hex(user.getUserPassword());
		System.out.println("密码密文"+md5Pssword);
		user.setUserPassword(md5Pssword);
		
		userMapper.insertUser(user);
	}
	//@Autowired(required=false)
	@Autowired
	private JedisCluster jedis;
	//private ShardedJedisPool pool;
	public String doLogin(User user) {
		//1.user的校验
		user.setUserPassword(DigestUtils.md2Hex(user.getUserPassword()));
		User exist=userMapper.selectUserByUserNameAndPassword(user);
		if(exist==null){
			return "";
		}else{
			//引入顶替的逻辑
		  try{
			 String userLoginIdent="user_login"+exist.getUserId();
			 String ticket="EM_TICKET"+System.currentTimeMillis()+exist.getUserId();
			 String userJson=MapperUtil.MP.writeValueAsString(exist);
			    
				if(jedis.exists(userLoginIdent)){
					jedis.del(jedis.get(userLoginIdent));	
				}
				jedis.setex(userLoginIdent, 60*60*2, ticket);
				jedis.setex(ticket,60*60*2,userJson);
				return ticket;
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
		}
			}
	public String checkLoginUserDate(String ticket) {
	     //加入剩余时间续约的判断
		Long leftTime=jedis.pttl(ticket);
		//判断是否小于30分钟
		if(leftTime<1000*60*30){
			Long expireTime=leftTime+60*60*1000;
			jedis.pexpire(ticket, expireTime);
		}
		return jedis.get(ticket);
	}
	
}









//finally{
//			//jedis.close();
//			if(jedis!=null){
//				pool.returnResource(jedis);
//			}
		//}





////TODO Auto-generated method stub
//user.setUserPassword(DigestUtils.md2Hex(user.getUserPassword()));
//User exist=userMapper.selectUserByUserNameAndPassword(user);
//String ticket="";
//if(exist==null){
//	return ticket;
//}else{
//	ticket="EM_TICKET"+System.currentTimeMillis()+exist.getUserId();
//	//Jedis jedis=new Jedis("192.168.23.128", 6379);
//	//ShardedJedis jedis = pool.getResource();
//	
//	try{
//		String userJson=MapperUtil.MP.writeValueAsString(exist);
//		jedis.setex(ticket, 60*60*2, userJson);
//		return ticket;
//	}catch(Exception e){
//		e.printStackTrace();
//		return "";
//	}
////	finally{
////		//jedis.close();
////		if(jedis!=null){
////			pool.returnResource(jedis);
////		}
////	}
//}
//
//}


// TODO Auto-generated method stub
//Jedis jedis=new Jedis();
//ShardedJedis jedis = pool.getResource();
//try{
//	
//	String userJson=jedis.get(ticket);
//	return userJson;
//}catch(Exception e){
//	e.printStackTrace();
//	return "";
//}