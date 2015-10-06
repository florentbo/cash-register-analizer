INSERT INTO `items`(`id`,`item_name`,`item_kort`,`newid`,`vervangen`,`category_id`,`popup`,`printer`,`openprijs`,`kleur`,`tax_percent`,`tax_percent_tafel`,`total_cost`,`quantity`,`reorder_level`,`image`,`barcode`,`webshow`,`onlinestart`,`mustsub`,`row`,`col`,`page`,`deleted`) VALUES
  (1,'Saumon fumé','Saumon fumé',NULL,0,0,0,NULL,1,'ItemsBGGroen',1.06,1.12,13.00,0,NULL,'images/items/item.gif','',1,0,0,0,0,0,0),
  (2,'Assortiment de fromages','Assortiment de fromages',NULL,0,0,0,NULL,0,'ItemsBGGroen',1.06,1.12,12.00,0,NULL,'images/items/item.gif','',1,0,0,0,0,0,0),
  (3,'Coca','Coca',NULL,0,5,0,NULL,0,'ItemsBGGroen',1.21,1.21,2.50,0,NULL,'images/items/item.gif','',1,0,0,0,0,0,0);

INSERT INTO `kassaorders`(`serial`,`customerid`,`kassaid`,`btw`,`bruto`,`whhg`,`soort`,`uur`,`bancontact`,`ordertijd`,`terugname`,`loginlogout`,`proforma`,`eindproforma`,`cacheid`,`btwticket`,`tafelverkoop`,`teruggenomen`,`login`,`logout`,`factuur`,`factuurid`,`delityorder`,`delityorderid`,`bcid`,`overschrijving`,`losseterugname`,`levertype`,`onlineorderid`) VALUES
  (1,0,1,0,13.00,1,1,'12:59',0,'2015-06-26 11:59:08',0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0),
  (2,0,1,0,13.00,1,1,'12:59',0,'2015-06-26 11:59:50',0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0),
  (3,0,1,0,13.00,1,1,'13:01',0,'2015-06-26 12:01:18',0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0);

INSERT INTO `kassaorder_detail`(`id`,`orderid`,`productid`,`productprijs`,`productbtw`,`sub1`,`sub1prijs`,`sub1btw`,`sub2`,`sub2prijs`,`sub2btw`,`sub3`,`sub3prijs`,`sub3btw`,`sub4`,`sub4prijs`,`sub4btw`,`sub5`,`sub5prijs`,`sub5btw`,`sub6`,`sub6prijs`,`sub6btw`,`sub7`,`sub7prijs`,`sub7btw`,`sub8`,`sub8prijs`,`sub8btw`,`sub9`,`sub9prijs`,`sub9btw`,`sub10`,`sub10prijs`,`sub10btw`,`price`,`category_id`,`aantal`,`terugname`,`takeout`,`bericht`) VALUES
  (1,1,1,13.00,1.06,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,13.00,1,1,0,1,NULL),
  (2,2,1,13.00,1.06,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,13.00,1,1,0,1,NULL),
  (3,3,1,13.00,1.06,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,NULL,0.00,0,13.00,1,1,0,1,NULL);