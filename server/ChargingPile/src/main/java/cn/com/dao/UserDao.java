package cn.com.dao;

import java.util.ArrayList;
import java.util.Arrays;
import cn.com.bean.Message;
import cn.com.bean.User;
import cn.com.jdbc.TxQueryRunner;
import cn.com.utils.CommonUtils;

public class UserDao {
	private static TxQueryRunner qr = new TxQueryRunner();

	/**
	 * 执行插入user表语句
	 * @param user	将此user记录插入到数据库中
	 * @throws Message 
	 */
	public static String add(User user) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into user(username,imageaddress,cartype,carnumber"
					+ ",integral,balance,password,address,cid,sid,uuid) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			Object paramObject[] = getparamObject(user); 
			ArrayList<Object> arrayList = new ArrayList<Object>(Arrays.asList(paramObject));
			String uuid = CommonUtils.uuid();
			arrayList.add(uuid);
			int result = qr.update(sql,arrayList.toArray());
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
				return uuid;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("用户注册失败");
		}
		
	}

	/**
	 * 执行修改user表语句
	 * 修改数据库中user的信息
	 * @param user	
	 * @throws Message
	 */
	public static void update(User user) throws Message {
		// TODO Auto-generated method stub
		try {
			String sql = "update user set username=?,imageaddress=?,cartype=?,"
					+ "carnumber=?,integral=?,balance=?,password=?,address=?,cid=?,"
					+ "sid=? where uuid=?";
			Object paramObject[] = getparamObject(user);  
			ArrayList<Object> arrayList = new ArrayList<Object>(Arrays.asList(paramObject));
			arrayList.add(user.getUuid());
			int result = qr.update(sql,arrayList.toArray());
			if(result>0) {
				System.out.println("操作数据库成功，影响行数："+result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Message("用户信息修改失败");
		}
		
	}
	
	private static Object[] getparamObject(User user) {
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
		return paramObject;
	}
}
