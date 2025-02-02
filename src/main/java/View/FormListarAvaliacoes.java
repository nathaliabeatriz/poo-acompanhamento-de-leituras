package View;

import Controller.EstanteController;
import Controller.AvaliacaoController;
import Controller.LeitorController;
import Controller.LivroController;
import Model.Objetos.Estante;
import Model.Objetos.Avaliacao;
import Model.Objetos.Leitor;
import Model.Objetos.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author natha
 */
public class FormListarAvaliacoes extends javax.swing.JFrame {
    
    AvaliacaoController avaController = new AvaliacaoController();
    LeitorController leitorController = new LeitorController();
    LivroController livroController = new LivroController();
    EstanteController estanteController = new EstanteController();
    List<Leitor>lstLeitores;
    List<Livro>lstLivros;
    
    private void preencherComboLeitor(){
        String nome = txtConsLeitor.getText();
        if(!nome.isEmpty()){
            lstLeitores = leitorController.getLeitores(nome);
            cmbLeitores.removeAllItems();
            lstLeitores.forEach(itemLeitor -> {
                cmbLeitores.addItem(itemLeitor.getNome() + " | " + itemLeitor.getId());
            });
        } else{
            cmbLeitores.removeAllItems();
            if(lstLeitores != null) lstLeitores.clear();
        }
    }
    
    private void preencherComboLivro(){
        String nome = txtConsLivro.getText();
        if(!nome.isEmpty()){
            lstLivros = livroController.getLivros(nome);
            cmbLivros.removeAllItems();
            lstLivros.forEach(itemLivro -> {
                cmbLivros.addItem(itemLivro.getNome() + " | " + itemLivro.getIdLivro());
            });
        } else{
            cmbLivros.removeAllItems();
            if(lstLivros != null) lstLivros.clear();
        }
    }
    
    private void pesquisar(){
        List<Avaliacao>lstAvaliacoes = new ArrayList<>();
        Leitor leitor = new Leitor();
        Livro livro = new Livro();
        
        if(lstLeitores != null && !lstLeitores.isEmpty()){
            int index = cmbLeitores.getSelectedIndex();
            leitor = lstLeitores.get(index);
        }
        if(lstLivros != null && !lstLivros.isEmpty()){
            int index = cmbLivros.getSelectedIndex();
            livro = lstLivros.get(index);
        }
        
        if(livro.getNome() != null && leitor.getNome() != null){
            Estante estante = estanteController.getEstantes(leitor, livro);
            lstAvaliacoes = avaController.getAvaliacoes(estante);
            preencherTabela(lstAvaliacoes);
        }
        else if(livro.getNome() == null && leitor.getNome() != null){
            List<Estante>lstEstante = estanteController.getEstantes(leitor);
            List<Avaliacao>lstAuxAva;
            for(Estante itemEstante : lstEstante){
                lstAuxAva = avaController.getAvaliacoes(itemEstante);
                for(Avaliacao itemAva : lstAuxAva){
                    lstAvaliacoes.add(itemAva);
                }
            }
            preencherTabela(lstAvaliacoes);
        }
        else if(livro.getNome() != null && leitor.getNome() == null){
            List<Estante>lstEstante = estanteController.getEstantes(livro);
            List<Avaliacao>lstAuxAva;
            for(Estante itemEstante : lstEstante){
                lstAuxAva = avaController.getAvaliacoes(itemEstante);
                for(Avaliacao itemAva : lstAuxAva){
                    lstAvaliacoes.add(itemAva);
                }
            }
            preencherTabela(lstAvaliacoes);
        }
        else{
            lstAvaliacoes = avaController.getAvaliacoes();
            preencherTabela(lstAvaliacoes);
        }
    }
    
    private void preencherTabela(List<Avaliacao> lstP){
        DefaultTableModel tabelaAvaliacoes = (DefaultTableModel)tabAvaliacoes.getModel();
        tabAvaliacoes.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabAvaliacoes.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabAvaliacoes.getColumnModel().getColumn(2).setPreferredWidth(20);
        tabAvaliacoes.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabAvaliacoes.getColumnModel().getColumn(4).setPreferredWidth(200);
        tabelaAvaliacoes.setNumRows(0);
        
        lstP.forEach(itemAva ->{
            tabelaAvaliacoes.addRow(new Object[]{itemAva.getEstante().getLeitor().getNome(), itemAva.getEstante().getLivro().getNome(), itemAva.getEstrelas(), itemAva.getTitulo(), itemAva.getResenha()});
        });
    }
    
    public FormListarAvaliacoes() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        nbls_BtnSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nbls_BtnConsultar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtConsLeitor = new javax.swing.JTextField();
        cmbLeitores = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtConsLivro = new javax.swing.JTextField();
        cmbLivros = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabAvaliacoes = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nbls_BtnSair.setText("Sair");
        nbls_BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbls_BtnSairActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("AVALIAÇÕES ");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Pesquisar históricos:");

        nbls_BtnConsultar.setText("Consultar");
        nbls_BtnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbls_BtnConsultarActionPerformed(evt);
            }
        });

        jLabel7.setText("Leitor:");

        txtConsLeitor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConsLeitorCaretUpdate(evt);
            }
        });

        cmbLeitores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Livro:");

        txtConsLivro.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConsLivroCaretUpdate(evt);
            }
        });

        cmbLivros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(nbls_BtnConsultar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbLivros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtConsLivro)
                            .addComponent(cmbLeitores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtConsLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtConsLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbLeitores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtConsLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbLivros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(nbls_BtnConsultar)
                .addGap(14, 14, 14))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabAvaliacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, "", "", null},
                {null, null, "", "", null},
                {null, null, "", "", null}
            },
            new String [] {
                "Leitor", "Livro", "Estrelas", "Título", "Resenha"
            }
        ));
        tabAvaliacoes.setGridColor(new java.awt.Color(102, 102, 102));
        tabAvaliacoes.setShowGrid(true);
        jScrollPane2.setViewportView(tabAvaliacoes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nbls_BtnSair)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nbls_BtnSair)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nbls_BtnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbls_BtnConsultarActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_nbls_BtnConsultarActionPerformed

    private void nbls_BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbls_BtnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_nbls_BtnSairActionPerformed

    private void txtConsLeitorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsLeitorCaretUpdate
        // TODO add your handling code here:
        preencherComboLeitor();
        pesquisar();
    }//GEN-LAST:event_txtConsLeitorCaretUpdate

    private void txtConsLivroCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsLivroCaretUpdate
        // TODO add your handling code here:
        preencherComboLivro();
        pesquisar();
    }//GEN-LAST:event_txtConsLivroCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormListarAvaliacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormListarAvaliacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormListarAvaliacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormListarAvaliacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormListarAvaliacoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbLeitores;
    private javax.swing.JComboBox<String> cmbLivros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton nbls_BtnConsultar;
    private javax.swing.JButton nbls_BtnSair;
    private javax.swing.JTable tabAvaliacoes;
    private javax.swing.JTextField txtConsLeitor;
    private javax.swing.JTextField txtConsLivro;
    // End of variables declaration//GEN-END:variables
}
