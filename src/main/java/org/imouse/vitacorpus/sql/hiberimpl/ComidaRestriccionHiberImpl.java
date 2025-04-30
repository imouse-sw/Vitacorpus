package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.relaciones.ComidaRestriccion;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class ComidaRestriccionHiberImpl implements Sql<ComidaRestriccion>
{
    private static ComidaRestriccionHiberImpl comidaRestriccionHiber;

    public static ComidaRestriccionHiberImpl getInstance()
    {
        if(comidaRestriccionHiber==null)
        {
            comidaRestriccionHiber = new ComidaRestriccionHiberImpl();
        }
        return comidaRestriccionHiber;
    }

    @Override
    public List<ComidaRestriccion> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        List<ComidaRestriccion> list = session
                .createQuery("FROM ComidaRestriccion", ComidaRestriccion.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(ComidaRestriccion comidaRestriccion)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean update(ComidaRestriccion comidaRestriccion)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(ComidaRestriccion comidaRestriccion)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public ComidaRestriccion findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        ComidaRestriccion comidaRestriccion = session
                .get(ComidaRestriccion.class, id);

        session.close();
        return comidaRestriccion;
    }

    public List<ComidaRestriccion> findByComidaId(Integer idComida)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        List<ComidaRestriccion> list = session
                .createQuery("FROM ComidaRestriccion cr WHERE cr.comida.id = :idComida", ComidaRestriccion.class)
                .setParameter("idComida", idComida)
                .getResultList();

        return list;
    }
}
