package com.niit.backend.dao.customRepositoryImpl;

import com.niit.backend.dao.customRepository.CustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


public class CustomRepositoryImpl<T> implements CustomRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> persistentClass;

    public CustomRepositoryImpl(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public Page<T> paginator(Pageable pageable, String query, String alias, Map<String, Object> params) {
        //Create query
        Query jpaQuery = getEntityManager().createQuery(query);
        //Lấy ra các bản ghi đầu tiên
        jpaQuery.setFirstResult(pageable.getPageNumber()* pageable.getPageSize());
        //Lấy ra các bản ghi cuối cùng
        jpaQuery.setMaxResults(pageable.getPageSize());
        //Kiểm tra param và set
        if (params != null && params.size() > 0) {
            params.forEach(jpaQuery::setParameter);
        }
        //
        List<T> resultList = jpaQuery.getResultList();
        // Đếm số trang
        String jpaQueryTotalStr = query.replaceFirst("(?i)(select)(.+?)(from)", "select count(" + alias + ".id) from");
        Query jpaQueryTotal = getEntityManager().createQuery(jpaQueryTotalStr);
        if (params != null && params.size() > 0) {
            params.forEach(jpaQueryTotal::setParameter);
        }
        long totalRecord = (long) jpaQueryTotal.getSingleResult();

        return new PageImpl<>(resultList, pageable, totalRecord);
    }
}
