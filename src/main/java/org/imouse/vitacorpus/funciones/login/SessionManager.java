package org.imouse.vitacorpus.funciones.login;

import lombok.Getter;
import lombok.Setter;
import org.imouse.vitacorpus.model.Usuario;

public class SessionManager
{
    @Setter
    @Getter
    private static Usuario usuarioActual;

}
