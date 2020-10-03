package com.engine.promotionTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import com.engine.promotion.Invoice;
import com.engine.promotion.SKU;

class InvoiceTest {

	public static  Map<String, SKU> input;
	Invoice calculator = new Invoice();
	
	@Test 
	public void fetchInvoiceTest1()
	{
		input = new HashMap<>();
		input.put("A", new SKU("A", 1, 50));
		input.put("B", new SKU("B", 1, 30));
		input.put("C", new SKU("C", 1, 20));
		int expectedOutput = 100;
		int actualOutput = calculator.fetchTotal(input);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test 
	public void fetchInvoiceTest2()
	{
		input = new HashMap<>();
		input.put("A", new SKU("A", 5, 50));
		input.put("B", new SKU("B", 5, 30));
		input.put("C", new SKU("C", 1, 20));
		int expectedOutput = 370;
		int actualOutput = calculator.fetchTotal(input);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test 
	public void fetchInvoiceTest3()
	{
		input = new HashMap<>();
		input.put("A", new SKU("A", 3, 50));
		input.put("B", new SKU("B", 5, 30));
		input.put("C", new SKU("C", 1, 20));
		input.put("D", new SKU("D", 1, 15));
		int expectedOutput = 280;
		int actualOutput = calculator.fetchTotal(input);
		assertEquals(expectedOutput, actualOutput);
	}

}
