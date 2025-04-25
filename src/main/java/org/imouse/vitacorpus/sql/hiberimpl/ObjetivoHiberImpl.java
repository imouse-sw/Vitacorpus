package org.imouse.vitacorpus.sql.hiberimpl;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.Sql;

import java.util.List;

@NoArgsConstructor
public class ObjetivoHiberImpl implements Sql<Objetivo>
{
    private static ObjetivoHiberImpl objetivoHiber;

    public static ObjetivoHiberImpl getInstance()
    {
        if(objetivoHiber==null)
        {
            objetivoHiber = new ObjetivoHiberImpl();
        }
        return objetivoHiber;
    }

    @Override
    public List<Objetivo> findAll()
    {
        Session session = HibernateUtil.getSession();
        assert session != null;
        List<Objetivo> list = session
                .createQuery("FROM Objetivo", Objetivo.class)
                .getResultList();

        session.close();
        return list;
    }

    @Override
    public boolean save(Objetivo objetivo)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public boolean update(Objetivo objetivo)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public boolean delete(Objetivo objetivo)
    {
        throw new UnsupportedOperationException("Método no utilizado ni implementado.");
    }

    @Override
    public Objetivo findById(Integer id)
    {
        Session session = HibernateUtil.getSession();
        assert session!=null;

        Objetivo objetivo = session
                .get(Objetivo.class,id);

        session.close();
        return objetivo;
    }

}
