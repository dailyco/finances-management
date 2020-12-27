package manufacture.datas;

public class Import {
	public String category;
	public String content;
	public Integer price;
	
	public Import(String category, String content, Integer price) {
		this.category = category;
		this.content = content;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
