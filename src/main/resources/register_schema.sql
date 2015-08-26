DROP TABLE IF EXISTS `items`;
CREATE TABLE IF NOT EXISTS `items` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) CHARACTER SET utf8 NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `kassaorders`;
CREATE TABLE IF NOT EXISTS `kassaorders` (
  `serial` int(11) NOT NULL AUTO_INCREMENT,
  `ordertijd` timestamp NULL,
  PRIMARY KEY (`serial`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

DROP TABLE IF EXISTS `kassaorder_detail`;
CREATE TABLE IF NOT EXISTS `kassaorder_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NULL,
  `productid` int(11) NULL,
  `aantal` int(11) NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `orderid` (`orderid`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
