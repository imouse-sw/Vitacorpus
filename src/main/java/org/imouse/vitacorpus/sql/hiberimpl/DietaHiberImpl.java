package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Dieta;
import org.imouse.vitacorpus.sql.Sql;
import java.util.List;

@NoArgsConstructor
public class DietaHiberImpl implements Sql<Dieta>
{
    private static DietaHiberImpl dietaHiber;

    public static DietaHiberImpl getInstance()
    {
        if(dietaHiber==null)
        {
            dietaHiber = new DietaHiberImpl();
        }
        return dietaHiber;
    }

    @Override
    public List<Dieta> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        List<Dieta> list = session
                .createQuery("FROM Dieta", Dieta.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Dieta dieta)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean update(Dieta dieta)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(Dieta dieta)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public Dieta findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        Dieta dieta = session
                .get(Dieta.class,id);

        session.close();
        return dieta;
    }

    public List<Dieta> findByObjetivoId(int idObjetivo)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;

        List<Dieta> dietas = session
                .createQuery("FROM Dieta WHERE objetivo.id = :idDieta", Dieta.class) // id refiere al idObjetivo
                .setParameter("idDieta", idObjetivo)
                .getResultList();

        session.close();
        return dietas;
    }
}
