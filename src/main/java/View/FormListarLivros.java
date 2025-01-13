package View;

import Controller.AutorController;
import Controller.EditoraController;
import Controller.GeneroController;
import Controller.LivroController;
import Model.Objetos.Autor;
import Model.Objetos.Editora;
import Model.Objetos.Genero;
import Model.Objetos.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author natha
 */
public class FormListarLivros extends javax.swing.JFrame {
    
    AutorController autorController = new AutorController();
    LivroController livroController = new LivroController();
    EditoraController editoraController = new EditoraController();
    GeneroController generoController = new GeneroController();
    List<Autor>lstAutores;
    List<Editora>lstEditoras;
    List<Genero>lstGeneros;
    
    private void preencherComboAutor(){
        String nome = txtConsAutor.getText();
        if(!nome.isEmpty()){
            lstAutores = autorController.getAutores(nome);
            cmbAutores.removeAllItems();
            lstAutores.forEach(itemAutor -> {
                cmbAutores.addItem(itemAutor.getNome() + " | " + itemAutor.getId());
            });
        } else{
            cmbAutores.removeAllItems();
            if(lstAutores != null) lstAutores.clear();
        }
    }
    
    private void preencherComboEditora(){
        String nome = txtConsEditora.getText();
        if(!nome.isEmpty()){
            lstEditoras = editoraController.getEditoras(nome);
            cmbEditoras.removeAllItems();
            lstEditoras.forEach(itemEditora -> {
                cmbEditoras.addItem(itemEditora.getNome() + " | " + itemEditora.getIdEditora());
            });
        } else{
            cmbEditoras.removeAllItems();
            if(lstEditoras != null) lstEditoras.clear();
        }
    }
    
    private void preencherComboGenero(){
        String nome = txtConsGenero.getText();
        if(!nome.isEmpty()){
            lstGeneros = generoController.getGeneros(nome);
            cmbGeneros.removeAllItems();
            lstGeneros.forEach(itemGenero -> {
                cmbGeneros.addItem(itemGenero.getDescricao()+ " | " + itemGenero.getIdGenero());
            });
        } else{
            cmbGeneros.removeAllItems();
            if(lstGeneros != null) lstGeneros.clear();
        }
    }
    
    private void pesquisar(){
        List<Livro>lstLivros = new ArrayList<>();
        Autor autor = new Autor();
        Editora editora = new Editora();
        Genero genero = new Genero();
        
        String titulo = txtConsTitulo.getText().trim();
        
        if(lstAutores != null && !lstAutores.isEmpty()){
            int index = cmbAutores.getSelectedIndex();
            autor = lstAutores.get(index);
        }
        if(lstEditoras != null && !lstEditoras.isEmpty()){
            int index = cmbEditoras.getSelectedIndex();
            editora = lstEditoras.get(index);
        }
        if(lstGeneros != null && !lstGeneros.isEmpty()){
            int index = cmbGeneros.getSelectedIndex();
            genero = lstGeneros.get(index);
        }
        
        if(!titulo.isEmpty() && autor.getNome() != null && editora.getNome() != null && genero.getDescricao() != null){
            lstLivros = livroController.getLivros(titulo, autor, editora, genero);
            preencherTabela(lstLivros);
        }
        else if(!titulo.isEmpty() && autor.getNome() == null && editora.getNome() == null && genero.getDescricao() == null){
            lstLivros = livroController.getLivros(titulo);
            preencherTabela(lstLivros);
        }
        else if(titulo.isEmpty() && autor.getNome() != null && editora.getNome() == null && genero.getDescricao() == null){
            lstLivros = livroController.getLivros(autor);
            preencherTabela(lstLivros);
        }
        else if(titulo.isEmpty() && autor.getNome() == null && editora.getNome() != null && genero.getDescricao() == null){
            lstLivros = livroController.getLivros(editora);
            preencherTabela(lstLivros);
        }
        else if(titulo.isEmpty() && autor.getNome() == null && editora.getNome() == null && genero.getDescricao() != null){
            lstLivros = livroController.getLivros(genero);
            preencherTabela(lstLivros);
        }
        else{
            lstLivros = livroController.getLivros();
            preencherTabela(lstLivros);
        }
    }
    
    private void preencherTabela(List<Livro> lstP){
        DefaultTableModel tabelaLivros = (DefaultTableModel)tabLivros.getModel();
        tabLivros.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabLivros.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabLivros.getColumnModel().getColumn(2).setPreferredWidth(60);
        tabLivros.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabLivros.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabelaLivros.setNumRows(0);
        
        lstP.forEach(itemLivro ->{
            tabelaLivros.addRow(new Object[]{itemLivro.getNome(), itemLivro.getAutor().getNome(), itemLivro.getMediaAvaliacao(), itemLivro.getEditora().getNome(), itemLivro.getGenero().getDescricao()});
        });
    }
    
    public FormListarLivros() {
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
        txtConsAutor = new javax.swing.JTextField();
        cmbAutores = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtConsEditora = new javax.swing.JTextField();
        cmbEditoras = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtConsGenero = new javax.swing.JTextField();
        cmbGeneros = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtConsTitulo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabLivros = new javax.swing.JTable();

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
        jLabel1.setText("HISTÓRICO DE LEITURA");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Pesquisar históricos:");

        nbls_BtnConsultar.setText("Consultar");
        nbls_BtnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbls_BtnConsultarActionPerformed(evt);
            }
        });

        jLabel7.setText("Autor:");

        txtConsAutor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConsAutorCaretUpdate(evt);
            }
        });

        cmbAutores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Editora:");

        txtConsEditora.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConsEditoraCaretUpdate(evt);
            }
        });

        cmbEditoras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Gênero:");

        txtConsGenero.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConsGeneroCaretUpdate(evt);
            }
        });

        cmbGeneros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Título:");

        txtConsTitulo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConsTituloCaretUpdate(evt);
            }
        });

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
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbEditoras, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtConsEditora)
                                    .addComponent(cmbAutores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtConsAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbGeneros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtConsGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtConsTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(226, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtConsTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtConsAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAutores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtConsEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEditoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtConsGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nbls_BtnConsultar)
                .addGap(14, 14, 14))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "", "", null, null},
                {null, "", "", null, null},
                {null, "", "", null, null}
            },
            new String [] {
                "Título", "Autor(a)", "Média de avaliações", "Editora", "Gênero"
            }
        ));
        tabLivros.setGridColor(new java.awt.Color(102, 102, 102));
        tabLivros.setShowGrid(true);
        jScrollPane2.setViewportView(tabLivros);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nbls_BtnSair)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nbls_BtnSair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
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

    private void txtConsAutorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsAutorCaretUpdate
        // TODO add your handling code here:
        preencherComboAutor();
        pesquisar();
    }//GEN-LAST:event_txtConsAutorCaretUpdate

    private void txtConsEditoraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsEditoraCaretUpdate
        // TODO add your handling code here:
        preencherComboEditora();
    }//GEN-LAST:event_txtConsEditoraCaretUpdate

    private void txtConsGeneroCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsGeneroCaretUpdate
        // TODO add your handling code here:
        preencherComboGenero();
    }//GEN-LAST:event_txtConsGeneroCaretUpdate

    private void txtConsTituloCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsTituloCaretUpdate
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtConsTituloCaretUpdate

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
            java.util.logging.Logger.getLogger(FormListarLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormListarLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormListarLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormListarLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FormListarLivros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbAutores;
    private javax.swing.JComboBox<String> cmbEditoras;
    private javax.swing.JComboBox<String> cmbGeneros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton nbls_BtnConsultar;
    private javax.swing.JButton nbls_BtnSair;
    private javax.swing.JTable tabLivros;
    private javax.swing.JTextField txtConsAutor;
    private javax.swing.JTextField txtConsEditora;
    private javax.swing.JTextField txtConsGenero;
    private javax.swing.JTextField txtConsTitulo;
    // End of variables declaration//GEN-END:variables
}
