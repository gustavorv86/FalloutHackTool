
package main;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import passwords.GroupPasswords;
import passwords.Password;

public class MainGUI extends javax.swing.JFrame {
        
    private final DefaultListModel listModel;
    private GroupPasswords keywords;
    
    private void lookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ex) {}
    }
    
    public MainGUI() {
        this.lookAndFeel();
        this.initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icon.png")));
        this.listModel = new DefaultListModel();
        this.jListKeywords.setModel(listModel);
    }
    
    private ArrayList<String> getTextAreaKeys(){
        String content = jTextAreaKeywords.getText().trim();
        if(content.isEmpty()) {
            return new ArrayList();
        }
        content = content.replace("\r", " ");
        content = content.replace("\n", " ");
        content = content.replace("\t", " ");
        String[] keys = content.split("\\s+");
        return new ArrayList(Arrays.asList(keys));
    }
    
    private void initKeywordsList(){
        jTextFieldKeyword.setForeground(Color.black);
        listModel.clear();
        for (Password keyword : keywords) {
            listModel.addElement(keyword.toString());
        }
        if(!keywords.isEmpty()) {
            jListKeywords.setSelectedIndex(0);
        }
    }
    
    private void listValueChanged(){
        int index = jListKeywords.getSelectedIndex();
        if(index >= 0) {
            Password key = keywords.get(index);
            jTextFieldKeyword.setText(key.keyword);
            ArrayList<Integer> correctChars = key.correctChars;
            if(correctChars.isEmpty()) {
                jTextFieldKeyword.setForeground(Color.GREEN);
                jSpinnerCorrectChars.setModel(new SpinnerNumberModel(0, 0, 0, 0));
            } else {
                jTextFieldKeyword.setForeground(Color.BLACK);
                int minVal = correctChars.get(0);
                int maxVal = correctChars.get(correctChars.size()-1);
                jSpinnerCorrectChars.setModel(new SpinnerNumberModel(minVal, minVal, maxVal, 1));
            }
        }
    }
    
    private void hackButton(){
        try {
            if(listModel.isEmpty()) {
                keywords = new GroupPasswords(getTextAreaKeys());
                initKeywordsList();
            } else {
                int index = jListKeywords.getSelectedIndex();
                if(index >= 0 && keywords.size() >= 2) {
                    int correctChars = (Integer) jSpinnerCorrectChars.getValue();
                    ArrayList<String> keys = keywords.similar(index, correctChars);
                    keywords = new GroupPasswords(keys);
                    initKeywordsList();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void resetButton(){
        listModel.clear();
        jTextFieldKeyword.setText("");
        jSpinnerCorrectChars.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jPanelKeywords = new javax.swing.JPanel();
        jLabelAddPasswords = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaKeywords = new javax.swing.JTextArea();
        jPanelHack = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListKeywords = new javax.swing.JList();
        jLabelSelectPassword = new javax.swing.JLabel();
        jButtonReset = new javax.swing.JButton();
        jLabelImage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldKeyword = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSpinnerCorrectChars = new javax.swing.JSpinner();
        jButtonHack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FALLOUT HACK TOOL");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanelMain.setBackground(new java.awt.Color(255, 255, 255));

        jPanelKeywords.setBackground(new java.awt.Color(255, 255, 255));
        jPanelKeywords.setBorder(javax.swing.BorderFactory.createTitledBorder("Passwords"));

        jLabelAddPasswords.setText("Add passwords");

        jTextAreaKeywords.setColumns(20);
        jTextAreaKeywords.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaKeywords.setRows(5);
        jScrollPane1.setViewportView(jTextAreaKeywords);

        javax.swing.GroupLayout jPanelKeywordsLayout = new javax.swing.GroupLayout(jPanelKeywords);
        jPanelKeywords.setLayout(jPanelKeywordsLayout);
        jPanelKeywordsLayout.setHorizontalGroup(
            jPanelKeywordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKeywordsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelKeywordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAddPasswords))
                .addContainerGap())
        );
        jPanelKeywordsLayout.setVerticalGroup(
            jPanelKeywordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKeywordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelAddPasswords)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanelHack.setBackground(new java.awt.Color(255, 255, 255));
        jPanelHack.setBorder(javax.swing.BorderFactory.createTitledBorder("Hack"));

        jListKeywords.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jListKeywords.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListKeywordsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListKeywords);

        jLabelSelectPassword.setText("Select password");

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jLabelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fallout-shelter.jpg"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Password:");

        jTextFieldKeyword.setEditable(false);

        jLabel2.setText("Likeness:");

        jSpinnerCorrectChars.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));

        jButtonHack.setText("Hack");
        jButtonHack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldKeyword))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerCorrectChars, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonHack)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinnerCorrectChars, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonHack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelHackLayout = new javax.swing.GroupLayout(jPanelHack);
        jPanelHack.setLayout(jPanelHackLayout);
        jPanelHackLayout.setHorizontalGroup(
            jPanelHackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHackLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelHackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonReset, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelImage, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabelSelectPassword))
                .addContainerGap())
        );
        jPanelHackLayout.setVerticalGroup(
            jPanelHackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSelectPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanelHackLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jButtonReset)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelKeywords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelHack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelKeywords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelHack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListKeywordsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListKeywordsValueChanged
        listValueChanged();
    }//GEN-LAST:event_jListKeywordsValueChanged

    private void jButtonHackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHackActionPerformed
        hackButton();
    }//GEN-LAST:event_jButtonHackActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        resetButton();
    }//GEN-LAST:event_jButtonResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHack;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAddPasswords;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JLabel jLabelSelectPassword;
    private javax.swing.JList jListKeywords;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelHack;
    private javax.swing.JPanel jPanelKeywords;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerCorrectChars;
    private javax.swing.JTextArea jTextAreaKeywords;
    private javax.swing.JTextField jTextFieldKeyword;
    // End of variables declaration//GEN-END:variables
}
