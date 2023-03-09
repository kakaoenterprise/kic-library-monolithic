
INSERT INTO book (book_Id, name, publish_Date, company, writer, status, quantity, category, recommended, image_Url)
VALUES
    ('COM.01.001', 'DOMAIN DRIVEN DESIGN',  NOW(), '위키북스',       '에릭에반스',              '최초등록', 5, '컴퓨터', true, '/images/DDD.jpeg')
   ,('COM.01.002', 'Doing Agile Right',     NOW(), 'Harvard Biz', 'Drarrell Rigby 외 2',   '최초등록', 5, '컴퓨터', true, '/images/Agile.jpeg')
   ,('COM.01.003', 'Cloud Native',          NOW(), 'OREILLY',     'Boris Scholl 외 2',     '최초등록', 5, '컴퓨터', true, '/images/cloudNative.jpeg')
   ,('COM.01.004', 'Event Storming',        NOW(), 'leanpub',      'Alberto Brandolini',  '최초등록', 5, '컴퓨터', true, '/images/eventStorming.jpg')
   ,('HUM.01.001', '슬기로운KEP생활',           NOW(), 'KEP',          'andrew',              '최초등록', 5, '인문',   true, '/images/kep_life.jpg')
   ,('HUM.01.002', '라이언, 내 곁에 있어줘',      NOW(), 'arte',         '전승환',                '최초등록', 5, '컴퓨터', true, '/images/rian_sideme.webp');


INSERT INTO category (id, name)
VALUES
    (10000001, '컴퓨터')
   ,(20000001, '과학')
   ,(30000001, '인문')
   ,(40000001, '만화');

INSERT INTO manager (librarian_id, name, gender, birth_day, address, phone, role, password)
 VALUES
    ('manager01',   '김매니저', '여', '2001-01-12', '한국-어디', '010-4567-7891', 'MANAGER', '1234')
   ,('user01',      '박사용자', '여', '2010-02-21', '한국-어디', '010-5678-9012', 'USER',    '1234')
   ,('user02',      '석사용자', '남', '2011-10-06', '한국-어디', '010-6789-0123', 'USER',    '1234');

