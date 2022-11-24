package tests;

import utils.DataBaseUtility;

public class DataBase_Runner {

	public static void main(String[] args) {
		
		
		DataBaseUtility.setupDBConnection();
		DataBaseUtility.runQuery("select * from Actor");
		DataBaseUtility.returnResult();
		DataBaseUtility.closeDBConnection();

	}

}
