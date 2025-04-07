package strategypattern;

import strategypattern.model.Order;
import strategypattern.model.OrderLine;
import strategypattern.model.Product;
import strategypattern.strategies.CategoryDiscountCalculatorStrategy;
import strategypattern.strategies.CheapestItemFreeCalculatorStrategy;
import strategypattern.strategies.FlatDiscountAbovePriceCalculatorStrategy;
import strategypattern.strategies.PercentDiscountCalculatorStrategy;

public class Program {

	public static void main(String[] args) {
		
		
		Product joystick = new Product("Telstar 3000", "Joystick", 690);
		Product keyboard = new Product("BlingBling 4000 Mechanical Keyboard", "Keyboard", 1800);
		Product mouse = new Product("WeeWee Beginner's Mouse", "Mouse", 1200);
		Product monitor = new Product("32 inch GlowPlate monitor", "Monitor", 4900);
		
		Order order = new Order();
		order.addOrderLine(new OrderLine(joystick, 4));
		order.addOrderLine(new OrderLine(keyboard, 1));
		order.addOrderLine(new OrderLine(mouse, 1));
		order.addOrderLine(new OrderLine(monitor, 2));
		
		System.out.println("Order: ");
		System.out.println(order);
		
		System.out.println();
		System.out.println("Setting totals calculator to CheapestItemFreeCalculatorStrategy");
		order.setTotalsCalculator(new CheapestItemFreeCalculatorStrategy());
		System.out.println("Price with cheapest item free: " + order.getOrderTotal());

		System.out.println();
		System.out.println("Setting totals calculator to PercentDiscountCalculatorStrategy with 10% off");
		order.setTotalsCalculator(new PercentDiscountCalculatorStrategy((float) 0.1));
		System.out.println("Price with cheapest item free: " + order.getOrderTotal());

		System.out.println();
		System.out.println("Setting totals calculator to FlatDiscountAbovePriceCalculatorStrategy");
		System.out.println("Orders above 10,000 get 1,500 off.");
		order.setTotalsCalculator(new FlatDiscountAbovePriceCalculatorStrategy(10000, 1500));
		System.out.println("Price with cheapest item free: " + order.getOrderTotal());
		
		System.out.println();
		System.out.println("Setting totals calculator to CategoryDiscountCalculatorStrategy");
		System.out.println("Orders with certain catagories get discount.");
		order.setTotalsCalculator(new CategoryDiscountCalculatorStrategy("Mouse", 50));
		System.out.println("Price with catagory discount: " + order.getOrderTotal());

		
		
		
	}

}
