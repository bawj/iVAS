package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.dao.DeviceDAO;
import com.yn.electricity.dao.ServiceDAO;
import com.yn.electricity.dao.VideoPlanDAO;
import com.yn.electricity.mapper.CameraMapper;
import com.yn.electricity.mapper.DeviceMapper;
import com.yn.electricity.mapper.ServiceMapper;
import com.yn.electricity.mapper.VideoPlanMapper;
import com.yn.electricity.pas.PasBaseRequest;
import com.yn.electricity.pas.PasDelVideoPlanInfo;
import com.yn.electricity.qto.VideoPlanDTO;
import com.yn.electricity.request.VideoPlanSaveRequest;
import com.yn.electricity.service.VideoPlanService;
import com.yn.electricity.service.pas.PasDeviceService;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.vo.VideoPlanVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * Date 2021/8/2 17:16
 * Description
 **/
@Service
public class VideoPlanServiceImpl extends ServiceImpl<VideoPlanMapper , VideoPlanDAO> implements VideoPlanService {

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private ServiceMapper serviceMapper;
    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private VideoPlanMapper videoPlanMapper;
    @Resource
    private PasDeviceService pasDeviceService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addVideoPlan(List<VideoPlanSaveRequest> videoPlanSaveRequest) {
        if (CollectionUtils.isEmpty(videoPlanSaveRequest)){
            BizBusinessUtils.bizBusinessException("参数错误");
        }
        for (VideoPlanSaveRequest planSaveRequest : videoPlanSaveRequest) {
            saveVideoPlan(planSaveRequest);
        }
        return null;
    }

    private void saveVideoPlan(VideoPlanSaveRequest videoPlanSaveRequest){
        //先删除在保存
        Integer cameraId = videoPlanSaveRequest.getCameraId();
        CameraDAO cameraDAO = cameraMapper.selectById(cameraId);
        if (cameraDAO == null){
            BizBusinessUtils.bizBusinessException("镜头id错误");
        }
        Integer devId = cameraDAO.getDevId();
        DeviceDAO deviceDAO = deviceMapper.selectById(devId);
        if (deviceDAO == null){
            BizBusinessUtils.bizBusinessException("镜头id错误 查询不到设备");
        }
        Integer serviceId = deviceDAO.getServiceId();
        ServiceDAO serviceDAO = serviceMapper.selectById(serviceId);
        if (serviceDAO == null){
            BizBusinessUtils.bizBusinessException("镜头id错误 查询不到服务");
        }
        List<VideoPlanDAO> videoPlanList = videoPlanMapper.selectByCameraId(cameraId);
        if (!CollectionUtils.isEmpty(videoPlanList)){
            List<Integer> collect = videoPlanList.stream().map(VideoPlanDAO::getId).collect(Collectors.toList());
            delVideoPlan(serviceDAO,collect);
        }
        List<VideoPlanSaveRequest.VideoPlanInfo> videoPlanInfoList = videoPlanSaveRequest.getVideoPlanInfoList();
        if (!CollectionUtils.isEmpty(videoPlanInfoList)){
            List<VideoPlanDAO> videoPlanDAOList = new ArrayList<>();
            //保存到数据
            for (VideoPlanSaveRequest.VideoPlanInfo videoPlanInfo : videoPlanInfoList) {
                VideoPlanDAO videoPlan = new VideoPlanDAO();
                videoPlan.setType(videoPlanInfo.getType());
                videoPlan.setCameraId(cameraId);
                videoPlan.setSubType(videoPlanInfo.getSubType());
                videoPlan.setBeginTime(videoPlanInfo.getBeginTime());
                videoPlan.setEndTime(videoPlanInfo.getEndTime());
                videoPlan.setCreateTime(new Date());
                videoPlanDAOList.add(videoPlan);
            }
            if (CollectionUtils.isEmpty(videoPlanDAOList)){
                BizBusinessUtils.bizBusinessException("参数错误");
            }
            saveBatch(videoPlanDAOList);
            //发送到pas
            VideoPlanDTO videoPlanDTO = new VideoPlanDTO();
            videoPlanDTO.setDevId(devId);
            videoPlanDTO.setChannelNo(cameraDAO.getChannelNo());
            videoPlanDTO.setIp(serviceDAO.getIp());
            videoPlanDTO.setPort(serviceDAO.getPort());
            videoPlanDTO.setVideoPlanDAOList(videoPlanDAOList);
            pasDeviceService.sendVideoPlan(videoPlanDTO);
        }
    }

    private void delVideoPlan(ServiceDAO serviceDAO,List<Integer> ids){
        videoPlanMapper.deleteBatchIds(ids);
        pasDeviceService.sendDelVideoPlan(serviceDAO , ids);
    }

    @Override
    public List<VideoPlanVO> findVideoPlan(Integer organizationId) {
        return cameraMapper.selectCameraByOrganizationId(organizationId);
    }

}
