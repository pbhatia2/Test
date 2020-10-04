package com.engine.promotion;
import java.util.Map;

public class Invoice implements InvoiceCalculator {
	
	private static final int ACTIVE_PROMOTION_A_PRICE = 130;
	private static final int ACTIVE_PROMOTION_B_PRICE = 45;
	private static final int ACTIVE_PROMOTION_A_QUANTITY = 3;
	private static final int ACTIVE_PROMOTION_B_QUANTITY = 2;
			
	@Override
	public int fetchTotal(Map<String, SKU> skuList) {
		int totalAmount = 0;
		int cdPromotionCount = 0;

		if (skuList.containsKey("C") && skuList.containsKey("D")) {
			cdPromotionCount = (skuList.get("C").getDiscountQuantity() < skuList.get("D").getDiscountQuantity())
					? skuList.get("C").getDiscountQuantity()
					: skuList.get("D").getDiscountQuantity();
			totalAmount += cdPromotionCount * 30;
		}

		for (Map.Entry<String, SKU> sku : skuList.entrySet()) {
			if (sku.getKey().equals("A")) {
				// 3 of A's for 130
				int aPromotionCount = sku.getValue().getDiscountQuantity() / 3;
				totalAmount += (Invoice.ACTIVE_PROMOTION_A_PRICE * aPromotionCount) + (sku.getValue().getofferPrice()
						* (sku.getValue().getDiscountQuantity() % Invoice.ACTIVE_PROMOTION_A_QUANTITY));
			} else if (sku.getKey().equals("B")) {
				// 2 of B's for 45
				int aPromotionCount = sku.getValue().getDiscountQuantity() / 2;
				totalAmount += (ACTIVE_PROMOTION_B_PRICE * aPromotionCount) + (sku.getValue().getofferPrice()
						* (sku.getValue().getDiscountQuantity() % Invoice.ACTIVE_PROMOTION_B_QUANTITY));
			} else if (sku.getKey().equals("C")) {
				// C individual price 20
				totalAmount += (sku.getValue().getDiscountQuantity() - cdPromotionCount)
						* sku.getValue().getofferPrice();
			} else if (sku.getKey().equals("D")) {
				// D individual price 15
				totalAmount += (sku.getValue().getDiscountQuantity() - cdPromotionCount)
						* sku.getValue().getofferPrice();
			}
		}
		return totalAmount;
	}

}
