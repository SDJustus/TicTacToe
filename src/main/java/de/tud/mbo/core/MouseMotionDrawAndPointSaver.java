package de.tud.mbo.core;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class MouseMotionDrawAndPointSaver extends JFrame {
    private int mX, mY;
    private ArrayList<Integer> seqXAsArrayList, seqYAsArrayList;
    private ArrayList<Float> angleForFor;
    private boolean deleteLines;
    private boolean saveTemplates = false;
    private Point startPoint;
    private Point previousPoint;
    private Point currentPoint;
    int tempcounter = 0;
    float[] angleArray;
    private List<Observer> observers = new ArrayList<>();

    public MouseMotionDrawAndPointSaver() {
        setTitle("TemplateCounter"+tempcounter);
        JPanel drawPanel = new JPanel();
        drawPanel.addMouseMotionListener(new MouseAdapter() {

            public void mouseMoved(MouseEvent me) {
            }

            public void mouseDragged(MouseEvent me) {
                mX = (int) me.getPoint().getX();
                mY = (int) me.getPoint().getY();
                seqXAsArrayList.add(mX);
                seqYAsArrayList.add(mY);
                repaint();
            }
        });
        drawPanel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                seqXAsArrayList = new ArrayList<>();
                seqYAsArrayList = new ArrayList<>();
            }

            public void mouseReleased(MouseEvent e) {
                deleteLines = true;
                repaint();
                for(int i = 0; i<seqXAsArrayList.size(); i++){
                    if(i==0) {
                        angleForFor = new ArrayList<>();
                        startPoint = e.getPoint();
                    } else if(i==1){
                        previousPoint = startPoint;
                        currentPoint = new Point(seqXAsArrayList.get(i), seqYAsArrayList.get(i));
                    } else {
                        previousPoint = currentPoint;
                        currentPoint = new Point(seqXAsArrayList.get(i), seqYAsArrayList.get(i));
                        angleForFor.add(computeAngleForDTW(currentPoint, previousPoint, startPoint));
                    }
                }
                angleArray = new float[angleForFor.size()];
                int i = 0;
                for(Float f:angleForFor){
                    angleArray[i] = f;
                    i++;
                }
                if(saveTemplates){
                    save(angleArray, tempcounter);
                    tempcounter++;
                }else {
                    HashMap<Double, String> testHash = computeDTWResult(angleArray);
                    Set<Double> set = testHash.keySet();
                    Object obj = Collections.min(set);
                    notifyAllObservers(testHash.get(obj).toLowerCase().charAt(0),Integer.parseInt(testHash.get(obj).substring(1)));

                }
                }
        });
        add(drawPanel);
        setMinimumSize(new Dimension(400,400));
        setVisible(true);
    }

    public void paint(Graphics g) {
        if(deleteLines){
            super.paint(g);
            deleteLines = false;
        } else {
            g.setColor(Color.blue);
            g.fillRect(mX, mY, 5, 5);
        }
    }

    public Float computeAngleForDTW(Point current, Point previous, Point startPoint){
        Float angle = null;
        return (float)Math.toDegrees(Math.atan2(current.x - startPoint.x,current.y - startPoint.y)-
                Math.atan2(previous.x- startPoint.x,previous.y- startPoint.y));
    }

    public void save(float[] angleArray, int tempCounter){

        try {
            FileOutputStream fos = new FileOutputStream("src/main/resources/templates/temp"+tempCounter+"C3.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            for(float f:angleArray) {
                dos.writeFloat(f);
            }
            dos.close();
        } catch (IOException e) {
            if(e instanceof EOFException){

            }else
            e.printStackTrace();
        }
    }
    public HashMap<Double, String> computeDTWResult(float[] angleArray) {
        ArrayList<Float> floats = null;
        HashMap<Double, String> dtwResults=new HashMap<>();
        DTW dtw = new DTW();
        float[] template = null;

            File dir = new File("src/main/resources/templates/");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    try {
                        FileInputStream fin = new FileInputStream(child);
                        DataInputStream din = new DataInputStream(fin);
                        floats = new ArrayList<>();
                        Float line;
                        System.out.println("iasjdkjsa");
                        while ((line = din.readFloat()) != null) {
                            floats.add(line);
                        }
                        din.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ajuwoq");
                    template = new float[floats.size()];
                    int i = 0;
                    for (float f : floats) {
                        template[i] = f;
                        i++;
                    }
                    DTW.Result result = dtw.compute(angleArray, template);
                    dtwResults.put(result.getDistance(), child.getName().substring(5,7));
                }
            }
                 else{
                    System.out.println("Something went wrong");
                }

                return(dtwResults);

            }
            public HashMap<Double, String> getResult(){
                return computeDTWResult(angleArray);

            }
            public void attach(Observer observer) {
                observers.add(observer);
            }

            public void notifyAllObservers(char row, int col){
                for(Observer observer:observers){
                    observer.updateButton(row, col);
                }
            }


}