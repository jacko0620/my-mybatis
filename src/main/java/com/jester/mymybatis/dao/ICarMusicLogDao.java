package com.jester.mymybatis.dao;

import com.jester.mymybatis.vo.CarMusicLogVO;
import org.springframework.stereotype.Repository;

/**
 * @author yuxinzh
 * @create 2019/8/12
 */
@Repository
public interface ICarMusicLogDao {

    /**
     * 根据名称查询对象
     * @param fileName
     * @return
     */
    CarMusicLogVO getByName(String fileName);

    /**
     * 新增
     * @param carMusicLogVO
     */
    void insert(CarMusicLogVO carMusicLogVO);
}
