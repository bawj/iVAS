package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.DecoderDAO;
import com.yn.electricity.vo.DecoderVO;

import java.util.List;

/**
 * @author 解码器相关
 */
public interface DecoderMapper extends BaseMapper<DecoderDAO> {

    /**
     * 根据解码器名称查询是否有重复
     * @param decoderName decoderName
     * @return DecoderDAO
     */
    DecoderDAO selectDecoderByName(String decoderName);

    /**
     * 根据ip端口查询是否有重复
     * @param ip   ip
     * @param port port
     * @return DecoderDAO
     */
    DecoderDAO selectDecoderByIpAndPort(String ip, Integer port);

    /**
     * 根据设备编码查询是否有重复
     * @param decoderCode 编码
     * @return DecoderDAO
     */
    DecoderDAO selectDecoderByCode(String decoderCode);

    /**
     * 根据名称查询不等于当前id的
     * @param decoderId   decoderId
     * @param decoderName decoderName
     * @return DecoderDAO
     */
    DecoderDAO selectDecoderByNameNotId(Integer decoderId, String decoderName);

    /**
     * 根据ip端口查询是否有重复 不等于当前id
     * @param decoderId id
     * @param ip   ip
     * @param port port
     * @return DecoderDAO
     */
    DecoderDAO selectDecoderByIpAndPortNotId(Integer decoderId, String ip, Integer port);

    /**
     * 根据设备编码查询是否有重复 不等于当前id
     * @param decoderId   id
     * @param decoderCode 编码
     * @return DecoderDAO
     */
    DecoderDAO selectDecoderByCodeNotId(Integer decoderId, String decoderCode);

    /**
     * 分页查询
     * @param decoderName decoderName
     * @param deviceTypeId deviceTypeId
     * @param startTime startTime
     * @param endTime endTime
     * @param companyCodeList 权限
     * @param depCodeList     权限
     * @return List
     */
    List<DecoderVO> selectByNameAndDeviceTypeAndTime(String decoderName, Integer deviceTypeId,
                                                     String startTime, String endTime,
                                                     List<String> companyCodeList ,List<String> depCodeList);

}