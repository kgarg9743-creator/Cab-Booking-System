package cab;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


public class Cab {
    
    static Scanner sc = new Scanner(System.in);
    static double dst;
    static double dst2;
    
    public int[] lcn(){
        System.out.print("Enter your location(x coordinate): ");
        int x = sc.nextInt();
        System.out.print("Enter your location(y coordinate): ");
        int y = sc.nextInt();
        return new int[] {x,y};
    }
    
    
    int farecalc( int k){
        return (int) (15 + dst + (k*0.15));
    }
    /*


    --- YET TO CREATE ---
    void rgstr() throws IOException {
        try {
             int ik = 0;
            try (BufferedReader br = new BufferedReader(new FileReader("User.csv"))) {
                String i;
                while ((i = br.readLine())!= null){
                    ik++;
                }
            }
            int id = 900 + ik;
            FileWriter myobj = new FileWriter("driver.csv",true);
            System.out.print("Enter choice for registering new driver (true/false): ");
            boolean ch = sc.nextBoolean();
            sc.nextLine();

            if (ch) {
                System.out.print("Enter the name of driver: ");
                String sitm = sc.nextLine();

                int[] coor = lcn();

                myobj.write(id + "," +sitm + "," + true + "," + coor[0] + "," + coor[1] + "\n");

                System.out.println("Driver Registered Successfully!");

            }
            myobj.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
*/

    void rgstrUser() throws IOException{
        FileWriter fobj = new FileWriter("user.csv", true);
        System.out.print("Enter the chocie for registering the new user(true/false): ");
        boolean ch = sc.nextBoolean();
        int ik = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("User.csv"))) {
            String i;
            while ((i = br.readLine())!= null){
                ik++;
            }
        }
        int id = 100 + ik;
        sc.nextLine();
        if (ch){
            System.out.println("Enter the name of user: ");
            String sitm = sc.nextLine();
            ArrayList<usr> tobj = new ArrayList<>();
            int[] lcshn = lcn();
            fobj.write(id+","+sitm+","+"completed,"+lcshn[0]+","+lcshn[1]+"\n");
            tobj.add(new usr());
            fobj.close();
        }
    }
// checks for vacancy of driver
    double d(int ax,int bx,int ay,int by){
        return Math.pow(Math.pow(ax-bx, 2)+Math.pow(ay-by, 2),0.5);
    }
    
    String[] srch(int c) throws IOException{// user content
        try (BufferedReader br = new BufferedReader(new FileReader("User.csv"))) {
            String i;
            
            System.out.print("Enter the ur id: ");
            int eid = sc.nextInt();
            while ((i = br.readLine())!= null){
                String[] icitm = i.split(",");
                switch(c){
                    case 1:
                        if (icitm[0].equals(String.valueOf(eid))){
                            return icitm;
                        }
                        break;
                    case 2:
                        break;

                }  
            }
        }
        return new String[] {"not found","do something"};
    }

    

    int min_dis(String[] dta/* user's data to be given */) throws IOException{// driver alloted 
        
        ArrayList<double[]> dis = new ArrayList<>();
        int ax = Integer.parseInt(dta[3]);
        int ay = Integer.parseInt(dta[4]);
        try (BufferedReader br = new BufferedReader(new FileReader("driver.csv"))) {
            String i;
            br.readLine();
            while ((i = br.readLine())!= null){
                String[] ij = i.split(",");
                if (ij[2].equals("true")){
                    int bx = Integer.parseInt(ij[3]);
                    int by = Integer.parseInt(ij[4]);
                    double[] tdis = {d(ax, bx, ay, by), Integer.parseInt(ij[0])}; //{Math.pow(Math.pow(ax-bx, 2)+Math.pow(ay-by, 2),0.5), Integer.parseInt(ij[0])};
                    dis.add(tdis);
                }
            }
        }
        TreeMap<Double,Integer> min_dis = new TreeMap<>();
        for(double[] i : dis){
           min_dis.put(i[0], (int) i[1]); 
        }

        TreeMap<Double,Integer> nearby = new TreeMap<>(min_dis.subMap(0.0, 3.0));

        if(nearby.isEmpty()){
            return -1;
        }

        return nearby.firstEntry().getValue();
    }

    String[] alot(String tf, String tfc,int j,int k) throws IOException{
        ArrayList<String[]> mmm = new ArrayList<>();
        boolean f = false;
        String[] dname = null;
        try (BufferedReader br = new BufferedReader(new FileReader("driver.csv"))) {
            String i;
            while ((i = br.readLine())!= null){
                String[] ij = i.split(",");
                if (!f && ij[j].equals(tfc) && (k == Integer.valueOf(ij[0]))){
                    ij[j] = tf;
                    f = true;
                    dname = ij;
                } 
                mmm.add(ij);
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("driver.csv"))) {
            for (String[] row : mmm) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
            bw.close();
        }
        return dname;
    }

    String[] alotuser(String qf, String tc, String[] dc, int k) throws IOException{
        ArrayList<String[]> mma = new ArrayList<>();
        boolean fa = false;
        String[] dame = null;
        try (BufferedReader br = new BufferedReader(new FileReader("user.csv"))) {
            String it;
            while ((it = br.readLine())!= null){
                String[] ij = it.split(",");
                if (!fa && ij[k].equals(String.valueOf(tc)) && ((Integer.valueOf(ij[0]))==(Integer.valueOf(dc[0])))){
                    ij[k] = qf;
                    fa = true;
                    dame = ij;
                } 
                mma.add(ij);
            }
            
        }
        try (BufferedWriter bwa = new BufferedWriter(new FileWriter("user.csv"))) {
            for (String[] ro : mma) {
                bwa.write(String.join(",", ro));
                bwa.newLine();
            }

            bwa.close();
        }
        
        return dame;
    }
    
    public static void main(String[] args) throws IOException {
        Cab cu = new Cab();
        
    }
}