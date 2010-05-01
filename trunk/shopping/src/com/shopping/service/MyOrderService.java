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
}
