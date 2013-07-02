SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS t_area(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	mapper_id bigint (20) , 
	coords varchar (255) , 
	color varchar (255) , 
	description varchar (255) , 
	PRIMARY KEY (id), 
	KEY mapper_id (mapper_id), 
	CONSTRAINT t_area_mapper_id FOREIGN KEY (mapper_id) REFERENCES t_mapper (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_user(
	id bigint (20)  AUTO_INCREMENT , 
	super_power varchar (255) , 
	account varchar (255) , 
	password varchar (255) , 
	status varchar (255) , 
	last_login_time varchar (255) , 
	last_login_ip varchar (255) , 
	register_time varchar (255) , 
	true_name varchar (255) , 
	email varchar (255) , 
	id_num varchar (255) , 
	fax varchar (255) , 
	addr varchar (255) , 
	office_phone varchar (255) , 
	mobile_num varchar (255) , 
	home_phone varchar (255) , 
	available_period varchar (255) , 
	add_time varchar (255) , 
	modify_time varchar (255) , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_perm_http_method(
	id bigint (20)  AUTO_INCREMENT , 
	perm_id bigint (20) , 
	http_method bigint (20) , 
	PRIMARY KEY (id), 
	KEY perm_id (perm_id), 
	CONSTRAINT t_perm_http_method_perm_id FOREIGN KEY (perm_id) REFERENCES t_permission (id), 
	KEY http_method (http_method), 
	CONSTRAINT t_perm_http_method_http_method FOREIGN KEY (http_method) REFERENCES t_code (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_resource(
	id bigint (20)  AUTO_INCREMENT , 
	uri varchar (255) , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_permission(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	resource_id bigint (20) , 
	type bigint (20) , 
	remark varchar (255) , 
	add_time varchar (255) , 
	modify_time varchar (255) , 
	PRIMARY KEY (id), 
	KEY resource_id (resource_id), 
	CONSTRAINT t_permission_resource_id FOREIGN KEY (resource_id) REFERENCES t_resource (id), 
	KEY type (type), 
	CONSTRAINT t_permission_type FOREIGN KEY (type) REFERENCES t_code (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_mapper(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	description varchar (255) , 
	image_id bigint (20) , 
	width int (8) , 
	height int (8) , 
	PRIMARY KEY (id), 
	KEY image_id (image_id), 
	CONSTRAINT t_mapper_image_id FOREIGN KEY (image_id) REFERENCES t_files (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_notice(
	id bigint (20)  AUTO_INCREMENT , 
	user_id bigint (20) , 
	file_id bigint (20) , 
	pub_time datetime, 
	publishTime varchar (255) , 
	title varchar (255) , 
	content varchar (255) , 
	PRIMARY KEY (id), 
	KEY user_id (user_id), 
	CONSTRAINT t_notice_user_id FOREIGN KEY (user_id) REFERENCES t_user (id), 
	KEY file_id (file_id), 
	CONSTRAINT t_notice_file_id FOREIGN KEY (file_id) REFERENCES t_files (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_article_cate(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	thumb varchar (255) , 
	sort int (8) , 
	parent_id bigint (20) , 
	PRIMARY KEY (id), 
	KEY parent_id (parent_id), 
	CONSTRAINT t_article_cate_parent_id FOREIGN KEY (parent_id) REFERENCES t_article_cate (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_file_cate(
	id bigint (20)  AUTO_INCREMENT , 
	media_type varchar (255) , 
	name varchar (255) , 
	sort int (8) , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_notice_reader(
	id bigint (20)  AUTO_INCREMENT , 
	user_id bigint (20) , 
	notice_id bigint (20) , 
	read_tag int (8) , 
	read_time datetime, 
	PRIMARY KEY (id), 
	KEY user_id (user_id), 
	CONSTRAINT t_notice_reader_user_id FOREIGN KEY (user_id) REFERENCES t_user (id), 
	KEY notice_id (notice_id), 
	CONSTRAINT t_notice_reader_notice_id FOREIGN KEY (notice_id) REFERENCES t_notice (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_department(
	id bigint (20)  AUTO_INCREMENT , 
	code_id bigint (20) , 
	depart_level bigint (20) , 
	depart_cate bigint (20) , 
	add_time varchar (255) , 
	modify_time varchar (255) , 
	PRIMARY KEY (id), 
	KEY code_id (code_id), 
	CONSTRAINT t_department_code_id FOREIGN KEY (code_id) REFERENCES t_code (id), 
	KEY depart_level (depart_level), 
	CONSTRAINT t_department_depart_level FOREIGN KEY (depart_level) REFERENCES t_code (id), 
	KEY depart_cate (depart_cate), 
	CONSTRAINT t_department_depart_cate FOREIGN KEY (depart_cate) REFERENCES t_code (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_files(
	id bigint (20)  AUTO_INCREMENT , 
	save_path varchar (255) , 
	cate_id bigint (20) , 
	display_name varchar (255) , 
	intro varchar (255) , 
	size bigint (20) , 
	PRIMARY KEY (id), 
	KEY cate_id (cate_id), 
	CONSTRAINT t_files_cate_id FOREIGN KEY (cate_id) REFERENCES t_file_cate (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_user_activity_log(
	id bigint (20)  AUTO_INCREMENT , 
	user_id bigint (20) , 
	user_name varchar (255) , 
	user_account varchar (255) , 
	activity varchar (255) , 
	time varchar (255) , 
	result varchar (255) , 
	failure_cause varchar (255) , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_article(
	id bigint (20)  AUTO_INCREMENT , 
	cate_id bigint (20) , 
	title varchar (255) , 
	intro varchar (255) , 
	content varchar (255) , 
	count_view bigint (20) , 
	thumb varchar (255) , 
	gallery_id bigint (20) , 
	PRIMARY KEY (id), 
	KEY cate_id (cate_id), 
	CONSTRAINT t_article_cate_id FOREIGN KEY (cate_id) REFERENCES t_article_cate (id), 
	KEY gallery_id (gallery_id), 
	CONSTRAINT t_article_gallery_id FOREIGN KEY (gallery_id) REFERENCES t_gallery (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_column(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	href varchar (255) , 
	sort int (8) , 
	parent_id bigint (20) , 
	PRIMARY KEY (id), 
	KEY parent_id (parent_id), 
	CONSTRAINT t_column_parent_id FOREIGN KEY (parent_id) REFERENCES t_column (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_nav_menu(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	href varchar (255) , 
	rank int (8) , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_preference(
	id bigint (20)  AUTO_INCREMENT , 
	prop_id varchar (255) , 
	param varchar (255) , 
	value varchar (255) , 
	remark varchar (255) , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_tree_menu(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	target varchar (255) , 
	rel varchar (255) , 
	reload_flag varchar (255) , 
	external varchar (255) , 
	width int (8) , 
	height int (8) , 
	href varchar (255) , 
	rank int (8) , 
	pid bigint (20) , 
	nav_menu_id bigint (20) , 
	PRIMARY KEY (id), 
	KEY pid (pid), 
	CONSTRAINT t_tree_menu_pid FOREIGN KEY (pid) REFERENCES t_tree_menu (id), 
	KEY nav_menu_id (nav_menu_id), 
	CONSTRAINT t_tree_menu_nav_menu_id FOREIGN KEY (nav_menu_id) REFERENCES t_nav_menu (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_ad(
	id bigint (20)  AUTO_INCREMENT , 
	title varchar (255) , 
	intro varchar (255) , 
	href varchar (255) , 
	image_id bigint (20) , 
	sort int (8) , 
	open_type varchar (255) , 
	PRIMARY KEY (id), 
	KEY image_id (image_id), 
	CONSTRAINT t_ad_image_id FOREIGN KEY (image_id) REFERENCES t_files (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_gallery(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	intro varchar (255) , 
	thumb varchar (255) , 
	width int (8) , 
	height int (8) , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_role(
	id bigint (20)  AUTO_INCREMENT , 
	name varchar (255) , 
	description varchar (255) , 
	add_time varchar (255) , 
	modify_time varchar (255) , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_code(
	id bigint (20)  AUTO_INCREMENT , 
	code_value varchar (255) , 
	remark varchar (255) , 
	parent_id bigint (20) , 
	type_id bigint (20) , 
	add_time varchar (255) , 
	modify_time varchar (255) , 
	PRIMARY KEY (id), 
	KEY parent_id (parent_id), 
	CONSTRAINT t_code_parent_id FOREIGN KEY (parent_id) REFERENCES t_code (id), 
	KEY type_id (type_id), 
	CONSTRAINT t_code_type_id FOREIGN KEY (type_id) REFERENCES t_code (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_setting(
	id bigint (20)  AUTO_INCREMENT , 
	user_default_role bigint (20) , 
	user_perm_control varchar (255) , 
	file_base_dir varchar (255) , 
	PRIMARY KEY (id), 
	KEY user_default_role (user_default_role), 
	CONSTRAINT t_setting_user_default_role FOREIGN KEY (user_default_role) REFERENCES t_role (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_user_role(
t_user_role 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_user_department(
t_user_department 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_perm_http_method(
t_perm_http_method 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_gallery_files_index(
t_gallery_files_index 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_role_permission(
t_role_permission 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_role_menu(
t_role_menu 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS t_role_navmenu(
t_role_navmenu 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
