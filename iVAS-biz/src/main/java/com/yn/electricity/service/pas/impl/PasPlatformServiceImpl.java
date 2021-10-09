package com.yn.electricity.service.pas.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yn.electricity.ResultVO;
import com.yn.electricity.as.PasAddPlatformVO;
import com.yn.electricity.as.PasPlatformDelete;
import com.yn.electricity.as.PlatformVO;
import com.yn.electricity.dao.PlatformCameraGroupDAO;
import com.yn.electricity.dao.PlatformDAO;
import com.yn.electricity.dao.PlatformVideoInDAO;
import com.yn.electricity.dao.ServiceDAO;
import com.yn.electricity.enums.ResultEnum;
import com.yn.electricity.mapper.PlatformCameraGroupMapper;
import com.yn.electricity.mapper.PlatformMapper;
import com.yn.electricity.mapper.PlatformVideoInMapper;
import com.yn.electricity.mapper.ServiceMapper;
import com.yn.electricity.pas.DeletePlatform;
import com.yn.electricity.service.pas.PasDeviceServiceFeignClient;
import com.yn.electricity.service.pas.PasPlatformService;
import com.yn.electricity.utils.BizBusinessUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author admin
 * Date 2021/9/3 14:21
 * Description
 **/
@Service
public class PasPlatformServiceImpl implements PasPlatformService {

    @Resource
    private PlatformMapper platformMapper;
    @Resource
    private PlatformVideoInMapper platformVideoInMapper;
    @Resource
    private PlatformCameraGroupMapper platformCameraGroupMapper;
    @Resource
    private ServiceMapper serviceMapper;
    @Resource
    private PasDeviceServiceFeignClient pasDeviceServiceFeignClient;

    @Override
    public Integer sendAddPlatformPas(List<PlatformDAO> platformList) {

        List<PlatformVO> platformVOList = new ArrayList<>();

        Map<Integer, List<PlatformDAO>> collect = platformList.stream().collect(Collectors.groupingBy(PlatformDAO::getServiceId));
        collect.forEach((serviceId, platformDAOList) -> {
            ServiceDAO serviceDAO = serviceMapper.selectById(serviceId);
            String ip = serviceDAO.getIp();
            Integer port = serviceDAO.getPort();
            PlatformVO platformVO = new PlatformVO();
            platformVO.setIp(ip);
            platformVO.setPort(port);
            platformVO.setServiceId(String.valueOf(serviceId));

            List<PasAddPlatformVO> pasAddPlatformList = new ArrayList<>();
            for (PlatformDAO platformDAO : platformDAOList) {
                PasAddPlatformVO pasAddPlatformVO = new PasAddPlatformVO();
                pasAddPlatformVO.setServiceId(String.valueOf(serviceId));
                pasAddPlatformVO.setPlatformId(String.valueOf(platformDAO.getId()));
                pasAddPlatformVO.setPlatformName(platformDAO.getName());
                String vendor = serviceDAO.getVendor();
                pasAddPlatformVO.setPlatformType(vendor);
                pasAddPlatformVO.setIp(platformDAO.getIp());
                pasAddPlatformVO.setPort(platformDAO.getPort());
                pasAddPlatformVO.setUsername(platformDAO.getRegisterAccount());
                pasAddPlatformVO.setPassword(platformDAO.getRegisterPassword());
                pasAddPlatformVO.setVendor("");
                pasAddPlatformVO.setDomainId(platformDAO.getCode());
                pasAddPlatformList.add(pasAddPlatformVO);
            }

            platformVO.setPlatformList(pasAddPlatformList);
            platformVOList.add(platformVO);
        });
        ResultVO<String> one = pasDeviceServiceFeignClient.createOne(platformVOList);
        if (one == null || !one.getCode().equals(ResultEnum.SUCCESS.getCode())){
            BizBusinessUtils.bizBusinessException("pas 添加失败");
        }
        return null;
    }

    @Override
    public void sendDelPlatformService(List<Integer> ids) {
        //删除 platform_camera_group
        QueryWrapper<PlatformCameraGroupDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("platform_id" , ids);
        platformCameraGroupMapper.delete(queryWrapper);

        //删除 platform_video_in信息
        QueryWrapper<PlatformVideoInDAO> videoInQueryWrapper = new QueryWrapper<>();
        videoInQueryWrapper.in("platform_id" , ids);
        platformVideoInMapper.delete(videoInQueryWrapper);


        List<PasPlatformDelete> pasPlatformDeleteList = new ArrayList<>();

        List<PlatformDAO> platformDAOList = platformMapper.selectBatchIds(ids);
        Map<Integer, List<PlatformDAO>> collect = platformDAOList.stream().collect(Collectors.groupingBy(PlatformDAO::getServiceId));
        collect.forEach((serviceId, platform) -> {
            ServiceDAO serviceDAO = serviceMapper.selectById(serviceId);
            String ip = serviceDAO.getIp();
            Integer port = serviceDAO.getPort();

            PasPlatformDelete pasPlatformDelete = new PasPlatformDelete();
            pasPlatformDelete.setIp(ip);
            pasPlatformDelete.setPort(port);
            List<DeletePlatform> info = new ArrayList<>();
            for (PlatformDAO platformDAO : platform) {
                DeletePlatform deletePlatform = new DeletePlatform();
                deletePlatform.setPlatformId(platformDAO.getId().toString());
                info.add(deletePlatform);
            }
            pasPlatformDelete.setInfo(info);
            pasPlatformDeleteList.add(pasPlatformDelete);
        });

        ResultVO<String> resultVO = pasDeviceServiceFeignClient.deleteOne(pasPlatformDeleteList);
        if (resultVO == null || !resultVO.getCode().equals(ResultEnum.SUCCESS.getCode())){
            BizBusinessUtils.bizBusinessException("pas删除失败");
        }
    }

}
