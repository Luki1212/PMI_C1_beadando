import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class XmlBeolvasó {
    public static ArrayList<Bank> readPillsFromXml(String filepath){
        ArrayList<Bank> szamla = new ArrayList<>();

        try{
            DocumentBuilderFactory dBf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = dBf.newDocumentBuilder();
            Document doc = dB.parse(filepath);
            Element root =doc.getDocumentElement();
            NodeList child = root.getChildNodes();
            Node node;
            for (int i = 0; i < child.getLength(); i++) {
                node = child.item(i);
                if(node.getNodeType()== Node.ELEMENT_NODE){
                    NodeList childnode= node.getChildNodes();
                    String név ="",id ="",pénz="";
                    for (int j = 0; j < childnode.getLength(); j++) {
                        if(childnode.item(j).getNodeType()==Node.ELEMENT_NODE){
                            switch (childnode.item(j).getNodeName()) {
                                case "név" -> név = childnode.item(j).getTextContent();
                                case "id" -> id = childnode.item(j).getTextContent();
                                case "pénz" -> pénz = childnode.item(j).getTextContent();
                            }
                        }
                    }
                    szamla.add(new Bank(név,id,Integer.parseInt(pénz)));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return szamla;
    }
}
