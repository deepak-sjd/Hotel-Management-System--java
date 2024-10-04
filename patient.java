
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public  class patient{
 
    // used connection interface
     private Connection connection;

     private Scanner scanner ;
 
     // patient constructor create
     public patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;

     }

     // now create all method of patient

     public  void addPatient(){
        System.out.print("Enter Patient Name: ");
        String name = scanner.next();

        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();

        System.out.print("Enter Patient PhoneNumber: ");
        String PhoneNumber = scanner.nextLine();

        System.out.print("Enter Patient Gender: ");
        String gender = scanner .next();

        // create a try catch blok due to connect database used try catch du to when we connect database sql so one exception is coming so we catch here
        
        try{
             String query = "INSERT INTO Patients(name, age,PhoneNumber,gender) VALUES(?, ?, ?,?)";
             PreparedStatement  preparedStatement = connection.prepareStatement(query);
             //set value of four ?
             preparedStatement.setString(1, name);
             preparedStatement.setInt(2, age);
             preparedStatement.setString(3, PhoneNumber);
             preparedStatement.setString(4, gender);
                // here chekc affected row if yes then 
             int affectedRows = preparedStatement.executeUpdate();
             if(affectedRows>0){
                System.out.println("Patient Added Successfully!! ");
             }
             else {
                System.out.println("Failed to add Patient!!");
             }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
     }

     // second method for view patient

     public  void  viewPatients(){
        String query = "select *from patients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // resultset use for which data comes from database(table) hold and uske upar next name pointer used 
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+--------------+-------------------------+----------------+-----------------------+--------------");
            System.out.println("|  Patient ID  |    Name                 |   Age          |  Phonenumber          |     Gender  |");
            System.out.println("+--------------+-------------------------+----------------+-----------------------+--------------");
            
            while(resultSet.next()){
                // this is access from sql
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String phonenumber = resultSet.getString("phonenumber");
                String gender = resultSet.getString("gender");
                // this is count space between very |patientid| similar all
                System.out.printf("|%-15s|%-26s|%-17s|%-24s|%-14s|\n" , id, name , age, phonenumber, gender);
                System.out.println("+--------------+-------------------------+----------------+-----------------------+--------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     //check patient
     public  boolean  getPatientById(int id){
        String query = "SELECT*FROM patients WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet=preparedStatement.executeQuery();
            // if data come from database then return true other wise return false;
            if(resultSet.next()){
                return  true;
            }
            else{
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if data not coming other reason so return always false;
        return false;
     }

}