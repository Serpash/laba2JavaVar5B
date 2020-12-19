package bsu.serega.pashko.sevengroup;
import javax.swing.*;
import java.lang.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class MainFrame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldMem;
    private JTextField textFieldMem2;
    private JTextField textFieldMem3;
// Текстовое поле для отображения результата,

    // как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioMemoryButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemoryType = Box.createHorizontalBox();
    // Идентификатор выбранной формулы
    private int formulaId = 1;
    private int memoryId = 1;

    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow(Math.cos(Math.PI * Math.pow(x, 3)) + Math.log(Math.pow(1 + y, 2)), 1 / 4) * (Math.exp(z * z) + Math.sqrt(1 / x) + Math.cos(Math.exp(y)));
    }

    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        return Math.exp(0.5 * x) / (Math.sqrt(z + y) * Math.log(Math.pow(x, z)));
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    private void addRadioButton2(String buttonName, final int memoryId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memoryId = memoryId;
            }
        });
        radioMemoryButtons.add(button);
        hboxMemoryType.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(Box.createHorizontalGlue());
        //радио кнопки вычислить1 2 3 radioMemoryButtons    hboxMemoryType
        hboxMemoryType.add(Box.createHorizontalGlue());
        addRadioButton2("mem 1", 1);
        addRadioButton2("mem 2", 2);
        addRadioButton2("mem 3", 3);
        radioMemoryButtons.setSelected(
                radioMemoryButtons.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
        hboxMemoryType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
        // область для записи MEM
        JLabel labelForMemory = new JLabel("Mem1:");
        textFieldMem = new JTextField("0", 10);
        textFieldMem.setMaximumSize(textFieldMem.getPreferredSize());
        JLabel labelForMemory2 = new JLabel("Mem2:");
        textFieldMem2 = new JTextField("0", 10);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        JLabel labelForMemory3 = new JLabel("Mem3:");
        textFieldMem3 = new JTextField("0", 10);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());
        Box hboxMemory = Box.createHorizontalBox();
        hboxMemory.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(labelForMemory);
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(textFieldMem);
        hboxMemory.add(Box.createHorizontalStrut(100));
        hboxMemory.add(labelForMemory2);
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(textFieldMem2);
        hboxMemory.add(Box.createHorizontalStrut(100));
        hboxMemory.add(labelForMemory3);
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(textFieldMem3);
        hboxMemory.add(Box.createHorizontalStrut(100));
        hboxMemory.add(Box.createHorizontalGlue());

// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // Добавление кнопок MC И M+

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    textFieldMem.setText("0");
                    textFieldMem2.setText("0");
                    textFieldMem3.setText("0");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    final Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonMPLUS = new JButton("M+");
        buttonMPLUS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                double result = Double.parseDouble(textFieldResult.getText());
                double temp;
                if(memoryId==1) { temp = Double.parseDouble(textFieldMem.getText()); result += temp;}
                if(memoryId==2) { temp = Double.parseDouble(textFieldMem2.getText());result += temp;}
                if(memoryId==3) { temp = Double.parseDouble(textFieldMem3.getText());result += temp;}
                textFieldResult.setText(String.valueOf(result));
            }
        });
        Box hboxButtonsM = Box.createHorizontalBox();
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.add(buttonMC);
        hboxButtonsM.add(Box.createHorizontalStrut(30));
        hboxButtonsM.add(buttonMPLUS);
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxMemory);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxButtonsM);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}