import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;


//Adam Tomaszewski 18598
//Laboratorium NR 8 - zadanie 4 (SPECJALNE)

public class maszyna extends JFrame implements ActionListener, KeyListener {
    JFrame frame;
    JButton kolor;
    JTextArea area;
    JComboBox font, rozmiar;
    JScrollPane scrollArea;
    JMenuItem cut, copy, paste, selectAll, cutAll, undo, redo;
    JMenu menu;
    JMenuBar mb;
    Dimension ekran;
    final UndoManager manager;
    Document doc;

    maszyna()
    {
        ekran = Toolkit.getDefaultToolkit().getScreenSize();

        int szer = ekran.width;
        int wys = ekran.height;

        frame = new JFrame("Maszyna do pisania");
        menu = new JMenu("Edytuj");
        mb = new JMenuBar();


        //utworzenie pola do pisania po nim (dużo łatwiejsze niż przy używaniu JPanelu i listenerów na nim)
        area = new JTextArea(21,60);


        //Utworzenie pola ze scrollem w którym umieszczamy pole tekstowe, zapopiega to niechcianemu rozszerzaniu się
        //pola tekstowego

        scrollArea = new JScrollPane(area);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        kolor = new JButton("Kolor tekstu");
        kolor.setToolTipText("Ustaw kolor font");

        manager = new UndoManager();
        doc = area.getDocument();

        //Część undo, redo

        doc.addUndoableEditListener(e -> manager.addEdit(e.getEdit()));
        area.addKeyListener(this);


        //Operacje menu edycji

        cut = new JMenuItem("Wytnij");
        copy = new JMenuItem("Kopiuj");
        paste = new JMenuItem("Wklej");
        selectAll = new JMenuItem("Zaznacz wszystko");
        cutAll = new JMenuItem("Wyczyść panel");
        undo = new JMenuItem("Cofnij");
        redo = new JMenuItem("Przywróć");

        //Listenery do każdej opcji w menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        cutAll.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);

        //Dodanie opcji do menu
        menu.add(undo);
        menu.add(redo);
        menu.add(cut);
        menu.add(copy);
        menu.add(paste);
        menu.add(selectAll);
        menu.add(cutAll);
        mb.add(menu);

        frame.setJMenuBar(mb);


        //Utworzenie tablic potrzebnych do utworzenia list rozwijanych (JComboBox)
        Integer sizes[]={8,9,10,11,12,14,16,18,20};
        String fonts[]={"Arial","Calibri","Times New Roman", "Ink Free"};

        //Utworzenie list rozwijanych (JComboBox) - czcionki i ich rozmiary
        rozmiar = new JComboBox(sizes);
        rozmiar.setToolTipText("Ustaw rozmiar font");
        rozmiar.setSelectedItem(sizes[6]);
        rozmiar.setSize(30,20);

        font = new JComboBox(fonts);
        font.setSelectedItem(fonts[0]);
        font.setToolTipText("Ustaw styl font");
        font.setSize(150,20);

        rozmiar.addActionListener(this);
        font.addActionListener(this);

        kolor.setSize(100,20);
        kolor.addActionListener(this);

        area.setLineWrap(true);
        area.setFont(new Font("Arial", Font.ITALIC, 16));



        frame.setBounds(szer/4,wys/4,810,505);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        frame.add(kolor);
        frame.add(font);
        frame.add(rozmiar);

        frame.getContentPane().add(scrollArea);
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == rozmiar)
        {
            String size = ""+rozmiar.getItemAt(rozmiar.getSelectedIndex());
            Font temp = new Font(area.getFont().getName(), Font.ITALIC, Integer.parseInt(size));
            area.setFont(temp);
        }

        if(source == font)
        {
            font = (JComboBox) e.getSource();
            Object selectedFont = font.getSelectedItem();
            area.setFont(new Font((String) selectedFont, Font.ITALIC, area.getFont().getSize()));
        }

        if(source == kolor)
        {
            Color c = JColorChooser.showDialog(this,"Kolory",Color.BLACK);
            area.setForeground(c);
        }

        if(source == cut)
        {
            area.cut();
        }

        if(source == copy)
        {
            area.copy();
        }

        if(source == paste)
        {
            area.paste();
        }

        if(source == selectAll)
        {
            area.selectAll();
        }

        if(source == cutAll)
        {
            area.selectAll();
            area.replaceSelection("");
        }

        if(source == undo)
        {
            try
            {
                if(manager.canUndo())
                {
                    manager.undo();
                }
            }
            catch(CannotUndoException ev)
            {
            }
        }

        if(source == redo)
        {
            try
            {
                if(manager.canRedo())
                {
                    manager.redo();
                }
            }
            catch(CannotRedoException ev)
            {
            }
        }

    }

    //kombinacje klawiszy ctrl+z, ctrl+y
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z)
        {
            try
            {
                if(manager.canUndo())
                {
                    manager.undo();
                }
            }
            catch(CannotUndoException ev)
            {
            }
        }

        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y)
        {
            try
            {
                if(manager.canRedo())
                {
                    manager.redo();
                }
            }
            catch(CannotRedoException ev)
            {
            }
        }

    }

    public static void main(String args[])
    {
        new maszyna();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}
