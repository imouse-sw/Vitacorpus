package org.imouse.vitacorpus.sql.hiberimpl;

import org.imouse.vitacorpus.model.relaciones.DietaComida;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DietaComidaHiberImplTest
{

    @Test
    void findAll()
    {
        List<DietaComida> list = DietaComidaHiberImpl.getInstance().findAll();

        assertNotNull(list);
        if(list.isEmpty())
        {
            System.out.println("jeje ta vacia jeje");
        }
    }
}