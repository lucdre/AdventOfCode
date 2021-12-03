import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public List<Integer> readFisherito() throws FileNotFoundException{
        int item = 0;

        Scanner scan = new Scanner(new File("input/day1Input.txt"));
        List<Integer> data = new ArrayList<Integer>();

        while(scan.hasNextInt()) {
            item = scan.nextInt();
            data.add(item);
        }
        scan.close();

        return data;
    }

    public int getIncreases1(List<Integer> data) {
        int start = -1;
        int increases = -1;

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) > start) {
                increases++;
            }
            start = data.get(i);
        }

        return increases;
    }

    public int getIncreases2(List<Integer> data) {
        int comparison = -1;
        int sum = 0;
        int increases = -1;

        //Go through the entire list
        for (int i = 0; i < data.size(); i++) {
            // If the list still has 3 or more numbers to read then we continue
            if (data.size() >= i+3) {
                // Add up the next 3 values
                for (int j = 0; j < 3; j++) {
                    sum += data.get(i+j);
                }
                // Increase the counter if what we added up is more than what we added up previously
                if (sum > comparison) {
                    increases++;
                }
                // Set the previous amount to what we just added
                comparison = sum;
                // Reset the sum for the next time we add up
                sum = 0;
            }
        }

        return increases;
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        List<Integer> data = new ArrayList<Integer>();

        try {
            data = day1.readFisherito();
        } catch (FileNotFoundException e) {
            System.out.println("File has not been found");
            e.printStackTrace();
        }

        System.out.println("Result of part 1 is: " + day1.getIncreases1(data));
        System.out.println("Result of part 2 is: " + day1.getIncreases2(data));
    }
}
