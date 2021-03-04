package com.sole.ray.distributed.tx.consumer.param;

import java.io.Serializable;

/**
 * (Provider)实体类
 *
 * @author SoleRay
 * @since 2021-03-01 11:35:42
 */
public class Provider implements Serializable {
    private static final long serialVersionUID = 981538246443136733L;
    
    private Integer id;
    
    private String pName;
    
    private Integer age;
    
    private Short ismale;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Short getIsmale() {
        return ismale;
    }

    public void setIsmale(Short ismale) {
        this.ismale = ismale;
    }

}