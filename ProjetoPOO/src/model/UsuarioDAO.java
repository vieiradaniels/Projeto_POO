package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utils.Utils;

public class UsuarioDAO {

    public boolean autenticar(String email, String senha) {
        String sql = "SELECT * FROM tbusuario WHERE email = ? and senha = ? and ativo = true";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.closeConnection(stmt, rs);
        }
        return false;
    }

    public boolean adicionarUsuario(Usuario u) {
        String sql = "INSERT  into TBUSUARIO (nome, email, senha, dataNasc, ativo, imagem) VALUES(?,?,?,?,?)";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            byte[] iconBytes = Utils.iconToBytes(u.getImagem());
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setDate(4, new java.sql.Date(u.getDataNasc().getTime()));
            stmt.setBoolean(5, u.isAtivo());
            stmt.setBytes(6,iconBytes);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,
                    "Usuário: " + u.getNome() + " inserido com sucesso!");
            return true;
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null,
                    "ERRO: " + e.getMessage());
        } finally {
            gerenciador.closeConnection(stmt);
        }
        return false;
    }
    
    public boolean alterarUsuario(Usuario u, Long pk) {
        String sql = "UPDATE TBUSUARIO SET nome = ?, email = ?, senha = ?,"
                + " dataNasc = ?, ativo = ?, imagem = ? WHERE pkusuario = ?";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            byte[] iconBytes = Utils.iconToBytes(u.getImagem());
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setDate(4, new java.sql.Date(u.getDataNasc().getTime()));
            stmt.setBoolean(5, u.isAtivo());
            stmt.setBytes(6,iconBytes);
            stmt.setLong(7, pk);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,
                    "Usuário: " + u.getNome() + " inserido com sucesso!");
            return true;
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null,
                    "ERRO: " + e.getMessage());
        } finally {
            gerenciador.closeConnection(stmt);
        }
        return false;
    }
    
    public boolean removerUsuario(int pkUsuario) {
        String sql = "DELETE FROM TBUSUARIO WHERE pkusuario = ?";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);         
            stmt.setLong(1, pkUsuario);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario removido com sucesso!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
        } finally {
            gerenciador.closeConnection(stmt);
        }

        return false;
    }

    public List<Usuario> readForDesc(int tipo, String desc) {
        String sql = "SELECT * FROM tbusuario";
        if (!desc.equals("")) {
            if (tipo == 0 || tipo == 1) {
                sql = sql + " WHERE nome LIKE ?";
            } else {
                sql = sql + " WHERE email LIKE ?";
            }
        }

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            if (!desc.equals("")) {
                if (tipo == 0 || tipo == 2) {
                    stmt.setString(1, desc + "%");
                } else {
                    stmt.setString(1, "%" + desc + "%");
                }
            }

            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setPkUsuario(rs.getLong("pkusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataNasc(rs.getDate("datanasc"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        } finally {
            gerenciador.closeConnection(stmt, rs);
        }
        return usuarios;
    }
    
    public Usuario readForPk(long pk){
        String sql = "SELECT * FROM tbusuario WHERE pkusuario = ?";

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();

        try {
            stmt = con.prepareStatement(sql);
             stmt.setLong(1, pk);

            rs = stmt.executeQuery();

            if(rs.next()){
                usuario.setPkUsuario(rs.getLong("pkusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataNasc(rs.getDate("datanasc"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                
                byte[] bytes = rs.getBytes("imagem");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                BufferedImage imagem = ImageIO.read(bis);
                
                usuario.setImagem(new ImageIcon(imagem));
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        } finally {
            gerenciador.closeConnection(stmt, rs);
        }
        return usuario;
    }
}