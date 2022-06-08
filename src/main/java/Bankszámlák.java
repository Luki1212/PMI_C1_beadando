import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.err;

public class Bankszámlák {
    private static final Scanner scanner = new Scanner(System.in);
    public static ArrayList<Bank> szamla = XmlBeolvasó.readPillsFromXml("src/main/resources/szamla.xml");

    public static Boolean Tartalmaz(String név){
        return szamla.contains(név);

    }

    public static String[][] createStringArray()
    {
        String newArray[][] = new String[szamla.size()][];
        for(Bank bank : szamla)
        {
            String[] innerArray = new String[4];
            innerArray[0]=bank.getName();
            innerArray[1]=bank.getId();
            innerArray[2]=String.valueOf(bank.getPenz());
            innerArray[3]=String.valueOf(bank.Getbesorolas());
            newArray[szamla.indexOf(bank)]=innerArray;
        }
        return newArray;
    }

    public static void removeAll()
    {
        szamla= new ArrayList<>();
    }

    public static String[] createNameArray()
    {
        String newArray[] = new String[szamla.size()];
        for(Bank bank : szamla)
        {
            newArray[szamla.indexOf(bank)]=bank.getName();
        }
        return newArray;
    }






    public static void main(String[] args){

        new SplashScreen(new Bank("","",0,Besorolas.magánszemély));
    }

    public static void Ujszamla(String név) {//                                         ablakosra át ir!!!!!!!!!!!!!!!!!!!!!!!!!!!
        while (true) {
            try {
                for (int i = 0; i < szamla.size(); i++) {
                    if (név.equals(szamla.get(i).getName())) {
                        throw new HibasNev();
                    } else if (név.equals(" ")) {
                        throw new ÜresNév();
                    }
                }
                break;
            } catch (HibasNev hn) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "Már létezik ilyen nevü számla tulajdonos, elöbb törölje a már meglévőt és kezdje ujra a folyamatot");
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            } catch (ÜresNév hn) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "nem adhat meg üres nevet");
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            }
        }

        JFrame frame2 = new JFrame();
        JMenuBar mb = new JMenuBar();
        frame2.setTitle("Uj Számla Létrehozása:");
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);
        constr.anchor = GridBagConstraints.WEST;
        constr.gridx=0;
        constr.gridy=0;
        JPanel panel = new JPanel();
        JLabel IdLabel = new JLabel("Id :");
        JLabel PéLabel = new JLabel("Egyenleg :");
        String id = "";
        int pénz = 0;
        JTextField IdTxt = new JTextField(20);
        JTextArea IdText = new JTextArea();
        JTextField PéTxt = new JTextField(20);
        JTextArea PéText = new JTextArea();
        panel.add(IdLabel, constr);
        constr.gridx=1;
        panel.add(IdTxt, constr);
        constr.gridx=0; constr.gridy=1;
        panel.add(PéLabel, constr);
        constr.gridx=1;
        panel.add(PéTxt, constr);
        constr.gridx=0; constr.gridy=2;

        boolean igaz = false;
        //System.out.print("Üsse be az id-t (min 2, max 20 karakter): \n");
        while (igaz == false) {

            try {
                id = IdText.getText();

                for (int i = 0; i < szamla.size(); i++) {
                    if (id.equals(szamla.get(i).getId())) {
                        throw new LetezoId();
                    } else if (id.length() > 20 || id.length() < 2) {

                        throw new HibasId();
                    } else if (i == szamla.size() - 1) {
                        igaz = true;
                        break;
                    }
                }
            } catch (HibasId hi) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "nem jo id hossz \nProbálja ujra");
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            } catch (LetezoId hi) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "Már Létezik ilyen id-val rendelkező számla \nProbálja ujra");
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            }

        }

        while (true) {

            System.out.println("Üsse be az új pénz összeget");

            try {
                pénz = Integer.parseInt(PéText.getText());
                break;
            } catch (InputMismatchException e) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "nem szám Pénz értéke");
                scanner.nextLine();
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            }

        }
        szamla.add(new Bank(név,id,pénz,Besorolas.magánszemély));/////                                                átíír
        JFrame frame1= new JFrame();
        frame1.setTitle("Siker");
        JOptionPane.showMessageDialog(frame1, "Uj számla sikeresen hozzáadva");
        new Ablak(new Bank("","",0,Besorolas.magánszemély));
    }
    public static void UjszamlaTeljes(String név,String id, String egyenleg,String besorolas) {//                 ablakosra át ir!!!!!!!!!!!!!!!!!!!!!!!!!!!

        while (true) {
            try {
                for (int i = 0; i < szamla.size(); i++) {
                    if (név.equals(szamla.get(i).getName())) {
                        throw new HibasNev();
                    } else if (név.equals(" ")) {
                        throw new ÜresNév();
                    }
                }
                break;
            } catch (HibasNev hn) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "Már létezik ilyen nevü számla tulajdonos, elöbb törölje a már meglévőt és kezdje ujra a folyamatot");
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            } catch (ÜresNév hn) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "nem adhat meg üres nevet");
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            }


        }
        boolean igaz = false;

        while (igaz == false) {
            try {
                for (int i = 0; i < szamla.size(); i++) {
                    if (id.equals(szamla.get(i).getId())) {
                        throw new LetezoId();
                    } else if (id.length() > 20 || id.length() < 2) {

                        throw new HibasId();
                    } else if (i == szamla.size() - 1) {
                        igaz = true;
                        break;
                    }
                }
            } catch (HibasId hi) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "nem jo id hossz \nProbálja ujra");
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            } catch (LetezoId hi) {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "Már Létezik ilyen id-val rendelkező számla \nProbálja ujra");
                new Ablak(new Bank("","",0,Besorolas.magánszemély));
            }

        }
        szamla.add(new Bank(név,id,Integer.parseInt(egyenleg),Besorolas.valueOf(besorolas)));///                           átír
        JFrame frame1= new JFrame();
        frame1.setTitle("Siker");
        JOptionPane.showMessageDialog(frame1, "Uj számla sikeresen hozzáadva");
        new Ablak(new Bank("","",0,Besorolas.magánszemély));
    }

        public static void UjszamlaNév() {//                                         ablakosra át ir!!!!!!!!!!!!!!!!!!!!!!!!!!!
            String nev="";


            JFrame frame2 = new JFrame();
            JMenuBar mb = new JMenuBar();
            frame2.setTitle("Uj Számla Létrehozása:");
            GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5, 5, 5, 5);
            constr.anchor = GridBagConstraints.WEST;
            constr.gridx=0;
            constr.gridy=0;
            JPanel panel = new JPanel();
            JLabel NéLabel = new JLabel("Számlatulajdonos :");
            JLabel IdLabel = new JLabel("Id :");
            JLabel PéLabel = new JLabel("Egyenleg :");
            String id = "";
            int pénz = 0;
            JTextField NéTxt = new JTextField(20);
            JTextArea NéText = new JTextArea();
            JTextField IdTxt = new JTextField(20);
            JTextArea IdText = new JTextArea();
            JTextField PéTxt = new JTextField(20);
            JTextArea PéText = new JTextArea();
            panel.add(NéLabel, constr);
            constr.gridx=1;
            panel.add(NéTxt, constr);
            constr.gridx=0; constr.gridy=1;
            panel.add(IdLabel, constr);
            constr.gridx=1;
            panel.add(IdTxt, constr);
            constr.gridx=0; constr.gridy=2;
            panel.add(PéLabel, constr);
            constr.gridx=1;
            panel.add(PéTxt, constr);
            constr.gridx=0; constr.gridy=3;
            while(true){
                nev=NéText.getText();
                try{

                    for (int i = 0; i < szamla.size(); i++) {
                        if(nev.equals(szamla.get(i).getName())){
                            throw new HibasNev();
                        }
                        else if (nev.equals(" ")){
                            throw new ÜresNév();
                        }

                    }
                    break;
                }catch (HibasNev hn){
                    JFrame frame1= new JFrame();
                    frame1.setTitle("Hiba");
                    JOptionPane.showMessageDialog(frame1, "Már létezik ilyen nevü számla tulajdonos, elöbb törölje a már meglévőt és kezdje ujra a folyamatot");
                    new Ablak(new Bank("","",0,Besorolas.magánszemély));
                }catch (ÜresNév hn){
                    JFrame frame1= new JFrame();
                    frame1.setTitle("Hiba");
                    JOptionPane.showMessageDialog(frame1, "nem adhat meg üres nevet");
                    new Ablak(new Bank("","",0,Besorolas.magánszemély));
                }


            }

            boolean Hamis=false;

            while(Hamis==false){

                try{
                    id = IdText.getText();

                    for (int i = 0; i < szamla.size(); i++) {
                        if(id.equals(szamla.get(i).getId())){
                            throw new LetezoId();
                        }
                        else if(id.length()>20 || id.length()<2) {

                            throw new HibasId();
                        }
                        else if(i== szamla.size()-1){
                            Hamis = true;
                            break;
                        }
                    }
                }
                catch (HibasId hi){
                    JFrame frame1= new JFrame();
                    frame1.setTitle("Hiba");
                    JOptionPane.showMessageDialog(frame1, "nem jo id hossz \nProbálja ujra");
                    new Ablak(new Bank("","",0,Besorolas.magánszemély));
                }
                catch (LetezoId hi){
                    JFrame frame1= new JFrame();
                    frame1.setTitle("Hiba");
                    JOptionPane.showMessageDialog(frame1, "Már Létezik ilyen id-val rendelkező számla \nProbálja ujra");
                    new Ablak(new Bank("","",0,Besorolas.magánszemély));
                }

            }

            while(true){
                try{
                    pénz = Integer.parseInt(PéText.getText());
                    scanner.nextLine();

                    break;
                }
                catch (InputMismatchException e){
                    JFrame frame1= new JFrame();
                    frame1.setTitle("Hiba");
                    JOptionPane.showMessageDialog(frame1, "nem szám Pénz értéke");
                    scanner.nextLine();
                    new Ablak(new Bank("","",0,Besorolas.magánszemély));
                }

            }

        szamla.add(new Bank(nev,id,pénz,Besorolas.magánszemély));
            JFrame frame1= new JFrame();
            frame1.setTitle("Siker");
            JOptionPane.showMessageDialog(frame1, "Uj számla sikeresen hozzáadva");
        new Ablak(new Bank("","",0,Besorolas.magánszemély));
    }
    public static void Modositás(ArrayList<Bank> szamla) {//                                       Ablakooooos!!!!!!!!!!!!!!!!!!!!!

        String nev="";


        JFrame frame2 = new JFrame();
        JMenuBar mb = new JMenuBar();
        frame2.setTitle("Uj Számla Létrehozása:");
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);
        constr.anchor = GridBagConstraints.WEST;
        constr.gridx=0;
        constr.gridy=0;
        JPanel panel = new JPanel();
        JLabel NéLabel = new JLabel("Számlatulajdonos :");
        JLabel IdLabel = new JLabel("Adja meg az Idt a modositáshoz :");
        JLabel PéLabel = new JLabel("Egyenleg :");
        String id = "";
        int pénz = 0;
        JTextField IdTxt = new JTextField(20);
        JTextArea IdText = new JTextArea();

        panel.add(IdLabel, constr);
        constr.gridx=1;
        panel.add(IdTxt, constr);
        constr.gridx=0; constr.gridy=2;




        id = IdText.getText();
        int choice = -1;
        for (Bank szamlak : szamla) {

            if (szamlak.getId().equals(id)) {

                JFrame frame= new JFrame();
                frame.setTitle("Bankszámlák");

                JPanel panel3 = new JPanel();

                constr.insets = new Insets(5, 5, 5, 5);
                constr.anchor = GridBagConstraints.WEST;

                constr.gridx=0;
                constr.gridy=0;

                JLabel NameLabel = new JLabel("Új Bankszámla hozzáadása :");
                JLabel pwdLabel = new JLabel("Meglévő Bankszámla módosítása :");
                //kell majd még egy választás
                JButton button = new JButton("Új számla");
                JButton button0 = new JButton("Módosítás");

                NameLabel.setForeground(Color.WHITE);
                pwdLabel.setForeground(Color.WHITE);

                panel.add(NameLabel, constr);
                constr.gridx=1;
                panel.add(button, constr);
                constr.gridx=0; constr.gridy=1;

                panel.add(pwdLabel, constr);
                constr.gridx=1;
                panel.add(button0, constr);
                constr.gridx=0; constr.gridy=2;

                constr.gridwidth = 2;
                constr.anchor = GridBagConstraints.CENTER;

                // add a listener to button
                button.addActionListener(e -> {
                    frame.setVisible(false);
                    //Bankszámlák.UjszamlaNév();
                });
                button0.addActionListener(e -> {
                    frame.setVisible(false);
                    //new Modositás2(bank);
                });

                while (choice != 0) {
                    switch (choice) {
                        case 1 -> Modnev(szamla,id);
                        case 2 -> Modpenz(szamla,id);
                    }
                    System.out.println("1 - Név modositás\r\n2 - Pénz modositás\r\n"
                            + "\r\n0 - Cancel\n");
                    try {
                        choice = scanner.nextInt();
                        if (choice < 0 || choice > 2) {
                            JFrame frame1= new JFrame();
                            frame1.setTitle("Hiba");
                            JOptionPane.showMessageDialog(frame1, "Nem létező opció.");
                            break;
                        }
                        if(choice==0){
                            new Ablak(new Bank("","",0,Besorolas.magánszemély));
                        }
                    } catch (InputMismatchException e) {
                        JFrame frame1= new JFrame();
                        frame1.setTitle("Hiba");
                        JOptionPane.showMessageDialog(frame1, "Nem létező opció.");
                        scanner.nextLine();
                        new Ablak(new Bank("","",0,Besorolas.magánszemély));
                    }
                }
                break;
            }
            else {
                JFrame frame1= new JFrame();
                frame1.setTitle("Hiba");
                JOptionPane.showMessageDialog(frame1, "Nem létezik ilyen id-val rendelkező számla");
            }
        }
        new Ablak(new Bank("","",0,Besorolas.magánszemély));

    }
    public static  void Modnev(ArrayList<Bank> szamla,String id){
        for (Bank szamlak : szamla) {
            if (szamlak.getId().equals(id)) {
                String név = "";
                boolean hamis=false;
                System.out.println("Üsse be a számla uj tulajdonosának nevét: ");

                while(hamis==false){
                    try{
                        while (név =="") név = scanner.nextLine();
                        System.out.println("log :" + név);
                        for (int i = 0; i < szamla.size(); i++) {
                            if(név.equals(szamla.get(i).getName())){
                                throw new LetezoNev();
                            }
                            else if (név==" "){
                                throw new ÜresNév();
                            }
                            else if(i== szamla.size()-1){
                                hamis = true;
                            }
                        }
                    }
                    catch (LetezoNev hn){
                        JFrame frame1= new JFrame();
                        frame1.setTitle("Hiba");
                        JOptionPane.showMessageDialog(frame1, "Már Létezik ilyen névvel számla \nProbálja ujra");
                        new Ablak(new Bank("","",0,Besorolas.magánszemély));
                    }catch (ÜresNév hn){
                        JFrame frame1= new JFrame();
                        frame1.setTitle("Hiba");
                        JOptionPane.showMessageDialog(frame1, "nem adhat meg üres nevet \nProbálja ujra");
                        new Ablak(new Bank("","",0,Besorolas.magánszemély));
                    }

                }
                szamla.set(szamla.indexOf(szamlak), new Bank(név, id, szamlak.getPenz(),Besorolas.magánszemély));
            }
        }
        JFrame frame1= new JFrame();
        frame1.setTitle("Siker");
        JOptionPane.showMessageDialog(frame1, "Számla tulajdonos neve sikeresen modosítva\n");
        new Ablak(new Bank("","",0,Besorolas.magánszemély));
        return;
    }

    public static  void Modid(ArrayList<Bank> szamla,String név){// név alapján id modositás
        for (Bank szamlak : szamla) {
            if (szamlak.getName().equals(név)) {
                String id="";
                boolean hamis=false;
                System.out.println("Üsse be a számla uj Id értékét: ");

                while(hamis==false){
                    try{
                        while (id =="") id = scanner.nextLine();
                        System.out.println("log :" + id);
                        for (int i = 0; i < szamla.size(); i++) {
                            if(id.equals(szamla.get(i).getId())){
                                throw new LetezoNev();
                            }
                            else if (id==" "){
                                throw new ÜresNév();
                            }
                            else if(i== szamla.size()-1){
                                hamis = true;
                            }
                        }
                    }
                    catch (LetezoNev hn){
                        JFrame frame1= new JFrame();
                        frame1.setTitle("Hiba");
                        JOptionPane.showMessageDialog(frame1, "Már Létezik ilyen idval számla \nProbálja ujra");
                        new Ablak(new Bank("","",0,Besorolas.magánszemély));
                    }catch (ÜresNév hn){
                        JFrame frame1= new JFrame();
                        frame1.setTitle("Hiba");
                        JOptionPane.showMessageDialog(frame1, "nem adhat meg üres idt \nProbálja ujra");
                        new Ablak(new Bank("","",0,Besorolas.magánszemély));
                    }

                }
                szamla.set(szamla.indexOf(szamlak), new Bank(név, id, szamlak.getPenz(),Besorolas.magánszemély));
            }
        }
        JFrame frame1= new JFrame();
        frame1.setTitle("Siker");
        JOptionPane.showMessageDialog(frame1, "Számla id-ja sikeresen modosítva\n");
        new Ablak(new Bank("","",0,Besorolas.magánszemély));
        return;
    }

    public static  void Modpenz(ArrayList<Bank> szamla,String id){
        for (Bank szamlak : szamla) {
            if (szamlak.getId().equals(id)) {

                while(true){
                    System.out.println("Üsse be a számlán szereplő pénz összeget: ");
                    int pénz =0;
                    try{
                        pénz = scanner.nextInt();
                        szamla.set(szamla.indexOf(szamlak), new Bank(szamlak.getName(), id, pénz,Besorolas.magánszemély));
                        break;
                    }
                    catch (Exception e){
                        JFrame frame1= new JFrame();
                        frame1.setTitle("Hiba");
                        JOptionPane.showMessageDialog(frame1, "Nem szám Pénz érték!");
                        scanner.nextLine();
                        new Ablak(new Bank("","",0,Besorolas.magánszemély));
                    }
                }
            }
        }
        JFrame frame1= new JFrame();
        frame1.setTitle("Siker");
        JOptionPane.showMessageDialog(frame1, "Számla pénz összege sikeresen módosítva.\n");
        new Ablak(new Bank("","",0,Besorolas.magánszemély));
        return;
    }
    public static void Törlés(String név) {

        for (Bank szamlak : szamla) {
            if (szamlak.getName().equals(név)) {
                szamla.remove(szamlak);
                return;
            }
        }
        new Ablak(new Bank("","",0,Besorolas.magánszemély));
    }
    public static void saveToXml(ArrayList<Bank> szamla, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("szamla");
            document.appendChild(rootElement);

            for (Bank szamlak : szamla) {
                Element pillElement = document.createElement("szamla");
                rootElement.appendChild(pillElement);
                createChildElement(document, pillElement, "név", szamlak.getName());
                createChildElement(document, pillElement, "id", szamlak.getId());
                createChildElement(document, pillElement, "pénz",  String.valueOf(szamlak.getPenz()));
                createChildElement(document, pillElement, "besorolas", String.valueOf(Besorolas.magánszemély));

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createChildElement(Document document, Element parent,
                                           String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}
class HibasPenz extends Exception{

}

class HibasId extends Exception{

}

 class HibasNev extends Exception{

}
class LetezoNev extends Exception{

}
class LetezoId extends Exception{

}
class ÜresNév extends Exception{

}