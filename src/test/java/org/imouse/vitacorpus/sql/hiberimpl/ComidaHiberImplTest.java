package org.imouse.vitacorpus.sql.hiberimpl;

import org.imouse.vitacorpus.model.Comida;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComidaHiberImplTest {

    @Test
    void findAll()
    {
        List<Comida> list = ComidaHiberImpl.getInstance().findAll();

        assertNotNull(list);
        list.forEach(comida->
        {
            System.out.println("🍽️ Comidas del lunes: "+comida.getComidasLunes());
        });
    }
}