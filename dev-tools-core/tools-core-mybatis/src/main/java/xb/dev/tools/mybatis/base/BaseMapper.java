package xb.dev.tools.mybatis.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: Created by huangxb on 2018-08-02 11:40:14
 * @Description:
 */
@Repository
public class BaseMapper {

        @Resource(name = "sqlSessionTemplate")
        private SqlSessionTemplate sqlSessionTemplate;

        /**
         * 添加对象
         *
         * @param str Mapper
         * @param obj 参数
         * @return
         */
        public int insert(String str, Object obj) {
            return sqlSessionTemplate.insert(str, obj);
        }

        /**
         * 修改对象
         *
         * @param str Mapper
         * @param obj 参数
         * @return
         */
        public int update(String str, Object obj) {
            return sqlSessionTemplate.update(str, obj);
        }

        /**
         * 删除对象
         *
         * @param str Mapper
         * @param obj 参数
         * @return
         */
        public int delete(String str, Object obj) {
            return sqlSessionTemplate.delete(str, obj);
        }

        /**
         * 批量操作（一条SQL）
         *
         * @param <T>
         * @param str Mapper
         * @param objs 参数
         * @return
         */
        public <T> int executByList(String str, List<T> objs) {
            return sqlSessionTemplate.update(str, objs);
        }

        /**
         * 批量操作(多条SQL，一次提交)
         *
         * @param <T>
         * @param str Mapper
         * @param objs 参数
         * @return
         */
        public <T> int executByBatch(String str, List<T> objs) {
            SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
            //批量执行器
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            try {
                int count = 0;
                if (objs != null) {
                    for (int i = 0, size = objs.size(); i < size; i++) {
                        count += sqlSession.update(str, objs.get(i));
                    }
                    sqlSession.flushStatements();
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
                return count;
            } finally {
                sqlSession.close();
            }
        }

        /**
         * 查找对象
         *
         * @param str Mapper
         * @param obj 参数
         * @return
         */
        public <T> T findForObject(String str, Object obj) {
            return sqlSessionTemplate.selectOne(str, obj);
        }

        /**
         * 查找对象
         *
         * @param str Mapper
         * @param obj 参数
         * @param key 指定KEY列名
         * @return
         */
        public <K, V> Map<K, V> findForMap(String str, Object obj, String key) {
            return sqlSessionTemplate.selectMap(str, obj, key);
        }

        /**
         * 查找对象集合
         *
         * @param str Mapper
         * @param obj 参数
         * @return
         */
        public <E> List<E> findForList(String str, Object obj) {
            return sqlSessionTemplate.selectList(str, obj);
        }

        /**
         * 分页查询对象集合
         *
         * @param str      Mapper
         * @param obj      参数
         * @param pageNum  查询页数
         * @param pageSize 每页大小
         * @param count
         * @return
         */
        @SuppressWarnings("unchecked")
        public <E> Page<E> findForPage(String str, Object obj, Integer pageNum, Integer pageSize, boolean count) {
            pageNum = null == pageNum ? 1 : pageNum;
            pageSize = null == pageSize ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize, count);
            return (Page<E>) sqlSessionTemplate.selectList(str, obj);
        }

}
