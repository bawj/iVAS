package com.yn.electricity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.qto.DeviceQueryDTO;
import com.yn.electricity.request.DeviceAlterRequest;
import com.yn.electricity.request.DeviceSaveRequest;
import com.yn.electricity.vo.DeviceResultVO;
import com.yn.electricity.vo.DeviceVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 设备相关
 */
public interface DeviceService extends IService<DeviceDAO> {

    /**
     * 添加设备
     * @param deviceSaveRequest deviceSaveRequest
     * @return String
     */
    String addDevice(DeviceSaveRequest deviceSaveRequest);

    /**
     * 修改设备
     * @param deviceAlterRequest deviceAlterRequest
     * @return String
     */
    String alterDevice(DeviceAlterRequest deviceAlterRequest);

    /**
     * 删除设备
     * @param id id
     * @return string
     */
    String deleteDevice(List<Integer> id);


    /**
     * 查询设备信息列表
     * @param deviceQuery deviceQuery
     * @return List
     */
    List<DeviceVO> findDevice(DeviceQueryDTO deviceQuery);

    /**
     * excel 导入
     * @param excelFile 文件
     * @return string
     */
    String importDevice(MultipartFile excelFile);

    /**
     * 根据镜头分组查设备
     * @param id
     * @return
     */
    List<DeviceResultVO> selectByCameraGroupId(Integer id);

}
