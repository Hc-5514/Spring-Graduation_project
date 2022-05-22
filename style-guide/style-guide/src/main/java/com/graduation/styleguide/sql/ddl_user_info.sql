CREATE TABLE `user_info` (
	`code` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '인덱스',
	`id` VARCHAR(20) NOT NULL COMMENT '아이디' COLLATE 'utf8mb3_general_ci',
	`password` VARCHAR(255) NOT NULL COMMENT '비밀번호' COLLATE 'utf8mb3_general_ci',
	`name` VARCHAR(20) NULL DEFAULT NULL COMMENT '이름' COLLATE 'utf8mb3_general_ci',
	`auth` VARCHAR(50) NULL DEFAULT NULL COMMENT '권한 목록' COLLATE 'utf8mb3_general_ci',
	`email` VARCHAR(50) NULL DEFAULT NULL COMMENT '이메일' COLLATE 'utf8mb3_general_ci',
	`contact` VARCHAR(20) NULL DEFAULT NULL COMMENT '전화번호' COLLATE 'utf8mb3_general_ci',
	`address` VARCHAR(100) NULL DEFAULT NULL COMMENT '주소' COLLATE 'utf8mb3_general_ci',
	`business_number` VARCHAR(20) NULL DEFAULT NULL COMMENT '사업자등록번호' COLLATE 'utf8mb3_general_ci',
	`ceo` VARCHAR(20) NULL DEFAULT NULL COMMENT '대표자명' COLLATE 'utf8mb3_general_ci',
	`business_name` VARCHAR(20) NULL DEFAULT NULL COMMENT '상호(단체명)' COLLATE 'utf8mb3_general_ci',
	`business_contact` VARCHAR(20) NULL DEFAULT NULL COMMENT '전화번호' COLLATE 'utf8mb3_general_ci',
	`business_address` VARCHAR(100) NULL DEFAULT NULL COMMENT '사업장 소재지' COLLATE 'utf8mb3_general_ci',
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `code` (`code`) USING BTREE
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=49
;
