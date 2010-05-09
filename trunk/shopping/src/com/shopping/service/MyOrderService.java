package com.shopping.service;

import java.util.List;

import com.shopping.vo.MyOrderVo;

/**
 * MyOrderService
 */
public interface MyOrderService {
	/**
	 * 添加一个订单
	 * 
	 * @param order
	 * @return boolean 是否添加正确
	 */
	public boolean addMyOrder(MyOrderVo order);

	/**
	 * 按订单ID删除
	 * 
	 * @param id
	 *            订单ID
	 * @return boolean 是否删除成功
	 */
	public boolean removeMyOrderById(int id);

	/**
	 * 修改订单，主要是更改它的状态
	 * 
	 * @param order
	 * @return boolean 是否添加成功
	 */
	public boolean modifyMyOrder(MyOrderVo order);

	/**
	 * 查询全部订单
	 * 
	 * @param start
	 *            int 开始位置
	 * @param limit
	 *            int 偏移量
	 * @return 符合条件的所有MyOrder记录
	 */
	public List<MyOrderVo> findAllMyOrder(int start, int limit);

	/**
	 * 按ID查询订单
	 * 
	 * @param id
	 * @return 相应ID的MyOrder记录
	 */
	public MyOrderVo findMyOrderById(int id);

	/**
	 * 通过订单号查询出订单
	 * 
	 * @param orderNum
	 * @return
	 */
	public MyOrderVo findOrderByOrderNum(String orderNum);

	/**
	 * 通过用户主键查询出当前用户所有的订单
	 * 
	 * @param id
	 * @return
	 */
	public List<MyOrderVo> findOrderByUserId(int id);

	/**
	 * 查询出订单的数量
	 * 
	 * @return
	 */
	public int getTotalNum();

	/**
	 * 按订单状态进行查询订单数量
	 * 
	 * @param state
	 * @return
	 */
	public int getTotalNum(int state);

	/**
	 * 按订单状态进行分页查询
	 * 
	 * @param state
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<MyOrderVo> findOrderByState(int state, int start, int limit);
}
