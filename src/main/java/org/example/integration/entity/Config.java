package org.example.integration.entity;

import lombok.Data;

/**
 * 全剧配置表
 */
@Data
public class Config {
    private Long configId;
    private String paraName;
    private String paraValue;
    private String paraDesc;
}
