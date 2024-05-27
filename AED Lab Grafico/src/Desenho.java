import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Desenho extends Canvas {
    private ArrayList<Ramo> lista = new ArrayList<>();
    private int nivelMax;
    private final int tamanhoyjanela;
    private final int tamanhoxjanela;
    private Random r = new Random();
    int aux;
    Desenho(int x, int y){
    super();
    tamanhoxjanela = x;
    tamanhoyjanela = y;

    }

    public void setNivelMax(int nivelMax) {
        this.nivelMax = nivelMax;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.aux = r.nextInt(3);
        int angulo = r.nextInt(0,35);
        this.lista = new ArrayList<>(2^nivelMax);
        arvoreFractal((Graphics2D) g,this.tamanhoxjanela/2, this.tamanhoyjanela-30, 1,angulo,230);
        try {
            desenhaArvore((Graphics2D) g);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void desenhaArvore(Graphics2D g) throws InterruptedException {
            synchronized(Thread.currentThread()){
                for (int i = 0; i<=nivelMax;i++){
                    g.setStroke(new BasicStroke((float) (12/Math.log(i*i +1))));
                    g.setColor(new Color(100 - i*(100/nivelMax), 50+i*(100/nivelMax), 50));
                    for (Ramo ramo : this.lista) {
                        if (ramo.nivel == i) {
                            g.drawLine(ramo.x1, ramo.y1, ramo.x2, ramo.y2);
                        }
                    }
                    Thread.currentThread().wait(300);
                }
            }
    }

    public void arvoreFractal(Graphics2D g, double x, double y, int nivel, int angulo, int tamanho){
        if(nivel<=nivelMax){
            double nx = x - tamanho * Math.sin(Math.toRadians(angulo));
            double ny = y - tamanho * Math.cos(Math.toRadians(angulo));
            this.lista.add(new Ramo((int)x,(int)nx,(int)y,(int)ny,nivel));
            arvoreFractal(g,nx,ny,nivel+1,angulo+(int)r.nextDouble(30,45),(int)(tamanho/r.nextDouble(1,2) ));
            arvoreFractal(g,nx,ny,nivel+1,angulo-(int)r.nextDouble(30,60),(int)(tamanho/r.nextDouble(0.98,2)));
        }
    }
    public class Ramo{
        int x1;
        int x2;
        int y1;
        int y2;
        int nivel;
        public Ramo(int x1, int x2, int y1, int y2, int nivel) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.nivel = nivel;
        }
    }
}
