package ContactProject.respository;

import ContactProject.util.DataUtil;
import ContactProject.dto.Contact;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContactRespository {
    public boolean saveContact(Contact contact) {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "INSERT INTO contact(name,surname,phone) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getPhone());
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Contact> getList() {
        List<Contact> contactList = new LinkedList<>();
        Connection con = null;
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select  * from contact";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");

                Contact contact = new Contact();

                contact.setId(id);
                contact.setName(name);
                contact.setSurname(surname);
                contact.setPhone(phone);
                contactList.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactList;
    }

    public Contact byPhone(String phone){
        Contact contact=null;
        try{
            Connection connection = DataUtil.getConnection();
            String sql = "select  * from contact where phone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                contact = new Contact();
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String p = resultSet.getString("phone");

                contact.setId(id);
                contact.setName(name);
                contact.setSurname(surname);
                contact.setPhone(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public int delate(String phone){
        Connection connection =null;
        try {
            connection = DataUtil.getConnection();
            String sql = "delete from contact where phone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,phone);
            int effectedRows = preparedStatement.executeUpdate();
            return effectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    return 0;
    }

    public List<Contact> search(String query){
        List<Contact> contactList = new LinkedList<>();
        Connection connection=null;
        try {
            connection = DataUtil.getConnection();
            String sql = "select * from contact where lower(name) like ? or lower(surname) like ? or phone like ?";
            String parametr = "%"+query.toLowerCase()+"%";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,parametr);
            preparedStatement.setString(2,parametr);
            preparedStatement.setString(3,parametr);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("Surname"));
                contact.setPhone(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactList;
    }
}
