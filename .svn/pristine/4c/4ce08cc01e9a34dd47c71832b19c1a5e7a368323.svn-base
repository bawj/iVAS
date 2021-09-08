package com.yn.electricity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yn.electricity.ResultVO;
import com.yn.electricity.dao.CameraDAO;
import com.yn.electricity.dao.DecoderDAO;
import com.yn.electricity.dao.TvWallDAO;
import com.yn.electricity.enums.ResultEnum;
import com.yn.electricity.mapper.CameraMapper;
import com.yn.electricity.mapper.DecoderMapper;
import com.yn.electricity.mapper.TvWallMapper;
import com.yn.electricity.qto.BaseQuery;
import com.yn.electricity.qto.CameraDTO;
import com.yn.electricity.request.TvWallAlterRequest;
import com.yn.electricity.request.TvWallSaveRequest;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.service.TvWallService;
import com.yn.electricity.service.pas.PasDeviceServiceFeignClient;
import com.yn.electricity.util.DataPermissionUtil;
import com.yn.electricity.util.EntityUtil;
import com.yn.electricity.util.RedisInfoUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.utils.ListUtil;
import com.yn.electricity.vo.TvWallVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author admin
 * Date 2021/3/20 10:44
 * Description 电视墙管理
 **/
@Service
public class TvWallServiceImpl implements TvWallService {

    @Resource
    private PasDeviceServiceFeignClient pasDeviceServiceFeignClient;
    @Resource
    private RedisInfoUtil redisInfoUtil;
    @Resource
    private TvWallMapper tvWallMapper;
    @Resource
    private DataPermissionUtil dataPermissionUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addTvWall(TvWallSaveRequest tvWallSaveRequest) {
        String name = tvWallSaveRequest.getName();

        HashMap<String, Object> tvWallMap = Maps.newHashMap();
        tvWallMap.put("name", name);
        List<TvWallDAO> tvWallList = tvWallMapper.selectByMap(tvWallMap);
        if (!CollectionUtils.isEmpty(tvWallList)) {
            BizBusinessUtils.bizBusinessException("配置名称重复");
        }
        TvWallDAO tvWall = new TvWallDAO();
        BeanUtils.copyProperties(tvWallSaveRequest, tvWall);

        UserResult userResult = redisInfoUtil.getUserResult();
        EntityUtil.setCpyCodeDepCodeIpIdUserId(tvWall, userResult);

        tvWall.setCreateTime(new Date());
        tvWall.setUpdateTime(new Date());

        tvWallMapper.insert(tvWall);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String alterTvWall(TvWallAlterRequest tvWallAlterRequest) {
        Integer id = tvWallAlterRequest.getId();
        TvWallDAO tvWallDAO = tvWallMapper.selectById(id);
        if (tvWallDAO == null) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        String name = tvWallAlterRequest.getName();
        TvWallDAO tvWall = tvWallMapper.selectByNameNotId(id, name);
        if (tvWall != null) {
            BizBusinessUtils.bizBusinessException("配置名称重复");
        }
        tvWall = new TvWallDAO();
        BeanUtils.copyProperties(tvWallAlterRequest, tvWall);

        UserResult userResult = redisInfoUtil.getUserResult();
        EntityUtil.setCpyCodeDepCodeIpIdUserId(tvWall, userResult);

        tvWall.setCreateTime(new Date());
        tvWall.setUpdateTime(new Date());

        tvWallMapper.updateById(tvWall);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteTvWall(List<Integer> ids) {
        if (ids == null) {
            BizBusinessUtils.bizBusinessException("id 错误");
        }
        tvWallMapper.deleteBatchIds(ids);
        return null;
    }

    @Override
    public PageInfo<TvWallVO> findTvWall(Integer pageNum, Integer pageSize, String name, Boolean status) {
        PageHelper.startPage(pageNum , pageSize);
        BaseQuery dataPermission = dataPermissionUtil.getDataPermission();
        List<TvWallVO> tvWallList = tvWallMapper.selectByNameAndStatus(dataPermission, name , status);
        return new PageInfo<>(ListUtil.newArrayList(tvWallList));
    }

    @Resource
    private DecoderMapper decoderMapper;
    @Resource
    private CameraMapper cameraMapper;

    @Override
    public String videoWall(Integer decoderId, Integer devId, Integer channelNo) {
        DecoderDAO decoderDAO = decoderMapper.selectById(decoderId);
        if (decoderDAO == null){
            BizBusinessUtils.bizBusinessException("请选择正确的解码器");
        }
        QueryWrapper<CameraDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dev_id" , devId);
        queryWrapper.eq("channel_no" , channelNo);
        List<CameraDAO> cameraDAOList = cameraMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(cameraDAOList)){
            BizBusinessUtils.bizBusinessException("通道号或设备id错误");
        }
        ResultVO<String> resultVO = pasDeviceServiceFeignClient.videoWall(decoderId, devId, channelNo);
        if (resultVO == null || !resultVO.getCode().equals(ResultEnum.SUCCESS.getCode())){
            BizBusinessUtils.bizBusinessException("解码上墙失败！远程服务调用异常");
        }
        return null;
    }

}
