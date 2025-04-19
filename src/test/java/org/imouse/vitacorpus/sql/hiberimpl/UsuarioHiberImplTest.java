package org.imouse.vitacorpus.sql.hiberimpl;

import org.imouse.vitacorpus.model.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioHiberImplTest {

    @Test
    void findAll()
    {
        List<Usuario> list = UsuarioHiberImpl.getInstance().findAll();
        assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    void save()
    {
        Usuario usuario = new Usuario();
        usuario.setUsuario("Usuario1");
        usuario.setEmail("Usuario2");
        usuario.setPassword("Password");

        assertTrue(UsuarioHiberImpl.getInstance().save(usuario));
    }
}