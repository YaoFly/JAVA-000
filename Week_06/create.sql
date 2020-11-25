CREATE TABLE `t_user`(
                       `id` VARCHAR(64) not null COMMENT '用户ID',
                       `code` VARCHAR(64) not null COMMENT '用户CODE',
                       `name` VARCHAR(64) not null COMMENT '用户名',
                       PRIMARY KEY (`id`) USING BTREE,
                       KEY `index_id`(`id`) USING BTREE,
                       KEY `index_code`(`code`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

CREATE TABLE `t_goods`(
                        `id` VARCHAR(64) not null COMMENT '商品ID',
                        `code` VARCHAR(64) not null COMMENT '商品CODE',
                        `name` VARCHAR(64) not null COMMENT '商品名',
                        price NUMERIC(7,2) not null COMMENT '商品价格',
                        PRIMARY KEY (`id`) USING BTREE,
                        KEY `index_id`(`id`) USING BTREE,
                        KEY `index_code`(`code`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

CREATE TABLE `t_order`(
                        `id` VARCHAR(64) not null COMMENT '订单ID',
                        `code` VARCHAR(64) not null COMMENT '订单CODE',
                        `price` NUMERIC(7,2) not null COMMENT '订单总价格',
                        PRIMARY KEY (`id`) USING BTREE,
                        KEY `index_id`(`id`) USING BTREE,
                        KEY `index_code`(`code`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='订单表';

CREATE TABLE `t_order_item`(
                             `id` VARCHAR(64) not null COMMENT '关联表ID',
                             `order_code` VARCHAR(64) not null COMMENT '订单CODE',
                             `goods_code` VARCHAR(64) not null COMMENT '商品CODE',
                             `goods_count` INT(8) NOT NULL COMMENT '商品数量',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `index_id`(`id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='订单-商品关联表';