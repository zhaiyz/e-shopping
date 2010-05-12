/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.OrderInfoVo;

/**
 * OrderInfoDao
 */
public interface OrderInfoDao {

	/**
	 * 添加订单寄送信息
	 * 
	 * @param info
	 * @return boolean 是否添加成功
	 */
	public boolean addOrderInfo(OrderInfoVo info);

	/**
	 * 按ID删除相应订单寄送信息
	 * 
	 * @param id
	 * @return boolean 是否删除成功
	 */
	public boolean removeOrderInfoById(int id);

	/**
	 * 修改订单寄送信息
	 * 
	 * @param info
	 * @return boolean 是否修改成功
	 */
	public boolean modifyOrderInfo(OrderInfoVo info);

	/**
	 * 查询全部订单寄送信息
	 * 
	 * @param start
	 *            int 开始位置
	 * @param limit
	 *            int 偏移量
	 * @return list 符合条件的所有OrderInfo记录
	 */
	public List<OrderInfoVo> findAllOrderInfo(int start, int limit);

	/**
	 * 按ID查询相应的OrderInfo记录
	 * 
	 * @param id
	 *            orderInfoId
	 * @return 一个OrderInfo记录
	 */
	public OrderInfoVo findOrderInfoById(int id);

	/**
	 * 按订单的ID查询OrderInfo记录，没有分页
	 * 
	 * @param id
	 *            int 订单的ID orderId
	 * @return
	 */
	public List<OrderInfoVo> findOrderInfoByOrderId(int id);

	/**
	 * 用来判断一个商品是否被购买过
	 * 
	 * @param proId
	 * @return
	 */
	public boolean findOrderInfoByProId(int proId);
}
