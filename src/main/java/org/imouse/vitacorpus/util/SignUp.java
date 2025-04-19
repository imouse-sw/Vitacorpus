package org.imouse.vitacorpus.util;

import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;

import java.util.List;

public class SignUp implements Ejecutable
{
    private static SignUp signUp;
    private boolean flag;

    private SignUp()
    {
    }

    public static SignUp getInstance()
    {
        if(signUp==null)
        {
            signUp = new SignUp();
        }
        return signUp;
    }

    @Override
    public void run()
    {
        List<Usuario> usuarios = UsuarioHiberImpl.getInstance().findAll();
        Usuario usuario = new Usuario();
        boolean flag1 = true, flag2 = true;

        while(flag1)
        {
            System.out.print("> Elige un nombre de usuario: ");
            String username = ReadUtil.read();
            boolean userExists = usuarios.stream().anyMatch(u->u.getUsuario().equals(username));

            if(userExists)
            {
                System.out.println("❌ El nombre de usuario está en uso.");
            }
            else
            {
                usuario.setUsuario(username);
                flag1 = false;
            }
        }

        while(flag2)
        {
            System.out.print("> Ingresa tu correo electrónico: ");
            String email = ReadUtil.read();
            boolean emailExists = usuarios.stream().anyMatch(u->u.getEmail().equals(email));

            if (emailExists)
            {
                System.out.println("❌ El correo electrónico está en uso.");
            }
            else
            {
                usuario.setEmail(email);
                flag2 = false;
            }
        }

        System.out.print("> Elige una contraseña, ¡no la olvides!: ");
        String password = ReadUtil.read();
        usuario.setPassword(password);

        UsuarioHiberImpl.getInstance().save(usuario);
        System.out.println("✅ Usuario generado con éxito.");
    }

    @Override
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
}
