package com.engine.promotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class PromotionEngineController {
	private final  InvoiceCalculator calculator;
	private int totalInvoice = 0;
	private Map<String, SKU> inputList = new HashMap<>();
	
	PromotionEngineController(InvoiceCalculator calculator)
	{
		this.calculator = calculator;
	}
	
	public static void main(String args[]) throws IOException
	{
        PromotionEngineController controller = new PromotionEngineController(new Invoice());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));		
		
        System.out.println("Enter SKU id and quantity eg A 50....!!");
        
        SkuFactoryModel skuFactory = new SkuFactoryModel();
        
        String input = reader.readLine().trim().toUpperCase();
        while(input != null && !input.isEmpty())
        {
        	controller.parseAndValidateInput(input, skuFactory);
        	input = reader.readLine().trim().toUpperCase();
        }      
        controller.totalInvoice = controller.getTotalInvoice(controller.inputList); 
        System.out.println(controller.totalInvoice);
	}

	private void parseAndValidateInput(String input, SkuFactoryModel skuFactory) {

		String[] SkuDetails = input.split(" ");
		if (SkuDetails.length != 2) {
			System.out.println("Invalid Input ! Please Enter a Valid Input");
			System.exit(0);
		}
		
		if(!skuFactory.getOfferPrice().containsKey(SkuDetails[0]))
		{
			System.out.println("Invalid SKU ID");
			System.exit(0);
		}
		
		if(!SkuDetails[1].matches("-?\\d+(\\.\\d+)?"))
		{
			System.out.println("Invalid SKU Quantity, Please enter valid number");
			System.exit(0);
		}
		
	 	inputList.put(SkuDetails[0], skuFactory.getSKUObject(SkuDetails[0], Integer.parseInt(SkuDetails[1])));
	}

	public InvoiceCalculator getCalculator() {
		return calculator;
	}

	public int getTotalInvoice(Map<String, SKU>  skuData) {
		return this.getCalculator().fetchTotal( skuData);
	}
}
