package cn.tedu.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;


public class JedisTest {
   @Test
	public void test1(){
		Jedis jedis=new Jedis("10.42.83.122",8000);
		
		jedis.set("name", "wang");
		System.out.println(jedis.get("name"));
	}
}
