package org.example.origin;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShardingBaseTest {

    public static void main(String[] args) throws Exception {
        //1,测试无配置时的api
//        testShardOriginApi();

        //2,测试yaml配置的api
        testYamlApi();
    }

    /**
     * 测试yaml方式配置
     */
    private static void testYamlApi() throws Exception{
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File("/Users/zhaoweihua/Desktop/书籍阅读/工具箱/Docker/Sharding-JDBC源码/sharding-test/src/main/resources/shardingConfig.yml"));
        //1, 添加数据分片
//        String sql = "insert into t_order_item(item_id,user_id,order_id) values (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,6,5),(6,5,6)";
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            boolean executeSuccess = preparedStatement.execute();
//            System.out.println(executeSuccess);
//        }

        //2, 查询数据
        String sql1 = "SELECT item_id, order_id,user_id FROM t_order_item ";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql1)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    System.out.println(rs.getInt("item_id") +"_" + rs.getInt("user_id") +"_" + rs.getInt("order_id"));
                }
            }
        }


    }

    /**
     * 测试无配置的api
     */
    public static void  testShardOriginApi() throws Exception{
        DataSource dateSource = getDateSource();

          //1, 添加数据分片
//        String sql = "insert into t_order(order_id, user_id) values (1,1),(2,2),(3,3),(4,4),(5,6),(6,5)";
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            boolean executeSuccess = preparedStatement.execute();
//            System.out.println(executeSuccess);
//        }

        //2, 查询数据
        String sql1 = "SELECT order_id,user_id FROM t_order ";
        try (
                Connection conn = dateSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql1)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    System.out.println(rs.getInt("order_id") +"_" + rs.getInt("user_id"));
                }
            }
        }
    }

    /**
     * 获取数据源
     */
    public static DataSource getDateSource() throws Exception{
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/shard0?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("Zwh12345");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第二个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3306/shard1?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai");
        dataSource2.setUsername("root");
        dataSource2.setPassword("Zwh12345");
        dataSourceMap.put("ds1", dataSource2);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order","ds${0..1}.t_order${0..1}");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 获取数据源对象
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());

    }


}
