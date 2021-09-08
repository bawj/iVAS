package com.yn.electricity.rpc.service;

import com.yn.electricity.ResultVO;
import com.yn.electricity.as.*;
import com.yn.electricity.enums.ErrorCommonEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: CameraFeignClientImpl
 * @Author: zzs
 * @Date: 2021/3/26 14:39
 * @Description: 出异常时返回次类值
 */
@Service
public class CameraFeignClientImpl implements CameraFeignClient {

    @Override
    public ResultVO<String> control(ParamsControlVO paramsControlVO) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(Integer.valueOf(ErrorCommonEnum.REMOTE_INVOKE_ERROR.getCode()));
        resultVO.setMsg(ErrorCommonEnum.REMOTE_INVOKE_ERROR.getChineseName());
        resultVO.setData(null);
        return resultVO;
    }

    @Override
    public ResultVO<List<PlayBackVO>> playback(List<PlaybackParam> cameraIdList) {
        ResultVO<List<PlayBackVO>> resultVO = new ResultVO<>();
        resultVO.setCode(Integer.valueOf(ErrorCommonEnum.REMOTE_INVOKE_ERROR.getCode()));
        resultVO.setMsg(ErrorCommonEnum.REMOTE_INVOKE_ERROR.getChineseName());
        resultVO.setData(null);
        return resultVO;
    }

    @Override
    public ResultVO<PlayBackVO> realPlay(Integer id, Integer auxiliaryBitStreamEnable) {
        ResultVO<PlayBackVO> resultVO = new ResultVO<>();
        resultVO.setCode(Integer.valueOf(ErrorCommonEnum.REMOTE_INVOKE_ERROR.getCode()));
        resultVO.setMsg(ErrorCommonEnum.REMOTE_INVOKE_ERROR.getChineseName());
        resultVO.setData(null);
        return resultVO;
    }

    @Override
    public ResultVO<List<QueryTimeVO>> queryTime(List<QueryTimeParam> cameraIdList) {
        ResultVO<List<QueryTimeVO>> resultVO = new ResultVO<>();
        resultVO.setCode(Integer.valueOf(ErrorCommonEnum.REMOTE_INVOKE_ERROR.getCode()));
        resultVO.setMsg(ErrorCommonEnum.REMOTE_INVOKE_ERROR.getChineseName());
        resultVO.setData(null);
        return resultVO;
    }
}
