package com.qming.yunprint.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-04-27-20:38
 */
public class PricingUtil {
	Logger logger = LoggerFactory.getLogger(PricingUtil.class);
	public static final int UNPAID = 0;
	public static final int PAID = 1;
	public static final int UN_PRINT = 2;
	public static final int DONE = 3;
	// 黑白单面打印单价
	private static final double SINGLE_BLACK_PRINT_ONE_PAGE_PRICE = 0.1;
	// 彩色单面打印单价
	private static final double SINGLE_COLOR_PRINT_ONE_PAGE_PRICE = 1;
	//黑白双面打印单价
	private static final double DUPLEX_BLACK_PRINT_ONE_PAGE_PRICE = 0.2;

	public static double calculatePrice(int page, boolean color, boolean duplex, int copy) {
		double cost;
		if (color) {
			//彩色单面
			cost = page * SINGLE_COLOR_PRINT_ONE_PAGE_PRICE;
		} else {
			if (duplex) {
				//黑白双面
				cost = (page - page % 2) * DUPLEX_BLACK_PRINT_ONE_PAGE_PRICE + SINGLE_BLACK_PRINT_ONE_PAGE_PRICE;
			} else {
				//黑白单面
				cost = page * SINGLE_BLACK_PRINT_ONE_PAGE_PRICE;
			}
		}
		cost *= copy;
		DecimalFormat df = new DecimalFormat("#.##");
		return  Double.parseDouble(df.format(cost));
	}

}
