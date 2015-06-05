package com.oplogo.kira.service;

import com.oplogo.kira.App;
import com.oplogo.kira.exception.AddEntityException;
import com.oplogo.kira.model.KiraEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yy on 5/24/14.
 */
@Service
@Transactional
public class EntityService {

    @Autowired
    HibernateTemplate ht;

    @Autowired
    App app;

    public List<KiraEntity> findAll() {
        return (List<KiraEntity>) ht.find("from KiraEntity");
    }

    public void save(KiraEntity entity) throws AddEntityException {
        ht.save(entity);
        app.addEntity(entity);
    }

    public void delete(long id) {
        ht.delete(findById(id));
        app.deleteEntity(id);
    }

    public void update(KiraEntity entity) throws AddEntityException{
        app.deleteEntity(entity.getId());
        ht.update(entity);
        app.addEntity(entity);
    }

    public KiraEntity findById(long id) {
        return (KiraEntity) ht.find("from KiraEntity where id = ?", id).get(0);
    }
}
