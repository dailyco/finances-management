package manufacture.datas;

public class Expenditure {
	String category;
	String subCategory;
	String content;
	Integer price;
	String priceStr;
	
	public Expenditure(String category, String subCategory, String content, Integer price, String priceStr) {
		this.category = category;
		this.subCategory = subCategory;
		this.content = content;
		this.price = price;
		this.priceStr = priceStr;
	}
}
