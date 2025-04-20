package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.RegistroDatos;
import org.imouse.vitacorpus.model.RegistroDatos;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class RegistroHiberImpl implements Sql<RegistroDatos>
{
    private static RegistroHiberImpl registroHiber;

    public static RegistroHiberImpl getInstance()
    {
        if(registroHiber==null)
        {
            registroHiber = new RegistroHiberImpl();
        }
        return registroHiber;
    }

    @Override
    public List<RegistroDatos> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<RegistroDatos> list = session
                .createQuery("FROM RegistroDatos", RegistroDatos.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(RegistroDatos registroDatos)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        session.beginTransaction();

        session.persist(registroDatos);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(RegistroDatos registroDatos)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        session.beginTransaction();

        session.merge(registroDatos);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(RegistroDatos registroDatos)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        session.beginTransaction();

        session.remove(registroDatos);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public RegistroDatos findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;

        RegistroDatos registroDatos = session
                .get(RegistroDatos.class, id);

        session.close();
        return registroDatos;
    }
}
