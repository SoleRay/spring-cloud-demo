package com.sole.ray.distributed.tx.provider.param;

import java.io.Serializable;

/**
 * (Consumer)实体类
 *
 * @author makejava
 * @since 2021-03-01 11:26:00
 */
public class Consumer implements Serializable {
    private static final long serialVersionUID = 838235454097461482L;
    
    private Integer id;
    
    private String cName;
    
    private Integer age;
    
    private Short ismale;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
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