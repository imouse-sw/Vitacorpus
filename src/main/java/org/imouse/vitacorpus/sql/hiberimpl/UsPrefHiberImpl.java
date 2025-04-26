package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.relaciones.UsuarioPreferencia;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class UsPrefHiberImpl implements Sql<UsuarioPreferencia>
{
    private static UsPrefHiberImpl usPrefHiber;

    public static UsPrefHiberImpl getInstance()
    {
        if(usPrefHiber==null)
        {
            usPrefHiber = new UsPrefHiberImpl();
        }
        return usPrefHiber;
    }

    @Override
    public List<UsuarioPreferencia> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        List<UsuarioPreferencia> list = session
                .createQuery("FROM UsuarioPreferencia", UsuarioPreferencia.class)
                .getResultList();

        session.close();
        return list;
    }

    public List<UsuarioPreferencia> findByUsuarioId(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<UsuarioPreferencia> list = session
                .createQuery("FROM UsuarioPreferencia up WHERE up.usuario.id = :idUsuario",
                        UsuarioPreferencia.class)
                .setParameter("idUsuario",id)
                .getResultList();

        session.close();
        return list;
    }

    public UsuarioPreferencia findByUsuarioIdYPreferenciaId(Integer idUsuario, Integer idPreferencia)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        UsuarioPreferencia relacion = session
                .createQuery("FROM UsuarioPreferencia up WHERE up.usuario.id = :idUsuario AND up.preferencia.id = :idPreferencia",
                        UsuarioPreferencia.class)
                .setParameter("idUsuario",idUsuario)
                .setParameter("idPreferencia",idPreferencia)
                .uniqueResult();

        session.close();
        return relacion;
    }

    @Override
    public boolean save(UsuarioPreferencia usuarioPreferencia)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        session.beginTransaction();

        session.persist(usuarioPreferencia);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(UsuarioPreferencia usuarioPreferencia)
    {
        throw new UnsupportedOperationException("Método no implementado ni utilizado.");
    }

    @Override
    public boolean delete(UsuarioPreferencia usuarioPreferencia)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        session.beginTransaction();

        session.remove(usuarioPreferencia);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public UsuarioPreferencia findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;
        UsuarioPreferencia usuarioPreferencia = session
                .get(UsuarioPreferencia.class, id);

        session.close();
        return usuarioPreferencia;
    }
}
