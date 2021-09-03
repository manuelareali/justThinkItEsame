package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class UpdateQueryDao {

	ResultSet rs;
    private static final Logger logger = LoggerFactory.getLogger(UpdateQueryDao.class);

	public UpdateQueryDao() {
		rs = null;
	}
	
	
	public void updateQuery(PreparedStatement pstmt) throws SQLException {
	      int rowAffected = pstmt.executeUpdate();
          if (rowAffected == 1) {

              rs = pstmt.getGeneratedKeys();
 
          try {
              if (rs != null) rs.close();
          } catch (SQLException e) {
              logger.debug(e.getMessage());
          }
      }

	}
	
}
