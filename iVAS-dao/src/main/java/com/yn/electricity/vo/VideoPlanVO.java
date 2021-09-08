package com.yn.electricity.vo;

import com.yn.electricity.dao.VideoPlanDAO;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/8/4 9:25
 * Description
 **/
@Data
public class VideoPlanVO {

    private String ip;

    private String name;

    private Integer cameraId;

    private List<VideoPlanDAO> videoPlanList;


}
