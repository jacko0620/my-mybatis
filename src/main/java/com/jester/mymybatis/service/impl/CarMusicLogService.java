package com.jester.mymybatis.service.impl;

import com.jester.mymybatis.dao.ICarMusicLogDao;
import com.jester.mymybatis.service.ICarMusicLogService;
import com.jester.mymybatis.vo.CarMusicLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author yuxinzh
 * @create 2019/8/12
 */
public class CarMusicLogService implements ICarMusicLogService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ICarMusicLogDao carMusicLogDao;

    @Override
    public void copyMusic(int num) {
        try {
            File destFolder = new File("E:\\carMusicTemp");
            int i = 0;
            int r;
            File file;
            File destFile;
            List<File> fileList = getAllFile("E:\\oneDriveFiles\\OneDrive - JJXY\\music");
            logger.info("list size -> {}",fileList.size());
            CarMusicLogVO logVO;
            while (i < num) {
                r = (int)Math.round(Math.random() * fileList.size());
                file = fileList.get(r);

                String fileName = file.getName();
                logVO = carMusicLogDao.getByName(fileName);
                if (logVO != null) {
                    continue;
                }

                destFile = new File(destFolder,fileName);
                copy(file,destFile);

                logVO = new CarMusicLogVO();
                logVO.setId(UUID.randomUUID().toString().replaceAll("\\-",""));
                logVO.setFileName(fileName);
                logVO.setCreateTs(new Date());
                carMusicLogDao.insert(logVO);

                i ++;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取路径下的所有文件
     * @param directoryPath 需要遍历的文件夹路径
     * @return
     */
    private static List<File> getAllFile(String directoryPath) {
        List<File> list = new ArrayList<>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                list.addAll(getAllFile(file.getAbsolutePath()));
            } else {
                String fileName = file.getName();
                if (fileName.endsWith(".mp3")) {
                    list.add(file);
                }
            }
        }
        return list;
    }

    private static void copy(File file, File destFile) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            byte[] bys = new byte[1024];
            int len = 0;
            while((len=bis.read(bys))!=-1){
                bos.write(bys,0,len);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
