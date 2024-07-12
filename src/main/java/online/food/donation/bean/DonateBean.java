package online.food.donation.bean;

import java.sql.Blob;

public class DonateBean extends BaseBean {
	
	private String category;
	
	private String food;
	
	private long quamtity;
	
	private String ngoname;
	
	private long ngoid;
	
	private Blob image;
	
	private long userid;
	
	private String donarname;
	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getDonarname() {
		return donarname;
	}

	public void setDonarname(String donarname) {
		this.donarname = donarname;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public long getQuamtity() {
		return quamtity;
	}

	public void setQuamtity(long quamtity) {
		this.quamtity = quamtity;
	}

	public String getNgoname() {
		return ngoname;
	}

	public void setNgoname(String ngoname) {
		this.ngoname = ngoname;
	}

	public long getNgoid() {
		return ngoid;
	}

	public void setNgoid(long ngoid) {
		this.ngoid = ngoid;
	}

	@Override
	public String getKey() {
		return id+"";
	}

	@Override
	public String getValue() {
		return ngoname;
	}

}
