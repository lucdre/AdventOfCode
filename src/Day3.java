import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {

    public List<String> readFisherito() throws FileNotFoundException{
        String item = "";
        List<String> data = new ArrayList<String>(); 

        Scanner scan = new Scanner(new File("input/day3Input.txt"));

        while(scan.hasNext()) {
            item = scan.next();
            data.add(item);
        }

        scan.close();

        return data;
    }

    public int[][] transformToArray(List<String> data) {
        int[][] arrayData = new int[data.size()][12];
        String line = "";

        for (int i = 0; i < data.size(); i++) {
            line = data.get(i);

            for (int j = 0; j < 12; j++) {
                arrayData[i][j] = Character.getNumericValue(line.charAt(j));
            }  
        }

        return arrayData;
    }

    public int part1(int[][] data) {
        String gamma = "";
        String epsilon = "";
        int zeros = 0;
        int ones = 0;

        for (int i = 0; i < data[0].length; i++) {
            for (int j = 0; j < data.length; j++) {
                switch (data[j][i]) {
                    case 0:
                        zeros++;
                        break;
                    case 1:
                        ones++;
                        break;
                
                    default:
                        break;
                }
            }

            if (ones > zeros) {
                gamma += 1;
                epsilon += 0;
            } else {
                gamma += 0;
                epsilon += 1;
            }

            zeros = 0;
            ones = 0;
        }

        int decimalGamma = Integer.parseInt(gamma, 2);
        int decimalEpsilon = Integer.parseInt(epsilon, 2);

        int power = decimalGamma * decimalEpsilon;

        return power;
    }
    
    public static void main(String[] args) {
        
        Day3 day3 = new Day3();
        
        try {
            List<String> data = day3.readFisherito();
            System.out.println("Result of part 1: " + day3.part1(day3.transformToArray(data)));
            
        } catch (FileNotFoundException e) {
            System.out.println("The file has not been found! :(");
            e.printStackTrace();
        }
    }
}
