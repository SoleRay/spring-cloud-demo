package com.sole.ray.distributed.tx.provider.dao;

import com.sole.ray.distributed.tx.provider.entity.TransactionLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TransactionLog)表数据库访问层
 *
 * @author SoleRay
 * @since 2021-03-03 21:38:44
 */
public interface TransactionLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TransactionLog queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TransactionLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param transactionLog 实例对象
     * @return 对象列表
     */
    List<TransactionLog> queryAll(TransactionLog transactionLog);

    /**
     * 新增数据
     *
     * @param transactionLog 实例对象
     * @return 影响行数
     */
    int insert(TransactionLog transactionLog);

    /**
     * 修改数据
     *
     * @param transactionLog 实例对象
     * @return 影响行数
     */
    int update(TransactionLog transactionLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    int selectById(String id);

}