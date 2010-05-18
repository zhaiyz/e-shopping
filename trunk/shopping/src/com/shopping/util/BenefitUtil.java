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

		// ���ж�����ϸ
		List<OrderInfoVo> lo = new ArrayList<OrderInfoVo>();

		// ȡ�������������еĶ�����ϸ
		Iterator<MyOrderVo> iterm = lm.iterator();
		while (iterm.hasNext()) {
			MyOrderVo order = new MyOrderVo();
			order = iterm.next();

			// ������������ѯ�����ж�����ϸ
			List<OrderInfoVo> lt = new ArrayList<OrderInfoVo>();
			lt = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(order.getOrderId());

			// �Ѷ�����ϸ�����ܵ��Ǹ�����
			Iterator<OrderInfoVo> itert = lt.iterator();
			while (itert.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				info = itert.next();

				lo.add(info);
			}
		} // end of while �����Ѿ����ⶩ�������������ϸ��������lo����

		// ��Ʒ����
		List<CategoryVo> lc = new ArrayList<CategoryVo>();
		lc = ServiceFactory.getCategoryServiceInstance().findAllCategory();

		Iterator<CategoryVo> iterc = lc.iterator();
		while (iterc.hasNext()) {
			// ����һ������ͳ�ƶ���
			BenefitVo b = new BenefitVo();

			// ��Ӫҵ��
			float sales = 0.0f;

			// ������
			float benefit = 0.0f;

			CategoryVo category = new CategoryVo();
			category = iterc.next();

			// ��ѯ�����е���ƷС��
			List<ItemVo> li = new ArrayList<ItemVo>();
			li = ServiceFactory.getItemServiceInstance().findItemByCategoryId(
					category.getCatId());

			/* ����Ӧ�����㷨����ĵĲ��� */

			// ����һ���б���������һ�������������е���Ʒ
			List<ProductVo> lpro = new ArrayList<ProductVo>();

			// �Ȳ�ѯ�����е���Ʒ
			Iterator<ItemVo> iteri = li.iterator();
			while (iteri.hasNext()) {
				ItemVo item = new ItemVo();
				item = iteri.next();

				List<ProductVo> lp = new ArrayList<ProductVo>();
				lp = ServiceFactory.getProductServiceInstance().findAllProduct(
						item.getItemId());

				// ����Ʒ���浽������Ʒ�б�����
				Iterator<ProductVo> iterp = lp.iterator();
				while (iterp.hasNext()) {
					ProductVo product = new ProductVo();
					product = iterp.next();

					lpro.add(product);
				}
			} // end of while ���ˣ��Ѿ���һ�������������Ʒ���浽��lpro����

			/**
			 * ����Ҫ����һ������һ�������<br>
			 * ֻҪ�ǶԱȴ����������Ʒ�Ͷ�����ϸ�������Ʒ<br>
			 * �����ͬ���Ǹ��ͼ�����������<br>
			 * lo �� lpro �ıȽ�
			 */

			Iterator<ProductVo> iterator = lpro.iterator();
			while (iterator.hasNext()) {
				ProductVo pro = new ProductVo();
				pro = iterator.next();

				// ����Ҫ�����pro��lo�������Ʒ���бȽϣ����������ͬ��ô���Ǳ������
				Iterator<OrderInfoVo> iter = lo.iterator();
				while (iter.hasNext()) {
					OrderInfoVo info = new OrderInfoVo();
					info = iter.next();

					if (pro.getProId() == info.getProId()) {
						// ���һ����Ʒ�������ˣ���ô�ͽ�����صļ���
						// ������
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

		// ���ж�����ϸ
		List<OrderInfoVo> lo = new ArrayList<OrderInfoVo>();

		// ȡ�������������еĶ�����ϸ
		Iterator<MyOrderVo> iterm = lm.iterator();
		while (iterm.hasNext()) {
			MyOrderVo order = new MyOrderVo();
			order = iterm.next();

			// ������������ѯ�����ж�����ϸ
			List<OrderInfoVo> lt = new ArrayList<OrderInfoVo>();
			lt = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(order.getOrderId());

			// �Ѷ�����ϸ�����ܵ��Ǹ�����
			Iterator<OrderInfoVo> itert = lt.iterator();
			while (itert.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				info = itert.next();

				lo.add(info);
			}
		} // end of while �����Ѿ����ⶩ�������������ϸ��������lo����

		// ��Ʒ����
		List<CategoryVo> lc = new ArrayList<CategoryVo>();
		lc = ServiceFactory.getCategoryServiceInstance().findAllCategory();

		Iterator<CategoryVo> iterc = lc.iterator();
		while (iterc.hasNext()) {
			CategoryVo category = new CategoryVo();
			category = iterc.next();

			// ��ѯ�����е���ƷС��
			List<ItemVo> li = new ArrayList<ItemVo>();
			li = ServiceFactory.getItemServiceInstance().findItemByCategoryId(
					category.getCatId());

			/* ����Ӧ�����㷨����ĵĲ��� */

			// �Ȳ�ѯ�����е���Ʒ
			Iterator<ItemVo> iteri = li.iterator();
			while (iteri.hasNext()) {
				// ����һ������ͳ�ƶ���
				BenefitItemVo b = new BenefitItemVo();

				// ��Ӫҵ��
				float sales = 0.0f;

				// ������
				float benefit = 0.0f;

				ItemVo item = new ItemVo();
				item = iteri.next();

				// ����һ���б���������һ��С���������е���Ʒ
				List<ProductVo> lpro = new ArrayList<ProductVo>();

				List<ProductVo> lp = new ArrayList<ProductVo>();
				lp = ServiceFactory.getProductServiceInstance().findAllProduct(
						item.getItemId());

				// ����Ʒ���浽������Ʒ�б�����
				Iterator<ProductVo> iterp = lp.iterator();
				while (iterp.hasNext()) {
					ProductVo product = new ProductVo();
					product = iterp.next();

					lpro.add(product);
				}

				/**
				 * ����Ҫ����һ������һ�������<br>
				 * ֻҪ�ǶԱȴ����������Ʒ�Ͷ�����ϸ�������Ʒ<br>
				 * �����ͬ���Ǹ��ͼ�����������<br>
				 * lo �� lpro �ıȽ�
				 */

				Iterator<ProductVo> iterator = lpro.iterator();
				while (iterator.hasNext()) {
					ProductVo pro = new ProductVo();
					pro = iterator.next();

					// ����Ҫ�����pro��lo�������Ʒ���бȽϣ����������ͬ��ô���Ǳ������
					Iterator<OrderInfoVo> iter = lo.iterator();
					while (iter.hasNext()) {
						OrderInfoVo info = new OrderInfoVo();
						info = iter.next();

						if (pro.getProId() == info.getProId()) {
							// ���һ����Ʒ�������ˣ���ô�ͽ�����صļ���
							// ������
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
			} // end of while ���ˣ��Ѿ���һ�������������Ʒ���浽��lpro����

		}

		return list;
	}

	public static List<BenefitItemVo> getBenefitByItem(int catId,
			List<MyOrderVo> lm) {
		List<BenefitItemVo> list = new ArrayList<BenefitItemVo>();

		// ���ж�����ϸ
		List<OrderInfoVo> lo = new ArrayList<OrderInfoVo>();

		// ȡ�������������еĶ�����ϸ
		Iterator<MyOrderVo> iterm = lm.iterator();
		while (iterm.hasNext()) {
			MyOrderVo order = new MyOrderVo();
			order = iterm.next();

			// ������������ѯ�����ж�����ϸ
			List<OrderInfoVo> lt = new ArrayList<OrderInfoVo>();
			lt = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(order.getOrderId());

			// �Ѷ�����ϸ�����ܵ��Ǹ�����
			Iterator<OrderInfoVo> itert = lt.iterator();
			while (itert.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				info = itert.next();

				lo.add(info);
			}
		} // end of while �����Ѿ����ⶩ�������������ϸ��������lo����

		// ��Ʒ����
		CategoryVo category = new CategoryVo();
		category = ServiceFactory.getCategoryServiceInstance()
				.findCategoryById(catId);

		// ��ѯ�����е���ƷС��
		List<ItemVo> li = new ArrayList<ItemVo>();
		li = ServiceFactory.getItemServiceInstance().findItemByCategoryId(
				category.getCatId());

		/* ����Ӧ�����㷨����ĵĲ��� */

		// �Ȳ�ѯ�����е���Ʒ
		Iterator<ItemVo> iteri = li.iterator();
		while (iteri.hasNext()) {
			// ����һ������ͳ�ƶ���
			BenefitItemVo b = new BenefitItemVo();

			// ��Ӫҵ��
			float sales = 0.0f;

			// ������
			float benefit = 0.0f;

			ItemVo item = new ItemVo();
			item = iteri.next();

			// ����һ���б���������һ��С���������е���Ʒ
			List<ProductVo> lpro = new ArrayList<ProductVo>();

			List<ProductVo> lp = new ArrayList<ProductVo>();
			lp = ServiceFactory.getProductServiceInstance().findAllProduct(
					item.getItemId());

			// ����Ʒ���浽������Ʒ�б�����
			Iterator<ProductVo> iterp = lp.iterator();
			while (iterp.hasNext()) {
				ProductVo product = new ProductVo();
				product = iterp.next();

				lpro.add(product);
			}

			/**
			 * ����Ҫ����һ������һ�������<br>
			 * ֻҪ�ǶԱȴ����������Ʒ�Ͷ�����ϸ�������Ʒ<br>
			 * �����ͬ���Ǹ��ͼ�����������<br>
			 * lo �� lpro �ıȽ�
			 */

			Iterator<ProductVo> iterator = lpro.iterator();
			while (iterator.hasNext()) {
				ProductVo pro = new ProductVo();
				pro = iterator.next();

				// ����Ҫ�����pro��lo�������Ʒ���бȽϣ����������ͬ��ô���Ǳ������
				Iterator<OrderInfoVo> iter = lo.iterator();
				while (iter.hasNext()) {
					OrderInfoVo info = new OrderInfoVo();
					info = iter.next();

					if (pro.getProId() == info.getProId()) {
						// ���һ����Ʒ�������ˣ���ô�ͽ�����صļ���
						// ������
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
		} // end of while ���ˣ��Ѿ���һ�������������Ʒ���浽��lpro����

		return list;
	}

	public static List<BenefitAllVo> getBenefitAll(List<MyOrderVo> lm) {
		List<BenefitAllVo> list = new ArrayList<BenefitAllVo>();

		// ���ж�����ϸ
		List<OrderInfoVo> lo = new ArrayList<OrderInfoVo>();

		// ȡ�������������еĶ�����ϸ
		Iterator<MyOrderVo> iterm = lm.iterator();
		while (iterm.hasNext()) {
			MyOrderVo order = new MyOrderVo();
			order = iterm.next();

			// ������������ѯ�����ж�����ϸ
			List<OrderInfoVo> lt = new ArrayList<OrderInfoVo>();
			lt = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(order.getOrderId());

			// �Ѷ�����ϸ�����ܵ��Ǹ�����
			Iterator<OrderInfoVo> itert = lt.iterator();
			while (itert.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				info = itert.next();

				lo.add(info);
			}
		} // end of while �����Ѿ����ⶩ�������������ϸ��������lo����

		// ��Ʒ����
		List<CategoryVo> lc = new ArrayList<CategoryVo>();
		lc = ServiceFactory.getCategoryServiceInstance().findAllCategory();

		// ����һ������ͳ�ƶ���
		BenefitAllVo b = new BenefitAllVo();

		// ��Ӫҵ��
		float sales = 0.0f;

		// ������
		float benefit = 0.0f;

		Iterator<CategoryVo> iterc = lc.iterator();
		while (iterc.hasNext()) {
			CategoryVo category = new CategoryVo();
			category = iterc.next();

			// ��ѯ�����е���ƷС��
			List<ItemVo> li = new ArrayList<ItemVo>();
			li = ServiceFactory.getItemServiceInstance().findItemByCategoryId(
					category.getCatId());

			/* ����Ӧ�����㷨����ĵĲ��� */

			// ����һ���б���������һ�������������е���Ʒ
			List<ProductVo> lpro = new ArrayList<ProductVo>();

			// �Ȳ�ѯ�����е���Ʒ
			Iterator<ItemVo> iteri = li.iterator();
			while (iteri.hasNext()) {
				ItemVo item = new ItemVo();
				item = iteri.next();

				List<ProductVo> lp = new ArrayList<ProductVo>();
				lp = ServiceFactory.getProductServiceInstance().findAllProduct(
						item.getItemId());

				// ����Ʒ���浽������Ʒ�б�����
				Iterator<ProductVo> iterp = lp.iterator();
				while (iterp.hasNext()) {
					ProductVo product = new ProductVo();
					product = iterp.next();

					lpro.add(product);
				}
			} // end of while ���ˣ��Ѿ���һ�������������Ʒ���浽��lpro����

			/**
			 * ����Ҫ����һ������һ�������<br>
			 * ֻҪ�ǶԱȴ����������Ʒ�Ͷ�����ϸ�������Ʒ<br>
			 * �����ͬ���Ǹ��ͼ�����������<br>
			 * lo �� lpro �ıȽ�
			 */

			Iterator<ProductVo> iterator = lpro.iterator();
			while (iterator.hasNext()) {
				ProductVo pro = new ProductVo();
				pro = iterator.next();

				// ����Ҫ�����pro��lo�������Ʒ���бȽϣ����������ͬ��ô���Ǳ������
				Iterator<OrderInfoVo> iter = lo.iterator();
				while (iter.hasNext()) {
					OrderInfoVo info = new OrderInfoVo();
					info = iter.next();

					if (pro.getProId() == info.getProId()) {
						// ���һ����Ʒ�������ˣ���ô�ͽ�����صļ���
						// ������
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
