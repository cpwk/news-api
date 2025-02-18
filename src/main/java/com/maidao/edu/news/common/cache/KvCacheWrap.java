package com.maidao.edu.news.common.cache;

import com.maidao.edu.news.common.task.TaskService;
import com.maidao.edu.news.common.util.L;
import com.sunnysuperman.kvcache.*;
import com.sunnysuperman.kvcache.converter.ModelConverter;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:KvCacheWrap
 * 类描述:TODO
 **/

public class KvCacheWrap<K, T> extends DefaultKvCache<K, T> {

    private final TaskService taskService;

    public KvCacheWrap(KvCacheExecutor executor, KvCachePolicy policy, RepositoryProvider<K, T> repository,
                       ModelConverter<T> converter, KvCacheSaveFilter<T> saveFilter, TaskService taskService) {
        super(executor, policy, repository, converter, saveFilter);
        this.taskService = taskService;
    }

    // 安全删除
    @Override
    public void remove(K id) {
        try {
            tryToRemove(id);
        } catch (Exception ex) {
            L.error(ex);
        }
        // 防止大并发情况下，删除缓存操作时，另一个线程（或者进程）获取到脏数据（老数据），并把脏数据保存到缓存里
        try {
            taskService.scheduleTask(new RemoveCacheTask(id, 0), 15000);
        } catch (Exception ex) {
            L.error(ex);
        }
    }

    // 尝试删除，出错会抛出异常
    public void tryToRemove(K id) throws KvCacheException {
        super.remove(id);
    }

    // 安全保存
    @Override
    public void save(K key, T value) throws KvCacheException {
        try {
            tryToSave(key, value);
        } catch (Exception e) {
            L.error(e);
            try {
                taskService.scheduleTask(new RemoveCacheTask(key, 0), 1000);
            } catch (Exception ex) {
                L.error(ex);
            }
        }
    }

    // 尝试保存，出错会抛出异常
    public void tryToSave(K key, T value) throws KvCacheException {
        super.save(key, value);
    }

    private class RemoveCacheTask implements Runnable {
        K key;
        int retry;

        public RemoveCacheTask(K key, int retry) {
            super();
            this.key = key;
            this.retry = retry;
        }

        @Override
        public void run() {
            try {
                tryToRemove(key);
            } catch (Exception e) {
                LOG.error(null, e);
                retry++;
                if (retry < 10) {
                    taskService.scheduleTask(new RemoveCacheTask(key, retry), 100);
                } else {
                    L.error("Failed to explicitly remove cache: " + key);
                }
            }
        }

    }
}
