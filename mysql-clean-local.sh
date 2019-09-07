#!/usr/bin/env bash
echo "Clean local docker MySQL..."

mysql --protocol=tcp -P 13306 -u root -proot -e "DROP DATABASE IF EXISTS ecommerce_order_mysql;CREATE DATABASE IF NOT EXISTS ecommerce_order_mysql DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;"