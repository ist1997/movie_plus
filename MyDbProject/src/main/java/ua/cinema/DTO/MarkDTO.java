package ua.cinema.DTO;

public class MarkDTO {
	private double sumOfMarks;
	private int amount;
	
	public MarkDTO (int amount, double sumOfMarks) {
		this.setSumOfMarks(sumOfMarks);
		this.setAmount(amount);
	}

	public double getSumOfMarks() {
		return sumOfMarks;
	}

	public void setSumOfMarks(double sumOfMarks) {
		this.sumOfMarks = sumOfMarks;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
