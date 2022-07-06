--테이블 삭제
drop table files;

--시퀸스 삭제
drop sequence seq_files_no;

-- 테이블 생성
create table files(
	no number(20), 
	orgName varchar2(100), 
	saveName varchar2(100), 
	filePath varchar2(100), 
	fileSize number(20), 
	primary key(no)
);

--시퀸스 생성
create sequence seq_files_no
increment by 1 
start with 1 
nocache;

--테이블 값 입력
insert into files values(seq_files_no.nextval,'원래이름', '저장이름', '저장경로', 2);

select  *
from files
order by no asc;

commit;

rollback;