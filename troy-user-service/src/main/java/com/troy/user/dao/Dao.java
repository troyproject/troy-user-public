package com.troy.user.dao;


import com.troy.user.dao.model.BaseModel;

/**
 * description 数据 CURD
 *
 * @author Han
 * @date 2018/9/28 11:14
 */
public interface Dao<M extends BaseModel> {

    /**
     * description: 新增
     *
     * @param model 数据
     * @return
     * @throws Exception
     */
    void insert(M model) throws Exception;

    /**
     * description: 删除
     *
     * @param id 主健
     * @return
     * @throws Exception
     */
    int deleteById(Long id) throws Exception;

    /**
     * description: 更新非空字段
     *
     * @param model 数据
     * @return
     * @throws Exception
     */
    int updateForSelectiveById(M model) throws Exception;

    /**
     * description: 安全更新（乐观锁）
     *
     * @param model 数据
     * @return
     * @throws Exception
     */
    int updateForSafeById(M model) throws Exception;

    /**
     * description: 根据 id 查询数据
     *
     * @param id 主健
     * @return
     * @throws Exception
     */
    M selectById(Long id) throws Exception;
}
