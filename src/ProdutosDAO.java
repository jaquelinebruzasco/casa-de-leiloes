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
import java.sql.Statement;
import java.util.ArrayList;


public class ProdutosDAO {
    
    public Connection conn;
    public String url = "jdbc:mysql://localhost:3306/leiloes_db?useSSL=false&useTimezone=true&serverTimezone=UTC";
    public String user = "root";
    public String password = "1234";
    
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
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        String query = "SELECT * FROM produtos";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int valor = rs.getInt("valor");
                String status = rs.getString("status");

                ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
    
    public void venderProduto(int id) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
        ps.setString(1, "Vendido");
        ps.setInt(2, id);
        ps.executeUpdate();
        conn.close();
        ps.close();
    } catch (ClassNotFoundException ex) {
        System.out.println("O driver não está disponível para acesso ou não existe");
    } catch (SQLException ex) {
        System.out.println("DAO Sintaxe de comando invalida: " + ex.getLocalizedMessage());
    }
}
}

