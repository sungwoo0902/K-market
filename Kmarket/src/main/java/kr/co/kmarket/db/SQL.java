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
	public static final String SELECT_MEMBER_AUTO_LOGIN = "SELECT * FROM `km_member` WHERE `uid`=?";
	public static final String SELECT_MEMBER_RECEIPT = "SELECT `name`,`hp`,`zip`,`addr1`,`addr2`,`point` FROM `km_member` "
														+ "WHERE `uid`=?";
	// 포인트 사용시 포인트 차감
	public static final String MINUS_MEMBER_POINT = "UPDATE `km_member` SET `point`= `point`-? WHERE `uid`=?";
	// 포인트 사용시 포인트 적립
	public static final String PLUS_MEMBER_POINT = "UPDATE `km_member` SET `point`= `point`+? WHERE `uid`=?";
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
	public final static String DELETE_PRODUCT_PRIVACY 			= "DELETE FROM `km_product` WHERE prodNo =?";
	
	public final static String SELECT_PRODUCT 					= "SELECT "
																+ "	a.*, "
																+ "	b.`level`, "
																+ "	b.`company`, "
																+ "	c.c1Name, "
																+ "	d.c2Name "
																+ "FROM `km_product` AS a "
																+ "JOIN `km_member` AS b ON a.`seller` = b.`uid` "
																+ "JOIN `km_product_cate1` AS c ON a.`prodCate1`=c.`cate1` "
																+ "JOIN `km_product_cate2` AS d ON a.`prodCate1`=d.`cate1` "
																+ "WHERE a.`stock` > 0 AND a.`prodCate2`=d.`cate2` AND `prodNo`=?";
	
	public final static String SELECT_PRODUCTS_ALL 				= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `seller`=? "
																	+ "LIMIT ?, 10";
	
	public final static String SELECT_PRODUCTS_ADMIN 		    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "LIMIT ?, 10";
	

	public final static String SELECT_PRODUCTS_SOLD_DESC	    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "ORDER BY `sold` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_PRICE_ASC	    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "ORDER BY `price`*(100 - `discount`) ASC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_PRICE_DESC	    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "ORDER BY `price`*(100 - `discount`) DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_SCORE_DESC	    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "ORDER BY `score` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_REVIEW_DESC	    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "ORDER BY `review` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_LATELY	    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "ORDER BY `rdate` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_HIT_DESC	    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "ORDER BY `hit` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_DISCOUNT_DESC	    = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `discount` > 0 "
																	+ "ORDER BY `rdate` DESC "
																	+ "LIMIT ?, 10";
	
	public final static String SELECT_PRODUCTS_ALL_SEARCH 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `prodName` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_ADMIN_SEARCH1 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `prodName` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_ADMIN_SEARCH2 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `prodNo` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_ADMIN_SEARCH3 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `prodCompany` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_ADMIN_SEARCH4 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `seller` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_ALL_SEARCH1		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `seller`=? AND `prodName` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_ALL_SEARCH2 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `seller`=? AND `prodNo` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_ALL_SEARCH3 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `seller`=? AND `prodCompany` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_ALL_SEARCH4 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 AND `seller`=? AND `seller` LIKE CONCAT('%', ?, '%') "
																	+ "LIMIT ?, 10";
	
	public final static String SELECT_PRODUCTS_BY_CATE1 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE1_SOLD_DESC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "ORDER BY `sold` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE1_PRICE_ASC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "ORDER BY `price`*(100 - `discount`) ASC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE1_PRICE_DESC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "ORDER BY `price`*(100 - `discount`) DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE1_SCORE_DESC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "ORDER BY `score` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE1_REVIEW_DESC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "ORDER BY `review` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE1_LATELY = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "ORDER BY `rdate` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE2 		= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE2_SOLD_DESC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "ORDER BY `sold` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE2_PRICE_ASC= "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "ORDER BY `price`*(100 - `discount`) ASC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE2_PRICE_DESC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "ORDER BY `price`*(100 - `discount`) DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE2_SCORE_DESC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "ORDER BY `score` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE2_REVIEW_DESC = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "ORDER BY `review` DESC "
																	+ "LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_BY_CATE2_LATELY = "SELECT a.*, b.level, b.company FROM "
																	+ "`km_product` AS a JOIN `km_member` AS b "
																	+ "ON a.seller=b.uid "
																	+ "WHERE `stock` > 0 "
																	+ "AND `prodCate1`=? "
																	+ "AND `prodCate2`=? "
																	+ "ORDER BY `rdate` DESC "
																	+ "LIMIT ?, 10";
	public static final String SELECT_COUNT_PRODUCTS_ALL 		= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0";
	public static final String SELECT_COUNT_PRODUCTS_SELLER 	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `seller`=?";
	public static final String SELECT_COUNT_PRODUCTS_SEARCH1	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `seller`=? AND `prodNo` LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_SEARCH2	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `seller`=? AND `prodName` LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_SEARCH3	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `seller`=? AND `prodCompany` LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_SEARCH4	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `seller`=? AND `seller` LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_ADMIN_SEARCH1	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `prodName` LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_ADMIN_SEARCH2	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `prodNo` LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_ADMIN_SEARCH3	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `prodCompany` LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_ADMIN_SEARCH4	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `seller` LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_SEARCH	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `seller`=? AND `c1Name`=? LIKE CONCAT('%', ?, '%')";
	public static final String SELECT_COUNT_PRODUCTS_BY_ALL 	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0";
	public static final String SELECT_COUNT_PRODUCTS_BY_CATE1 	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `prodCate1`=?";
	public static final String SELECT_COUNT_PRODUCTS_BY_CATE2 	= "SELECT COUNT(*) FROM `km_product` WHERE `stock` > 0 AND `prodCate1`=? AND `prodCate2`=?";
	
	public static final String SELECT_BEST_PRODUCT      = "SELECT * FROM `km_product` ORDER BY `sold` DESC LIMIT 5";
	public static final String SELECT_HIT_PRODUCT       = "SELECT * FROM `km_product` ORDER BY `hit` DESC LIMIT 8";
	public static final String SELECT_RECOMMEND_PRODUCT = "SELECT * FROM `km_product` ORDER BY `score` DESC LIMIT 8";
	public static final String SELECT_CURRENT_PRODUCT   = "SELECT * FROM `km_product` ORDER BY `rdate` DESC LIMIT 8";
	public static final String SELECT_DISCOUNT_PRODUCT  = "SELECT * FROM `km_product` ORDER BY `discount` DESC LIMIT 8";
	
	public static final String UPDATE_PRODUCT_HIT = "UPDATE `km_product` SET `hit`=`hit`+1 WHERE `prodNo`=?";
	
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
	public static final String SELECT_COUNT_CART				= "SELECT COUNT(*) FROM `km_product_cart` WHERE `uid`=? AND `prodNo`=?";
	public static final String SELECT_CARTS						= "SELECT a.*, b.thumb1, b.prodCate1, b.prodCate2, b.prodName, b.descript, b.price FROM "
																	+ "`km_product_cart` AS a JOIN "
																	+ "`km_product` AS b ON a.prodNo=b.prodNo "
																	+ "WHERE `uid`=?";
	public static final String SELECT_SELECTED_CART				= "SELECT a.*, b.thumb1, b.prodCate1, b.prodCate2, b.prodName, b.descript, b.price FROM"
																	+ "`km_product_cart` AS a JOIN "
																	+ "`km_product` AS b ON a.prodNo=b.prodNo "
																	+ "WHERE `uid`=? AND `cartNo`=?";
	public static final String UPDATE_CART						= "UPDATE `km_product_cart` "
																	+ "SET `count`= `count`+?, "
																	+ "`total`= `total`+? "
																	+ "WHERE `uid`=? AND `prodNo`=?";
	public static final String DELETE_CART						= "DELETE FROM `km_product_cart` WHERE `uid`=? AND `cartNo`=?";
	
	public static final String DELETE_CART_BY_PRODUCT = "DELETE FROM `km_product_cart` WHERE `prodNo`=?";
	//*********************************************************************************************************//
	//********************************************* Product_Cate1 *********************************************//
	//*********************************************************************************************************//
	public static final String SELECT_CATE1 = "SELECT * FROM `km_product_cate1` WHERE `cate1`=?";
	public static final String SELECT_CATE1S = "SELECT * FROM `km_product_cate1` ORDER BY `cate1`";
	
	
	//*********************************************************************************************************//
	//********************************************* Product_Cate2 *********************************************//
	//*********************************************************************************************************//
	public static final String SELECT_CATE2 = "SELECT * FROM `km_product_cate2` WHERE `cate2`=?";
	public static final String SELECT_CATE2S = "SELECT * FROM `km_product_cate2` WHERE `cate1`=? ORDER BY `cate2`";
	
	public static final String SELECT_ALL_CATE = "SELECT a.*, b.`cate2`, b.`c2Name` FROM `km_product_cate1` AS a	"
												+ "JOIN `km_product_cate2` AS b "
												+ "ON a.`cate1` = b.`cate1` "
												+ "ORDER BY a.`cate1`, b.`cate2`";
	
	public static final String SELECT_CATE = "SELECT a.*, b.cate2, b.c2Name FROM `km_product_cate1` AS a JOIN `km_product_cate2` AS b WHERE a.`cate1`=? AND b.`cate1`=? AND b.`cate2`=?";
	
	//*********************************************************************************************************//
	//********************************************* Product_Order *********************************************//
	//*********************************************************************************************************//
	public static final String INSERT_ORDER = "INSERT INTO `km_product_order` SET "
												+ "`ordUid`=?,"
												+ "`ordCount`=?,"
												+ "`ordPrice`=?,"
												+ "`ordDiscount`=?,"
												+ "`ordDelivery`=?,"
												+ "`savePoint`=?,"
												+ "`ordTotPrice`=?,"
												+ "`ordComplete`=?,"
												+ "`recipName`=?,"
												+ "`recipHp`=?,"
												+ "`recipZip`=?,"
												+ "`recipAddr1`=?,"
												+ "`recipAddr2`=?,"
												+ "`ordPayment`=?,"
												+ "`ordDate`=NOW()";
	public static final String SELECT_ORDER = "SELECT * FROM `km_product_order` WHERE `ordNo`=? AND `ordUid`=?";
	public static final String SELECT_ORDER_COUNT = "SELECT * FROM `km_product_order` WHERE `ordUid`=? AND `ordComplete`=0";
	public static final String DELETE_BEFORE_ORDER = "DELETE FROM `km_product_order` WHERE `ordUid`=? AND `ordComplete`=0";
	public static final String UPDATE_ORDER = "UPDATE `km_product_order` SET "
			+ "`usedPoint`=?,"
			+ "`ordTotPrice`=?,"
			+ "`recipName`=?,"
			+ "`recipHp`=?,"
			+ "`recipZip`=?,"
			+ "`recipAddr1`=?,"
			+ "`recipAddr2`=?,"
			+ "`ordPayment`=?,"
			+ "`ordComplete`=? "
			+ "WHERE `ordNo`=?";
	public static final String INSERT_POINT = "INSERT INTO `km_member_point` SET `uid`=?, `ordNo`=?, `point`=?, `pointDate`=NOW()";
	public static final String SELECT_LAST_ORDNO = "SELECT * FROM `km_product_order` WHERE `ordUid`=? ORDER BY `ordNo` DESC LIMIT 1";
	
	//**************************************************************************************************************//
	//********************************************* Product_Order_Item *********************************************//
	//**************************************************************************************************************//
	public static final String INSERT_ORDER_ITEM = "INSERT INTO `km_product_order_item` SET "
													+ "`ordNo`=?,"
													+ "`prodNo`=?,"
													+ "`count`=?,"
													+ "`price`=?,"
													+ "`discount`=?,"
													+ "`point`=?,"
													+ "`delivery`=?,"
													+ "`total`=? ";
	public static final String SELECT_ORDER_ITEMS = "SELECT b.*, c.thumb1, c.prodCate1, c.prodCate2, c.prodName, c.descript "
													+ "FROM `km_product_order` AS a "
													+ "JOIN "
													+ "`km_product_order_item` AS b "
													+ "ON a.ordNo=b.ordNo "
													+ "JOIN "
													+ "`km_product` AS c "
													+ "ON b.prodNo=c.prodNo "
													+ "WHERE a.ordUid=? AND b.ordNo=?";
	public static final String DELETE_BEFORE_ORDER_ITEMS = "DELETE b FROM `km_product_order` as a INNER JOIN `km_product_order_item` AS b "
													+ "ON a.ordNo=b.ordNo "
													+ "WHERE a.ordUid=? "
													+ "AND a.ordComplete=0";												
	
	//**********************************************************************************************************//
	//********************************************* Product_Review *********************************************//
	//**********************************************************************************************************//
	public static final String SELECT_REVIEWS = "SELECT a.*, b.prodName FROM `km_product_review` AS a JOIN `km_product` AS b ON a.`prodNo` = b.`prodNo` WHERE a.`prodNo`=? ORDER BY `rdate` DESC, `revNo` DESC LIMIT ?, 5";
	public static final String SELECT_REVIEW_COUNT = "SELECT COUNT(*) FROM `km_product_review` WHERE `prodNo`=?";
	
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
	
	public final static String INSERT_COMMENT = "INSERT INTO `km_board` SET "
												+ "`group`=?, "
												+ "`parent`=?, "
												+ "`cate1`=?, "
												+ "`cate2`=?, "
												+ "`uid`=?, "
												+ "`title`=?, "
												+ "`content`=?, "
												+ "`rdate`=NOW()";
	
	public static final String UPDATE_BOARD_NOTICE = "UPDATE `km_board` SET "
													+ "`cate1`=?, "
													+ "`title`=?, "
													+ "`content`=? "
													+ "WHERE `no`=?";
	
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
	
	public final static String SELECT_LATESTS = "SELECT * FROM `km_board` "
												+ "WHERE `parent`=0 AND `group`=? "
												+ "ORDER BY `no` DESC "
												+ "LIMIT 0, ?";

	public static final String UPDATE_BOARD = "UPDATE `km_board` SET `cate1`=?, `cate2`=?, `title`=?, `content`=? WHERE `no`=?";
	
	public static final String DELETE_BOARD = "DELETE FROM `km_board` WHERE no=?";
	
	public static final String SELECT_ANSWER = "SELECT * FROM `km_board` WHERE `parent`=?";
	
	public static final String SELECT_BOARD_CATE1_NAME_DISCRIPTION = "SELECT * FROM `km_board_cate1` WHERE `group`=? AND `cate1`=?";

	public static final String SELECT_BOARD_CATE21_NAME = "SELECT * FROM `km_board_cate2` WHERE `cate1`=? AND `cate2`=?";
	
	public static final String SELECT_BOARDS_MAIN_CATE = "SELECT "
															+ "a.*, "
															+ "b.`group_name`, "
															+ "c.`cate1_name`, "
															+ "d.`cate2_name`, "
															+ "COUNT(e.no) AS answer "
															+ "FROM `km_board` AS a "
															+ "LEFT JOIN `km_board_group` AS b ON a.`group` = b.`group` "
															+ "LEFT JOIN `km_board_cate1` AS c ON a.`cate1` = c.`cate1` AND a.`group` = c.`group` "
															+ "LEFT JOIN `km_board_cate2` AS d ON a.`cate2` = d.`cate2` AND a.`cate1` = d.`cate1` "
															+ "LEFT JOIN `km_board` AS e ON a.`no` = e.`parent` "
															+ "WHERE a.`parent`=0 AND a.`group`=? "
															+ "GROUP BY a.`no`, a.`group`, a.`cate1`, a.`cate2`, b.`group_name`, c.`cate1_name`, d.`cate2_name` "
															+ "ORDER BY `no` DESC "
															+ "LIMIT ?, 10";
	
	public static final String SELECT_BOARDS_MIDDLE_CATE = "SELECT "
															+ "a.*, "
															+ "b.`group_name`, "
															+ "c.`cate1_name`, "
															+ "d.`cate2_name`, "
															+ "COUNT(e.no) AS answer "
															+ "FROM `km_board` AS a "
															+ "LEFT JOIN `km_board_group` AS b ON a.`group` = b.`group` "
															+ "LEFT JOIN `km_board_cate1` AS c ON a.`cate1` = c.`cate1` AND a.`group` = c.`group` "
															+ "LEFT JOIN `km_board_cate2` AS d ON a.`cate2` = d.`cate2` AND a.`cate1` = d.`cate1` "
															+ "LEFT JOIN `km_board` AS e ON a.`no` = e.`parent` "
															+ "WHERE a.`parent`=0 AND a.`group`=? AND a.`cate1`=? "
															+ "GROUP BY a.`no`, a.`group`, a.`cate1`, a.`cate2`, b.`group_name`, c.`cate1_name`, d.`cate2_name` "
															+ "ORDER BY `no` DESC "
															+ "LIMIT ?, 10";
	
	public static final String SELECT_BOARDS_MIDDLE_CATE_ALL = "SELECT "
															+ "a.*, "
															+ "b.`group_name`, "
															+ "c.`cate1_name`, "
															+ "d.`cate2_name`, "
															+ "COUNT(e.no) AS answer "
															+ "FROM `km_board` AS a "
															+ "LEFT JOIN `km_board_group` AS b ON a.`group` = b.`group` "
															+ "LEFT JOIN `km_board_cate1` AS c ON a.`cate1` = c.`cate1` AND a.`group` = c.`group` "
															+ "LEFT JOIN `km_board_cate2` AS d ON a.`cate2` = d.`cate2` AND a.`cate1` = d.`cate1` "
															+ "LEFT JOIN `km_board` AS e ON a.`no` = e.`parent` "
															+ "WHERE a.`parent`=0 AND a.`group`=? AND a.`cate1`=? "
															+ "GROUP BY a.`no`, a.`group`, a.`cate1`, a.`cate2`, b.`group_name`, c.`cate1_name`, d.`cate2_name` "
															+ "ORDER BY `no` DESC";
	
	public static final String SELECT_BOARDS_SUB_CATE = "SELECT "
															+ "a.*, "
															+ "b.`group_name`, "
															+ "c.`cate1_name`, "
															+ "d.`cate2_name`, "
															+ "COUNT(e.no) AS answer "
															+ "FROM `km_board` AS a "
															+ "LEFT JOIN `km_board_group` AS b ON a.`group` = b.`group` "
															+ "LEFT JOIN `km_board_cate1` AS c ON a.`cate1` = c.`cate1` AND a.`group` = c.`group` "
															+ "LEFT JOIN `km_board_cate2` AS d ON a.`cate2` = d.`cate2` AND a.`cate1` = d.`cate1` "
															+ "LEFT JOIN `km_board` AS e ON a.`no` = e.`parent` "
															+ "WHERE a.`parent`=0 AND a.`group`=? AND a.`cate1`=? AND a.`cate2`=? "
															+ "GROUP BY a.`no`, a.`group`, a.`cate1`, a.`cate2`, b.`group_name`, c.`cate1_name`, d.`cate2_name` "
															+ "ORDER BY `no` DESC "
															+ "LIMIT ?, 10";

	public static final String SELECT_QNA_PARENT = "SELECT "
													+ "a.*, "
													+ "b.group_name, "
													+ "c.cate1_name, "
													+ "d.cate2_name, "
													+ "COUNT(e.no) AS answer "
													+ "FROM km_board AS a "
													+ "LEFT JOIN km_board_group AS b ON a.group = b.group "
													+ "LEFT JOIN km_board_cate1 AS c ON a.cate1 = c.cate1 AND a.group = c.group "
													+ "LEFT JOIN km_board_cate2 AS d ON a.cate2 = d.cate2 AND a.cate1 = d.cate1 "
													+ "LEFT JOIN km_board AS e ON a.no = e.parent "
													+ "WHERE a.parent=0 AND a.group=? AND a.cate1=? "
													+ "GROUP BY a.no, a.group, a.cate1, a.cate2, b.group_name, c.cate1_name, d.cate2_name "
													+ "ORDER BY no DESC "
													+ "LIMIT ?, 10;";
	
	public static final String SELECT_CATE1_LIST_WHEN_GROUP_CHOOSE = "SELECT * FROM `km_board_cate1` WHERE `group`=?";
	public static final String SELECT_CATE2_LIST_WHEN_CATE1_CHOOSE = "SELECT * FROM `km_board_cate2` WHERE `cate1`=?";

	
	public static final String SELECT_COUNT_MAIN_CATE   = "SELECT COUNT(*) FROM `km_board` WHERE `group`=?";
	public static final String SELECT_COUNT_MIDDLE_CATE = "SELECT COUNT(*) FROM `km_board` WHERE `group`=? AND `cate1`=?";
	public static final String SELECT_COUNT_SUB_CATE    = "SELECT COUNT(*) FROM `km_board` WHERE `group`=? AND `cate1`=? AND `cate2`=?";
	
}