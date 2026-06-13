package cab;

import java.io.*;
import java.util.*;

class usr extends Thread{
    public static String[] hello;
    String m;
    static Cab n = new Cab();
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            Random r = new Random();
            double dis = 0;
            double ds = 0;
            String[] udata;
            udata = n.srch(1);
            int kju = n.min_dis(udata);
            System.out.println(Arrays.toString(udata));
            if (!(udata[0].equals("not found")) ){
                try {
                    n.alotuser("Booking","completed",udata,2);
                    int x = Integer.valueOf(udata[3]);
                    int y = Integer.valueOf(udata[4]);
                    System.out.print("Enter your destination location(x coordinate): ");
                    int x1 = sc.nextInt();
                    System.out.print("Enter your destination location(y coordinate): ");
                    int y1 = sc.nextInt();
// user input finished

                    double dst = Math.pow(Math.pow(x-x1, 2)+Math.pow(y-y1, 2),0.5);
                    System.out.println("You are "+udata[1]);
// driver gets a reuqest.............

                    

                    hello = n.alot("false","true",2,kju);
                    if ((hello != null)){
                        System.out.println(hello[1] + " is alloted to you");
                        String[] temp = n.alotuser("Waiting","Booking",udata,2);
                        if (temp != null){
                            int cgp = 0;
                            double dstance = Math.pow(Math.pow(x-Integer.valueOf(hello[3]), 2)+Math.pow(y-Integer.valueOf(hello[4]), 2),0.5);
                            while (cgp < dstance) {
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {}
                                cgp += r.nextDouble(10);
                            }
                            System.out.println(hello[1] + " reached you");
                            n.alotuser("Riding","Waiting",udata,2);
                            while (ds < dst) {
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {}
                                ds += r.nextDouble(1);
                            }
                            n.alot("true","false",2,kju);
                            System.out.println("you reached your location");
                            n.alotuser("completed","Riding",udata,2);//yes
                            n.alotuser(String.valueOf(x1),udata[3],udata,3);//yes
                            n.alotuser(String.valueOf(y1),udata[4],udata,4);//yes
                            n.alot(String.valueOf(x1),hello[3],3,kju);
                            n.alot(String.valueOf(y1),hello[4],4,kju);
                        } else {
                            System.out.println("user already booking cab");
                        }
                    } else {
                        System.out.println("no driver near you");
                    }
                } catch (IOException ex) { 
                    System.out.println(ex);
                }
                
            } else {
                System.out.println("not found");
            }
        } catch (IOException ex) {
        }
        
    }
}
public class user {
    static Cab cobj = new Cab(); 
      
    public static void main(String[] args) throws IOException {
        Scanner ch = new Scanner(System.in);        
        Cab ns = new Cab();
        int j = 0;
        while (j<5) {
            System.out.println("1. Register User");
            System.out.println("2. Book Cab");
            System.out.println("3. Exit");

            int k = ch.nextInt();

            switch(k){
                case 1:
                    ns.rgstrUser();
                    break;

                case 2:
                    usr u = new usr();
                    u.run();
                    break;
                case 3:
                    return ;
            }
            j++;
        }

    }    
}