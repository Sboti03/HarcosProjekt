import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jatek {

    private List<Harcos> harcosok;
    Scanner sc;
    int korokszama = 0;

    public Jatek(List<Harcos> harcosok) {
        sc = new Scanner(System.in);

        System.out.println("Játék kezdete");
        System.out.println("...............");
        System.out.print("Kérem adja meg mi legyen a harcos neve: ");
        String name = sc.nextLine();
        System.out.print("Kérem adja meg milyen státusz sablonnal akar játszani: ");
        int statuszSablon = sc.nextInt();
        this.harcosok = harcosok;
        harcosok.add(0, new Harcos(name, statuszSablon));


    }

    private void menu() {
        System.out.println((korokszama + 1) + ". kör kezdete");
        for (int i = 0; i < harcosok.size(); i++) {
            if (i == 1) {
                System.out.print("(Saját) ");
            }
            System.out.println((i + 1) + ". " + harcosok.get(i).toString());

        }
        System.out.println("Válassza ki mit szeretne tenni");
        System.out.println("a - Megküzdeni egy harcossal");
        System.out.println("b - Gyógyulni");
        System.out.println("c - Kilépni");
        String valasz = sc.next();

        switch (valasz) {
            case "a":
                megKuzd();
                break;
            case "b":
                gyogyul();
                break;
            case "c":
                kilep();
                break;
            default:
                menu();
                break;
        }
    }

    public void megKuzd() {
        System.out.print("Adja meg melyik harcossal akar megküzdeni: ");
        String tmpIndex = sc.nextLine();
        int index = -1;
        try {
            index = Integer.parseInt(tmpIndex);
        } catch (Exception e) {
            System.out.println("Nem számot adott meg, próbálja újra");
            megKuzd();
        }
        index--;
        if (index < 0 || index > harcosok.size() - 1) {
            System.out.println("Érvénytelen számot adott meg, próbálja újra");
            megKuzd();
        }
        harcosok.get(0).megkuzd(harcosok.get(index));
        korVege();
    }

    public void gyogyul() {
        harcosok.get(0).gyogyul();
        korVege();
    }

    public void kilep() {

    }

    private void korVege() {
        korokszama++;
        if (korokszama % 3 == 0) {
            Random rnd = new Random();
            int rndIndex = rnd.nextInt(harcosok.size() - 1) + 1;
            System.out.println("Véletlen szerű harc");
            System.out.println("...............");
            System.out.println("A következő játékossal küzd meg");
            System.out.println(harcosok.get(rndIndex).toString());
            harcosok.get(rndIndex).megkuzd(harcosok.get(0));
            System.out.println("Harc eredménye");
            System.out.println("Saját harcos - " + harcosok.get(0).toString());
            System.out.println("Kihívó harcos - " + harcosok.get(rndIndex));
            for (Harcos h:harcosok) {
                h.gyogyul();
            }
        }
        menu();
    }


}
