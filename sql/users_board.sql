--board 시퀸스 삭제 
drop sequence seq_board_no;
--users 시퀸스 삭제 
drop sequence seq_users_id;

--board 테이블 삭제
drop table board;
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

--board 테이블 생성
create table board(
	no number,
	title varchar2(500) not null, 
	content varchar2(4000), 
	hit number default 0,
	reg_date date not null,
	user_no number not null,
	primary key(no),
	constraint board_fk foreign key(user_no)
	references users(no)
);


--users 시퀸스 생성
create sequence seq_users_id
increment by 1
start with 1
nocache;

--시퀸스 생성
create sequence seq_board_no
increment by 1
start with 1
nocache;
--users 테이블 데이터 입력
insert into users
values(seq_users_id.nextval,'wjdwo1234', '1234', '이정재', 'male');

insert into users
values(seq_users_id.nextval,'wotjr2345', '2345', '유재석', 'male');

insert into users
values(seq_users_id.nextval,'tjsgml5555', '5555', '이선희', 'female');

insert into users
values(seq_users_id.nextval,'wotjr', '1234', '유재석', 'male');

select*
from users
order by no asc;

--값 입력
insert into board(no,title,content,reg_date,user_no)
values (seq_board_no.nextval, '신세계','안녕하세요',sysdate,1);

insert into board(no,title,content,reg_date,user_no)
values (seq_board_no.nextval, '놀면뭐하니','압구정날라리',sysdate,2);

insert into board(no,title,content,reg_date,user_no)
values (seq_board_no.nextval, '인연','인연이라고 하죠',sysdate,3);

insert into board(no,title,content,reg_date,user_no)
values (seq_board_no.nextval, '무한도전','안녕하세요 감사해요 잘 있어요',sysdate,2);

select *
from board;

select *
from users u,board b
where b.user_no=u.no;

commit;

rollback;

delete board
where no = 10;

select 	rownum, 
        b.no, 
        b.title, 
        b.content, 
        b.hit, 
        to_char(b.reg_date,'yy-mm-dd hh:mi') regDate, 
        s.name, 
        b.user_no 
from users s 
left outer join board b on b.user_no = s.no 
where not b.no is null 
order by b.no desc;

select  ort.rn, 
        ort.no, 
        ort.title, 
        ort.content, 
        ort.hit, 
        ort.regDate, 
        ort.userNo, 
        ort.userName 
from(select rownum rn, 
            ot.no, 
            ot.title, 
            ot.content, 
            ot.hit, 
            ot.regDate, 
            ot.userNo, 
            ot.userName 
        from (select b.no, 
                b.title, 
                b.content, 
                b.hit, 
                to_char(b.reg_date,'yy-mm-dd hh:mi') regDate, 
                s.name userName, 
                b.user_no userNo 
        from users s 
        left outer join board b on b.user_no = s.no 
        where not b.no is null 
        order by b.no desc)ot
        )ort
where rn >= 1 
and rn <= 10;




        
select  b.no,
        b.title,
        b.content,
        b.hit,
        to_char(b.reg_date, 'yy-mm-dd hh:mi'),
        s.name
from users s
left outer join board b on b.user_no = s.no
where b.title like '%안%';
