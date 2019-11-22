/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoAviao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Aviao;
import tela.manutencao.ManutencaoAviao;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class ControladorAviao {

    public static void inserir(ManutencaoAviao man){
        Aviao objeto = new Aviao();
        
        
        objeto.setConstrucao(LocalDate.parse(man.jtfConstrucao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setVolume(Double.parseDouble(man.jtfVolume.getText()));
        objeto.setCapacidade(Integer.parseInt(man.jtfCapacidade.getText()));
        objeto.setModelo(man.jtfModelo.getText());
        
        boolean resultado = DaoAviao.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            
        if (man.listagem != null) {
        atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
            
            
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

   public static void alterar(ManutencaoAviao man){
        Aviao objeto = new Aviao();
        //definir todos os atributos
        objeto.setConstrucao(LocalDate.parse(man.jtfConstrucao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setVolume(Double.parseDouble(man.jtfVolume.getText()));
        objeto.setCapacidade(Integer.parseInt(man.jtfCapacidade.getText()));
        objeto.setModelo(man.jtfModelo.getText());
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        
        boolean resultado = DaoAviao.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            
        if (man.listagem != null) {
        atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
            
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

   public static void excluir(ManutencaoAviao man){
        Aviao objeto = new Aviao();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoAviao.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        if (man.listagem != null) {
        atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
            
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

   
   
   public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        
        //definindo o cabeçalho da tabela
        
        modelo.addColumn("Data Construcao");
        modelo.addColumn("Volume");
        modelo.addColumn("Capacidade");
        modelo.addColumn("Modelo");
        modelo.addColumn("Codigo");
        
        List<Aviao> resultados = DaoAviao.consultar();
        for (Aviao objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getConstrucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getVolume());
            linha.add(objeto.getCapacidade());
            linha.add(objeto.getModelo());
            linha.add(objeto.getCodigo());
            
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
   
   
   
   
  public static void atualizaCampos(ManutencaoAviao man, int pk){ 
        Aviao objeto = DaoAviao.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        
        man.jtfConstrucao.setText(objeto.getConstrucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfVolume.setText(objeto.getVolume().toString());
        man.jtfCapacidade.setText(objeto.getCapacidade().toString());
        man.jtfModelo.setText(objeto.getModelo());
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
