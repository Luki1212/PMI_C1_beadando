import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
            String[] innerArray = new String[3];
            innerArray[0]=bank.getName();
            innerArray[1]=bank.getId();
            innerArray[2]=String.valueOf(bank.getPenz());
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

        new SplashScreen(new Bank("","",0));
    }

    public static String Listázás(ArrayList<Bank> szamla) {//                                                 Ablakooooos!!!!!!!!!!!!!!!!!
        String sorok="";

        for (int i = 0; i < szamla.size(); i++) {

            sorok+=(szamla.get(i).getName() + " - " + szamla.get(i).getId() + " - " + szamla.get(i).getPenz());
            if(i<szamla.size()-1)sorok+="\n";
        }
        return sorok;
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
                System.err.println("Már létezik ilyen nevü számla tulajdonos, elöbb törölje a már meglévőt és kezdje ujra a folyamatot");
            } catch (ÜresNév hn) {
                System.err.println("nem adhat meg üres nevet");
            }
        new Ablak(new Bank("", "",0));

        }
        String id = "";
        String id2;
        boolean igaz = false;
        System.out.print("Üsse be az id-t (min 2, max 20 karakter): \n");
        while (igaz == false) {

            try {
                id = scanner.next();

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
                System.err.println("nem jo id hossz \nProbálja ujra");
            } catch (LetezoId hi) {
                System.err.println("Már Létezik ilyen id-val rendelkező számla \nProbálja ujra");
            }

        }
        int pénz = 0;
        while (true) {

            System.out.println("Üsse be az új pénz összeget");
            pénz = 0;
            try {
                pénz = scanner.nextInt();
                scanner.nextLine();

                break;
            } catch (InputMismatchException e) {
                System.err.println("nem szám Pénz értéke");
                scanner.nextLine();
            }

        }
    }
    public static void UjszamlaTeljes(String név,String id, String egyenleg) {//                                         ablakosra át ir!!!!!!!!!!!!!!!!!!!!!!!!!!!

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
                System.err.println("Már létezik ilyen nevü számla tulajdonos, elöbb törölje a már meglévőt és kezdje ujra a folyamatot");
            } catch (ÜresNév hn) {
                System.err.println("nem adhat meg üres nevet");
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
                System.err.println("nem jo id hossz \nProbálja ujra");
            } catch (LetezoId hi) {
                System.err.println("Már Létezik ilyen id-val rendelkező számla \nProbálja ujra");
            }

        }

        while (true) {
            try {
                break;
            } catch (InputMismatchException e) {
                System.err.println("nem szám Pénz értéke");
            }

        }
        new Ablak(new Bank("","",0));
    }

        public static void UjszamlaNév() {//                                         ablakosra át ir!!!!!!!!!!!!!!!!!!!!!!!!!!!
            String nev="";
            while(true){
                System.out.println("adja meg a Bankszámla tulajdonos nevét: ");
                nev=scanner.nextLine();
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
                    System.err.println("Már létezik ilyen nevü számla tulajdonos, elöbb törölje a már meglévőt és kezdje ujra a folyamatot");
                }catch (ÜresNév hn){
                    System.err.println("nem adhat meg üres nevet");
                }


            }
            String id = "";
            boolean Hamis=false;
            System.out.print("Üsse be az id-t (min 2, max 20 karakter): \n");
            while(Hamis==false){

                try{
                    id = scanner.next();

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
                    System.err.println("nem jo id hossz \nProbálja ujra");
                }
                catch (LetezoId hi){
                    System.err.println("Már Létezik ilyen id-val rendelkező számla \nProbálja ujra");
                }

            }
            int pénz =0;
            while(true){

                System.out.println("Üsse be az új pénz összeget");
                pénz =0;
                try{
                    pénz = scanner.nextInt();
                    scanner.nextLine();

                    break;
                }
                catch (InputMismatchException e){
                    System.err.println("nem szám Pénz értéke");
                    scanner.nextLine();
                }

            }

        szamla.add(new Bank(nev,id,pénz));
        System.out.println("Uj számla sikeresen hozzáadva");
        new Ablak(new Bank("","",0));
    }
    public static void Modositás(ArrayList<Bank> szamla) {//                                       Ablakooooos!!!!!!!!!!!!!!!!!!!!!
        System.out.println("Üsse be a számla id-ját a modositáshoz: ");
        String id = scanner.nextLine();
        int choice = -1;
        for (Bank szamlak : szamla) {

            if (szamlak.getId().equals(id)) {


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
                            System.err.println("Nem létező opció.");
                            break;
                        }
                        if(choice==0){
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.err.println("Nem létező opció.");
                        scanner.nextLine();
                    }
                }
                break;
            }

        }
        if(choice!=0) System.err.println("Nem létezik ilyen id-val rendelkező számla");

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
                        System.err.println("Már Létezik ilyen névvel számla \nProbálja ujra");
                    }catch (ÜresNév hn){
                        System.err.println("nem adhat meg üres nevet \nProbálja ujra");
                    }

                }
                szamla.set(szamla.indexOf(szamlak), new Bank(név, id, szamlak.getPenz()));
            }
        }
        System.out.println("Számla tulajdonos neve sikeresen modosítva\n");
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
                        System.err.println("Már Létezik ilyen idval számla \nProbálja ujra");
                    }catch (ÜresNév hn){
                        System.err.println("nem adhat meg üres idt \nProbálja ujra");
                    }

                }
                szamla.set(szamla.indexOf(szamlak), new Bank(név, id, szamlak.getPenz()));
            }
        }
        System.out.println("Számla id-ja sikeresen modosítva\n");
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
                        szamla.set(szamla.indexOf(szamlak), new Bank(szamlak.getName(), id, pénz));
                        break;
                    }
                    catch (Exception e){
                        System.err.println("Nem szám Pénz érték!");
                        scanner.nextLine();
                    }
                }
            }
        }
        System.out.println("Számla pénz összege sikeresen módosítva.\n");
        return;
    }
    public static void Törlés(String név) {

        for (Bank szamlak : szamla) {
            if (szamlak.getName().equals(név)) {
                szamla.remove(szamlak);
                return;
            }
        }
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