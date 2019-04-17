CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `role` varchar(255),
  `name` varchar(255),
  `mobile_phone_number` char(11),
  `password` varchar(32),
  `email` varchar(255),
  `motto` varchar(255),
  `profile` varchar(255),
  `gender` varchar(255),
  `follow` int(11),
  `follower` int(11),
  `favorite` int(11),
  `book` int(3),
  `avatar` varchar(255),
  `portrait` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `role` varchar(255),
  `code` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `name` varchar(255),
  `style` varchar(255),
  `type` varchar(255),
  `description` varchar(255),
  `cover` varchar(255),
  `page` int(3),
  `favor` int(11),
  `publish` boolean,
  `author_id` int(11),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `name` varchar(255),
  `image` varchar(255),
  `book_id` int(11),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `author_id` int(11),
  `user_id` int(11),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `book_id` int(11),
  `user_id` int(11),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `book_id` int(11),
  `question` varchar(255),
  `options` varchar(255),
  `single` boolean,
  `varchar` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` char(32),
  `status` int(1),
  `create_at` datetime,
  `update_at` datetime,
  `book_id` int(11),
  `user_id` int(11),
  `ratio` decimal(4, 2),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;