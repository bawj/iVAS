package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.DecoderPassagewayDAO;
import com.yn.electricity.vo.PassagewayInfoVO;

import java.util.List;

/**
 * @author 解码器通道
 */
public interface DecoderPassagewayMapper extends BaseMapper<DecoderPassagewayDAO> {


    /**
     * 根据解码器id 查询解码器下的通道
     * @param decoderId id
     * @return List
     */
    List<PassagewayInfoVO> selectByDecoderId(Integer decoderId);

}