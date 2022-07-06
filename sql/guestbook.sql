--테이블 삭제
drop table guestbook;

--시퀸스 삭제 
drop sequence seq_guestbook_no;

--테이블 생성
create table guestbook(
	no number, 
	name varchar2(80), 
	password varchar2(20), 
	content varchar2(2000),
	reg_date date,
	primary key(no)
);

--시퀸스 생성
create sequence seq_guestbook_no
increment by 1
start with 1
nocache;

--값 입력
insert into guestbook 
values (seq_guestbook_no.nextval, '이정재', '1234', '안녕하세요', sysdate);

insert into guestbook 
values (seq_guestbook_no.nextval, '천우희', '1234', '안녕', sysdate);

insert into guestbook 
values (seq_guestbook_no.nextval, '유재석', '3421', '안녕하시오', sysdate);

insert into guestbook 
values (seq_guestbook_no.nextval, '한소희', '4321', '잘 가세요', sysdate);

select *
from guestbook;

commit;

rollback;