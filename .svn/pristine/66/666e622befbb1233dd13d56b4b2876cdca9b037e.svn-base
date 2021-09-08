package com.yn.electricity.service;

import com.github.pagehelper.PageInfo;
import com.yn.electricity.as.PassagewayVO;
import com.yn.electricity.request.DecoderAlterRequest;
import com.yn.electricity.request.DecoderSaveRequest;
import com.yn.electricity.vo.DecoderInfoVO;
import com.yn.electricity.vo.DecoderVO;

import java.util.List;

/**
 * @author 解码器相关
 */
public interface DecoderService {


    /**
     * 获取输出通道
     *
     * @param ip               ip
     * @param port             port
     * @param registerAccount  账号
     * @param registerPassword 密码
     * @return list
     */
    List<PassagewayVO> obtainPassageway(String ip, Integer port, String registerAccount, String registerPassword);

    /**
     * 增加解码器
     *
     * @param decoder decoder
     * @return String
     */
    String addDecoder(DecoderSaveRequest decoder);

    /**
     * 修改解码器
     *
     * @param decoder decoder
     * @return String
     */
    String updateDecoder(DecoderAlterRequest decoder);

    /**
     * 根据id查询单个解码器所有信息
     *
     * @param id id
     * @return DecoderVO
     */
    DecoderInfoVO findDecoderOne(Integer id);

    /**
     * 删除解码器
     *
     * @param ids ids
     * @return String
     */
    String deleteDecoder(List<Integer> ids);

    /**
     * 查询解码器
     *
     * @param decoderName  名称
     * @param deviceTypeId 类型
     * @param startTime    创建时间
     * @param endTime      创建时间结束时间
     * @param pageNum      分页
     * @param pageSize     分页
     * @return DecoderVO
     */
    PageInfo<DecoderVO> findDecoder(String decoderName, Integer deviceTypeId, String startTime, String endTime, Integer pageNum, Integer pageSize);
}
