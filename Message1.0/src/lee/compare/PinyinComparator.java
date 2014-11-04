package lee.compare;

import java.util.Comparator;

import bean.MyContacts;

public class PinyinComparator implements Comparator<MyContacts>{

	/**
	 * �Ƚ�������ϵ�˵�ƴ������
	 * 
	 * @param lhs ��ϵ��1
	 * @param rhs ��ϵ��2
	 * @return lhs��rhs�ıȽϽ��
	 * */
	@Override
	public int compare(MyContacts lhs, MyContacts rhs) {
		return lhs.getSortLetters().compareTo(rhs.getSortLetters());
	}

}
