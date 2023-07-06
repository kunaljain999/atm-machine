package bankPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class UserLoginDetails {

    private String userName;
    private String password;
    private double uBalance;
    Map<String, String> map = new HashMap<>();
    static List<String> list = new ArrayList<>();
    private final String filePath = "C:\\Java_Console_app\\Customer_Details.txt";

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public UserLoginDetails() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void init() {
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) { // readline = next lines // read file till its value is not null
                // line= br.readLine();
                list.add(line);
                String arr[] = line.split(",");
                map.put(arr[0], arr[1]);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public boolean isValidUser() {

        if (map.containsKey(userName)) {
            String pass = map.get(userName); // we get value here ie. password
            if (pass.equals(password)) {

                return true;
            }
        }
        return false;
    }

    public double getbalance(String userName) {
        for (String st : list) {
            String userDetails[] = st.split(",");
            if (userName.equals(userDetails[0])) {
                uBalance = Double.parseDouble(userDetails[4]);
                return uBalance;
            }

        }
        return uBalance;
    }

    public void login() {

        Scanner sc = new Scanner(System.in);
        boolean userLogin = false; // we set this as false becouse when it come inside if condition then show
        // operation only hen its enter valid and condition true

        for (int i = 0; i < 3; i++) {

            System.out.println("Enter a Username :");
            userName = sc.next();
            System.out.println("Enter a Password :");
            password = sc.next();
            init();
            if (isValidUser()) {
                System.out.println("Hey " + userName + "! You're login Successfully!! ");
                userLogin = true;
                System.out.println(
                        "-------------------------------------------------------------------------------------------------------");
                break;
            } else {
                System.out.println("Invalid Username/Password" + userName + "/" + password);

                System.out.println(
                        "-------------------------------------------------------------------------------------------------------");

            }
        }

        if (userLogin) { // valid then only show operation
            Operation imp = new Operation(userName);
            imp.show();
        }
    }

    public void registration() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Enter account holder name ");
        String accHolderName = sc.next();
        System.out.println(" Enter User Name : ");
        userName = sc.next();
        System.out.println("Enter your  passward : ");
        password = sc.next();
        File file = new File(filePath);
        int accNumber = generateAccountNo();
        BufferedWriter br = new BufferedWriter(new FileWriter(file, true)); // always need to open file in append mode
        // so that the previous data don't get erase
        String details = userName + "," + password + "," + accNumber + "," + accHolderName + "," + 0.0;
        br.write(details); // write the date into file
        br.newLine();
        br.close();

        map.put(userName, password);

        System.out.println("User: " + userName + " your account No is = " + accNumber);

        if (list.size() == 0) {
            init();
        } // when list size is zero then only we read data from file and add into the list
        // for further operation like withdraw.

        Operation imp = new Operation(userName);
        imp.show();

    }

    public void fileUpdate() {
        File file = new File(filePath);

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            if (list.size() > 0) {
                for (String str : list) {
                    br.write(str);
                    br.newLine();
                }
                br.close();

            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void updateBalance(String userName, double balance) {
        int index = -1;
        String updated = null;
        for (String str1 : list) {
            index++;
            String UserArr[] = str1.split(",");
            if (userName.equals(UserArr[0])) {
                updated = str1.substring(0, str1.lastIndexOf(","));
                list.set(index, updated + "," + balance);
                break;
            }
        }
        fileUpdate(); // write updated data into file here
    }

    public int generateAccountNo() {

        Random random = new Random();
        return 10000 + random.nextInt(2000);

    }

}