package com.yn.electricity.qto;

import com.yn.electricity.dao.VideoPlanDAO;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * Date 2021/8/3 14:56
 * Description
 **/
@Data
public class VideoPlanDTO {

    private Integer devId;

    private Integer channelNo;

    private String ip;

    private Integer port;

    private List<VideoPlanDAO> videoPlanDAOList;

}
