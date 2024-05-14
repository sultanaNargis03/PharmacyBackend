package com.pharma.model;

public class MedicineWrapper 
{
	private Integer id;
	private String medicineCompany;
	private Double medicinePrice;
	private Integer medicineQuantity;
	
	public MedicineWrapper() 
	{
		
	}

	public MedicineWrapper(Integer id,String medicineCompany,Double medicinePrice, Integer medicineQuantity) 
	{
		this.id=id;
		this.medicineCompany = medicineCompany;
		this.medicinePrice = medicinePrice;
		this.medicineQuantity = medicineQuantity;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMedicineCompany() {
		return medicineCompany;
	}

	public void setMedicineCompany(String medicineCompany) {
		this.medicineCompany = medicineCompany;
	}


	public Double getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(Double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

	public Integer getMedicineQuantity() {
		return medicineQuantity;
	}

	public void setMedicineQuantity(Integer medicineQuantity) {
		this.medicineQuantity = medicineQuantity;
	}



	@Override
	public String toString() {
		return "MedicineWrapper [id=" + id + ", medicineCompany=" + medicineCompany + ", medicinePrice=" + medicinePrice
				+ ", medicineQuantity=" + medicineQuantity + "]";
	}
	

}

