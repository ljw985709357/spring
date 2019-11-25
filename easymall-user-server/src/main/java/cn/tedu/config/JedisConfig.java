//package cn.tedu.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisShardInfo;
//import redis.clients.jedis.ShardedJedisPool;
//
//@Configuration
//@ConfigurationProperties(prefix="easymall.jedis")
//public class JedisConfig {
//    private List<String> nodes;
//    private Integer maxTotal;
//    private Integer maxIdle;
//    private Integer minIdle;
//    
//    //初始化方法，实现shardedJedisPool的创建
//    @Bean
//    public ShardedJedisPool initJedisSPool(){
//    	//收集节点信息
//    	List<JedisShardInfo> info=new ArrayList<JedisShardInfo>();
//    	for(String node:nodes){
//    		String host=node.split(":")[0];
//    		int port=Integer.parseInt(node.split(":")[1]);
//    		info.add(new JedisShardInfo(host, port));
//    	}
//    	//连接池的属性对象
//    	JedisPoolConfig config=new JedisPoolConfig();
//    	config.setMaxTotal(maxTotal);
//    	config.setMaxIdle(maxIdle);
//    	config.setMinIdle(minIdle);
//    	return new ShardedJedisPool(config, info);
//    }
//	public List<String> getNodes() {
//		return nodes;
//	}
//	public void setNodes(List<String> nodes) {
//		this.nodes = nodes;
//	}
//	public Integer getMaxTotal() {
//		return maxTotal;
//	}
//	public void setMaxTotal(Integer maxTotal) {
//		this.maxTotal = maxTotal;
//	}
//	public Integer getMaxIdle() {
//		return maxIdle;
//	}
//	public void setMaxIdle(Integer maxIdle) {
//		this.maxIdle = maxIdle;
//	}
//	public Integer getMinIdle() {
//		return minIdle;
//	}
//	public void setMinIdle(Integer minIdle) {
//		this.minIdle = minIdle;
//	}
//    
//    
//}
