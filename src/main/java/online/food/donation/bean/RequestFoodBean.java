package online.food.donation.bean;

public class RequestFoodBean extends BaseBean{
	
	
	private String ngoName;
	
	private String category;
	
	private String foodname;
	
	private String address;
	
	private long quantity;
	
	private long ngoid;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String sendername;
	
	private long sendercontactno;
	
	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public long getSendercontactno() {
		return sendercontactno;
	}

	public void setSendercontactno(long sendercontactno) {
		this.sendercontactno = sendercontactno;
	}

	public String getNgoName() {
		return ngoName;
	}

	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getNgoid() {
		return ngoid;
	}

	public void setNgoid(long ngoid) {
		this.ngoid = ngoid;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
