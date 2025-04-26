package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Preferencia;
import org.imouse.vitacorpus.model.Preferencia;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class PreferenciaHiberImpl implements Sql<Preferencia>
{
    private static PreferenciaHiberImpl preferenciaHiber;

    public static PreferenciaHiberImpl getInstance()
    {
        if(preferenciaHiber==null)
        {
            preferenciaHiber = new PreferenciaHiberImpl();
        }
        return preferenciaHiber;
    }

    @Override
    public List<Preferencia> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<Preferencia> list = session
                .createQuery("FROM Preferencia", Preferencia.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Preferencia preferencia)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public boolean update(Preferencia preferencia)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public boolean delete(Preferencia preferencia)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public Preferencia findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        Preferencia preferencia = session
                .get(Preferencia.class,id);

        session.close();
        return preferencia;
    }
}
