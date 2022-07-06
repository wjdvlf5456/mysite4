--작가와 책 테이블지우기
drop table book;

drop table author;

--시퀸스 삭제
drop SEQUENCE seq_author_id;

drop SEQUENCE seq_book_id;

--작가 테이블 생성
create table author(
	author_id number(10),
	author_name varchar2(100) not null,
	author_desc varchar2(100),
	primary key (author_id)
);

--책 테이블 생성
create table book (
	book_id number(10),
	title varchar2(100) not null,
	pubs varchar2(100),
	pub_date date,
	author_id number(10),
	primary key (book_id),
	constraint book_fk foreign key (author_id)
	references author(author_id)
);


--작가 시퀸스 생성
create sequence seq_author_id
increment by 1
start with 1
nocache;

--책 시퀸스 생성
create sequence seq_book_id
increment by 1
start with 1
nocache;

--insert into 작성시 한 줄씩 띄어주지 않으면 오류 발생
--작가 테이블 데이터생성
insert into author 
values(seq_author_id.nextval, '김문열', '경북 영양');

insert into author 
values(seq_author_id.nextval, '박경리', '경상남도 통영');

insert into author 
values(seq_author_id.nextval, '유시민', '17대 국회의원');

insert into author 
values(seq_author_id.nextval, '기안84', '기안동에서 산 84년생');

insert into author 
values(seq_author_id.nextval, '강풀', '온라인 만화가 1세대');

insert into author 
values(seq_author_id.nextval, '김영하', '알쓸신잡');


--책 테이블 데이터생성
insert into book 
values (seq_book_id.nextval, '우리들의 일그러진 영웅', '다림', '1998-02-22', 1);

insert into book 
values (seq_book_id.nextval, '삼국지', '민음사', '2002-03-01', 1);

insert into book 
values (seq_book_id.nextval, '토지', '마로니에북스', '2012-08-15', 2);

insert into book 
values (seq_book_id.nextval, '유시민의 글쓰기 특강', '생각의 길', '2015-04-01', 3);

insert into book 
values (seq_book_id.nextval, '패션왕', '중앙북스(books)', '2012-02-22', 4);

insert into book 
values (seq_book_id.nextval, '순정만화', '재미주의', '2011-08-03', 5);

insert into book 
values (seq_book_id.nextval, '오직두사람', '문학동네', '2017-05-04', 6);

insert into book 
values (seq_book_id.nextval, '26년', '재미주의', '2012-02-04', 5);

select*
from author;

select *
from book;

select  b.book_id ,
        b.title,
        b.pubs,
        b.pub_date,
        a.author_id,
        a.author_name,
        a.author_desc
from author a
left outer join book b on b.author_id= a.author_id;

update author
    set author_desc = '서울특별시'
    where author_name = '강풀';

--book테이블에 있는 값들은 따로 작가id를 지정해놓아서 아래명령어로 삭제 할 수 없다.    
delete from author
where author_name = '기안84';



commit;

rollback;