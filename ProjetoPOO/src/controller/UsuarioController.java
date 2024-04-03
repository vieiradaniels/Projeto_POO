package controller;

import model.UsuarioDAO;
import javax.swing.JOptionPane;

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
}
