package com.maidao.edu.news.common.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:BaseRepositoryFactoryBean
 * 类描述:TODO
 **/
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T,
        I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new BaseRepositoryFactory(em);
    }

    /**
     * 创建一个内部类，该类不用在外部访问
     *
     * @param <T>
     * @param <I>
     */
    private static class BaseRepositoryFactory<T, I extends Serializable>
            extends JpaRepositoryFactory {

        private final EntityManager em;

        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        /**
         * 设置具体的实现类是BaseRepositoryImpl
         *
         * @param information
         * @return
         */

        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseRepositoryImpl<T, I>((Class<T>) information.getDomainType(), em);
        }

        /**
         * 获取具体的实现类的class
         *
         * @param metadata
         * @return
         */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}