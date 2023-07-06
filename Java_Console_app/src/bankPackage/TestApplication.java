package bankPackage;

import java.io.IOException;
import java.util.Scanner;

public class TestApplication {

    static {

        System.out.println("@@-------Wellcome to the Bank Application---------@@");
        System.out.println("DO you want to Login/Register ?");
        System.out.println("Press 1 for login");
        System.out.println("Press 2 for Register");
        System.out.println("press 3 for EXIT");
    }


    public static void main(String[] args) {

        UserLoginDetails userLogin = new UserLoginDetails();
        Scanner sc  = new Scanner(System.in);
        int num =sc.nextInt();
        if(num==1) {
            userLogin.login();
        }
        else if (num==2){
            System.out.println("@@----------WELLCOME TO REGISTRARTION PAGE--------------@@");
            try {
                userLogin.registration();
            } catch (IOException e) {
                e.printStackTrace();

            }
            System.out.println();
        }else{

            System.exit(0);
        }
    }
}