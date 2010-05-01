/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.ContactVo;

/**
 * ContactDao
 */
public interface ContactDao {

	/**
	 * 添加一个寄送地址
	 * 
	 * @param contact
	 * @return boolean 是否添加成功
	 */
	public boolean addContact(ContactVo contact);

	/**
	 * 按ID删除寄送地址
	 * 
	 * @param id
	 * @return boolean 是否删除成功
	 */
	public boolean removeContactById(int id);

	/**
	 * 修改寄送地址
	 * 
	 * @param contact
	 * @return boolean 是否修改成功
	 */
	public boolean modifyContact(ContactVo contact);

	/**
	 * 查询出全部联系地址
	 * 
	 * @param start
	 *            int 开始位置
	 * @param limit
	 *            int 偏移量
	 * @return list 符合条件的Contact记录
	 */
	public List<ContactVo> findAllContact(int start, int limit);

	/**
	 * 按ID查询Contact
	 * 
	 * @param id
	 * @return ContactVo
	 */
	public ContactVo findContactById(int id);

	/**
	 * 按OrderId查询出相应的Contact记录
	 * 
	 * @param id orderId 订单ID
	 * @return ContactVo 相应订单ID的订单寄送地址
	 */
	public ContactVo findContactByOrderId(int id);
}
