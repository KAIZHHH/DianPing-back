package com.kai.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器实现：进行用户登录信息的校验 查看threadlocal中是否存在用户信息 有则放行
 */
public class LoginInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public LoginInterceptor() {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//      判断是否需要拦截（ThreadLocal中是否有用户）
        if (UserHolder.getUser() == null) {
            // 没有，需要拦截，设置状态码
            response.setStatus(401);
            // 拦截
            return false;
        }
        // 有用户，则放行
        return true;


//        //1、获取请求头的token
//        String token = request.getHeader("authorization");
//        if (StrUtil.isBlank(token)) {
//            // 没有，需要拦截，设置状态码
//            response.setStatus(401);
//            // 拦截
//            return false;
//        }
//        //2、通过token获取redis用户信息
//        String key = RedisConstants.LOGIN_USER_KEY + token;
//        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(RedisConstants.LOGIN_USER_KEY + token);
//        //3、判断用户是否存在
//        if (userMap.isEmpty()) {
//            //4、不存在 拦截 返回401状态码
//            response.setStatus(401);
//            return false;
//        }
//        //5、将查询到的Hash数据转为UserDTO对象
//        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
//        //6、存在 保存用户信息到ThreadLocal 用于提取用户信息
//        UserHolder.saveUser(userDTO);
//        //7、刷新token过期时间
//        stringRedisTemplate.expire(key, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
//
//        //8、放行
//        return true;
    }
}
