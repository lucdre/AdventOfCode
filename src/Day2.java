import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {

    public List<String> readFisherito() throws FileNotFoundException{
        String item = "";

        Scanner scan = new Scanner(new File("input/day2Input.txt"));
        List<String> data = new ArrayList<String>();

        while(scan.hasNext()) {
            item = scan.next();
            data.add(item);
        }
        scan.close();

        return data;
    }

    public int part1(List<String> data) {

        int horizontal = 0;
        int depth = 0;

        for (int i = 0; i < data.size(); i++) {
            if (data.size() >= i+1) {
                switch (data.get(i)) {
                    case "forward":
                        horizontal += Integer.parseInt(data.get(i+1));
                        break;
                    case "up":
                        depth -= Integer.parseInt(data.get(i+1));
                        break;
                    case "down":
                        depth += Integer.parseInt(data.get(i+1));
                        break;
                    default:
                        break;
                }
            }
        }

        return horizontal*depth;
    }

    public int part2(List<String> data) {

        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (int i = 0; i < data.size(); i++) {
            if (data.size() >= i+1) {
                switch (data.get(i)) {
                    case "forward":
                        horizontal += Integer.parseInt(data.get(i+1));
                        depth += aim*Integer.parseInt(data.get(i+1));
                        break;
                    case "up":
                        aim -= Integer.parseInt(data.get(i+1));
                        break;
                    case "down":
                        aim += Integer.parseInt(data.get(i+1));
                        break;
                    default:
                        break;
                }
            }
        }

        return horizontal*depth;
    }
    
    public static void main(String[] args) {

        Day2 day2 = new Day2();
        

        List<String> data = new ArrayList<String>();
        try {
            data = day2.readFisherito();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("The result of part 1: " + day2.part1(data));
        System.out.println("The result of part 2: " + day2.part2(data));
    }
    
}
