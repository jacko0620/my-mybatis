package com.jester.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuxinzh
 * @create 2019/8/12
 */
@Data
public class CarMusicLogVO implements Serializable {

    private static final long serialVersionUID = -1201043043765102656L;

    private String id;

    private String fileName;

    private Date createTs;
}
