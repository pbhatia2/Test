package com.engine.promotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class PromotionEngineController {
	private final  InvoiceCalculator calculator;
	private int totalInvoice = 0;
	private static SKUMapper mapper = new SKUMapper();
	
	PromotionEngineController(InvoiceCalculator calculator)
	{
		this.calculator = calculator;
	}
	
	public static void main(String args[])
	{
		try {
			PromotionEngineController controller = new PromotionEngineController(new Invoice());
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			Map<String, SKU> inputList = null;
			System.out.println("Enter SKU id and quantity eg A 50....!!");
			SkuModel skuFactory = new SkuModel();
			String input = reader.readLine().trim().toUpperCase();
			
			/* Taking input from User line by line
			 * parsing and validating input
			 */
			while (input != null && !input.isEmpty()) {
				inputList = mapper.parseAndValidateInput(input, skuFactory);
				input = reader.readLine().trim().toUpperCase();
			}
			controller.totalInvoice = controller.getTotalInvoice(inputList);
			System.out.println(controller.totalInvoice);
		} catch (IOException e) {
				System.out.println("Exception in taking input" + e);
		}
	}

	/* Calculates total invoice */
	public int getTotalInvoice(Map<String, SKU>  skuData) {
		return calculator.fetchTotal(skuData);
	}
}
