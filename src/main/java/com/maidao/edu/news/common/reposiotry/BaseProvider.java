package com.maidao.edu.news.common.reposiotry;

import com.maidao.edu.news.common.entity.Constants;
import com.maidao.edu.news.common.exception.RuntimeServiceException;
import com.maidao.edu.news.common.reposiotry.support.DataQueryObject;
import com.maidao.edu.news.common.reposiotry.support.QueryField;
import com.maidao.edu.news.common.reposiotry.support.QueryTable;
import com.maidao.edu.news.common.reposiotry.support.QueryType;
import com.maidao.edu.news.common.util.StringUtils;
import com.sunnysuperman.commons.util.FormatUtil;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public class BaseProvider {

    public String findByQo(DataQueryObject query) {
        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(getTable(query));
        String where = getWhereSql(query);
        if (StringUtils.isNotEmpty(where)) {
            sql.append(where);
        }
        sql.append(" order by ").append(getSortName(query)).append(" ")
                .append(getSort(query) ? "asc" : "desc");
        Integer pageNo = getPageNo(query);
        Integer pageSize = getPageSize(query);
        sql.append(" limit ").append(pageNo * pageSize).append(",").append(pageSize);
        return sql.toString();
    }

    public String countByQo(DataQueryObject query) {
        StringBuilder sql = new StringBuilder("select count(*) from ");
        sql.append(getTable(query));
        String where = getWhereSql(query);
        if (StringUtils.isNotEmpty(where)) {
            sql.append(where);
        }
        return sql.toString();
    }

    private String getSortName(DataQueryObject query) {
        String sort = "id";
        try {
            Method method = query.getClass().getMethod("getSortPropertyName");
            sort = FormatUtil.parseString(method.invoke(query));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sort;
    }

    private Boolean getSort(DataQueryObject query) {
        Boolean sortAsc = false;
        try {
            Method method = query.getClass().getMethod("isSortAscending");
            sortAsc = FormatUtil.parseBoolean(method.invoke(query));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortAsc;
    }

    private Integer getPageNo(DataQueryObject query) {
        Integer pageNo = Constants.DEFAULT_PAGENUMBER;
        try {
            Method getPageNumber = query.getClass().getMethod("getPageNumber");
            pageNo = FormatUtil.parseInteger(getPageNumber.invoke(query));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageNo;
    }

    private Integer getPageSize(DataQueryObject query) {
        Integer pageSize = Constants.PAGESIZE_MIN;
        try {
            Method getPageSize = query.getClass().getMethod("getPageSize");
            pageSize = FormatUtil.parseInteger(getPageSize.invoke(query));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageSize;
    }

    private String getTable(DataQueryObject query) {
        QueryTable annotation = query.getClass().getDeclaredAnnotation(QueryTable.class);
        if (annotation == null) {
            throw new RuntimeServiceException("Can't find QueryTable.");
        }
        if (StringUtils.isEmpty(annotation.value())) {
            throw new RuntimeServiceException("QueryTable don't have value.");
        }
        return annotation.value();
    }

    protected String getWhereSql(DataQueryObject query) {
        List<String> condition = extractCondition(query);
        if (CollectionUtils.isEmpty(condition)) {
            return "";
        }
        StringBuilder where = new StringBuilder(" where ");
        for (int i = 0; i < condition.size(); i++) {
            if (i > 0) {
                where.append(" and ");
            }
            where.append(condition.get(i));
        }
        return where.toString();
    }

    private List<String> extractCondition(DataQueryObject query) {
        List<String> condition = new ArrayList<>();
        Field[] fields = query.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            QueryField annotation = field.getAnnotation(QueryField.class);
            if (annotation == null) {
                continue;
            }
            String queryFiled = null;
            //如果注解中 name为空 则用字段名称作为属性名
            if (!StringUtils.isEmpty(annotation.name())) {
                queryFiled = annotation.name();
            } else {
                queryFiled = field.getName();
            }
            QueryType queryType = annotation.type();
            Object value = null;
            try {
                value = field.get(query);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == null) {
                continue;
            }
            switch (queryType) {
                case EQUAL:
                    condition.add(queryFiled + "=" + FormatUtil.parseString(value));
                    break;
                case DEFAULT_LIKE:
                    condition.add(queryFiled + " like '%" + FormatUtil.parseString(value) + "%'");
                    break;
                default:
                    break;
            }
        }
        return condition;
    }
}
