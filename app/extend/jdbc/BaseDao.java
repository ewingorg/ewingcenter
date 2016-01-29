package extend.jdbc;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jws.Logger;
import jws.dal.ConnectionHandler;
import jws.dal.Dal;
import jws.dal.annotation.Column;
import jws.dal.annotation.Id;
import jws.dal.common.DbType;
import jws.dal.common.DbTypeUtils;
import jws.dal.common.Entity;
import jws.dal.common.EntityField;
import jws.dal.common.EntityPacket;
import jws.dal.common.SqlParam;
import jws.dal.exception.DalException;
import jws.dal.manager.EntityManager;
import jws.dal.sqlbuilder.Condition;
import jws.dal.sqlbuilder.Shard;
import jws.dal.sqlbuilder.Sort;
import jws.db.DB;
import jws.db.RWType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 数据对象操作的基础类
 * 
 * @author tanson lam
 * @createDate 2015年2月27日
 * 
 */
public class BaseDao {

    /**
     * 新增
     */
    protected static <T> boolean add(T entity) { 
        return Dal.insert(entity) > 0;
    }

    /**
     * 新增
     */
    protected static <T> boolean addSelectLastId(T entity) {
        try {

            long id = Dal.insertSelectLastId(entity);
            if (id > 0) {
                Field idField = getPrimaryField(entity);

                idField.setAccessible(true);
                Class fieldClass = idField.getType();
                if (fieldClass.equals(Integer.class))
                    idField.set(entity, Integer.valueOf(String.valueOf(id)));
                else if (fieldClass.equals(Long.class))
                    idField.set(entity, Integer.valueOf(String.valueOf(id)));
            }
        } catch (Exception e) {
            // Logger.error("error to set the value of primary column", e);
        }
        return true;
    }

    /**
     * 删除
     */
    protected static <T> boolean delete(T entity) {
        int update = 0;
        String simpleClazzName = entity.getClass().getSimpleName();
        Field primaryField = getPrimaryField(entity);
        Object primaryValue = null;
        if (primaryField != null) {
            try {
                primaryValue = primaryField.get(entity);
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
            }

            if (primaryValue != null) {
                Condition condition = new Condition(simpleClazzName + "." + primaryField.getName(),
                        "=", primaryValue);
                update = Dal.delete(condition);
            }
        }
        return update > 0;
    }

    /**
     * 执行sql
     * 
     * @param commendIds
     * @return
     * @throws Exception
     */
    protected static <T> int executeSql(Class<T> entityClazz, final String sql) {
        ConnectionHandler handler = new ConnectionHandler() {
            PreparedStatement pst = null;

            @Override
            public Integer handle(Connection conn) throws Exception {
                try {
                    pst = conn.prepareStatement(sql);
                    int ret = pst.executeUpdate();
                    return ret;
                } finally {
                    DB.close();
                }
            }
        };

        try {
            return (Integer) getConnection(entityClazz, handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static <T> T getConnection(Class<?> cl, ConnectionHandler handler)
            throws RuntimeException {
        try {
            Connection conn = Dal.getConnection(cl);
            return (T) handler.handle(conn);
        } catch (Exception e) {
            String message = "ConnectionHandler exception";
            throw new RuntimeException(message, e);
        } finally {
            DB.close();
        }
    }

    protected static <T> List<T> executeQuery(Class<T> clz, String sql) {
        BeanListProcessHandler handler = new BeanListProcessHandler(clz);
        return Dal.executeQuery(clz, sql, handler);
    }

    protected static <T> List<T> executeQuery(final Class<T> clz, final String sql, Connection conn) {

        ConnectionHandler handler = new ConnectionHandler() {

            @Override
            public List<T> handle(Connection conn) throws Exception {
                Statement stmt = null;
                ResultSet result = null;
                try {
                    stmt = conn.createStatement();
                    result = stmt.executeQuery(sql);
                    BeanListProcessHandler handler = new BeanListProcessHandler(clz);
                    return handler.handle(result);
                } finally {
                    try {
                        if (result != null)
                            result.close();
                        if (stmt != null)
                            stmt.close();
                        DB.close();
                    } catch (Exception e) {
                        Logger.error(e, e.getMessage());
                    }
                }
            }
        };

        try {
            return handler.handle(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改
     */
    protected static <T> boolean update(T entity) {
        int update = 0;
        Field primaryField = getPrimaryField(entity);
        String simpleClazzName = entity.getClass().getSimpleName();
        Object primaryValue = null;
        if (primaryField != null) {
            try {
                primaryValue = primaryField.get(entity);
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
            }
            if (primaryValue != null) {
                List<String> excludes = new ArrayList<String>();
                excludes.add(primaryField.getName());
                String fieldNames = populateFields(entity.getClass(), excludes);
                Condition condition = new Condition(simpleClazzName + "." + primaryField.getName(),
                        "=", primaryValue);
                update = Dal.update(entity, fieldNames, condition);
            }
        }
        return update > 0;
    }

    /**
     * 修改
     */
    protected static <T> boolean update(T entity, List<String> excludeFieds, Shard shard) {
        int update = 0;
        Field primaryField = getPrimaryField(entity);
        String simpleClazzName = entity.getClass().getSimpleName();
        Object primaryValue = null;
        if (primaryField != null) {
            try {
                primaryValue = primaryField.get(entity);
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
            }
            if (primaryValue != null) {
                List<String> excludes = new ArrayList<String>();
                excludes.add(primaryField.getName());
                if (excludeFieds != null)
                    excludes.addAll(excludeFieds);
                String fieldNames = populateFields(entity.getClass(), excludes);
                Condition condition = new Condition(simpleClazzName + "." + primaryField.getName(),
                        "=", primaryValue);
                update = Dal.update(entity, fieldNames, condition, shard);
            }
        }
        return update > 0;
    }

    /**
     * 查找单个实体对象
     * 
     * @return
     */
    protected static <T> T findById(Integer id, Class<T> entityClazz) {
        String simpleClazzName = entityClazz.getSimpleName();
        return (T) Dal.select(simpleClazzName + ".*", id);
    }

    /**
     * 查询实体列表
     * 
     * @return
     */
    protected static <T> List<T> list(Class<T> entityClazz, Condition condition) {
        String simpleClazzName = entityClazz.getSimpleName();
        return Dal.select(simpleClazzName + ".*", condition, null, -1, -1);
    }

    /**
     * 查询实体列表
     * 
     * @return
     */
    protected static <T> List<T> list(Class<T> entityClazz, Condition condition, Sort sort) {
        String simpleClazzName = entityClazz.getSimpleName();
        return Dal.select(simpleClazzName + ".*", condition, sort, -1, -1);
    }

    /**
     * 分页查询
     * 
     * @return
     */
    protected static <T> List<T> list(Class<T> entityClazz, Condition condition, Sort sort,
            int page, int pageSize) {
        int offset = getOffset(page, pageSize);
        int limit = getLimit(page, pageSize);
        String simpleClazzName = entityClazz.getSimpleName();
        return Dal.select(simpleClazzName + ".*", condition, sort, offset, limit);
    }

    protected static <T> int count(Condition condition) {
        return Dal.count(condition);
    }

    /**
     * support so much sort query support avoid SQL Injection not support share
     * 
     * @param selected
     * @param condition
     * @param sorts
     * @param page
     * @param pageSize
     * @return
     */
    protected static <T> List<T> executeSql(Class clz, String selected, Condition condition,
            Sort[] sorts, int page, int pageSize) {

        List<T> resultList = new ArrayList<T>();

        if (StringUtils.isEmpty(selected)) {
            throw new DalException("BaseDao.executeSql->the selected param is empty.");
        }

        Entity entity = EntityManager.getInstance().getEntity(clz);

        String sqlSelect = getSqlSelect(selected);
        String orderBy = getOrderBy(sorts);

        StringBuilder sql = new StringBuilder();

        sql.append(" select ").append(sqlSelect).append(" from ").append(entity.getTableName());

        List<SqlParam> sqlParams = new ArrayList<SqlParam>();
        String conditionSql = (condition == null) ? null : condition.getSql();
        if (conditionSql != null) {
            sql.append(" where ");
            sql.append(conditionSql);
            sqlParams = condition.getSqlParams();
        }
        if (orderBy != null) {
            sql.append(" order by ");
            sql.append(orderBy);
        }
        if (pageSize != -1) {
            sql.append(" limit ");
            sql.append(getOffset(page, pageSize));
            sql.append(",");
            sql.append(getLimit(page, pageSize));
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String client = entity.getGroups().get(0).getClient();
            conn = DB.getConnection(client, RWType.READ);
            ps = conn.prepareStatement(sql.toString());
            paramsPrepare(ps, sqlParams);
            Logger.debug("[sql][client:%s] %s", client, sql.toString());
            for (SqlParam p : sqlParams) {
                Logger.debug("[param][%s] : %s", p.getFname(), p.getValue());
            }
            // 执行sql语句
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                @SuppressWarnings("unchecked")
                T data = (T) clz.newInstance();
                for (EntityField entityField : entity.getEntityFields()) {
                    Object obj = null;
                    try {
                        if (entityField.getDbType() == DbType.Blob) {
                            obj = result.getBlob(entityField.getColumnName());
                        } else {
                            obj = result.getObject(entityField.getColumnName());
                        }
                    } catch (SQLException e) {
                        // 不存在该column的情况
                        obj = null;
                    }
                    if (obj == null) {
                        // 该字段在数据库中为null
                        if (!entityField.isRefType()) {
                            // 值类型，赋默认值
                            obj = DbTypeUtils.getDefaultValue(entityField.getDbType());
                        }
                    } else {
                        // 特殊处理：boolean值
                        obj = DbTypeUtils.getBooleanValue(obj);

                        // 特殊处理：时间类型处理
                        if (entityField.getDbType() == DbType.DateTime) {
                            obj = DbTypeUtils.getTimestampValue(obj);
                        }

                        if (entityField.getDbType() == DbType.Blob) {
                            obj = DbTypeUtils.getBlobValue(obj);
                        }
                        // 反射赋值
                        entityField.getField().set(data, obj);
                    }
                }
                resultList.add(data);
            }
            DB.close();
        } catch (Exception e) {
            Logger.error(e, "BaseDao.executeSql - > ", e.getMessage());
            throw new DalException(e.getMessage(), e);
        } finally {
            DB.close();
        }
        return resultList;
    }

    private static void paramsPrepare(PreparedStatement ps, List<SqlParam> sqlParams)
            throws Exception {
        try {
            for (int i = 0; i < sqlParams.size(); i++) {
                SqlParam sqlParam = sqlParams.get(i);
                String fname = sqlParam.getFname();
                EntityPacket pair = getEntityPair(fname);
                EntityField entityField = pair.getEntityField();
                if (Logger.isDebugEnabled()) {
                    if (entityField.getDbType() == DbType.Blob) {
                        String show = null;
                        if (sqlParam.getValue() != null) {
                            InputStream input = (InputStream) sqlParam.getValue();
                            show = input.available() + "(blob-len)";
                        }
                        Logger.debug("[param] : %s", show);
                    } else {
                        Logger.debug("[param] : %s", sqlParam.getValue());
                    }
                }
                if (entityField.isParam()) {
                    // 该字段是listcache的@Param字段
                    DbTypeUtils.setParams(ps, DbType.Varchar, i + 1, sqlParam.getValue());
                } else {
                    DbTypeUtils.setParams(ps, entityField.getDbType(), i + 1, sqlParam.getValue());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static <T> Field getPrimaryField(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        Field primaryField = null;
        for (Field field : fields) {
            field.setAccessible(true);
            Id primaryId = field.getAnnotation(Id.class);
            if (primaryId != null) {
                primaryField = field;
                break;
            }
        }
        return primaryField;
    }

    /** 获取class.field 的解析缓存 */
    protected static Map<String, EntityPacket> packetTable = new ConcurrentHashMap<String, EntityPacket>();

    private static EntityPacket getEntityPair(String fname) throws DalException {
        if (packetTable.containsKey(fname)) {
            return packetTable.get(fname);
        } else {
            EntityPacket pair = EntityManager.getInstance().getClassField(fname);
            if (pair.isAll()) {
                pair.setSelectedSql("*");
                pair.setEntityFields(pair.getEntity().getEntityFields());
            } else {
                pair.setSelectedSql(pair.getEntityField().getColumnName());
                pair.setEntityFields(new ArrayList<EntityField>());
                pair.getEntityFields().add(pair.getEntityField());
            }
            packetTable.put(fname, pair);
            return pair;
        }
    }

    private static String getSqlSelect(String selected) {
        String[] selectedArry = selected.split(",");
        String[] classNameTmp = selectedArry[0].split("\\.");
        if (classNameTmp.length != 2) {
            throw new DalException(
                    "BaseDao.getSqlSelect->the selected param is legal,eg:Class.field ");
        }
        StringBuilder sqlSelected = new StringBuilder();
        if (classNameTmp[1].equals("*")) {
            sqlSelected.append(" * ");
        } else {
            for (int i = 0; i < selectedArry.length; i++) {
                String[] tmp = selectedArry[i].split("\\.");
                if (tmp.length != 2) {
                    throw new DalException(
                            "BaseDao.getSqlSelect->the selected param is legal,eg:Class.field ");
                }
                EntityPacket pair = EntityManager.getInstance().getClassField(tmp[1]);
                if (i == selectedArry.length - 1) {
                    sqlSelected.append(pair.getEntityField().getColumnName());
                } else {
                    sqlSelected.append(pair.getEntityField().getColumnName()).append(",");
                }
            }
        }
        return sqlSelected.toString();
    }

    /**
     * get order by sql from sort
     * 
     * @param sorts
     * @return
     */
    private static String getOrderBy(Sort[] sorts) {
        StringBuilder orderBy = new StringBuilder();
        if (null != sorts) {
            for (int i = 0; i < sorts.length; i++) {
                if (i == sorts.length - 1) {
                    orderBy.append(sorts[i].getSql());
                } else {
                    orderBy.append(sorts[i].getSql()).append(",");
                }

            }
            Logger.debug("BaseDao.getOrderBy,the order by = %s", orderBy.toString());
        }
        return orderBy.toString();
    }

    /**
     * 获取 offset
     * 
     * @param page
     * @param pageSize
     * @return
     */
    private static int getOffset(Integer page, Integer pageSize) {
        int offset = (page == null || page < 1) ? 0 : (page - 1) * getLimit(page, pageSize);
        return offset;
    }

    /**
     * 获取limit
     * 
     * @param page
     * @param pageSize
     * @return
     */
    private static int getLimit(Integer page, Integer pageSize) {
        return pageSize == null || pageSize == 0 ? 20 : pageSize;
    }

    /**
     * 组装字段列表
     * 
     * @param clazz 类
     * @param excludes 排除字段
     * 
     * @return string contains fields names.
     */
    private static String populateFields(Class clazz, List<String> excludes) {

        Field[] fields = clazz.getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return null;
        }

        List<String> nameList = new ArrayList<String>();

        for (Field field : fields) {
            Column c = field.getAnnotation(Column.class);
            if (c != null)
                nameList.add(clazz.getSimpleName() + "." + field.getName());
        }

        if (CollectionUtils.isNotEmpty(excludes)) {
            for (int i = 0; i < excludes.size(); i++) {
                excludes.set(i, clazz.getSimpleName() + "." + excludes.get(i));
            }
            nameList = ListUtils.removeAll(nameList, excludes);
        }

        if (CollectionUtils.isEmpty(nameList)) {
            return null;
        }

        String result = nameList.toString();
        return StringUtils.substring(result, 1, result.length() - 1);
    }

}
