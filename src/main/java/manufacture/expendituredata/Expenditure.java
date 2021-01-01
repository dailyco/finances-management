package manufacture.expendituredata;

public class Expenditure {
	public String category;
	public String subCategory;
	public String content;
	public Integer price;
	
	public Expenditure(String category, String subCategory, String content, Integer price) {
		this.category = category;
		this.subCategory = subCategory;
		this.content = content;
		this.price = price;
	}
}
