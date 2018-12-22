package com.kirck.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.kirck.commen.constants.SysConstants;
import com.kirck.entity.User;

/**
 * session 缓存到 redis ，因此直接继承抽象类，覆写 CRUD 方法即可
 * @author zhouman
 */
@Component
public class RedisSessionDao extends AbstractSessionDAO {
    private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);

	@Resource
    RedisTemplate<String,Object> redisTemplate;

    /**
     * The Redis key prefix for the sessions
     */
    private String keyPrefix = "shiro_session:";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(keyPrefix + session.getId().toString(), 
                session, SysConstants.Token.CACHE_TIME_SECOND,TimeUnit.SECONDS);
        logger.debug("创建 Session, 存储于 redis, sessionId: {}", sessionId.toString());
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("从redis获取 Session, sessionId: {}", sessionId.toString());
        return (Session) redisTemplate.opsForValue().get(keyPrefix + sessionId.toString());
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        logger.debug("刷新新 redis Session生效时间");
        redisTemplate.opsForValue().set(keyPrefix + session.getId().toString(), 
                session, SysConstants.Token.CACHE_TIME_SECOND,TimeUnit.SECONDS);
    }

    @Override
    public void delete(Session session) {
    	redisTemplate.delete(keyPrefix + session.getId().toString());
    }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Session> getActiveSessions() {
		 Map<Object, Object> entries = redisTemplate.opsForHash().entries(keyPrefix);
		 Object mapObj = entries.values();
	     Collection<Session> values = (Collection<Session>) mapObj;
	     return values;
	}
    
}
