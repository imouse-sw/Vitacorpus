package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.relaciones.UsuarioRestriccion;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class UsuarioRestriccionHiberImpl implements Sql<UsuarioRestriccion>
{
    private static UsuarioRestriccionHiberImpl usuarioRestriccionHiber;

    public static UsuarioRestriccionHiberImpl getInstance()
    {
        if(usuarioRestriccionHiber==null)
        {
            usuarioRestriccionHiber = new UsuarioRestriccionHiberImpl();
        }
        return usuarioRestriccionHiber;
    }

    @Override
    public List<UsuarioRestriccion> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        List<UsuarioRestriccion> list = session
                .createQuery("FROM UsuarioRestriccion", UsuarioRestriccion.class)
                .getResultList();

        session.close();
        return list;
    }

    public List<UsuarioRestriccion> findByUsuarioId(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<UsuarioRestriccion> list = session
                .createQuery("FROM UsuarioRestriccion ur WHERE ur.usuario.id = :idUsuario",
                        UsuarioRestriccion.class)
                .setParameter("idUsuario",id)
                .getResultList();

        session.close();
        return list;
    }

    public UsuarioRestriccion findByUsuarioIdYRestriccionId(Integer idUsuario, Integer idRestriccion)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        UsuarioRestriccion relacion = session
                .createQuery("FROM UsuarioRestriccion ur WHERE ur.usuario.id = :idUsuario AND ur.restriccion.id = :idRestriccion",
                        UsuarioRestriccion.class)
                .setParameter("idUsuario",idUsuario)
                .setParameter("idRestriccion",idRestriccion)
                .uniqueResult();

        session.close();
        return relacion;
    }

    @Override
    public boolean save(UsuarioRestriccion usuarioRestriccion)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        session.beginTransaction();

        session.persist(usuarioRestriccion);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(UsuarioRestriccion usuarioRestriccion)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(UsuarioRestriccion usuarioRestriccion)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        session.beginTransaction();

        session.remove(usuarioRestriccion);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public UsuarioRestriccion findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        UsuarioRestriccion usuarioRestriccion = session
                .get(UsuarioRestriccion.class, id);

        session.close();
        return usuarioRestriccion;
    }
}
