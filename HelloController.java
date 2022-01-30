package com.hammad.jfx1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.awt.Desktop;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {



    

    public HelloController() throws SQLException {
    }

    //add user fxml
    @FXML
    private TextField email;

    @FXML
    private TextField fname;

    @FXML
    private TextField license;

    @FXML
    private TextField lname;

    @FXML
    private TextField phone;

    //add car fxml
    @FXML
    private ChoiceBox<String> addCarMake;

    private String[] carMakes = {"Toyota","KIA","Hyundai","Honda", "Nissan", "Ford", "Mitsubishi", "Mazda"};






    @FXML
    private ChoiceBox<String> carYear;

    private  String[] years = {"2015","2016","2017","2018","2019","2020","2021","2022"};

    @FXML
    private TextField addCarModel;

    @FXML
    private TextField addPlateNo;

    @FXML
    private RadioButton petrolRadio;

    @FXML
    private RadioButton dieselRadio;

    @FXML
    private TextField addCarCost;

    //rent fxml
    @FXML
    private DatePicker rentDate;






    //view rented cars
    @FXML
    private  ChoiceBox<String> dropDownCarPlate;

    @FXML
    private ChoiceBox<String> rentedLicenseDropDown;

    @FXML
    private TextField daysRentedInput;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField rentedFullName;




    //rent out the car
    @FXML
    private ChoiceBox<String> rentCarPlateDropDown;

    @FXML
    private ChoiceBox<String> rentUserLicenseDropDown;











    String carPlate = "";

    double result = 0;
    @FXML
    void calculate(ActionEvent event) throws SQLException {


        double daysRented = Double.parseDouble(daysRentedInput.getText());
        double costPerDay= 0;
        carPlate = dropDownCarPlate.getValue();


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        ObservableList<String> list = FXCollections.observableArrayList();
        PreparedStatement stmt = con.prepareStatement("select COST_PER_DAY from cars " +
                "where CAR_PLATE_NO = '"+carPlate+"';");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            costPerDay = rs.getDouble("COST_PER_DAY");
        }

        result = daysRented * costPerDay;

        resultLabel.setText(Double.toString(result));
    }

    @FXML
    void updateValues(MouseEvent mouseEvent){
        carPlate = dropDownCarPlate.getValue();
    }

    @FXML
    void createInvoice(ActionEvent event) throws IOException, SQLException {

        String fullName = rentedFullName.getText();
        String license = rentedLicenseDropDown.getValue();
        String plate = dropDownCarPlate.getValue();
        String make="";
        String model="";
        String daysRented = daysRentedInput.getText();
        String invoiceResult = Double.toString(result);


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        PreparedStatement stmt = con.prepareStatement("select MAKE from cars " +
                "where CAR_PLATE_NO = '"+dropDownCarPlate.getValue()+"';");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            make = rs.getString("MAKE");
        }

        PreparedStatement stmt1 = con.prepareStatement("select MODEL from cars " +
                "where CAR_PLATE_NO = '"+dropDownCarPlate.getValue()+"';");
        ResultSet rs1 = stmt1.executeQuery();
        while(rs1.next()){
            model = rs1.getString("MODEL");
        }












        GenerateInvoice generateInvoice = new GenerateInvoice(fullName,license,plate, make, model,daysRented,invoiceResult);
        generateInvoice.generateBill();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Invoice Generated");
        alert.show();

        File file = new File("C:\\Users\\Hammad\\Desktop\\CAT201 PROJECT\\invoice.pdf");
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }









    @FXML
    void addUser(ActionEvent event)  {
        try {
            PreparedStatement stmt = con.prepareStatement("insert into users values(?,?,?,?,?);");
            stmt.setString(1, fname.getText());
            stmt.setString(2, lname.getText());
            stmt.setString(3, email.getText());
            stmt.setString(4, phone.getText());
            stmt.setString(5, license.getText());
            stmt.execute();
            stmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Added");
            alert.setHeaderText("New user added");
            alert.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Database Error");
            error.setHeaderText(e.getMessage());
            error.show();
        }

    }

    @FXML
    void addCar(ActionEvent event) {
        try {
            String fuelType = "";

            if (petrolRadio.isSelected()){
                fuelType = petrolRadio.getText();
            }
            if (dieselRadio.isSelected()){
                fuelType = dieselRadio.getText();
            }

            PreparedStatement stmt = con.prepareStatement("insert into cars values(?,?,?,?,?,?);");
            stmt.setString(1, addCarMake.getValue());
            stmt.setString(2,addCarModel.getText());
            stmt.setString(3,carYear.getValue());
            stmt.setString(4,addPlateNo.getText());
            stmt.setString(5,fuelType);
            stmt.setDouble(6,Double.parseDouble(addCarCost.getText()));
            stmt.execute();
            stmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Added");
            alert.setHeaderText("New car added");
            alert.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Database Error");
            error.setHeaderText(e.getMessage());
            error.show();
        }
    }

    @FXML
    void addRent(ActionEvent event) {
        try {
            LocalDate myDate = rentDate.getValue();
            String formattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println(formattedDate);

            PreparedStatement rentStmt = con.prepareStatement("insert into rent_car values(?,?,?);");
            rentStmt.setString(1, rentUserLicenseDropDown.getValue());
            rentStmt.setString(2,rentCarPlateDropDown.getValue());
            rentStmt.setString(3,formattedDate);
            rentStmt.execute();
            rentStmt.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Added");
            alert.setHeaderText("Rent added");
            alert.show();
        }catch (SQLIntegrityConstraintViolationException se){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error: Referential Integrity Violation");
            error.setHeaderText("Please add user/car into database first");
            error.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Database Error");
            error.setHeaderText(e.getMessage());
            error.show();
        }
    }

    //Show cars
    @FXML
    private TableView<cars> showTableCars;

    @FXML
    private TableColumn<cars, String> CarMake;

    @FXML
    private TableColumn<cars, String> CarModel;

    @FXML
    private TableColumn<cars, String> CarYear;

    @FXML
    private TableColumn<cars, String> CarPlate;

    @FXML
    private TableColumn<cars, String> fuelTypeCol;

    @FXML
    private TableColumn<cars, Double> cost24Col;

    //Show Users
    @FXML
    private TableView<Users> usersTable;

    @FXML
    private TableColumn<Users, String> fnameCol;

    @FXML
    private TableColumn<Users, String> lnameCol;

    @FXML
    private TableColumn<Users, String> emailCol;

    @FXML
    private TableColumn<Users, String> phoneCol;

    @FXML
    private TableColumn<Users, String> licenseCol;

    //Rent Table
    @FXML
    private TableView<Rent> rentTable;

    @FXML
    private TableColumn<Rent, String>rentFnameCol;

    @FXML
    private TableColumn<Rent, String>rentLnameCol;

    @FXML
    private TableColumn<Rent, String>rentLicenseCol;

    @FXML
    private TableColumn<Rent, String>rentCarMakeCol;

    @FXML
    private TableColumn<Rent, String>rentCarModelCol;

    @FXML
    private TableColumn<Rent, String>rentCarPlateCol;

    @FXML
    private TableColumn<Rent, String>rentDaysRentedCol;





    static ObservableList<String>  plates;

    public static ObservableList<String> getDataPlates() throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        ObservableList<String> list = FXCollections.observableArrayList();
        PreparedStatement stmt = con.prepareStatement("select distinct car_plate_no from cars;");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("CAR_PLATE_NO"));
        }


        return list;
    }

    static ObservableList<String> licenses;

    public static ObservableList<String> getDataLicenses() throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        ObservableList<String> list = FXCollections.observableArrayList();
        PreparedStatement stmt = con.prepareStatement("select distinct LICENSE_NO from users;");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("LICENSE_NO"));
        }
        return list;
    }

    static ObservableList<String> rentedPlates;
    public static ObservableList<String> getDataRentedPlates() throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        ObservableList<String> list = FXCollections.observableArrayList();
        PreparedStatement stmt = con.prepareStatement("select distinct car_plate_no from rent_car;");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("CAR_PLATE_NO"));
        }
        return list;
    }


    static ObservableList<String> rentedLicenses;
    public ObservableList<String> getDataRentedLicense() throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        ObservableList<String> list = FXCollections.observableArrayList();

        PreparedStatement stmt = con.prepareStatement("select LICENSE_NO from rent_car " +
                "where CAR_PLATE_NO = '"+carPlate+"';");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("LICENSE_NO"));
        }
        return list;
    }













    ObservableList<cars> listM;

    public static ObservableList<cars> getDataCars() throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        ObservableList<cars> list = FXCollections.observableArrayList();
        PreparedStatement stmt = con.prepareStatement("select * from cars");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            list.add(new cars(rs.getString("make"), rs.getString("model"), rs.getString("make_year"), rs.getString("car_plate_no"), rs.getString("fuel_type"),rs.getDouble("COST_PER_DAY")));
        }


        return list;
    }


    ObservableList<Users> listUsers;

    public static ObservableList<Users> getDataUsers() throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        ObservableList<Users> userList = FXCollections.observableArrayList();
        PreparedStatement stmt = con.prepareStatement("select * from users");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            userList.add(new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }


        return userList;
    }

    ObservableList<Rent> listRent;

    public static ObservableList<Rent> getDataRent() throws SQLException{

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");
        ObservableList<Rent> rentList = FXCollections.observableArrayList();
        PreparedStatement stmt = con.prepareStatement("""
                SELECT FNAME, LNAME, RENT_CAR.LICENSE_NO, MAKE, MODEL,RENT_CAR.CAR_PLATE_NO,DATEDIFF(CAST( SYSDATE() AS Date ), RENT_CAR.RENT_DATE)
                FROM CARS  JOIN RENT_CAR  JOIN USERS
                WHERE USERS.LICENSE_NO = RENT_CAR.LICENSE_NO
                AND\s
                CARS.CAR_PLATE_NO = RENT_CAR.CAR_PLATE_NO;""");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            rentList.add(new Rent(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) , rs.getString(6) , rs.getString(7)));
        }

        return rentList;
    }


    @FXML
    void showCarsAdded(ActionEvent event) throws SQLException {
        listM = getDataCars();
        showTableCars.setItems(listM);

    }

    @FXML
    void showUsersAdded(ActionEvent event) throws SQLException{
        listUsers = getDataUsers();
        usersTable.setItems(listUsers);
    }

    @FXML
    void showRent(MouseEvent event) throws SQLException {
        listRent = getDataRent();
        rentTable.setItems(listRent);
    }


    @FXML
    void showRentCarPlates(MouseEvent event) throws SQLException{
        plates = getDataPlates();
        rentCarPlateDropDown.setItems(plates);
    }

    @FXML
    void showRentUserLicense(MouseEvent event) throws SQLException{
        licenses = getDataLicenses();
        rentUserLicenseDropDown.setItems(licenses);
    }

    @FXML
    void showRentedCarPlates(MouseEvent mouseEvent) throws SQLException{
        rentedPlates = getDataRentedPlates();
        dropDownCarPlate.setItems(rentedPlates);
    }



    @FXML
    void showRentedLicenseDropDown(MouseEvent event) throws SQLException{
        rentedLicenses = getDataRentedLicense();
        rentedLicenseDropDown.setItems(rentedLicenses);
    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            plates = getDataPlates();
            rentCarPlateDropDown.setItems(plates);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            licenses = getDataLicenses();
            rentUserLicenseDropDown.setItems(licenses);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            rentedLicenses = getDataRentedLicense();
            rentedLicenseDropDown.setItems(rentedLicenses);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            rentedPlates = getDataRentedPlates();
            dropDownCarPlate.setItems(rentedPlates);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        addCarMake.getItems().addAll(carMakes);
        carYear.getItems().addAll(years);



        CarMake.setCellValueFactory(new PropertyValueFactory<cars, String>("make"));
        CarModel.setCellValueFactory(new PropertyValueFactory<cars, String>("model"));
        CarYear.setCellValueFactory(new PropertyValueFactory<cars, String>("year"));
        CarPlate.setCellValueFactory(new PropertyValueFactory<cars, String>("plate"));
        fuelTypeCol.setCellValueFactory(new PropertyValueFactory<cars, String>("fuelType"));
        cost24Col.setCellValueFactory(new PropertyValueFactory<cars, Double>("cost24hrs"));

        try {
            listM = getDataCars();
            showTableCars.setItems(listM);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        fnameCol.setCellValueFactory(new PropertyValueFactory<Users, String>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<Users, String>("lname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Users, String>("phone"));
        licenseCol.setCellValueFactory(new PropertyValueFactory<Users, String>("license"));

        try{
            listUsers = getDataUsers();
            usersTable.setItems(listUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        rentFnameCol.setCellValueFactory(new PropertyValueFactory<Rent,String>("rentFname"));
        rentLnameCol.setCellValueFactory(new PropertyValueFactory<Rent,String>("rentLname"));
        rentLicenseCol.setCellValueFactory(new PropertyValueFactory<Rent, String>("rentLicense"));
        rentCarMakeCol.setCellValueFactory(new PropertyValueFactory<Rent, String>("rentCarMake"));
        rentCarModelCol.setCellValueFactory(new PropertyValueFactory<Rent, String>("rentCarModel"));
        rentCarPlateCol.setCellValueFactory(new PropertyValueFactory<Rent, String>("rentCarPlate"));
        rentDaysRentedCol.setCellValueFactory(new PropertyValueFactory<Rent, String>("rentDaysRented"));

        try{
            listRent = getDataRent();
            rentTable.setItems(listRent);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




}


