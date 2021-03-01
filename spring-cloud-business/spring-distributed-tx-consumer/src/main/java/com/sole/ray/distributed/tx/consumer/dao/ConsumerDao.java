package com.sole.ray.distributed.tx.consumer.dao;

import com.sole.ray.distributed.tx.consumer.entity.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Consumer)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-01 11:26:00
 */
@Mapper
public interface ConsumerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Consumer queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Consumer> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param consumer 实例对象
     * @return 对象列表
     */
    List<Consumer> queryAll(Consumer consumer);

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 影响行数
     */
    int insert(Consumer consumer);

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 影响行数
     */
    int update(Consumer consumer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}