package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Comida;
import org.imouse.vitacorpus.model.Ejercicio;
import org.imouse.vitacorpus.model.Rutina;
import org.imouse.vitacorpus.model.relaciones.DietaComida;
import org.imouse.vitacorpus.model.relaciones.RutinaEjercicio;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class RutinaEjercicioHiberImpl implements Sql<RutinaEjercicio>
{
    private static RutinaEjercicioHiberImpl rutinaEjercicioHiber;

    public static RutinaEjercicioHiberImpl getInstance()
    {
        if(rutinaEjercicioHiber==null)
        {
            rutinaEjercicioHiber = new RutinaEjercicioHiberImpl();
        }
        return rutinaEjercicioHiber;
    }

    @Override
    public List<RutinaEjercicio> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        List<RutinaEjercicio> list = session
                .createQuery("FROM RutinaEjercicio", RutinaEjercicio.class)
                .getResultList();

        session.close();
        return list;
    }

    public List<RutinaEjercicio> findByRutinaId(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<RutinaEjercicio> list = session
                .createQuery("FROM RutinaEjercicio dc WHERE dc.rutina.id = :idRutina",
                        RutinaEjercicio.class)
                .setParameter("idRutina",id)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(RutinaEjercicio rutinaEjercicio)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");

    }

    @Override
    public boolean update(RutinaEjercicio rutinaEjercicio)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(RutinaEjercicio rutinaEjercicio)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");

    }

    @Override
    public RutinaEjercicio findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        RutinaEjercicio rutinaEjercicio = session
                .get(RutinaEjercicio.class, id);

        session.close();
        return rutinaEjercicio;
    }
    public List<Ejercicio> getEjerciciosByRutinaId(Integer idRutina)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        List<Ejercicio> ejercicios = session
                .createQuery("SELECT dc.ejercicio FROM RutinaEjercicio dc WHERE dc.rutina.id = :idRutina", Ejercicio.class)
                .setParameter("idRutina", idRutina)
                .getResultList();

        session.close();
        return ejercicios;
    }

    public Ejercicio getEjercicioByRutinaId(Integer idRutina)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        Ejercicio ejercicio = session
                .createQuery("SELECT dc.ejercicio FROM RutinaEjercicio dc WHERE dc.rutina.id = :idRutina", Ejercicio.class)
                .setParameter("idRutina", idRutina)
                .uniqueResult();

        session.close();
        return ejercicio;
    }
}
