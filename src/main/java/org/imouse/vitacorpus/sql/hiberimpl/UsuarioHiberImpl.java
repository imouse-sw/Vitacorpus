package org.imouse.vitacorpus.sql.hiberimpl;

import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

public class UsuarioHiberImpl implements Sql<Usuario>
{
    private static UsuarioHiberImpl usuarioHiber;

    private UsuarioHiberImpl()
    {
    }

    public static UsuarioHiberImpl getInstance()
    {
        if(usuarioHiber==null)
        {
            usuarioHiber = new UsuarioHiberImpl();
        }
        return usuarioHiber;
    }

    @Override
    public List<Usuario> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<Usuario> list = session
                .createQuery("FROM Usuario", Usuario.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Usuario usuario)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        session.beginTransaction();

        session.persist(usuario);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Usuario usuario)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        session.beginTransaction();

        session.merge(usuario);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(Usuario usuario)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        session.beginTransaction();

        session.remove(usuario);
        session.getTransaction().commit();

        session.close();
        return true;
    }

    @Override
    public Usuario findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session != null;

        Usuario usuario = session
                .get(Usuario.class, id);

        session.close();
        return usuario;
    }
}
