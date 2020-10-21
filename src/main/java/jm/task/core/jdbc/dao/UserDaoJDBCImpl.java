package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connect = Util.getConnection();
            Statement createTable =connect.createStatement();) {
            createTable.executeUpdate("CREATE TABLE `user` (`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,`name` VARCHAR(255) NOT NULL, `last_name` VARCHAR(255) NOT NULL, `age` INTEGER (255) NOT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connect = Util.getConnection();
            Statement droptable =connect.createStatement();) {
            droptable.executeUpdate("drop table IF EXISTS user");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(
                Connection connect = Util.getConnection();
                PreparedStatement statement =connect.prepareStatement("INSERT INTO user (name,last_name,age) VALUES (?, ?, ?)");) {
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connect = Util.getConnection();
            PreparedStatement preparedStmt = connect.prepareStatement("delete from user where id = ?");
        ){
            preparedStmt.setLong(1, id);
            preparedStmt.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement();){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lasetName = resultSet.getString("last_name");
                Byte age = resultSet.getByte("age");
                User obj = new User();
                obj.setId(id);
                obj.setName(name);
                obj.setLastName(lasetName);
                obj.setAge(age);
                users.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection connect = Util.getConnection(); Statement deleteAll = connect.createStatement()){
            deleteAll.execute("DELETE FROM user");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
