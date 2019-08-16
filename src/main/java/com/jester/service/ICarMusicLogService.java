package com.jester.service;

/**
 * @author yuxinzh
 * @create 2019/8/12
 */
public interface ICarMusicLogService {

    /**
     * 复制随机N个文件到写死的位置
     * 保存日志，跳过日志中已有的文件
     * @param num 几个文件
     */
    void copyMusic(int num);
}
