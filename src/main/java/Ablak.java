import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class Ablak extends JFrame
{
        public final String url =  "https://cdn.discordapp.com/attachments/981981881681715220/984051752527618048/harree_bank.jpg";
        //ki kell cseréli

        public final String url0 = "https://cdn.discordapp.com/attachments/981981881681715220/984051752833794058/bank_toltokep.jpg";

        public final Image image = BackgroundImage.requestImage(url);
        public final Image image0 = BackgroundImage.requestImage(url0);

        // ezt is
        public ImageIcon img = new ImageIcon("src/main/resources/bankikon.png");

        public Ablak(Bank bank)
        {
            JMenuBar mb;

            JMenu exit= new JMenu("Exit");


            JFrame frame= new JFrame();
            frame.setTitle("Bankszámlák");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setJMenuBar(mb=new JMenuBar());
            mb.add(exit);

            JPanel panel = new JPanel(new GridBagLayout()){
                @Override
                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };

            GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5, 5, 5, 5);
            constr.anchor = GridBagConstraints.WEST;

            constr.gridx=0;
            constr.gridy=0;

            JLabel NameLabel = new JLabel("Új Bankszámla hozzáadása :");
            JLabel pwdLabel = new JLabel("Meglévő Bankszámla módosítása :");
            JLabel list = new JLabel("Bankszámlák listázása :");
            //kell majd még egy választás
            JButton button = new JButton("Új számla");
            JButton button0 = new JButton("Módosítás");
            JButton button1 = new JButton("Listázás");

            NameLabel.setForeground(Color.WHITE);
            pwdLabel.setForeground(Color.WHITE);
            list.setForeground(Color.WHITE);

            panel.add(NameLabel, constr);
            constr.gridx=1;
            panel.add(button, constr);
            constr.gridx=0; constr.gridy=1;

            panel.add(pwdLabel, constr);
            constr.gridx=1;
            panel.add(button0, constr);
            constr.gridx=0; constr.gridy=2;

            panel.add(list, constr);
            constr.gridx=1;
            panel.add(button1, constr);
            constr.gridx=0; constr.gridy=2;

            constr.gridwidth = 2;
            constr.anchor = GridBagConstraints.CENTER;

            // add a listener to button
            button.addActionListener(e -> {
                frame.setVisible(false);
                Bankszámlák.UjszamlaNév();
            });
            button0.addActionListener(e -> {
                frame.setVisible(false);
                new Modositás2(bank);
            });
            button1.addActionListener(e ->{
                frame.setVisible(false);
                Bankszámlák.Listázás(Bankszámlák.szamla);
            });

            exit.addActionListener(e -> System.exit(0));

            frame.setJMenuBar(mb);
            frame.add(panel);
            frame.pack();
            frame.setSize(720,360);//                                                            lehet kell változtatni
            frame.setResizable(false);
            frame.setIconImage(img.getImage());
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        public Ablak() {

        }
    }
    class Modositás2 extends Ablak
    {
        Modositás2(Bank bank) {

            JMenuBar mb;
            JMenu file = new JMenu("További Opciók");
            JMenuItem back,exit;

            setJMenuBar(mb=new JMenuBar());
            file.add(back=new JMenuItem("Back"));
            file.add(exit=new JMenuItem("Exit"));
            mb.add(file);

            JFrame frame4 = new JFrame();
            frame4.setTitle("Bankszámlák");

            JPanel panel = new JPanel(new GridBagLayout()){
                @Override
                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };
            GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5, 5, 5, 5);
            constr.anchor = GridBagConstraints.WEST;

            constr.gridx = 0;
            constr.gridy = 0;

            JLabel NameLabel = new JLabel("Bankszámla törlése:");
            JLabel pwdLabel = new JLabel("Bankszámla tulajdonos módosítása:");

            // Declare Text fields
            JButton button2 = new JButton("Törlés");
            JButton button3 = new JButton("Módosítás");

            panel.add(NameLabel, constr);
            constr.gridx = 1;
            panel.add(button2, constr);
            constr.gridx = 0;
            constr.gridy = 1;

            panel.add(pwdLabel, constr);
            constr.gridx = 1;
            panel.add(button3, constr);
            constr.gridx = 0;
            constr.gridy = 2;

            constr.gridwidth = 2;
            constr.anchor = GridBagConstraints.CENTER;

            button2.addActionListener(e -> {
                try {
                    String rename = JOptionPane.showInputDialog("Törlendő Bankszámla tulajdonosa: ");
                    if(!rename.equals(""))
                    {
                        if(Bankszámlák.Tartalmaz(rename))//                                  !!!!!!!!!!!!!!!!!!!
                        {
                            frame4.setVisible(false);
                            JOptionPane.showMessageDialog(this, "Operation successful!");
                            Bankszámlák.Törlés(rename);
                            new Ablak(bank);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Hiba történt!\nA megadott Név nem szerepel az adatbázisban!");
                            int reply =JOptionPane.showConfirmDialog(this,"Kíván új számlát létrehozni a megadott névvel?");
                            if(reply==JOptionPane.YES_OPTION)
                            {
                                frame4.setVisible(false);
                                Bankszámlák.Ujszamla(rename);
                            }
                        }
                    }
                    else
                    {
                        frame4.setVisible(false);
                        JOptionPane.showMessageDialog(this, "Hiba történt!");
                        new Modositás2(bank);
                    }
                }catch (NullPointerException exception)
                {
                    frame4.setVisible(false);
                    new Modositás2(bank);
                }

            });
            button3.addActionListener(e -> {
                frame4.setVisible(false);
                Bankszámlák.Modositás(Bankszámlák.szamla);
            });
            back.addActionListener(e ->
            {
                frame4.setVisible(false);
                new Ablak(bank);
            });
            exit.addActionListener(e -> System.exit(0));


            frame4.setJMenuBar(mb);
            frame4.add(panel);
            frame4.pack();
            frame4.setSize(720,360);
            frame4.setLocationRelativeTo(null);
            frame4.setIconImage(img.getImage());
            frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame4.setVisible(true);
        }
    }

    class Listázás extends Ablak {
        public Listázás(Bank bank)
        {
            Bankszámlák.Listázás(Bankszámlák.szamla);//                                  !!!!!!!!!!!!!!!!!!!
            JFrame frame2 = new JFrame();
            JMenuBar mb = new JMenuBar();
            JMenu file = new JMenu("További Lehetőségek");
            JMenu med = new JMenu("Medicine");
            JMenu modify;
            JMenuItem exit,back,remove,update,added,removea;

            //file.add(generate=new JMenuItem("Generate test Data"));
            file.add(exit=new JMenuItem("Exit"));
            file.add(back=new JMenuItem("Back"));

            med.add(added = new JMenuItem("Add") );
            med.add(modify = new JMenu("Modify") );
            med.add(removea = new JMenuItem("Remove all") );

            modify.add(remove = new JMenuItem("Remove"));
            modify.add(update = new JMenuItem("Update"));

            mb.add(file);
            mb.add(med);

            frame2.setTitle("Összes Bankszámla:");

            JPanel panel = new JPanel(new GridBagLayout()){
                @Override
                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    g.drawImage(image0, 0, 0, null);
                }
            };
            GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5, 5, 5, 5);
            constr.anchor = GridBagConstraints.WEST;

            // Declare the required Labels
            String[] col = {"Tulajdonos Neve","Id","Besorolás","Egyenleg"};//                                  !!!!!!!!!!!!!!!!!!!

            JTable table = new JTable()
            {
                final DefaultTableCellRenderer renderCENTER = new DefaultTableCellRenderer();

                {
                    renderCENTER.setHorizontalAlignment(SwingConstants.CENTER);
                }

                @Override
                public TableCellRenderer getCellRenderer (int arg0, int arg1)
                {
                    return renderCENTER;
                }
            };
            table.setBounds(0,0,400,500);

            JScrollPane scrollPane = new JScrollPane(table);
            JTextArea Content = new JTextArea(Bankszámlák.Listázás(Bankszámlák.szamla));

            JButton button8 = new JButton("Back to Main");


            constr.gridy=0;

            constr.gridx=1;
            panel.add(Content, constr);
            panel.add(scrollPane, constr);
            constr.gridx=0;
            constr.gridx=2;
            panel.add(button8, constr);



            button8.addActionListener(e -> {
                frame2.setVisible(false);
                new Ablak(bank);
            });
            added.addActionListener(e -> {
                frame2.setVisible(false);
                Bankszámlák.UjszamlaNév();//                                                     !!!!!!!!!!!!!!!!!!!!!!!!!
            });
            update.addActionListener(e ->{
                frame2.setVisible(false);
                Component source = (Component) e.getSource();
                String response = (String) JOptionPane.showInputDialog(source,"Válasszon: ", "Frissités", JOptionPane.QUESTION_MESSAGE, null, Bankszámlák.createNameArray(), Bankszámlák.createNameArray()[0]);
                for(Bank bankok:Bankszámlák.szamla)///                           !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                {
                    if(bankok.getName().equals(response))
                    {
                        Bankszámlák.Modid(Bankszámlák.szamla,response);//                    !!!!!!!!!!!!!!!!!!!!!!!
                    }
                }

            });
            remove.addActionListener(e ->{
                frame2.setVisible(false);
                Component source = (Component) e.getSource();
                /*       Mi a fasz???       */String response = (String) JOptionPane.showInputDialog(source,"Choose One?", "Remove", JOptionPane.QUESTION_MESSAGE, null, Bankszámlák.createNameArray(), Bankszámlák.createNameArray()[0]);
                Bankszámlák.Törlés(response);//                                   !!!!!!!!!!!!!!!!!!!!!
                JOptionPane.showMessageDialog(this,"Sikeres végrehajtás!");
                new Listázás(bank);
            });
            removea.addActionListener(e -> {//                        !!!!!!!!!!!!!!!!!!
                if (!Bankszámlák.szamla.isEmpty()){
                    Bankszámlák.removeAll();
                    frame2.setVisible(false);
                    JOptionPane.showMessageDialog(this ,"Minden Bankszámla törölve");
                    new Listázás(bank);
                }
                else JOptionPane.showMessageDialog(this ,"Nincs nyilvántartott Bankszámla");
            });
            exit.addActionListener(e -> System.exit(0));
            // Editable Table
            DefaultTableModel tableModel = new DefaultTableModel(Bankszámlák.createStringArray(),col) {//               !!!!!!!!!!!!!!!!!

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };

            table.setModel(tableModel);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(50);
            table.getColumnModel().getColumn(2).setPreferredWidth(150);
            //Table Content Double Click
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (me.getClickCount() == 2) {     // to detect double click events
                        JTable target = (JTable)me.getSource();
                        int row = target.getSelectedRow(); // select a row
                        int column = target.getSelectedColumn(); // select a column
                        JOptionPane.showMessageDialog(null, table.getValueAt(row, column)); // get the value of a row and column.
                    }
                }
            });
            table.getTableHeader().setResizingAllowed(false);
            // Add panel to frame
            frame2.add(panel);
            frame2.pack();
            frame2.setLocationRelativeTo(null);
            //frame2.setSize(Content.getWidth()+220,Content.getHeight()+100);
            System.out.println(table.getHeight());
            frame2.setSize(800,600);
            frame2.setJMenuBar(mb);
            frame2.setIconImage(img.getImage());
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setVisible(true);

        }/*
        public void saveToXML(Bank bank)
        {
            Xml.saveMedToXml(bank.medList,"src/main/resources/medicines.xml");//                    !!!!!!!!!!!!!!!!!!!!
        }*/
    }
    class UjSzámla extends Ablak
    {
        public UjSzámla(Bank bank,String name,String id,String besorolas,String egyenleg)
        {
            // Menu
            JMenuBar mb;
            JMenu file = new JMenu("További Lehetőségek");
            JMenuItem back,exit;

            setJMenuBar(mb=new JMenuBar());
            file.add(back=new JMenuItem("Back"));
            file.add(exit=new JMenuItem("Exit"));
            mb.add(file);

            // Create frame with title Registration Demo
            JFrame frame1= new JFrame();
            frame1.setTitle("Uj Bankszámla létrehozása");

            // Panel to define the layout. We are using GridBagLayout


            // Panel to define the layout. We are using GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout()){
                @Override
                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };
            // Constraints for the layout
            GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5, 5, 5, 5);
            constr.anchor = GridBagConstraints.WEST;

            // Set the initial grid values to 0,0
            constr.gridx=0;
            constr.gridy=0;

            // Declare the required Labels
            JLabel NameLabel = new JLabel("Számla tulajdonos neve :");
            JLabel IdLabel = new JLabel("Id :");
            JLabel BeLabel = new JLabel("Besorolás :");
            JLabel EgyLabel = new JLabel("Egyenleg :");

            // Declare Text fields
            JTextField NameTxt = new JTextField(20);
            JTextField IdTxt = new JTextField(20);
            JTextField BeTxt = new JTextField(20);
            JTextField EgyTxt = new JTextField(20);
            NameTxt.setText(name);//                                     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            IdTxt.setText(id);
            BeTxt.setText(besorolas);
            EgyTxt.setText(egyenleg);

            panel.add(NameLabel, constr);
            constr.gridx=1;
            panel.add(NameTxt, constr);
            constr.gridx=0; constr.gridy=1;

            panel.add(IdLabel, constr);
            constr.gridx=1;
            panel.add(IdTxt, constr);
            constr.gridx=0; constr.gridy=2;

            panel.add(BeLabel, constr);
            constr.gridx=1;
            panel.add(BeTxt, constr);
            constr.gridx=0; constr.gridy=3;

            panel.add(EgyLabel, constr);
            constr.gridx=1;
            panel.add(EgyTxt, constr);
            constr.gridx=0; constr.gridy=4;

            constr.gridwidth = 4;
            constr.anchor = GridBagConstraints.CENTER;

            // Button with text "Register"
            JButton button6 = new JButton("Számla létrehozása!");
            // add a listener to button
            button6.addActionListener(e -> {
                if(!NameTxt.getText().equals("")&&!IdTxt.getText().equals("")&&!NameTxt.getText().equals(" ")&&!IdTxt.getText().equals(" "))
                {
                    if(!Bankszámlák.szamla.contains(NameTxt.getText()))//                                   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    {
                        frame1.setVisible(false);
                        Bankszámlák.UjszamlaTeljes(NameTxt.getText(), IdTxt.getText(),EgyTxt.getText());
                        JOptionPane.showMessageDialog(this, "Müvelet sikeresen végrehajtva!");
                        new Ablak(bank); // Main Form to show after the Login Form.
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Hiba!\nLétezik evvel a névvel Bankszámla!");
                        int reply =JOptionPane.showConfirmDialog(this,"Kívánja módosítani a már megléső Bankszámlát?");
                        if(reply==JOptionPane.YES_OPTION)
                        {
                            frame1.setVisible(false);
                            for(Bank bankok:Bankszámlák.szamla)//                              !!!!!!!!!!!!!!!!!!!!!
                            {
                                if(NameTxt.getText().equals(bankok.getName())) Bankszámlák.Modid(Bankszámlák.szamla, bankok.getName());
                            }

                        }
                    }
                }
                else {//                                  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    if (NameTxt.getText().equals("") && IdTxt.getText().equals("")) JOptionPane.showMessageDialog(frame1, "Operation failed!\n No Name given!\n No Description given!");
                    else if(NameTxt.getText().equals("")) JOptionPane.showMessageDialog(frame1, "Operation failed!\n No Name given!");
                    else if(IdTxt.getText().equals("")) JOptionPane.showMessageDialog(frame1, "Operation failed!\n No Description given!");
                    else System.out.println("This cant happen!");
                }
            });
            back.addActionListener(e ->
            {
                frame1.setVisible(false);
                new Ablak(bank);
            });
            exit.addActionListener(e -> System.exit(0));
            // Add label and button to panel
            panel.add(button6, constr);


            // Add panel to frame
            frame1.add(panel);
            frame1.pack();
            frame1.setSize(720,360);//                    !!!!!!!!!!!!!!!!!!!!!!!!!!!
            frame1.setJMenuBar(mb);
            frame1.setLocationRelativeTo(null);
            frame1.setIconImage(img.getImage());
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setVisible(true);
        }
    }/*
    class Módosítás extends Ablak
    {
        Módosítás(Bank bank, String name,String des)//                              Kiegészít
        {

            // Menu
            JMenuBar mb;
            JMenu file = new JMenu("További lehetőségek");
            JMenuItem back,exit;

            setJMenuBar(mb=new JMenuBar());
            file.add(back=new JMenuItem("Back"));
            file.add(exit=new JMenuItem("Exit"));
            mb.add(file);

            //Frame
            JFrame frame1= new JFrame();
            frame1.setTitle("Meglévő Bankszámla egyenleg módosítása");

            // Panel to define the layout. We are using GridBagLayout

            // Panel to define the layout. We are using GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout()){
                @Override
                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };
            // Constraints for the layout
            GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5, 5, 5, 5);
            constr.anchor = GridBagConstraints.WEST;

            // Set the initial grid values to 0,0
            constr.gridx=0;
            constr.gridy=0;

            // Declare the required Labels
            JLabel NameLabel = new JLabel("Enter the name: ");//                       Kiegésziiiiiiiiiiiiiit
            JLabel pwdLabel = new JLabel("Enter the description: ");

            // Declare Text fields
            JTextField NameTxt = new JTextField(20);
            JTextField pwdTxt = new JTextField(20);
            NameTxt.setText(name);
            pwdTxt.setText(des);

            panel.add(NameLabel, constr);
            constr.gridx=1;
            panel.add(NameTxt, constr);
            constr.gridx=0; constr.gridy=1;

            panel.add(pwdLabel, constr);
            constr.gridx=1;
            panel.add(pwdTxt, constr);
            constr.gridx=0; constr.gridy=2;

            constr.gridwidth = 2;
            constr.anchor = GridBagConstraints.CENTER;

            // Button with text "Register"
            JButton button6 = new JButton("Done!");
            // add a listener to button
            button6.addActionListener(e -> {
                if(!NameTxt.getText().equals("")&&!pwdTxt.getText().equals(""))
                {
                    if(Bankszámlák.szamla.contains(NameTxt.getText()))//                                 !!!!!!!!!!!!!!!!!!!!!!!
                    {
                        frame1.setVisible(false);
                        bank.updateDes(NameTxt.getText(), IdTxt.getText());
                        JOptionPane.showMessageDialog(frame1, "Operation successful!");
                        new Ablak(bank); // Main Form to show after the Login Form.
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame1, "Operation failed!\nMedicine with given Name does not exist!");
                        int reply = JOptionPane.showConfirmDialog(this,"Do you want to create new with this name?");
                        if(reply==JOptionPane.YES_OPTION)
                        {
                            frame1.setVisible(false);
                            new AddMed(medicines,NameTxt.getText(),pwdTxt.getText());
                        }
                    }
                }
                else {
                    if (NameTxt.getText().equals("") && pwdTxt.getText().equals("")) JOptionPane.showMessageDialog(frame1, "Operation failed!\n No Name given!\n No Description given!");
                    else if(NameTxt.getText().equals("")) JOptionPane.showMessageDialog(frame1, "Operation failed!\n No Name given!");
                    else if(pwdTxt.getText().equals("")) JOptionPane.showMessageDialog(frame1, "Operation failed!\n No Description given!");
                }
            });
            back.addActionListener(e ->
            {
                frame1.setVisible(false);
                new Ablak(bank);
            });
            exit.addActionListener(e -> System.exit(0));
            // Add label and button to panel
            panel.add(button6, constr);


            // Add panel to frame
            frame1.add(panel);
            frame1.pack();
            frame1.setSize(Xml.getdata()[0], Xml.getdata()[1]);
            frame1.setJMenuBar(mb);
            frame1.setIconImage(img.getImage());
            frame1.setLocationRelativeTo(null);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setVisible(true);
        }
    }*/
    class BackgroundImage extends Ablak
    {
        static Image requestImage(String url)
        {
            Image image = null;
            try
            {
                image = ImageIO.read(new URL(url));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return image;
        }
    }
    class SplashScreen extends Ablak//                           Nem Tudom Mi Ez
    {
        JFrame frame;
        JPanel panel = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(image0, 0, 0, null);
            }
        };
        JLabel text=new JLabel("Bankszámla Adatbázis");
        JProgressBar progressBar=new JProgressBar();
        JLabel message=new JLabel();//Crating a JLabel for displaying the message
        public SplashScreen(Bank bank)
        {
            createGUI();
            addText();
            addProgressBar();
            runningPBar();
            new Ablak(bank);
        }
        public void createGUI(){
            frame=new JFrame();
            frame.add(panel);
            panel.setBounds(0,0,600,400);
            frame.getContentPane().setLayout(null);
            frame.setUndecorated(true);
            frame.setSize(600,200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }
        public void addText()
        {
            text.setFont(new Font("arial",Font.BOLD,30));
            text.setBounds(140,50,600,40);
            text.setForeground(Color.GREEN);
            panel.add(text);
        }
        public void addProgressBar(){
            progressBar.setBounds(100,100,400,30);
            progressBar.setBorderPainted(true);
            progressBar.setStringPainted(true);
            progressBar.setBackground(Color.BLACK);
            progressBar.setForeground(Color.GRAY);
            progressBar.setValue(0);
            panel.add(progressBar);
        }
        public void runningPBar(){//                                                                     Szerkeszteni akarom
            int i=0;//Creating an integer variable and initializing it to 0

            while( i<=100)
            {
                try{
                    Thread.sleep(15);//Pausing execution for 50 milliseconds
                    progressBar.setBackground(Color.BLACK);
                    progressBar.setValue(i);//Setting value of Progress Bar
                    message.setForeground(Color.black);
                    message.setText("Betöltés "+ i +"%");//Setting text of the message JLabel
                    i++;
                    if(i==100)
                        frame.dispose();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

