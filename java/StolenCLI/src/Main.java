import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ArrayList<Stolens> data = new ArrayList<>();

    public static void main(String[] args) {
        fileRead();

        //1,
        int records = 0;
        for (Stolens stolen : data){
            records++;
        }
        System.out.println("1) Beolvasott rekordok száma: " + records);

        //2,
        Stolens lopas = new Stolens();
        int price = 0;
        for (Stolens stolen : data){
            if (stolen.getPrice() > price){
                lopas = stolen;
                price = stolen.getPrice();
            }
        }
        String lopase = lopas.isIs_stolen() ? "igen" : "nem";
        System.out.println("2) Legértékesebb lopás: ");
        System.out.println("- ["+ lopas.getAsset_id()+"] "+ lopas.getAsset_name() + " | Ár: " + lopas.getPrice() + " | Lopás: " + lopase + "\n  Tulajdonos: " + lopas.getOwner_first_name() + " " + lopas.getOwner_last_name() + " ("+ lopas.getOwner_birthday()+")" + "\n  Tolvaj: " + lopas.getThief_first_name() + " " + lopas.getThief_last_name());

        //3,
        ArrayList<Stolens> ismert = new ArrayList<>();
        for (Stolens stolen : data){
            if (!Objects.equals(stolen.getThief_first_name(), "") || !Objects.equals(stolen.getThief_last_name(), "")){
                ismert.add(stolen);
            }
        }

        System.out.println("3) Ismert tolvajjal megadott lopások (tulajdonos születésnap szerint csökkenő): ");
        ismert.sort(Comparator.comparing(Stolens::getOwner_birthday).reversed());
        for (Stolens stolen : ismert){
            lopase = stolen.isIs_stolen() ? "igen" : "nem";
            System.out.println("- ["+ stolen.getAsset_id()+"] "+ stolen.getAsset_name() + " | Ár: " + stolen.getPrice() + " | Lopás: " + lopase + "\n  Tulajdonos: " + stolen.getOwner_first_name() + " " + stolen.getOwner_last_name() + " ("+ stolen.getOwner_birthday()+")" + "\n  Tolvaj: " + stolen.getThief_first_name() + " " + stolen.getThief_last_name());

        }
        //4,
        Set<String> nevek = new HashSet<>();
        for (Stolens stolen : data) {
            String nev  = (stolen.getOwner_first_name() + " " + stolen.getOwner_last_name()).trim();
            String tnev = (stolen.getThief_first_name() + " " + stolen.getThief_last_name()).trim();

            if (!nev.isEmpty())  nevek.add(nev);
            if (!tnev.isEmpty()) nevek.add(tnev);
        }

        System.out.println(nevek);

        System.out.println("4) Egyedi nevek száma: "+ nevek.size());
        int randomIndex = new Random().nextInt(nevek.size());
        int i = 0;
        for () // get random choice and write --> 

    }

    public static void fileRead(){
        File file = new File("stolen.csv");
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] p = line.split(",", -1);
                if (p.length < 12) {
                    System.out.println("Hibás sor (" + p.length + " mező): " + line);
                    continue;
                }

                Stolens s = new Stolens();
                s.setAsset_id(Integer.parseInt(p[0]));
                s.setAsset_name(p[1]);
                s.setPrice(parseOrZero(p[2]));
                s.setIs_stolen("1".equals(p[3]));
                s.setOwner_id(Integer.parseInt(p[4]));
                s.setOwner_first_name(p[5]);
                s.setOwner_last_name(p[6]);
                s.setOwner_birthday(p[7]);
                s.setThief_id(parseOrZero(p[8]));
                s.setThief_first_name(p[9]);
                s.setThief_last_name(p[10]);
                s.setThief_birthday(p[11]);

                data.add(s);
                //System.out.println(Arrays.toString(p));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int parseOrZero(String s) {
        return s.isEmpty() ? 0 : Integer.parseInt(s);
    }
}