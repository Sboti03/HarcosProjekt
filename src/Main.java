import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Harcos> harcosok = new ArrayList<>();

        harcosok.add(new Harcos("Kálmán", 1));
        harcosok.add(new Harcos("Árpád", 2));
        harcosok.add(new Harcos("Vezér", 3));
        ReadIn(harcosok);
        for(Harcos h :harcosok) {
            System.out.println(h.toString());
        }
        new Jatek(harcosok);
    }

    public static void ReadIn(List<Harcos> harcosok) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("harcosok.csv"));

        String currentLine;
        while ((currentLine = bf.readLine()) != null) {
            String[] tmp = currentLine.split(";");
            harcosok.add(new Harcos(tmp[0], Integer.parseInt(tmp[1])));
        }
    }



}