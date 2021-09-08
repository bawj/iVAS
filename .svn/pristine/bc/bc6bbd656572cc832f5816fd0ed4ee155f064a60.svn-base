package com.yn.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yn.electricity.dao.DecoderPassagewayCameraDAO;
import com.yn.electricity.vo.DecoderPassagewayCameraVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 解码器和镜头的中间表
 */
public interface DecoderPassagewayCameraMapper extends BaseMapper<DecoderPassagewayCameraDAO> {


    /**
     * 批量保存
     * @param decoderPassagewayCameraList list
     * @return Integer
     */
    Integer insertList(@Param("decoderPassagewayCameraList") List<DecoderPassagewayCameraDAO> decoderPassagewayCameraList);

    /**
     * 查询镜头id
     * @param passagewayId passagewayId
     * @return List
     */
    List<DecoderPassagewayCameraVO> selectByPassagewayListId(Integer passagewayId);

}