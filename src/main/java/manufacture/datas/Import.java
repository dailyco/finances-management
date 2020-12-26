package manufacture.datas;

public class Import {
	public String category;
	public String content;
	public Integer price;
	public String priceStr;
	
	public Import(String category, String content, Integer price, String priceStr) {
		this.category = category;
		this.content = content;
		this.price = price;
		this.priceStr = priceStr;
	}
}
