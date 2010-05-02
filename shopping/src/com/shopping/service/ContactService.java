package com.shopping.service;

import java.util.List;

import com.shopping.vo.ContactVo;

/**
 * ContactService
 */
public interface ContactService {
	
	/**
	 * 添加寄送地址信息
	 * @param contact
	 * @return 是否添加成功
	 */
	public boolean addContact(ContactVo contact);
	
	/**
	 * 按id删除寄送地址信息
	 * @param id
	 * @return 是否删除成功
	 */
	public boolean removeContactById(int id);
	
	/**
	 * 修改寄送地址信息
	 * @param contact
	 * @return 是否修改成功
	 */
	public boolean modifyContact(ContactVo contact);
	
	/**
	 * 查询所以寄送地址信息并分页
	 * @param start 开始位置
	 * @param limit 偏移量
	 * @return 符合条件的所有Contact记录
	 */
	public List<ContactVo> findAllContact(int start, int limit);
	
	/**
	 * 按id查询寄送地址信息
	 * @param id
	 * @return 寄送地址
	 */
	public ContactVo findContactById(int id);
	
	/**
	 * 按订单号查询寄送地址信息
	 * @param id
	 * @return 寄送地址
	 */
	public ContactVo findContactByOrderId(int id);
}
