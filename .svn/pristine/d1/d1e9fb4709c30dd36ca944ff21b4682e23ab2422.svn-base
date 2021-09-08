package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yn.electricity.ResultVO;
import com.yn.electricity.as.PassagewayVO;
import com.yn.electricity.dao.DecoderDAO;
import com.yn.electricity.dao.DecoderPassagewayCameraDAO;
import com.yn.electricity.dao.DecoderPassagewayDAO;
import com.yn.electricity.dao.DeviceTypeDAO;
import com.yn.electricity.enums.ResultEnum;
import com.yn.electricity.mapper.DecoderMapper;
import com.yn.electricity.mapper.DecoderPassagewayCameraMapper;
import com.yn.electricity.mapper.DecoderPassagewayMapper;
import com.yn.electricity.mapper.DeviceTypeMapper;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.PassagewayDTO;
import com.yn.electricity.request.DecoderAlterRequest;
import com.yn.electricity.request.DecoderSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.DecoderService;
import com.yn.electricity.service.pas.PasDeviceServiceFeignClient;
import com.yn.electricity.util.DataPermissionUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.vo.DecoderInfoVO;
import com.yn.electricity.vo.DecoderPassagewayCameraVO;
import com.yn.electricity.vo.DecoderVO;
import com.yn.electricity.vo.PassagewayInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * Date 2021/6/15 16:09
 * Description
 **/
@Service
public class DecoderServiceImpl implements DecoderService {

    @Resource
    private DeviceTypeMapper deviceTypeMapper;
    @Resource
    private DecoderMapper decoderMapper;
    @Resource
    private DecoderPassagewayMapper decoderPassagewayMapper;
    @Resource
    private DecoderPassagewayCameraMapper decoderPassagewayCameraMapper;
    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private PasDeviceServiceFeignClient pasDeviceServiceFeignClient;

    @Override
    public List<PassagewayVO> obtainPassageway(String ip, Integer port, String registerAccount, String registerPassword) {
        ResultVO<List<PassagewayVO>> obtainPassageway = pasDeviceServiceFeignClient.findObtainPassageway(ip, port, registerAccount, registerPassword);
        if (obtainPassageway.getCode().equals(ResultEnum.SUCCESS.getCode())){
            return obtainPassageway.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public PageInfo<DecoderVO> findDecoder(String decoderName, Integer deviceTypeId, String startTime, String endTime,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum , pageSize);
        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();
        List<String> companyCodeList = dataPermission.getCompanyCodeList();
        List<String> depCodeList = dataPermission.getDepCodeList();
        List<DecoderVO> decoderList = decoderMapper.selectByNameAndDeviceTypeAndTime(decoderName , deviceTypeId , startTime , endTime,companyCodeList,depCodeList);
        return new PageInfo<>(decoderList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addDecoder(DecoderSaveRequest decoder) {
        String decoderName = decoder.getDecoderName();
        DecoderDAO decoderDAO = decoderMapper.selectDecoderByName(decoderName);
        if (decoderDAO != null){
            BizBusinessUtils.bizBusinessException("解码器名称重复");
        }
        String ip = decoder.getIp();
        Integer port = decoder.getPort();
        decoderDAO = decoderMapper.selectDecoderByIpAndPort(ip, port);
        if (decoderDAO != null){
            BizBusinessUtils.bizBusinessException("ip和端口有重复");
        }
        String decoderCode = decoder.getDecoderCode();
        decoderDAO = decoderMapper.selectDecoderByCode(decoderCode);
        if (decoderDAO != null){
            BizBusinessUtils.bizBusinessException("统一编码有重复");
        }

        decoderDAO = new DecoderDAO();
        decoderDAO.setName(decoderName);
        decoderDAO.setIp(ip);
        decoderDAO.setPort(port);
        decoderDAO.setDeviceTypeId(decoder.getDeviceTypeId());
        decoderDAO.setRegisterAccount(decoder.getRegisterAccount());
        decoderDAO.setRegisterPassword(decoder.getRegisterPassword());
        decoderDAO.setDecoderCode(decoderCode);

        UserResult userResult = redisInfoUtil.getUserResult();
        decoderDAO.setCompanyCode(userResult.getCompanyCode());
        decoderDAO.setDepartmentCode(userResult.getDepParentCode());
        decoderDAO.setCreateTime(new Date());
        decoderDAO.setUpdateTime(new Date());
        //保存解码器信息
        decoderMapper.insert(decoderDAO);
        //保存decoder_passageway
        Integer id = decoderDAO.getId();
        List<PassagewayDTO> passagewayList = decoder.getPassagewayList();
        savePassageway(id,passagewayList);
        return null;
    }

    private void savePassageway(Integer decoderId,List<PassagewayDTO> passagewayList){
        List<Integer> cameraIdList = new ArrayList<>();
        passagewayList.forEach(passageway -> {
            Integer tvWallId = passageway.getTvWallId();
            if (tvWallId == null){
                BizBusinessUtils.bizBusinessException("请选择电视墙配置");
            }
            String passagewayName = passageway.getPassagewayName();
            if (StringUtils.isEmpty(passagewayName)){
                BizBusinessUtils.bizBusinessException("请选择解码器输出通道");
            }
            List<DecoderPassagewayCameraVO> cameraIds = passageway.getCameraIdList();
            if (CollectionUtils.isEmpty(cameraIds)){
                BizBusinessUtils.bizBusinessException("请关联镜头");
            }
            //保存解码器通道
            DecoderPassagewayDAO passagewayDAO = new DecoderPassagewayDAO();
            passagewayDAO.setDecoderId(decoderId);
            passagewayDAO.setTvWallId(tvWallId);
            passagewayDAO.setStatus(passageway.getStatus());
            passagewayDAO.setChannelNo(passageway.getChannelNo());
            passagewayDAO.setName(passagewayName);
            passagewayDAO.setCreateTime(new Date());
            passagewayDAO.setUpdateTime(new Date());
            decoderPassagewayMapper.insert(passagewayDAO);
            //保存镜头和解码器中间表
            Integer passagewayId = passagewayDAO.getId();
            List<DecoderPassagewayCameraDAO> decoderPassagewayCameraList = new ArrayList<>();
            cameraIds.forEach(camera -> {
                Integer cameraId = camera.getCameraId();
                DecoderPassagewayCameraDAO decoderPassagewayCamera = new DecoderPassagewayCameraDAO();
                decoderPassagewayCamera.setDecoderPassagewayId(passagewayId);
                decoderPassagewayCamera.setCameraId(cameraId);
                decoderPassagewayCameraList.add(decoderPassagewayCamera);

                //判断镜头id是否有重复
                if (cameraIdList.contains(cameraId)){
                    BizBusinessUtils.bizBusinessException("有镜头重复");
                }else {
                    cameraIdList.add(cameraId);
                }
            });
            decoderPassagewayCameraMapper.insertList(decoderPassagewayCameraList);
        });
    }


    @Override
    public String updateDecoder(DecoderAlterRequest decoder) {
        Integer decoderId = decoder.getId();
        String decoderName = decoder.getDecoderName();
        DecoderDAO decoderDAO = decoderMapper.selectDecoderByNameNotId(decoderId,decoderName);
        if (decoderDAO != null){
            BizBusinessUtils.bizBusinessException("解码器名称重复");
        }
        String ip = decoder.getIp();
        Integer port = decoder.getPort();
        decoderDAO = decoderMapper.selectDecoderByIpAndPortNotId(decoderId,ip, port);
        if (decoderDAO != null){
            BizBusinessUtils.bizBusinessException("ip和端口有重复");
        }
        String decoderCode = decoder.getDecoderCode();
        if (!StringUtils.isEmpty(decoderCode)){
            decoderDAO = decoderMapper.selectDecoderByCodeNotId(decoderId,decoderCode);
            if (decoderDAO != null){
                BizBusinessUtils.bizBusinessException("统一编码有重复");
            }
        }
        decoderDAO = decoderMapper.selectById(decoderId);
        if (decoderDAO == null){
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        BeanUtils.copyProperties(decoder , decoderDAO);
        decoderDAO.setName(decoderName);
        UserResult userResult = redisInfoUtil.getUserResult();
        decoderDAO.setCompanyCode(userResult.getCompanyCode());
        decoderDAO.setDepartmentCode(userResult.getDepParentCode());
        decoderDAO.setUpdateTime(new Date());
        decoderMapper.updateById(decoderDAO);

        //先删除
        deleteDecoderOne(decoderId);
        //在增加
        List<PassagewayDTO> passagewayList = decoder.getPassagewayList();
        savePassageway(decoderId,passagewayList);

        // TODO: 2021/6/16 解码器修改
        return null;
    }

    @Resource
    private DataPermissionUtil dataPermissionUtil;

    @Override
    public DecoderInfoVO findDecoderOne(Integer id) {
        DecoderDAO decoderDAO = decoderMapper.selectById(id);
        if (decoderDAO == null){
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        Integer deviceTypeId = decoderDAO.getDeviceTypeId();
        DeviceTypeDAO deviceTypeDAO = deviceTypeMapper.selectById(deviceTypeId);

        DecoderInfoVO decoderInfoVO = new DecoderInfoVO();
        decoderInfoVO.setId(decoderDAO.getId());
        decoderInfoVO.setDecoderName(decoderDAO.getName());
        decoderInfoVO.setDeviceTypeId(deviceTypeId);
        decoderInfoVO.setDeviceTypeName(deviceTypeDAO.getName());
        decoderInfoVO.setIp(decoderDAO.getIp());
        decoderInfoVO.setPort(decoderDAO.getPort());
        decoderInfoVO.setRegisterAccount(decoderDAO.getRegisterAccount());
        decoderInfoVO.setRegisterPassword(decoderDAO.getRegisterPassword());
        decoderInfoVO.setDecoderCode(decoderDAO.getDecoderCode());

        //查询通道信息
        List<PassagewayInfoVO> passagewayInfoList = decoderPassagewayMapper.selectByDecoderId(decoderDAO.getId());
        decoderInfoVO.setPassagewayInfoVO(passagewayInfoList);

        //镜头信息
        if (!CollectionUtils.isEmpty(passagewayInfoList)){
            passagewayInfoList.forEach(passagewayInfoVO -> {
                Integer passagewayId = passagewayInfoVO.getPassagewayId();
                List<DecoderPassagewayCameraVO> cameraIdList = decoderPassagewayCameraMapper.selectByPassagewayListId(passagewayId);
                passagewayInfoVO.setCameraIdList(cameraIdList);
            });
        }
        return decoderInfoVO;
    }

    @Override
    public String deleteDecoder(List<Integer> ids) {
        //删除decoder decoder_passageway decoder_passageway_camera
        ids.forEach(id -> {
            decoderMapper.deleteById(id);
            deleteDecoderOne(id);
        });
        // TODO: 2021/6/16 发送删除解码器通知

        return null;
    }


    private void deleteDecoderOne(Integer decoderId){
        List<PassagewayInfoVO> passagewayInfoList = decoderPassagewayMapper.selectByDecoderId(decoderId);
        if (!CollectionUtils.isEmpty(passagewayInfoList)){
            List<Integer> decoderPassagewayIds = new ArrayList<>();
            passagewayInfoList.forEach(passagewayInfoVO -> decoderPassagewayIds.add(passagewayInfoVO.getPassagewayId()));
            QueryWrapper<DecoderPassagewayCameraDAO> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("decoder_passageway_id" , decoderPassagewayIds);
            decoderPassagewayCameraMapper.delete(queryWrapper);
        }
        QueryWrapper<DecoderPassagewayDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("decoder_id" , decoderId);
        decoderPassagewayMapper.delete(queryWrapper);
    }

}
