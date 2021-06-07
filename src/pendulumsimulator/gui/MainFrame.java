/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulumsimulator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author ANTONIO
 */
public class MainFrame extends JFrame {

    private JPanel container;
    private JLabel jlSelector;
    private JComboBox jcbSelector;
    private JButton jbStart;
    private DisplayPendulum3D displayPend3D;

    public MainFrame(String title) {
        super(title);

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));

        int insetsSize = 8;
        
        //Container
        container = new JPanel();
        add(container);

        GridBagLayout containerLayout = new GridBagLayout();
        container.setLayout(containerLayout);

        //JComboBox Selector 
        jcbSelector = new JComboBox(
                new String[]{"Pendulum 2D", "Pendulum 3D", "Magnetic Pendulum"});
        container.add(jcbSelector);

        GridBagConstraints constrSelector = new GridBagConstraints();
        constrSelector.gridx = 1;
        constrSelector.gridy = 0;
        constrSelector.anchor = GridBagConstraints.WEST;
        constrSelector.insets = new Insets(insetsSize, insetsSize, insetsSize, insetsSize);
        containerLayout.setConstraints(jcbSelector, constrSelector);

        //JLabel Selector
        jlSelector = new JLabel("Select a demo: ");
        container.add(jlSelector);

        GridBagConstraints constrJLSelector = new GridBagConstraints();
        constrJLSelector.gridx = 0;
        constrJLSelector.gridy = 0;
        constrJLSelector.anchor = GridBagConstraints.WEST;
        constrJLSelector.insets = new Insets(insetsSize, insetsSize, insetsSize, insetsSize);
        containerLayout.setConstraints(jlSelector, constrJLSelector);

        //JButton Start
        jbStart = new JButton("Start");
        container.add(jbStart);
        
        GridBagConstraints constrJbStart = new GridBagConstraints();
        constrJbStart.gridx = 2;
        constrJbStart.gridy = 0;
        constrJbStart.anchor = GridBagConstraints.WEST;
        constrJbStart.insets = new Insets(insetsSize, insetsSize, insetsSize, insetsSize);
        containerLayout.setConstraints(jbStart, constrJbStart);
        
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        //Pendulum Display 3D
        displayPend3D = new DisplayPendulum3D();
        container.add(displayPend3D);

        GridBagConstraints constrPend3D = new GridBagConstraints();
        constrPend3D.gridy = 1;
        constrPend3D.weightx = 1.0f;
        constrPend3D.weighty = 1.0f;
        constrPend3D.fill = GridBagConstraints.BOTH;
        constrPend3D.insets = new Insets(insetsSize, insetsSize, insetsSize, insetsSize);
        constrPend3D.gridwidth = 3;
        containerLayout.setConstraints(displayPend3D, constrPend3D);

        displayPend3D.setBorder(new LineBorder(Color.GRAY, 1));
        
        //Pack 
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame("Pendulum Simulator").setVisible(true);
            }
        });
    }

}
