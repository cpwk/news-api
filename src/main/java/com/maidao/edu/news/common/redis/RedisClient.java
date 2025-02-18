package com.maidao.edu.news.common.redis;

import com.maidao.edu.news.common.util.StringUtils;
import com.sunnysuperman.commons.util.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:RedisClient
 * 类描述:TODO
 **/
public class RedisClient {
    private static final Logger LOG = LoggerFactory.getLogger(RedisClient.class);
    private final JedisPool pool;

    public RedisClient(JedisPool pool, boolean testOnStart) {
        this.pool = pool;
        if (testOnStart) {
            doWork(new TestRedisWork());
        }
    }

    public <T> T doWork(RedisWork<T> work) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return work.run(jedis);
        } finally {
            if (jedis != null) {
                try {
                    jedis.close();
                } catch (Throwable t) {
                    LOG.error(null, t);
                }
            }
        }
    }

    public boolean expire(String key, int seconds) {
        return doWork(new ExpireWork(key, seconds));
    }

    public boolean expireAt(String key, Date expireAt) {
        return doWork(new ExpireAtWork(key, expireAt.getTime() / 1000));
    }

    public byte[] get(String key) {
        return doWork(new GetWork(key));
    }

    public String getString(String key) {
        return ByteUtil.toString(doWork(new GetWork(key)));
    }

    public void set(String key, byte[] value) {
        doWork(new SetWork(key, value, 0));
    }

    public void setex(String key, byte[] value, int seconds) {
        doWork(new SetWork(key, value, seconds));
    }

    public long increase(String key, long num) {
        return doWork(new IncreaseWork(key, num));
    }

    public boolean sadd(String key, String member) {
        return doWork(new SetAddWork(key, member));
    }

    public boolean srem(String key, String member) {
        return doWork(new SetRemoveWork(key, member));
    }

    public boolean sismember(String key, String member) {
        return doWork(new SetIsMemberWork(key, member));
    }

    public Long hincrBy(String key, String field, long incrBy) {
        return doWork(new HincrByWork(key, field, incrBy));
    }

    public boolean hset(String key, String field, String value) {
        return doWork(new HsetWork(key, field, value));
    }

    public Map<String, String> hgetAll(String key) {
        return doWork(new HgetAllWork(key));
    }

    public interface RedisWork<T> {
        T run(Jedis jedis);
    }

    private static class TestRedisWork implements RedisWork<Void> {

        @Override
        public Void run(Jedis jedis) {
            jedis.expire("0", 1);
            return null;
        }

    }

    private static class ExpireWork implements RedisWork<Boolean> {
        String key;
        int seconds;

        public ExpireWork(String key, int seconds) {
            super();
            this.key = key;
            this.seconds = seconds;
        }

        @Override
        public Boolean run(Jedis jedis) {
            return jedis.expire(key, seconds) > 0;
        }

    }

    private static class ExpireAtWork implements RedisWork<Boolean> {
        String key;
        long expireAt;

        public ExpireAtWork(String key, long expireAt) {
            super();
            this.key = key;
            this.expireAt = expireAt;
        }

        @Override
        public Boolean run(Jedis jedis) {
            return jedis.expireAt(key, expireAt) > 0;
        }

    }

    private static class GetWork implements RedisWork<byte[]> {
        String key;

        public GetWork(String key) {
            super();
            this.key = key;
        }

        @Override
        public byte[] run(Jedis jedis) {
            return jedis.get(key.getBytes(StringUtils.UTF8_CHARSET));
        }

    }

    private static class SetWork implements RedisWork<String> {
        String key;
        byte[] value;
        int seconds;

        public SetWork(String key, byte[] value, int seconds) {
            super();
            this.key = key;
            this.value = value;
            this.seconds = seconds;
        }

        @Override
        public String run(Jedis jedis) {
            if (seconds > 0) {
                return jedis.setex(key.getBytes(StringUtils.UTF8_CHARSET), seconds, value);
            } else {
                return jedis.set(key.getBytes(StringUtils.UTF8_CHARSET), value);
            }
        }

    }

    private static class IncreaseWork implements RedisWork<Long> {
        String key;
        long num;

        public IncreaseWork(String key, long num) {
            super();
            this.key = key;
            this.num = num;
        }

        @Override
        public Long run(Jedis jedis) {
            return jedis.incrBy(key, num);
        }

    }

    private static class SetAddWork implements RedisWork<Boolean> {
        String key;
        String member;

        public SetAddWork(String key, String member) {
            super();
            this.key = key;
            this.member = member;
        }

        @Override
        public Boolean run(Jedis jedis) {
            return jedis.sadd(key, member) > 0;
        }

    }

    private static class SetRemoveWork implements RedisWork<Boolean> {
        String key;
        String member;

        public SetRemoveWork(String key, String member) {
            super();
            this.key = key;
            this.member = member;
        }

        @Override
        public Boolean run(Jedis jedis) {
            return jedis.srem(key, member) > 0;
        }

    }

    private static class SetIsMemberWork implements RedisWork<Boolean> {
        String key;
        String member;

        public SetIsMemberWork(String key, String member) {
            super();
            this.key = key;
            this.member = member;
        }

        @Override
        public Boolean run(Jedis jedis) {
            return jedis.sismember(key, member);
        }

    }

    private static class HincrByWork implements RedisWork<Long> {
        String key;
        String field;
        long incrBy;

        public HincrByWork(String key, String field, long incrBy) {
            super();
            this.key = key;
            this.field = field;
            this.incrBy = incrBy;
        }

        @Override
        public Long run(Jedis jedis) {
            return jedis.hincrBy(key, field, incrBy);
        }

    }

    private static class HsetWork implements RedisWork<Boolean> {
        String key;
        String field;
        String value;

        public HsetWork(String key, String field, String value) {
            super();
            this.key = key;
            this.field = field;
            this.value = value;
        }

        @Override
        public Boolean run(Jedis jedis) {
            return jedis.hset(key, field, value) > 0;
        }

    }

    private static class HgetAllWork implements RedisWork<Map<String, String>> {
        String key;

        public HgetAllWork(String key) {
            super();
            this.key = key;
        }

        @Override
        public Map<String, String> run(Jedis jedis) {
            return jedis.hgetAll(key);
        }

    }
}
