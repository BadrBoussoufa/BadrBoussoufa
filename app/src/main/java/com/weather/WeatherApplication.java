package com.weather;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;

//import com.weather.domain.User;
//import com.weather.domain.WeatherInfo;

/**
 * This program is about weather forcast according certain zipcodes.
 * 
 * @author Badr Boussoufa
 * @since March 25, 2021
 */
public class WeatherApplication {

	

	public static void main(String[] args) {
		//Connect to DB first
		String url = "jdbc:postgresql://localhost/weather";
		String username = "BadrBoussoufa";
		 String passw = "badr19811";

		 try {
			 
		Connection connection = DriverManager.getConnection( url,username, passw);
		Statement statement = connection.createStatement();
		int info = statement.executeUpdate("insert into weatherlist_list Values(22099,'stormy','9')");
		System.out.println(info);
		ResultSet resultSet=statement.executeQuery("select * from WeatherList_list");
		while(resultSet.next()){
			System.out.println(resultSet.getInt("zipcode") + " " + resultSet.getString("forecast") + " " + resultSet.getString("temperature"));
		}

        Statement statement1 = connection.createStatement();
		int users = statement.executeUpdate("insert into users_login Values('Ana','log3','pass3')");
		System.out.println(users);
		ResultSet resultSet1=statement1.executeQuery("select * from users_login");
		while(resultSet1.next()){
			System.out.println(resultSet1.getString("name") + " " + resultSet1.getString("username") + " " + resultSet1.getString("password"));
		}

      
	    } catch (Exception sQLException) {
		
	    }
	
	}}
        //Call to load users
		//Call to load weathermap

/*

		try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your username : ");
            String username = scanner.nextLine();
            System.out.println("Enter your password : ");
            String password = scanner.nextLine();
            User loggedUser = findUser(userList, username, password);

            if (loggedUser != null) {
                System.out.println("Enter your zipcode  : ");
                String key = scanner.nextLine();

                WeatherInfo info = weatherList.get(key);
                if (info != null) {
                    System.out.println(String.format("Hi %s, you weather is %s you temp %s", loggedUser.getName(), info.getForecast(), info.getTemperature()));
                } else {
                    System.out.println("Zipcode does not exist");
                }
            } else {
                System.out.println("Invalid username and password");
            }
        }
    }

    private static User findUser(List<User> users, String username, String password) {
        User loggedUser = null;
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)) {
              loggedUser = user;
            }   
        }
        return loggedUser;
    }

    private static List<Users_login> loadUsers() {
        List<User> users = new ArrayList<>();
        //Use the database connection to fetch users
        //COnvert the list (for each) of rows to User object list
        //return the list
        return users;
    }

    private static Map<String, WeatherInfo> loadWeather() {
        Map<String, WeatherInfo> map = new HashMap<>();
        //Use the database connection to fetch users
        //COnvert the list (for each) of rows to Weather info object
        // add the object to the map and use the weather info zip to be the key in the map
        //return the list
        return map;
    
    }




















       }
       }*/