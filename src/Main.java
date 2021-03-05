import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws TransformerException, IOException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число N: ");
        int number = scanner.nextInt();
        DBConnect dbConnect = new DBConnect();
        dbConnect.queryDeleteAndInsert(number);
        dbConnect.querySelect();
        XMLCreator xmlCreator = new XMLCreator();
        xmlCreator.entriesElement();
    }
}
