package apo.taskapp.views;

import apo.taskapp.controllers.TaskController;
import apo.taskapp.interfaces.ICallback;
import apo.taskapp.models.User;
import java.util.ArrayList;
import apo.taskapp.models.Task;
import apo.taskapp.views.cards.TaskContentCard;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    private final User user;
    private final TaskController taskCtrl;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Main(User user) {
        initComponents();
        this.taskCtrl = new TaskController();

        this.user = user;

        setPropertiesUser();
        loadTasks();
    }

    void deleteTask(Task task) {
        taskCtrl.deleteTask(task);
        loadTasks();
    }

    void updateTask(Task task) {
        boolean taskIsUpdated = taskCtrl.updateTask(task);
        if (!taskIsUpdated) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la tarea");
            return;
        }
        loadTasks();
    }

    void setPropertiesUser() {
        lblWelcome.setText("Hola " + user.getNames());

    }

    void loadTasks() {
        pnlTasks.removeAll();
        pnlTasks.updateUI();
        tasks = taskCtrl.getTasks(user);
        TaskContentCard taskContent;
        for (int i = 0; i < tasks.size(); ++i) {
            taskContent = new TaskContentCard(tasks.get(i), (Task task) -> deleteTask(task), (Task task) -> updateTask(task));
            taskContent.setBounds(0, i * 100, 325, 100);
            pnlTasks.add(taskContent);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaContent = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlTasks = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblWelcome.setFont(new java.awt.Font("Maple Mono NF", 0, 24)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(0, 0, 0));
        lblWelcome.setText("Hola");

        txtTitle.setBackground(new java.awt.Color(255, 255, 255));
        txtTitle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Titulo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Maple Mono NF", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        txaContent.setBackground(new java.awt.Color(255, 255, 255));
        txaContent.setColumns(20);
        txaContent.setFont(new java.awt.Font("Maple Mono NF", 0, 14)); // NOI18N
        txaContent.setRows(5);
        txaContent.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contenido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Maple Mono NF", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jScrollPane1.setViewportView(txaContent);

        jScrollPane2.setToolTipText("");
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setMinimumSize(null);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(320, 700));

        pnlTasks.setMinimumSize(new java.awt.Dimension(320, 700));

        javax.swing.GroupLayout pnlTasksLayout = new javax.swing.GroupLayout(pnlTasks);
        pnlTasks.setLayout(pnlTasksLayout);
        pnlTasksLayout.setHorizontalGroup(
            pnlTasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );
        pnlTasksLayout.setVerticalGroup(
            pnlTasksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlTasks);

        btnAdd.setBackground(new java.awt.Color(153, 255, 153));
        btnAdd.setFont(new java.awt.Font("Maple Mono NF", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 0, 0));
        btnAdd.setText("AGREGAR");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnAdd)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lblWelcome))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblWelcome)
                        .addGap(58, 58, 58)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String title = txtTitle.getText().trim();
        String content = txaContent.getText().trim();

        Task task = new Task(title, content);
        taskCtrl.createNewTask(task, user);
        clearFields();
        loadTasks();
    }//GEN-LAST:event_btnAddActionPerformed

    void clearFields() {
        txtTitle.setText("");
        txaContent.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlTasks;
    private javax.swing.JTextArea txaContent;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
