INSERT INTO `items`(`item_name`) VALUES ('Saumon fumé'), ('Assortiment de fromages'), ('Coca');

INSERT INTO `kassaorders`(`ordertijd`) VALUES ('2015-06-23 11:59:08'), ('2015-06-26 11:59:08'), ('2015-06-26 11:59:50');

INSERT INTO `kassaorder_detail`(`orderid`, `productid`,`aantal`) VALUES
  (1,1,1),
  (1,2,1),
  (1,3,2),
  (2,1,1),
  (3,3,2),
  (3,1,1);

