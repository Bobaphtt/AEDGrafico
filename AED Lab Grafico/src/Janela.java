import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame implements ActionListener{
    private Desenho desenho;
    private JTextField tfNivel;
    public Janela(){
        initGui();
    }

    private void initGui(){
        setTitle("AED 1 - Arvore Fractal");
        setSize(1600,950);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        this.add(panel, BorderLayout.NORTH);
        JLabel lbNivel = new JLabel("Complexidade:");
        panel.add(lbNivel);
        tfNivel = new JTextField(20);
        panel.add(tfNivel);
        JButton btNivel = new JButton("Executar");
        btNivel.addActionListener(this);
        panel.add(btNivel);
        desenho = new Desenho((int)this.getSize().getWidth(),(int)this.getSize().getHeight());
        add(desenho, BorderLayout.CENTER);
        this.setVisible(true);
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        int nivelMax = Integer.parseInt(tfNivel.getText());
        desenho.setNivelMax(nivelMax);
        desenho.repaint();
    }

    public static void main(String[] args) {
        new Janela();
    }
}
