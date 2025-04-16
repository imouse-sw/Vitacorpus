package org.imouse.vitacorpus.inicio;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.imouse.vitacorpus.hibernate.HibernateUtil;
import org.imouse.vitacorpus.model.RegistroDatos;

public class Inicio
{
    public static void main(String[] args)
    {
        RegistroDatos registro = new RegistroDatos();
        registro.setNumero(1);
        registro.setEdad(29);
        registro.setPeso(80.9);
        registro.setEstatura(1.65);

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try
        {
            transaction = session.beginTransaction();

            session.persist(registro);

            transaction.commit();

            System.out.println("Registro guardado con id: "+registro.getId());
            System.out.println("IMC calculado: "+registro.getImc());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            session.close();
        }
    }
}
