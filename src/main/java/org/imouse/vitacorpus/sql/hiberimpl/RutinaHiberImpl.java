package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.Rutina;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.Sql;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class RutinaHiberImpl implements Sql<Rutina> {
    private static RutinaHiberImpl rutinaHiber;
    private Usuario usuarioActual;

    public static RutinaHiberImpl getInstance() {
        if (rutinaHiber == null) {
            rutinaHiber = new RutinaHiberImpl();
        }
        return rutinaHiber;
    }

    @Override
    public List<Rutina> findAll() {
        Session session = HibernateUtil.getSession();
        assert session != null;

        List<Rutina> list = session
                .createQuery("FROM Rutina", Rutina.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Rutina rutina) {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean update(Rutina rutina) {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(Rutina rutina) {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public Rutina findById(Integer id) {
        Session session = HibernateUtil.getSession();
        assert session != null;

        Rutina rutina = session
                .get(Rutina.class, id);

        session.close();
        return rutina;
    }

    public List<Rutina> findByObjetivo(Objetivo objetivo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Rutina> lista = new ArrayList<>();

        try {
            Query<Rutina> query = session.createQuery(
                    "FROM Rutina WHERE objetivo = :objetivo", Rutina.class);
            query.setParameter("objetivo", objetivo);
            lista = query.getResultList();
        } finally {
            session.close();
        }

        return lista;
    }


}

