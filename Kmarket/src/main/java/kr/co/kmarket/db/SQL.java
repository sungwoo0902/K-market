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

	
	// 사용자 중복체크
	public static final String DUPLICATION_CHECK_UID        = "SELECT COUNT(*) FROM `km_member` WHERE `uid`=?";
	public static final String DUPLICATION_CHECK_HP         = "SELECT COUNT(*) FROM `km_member` WHERE `hp`=?";
	public static final String DUPLICATION_CHECK_TEL        = "SELECT COUNT(*) FROM `km_member` WHERE `tel`=?";
	public static final String DUPLICATION_CHECK_FAX        = "SELECT COUNT(*) FROM `km_member` WHERE `fax`=?";
	public static final String DUPLICATION_CHECK_BIZ_NUM    = "SELECT COUNT(*) FROM `km_member` WHERE `bizRegNum`=?";
	public static final String DUPLICATION_CHECK_ONLINE_NUM = "SELECT COUNT(*) FROM `km_member` WHERE `comRegNum`=?";
	public static final String DUPLICATION_CHECK_EMAIL      = "SELECT COUNT(*) FROM `km_member` WHERE `email`=?";
	public static final String DUPLICATION_CHECK_MANAGER_HP = "SELECT COUNT(*) FROM `km_member` WHERE `managerHp`=?";
	
	
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
												+ "`prodCompany`=?,"
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
	public final static String DELETE_PRODUCT 					= "DELETE FROM `km_product` WHERE `seller`=? AND prodNo =?";
	public final static String SELECT_PRODUCT 					= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "AND `prodNo`=?";
	public final static String SELECT_PRODUCTS_ALL 				= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `seller`=? "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE1 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE2 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "LIMIT ?, 10";
	public static final String SELECT_COUNT_PRODUCTS_ALL 		= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `seller`=?";
	public static final String SELECT_COUNT_PRODUCTS_BY_CATE1 	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `prodCate1`=?";
	public static final String SELECT_COUNT_PRODUCTS_BY_CATE2 	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `prodCate1`=? AND `prodCate2`=?";
	
	//********************************************************************************************************//
	//********************************************* Product_Cart *********************************************//
	//********************************************************************************************************//
	public static final String INSERT_CART 						= "INSERT INTO `km_product_cart` SET "
																	+ "`uid`=?,"
																	+ "`prodNo`=?,"
																	+ "`count`=?,"
																	+ "`price`=?,"
																	+ "`discount`=?,"
																	+ "`point`=?,"
																	+ "`delivery`=?,"
																	+ "`total`=?,"
																	+ "`rdate`=NOW()";
	public static final String SELECT_CARTS						= "SELECT a.*, b.thumb1, b.prodCate1, b.prodCate2, b.prodName, b.descript FROM "
																	+ "`km_product_cart` AS a JOIN "
																	+ "`km_product` AS b ON a.prodNo=b.prodNo "
																	+ "WHERE `uid`=?";
	
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
	
	//**************************************************************************************************//
	//********************************************* CS *************************************************//
	//**************************************************************************************************//

	public static final String INSERT_BOARD = "INSERT INTO `km_board` SET "
												+ "`group`=?, "
												+ "`cate1`=?, "
												+ "`cate2`=?, "
												+ "`uid`=?, "
												+ "`title`=?, "
												+ "`content`=?, "
												+ "`rdate`=NOW()";
	
	public static final String SELECT_BOARD = "SELECT "
												+ "a.*, "
												+ "b.`group_name`, "
												+ "c.`cate1_name`, "
												+ "d.`cate2_name` "
												+ "FROM `km_board` AS a "
												+ "LEFT JOIN `km_board_group` AS b ON a.`group` = b.`group` "
												+ "LEFT JOIN `km_board_cate1` AS c ON a.`cate1` = c.`cate1` AND a.`group` = c.`group` "
												+ "LEFT JOIN `km_board_cate2` AS d ON a.`cate2` = d.`cate2` AND a.`cate1` = d.`cate1` "
												+ "WHERE `no`=?";
	
	public static final String SELECT_BOARDS_MAIN_CATE = "SELECT "
														+ "a.*, "
														+ "b.`group_name`, "
														+ "c.`cate1_name`, "
														+ "d.`cate2_name` "
														+ "FROM `km_board` AS a "
														+ "LEFT JOIN `km_board_group` AS b ON a.`group` = b.`group` "
														+ "LEFT JOIN `km_board_cate1` AS c ON a.`cate1` = c.`cate1` AND a.`group` = c.`group` "
														+ "LEFT JOIN `km_board_cate2` AS d ON a.`cate2` = d.`cate2` AND a.`cate1` = d.`cate1` "
														+ "WHERE `parent`=0 AND a.`group`=? "
														+ "ORDER BY `no` DESC "
														+ "LIMIT ?, 10";
	
	public static final String SELECT_BOARDS_MIDDLE_CATE = "SELECT "
														+ "a.*, "
														+ "b.`group_name`, "
														+ "c.`cate1_name`, "
														+ "d.`cate2_name` "
														+ "FROM `km_board` AS a "
														+ "LEFT JOIN `km_board_group` AS b ON a.`group` = b.`group` "
														+ "LEFT JOIN `km_board_cate1` AS c ON a.`cate1` = c.`cate1` AND a.`group` = c.`group` "
														+ "LEFT JOIN `km_board_cate2` AS d ON a.`cate2` = d.`cate2` AND a.`cate1` = d.`cate1` "
														+ "WHERE `parent`=0 AND `group`=? AND `cate1`=? "
														+ "ORDER BY `no` DESC "
														+ "LIMIT ?, 10";
	
	public static final String SELECT_BOARDS_SUB_CATE = "SELECT "
														+ "a.*, "
														+ "b.`group_name`, "
														+ "c.`cate1_name`, "
														+ "d.`cate2_name` "
														+ "FROM `km_board` AS a "
														+ "LEFT JOIN `km_board_group` AS b ON a.`group` = b.`group` "
														+ "LEFT JOIN `km_board_cate1` AS c ON a.`cate1` = c.`cate1` AND a.`group` = c.`group` "
														+ "LEFT JOIN `km_board_cate2` AS d ON a.`cate2` = d.`cate2` AND a.`cate1` = d.`cate1` "
														+ "WHERE `parent`=0 AND `group`=? AND `cate1`=? AND `cate2`=? "
														+ "ORDER BY `no` DESC "
														+ "LIMIT ?, 10";
	
	public static final String SELECT_GROUP_LIST_WHEN_GROUP_CHOOSE = "SELECT * FROM `km_board_group` WHERE `group`=?";
	public static final String SELECT_CATE1_LIST_WHEN_CATE1_CHOOSE = "SELECT * FROM `km_board_cate1` WHERE `cate1`=?";
	public static final String SELECT_CATE2_LIST_WHEN_CATE2_CHOOSE = "SELECT * FROM `km_board_cate2` WHERE `cate2`=?";
	
	public static final String SELECT_COUNT_MAIN_CATE   = "SELECT COUNT(*) FROM `km_board` WHERE `group`=?";
	public static final String SELECT_COUNT_MIDDLE_CATE = "SELECT COUNT(*) FROM `km_board` WHERE `group`=? AND `cate1`=?";
	public static final String SELECT_COUNT_SUB_CATE    = "SELECT COUNT(*) FROM `km_board` WHERE `group`=? AND `cate1`=? AND `cate2`=?";
}