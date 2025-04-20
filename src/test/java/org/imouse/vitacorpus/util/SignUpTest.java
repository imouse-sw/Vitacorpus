package org.imouse.vitacorpus.util;

import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.funciones.login.SignUp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignUpTest {

    @Test
    void getInstance()
    {
        SignUp signUp = null;
        signUp = SignUp.getInstance();

        assertNotNull(signUp);
    }

    @Test
    void run()
    {
        Usuario usuario = new Usuario();
        for(int i = 0; i < 3; i++)
        {
            usuario.setUsuario("Usuario1");
            usuario.setEmail("email1");
            usuario.setPassword("contraseña");
        }
    }
}