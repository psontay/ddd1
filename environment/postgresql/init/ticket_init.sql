create database ddd1;
\c ddd1;

-- 1. ticket table
CREATE TABLE IF NOT EXISTS ticket (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(50) NOT NULL,
    description TEXT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status INT NOT NULL DEFAULT 0,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- 2. ticket detail (item) table
CREATE TABLE IF NOT EXISTS ticket_item (
                                           id BIGSERIAL PRIMARY KEY,
                                           name VARCHAR(50) NOT NULL,
    description TEXT,
    stock_initial INT NOT NULL DEFAULT 0,
    stock_available INT NOT NULL DEFAULT 0,
    is_stock_prepared BOOLEAN NOT NULL DEFAULT FALSE,
    price_original BIGINT NOT NULL,
    price_flash BIGINT NOT NULL,
    sale_start_time TIMESTAMP NOT NULL,
    sale_end_time TIMESTAMP NOT NULL,
    status INT NOT NULL DEFAULT 0,
    activity_id BIGINT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- INSERT MOCK DATA
-- Insert data into `ticket` table
INSERT INTO ticket (name, description, start_time, end_time, status)
VALUES
    ('Đợt Mở Bán Vé Ngày 12/12', 'Sự kiện mở bán vé đặc biệt cho ngày 12/12', '2024-12-12 00:00:00', '2024-12-12 23:59:59', 1),
    ('Đợt Mở Bán Vé Ngày 01/01', 'Sự kiện mở bán vé cho ngày đầu năm mới 01/01', '2025-01-01 00:00:00', '2025-01-01 23:59:59', 1);

INSERT INTO ticket_item (name, description, stock_initial, stock_available, is_stock_prepared, price_original, price_flash, sale_start_time, sale_end_time, status, activity_id)
VALUES
    ('Vé Sự Kiện 12/12 - Hạng Phổ Thông', 'Vé phổ thông cho sự kiện ngày 12/12', 1000, 1000, FALSE, 100000, 10000, '2024-12-12 00:00:00', '2024-12-12 23:59:59', 1, 1),
    ('Vé Sự Kiện 12/12 - Hạng VIP', 'Vé VIP cho sự kiện ngày 12/12', 500, 500, FALSE, 200000, 15000, '2024-12-12 00:00:00', '2024-12-12 23:59:59', 1, 1),
    ('Vé Sự Kiện 01/01 - Hạng Phổ Thông', 'Vé phổ thông cho sự kiện ngày 01/01', 2000, 2000, FALSE, 100000, 10000, '2025-01-01 00:00:00', '2025-01-01 23:59:59', 1, 2),
    ('Vé Sự Kiện 01/01 - Hạng VIP', 'Vé VIP cho sự kiện ngày 01/01', 1000, 1000, FALSE, 200000, 15000, '2025-01-01 00:00:00', '2025-01-01 23:59:59', 1, 2);


