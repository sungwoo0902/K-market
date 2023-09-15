package kr.co.kmarket.db;

public class SQL {

	//**************************************************************************************************//
	//********************************************* Member *********************************************//
	//**************************************************************************************************//
	public static final String INSERT_MEMBER = "INSERT INTO `km_member` SET "
												+ "`uid`= ?, "
												+ "`pass`= SHA2(?, 256), "
												+ "`name`= ?, "
												+ "`gender`= ?, "
												+ "`email`= ?, "
												+ "`type` = 1, "
												+ "`hp`= ?, "
												+ "`zip`= ?, "
												+ "`addr1`= ?, "
												+ "`addr2`= ?, "
												+ "`regip`= ?, "
												+ "`location`= ?, "
												+ "`rdate`= NOW()";
	
	public static final String INSERT_SELLER = "INSERT INTO `km_member` SET "
												+ "`uid`=?, "
												+ "`pass`=SHA2(?, 256), "
												+ "`company`=?, "
												+ "`ceo`=?, "
												+ "`bizRegNum`=?, "
												+ "`comRegNum`=?, "
												+ "`tel`=?, "
												+ "`fax`=?, "
												+ "`email`=?, "
												+ "`zip`=?, "
												+ "`addr1`=?, "
												+ "`addr2`=?, "
												+ "`manager`=?, "
												+ "`managerHp`=?, "
												+ "`regip`=?, "
												+ "`name`=?, "
												+ "`hp`=?, "
												+ "`type`=2, "
												+ "`level`=5, "
												+ "`gender`=0, "
												+ "`rdate`=NOW()";
	
	public static final String SELECT_MEMBER = "SELECT * FROM `km_member` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
	
	//********************************************************************************************************//
	//********************************************* Member_Point *********************************************//
	//********************************************************************************************************//
	
	
	
	//*************************************************************************************************//
	//********************************************* Terms *********************************************//
	//*************************************************************************************************//
	public static final String SELECT_TERMS = "SELECT * FROM `km_member_terms`";
	
	
	//***************************************************************************************************//
	//********************************************* Product *********************************************//
	//***************************************************************************************************//
	public static final String INSERT_PRODUCT = "INSERT INTO `km_product` SET "
												+ "`prodCate1`=?,"
												+ "`prodCate2`=?,"
												+ "`prodName`=?,"
												+ "`descript`=?,"
												+ "`company`=?,"
												+ "`seller`=?,"
												+ "`price`=?,"
												+ "`discount`=?,"
												+ "`point`=?,"
												+ "`stock`=?,"
												+ "`delivery`=?,"
												+ "`thumb1`=?,"
												+ "`thumb2`=?,"
												+ "`thumb3`=?,"
												+ "`detail`=?,"
												+ "`status`=?,"
												+ "`duty`=?,"
												+ "`receipt`=?,"
												+ "`bizType`=?,"
												+ "`origin`=?,"
												+ "`ip`=?,"
												+ "`rdate`=NOW()";
	public final static String SELECT_PRODUCTS_ALL = "SELECT * FROM `km_product` WHERE `stock` > 0 LIMIT ?, 10";
	
	public final static String SELECT_COUNT_PRODUCTS_ALL = "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0";
	
	public final static String DELETE_PRODUCT = "DELETE FROM `km_product` WHERE `uid`=?";
	
	//********************************************************************************************************//
	//********************************************* Product_Cart *********************************************//
	//********************************************************************************************************//
	
	
	
	//*********************************************************************************************************//
	//********************************************* Product_Cate1 *********************************************//
	//*********************************************************************************************************//
	public static final String SELECT_CATE1 = "SELECT * FROM `km_product_cate1` WHERE `cate1`=?";
	public static final String SELECT_CATE1S = "SELECT * FROM `km_product_cate1`";
	
	
	//*********************************************************************************************************//
	//********************************************* Product_Cate2 *********************************************//
	//*********************************************************************************************************//
	public static final String SELECT_CATE2 = "SELECT * FROM `km_product_cate2` WHERE `cate2`=?";
	public static final String SELECT_CATE2S = "SELECT * FROM `km_product_cate2` WHERE `cate1`=? ORDER BY `cate2`";
	
	
	//*********************************************************************************************************//
	//********************************************* Product_Order *********************************************//
	//*********************************************************************************************************//
	
	
	
	//**************************************************************************************************************//
	//********************************************* Product_Order_Item *********************************************//
	//**************************************************************************************************************//
	
	
	
	//**********************************************************************************************************//
	//********************************************* Product_Review *********************************************//
	//**********************************************************************************************************//
}
