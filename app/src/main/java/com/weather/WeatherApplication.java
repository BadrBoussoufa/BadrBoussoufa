package com.weather;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.Map;

import com.weather.domain.User;
import com.weather.domain.WeatherInfo;

/**
 * This program is about weather forcast according to certain zipcodes.
 * 
 * @author Badr Boussoufa
 * @since March 25, 2021
 */
public class WeatherApplication {
	public static void main(String[] args) throws Exception { 
		Connection connection = getConnection();
		List<User> users = loadUsers(connection);
		Map<String, WeatherInfo> weatherMap = loadWeather(connection);

		try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your username : ");
            String username = scanner.nextLine();
            System.out.println("Enter your password : "); 
            String password = scanner.nextLine();
            User loggedUser = findUser(users, username, password);

            if (loggedUser != null) {
				String answer;
				do {
					System.out.println("Enter your zipcode  : ");
					String key = scanner.nextLine();

					WeatherInfo info = weatherMap.get(key);
					if (info != null) {
						System.out.println(String.format("\n Hi %s, you weather is %s you temp %s\n", loggedUser.getName(), info.getForecast(), info.getTemperature()));
					} else {
						System.out.println("Zipcode does not exist");
					}
					System.out.println("Do you want to enter a new zipcode y/n");
					answer = scanner.nextLine();
			    } while ("y".equalsIgnoreCase(answer));
            } else {
                System.out.println("Invalid username and password");
            }
        }
	}
        
    private static Connection getConnection() throws SQLException { 
	return DriverManager.getConnection("jdbc:postgresql://localhost/weather", "BadrBoussoufa", "badr19811");
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

    private static List<User> loadUsers(Connection connection) throws SQLException {
        List<User> users = new ArrayList<>();
		ResultSet results = fetchUsers(connection);
		while (results.next()) {
			User user = new User(results.getString("username"), results.getString("password"), results.getString("name"));
			users.add(user);
		}
        return users;
    }

    private static Map<String, WeatherInfo> loadWeather(Connection connection) throws SQLException {
        Map<String, WeatherInfo> map = new HashMap<>();
		ResultSet result = fetchWeatherInfo(connection);
		while(result.next()){
		WeatherInfo info = new WeatherInfo(result.getString("zipCode"), result.getString("forecast"), result.getInt("temperature"));
		map.put(info.getZipCode(), info);
		}
        
        return map;
    
    }

	private static ResultSet fetchUsers(Connection connection) throws SQLException {
		return fetchData(connection, "select * from users_login");
	}

	private static ResultSet fetchWeatherInfo(Connection connection) throws SQLException {
		return fetchData(connection, "select * from weatherlist_list");
	}

	private static ResultSet fetchData(Connection connection, String sqlStatement) throws SQLException {
		 Statement statement = connection.createStatement() ;
			return statement.executeQuery(sqlStatement);
		
			
	}
}
