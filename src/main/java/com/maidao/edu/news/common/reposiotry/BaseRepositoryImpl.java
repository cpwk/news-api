package com.maidao.edu.news.common.reposiotry;

import com.maidao.edu.news.common.reposiotry.support.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder.In;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:BaseRepositoryImpl
 * 类描述:基础持久层接口实现类
 **/
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID>, JpaSpecificationExecutor<T> {

    private final EntityManager entityManager;
    private final Class<T> clazz;
    final private BaseRepositoryImpl exmple;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.clazz = domainClass;
        this.entityManager = entityManager;
        exmple = this;
    }

    @Override
    public List<T> findAll(DataQueryObjectSort dataQueryObjectSort) {
        final DataQueryObject dqo = dataQueryObjectSort;
//		如果排序内容为空 则执行不排序的 查找
        if (dataQueryObjectSort.getSortPropertyName() != null && dataQueryObjectSort.getSortPropertyName().trim().length() != 0) {
            return this.findAll(dqo, new Sort(dataQueryObjectSort.isSortAscending() ? Sort.Direction.ASC : Sort.Direction.DESC, dataQueryObjectSort.getSortPropertyName()));
        } else {
            return this.findAll(dqo);
        }
    }

    @Override
    public List<T> findAll(DataQueryObject dataQueryObject, Sort sort) {
        final DataQueryObject dqo = dataQueryObject;
        return this.findAll(new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return exmple.getPredocate(root, query, cb, dqo);
            }
        }, sort);
    }

    @Override
    public List<T> findAll(DataQueryObject dataQueryObject) {
        final DataQueryObject dqo = dataQueryObject;
        return this.findAll(new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return exmple.getPredocate(root, query, cb, dqo);
            }
        });
    }

    @Override
    public Page<T> findAll(DataQueryObjectPage dataQueryObjectpage) {
        Pageable pageable = null;
        if (dataQueryObjectpage.getSortPropertyName() != null && dataQueryObjectpage.getSortPropertyName().trim().length() != 0) {
            pageable = PageRequest.of(dataQueryObjectpage.getPageNumber(), dataQueryObjectpage.getPageSize(), new Sort(dataQueryObjectpage.isSortAscending() ? Sort.Direction.ASC : Sort.Direction.DESC, dataQueryObjectpage.getSortPropertyName()));
        } else {
            pageable = PageRequest.of(dataQueryObjectpage.getPageNumber(), dataQueryObjectpage.getPageSize());
        }
        return this.findAll(dataQueryObjectpage, pageable);
    }

    @Override
    public Page<T> findAll(DataQueryObject dataQueryObject, Pageable page) {
        final DataQueryObject dqo = dataQueryObject;
        return this.findAll(new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return exmple.getPredocate(root, query, cb, dqo);
            }
        }, page);
    }


    //	核心方法 拼接条件
    protected Predicate getPredocate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb,
                                     DataQueryObject dqo) {
        List<Predicate> predicates = new ArrayList<>();
//		获取查询对象的所有属性
        Field[] fields = dqo.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            String queryFiled = null;
            QueryType queryType = null;
            Object value = null;
            Predicate predicate = null;
//			获取属性的 自定义注解类型
            QueryField annotaion = field.getAnnotation(QueryField.class);
//			如果没有注解 则跳过
            if (annotaion == null) {
                continue;
            }
//			如果注解中 name为空 则用字段名称作为属性名
            if (!StringUtils.isEmpty(annotaion.name())) {
                queryFiled = annotaion.name();
            } else {
                queryFiled = field.getName();
            }
            queryType = annotaion.type();
            try {
                value = field.get(dqo);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
//			判断字段类型是否为空
            if (value == null && !queryType.isCanBeNull()) {
//                L.error("查询类型：" + queryType + " " + queryFiled + "不允许为空。");
                continue;
            }
//			判断注解中 的条件类型
            switch (queryType) {
                case EQUAL:
                    Path<Object> equal = getRootByQueryFiled(queryFiled, root);
                    predicate = cb.equal(equal, value);
                    predicates.add(predicate);
                    break;
                case BEWTEEN:
                    Path<Comparable> between = getRootByQueryFiledComparable(queryFiled, root);
                    QueryBetween queryBetween = null;
                    if (value instanceof QueryBetween)
                        queryBetween = (QueryBetween) value;
                    else
                        continue;
                    predicate = cb.between(between, queryBetween.after, queryBetween.before);
                    predicates.add(predicate);
                    break;
                case LESS_THAN:
                    Path<Comparable> lessThan = getRootByQueryFiledComparable(queryFiled, root);
                    if (value instanceof QueryBetween)
                        queryBetween = (QueryBetween) value;
                    else
                        continue;
                    predicate = cb.lessThan(lessThan, queryBetween.after);
                    predicates.add(predicate);
                    break;
                case LESS_THAN_EQUAL:
                    Path<Comparable> lessThanOrEqualTo = getRootByQueryFiledComparable(queryFiled, root);
                    if (value instanceof QueryBetween)
                        queryBetween = (QueryBetween) value;
                    else
                        continue;
                    predicate = cb.lessThanOrEqualTo(lessThanOrEqualTo, queryBetween.after);
                    predicates.add(predicate);
                    break;
                case GREATEROR_THAN:
                    Path<Comparable> greaterThan = getRootByQueryFiledComparable(queryFiled, root);
                    if (value instanceof QueryBetween)
                        queryBetween = (QueryBetween) value;
                    else
                        continue;
                    predicate = cb.greaterThan(greaterThan, queryBetween.after);
                    predicates.add(predicate);
                    break;
                case GREATEROR_THAN_EQUAL:
                    Path<Comparable> greaterThanOrEqualTo = getRootByQueryFiledComparable(queryFiled, root);
                    if (value instanceof QueryBetween)
                        queryBetween = (QueryBetween) value;
                    else
                        continue;
                    predicate = cb.lessThanOrEqualTo(greaterThanOrEqualTo, queryBetween.after);
                    predicates.add(predicate);
                    break;
                case NOT_EQUAL:
                    Path<Object> notEqual = getRootByQueryFiled(queryFiled, root);
                    predicate = cb.notEqual(notEqual, value);
                    predicates.add(predicate);
                    break;
                case IS_NULL:
                    Path<Object> isNull = getRootByQueryFiled(queryFiled, root);
                    predicate = cb.isNull(isNull);
                    predicates.add(predicate);
                    break;
                case IS_NOT_NULL:
                    Path<Object> isNotNull = getRootByQueryFiled(queryFiled, root);
                    predicate = cb.isNotNull(isNotNull);
                    predicates.add(predicate);
                    break;
                case LEFT_LIKE:
                    Path<String> leftLike = getRootByQueryFiledString(queryFiled, root);
                    predicate = cb.like(leftLike, "%" + value.toString());
                    predicates.add(predicate);
                    break;
                case RIGHT_LIKE:
                    Path<String> rightLike = getRootByQueryFiledString(queryFiled, root);
                    predicate = cb.like(rightLike, value.toString() + "%");
                    predicates.add(predicate);
                    break;
                case FULL_LIKE:
                    Path<String> fullLike = getRootByQueryFiledString(queryFiled, root);
                    predicate = cb.like(fullLike, "%" + value.toString() + "%");
                    predicates.add(predicate);
                    break;
                case DEFAULT_LIKE:
                    Path<String> like = getRootByQueryFiledString(queryFiled, root);
                    predicate = cb.like(like, value.toString());
                    predicates.add(predicate);
                    break;
                case NOT_LIKE:
                    Path<String> notLike = getRootByQueryFiledString(queryFiled, root);
                    predicate = cb.like(notLike, value.toString());
                    predicates.add(predicate);
                    break;
                case IN:
                    Path<Object> in = getRootByQueryFiled(queryFiled, root);
                    In ins = cb.in(in);
                    List inList = null;
                    if (value instanceof List) {
                        inList = (List) value;
                    }
                    for (Object object : inList) {
                        ins.value(object);
                    }
                    predicates.add(ins);
                    break;
                default:
                    break;
            }
        }
//		如果 为空 代表  没有任何有效的条件
        if (predicates.size() == 0) {
            return cb.and();
        }
        Object[] list = predicates.toArray();
        Predicate[] t = new Predicate[predicates.size()];
        Predicate[] result = predicates.toArray(t);
        return cb.and(result);
    }

    private Path<Object> getRootByQueryFiled(String queryFiled, Root<T> root) {
        if (queryFiled.indexOf(".") < 0) {
            return root.get(queryFiled);
        } else {
            return getRootByQueryFiled(queryFiled.substring(queryFiled.indexOf(".") + 1), root.get(queryFiled.substring(0, queryFiled.indexOf("."))));
        }
    }

    private Path<Object> getRootByQueryFiled(String queryFiled, Path<Object> path) {
        if (queryFiled.indexOf(".") < 0) {
            return path.get(queryFiled);
        } else {
            return getRootByQueryFiled(queryFiled.substring(queryFiled.indexOf(".") + 1), path.get(queryFiled.substring(0, queryFiled.indexOf("."))));
        }
    }

    private Path<String> getRootByQueryFiledString(String queryFiled, Root<T> root) {
        if (queryFiled.indexOf(".") < 0) {
            return root.get(queryFiled);
        } else {
            return getRootByQueryFiledString(queryFiled.substring(queryFiled.indexOf(".") + 1), root.get(queryFiled.substring(0, queryFiled.indexOf("."))));
        }
    }

    private Path<String> getRootByQueryFiledString(String queryFiled, Path<Object> path) {
        if (queryFiled.indexOf(".") < 0) {
            return path.get(queryFiled);
        } else {
            return getRootByQueryFiledString(queryFiled.substring(queryFiled.indexOf(".") + 1), path.get(queryFiled.substring(0, queryFiled.indexOf("."))));
        }
    }

    private Path<Comparable> getRootByQueryFiledComparable(String queryFiled, Root<T> root) {
        if (queryFiled.indexOf(".") < 0) {
            return root.get(queryFiled);
        } else {
            return getRootByQueryFiledComparable(queryFiled.substring(queryFiled.indexOf(".") + 1), root.get(queryFiled.substring(0, queryFiled.indexOf("."))));
        }
    }

    private Path<Comparable> getRootByQueryFiledComparable(String queryFiled, Path<Object> path) {
        if (queryFiled.indexOf(".") < 0) {
            return path.get(queryFiled);
        } else {
            return getRootByQueryFiledComparable(queryFiled.substring(queryFiled.indexOf(".") + 1), path.get(queryFiled.substring(0, queryFiled.indexOf("."))));
        }
    }

}
