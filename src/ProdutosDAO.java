/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    public Connection conn;
    public String url = "jdbc:mysql://localhost:3306/leiloes_db?useSSL=false&useTimezone=true&serverTimezone=UTC";
    public String user = "root";
    public String password = "1234";
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (String nome, int valor, String status){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
            ps.setString(1, nome);
            ps.setInt(2, valor);
            ps.setString(3, status);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("O driver não está disponível para acesso ou não existe");
        } catch (SQLException ex) {
            System.out.println("DAO Sintaxe de comando invalida: " + ex.getLocalizedMessage());
        }
        
        
      
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

