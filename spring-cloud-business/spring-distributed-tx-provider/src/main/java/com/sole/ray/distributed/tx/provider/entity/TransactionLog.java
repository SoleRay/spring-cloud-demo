package com.sole.ray.distributed.tx.provider.entity;

import java.io.Serializable;

/**
 * (TransactionLog)实体类
 *
 * @author SoleRay
 * @since 2021-03-03 21:38:44
 */
public class TransactionLog implements Serializable {
    private static final long serialVersionUID = -86815849066116492L;
    
    private String id;
    
    private String business;
    
    private Integer foreignKey;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Integer getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(Integer foreignKey) {
        this.foreignKey = foreignKey;
    }

}