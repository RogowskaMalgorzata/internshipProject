package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Fund;

public class FundDAOImpl implements FundDAO{
	
	private DataSource dataSource;
	 
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
	@Override
	public void save(Fund fund) {
		String query = "insert into fund (id, date, value) values (?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = dataSource.getConnection();
            
            ps = con.prepareStatement(query);
            ps.setInt(1, fund.getId());
            
            java.util.Date javaDate = fund.getDate();
            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
            ps.setDate(2, sqlDate); 
            
            ps.setDouble(3, fund.getValue());
            
            int out = ps.executeUpdate();
            if(out != 0){
//                System.out.println("Fund saved with id=" + fund.getId());
            } else {}//System.out.println("Fund save failed with id=" + fund.getId());
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}

	@Override
	public Fund getById(int id) {
		String query = "select date, value from fund where id = ?";
        Fund fund = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
            	java.sql.Date sqlDate = rs.getDate("date");
                java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
                
                fund = new Fund(id, javaDate, rs.getDouble("value"));
                System.out.println("Fund Found:" + fund);
            } else {
                System.out.println("No Fund found with id=" + id);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return fund;
	}

	@Override
	public void update(Fund fund) {
		String query = "update fund set date=?, value=? where id=?";
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setDate(1, (Date) fund.getDate());
            ps.setDouble(2, fund.getValue());
            ps.setInt(3, fund.getId());
            int out = ps.executeUpdate();
            if (out != 0){
                System.out.println("Fund updated with id=" + fund.getId());
            } else {
            	System.out.println("No Fund found with id=" + fund.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}

	@Override
	public void deleteById(int id) {
		String query = "delete from fund where id=?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int out = ps.executeUpdate();
            
            if (out != 0){
                System.out.println("Fund deleted with id="+id);
            } else {
            	System.out.println("No Fund found with id="+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}

	@Override
	public List<Fund> getAll() {
		String query = "select id, date, value from fund";
        List<Fund> fundsList = new ArrayList<Fund>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
            	java.sql.Date sqlDate = rs.getDate("date");
                java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
            	
                Fund fund = new Fund(rs.getInt("id"), javaDate, rs.getDouble("value"));

                fundsList.add(fund);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return fundsList;
	}

	

}
