package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Comida;
import org.imouse.vitacorpus.sql.Sql;
import java.util.List;

@NoArgsConstructor
public class ComidaHiberImpl implements Sql<Comida>
{
    private static ComidaHiberImpl comidaHiber;

    public static ComidaHiberImpl getInstance()
    {
        if(comidaHiber==null)
        {
            comidaHiber = new ComidaHiberImpl();
        }
        return comidaHiber;
    }

    @Override
    public List<Comida> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        List<Comida> list = session
                .createQuery("FROM Comida", Comida.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Comida comida)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean update(Comida comida)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(Comida comida)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public Comida findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        Comida comida = session
                .get(Comida.class,id);

        session.close();
        return comida;
    }
}
