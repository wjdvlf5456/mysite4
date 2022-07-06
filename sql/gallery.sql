drop table gallery;

drop sequence seq_gallery_no;

create table gallery(
	no number, 
	user_no number, 
	content varchar2(1000), 
	file_path varchar2(500), 
	org_name varchar2(200), 
	save_name varchar2(500), 
	file_size number, 
	primary key(no), 
	constraint gallery_fk foreign key(user_no)
	references users(no)
);

create sequence seq_gallery_no
increment by 1
start with 1
nocache;



select g.no, 
		g.user_no, 
		g.content, 
		g.file_path, 
		g.org_name, 
		g.save_name, 
		g.file_size, 
        u.name 
from gallery g left outer join users u on g.user_no = u.no
where not g.no is null 
order by g.no asc;

commit;

rollback;
