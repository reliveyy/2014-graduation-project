package com.oplogo.kira.service;

import com.oplogo.kira.AutHibernateManager;
import com.oplogo.kira.Global;
import com.oplogo.kira.exception.AddEntityException;
import com.oplogo.kira.model.Aut;
import com.oplogo.kira.model.AutManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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
    AutHibernateManager autHibernateManager;

    @Autowired
    AutManager autManager;

    public List<Aut> findAll() {
        return (List<Aut>) ht.find("from Aut");
    }

    public void save(Aut entity) throws AddEntityException {
        entity.setLastModifyDate(new Date());
        String java,clazz;
        if(entity.getPackageName().isEmpty()){
            java = entity.getName() + ".java";
            clazz = entity.getName() + ".class";
        }else {
            java = entity.getPackageName().replace(".", "/") + "/" + entity.getName() + ".java";
            clazz = entity.getPackageName().replace(".", "/") + "/" + entity.getName() + ".class";
        }
        entity.setJavaFilePath(Global.getJavaFolder().getAbsolutePath() + "/" + java);
        entity.setJavaClassFilePath(Global.getJavaFolder().getAbsolutePath() + "/" + clazz);
        ht.save(entity);
        autManager.add(entity);
    }

    public void delete(long id) {
        ht.delete(findById(id));
        autManager.remove(id);
    }

    public void update(Aut entity) {
        entity.setLastModifyDate(new Date());
        String java,clazz;
        if(entity.getPackageName().isEmpty()){
            java = entity.getName() + ".java";
            clazz = entity.getName() + ".class";
        }else {
            java = entity.getPackageName().replace(".", "/") + "/" + entity.getName() + ".java";
            clazz = entity.getPackageName().replace(".", "/") + "/" + entity.getName() + ".class";
        }
        entity.setJavaFilePath(Global.getJavaFolder().getAbsolutePath() + "/" + java);
        entity.setJavaClassFilePath(Global.getJavaFolder().getAbsolutePath() + "/" + clazz);
        ht.update(entity);
        autManager.remove(entity.getId());
        autManager.add(findById(entity.getId()));
    }

    public Aut findById(long id) {
        return (Aut) ht.find("from Aut where id = ?", id).get(0);
    }
}
