package com.yn.electricity.util;

import com.yn.electricity.entity.AlarmConfigurationEntity;
import com.yn.electricity.mapper.AlarmConfigurationMapper;
import com.yn.electricity.mapper.UserMapper;
import com.yn.electricity.qto.DeviceDTO;
import com.yn.electricity.qto.UserDTO;
import com.yn.electricity.redis.RedisUtil;
import com.yn.electricity.result.UserResult;
import com.yn.electricity.utils.BizBusinessUtils;
import com.yn.electricity.vo.RoleMenuVo;
import com.yn.electricity.vo.UserRoleMenuVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * Date 2021/3/17 11:12
 * Description 获取redis 信息 util
 **/
@Component
public class RedisInfoUtil {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AlarmConfigurationMapper alarmConfigurationMapper;

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    public UserResult getUserResult() {
        Object obj = redisUtil.get(RedisKey.USER_INFO + SecurityUtils.getSubject().getPrincipal());
        if (null == obj) {
            checkLogStatus();
            obj = redisUtil.get(RedisKey.USER_INFO + SecurityUtils.getSubject().getPrincipal());
        }
        return (UserResult) obj;
    }

    /**
     * 获取用户操作权限
     *
     * @return
     */
    public List<RoleMenuVo> getPermission() {
        Object obj = redisUtil.get(RedisKey.ROLE_MENU_INFO + SecurityUtils.getSubject().getPrincipal());
        if (null == obj) {
            checkLogStatus();
            obj = redisUtil.get(RedisKey.ROLE_MENU_INFO + SecurityUtils.getSubject().getPrincipal());
        }
        return (List<RoleMenuVo>) obj;
    }

    /**
     * 登陆信息提交到redis
     *
     * @param user
     * @param loginName
     */
    public void commit(UserRoleMenuVo user, String loginName) {
        redisUtil.set(RedisKey.ROLE_MENU_INFO + loginName, user.getRoleMenuVoList());
        UserResult userDao = new UserResult();
        userDao.setUserName(user.getUserName());
        userDao.setAvailable(user.getAvailable());
        userDao.setNickname(user.getNickname());
        userDao.setCompanyCode(user.getCompanyCode());
        userDao.setDepCode(user.getDepCode());
        userDao.setPhone(user.getPhone());
        userDao.setSex(user.getSex());
        userDao.setEmail(user.getEMail());
        userDao.setId(user.getId());
        userDao.setIpId(user.getIpId());
        redisUtil.set(RedisKey.USER_INFO + loginName, userDao);
    }

    /**
     * 检查登陆
     */
    public void checkLogStatus() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            BizBusinessUtils.bizBusinessException("用户未登陆");
        }
        String loginName = String.valueOf(subject.getPrincipal());
        UserDTO userDTO = new UserDTO();
        if (loginName.contains("@")) {
            userDTO.setEMail(loginName);
        } else {
            userDTO.setPhone(loginName);
        }
        UserRoleMenuVo user = userMapper.selectByLoginName(userDTO);
        commit(user, loginName);
    }


    /**
     * 保存配置信息到redis
     *
     * @param devId       设备id
     * @param alarmTypeId 报警类型
     * @param channelNo   camera通道号
     */
    public AlarmConfigurationEntity setAlarmConfigurationVO(Integer devId, Integer alarmTypeId, Integer channelNo) {
        AlarmConfigurationEntity alarmConfiguration = alarmConfigurationMapper.findConfigurationByDevIdAndAlarmTypeId(
                devId, alarmTypeId, channelNo);
        if (alarmConfiguration != null) {
            redisUtil.set(RedisKey.CONFIGURATION_ALARM + devId +"--"+ alarmTypeId, alarmConfiguration);
        }
        return alarmConfiguration;
    }

    /**
     * 从redis中读取配置信息 如果redis中不存在则查询数据库
     *
     * @param devId       设备id
     * @param alarmTypeId 报警类型
     * @return AlarmConfigurationVO
     */
    public AlarmConfigurationEntity getAlarmConfigurationEntity(Integer devId, Integer alarmTypeId, Integer channelNo) {
        Object o = redisUtil.get(RedisKey.CONFIGURATION_ALARM + devId +"--"+ alarmTypeId);
        if (o == null) {
            return setAlarmConfigurationVO(devId, alarmTypeId, channelNo);
        }else {
            return (AlarmConfigurationEntity) o;
        }
    }

    /**
     * 删除redis缓存信息
     * @param devId devId
     * @param alarmTypeId 报警类型
     */
    public void delAlarmConfigurationEntity(Integer devId, Integer alarmTypeId){
        redisUtil.del(RedisKey.CONFIGURATION_ALARM + devId +"--"+ alarmTypeId);
    }


    /**
     * 设备信息缓存
     */
    public void setDevice(List<DeviceDTO> deviceList) {
        redisUtil.set(RedisKey.DEVICE_KEY, deviceList , 60 * 60 *24);
    }

    /**
     * 删除设备缓存信息
     */
    public void delDevice() {
        redisUtil.del(RedisKey.DEVICE_KEY);
    }

    /**
     * 读取redis缓存的设备信息
     *
     * @return list
     */
    public List<DeviceDTO> getDeviceList() {
        Object o = redisUtil.get(RedisKey.DEVICE_KEY);
        if (o == null) {
            return null;
        }
        return castList(o, DeviceDTO.class);
    }

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

}
