package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.request.TvWallAlterRequest;
import com.yn.electricity.request.TvWallSaveRequest;
import com.yn.electricity.vo.TvWallResource;
import com.yn.electricity.vo.TvWallVO;

import java.util.List;

/**
 * @author 电视墙管理
 */
public interface TvWallService {

    /**
     * 添加电视墙
     * @param tvWallSaveRequest 数据信息
     * @return string
     */
    String addTvWall(TvWallSaveRequest tvWallSaveRequest);

    /**
     * 修改电视墙配置
     * @param tvWallAlterRequest 数据信息
     * @return string
     */
    String alterTvWall(TvWallAlterRequest tvWallAlterRequest);

    /**
     * 删除电视墙配置
     * @param ids ids
     * @return Integer
     */
    Integer deleteTvWall(List<Integer> ids);

    /**
     * 查询电视墙配置信息
     * @param pageNum   分页
     * @param pageSize  分页
     * @param name      姓名
     * @param status    状态
     * @return PageInfo
     */
    PageInfo<TvWallVO> findTvWall(Integer pageNum, Integer pageSize, String name, Boolean status);

    /**
     * 解码器上墙
     * @param decoderId 解码器id
     * @param devId 设备id
     * @param channelNo 通道号
     * @return string
     */
    String videoWall(Integer decoderId, Integer devId, Integer channelNo);

}
