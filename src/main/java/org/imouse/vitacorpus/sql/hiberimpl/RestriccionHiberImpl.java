package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Restriccion;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class RestriccionHiberImpl implements Sql<Restriccion>
{
    private static RestriccionHiberImpl restriccionHiber;

    public static RestriccionHiberImpl getInstance()
    {
        if(restriccionHiber==null)
        {
            restriccionHiber = new RestriccionHiberImpl();
        }
        return restriccionHiber;
    }

    @Override
    public List<Restriccion> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<Restriccion> list = session
                .createQuery("FROM Restriccion", Restriccion.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Restriccion restriccion)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public boolean update(Restriccion restriccion)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public boolean delete(Restriccion restriccion)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public Restriccion findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        Restriccion restriccion = session
                .get(Restriccion.class,id);

        session.close();
        return restriccion;
    }
}
