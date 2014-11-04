package bean;

public class MyContacts {
	private String name;
	private String phone;

	private String sortLetters;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MyContacts) {
			MyContacts con = (MyContacts) o;
			return this.getName().equals(con.getName())
					&& this.getPhone().equals(con.getPhone());
		}
		return super.equals(o);
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

}
