--rboard 시퀸스 삭제 
drop sequence seq_rboard_no;
--users 시퀸스 삭제 
drop sequence seq_users_id;

--rboard 테이블 삭제
drop table rboard;
--users 테이블 삭제
drop table users;


--users 테이블 생성
create table users(
	no number,
	id varchar2(20) unique not null,
	password varchar2(20) not null,
	name varchar2(20),
	gender varchar2(10),
	primary key(no)
);
--rboard 테이블 생성
create table rboard(
	no number, 
	user_no number not null, 
	title varchar2(500) not null, 
	content varchar2(4000), 
	hit number default 0,
	reg_date date,
	group_no number,
	order_no number,
	depth number,
	primary key(no),
	constraint rboard_fk foreign key(user_no)
	references users(no)
);

--users 시퀸스 생성
create sequence seq_users_id
increment by 1
start with 1
nocache;

--rboard시퀸스 생성
create sequence seq_rboard_no
increment by 1
start with 1
nocache;

--users 테이블 데이터 입력
insert into users
values(seq_users_id.nextval,'wjdwo1234', '1234', '이정재', 'male');

insert into users
values(seq_users_id.nextval,'tjsgml5555', '5555', '이선희', 'female');

insert into users
values(seq_users_id.nextval,'wotjr2345', '2345', '유재석', 'male');

insert into users
values(seq_users_id.nextval,'dngml1111', '1111', '천우희', 'female');


--rboard 테이블 데이터 입력
insert into rboard(no,user_no,title,content,reg_date,group_no,order_no,depth)
values (seq_rboard_no.nextval, 1, '신세계','안녕하세요',sysdate,1,1,0);

insert into rboard(no,user_no,title,content,reg_date,group_no,order_no,depth)
values (seq_rboard_no.nextval,2, '놀면뭐하니','압구정날라리',sysdate,2,1,0);

insert into rboard(no,user_no,title,content,reg_date,group_no,order_no,depth)
values (seq_rboard_no.nextval, 1,'인연','인연이라고 하죠',sysdate,3,1,0);

insert into rboard(no,user_no,title,content,reg_date,group_no,order_no,depth)
values (seq_rboard_no.nextval, 4,'무한도전','안녕하세요 감사해요 잘 있어요',sysdate,4,1,0);


select *
from users;

select *
from rboard;


select u.no, 
		u.id, 
		u.password, 
		u.name, 
		u.gender, 
		r.no, 
		r.user_no, 
		r.title, 
		r.hit, 
        to_char(r.reg_date,'yy-mm-dd hh:mi') regDate, 
		r.group_no, 
		r.order_no, 
		r.depth 
from users u left outer join rboard r on r.user_no=u.no
where not r.no is null 
order by r.no asc;


select no
from(
    select *
    from rboard
    order by rownum desc
)
where rownum = 1;

commit;