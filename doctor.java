import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class doctor {

    // used connection interface
     private Connection connection;

     //private Scanner scanner ; // scanner class not used here so clear from here
 
     // patient constructor create
     public doctor(Connection connection, Scanner scanner){
        this.connection = connection;
       // this.scanner = scanner;

     }

     // now create all method of patient not used in doctor method because doctors add admin from backend

    //  public  void addPatient(){
    //     System.out.print("Enter Patient Name: ");
    //     String name = scanner.next();

    //     System.out.print("Enter Patient Age: ");
    //     int age = scanner.nextInt();

    //     System.out.print("Enter Patient PhoneNumber: ");
    //     String PhoneNumber = scanner.nextLine();

    //     System.out.print("Enter Patient Gender: ");
    //     String gender = scanner .next();

    //     // create a try catch blok due to connect database used try catch du to when we connect database sql so one exception is coming so we catch here
        
    //     try{
    //          String query = "INSERT INTO Patients(name, age,PhoneNumber,gender) VALUES(?, ?, ?,?)";
    //          PreparedStatement  preparedStatement = connection.prepareStatement(query);
    //          //set value of four ?
    //          preparedStatement.setString(1, name);
    //          preparedStatement.setInt(2, age);
    //          preparedStatement.setString(3, PhoneNumber);
    //          preparedStatement.setString(4, gender);
    //             // here chekc affected row if yes then 
    //          int affectedRows = preparedStatement.executeUpdate();
    //          if(affectedRows>0){
    //             System.out.println("Patient Added Successfully!! ");
    //          }
    //          else {
    //             System.out.println("Failed to add Patient!!");
    //          }
    //     }
    //     catch(SQLException e){
    //         e.printStackTrace();
    //     }
    //  }

     // second method for view patient

     public  void  viewdoctors(){
        String query = "select *from doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // resultset use for which data comes from database(table) hold and uske upar next name pointer used 
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("doctors: ");
            System.out.println("+--------------+-------------------------+-------------------------------+--------------------");
            System.out.println("|  Doctor ID   |    Name                 |     Specialization            |     Phonenumber   |");
            System.out.println("+--------------+-------------------------+-------------------------------+--------------------");
            
            while(resultSet.next()){
                // this is access from sql
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                String phonenumber = resultSet.getString("phonenumber");
                // this is count space between very |patientid| similar all
                System.out.printf("|%-15s|%-25s|%-29s|%-18s|\n", id, name, specialization, phonenumber);
                System.out.println("+--------------+-------------------------+-------------------------------+--------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     //check patient
     public  boolean  getDoctorById(int id){
        String query = "SELECT*FROM doctors WHERE id = ?";
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
