
package farmaciaci;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuPrincipal extends javax.swing.JFrame {
    String CedulaCajero;

    public MenuPrincipal() {
        initComponents();
        jDesktopPane1.setBorder(new FondoFarmacia());
        this.setExtendedState(MAXIMIZED_BOTH);
        lblUsuario.setLocation(1100, 650);
        lblMensaje.setLocation(950, 655); 
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        lblUsuario = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnInicio = new javax.swing.JMenu();
        mnItCambiarUsuario = new javax.swing.JMenuItem();
        mnItCerrarSesion = new javax.swing.JMenuItem();
        mnMantenimiento = new javax.swing.JMenu();
        mnItClientes = new javax.swing.JMenuItem();
        mnItUsuarios = new javax.swing.JMenuItem();
        mnItProductos = new javax.swing.JMenuItem();
        miFarmaceutico = new javax.swing.JMenuItem();
        miVisitador = new javax.swing.JMenuItem();
        mnVentas = new javax.swing.JMenu();
        mnItFacturaVenta = new javax.swing.JMenuItem();
        mnItPedidoProductos = new javax.swing.JMenuItem();
        mnCompras = new javax.swing.JMenu();
        mnItPedidoProducto = new javax.swing.JMenuItem();
        miDetallePedido = new javax.swing.JMenuItem();
        mnReportes = new javax.swing.JMenu();
        mnItReporteClientesFarmaciaBustillos = new javax.swing.JMenuItem();
        mnItInventarioProductos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FARMACIA C.I.A");
        setName(""); // NOI18N
        setResizable(false);

        lblUsuario.setForeground(new java.awt.Color(255, 0, 51));
        lblUsuario.setBounds(210, 300, 240, 30);
        jDesktopPane1.add(lblUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblMensaje.setText("Usuario Conectado:");
        lblMensaje.setBounds(300, 210, 140, 20);
        jDesktopPane1.add(lblMensaje, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenuBar1.setName("FARMACIA "); // NOI18N

        mnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Inicio.jpg"))); // NOI18N
        mnInicio.setText("Inicio");

        mnItCambiarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CambiarUsuario1.png"))); // NOI18N
        mnItCambiarUsuario.setText("Cambiar Usuario");
        mnItCambiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItCambiarUsuarioActionPerformed(evt);
            }
        });
        mnInicio.add(mnItCambiarUsuario);

        mnItCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CerrarSesion.png"))); // NOI18N
        mnItCerrarSesion.setText("Cerrar Sesion");
        mnItCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItCerrarSesionActionPerformed(evt);
            }
        });
        mnInicio.add(mnItCerrarSesion);

        jMenuBar1.add(mnInicio);

        mnMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ManteniminetoFarmacia.jpg"))); // NOI18N
        mnMantenimiento.setText("Mantenimineto");

        mnItClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ClientesFarmaciaB.png"))); // NOI18N
        mnItClientes.setText("Clientes");
        mnItClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItClientesActionPerformed(evt);
            }
        });
        mnMantenimiento.add(mnItClientes);

        mnItUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Usuarios1.jpg"))); // NOI18N
        mnItUsuarios.setText("Bodegueros");
        mnItUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItUsuariosActionPerformed(evt);
            }
        });
        mnMantenimiento.add(mnItUsuarios);

        mnItProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Medicamentos.jpg"))); // NOI18N
        mnItProductos.setText("Medicamentos");
        mnItProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItProductosActionPerformed(evt);
            }
        });
        mnMantenimiento.add(mnItProductos);

        miFarmaceutico.setText("Farmaceutico");
        miFarmaceutico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFarmaceuticoActionPerformed(evt);
            }
        });
        mnMantenimiento.add(miFarmaceutico);

        miVisitador.setText("Visitador M.");
        miVisitador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miVisitadorActionPerformed(evt);
            }
        });
        mnMantenimiento.add(miVisitador);

        jMenuBar1.add(mnMantenimiento);

        mnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas.jpg"))); // NOI18N
        mnVentas.setText("Ventas");

        mnItFacturaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Productos.jpg"))); // NOI18N
        mnItFacturaVenta.setText("Ventas");
        mnItFacturaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItFacturaVentaActionPerformed(evt);
            }
        });
        mnVentas.add(mnItFacturaVenta);

        mnItPedidoProductos.setText("Detalle Venta");
        mnItPedidoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItPedidoProductosActionPerformed(evt);
            }
        });
        mnVentas.add(mnItPedidoProductos);

        jMenuBar1.add(mnVentas);

        mnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/PEDIDOPRODUCTOS.jpg"))); // NOI18N
        mnCompras.setText("Compras");

        mnItPedidoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/COMPRA DE PRODUCTOS1.jpg"))); // NOI18N
        mnItPedidoProducto.setText("Pedido Productos");
        mnItPedidoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItPedidoProductoActionPerformed(evt);
            }
        });
        mnCompras.add(mnItPedidoProducto);

        miDetallePedido.setText("Detalle Pedido");
        miDetallePedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDetallePedidoActionPerformed(evt);
            }
        });
        mnCompras.add(miDetallePedido);

        jMenuBar1.add(mnCompras);

        mnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Reportes.jpg"))); // NOI18N
        mnReportes.setText("Reportes");

        mnItReporteClientesFarmaciaBustillos.setText("Reportes Clientes");
        mnItReporteClientesFarmaciaBustillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItReporteClientesFarmaciaBustillosActionPerformed(evt);
            }
        });
        mnReportes.add(mnItReporteClientesFarmaciaBustillos);

        mnItInventarioProductos.setText("Inventario Productos");
        mnItInventarioProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItInventarioProductosActionPerformed(evt);
            }
        });
        mnReportes.add(mnItInventarioProductos);

        jMenuBar1.add(mnReportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void mnItClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItClientesActionPerformed
        try {
            // TODO add your handling code here:
                Clientes clientesescritorio = new Clientes();
                jDesktopPane1.add(clientesescritorio);
                clientesescritorio.setMaximum(true);
                clientesescritorio.show();
        } catch (Exception ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
                            
    
    
}//GEN-LAST:event_mnItClientesActionPerformed

private void mnItUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItUsuariosActionPerformed
//        
    
    BODEGEROS b= new BODEGEROS();
    jDesktopPane1.add(b);
    b.show();
    
    
}//GEN-LAST:event_mnItUsuariosActionPerformed

private void mnItProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItProductosActionPerformed

                Medicamentos m=new Medicamentos();
                jDesktopPane1.add(m);
               // m.setMaximum(true);
                m.show();  
}//GEN-LAST:event_mnItProductosActionPerformed

private void mnItFacturaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItFacturaVentaActionPerformed

    ventas2 v= new ventas2();
    jDesktopPane1.add(v);
    v.show();
    
}//GEN-LAST:event_mnItFacturaVentaActionPerformed

private void mnItPedidoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItPedidoProductoActionPerformed
  
                Pedido p = new Pedido();
                jDesktopPane1.add(p);
                p.show();
}//GEN-LAST:event_mnItPedidoProductoActionPerformed

private void mnItCambiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItCambiarUsuarioActionPerformed

    AccesoUsuarios a = new AccesoUsuarios();
    a.setVisible(true);
    a.setLocation(400, 200);
    this.dispose();    
}//GEN-LAST:event_mnItCambiarUsuarioActionPerformed

private void mnItCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItCerrarSesionActionPerformed

    System.exit(0);    
}//GEN-LAST:event_mnItCerrarSesionActionPerformed

private void miDetallePedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDetallePedidoActionPerformed

               DetallePedido d = new DetallePedido();
                jDesktopPane1.add(d);
//                //revisarcantidad.setMaximum(true);
                d.show();
                d.setLocation(400, 100);
        
}//GEN-LAST:event_miDetallePedidoActionPerformed

private void mnItReporteClientesFarmaciaBustillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItReporteClientesFarmaciaBustillosActionPerformed
// TODO add your handling code here:
                //try {
            // TODO add your handling code here:
//                Clientes clientesescritorio = new Clientes();
//                //jDesktopPane1.add(clientesescritorio);
//                clientesescritorio.Reportes_Clientes();
//                //clientesescritorio.setMaximum(true);
//                clientesescritorio.show();
        //} catch (PropertyVetoException ex) {
           // Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        //}
}//GEN-LAST:event_mnItReporteClientesFarmaciaBustillosActionPerformed

private void mnItInventarioProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItInventarioProductosActionPerformed
// TODO add your handling code here:
//    Productos productosexistentes = new Productos();
//    productosexistentes.Reportes_ProductosExistentes();
}//GEN-LAST:event_mnItInventarioProductosActionPerformed

private void miVisitadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miVisitadorActionPerformed
// TODO add your handling code here:
    VisitadorMedico vm= new VisitadorMedico();
    jDesktopPane1.add(vm);
    vm.show();
    
}//GEN-LAST:event_miVisitadorActionPerformed

private void miFarmaceuticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFarmaceuticoActionPerformed
// TODO add your handling code here:
    FARMACEUTICO f= new FARMACEUTICO();
    jDesktopPane1.add(f);
    f.show();
    //f.dispose();
    
}//GEN-LAST:event_miFarmaceuticoActionPerformed

private void mnItPedidoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItPedidoProductosActionPerformed
// TODO add your handling code here:
    DetalleVenta2 dv= new DetalleVenta2();
    jDesktopPane1.add(dv);
    dv.show();
}//GEN-LAST:event_mnItPedidoProductosActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblMensaje;
    public javax.swing.JLabel lblUsuario;
    public static javax.swing.JMenuItem miDetallePedido;
    public javax.swing.JMenuItem miFarmaceutico;
    private javax.swing.JMenuItem miVisitador;
    public static javax.swing.JMenu mnCompras;
    public static javax.swing.JMenu mnInicio;
    private javax.swing.JMenuItem mnItCambiarUsuario;
    private javax.swing.JMenuItem mnItCerrarSesion;
    private javax.swing.JMenuItem mnItClientes;
    private javax.swing.JMenuItem mnItFacturaVenta;
    private javax.swing.JMenuItem mnItInventarioProductos;
    private javax.swing.JMenuItem mnItPedidoProducto;
    private javax.swing.JMenuItem mnItPedidoProductos;
    public static javax.swing.JMenuItem mnItProductos;
    private javax.swing.JMenuItem mnItReporteClientesFarmaciaBustillos;
    private javax.swing.JMenuItem mnItUsuarios;
    public static javax.swing.JMenu mnMantenimiento;
    public static javax.swing.JMenu mnReportes;
    public static javax.swing.JMenu mnVentas;
    // End of variables declaration//GEN-END:variables
}
