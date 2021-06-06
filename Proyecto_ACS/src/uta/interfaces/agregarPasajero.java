/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.interfaces;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class agregarPasajero extends javax.swing.JInternalFrame {

    DefaultTableModel modelo = new DefaultTableModel();

    /**
     * Creates new form IntEstudiantes
     */
    public agregarPasajero() {
        initComponents();
        bloquearBotonesInicio();
        bloquearTextosInicio();
        cargarTablaEstudiantes("");
        cargarDatosModificar();
        jcbxEstCiv.removeAllItems();
        ArrayList <String> lista = new ArrayList <String>();
        lista = llenarComboBox();
        for(int i=0; i < lista.size(); i++){
        jcbxEstCiv.addItem(String.valueOf(lista.get(i)));
        }
    }

    public void cargarDatosModificar(){
        jtblEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(jtblEstudiantes.getSelectedRow() != -1){
                    int fila = jtblEstudiantes.getSelectedRow();
                    jtxtCedula.setText(jtblEstudiantes.getValueAt(fila, 0).toString());
                    jtxtCurso.setText(jtblEstudiantes.getValueAt(fila, 1).toString());
                    jtxtNombre.setText(jtblEstudiantes.getValueAt(fila, 2).toString());
                    jtxtApellido.setText(jtblEstudiantes.getValueAt(fila, 3).toString());
                    jtxtDireccion.setText(jtblEstudiantes.getValueAt(fila, 4).toString());
                    desbloquearTextosInicio();
                    desbloquearBotonesActualizar();
                    jtxtCedula.setEnabled(false);
                    
                }
            }
        });
    }
    
    public void eliminarEstudiante(){
        try {
            //1 Conectarme a la BD.
            conexion cc = new conexion();
            Connection cn = cc.conectar();
            //2 Crear la sentencia SQL.
            String sql = "";
            sql = "DELETE FROM PASAJEROS WHERE PAS_CEDULA = '" + jtxtCedula.getText() + "'";
            //3 Preparar la sentencia SQL.
            PreparedStatement ps = cn.prepareStatement(sql);
            //4 Ejecutar la sentencia SQL.
            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Ok, se eliminó correctamente");
            }
            cargarTablaEstudiantes("");
            bloquearBotonesInicio();
            bloquearTextosInicio();
            limpiarTextos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void modificarEstudiante(){
        try {
            //1 Conectarme a la BD.
            conexion cc = new conexion();
            Connection cn = cc.conectar();
            //2 Crear la sentencia SQL.
            String sql = "";
            sql = "UPDATE PASAJEROS SET PAS_APELLIDO = '" + jtxtApellido.getText()
                    + "', PAS_NOMBRE = '" + jtxtNombre.getText() 
                    + "', PAS_SALIDA = '" + jtxtDireccion.getText()
                    + "', PAS_DESTINO = '" + jcbxEstCiv.getSelectedItem()
                    + "', CUR_ID = '" + jtxtCurso.getText()
                    + "WHERE PAS_CEDULA = '" + jtxtCedula.getText() + "'";
            //3 Preparar la sentencia SQL.
            PreparedStatement ps = cn.prepareStatement(sql);
            //4 Ejecutar la sentencia SQL.
            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Ok, se modificó correctamente");
            }
            cargarTablaEstudiantes("");
            bloquearBotonesInicio();
            bloquearTextosInicio();
            limpiarTextos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void limpiarTextos(){
        jtxtCedula.setText("");
        jtxtCurso.setText("");
        jtxtNombre.setText("");
        jtxtApellido.setText("");
        jtxtDireccion.setText("");
    }
    
    public void cargarTablaEstudiantes(String dato) {
        try {
            String titulos[] = {"CEDULA", "ASIENTO", "NOMBRE", "APELLIDO", "SALIDA",
            "DESTINO", "SEXO"};
            String registros[] = new String[7];
            modelo = new DefaultTableModel(null, titulos);

            conexion cc = new conexion();
            Connection cn = cc.conectar();
            String sql = "";
            sql = "SELECT * FROM PASAJEROS WHERE PAS_CEDULA LIKE '%" + dato + "%'";
            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("PAS_CEDULA");
                registros[1] = rs.getString("CUR_ID");
                registros[2] = rs.getString("PAS_NOMBRE");
                registros[3] = rs.getString("PAS_APELLIDO");
                registros[4] = rs.getString("PAS_SALIDA");
                registros[5] = rs.getString("PAS_DESTINO");
                registros[6] = rs.getString("PAS_SEXO");
                modelo.addRow(registros);
            }
            jtblEstudiantes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(agregarPasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bloquearBotonesInicio() {
        jbtnNuevo.setEnabled(true);
        jbtnGuardar.setEnabled(false);
        jbtnActualizar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
        jbtnCancelar.setEnabled(false);
        jbtnSalir.setEnabled(true);
    }

    public void desbloquearBotonesActualizar() {
        jbtnNuevo.setEnabled(false);
        jbtnGuardar.setEnabled(false);
        jbtnActualizar.setEnabled(true);
        jbtnEliminar.setEnabled(true);
        jbtnCancelar.setEnabled(true);
        jbtnSalir.setEnabled(true);
    }
    
    public void desbloquearBotonesNuevo() {
        jbtnNuevo.setEnabled(false);
        jbtnGuardar.setEnabled(true);
        jbtnActualizar.setEnabled(false);
        jbtnEliminar.setEnabled(false);
        jbtnCancelar.setEnabled(true);
        jbtnSalir.setEnabled(true);
    }

    public void bloquearTextosInicio() {
        jtxtCedula.setEnabled(false);
        jtxtCurso.setEnabled(false);
        jtxtNombre.setEnabled(false);
        jtxtApellido.setEnabled(false);
        jtxtDireccion.setEnabled(false);
        jcbxEstCiv.setEnabled(false);
        jrbtnMasculino.setEnabled(false);
        jrbtnFemenino.setEnabled(false);
    }

    public void desbloquearTextosInicio() {
        jtxtCedula.setEnabled(true);
        jtxtCurso.setEnabled(true);
        jtxtNombre.setEnabled(true);
        jtxtApellido.setEnabled(true);
        jtxtDireccion.setEnabled(true);
        jcbxEstCiv.setEnabled(true);
        jrbtnMasculino.setEnabled(true);
        jrbtnFemenino.setEnabled(true);
        
    }

    public void insertarEstudiante() {
        if (jtxtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la cédula");
            jtxtCedula.requestFocus();
        } else if (jtxtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            jtxtNombre.requestFocus();
        } else if (jtxtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido");
            jtxtApellido.requestFocus();
        } else if (jtxtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la salida");
            jtxtDireccion.requestFocus();
        } else {
            try {
                String cedula, curso, nombre, apellido, direccion, estCiv, sexo;
                cedula = jtxtCedula.getText();
                curso = jtxtCurso.getText();
                nombre = jtxtNombre.getText();
                apellido = jtxtApellido.getText();
                direccion = jtxtDireccion.getText();
                estCiv = jcbxEstCiv.getSelectedItem().toString();
                if(jrbtnMasculino.isSelected()){
                    sexo = "Masculino";
                } else {
                    sexo = "Femenino";
                }
                //1
                conexion cc = new conexion();
                Connection cn = cc.conectar();
                //2 y 3
                String sql = "";
                sql = "INSERT INTO PASAJEROS VALUES (?,?,?,?,?,?,?)";
                //4
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, cedula);
                ps.setString(2, curso);
                ps.setString(3, nombre);
                ps.setString(4, apellido);
                ps.setString(5, direccion);
                ps.setString(6, estCiv);
                ps.setString(7, sexo);
                //5
                int n = ps.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Okey, se insertó correctamente");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: Contáctate con el administrador");
            }
        }
    }

    public ArrayList<String> llenarComboBox(){
        ArrayList<String> lista = new ArrayList<String>();
        try {
            conexion cc = new conexion();
            Connection cn = cc.conectar();
            String sql = "SELECT * FROM DESTINOS";
            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()){
                lista.add(rs.getString("CIU_DES"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(agregarPasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    
     public void interfazSecretariaeliminar() {
    jbtnEliminar.setEnabled(false);
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtngSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellido = new javax.swing.JTextField();
        jtxtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcbxEstCiv = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jrbtnMasculino = new javax.swing.JRadioButton();
        jrbtnFemenino = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jtxtCurso = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnActualizar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jbtnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblEstudiantes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jtxtBuscarCedula = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cédula");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Salida");

        jtxtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCedulaActionPerformed(evt);
            }
        });
        jtxtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtCedulaKeyTyped(evt);
            }
        });

        jtxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreActionPerformed(evt);
            }
        });

        jLabel6.setText("Destino");

        jcbxEstCiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Sexo:");

        jbtngSexo.add(jrbtnMasculino);
        jrbtnMasculino.setText("Masculino");

        jbtngSexo.add(jrbtnFemenino);
        jrbtnFemenino.setText("Femenino");

        jLabel8.setText("Asiento");

        jtxtCurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtCursoKeyTyped(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jrbtnMasculino)
                                    .addComponent(jrbtnFemenino)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jtxtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                        .addComponent(jtxtDireccion)
                                        .addComponent(jtxtNombre))
                                    .addComponent(jtxtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jcbxEstCiv, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 58, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtxtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbxEstCiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jrbtnMasculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbtnFemenino)))
                .addContainerGap())
        );

        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        jbtnActualizar.setText("Actualizar");
        jbtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarActionPerformed(evt);
            }
        });

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jbtnSalir.setText("Salir");
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnSalir)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jtblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtblEstudiantes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel5.setText("Buscar por cédula:");

        jtxtBuscarCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtBuscarCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtBuscarCedulaKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Cooper Black", 2, 24)); // NOI18N
        jLabel9.setText("Agregar Pasajero");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 157, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCedulaActionPerformed

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreActionPerformed

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de que deseas eliminar?", "ATENCIÓN",
                JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_NO_OPTION){
            eliminarEstudiante();
        }else{
            JOptionPane.showMessageDialog(null, "Okay");
        }
        
    }//GEN-LAST:event_jbtnEliminarActionPerformed

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        // TODO add your handling code here:
        insertarEstudiante();
        cargarTablaEstudiantes("");
        bloquearBotonesInicio();
        bloquearTextosInicio();
        limpiarTextos();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        // TODO add your handling code here:
        desbloquearTextosInicio();
        desbloquearBotonesNuevo();
    }//GEN-LAST:event_jbtnNuevoActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        // TODO add your handling code here:
        bloquearBotonesInicio();
        bloquearTextosInicio();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        limpiarTextos();
    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void jtxtBuscarCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtBuscarCedulaKeyReleased
        // TODO add your handling code here:
        cargarTablaEstudiantes(jtxtBuscarCedula.getText());
    }//GEN-LAST:event_jtxtBuscarCedulaKeyReleased

    private void jbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarActionPerformed
        // TODO add your handling code here:
        modificarEstudiante();
    }//GEN-LAST:event_jbtnActualizarActionPerformed

    private void jtxtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCedulaKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (caracter < '0' || caracter > '9') {
            evt.consume();
        } else if (jtxtCedula.getText().length() >= 10) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jtxtCedulaKeyTyped

    private void jtxtCursoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCursoKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (caracter < '0' || caracter > '9') {
            evt.consume();
        } else if (jtxtCurso.getText().length() >= 11) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jtxtCursoKeyTyped

    private void jtxtBuscarCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtBuscarCedulaKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (caracter < '0' || caracter > '9') {
            evt.consume();
        } else if (jtxtBuscarCedula.getText().length() >= 10) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jtxtBuscarCedulaKeyTyped

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
            java.util.logging.Logger.getLogger(agregarPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(agregarPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(agregarPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(agregarPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new agregarPasajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnActualizar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.ButtonGroup jbtngSexo;
    private javax.swing.JComboBox<String> jcbxEstCiv;
    private javax.swing.JRadioButton jrbtnFemenino;
    private javax.swing.JRadioButton jrbtnMasculino;
    private javax.swing.JTable jtblEstudiantes;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtBuscarCedula;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtCurso;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtNombre;
    // End of variables declaration//GEN-END:variables
}

//DEBER : CONSULTAR LOS TIPOS DE CUADROS DE DIÁLOGO QUE EXISTE EN JAVA (EXISTEN 4)