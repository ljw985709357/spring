package cn.tedu.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class CLusterTest {
   @Test
   public void clusterTest(){
	   //收集几点信息
	   Set<HostAndPort> set=new HashSet<HostAndPort>();
	   set.add(new HostAndPort("10.42.83.122", 8000));
	   //底层创建需要的连接池属性]
	   JedisPoolConfig config=new JedisPoolConfig();
	   config.setMaxTotal(200);
	   config.setMaxIdle(8);
	   config.setMinIdle(3);
	   //构造JedisCluster
	   JedisCluster cluster=new JedisCluster(set,config);
	   cluster.set("name", "王老师");
	   
   }
}
