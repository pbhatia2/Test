package com.engine.promotion;

import java.util.HashMap;
import java.util.Map;

public class SKUMapper {

	private Map<String, SKU> inputList = new HashMap<>();
	
	public Map<String, SKU> parseAndValidateInput(String input, SkuModel skuFactory) {

		String[] SkuDetails = input.split(" ");

		if (SkuDetails.length != 2) {
			System.err.println("Invalid Input ! Please Enter a Valid Input");
			System.exit(0);
		}

		if (!skuFactory.getOfferPrice().containsKey(SkuDetails[0])) {
			System.err.println("Invalid SKU ID");
			System.exit(0);
		}

		if (!SkuDetails[1].matches("-?\\d+(\\.\\d+)?")) {
			System.err.println("Invalid SKU Quantity, Please enter valid number");
			System.exit(0);
		}

		inputList.put(SkuDetails[0], skuFactory.getSKUObject(SkuDetails[0], Integer.parseInt(SkuDetails[1])));

		return inputList;
	}
}
