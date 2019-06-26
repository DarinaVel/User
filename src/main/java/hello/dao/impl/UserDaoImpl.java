package hello.dao.impl;

import hello.model.User;
import hello.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO user " +
                "(login, name, password, birthdate) VALUES (?, ?, ?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                user.getLogin(), user.getName(), user.getPassword(), user.getBirthdate()
        });
    }

    @Override
    public void insertAll(List<User> users) {
        String sql = "INSERT INTO user " +
                "(login, name, password, birthdate) VALUES (?, ?, ?, ?)" ;
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = users.get(i);
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                ps.setDate(3, (Date) user.getBirthdate());
            }

            public int getBatchSize() {
                return users.size();
            }
        });
    }

    @Override
    public List<User> loadAllUser() {
        String sql = "SELECT * FROM user";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<User> result = new ArrayList<User>();
        for(Map<String, Object> row:rows){
            User user = new User();
            user.setLogin((String)row.get("login"));
            user.setName((String)row.get("name"));
            user.setPassword((String) row.get("password"));
            user.setBirthdate((Date) row.get("birthdate"));
            result.add(user);
        }

        return result;
    }

    @Override
    public User findUserByLogin(String login) {
        String sql = "SELECT * FROM user WHERE login = ?";
        return (User)getJdbcTemplate().queryForObject(sql, new Object[]{login}, new RowMapper<User>(){
            @Override
            public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setBirthdate(rs.getDate("birthdate"));
                return user;
            }
        });
    }
}
