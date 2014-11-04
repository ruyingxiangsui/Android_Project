package lee.compare;

import java.util.Comparator;

import bean.MyContacts;

public class PinyinComparator implements Comparator<MyContacts>{

	/**
	 * 比较两个联系人的拼音序列
	 * 
	 * @param lhs 联系人1
	 * @param rhs 联系人2
	 * @return lhs与rhs的比较结果
	 * */
	@Override
	public int compare(MyContacts lhs, MyContacts rhs) {
		return lhs.getSortLetters().compareTo(rhs.getSortLetters());
	}

}
