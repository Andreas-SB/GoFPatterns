package strategypattern.strategies;

import java.util.List;

import strategypattern.model.OrderLine;
import strategypattern.model.Product;

public class CategoryDiscountCalculatorStrategy implements OrderTotalCalculatorStrategy {
	private String categoryToDiscount;
	private int flatDiscount;

	public CategoryDiscountCalculatorStrategy(String categoryToDiscount, int flatDiscount) {
		super();
		this.categoryToDiscount = categoryToDiscount;
		this.flatDiscount = flatDiscount;
	}

	@Override
	public float getTotal(List<OrderLine> orderLines) {
		float total = 0;
		float linePrice = 0;
		for(OrderLine line : orderLines) {	
			if(line.getProduct().getCategory().toUpperCase().equals(getCategoryToDiscount())) {
				linePrice = line.getLineTotal() - line.getQuantity() * getFlatDiscount();
				if(linePrice < 0) {linePrice = 0;}
			}
			else {
				linePrice = line.getLineTotal();
			}

		}
		total += linePrice;
		return total;
	}


	public String getCategoryToDiscount() {
		return categoryToDiscount;
	}

	public void setCategoryToDiscount(String categoryToDiscount) {
		this.categoryToDiscount = categoryToDiscount;
	}

	public int getFlatDiscount() {
		return flatDiscount;
	}

	public void setFlatDiscount(int flatDiscount) {
		this.flatDiscount = flatDiscount;
	}
}
