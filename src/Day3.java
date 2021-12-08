import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {

    public List<String> readFisherito() throws FileNotFoundException {
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
        int[][] arrayData = new int[data.size()][12]; //AND THIS (12)
        String line = "";

        for (int i = 0; i < data.size(); i++) {
            line = data.get(i);

            //TODO change this then
            for (int j = 0; j < 12; j++) {
                arrayData[i][j] = Character.getNumericValue(line.charAt(j));
            }  
        }

        return arrayData;
    }

    public String part1(int[][] data) {
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

        return String.valueOf(power);
    }

    public int part2(List<String> dataList) {
        List<String> dataInitialG = new ArrayList<String>(), dataInitialE = new ArrayList<String>();
        for (int i = 0; i < dataList.size(); i++) {
            dataInitialG.add(dataList.get(i));
            dataInitialE.add(dataList.get(i));
        }
        List<String> dataUpdateG = new ArrayList<String>(), dataUpdateE = new ArrayList<String>();
        char common;
        char lessCommon;
        boolean continueG = true;
        boolean continueE = true;
        int o2;
        int c02;


        for (int i = 0; i < 12; i++) { // AAAND this
            
            if(continueG) {
                common = getCommon(dataInitialG, i, false);
                dataUpdateG.clear();
                for (int j = 0; j < dataInitialG.size(); j++) {
                    if (common == dataInitialG.get(j).charAt(i)){
                        dataUpdateG.add(dataInitialG.get(j));
                    }
                }

                dataInitialG.clear();
                for (int j = 0; j < dataUpdateG.size(); j++) {
                    dataInitialG.add(dataUpdateG.get(j));
                }
            }

            if(continueE) {
                lessCommon = getCommon(dataInitialE, i, true);
                dataUpdateE.clear();
                for (int j = 0; j < dataInitialE.size(); j++) {
                    if (lessCommon == dataInitialE.get(j).charAt(i)){
                        dataUpdateE.add(dataInitialE.get(j));
                    }
                }

                dataInitialE.clear();
                for (int j = 0; j < dataUpdateE.size(); j++) {
                    dataInitialE.add(dataUpdateE.get(j));
                }
            }                  
          
            if (dataInitialE.size() == 1)
                continueE = false;

            if (dataInitialG.size() == 1)
                continueG = false;
        }
        
        o2 = Integer.parseInt(dataInitialE.get(0), 2);
        c02 = Integer.parseInt(dataInitialG.get(0), 2); 
        
        
        return o2 * c02;
    }

    public char getCommon(List<String> data, int pos, boolean toggle) {
        int zeros = 0;
        int ones = 0;

        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).charAt(pos) == '1') ones++;
            else zeros++;              
        }

        if (ones >= zeros) {  
            if(!toggle) return '1'; 
            else return '0'; 
        } else { 
            if(!toggle) return '0';
            else return '1';   
         }
    }
    
    public static void main(String[] args) {
        
        Day3 day3 = new Day3();
         
        try {
            //PART 1
            List<String> data = day3.readFisherito();
            String part1Result = day3.part1(day3.transformToArray(data));
            System.out.println("Result of part 1: " + part1Result);

            //PART 2
            int part2Result = day3.part2(data);          
            System.out.println("Result of part 2: " + part2Result);
            
            
        } catch (FileNotFoundException e) {
            System.out.println("The file has not been found! :(");
            e.printStackTrace();
        }

        
    }
}
