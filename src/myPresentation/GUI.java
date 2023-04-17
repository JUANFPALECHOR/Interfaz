package myPresentation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;


public class GUI extends JFrame {
    //atributos
    private JButton myPhoto, myHobby, myExpectations;
    private JPanel containerButtons, containerImage;
    private Listener listener;
    private Title title;
    private JLabel imageLabel;
    private JTextArea expectativesText;

    //Clase FillPainter


    //metodos
    public GUI(){
        initGUI();

        this.setTitle("My Presentation");
        this.setSize(600, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    myExpectations.doClick();
                }
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                myPhoto.requestFocus();
            }
        });

    }
    private void initGUI() {
        //Definir container y Layout del JFrame
        //Crear objetos Escucha y Control
        //Configurar JComponents
        title = new Title("A little more about me", Color.BLACK);
        myPhoto = new JButton("This is me");
        myHobby = new JButton("This is my passion");
        myExpectations = new JButton("I expect to get the best of you");
        containerButtons = new JPanel();
        containerImage = new JPanel();
        listener = new Listener();
        imageLabel = new JLabel();
        expectativesText = new JTextArea(10, 12);

        containerImage.setBorder(BorderFactory.createTitledBorder(null, "Sobre mi", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.SANS_SERIF,Font.PLAIN,20), Color.BLACK));
        containerImage.add(imageLabel);

        containerButtons.add(myPhoto);
        containerButtons.add(myHobby);
        containerButtons.add(myExpectations);

        myPhoto.addActionListener(listener);
        myHobby.addActionListener(listener);
        myExpectations.addActionListener(listener);

        this.add(title, BorderLayout.NORTH);
        this.add(containerButtons, BorderLayout.SOUTH);
        this.add(containerImage, BorderLayout.CENTER);

        containerImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    myPhoto.doClick();
                }
            }
        });
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // handle exception
        }


        myExpectations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                myExpectations.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                myExpectations.setBackground(UIManager.getColor("control"));
            }
        });
        myPhoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                myPhoto.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                myPhoto.setBackground(UIManager.getColor("control"));
            }
        });
        myHobby.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                myHobby.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                myHobby.setBackground(UIManager.getColor("control"));
            }
        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI myGui = new GUI();
            }
        });
    }

    private class Listener implements ActionListener{
        private ImageIcon image;
        @Override
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "Press button");
            imageLabel.setIcon(null);
            containerImage.remove(expectativesText);
            if(e.getSource() == myPhoto){
                this.image = new ImageIcon(getClass().getResource("/resources/yo.jpeg"));
                Image scaledImage = image.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                image = new ImageIcon(scaledImage);
                imageLabel.setIcon(image);
            }else if(e.getSource() == myHobby){
                this.image = new ImageIcon(getClass().getResource("/resources/Salchipapa.jpg"));
                Image scaledImage = image.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                image = new ImageIcon(scaledImage);
                imageLabel.setIcon(image);
            }else if(e.getSource() == myExpectations) {
                expectativesText.setText("Mi expectativa es crecer mucho en conocimiento para lograr grandes cosas  \n" +
                        "");
                expectativesText.setBackground(null);
                expectativesText.setForeground(Color.BLACK);
                containerImage.add(expectativesText);
            }
            GUI.this.requestFocusInWindow();
            revalidate();
            repaint();
        }
    }
}
