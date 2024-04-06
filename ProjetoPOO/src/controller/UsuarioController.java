package controller;

import java.util.List;
import model.UsuarioDAO;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public boolean autenticar(String email, String senha) {
        if (usuarioDAO.autenticar(email, senha)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta");
            return false;
        }
    }
    
    public boolean adicionarUsuario(Usuario u){
        if(usuarioDAO.adicionarUsuario(u)){
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
            return false;
        }
    }
    
    public List<Usuario> readForDesc(int tipo, String desc){
        return usuarioDAO.readForDesc(tipo, desc);
    }
}
