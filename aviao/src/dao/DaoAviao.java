/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Aviao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class DaoAviao {
    
     public static boolean inserir(Aviao objeto) {
        String sql = "INSERT INTO aviao (construcao, volume, capacidade, modelo) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getConstrucao()));
            ps.setDouble(2, objeto.getVolume());
            ps.setInt(3, objeto.getCapacidade());
            ps.setString(4, objeto.getModelo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static void main(String[] args) {
        Aviao objeto = new Aviao();
        
        objeto.setConstrucao(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setVolume(1.50);
        objeto.setCapacidade(15);
        objeto.setModelo("Boing 427");
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
      
      
      public static boolean alterar(Aviao objeto) {
        String sql = "UPDATE aviao SET construcao = ?, volume = ?, capacidade = ?, modelo = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            
            ps.setDate(1, Date.valueOf(objeto.getConstrucao()));
            ps.setDouble(2, objeto.getVolume());
            ps.setInt(3, objeto.getCapacidade());
            ps.setString(4, objeto.getModelo());
            ps.setInt(5, objeto.getCodigo());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      
      
        public static boolean excluir(Aviao objeto) {
        String sql = "DELETE FROM aviao WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
        
        
        
        
        public static List<Aviao> consultar() {
        List<Aviao> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT construcao, volume, capacidade, modelo, codigo FROM aviao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aviao objeto = new Aviao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                
                objeto.setConstrucao(rs.getDate("construcao").toLocalDate());
                objeto.setVolume(rs.getDouble("volume"));
                objeto.setCapacidade(rs.getInt("capacidade"));
                objeto.setModelo(rs.getString("modelo"));
                objeto.setCodigo(rs.getInt("codigo"));
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        }
        
        
         public static Aviao consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT construcao, volume, capacidade, modelo, codigo FROM aviao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aviao objeto = new Aviao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                
                objeto.setConstrucao(rs.getDate("construcao").toLocalDate());
                objeto.setVolume(rs.getDouble("volume"));
                objeto.setCapacidade(rs.getInt("capacidade"));
                objeto.setModelo(rs.getString("modelo"));
                objeto.setCodigo(rs.getInt("codigo"));
                
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    
}
