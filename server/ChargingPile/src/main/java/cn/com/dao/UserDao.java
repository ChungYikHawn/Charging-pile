package cn.com.dao;

import java.sql.SQLException;

import cn.com.bean.Message;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;

public class UserDao {
	private static TxQueryRunner qr = new TxQueryRunner();

	/**
	 * 执行插入user表语句
	 * @param user	将此user记录插入到数据库中
	 * @throws SQLException 
	 */
	public static void add(User user) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into user(username,imageaddress,cartype,carnumber"
					+ ",integral,balance,password,address,cid,sid) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			Object cid,sid;
			if(user.getChargingpile()!=null)
				cid = user.getChargingpile().getCid();
			else 
				cid = null;
			if(user.getSchedule()!=null)
				sid = user.getSchedule().getSid();
			else 
				sid = null;
			Object paramObject[] = {user.getUsername(),user.getImageaddress(),user.getCartype(),
					user.getCarnumber(),user.getIntegral(),user.getBalance(),user.getPassword(),
					user.getAddress(),cid,sid};
			int result = qr.update(sql,paramObject);
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("用户注册失败");
		}
		
	}
}
