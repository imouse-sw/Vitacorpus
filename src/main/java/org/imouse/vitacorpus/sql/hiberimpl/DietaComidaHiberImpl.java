package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Comida;
import org.imouse.vitacorpus.model.relaciones.DietaComida;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class DietaComidaHiberImpl implements Sql<DietaComida>
{
    private static DietaComidaHiberImpl dietaComidaHiber;

    public static DietaComidaHiberImpl getInstance()
    {
        if(dietaComidaHiber==null)
        {
            dietaComidaHiber = new DietaComidaHiberImpl();
        }
        return dietaComidaHiber;
    }

    @Override
    public List<DietaComida> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        List<DietaComida> list = session
                .createQuery("FROM DietaComida", DietaComida.class)
                .getResultList();

        session.close();
        return list;
    }

    public List<DietaComida> findByDietaId(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<DietaComida> list = session
                .createQuery("FROM DietaComida dc WHERE dc.dieta.id = :idDieta",
                        DietaComida.class)
                .setParameter("idDieta",id)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(DietaComida dietaComida)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");

    }

    @Override
    public boolean update(DietaComida dietaComida)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(DietaComida dietaComida)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");

    }

    @Override
    public DietaComida findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        DietaComida dietaComida = session
                .get(DietaComida.class, id);

        session.close();
        return dietaComida;
    }

    public List<Comida> getComidasByDietaId(Integer idDieta)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        List<Comida> comidas = session
                .createQuery("SELECT dc.comida FROM DietaComida dc WHERE dc.dieta.id = :idDieta", Comida.class)
                .setParameter("idDieta", idDieta)
                .getResultList();

        session.close();
        return comidas;
    }

    public Comida getComidaByDietaId(Integer idDieta)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        Comida comida = session
                .createQuery("SELECT dc.comida FROM DietaComida dc WHERE dc.dieta.id = :idDieta", Comida.class)
                .setParameter("idDieta", idDieta)
                .uniqueResult();

        session.close();
        return comida;
    }
}
