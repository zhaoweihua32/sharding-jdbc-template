package org.example.origin.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 范围查询所使用的分片算法
 */
public class TblRangeShardAlgo implements RangeShardingAlgorithm<Integer> {
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Integer> rangeShardingValue) {
        System.out.println("范围-*-*-*-*-*-*-*-*-*-*-*---------------"+availableTargetNames);
        System.out.println("范围-*-*-*-*-*-*-*-*-*-*-*---------------"+rangeShardingValue);
        Collection<String> collect = new LinkedHashSet<>();
        Range<Integer> valueRange = rangeShardingValue.getValueRange();
        for (Integer i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            // 不分表
            for (String each : availableTargetNames) {
                collect.add(each);
/*                if (each.endsWith(i % availableTargetNames.size() + "")) {
                    collect.add(each);
                }*/
            }
        }
        return collect;
    }

}