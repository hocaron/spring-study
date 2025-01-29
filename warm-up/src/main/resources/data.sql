-- Categories
INSERT INTO categories (id, name, description, depth_level, sort_order)
VALUES (1, '전자제품', '전자제품 카테고리입니다', 1, 1),
       (2, '의류', '의류 카테고리입니다', 1, 2),
       (3, '식품', '식품 카테고리입니다', 1, 3);

-- Products
INSERT INTO products (id, name, description, price, stock_quantity, category_id, created_at, is_active)
VALUES (1, '노트북', '고성능 노트북', 1000000, 100, 1, CURRENT_TIMESTAMP, true),
       (2, '스마트폰', '최신형 스마트폰', 500000, 200, 1, CURRENT_TIMESTAMP, true),
       (3, '셔츠', '브랜드 셔츠', 50000, 300, 2, CURRENT_TIMESTAMP, true),
       (4, '바지', '청바지', 60000, 200, 2, CURRENT_TIMESTAMP, true),
       (5, '과자', '과자 세트', 1500, 1000, 3, CURRENT_TIMESTAMP, true);

-- Members
INSERT INTO members (id, name, email, street, city, state, zip_code, created_at, updated_at)
VALUES (1, '홍길동', 'hong@test.com', '강남대로', '서울', '서울특별시', '12345', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, '김철수', 'kim@test.com', '서초대로', '서울', '서울특별시', '12345', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, '이영희', 'lee@test.com', '해운대로', '부산', '부산광역시', '54321', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Orders
INSERT INTO orders (id, member_id, status, order_date, total_amount, version)
VALUES (1, 1, 'PAID', CURRENT_TIMESTAMP, 2000000, 0),
       (2, 1, 'DELIVERED', DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 1 DAY), 150000, 0),
       (3, 2, 'PAID', DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 2 HOUR), 127500, 0);

-- Order Items
INSERT INTO order_items (id, order_id, product_id, quantity, order_price, total_price)
VALUES (1, 1, 1, 1, 1000000, 1000000), -- 홍길동 주문1: 노트북 1개
       (2, 1, 2, 2, 500000, 1000000),  -- 홍길동 주문1: 스마트폰 2개
       (3, 2, 3, 3, 50000, 150000),    -- 홍길동 주문2: 셔츠 3개
       (4, 3, 4, 2, 60000, 120000),    -- 김철수 주문: 바지 2개
       (5, 3, 5, 5, 1500, 7500); -- 김철수 주문: 과자 5개
