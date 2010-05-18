package com.shopping.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.BenefitAllVo;
import com.shopping.vo.BenefitItemVo;
import com.shopping.vo.BenefitVo;
import com.shopping.vo.CategoryVo;
import com.shopping.vo.ItemVo;
import com.shopping.vo.MyOrderVo;
import com.shopping.vo.OrderInfoVo;
import com.shopping.vo.ProductVo;

public class BenefitUtil {
	public static List<BenefitVo> getBenefitByCategory(List<MyOrderVo> lm) {
		List<BenefitVo> list = new ArrayList<BenefitVo>();

		// 所有订单明细
		List<OrderInfoVo> lo = new ArrayList<OrderInfoVo>();

		// 取出订单里面所有的订单明细
		Iterator<MyOrderVo> iterm = lm.iterator();
		while (iterm.hasNext()) {
			MyOrderVo order = new MyOrderVo();
			order = iterm.next();

			// 按订单主键查询出所有订单明细
			List<OrderInfoVo> lt = new ArrayList<OrderInfoVo>();
			lt = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(order.getOrderId());

			// 把订单明细放入总的那个里面
			Iterator<OrderInfoVo> itert = lt.iterator();
			while (itert.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				info = itert.next();

				lo.add(info);
			}
		} // end of while 到此已经把这订单里面的所有明细，放在了lo里面

		// 商品大类
		List<CategoryVo> lc = new ArrayList<CategoryVo>();
		lc = ServiceFactory.getCategoryServiceInstance().findAllCategory();

		Iterator<CategoryVo> iterc = lc.iterator();
		while (iterc.hasNext()) {
			// 定义一个收益统计对象
			BenefitVo b = new BenefitVo();

			// 总营业额
			float sales = 0.0f;

			// 总利润
			float benefit = 0.0f;

			CategoryVo category = new CategoryVo();
			category = iterc.next();

			// 查询出所有的商品小类
			List<ItemVo> li = new ArrayList<ItemVo>();
			li = ServiceFactory.getItemServiceInstance().findItemByCategoryId(
					category.getCatId());

			/* 下面应该是算法最核心的部分 */

			// 定义一个列表用来保存一个大类正面所有的商品
			List<ProductVo> lpro = new ArrayList<ProductVo>();

			// 先查询出所有的商品
			Iterator<ItemVo> iteri = li.iterator();
			while (iteri.hasNext()) {
				ItemVo item = new ItemVo();
				item = iteri.next();

				List<ProductVo> lp = new ArrayList<ProductVo>();
				lp = ServiceFactory.getProductServiceInstance().findAllProduct(
						item.getItemId());

				// 把商品保存到大类商品列表里面
				Iterator<ProductVo> iterp = lp.iterator();
				while (iterp.hasNext()) {
					ProductVo product = new ProductVo();
					product = iterp.next();

					lpro.add(product);
				}
			} // end of while 到此，已经把一个大类里面的商品保存到了lpro里面

			/**
			 * 现在要计算一个大类一收益情况<br>
			 * 只要是对比大类里面的商品和订单明细里面的商品<br>
			 * 如果相同，那个就计算出收益情况<br>
			 * lo 与 lpro 的比较
			 */

			Iterator<ProductVo> iterator = lpro.iterator();
			while (iterator.hasNext()) {
				ProductVo pro = new ProductVo();
				pro = iterator.next();

				// 现在要用这个pro和lo里面的商品进行比较，如果主键相同那么就是被购买过
				Iterator<OrderInfoVo> iter = lo.iterator();
				while (iter.hasNext()) {
					OrderInfoVo info = new OrderInfoVo();
					info = iter.next();

					if (pro.getProId() == info.getProId()) {
						// 如果一个商品被购买了，那么就进行相关的计算
						// 总收入
						sales += info.getPrice();

						benefit += (pro.getDisPrice() - pro.getPurPrice())
								* info.getAmount();
					}
				}
			}

			b.setCatId(category.getCatId());
			b.setCatName(category.getCatName());
			b.setSales(sales);
			b.setBenefit(benefit);

			list.add(b);
		}

		return list;
	}

	public static List<BenefitItemVo> getBenefitByItem(List<MyOrderVo> lm) {
		List<BenefitItemVo> list = new ArrayList<BenefitItemVo>();

		// 所有订单明细
		List<OrderInfoVo> lo = new ArrayList<OrderInfoVo>();

		// 取出订单里面所有的订单明细
		Iterator<MyOrderVo> iterm = lm.iterator();
		while (iterm.hasNext()) {
			MyOrderVo order = new MyOrderVo();
			order = iterm.next();

			// 按订单主键查询出所有订单明细
			List<OrderInfoVo> lt = new ArrayList<OrderInfoVo>();
			lt = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(order.getOrderId());

			// 把订单明细放入总的那个里面
			Iterator<OrderInfoVo> itert = lt.iterator();
			while (itert.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				info = itert.next();

				lo.add(info);
			}
		} // end of while 到此已经把这订单里面的所有明细，放在了lo里面

		// 商品大类
		List<CategoryVo> lc = new ArrayList<CategoryVo>();
		lc = ServiceFactory.getCategoryServiceInstance().findAllCategory();

		Iterator<CategoryVo> iterc = lc.iterator();
		while (iterc.hasNext()) {
			CategoryVo category = new CategoryVo();
			category = iterc.next();

			// 查询出所有的商品小类
			List<ItemVo> li = new ArrayList<ItemVo>();
			li = ServiceFactory.getItemServiceInstance().findItemByCategoryId(
					category.getCatId());

			/* 下面应该是算法最核心的部分 */

			// 先查询出所有的商品
			Iterator<ItemVo> iteri = li.iterator();
			while (iteri.hasNext()) {
				// 定义一个收益统计对象
				BenefitItemVo b = new BenefitItemVo();

				// 总营业额
				float sales = 0.0f;

				// 总利润
				float benefit = 0.0f;

				ItemVo item = new ItemVo();
				item = iteri.next();

				// 定义一个列表用来保存一个小类里面所有的商品
				List<ProductVo> lpro = new ArrayList<ProductVo>();

				List<ProductVo> lp = new ArrayList<ProductVo>();
				lp = ServiceFactory.getProductServiceInstance().findAllProduct(
						item.getItemId());

				// 把商品保存到大类商品列表里面
				Iterator<ProductVo> iterp = lp.iterator();
				while (iterp.hasNext()) {
					ProductVo product = new ProductVo();
					product = iterp.next();

					lpro.add(product);
				}

				/**
				 * 现在要计算一个大类一收益情况<br>
				 * 只要是对比大类里面的商品和订单明细里面的商品<br>
				 * 如果相同，那个就计算出收益情况<br>
				 * lo 与 lpro 的比较
				 */

				Iterator<ProductVo> iterator = lpro.iterator();
				while (iterator.hasNext()) {
					ProductVo pro = new ProductVo();
					pro = iterator.next();

					// 现在要用这个pro和lo里面的商品进行比较，如果主键相同那么就是被购买过
					Iterator<OrderInfoVo> iter = lo.iterator();
					while (iter.hasNext()) {
						OrderInfoVo info = new OrderInfoVo();
						info = iter.next();

						if (pro.getProId() == info.getProId()) {
							// 如果一个商品被购买了，那么就进行相关的计算
							// 总收入
							sales += info.getPrice();

							benefit += (pro.getDisPrice() - pro.getPurPrice())
									* info.getAmount();
						}
					}
				}

				b.setItemName(item.getItemName());
				b.setSales(sales);
				b.setBenefit(benefit);

				list.add(b);
			} // end of while 到此，已经把一个大类里面的商品保存到了lpro里面

		}

		return list;
	}

	public static List<BenefitItemVo> getBenefitByItem(int catId,
			List<MyOrderVo> lm) {
		List<BenefitItemVo> list = new ArrayList<BenefitItemVo>();

		// 所有订单明细
		List<OrderInfoVo> lo = new ArrayList<OrderInfoVo>();

		// 取出订单里面所有的订单明细
		Iterator<MyOrderVo> iterm = lm.iterator();
		while (iterm.hasNext()) {
			MyOrderVo order = new MyOrderVo();
			order = iterm.next();

			// 按订单主键查询出所有订单明细
			List<OrderInfoVo> lt = new ArrayList<OrderInfoVo>();
			lt = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(order.getOrderId());

			// 把订单明细放入总的那个里面
			Iterator<OrderInfoVo> itert = lt.iterator();
			while (itert.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				info = itert.next();

				lo.add(info);
			}
		} // end of while 到此已经把这订单里面的所有明细，放在了lo里面

		// 商品大类
		CategoryVo category = new CategoryVo();
		category = ServiceFactory.getCategoryServiceInstance()
				.findCategoryById(catId);

		// 查询出所有的商品小类
		List<ItemVo> li = new ArrayList<ItemVo>();
		li = ServiceFactory.getItemServiceInstance().findItemByCategoryId(
				category.getCatId());

		/* 下面应该是算法最核心的部分 */

		// 先查询出所有的商品
		Iterator<ItemVo> iteri = li.iterator();
		while (iteri.hasNext()) {
			// 定义一个收益统计对象
			BenefitItemVo b = new BenefitItemVo();

			// 总营业额
			float sales = 0.0f;

			// 总利润
			float benefit = 0.0f;

			ItemVo item = new ItemVo();
			item = iteri.next();

			// 定义一个列表用来保存一个小类里面所有的商品
			List<ProductVo> lpro = new ArrayList<ProductVo>();

			List<ProductVo> lp = new ArrayList<ProductVo>();
			lp = ServiceFactory.getProductServiceInstance().findAllProduct(
					item.getItemId());

			// 把商品保存到大类商品列表里面
			Iterator<ProductVo> iterp = lp.iterator();
			while (iterp.hasNext()) {
				ProductVo product = new ProductVo();
				product = iterp.next();

				lpro.add(product);
			}

			/**
			 * 现在要计算一个大类一收益情况<br>
			 * 只要是对比大类里面的商品和订单明细里面的商品<br>
			 * 如果相同，那个就计算出收益情况<br>
			 * lo 与 lpro 的比较
			 */

			Iterator<ProductVo> iterator = lpro.iterator();
			while (iterator.hasNext()) {
				ProductVo pro = new ProductVo();
				pro = iterator.next();

				// 现在要用这个pro和lo里面的商品进行比较，如果主键相同那么就是被购买过
				Iterator<OrderInfoVo> iter = lo.iterator();
				while (iter.hasNext()) {
					OrderInfoVo info = new OrderInfoVo();
					info = iter.next();

					if (pro.getProId() == info.getProId()) {
						// 如果一个商品被购买了，那么就进行相关的计算
						// 总收入
						sales += info.getPrice();

						benefit += (pro.getDisPrice() - pro.getPurPrice())
								* info.getAmount();
					}
				}
			}

			b.setItemName(item.getItemName());
			b.setSales(sales);
			b.setBenefit(benefit);

			list.add(b);
		} // end of while 到此，已经把一个大类里面的商品保存到了lpro里面

		return list;
	}

	public static List<BenefitAllVo> getBenefitAll(List<MyOrderVo> lm) {
		List<BenefitAllVo> list = new ArrayList<BenefitAllVo>();

		// 所有订单明细
		List<OrderInfoVo> lo = new ArrayList<OrderInfoVo>();

		// 取出订单里面所有的订单明细
		Iterator<MyOrderVo> iterm = lm.iterator();
		while (iterm.hasNext()) {
			MyOrderVo order = new MyOrderVo();
			order = iterm.next();

			// 按订单主键查询出所有订单明细
			List<OrderInfoVo> lt = new ArrayList<OrderInfoVo>();
			lt = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(order.getOrderId());

			// 把订单明细放入总的那个里面
			Iterator<OrderInfoVo> itert = lt.iterator();
			while (itert.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				info = itert.next();

				lo.add(info);
			}
		} // end of while 到此已经把这订单里面的所有明细，放在了lo里面

		// 商品大类
		List<CategoryVo> lc = new ArrayList<CategoryVo>();
		lc = ServiceFactory.getCategoryServiceInstance().findAllCategory();

		// 定义一个收益统计对象
		BenefitAllVo b = new BenefitAllVo();

		// 总营业额
		float sales = 0.0f;

		// 总利润
		float benefit = 0.0f;

		Iterator<CategoryVo> iterc = lc.iterator();
		while (iterc.hasNext()) {
			CategoryVo category = new CategoryVo();
			category = iterc.next();

			// 查询出所有的商品小类
			List<ItemVo> li = new ArrayList<ItemVo>();
			li = ServiceFactory.getItemServiceInstance().findItemByCategoryId(
					category.getCatId());

			/* 下面应该是算法最核心的部分 */

			// 定义一个列表用来保存一个大类正面所有的商品
			List<ProductVo> lpro = new ArrayList<ProductVo>();

			// 先查询出所有的商品
			Iterator<ItemVo> iteri = li.iterator();
			while (iteri.hasNext()) {
				ItemVo item = new ItemVo();
				item = iteri.next();

				List<ProductVo> lp = new ArrayList<ProductVo>();
				lp = ServiceFactory.getProductServiceInstance().findAllProduct(
						item.getItemId());

				// 把商品保存到大类商品列表里面
				Iterator<ProductVo> iterp = lp.iterator();
				while (iterp.hasNext()) {
					ProductVo product = new ProductVo();
					product = iterp.next();

					lpro.add(product);
				}
			} // end of while 到此，已经把一个大类里面的商品保存到了lpro里面

			/**
			 * 现在要计算一个大类一收益情况<br>
			 * 只要是对比大类里面的商品和订单明细里面的商品<br>
			 * 如果相同，那个就计算出收益情况<br>
			 * lo 与 lpro 的比较
			 */

			Iterator<ProductVo> iterator = lpro.iterator();
			while (iterator.hasNext()) {
				ProductVo pro = new ProductVo();
				pro = iterator.next();

				// 现在要用这个pro和lo里面的商品进行比较，如果主键相同那么就是被购买过
				Iterator<OrderInfoVo> iter = lo.iterator();
				while (iter.hasNext()) {
					OrderInfoVo info = new OrderInfoVo();
					info = iter.next();

					if (pro.getProId() == info.getProId()) {
						// 如果一个商品被购买了，那么就进行相关的计算
						// 总收入
						sales += info.getPrice();

						benefit += (pro.getDisPrice() - pro.getPurPrice())
								* info.getAmount();
					}
				}
			}
		}

		b.setSales(sales);
		b.setBenefit(benefit);

		list.add(b);

		return list;
	}
}
