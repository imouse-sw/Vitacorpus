package org.imouse.vitacorpus.funciones.login;

import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.ui.MenuPrincipal;
import org.imouse.vitacorpus.util.ReadUtil;

import java.awt.*;
import java.util.List;

public class Login implements Ejecutable
{
    Ejecutable ejecutable = null;
    private static Login login;
    private boolean flag;

    private Login()
    {
    }

    public static Login getInstance()
    {
        if(login==null)
        {
            login = new Login();
        }
        return login;
    }

    @Override
    public void run()
    {
        System.out.print("> Correo electrónico: ");
        String email = ReadUtil.read();

        System.out.print("> Contraseña: ");
        String password = ReadUtil.read();

        List<Usuario> usuarios = UsuarioHiberImpl.getInstance().findAll();
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password) )
                .findFirst()
                .orElse(null);

        if(usuario!=null)
        {
            System.out.println("✅ Bienvenido, "+usuario.getUsuario()+"!");
            SessionManager.setUsuarioActual(usuario);
            MenuPrincipal.resetInstance();
            MenuPrincipal.getInstance().run();
        }
        else
        {
            System.out.println("❌ Usuario o contraseña incorrectos.");
        }
    }

    @Override
    public void setFlag(boolean flag)
    {
        this.flag = true;
    }
}
