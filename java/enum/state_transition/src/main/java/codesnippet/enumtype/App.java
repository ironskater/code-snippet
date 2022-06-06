package codesnippet.enumtype;

public class App
{
	private PizzaStatus status;

	public enum PizzaStatus
	{
		ORDERED (5) {
			@Override
			public boolean isOrdered() {
				return true;
			}
		},
		READY (2){
			@Override
			public boolean isReady() {
				return true;
			}
		},
		DELIVERED (0) {
			@Override
			public boolean isDelivered() {
				return true;
			}
		};

		PizzaStatus (int timeToDelivery) {
			this.timeToDelivery = timeToDelivery;
		}

		private int timeToDelivery;

		public boolean isOrdered() {return false;}

		public boolean isReady() {return false;}

		public boolean isDelivered() {return false;}

		public int getTimeToDelivery() {
			return timeToDelivery;
		}
	}

	public boolean isDeliverable() {
		return this.status.isReady();
	}

	public void printTimeToDeliver() {
		System.out.println("Time to delivery is " +
			this.status.getTimeToDelivery());
	}

	// Methods that set and get the status variable.
	public PizzaStatus getStatus() {
		return status;
	}

	public void setStatus(PizzaStatus status) {
		this.status = status;
	}
}
