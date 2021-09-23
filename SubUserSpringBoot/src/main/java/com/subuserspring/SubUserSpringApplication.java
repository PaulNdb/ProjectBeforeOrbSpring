package com.subuserspring;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class SubUserSpringApplication implements CommandLineRunner {

    static final String DB_URL = "jdbc:mysql://localhost:3306/subinfo";
    static final String USER = "paul";
    static final String PASS = "paulndb";
    static final String QUERY = "SELECT * FROM subinfo.subscription";

    public static void main(String[] args) {
        SpringApplication.run(SubUserSpringApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

        Scanner nameSub = new Scanner(System.in);
        Scanner editSub = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {

            System.out.println("Do you want the list of subscribers ?[Y]:yes [N]:no");
            String str = nameSub.next();

            if (Objects.equals(str, "Y")) {
                while(rs.next()) {
                    System.out.println(rs.getString("username"));
                }
                System.out.println("Do you want to modify the list ?[Y]:yes [N]:no");
                String str2 = editSub.next();
                if (Objects.equals(str2, "Y")) {
                    System.out.println("work modify");
                }
                if (Objects.equals(str2, "N")) {
                    System.out.println("See you soon");
                    System.exit(0);
                }
            }
            if (Objects.equals(str, "N")) {
                System.out.println("See you soon");
                System.exit(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }


