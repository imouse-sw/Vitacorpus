package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Comida;
import org.imouse.vitacorpus.model.Ejercicio;
import org.imouse.vitacorpus.sql.Sql;
import java.util.List;

@NoArgsConstructor
public class EjercicioHiberImpl implements Sql<Ejercicio>
{
    private static EjercicioHiberImpl ejercicioHiber;

    public static EjercicioHiberImpl getInstance()
    {
        if(ejercicioHiber==null)
        {
            ejercicioHiber = new EjercicioHiberImpl();
        }
        return ejercicioHiber;
    }

    @Override
    public List<Ejercicio> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        List<Ejercicio> list = session
                .createQuery("FROM Ejercicio", Ejercicio.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Ejercicio ejercicio)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean update(Ejercicio ejercicio)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(Ejercicio ejercicio)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public Ejercicio findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        Ejercicio ejercicio = session
                .get(Ejercicio.class,id);

        session.close();
        return ejercicio;
    }
}
