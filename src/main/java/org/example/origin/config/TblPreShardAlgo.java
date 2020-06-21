package org.example.origin.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 等值查询使用的分片算法，包括in
 */
public class TblPreShardAlgo implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingColumn) {
        // 不分表
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-availableTargetNames:" + availableTargetNames);
        Integer value = shardingColumn.getValue();
        return "t_order"+value%2;
//        for (String tbname : availableTargetNames) {
//            return tbname;
//        }
//        throw new IllegalArgumentException();
    }
}