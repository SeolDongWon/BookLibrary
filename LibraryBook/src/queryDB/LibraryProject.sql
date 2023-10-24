--================================================��������
--���� ���̺� ��������
drop table book;
drop sequence book_no_seq;

create table book(
no number not null, -- �Ϸù�ȣ
ISBN varchar2(20) not null, -- ����ǥ�ص�����ȣ 
bookTitle varchar2(100) not null, -- å ����
bookAuthor varchar2(50) not null, -- �۰�
bookRelease date not null, -- å ������
bookIntro varchar2(1000), -- å�Ұ�
CONSTRAINT book_isbn_uk unique (isbn), -- ����Ű
constraint book_no_pk primary key (no) -- �⺻Ű
);
--���� ������ �Ϸù�ȣ
create sequence book_no_seq start with 1 increment by 1;

select * from book order by no;
--���� ���ڵ� ��������
--insert into book values (book_no_seq.nextval,ISBN,title,bookauthor,bookrelease,bookintro);
insert into book values (book_no_seq.nextval, 9788960883727,'���õ� �����ڰ� �� �ȴٰ� ���ߴ�','����ö,�����','2021/03/25','�������ϱ� �Ⱦ �� �ȴٰ� ���ϴ� �� �ƴϴ١� ���� IT �����ڵ��� �� �ȴٰ� ���ϴ� �����ڷ� ���� ������ ������� �޴´�. �츮�� IT �������ڷμ� ������ ���ϱ� ���� �������� ���忡�� ���� �����ϰ� �ư�, �� ������ ���� �������� �� �ȴٴ� ���� ��� �ִ� ���� ���� �ǹ̸� ���޾Ҵ�. �� å���� �츮�� ���� �������� �߰��� ���� ���Ͽ���� ��Ƴ´�. ���� �� ���� ���� �� ���� �˰� �־�� �� �����鿡 ���� �˾ƺ���.');
insert into book values (book_no_seq.nextval, 9788979149418, '���������ϱ� ��ư� �ڵ��ϴ� ���','�ο��� �׸�', '2012/07/16','������ ���� ������ �� �ִ� �ڵ常 �ۼ��Ѵٸ� �ڽ��� ��ġ�� ���� �� ������? ����� ����� �����ϴµ�, ������ �ڵ带 �����ϱ� ��ƴٸ�? �Ƹ��� �ֺ� ������� ���� ���α׷��� ���������ϱ� ���ؼ� ��Ÿ��� ã�� �� �� �̴�. �� å�� ������ �ο��� �׸��� �̷� �ǹ��� �������� ���������ϱ� ��ư� �ۼ��ϴ� ����鿡 ���ؼ� ����Ͽ���. ���ڰ� �����ϴ� ������� �ϳ��ϳ� ���� �ϴ� ����, �ڽ��� ��ġ�� ���� �� �ִ� �ڵ� ����� ��� �� ���� ���̴�.');
insert into book values (book_no_seq.nextval, 9791137227279,'������ ������, SI���� ��Ƴ���','������','2020/12/28','���ѹα� �� �������� �� 70% ������ SI���� ���մϴ�. ������ �����ڷμ��� �̾߱⸦ �����ϴ�. ���ߵ� �����ϰ� ������ � �����ϴ�, ����� ������ �ٸ� �ͺ��� ���� �� ���ϴ� ���� �������ν��� �����ڷμ��� ���� ������ ���ϴ�. �������� �ؾ� �ϰ� �������� �ϰ� �ִ�, �׸��� �������� �ϰ� �;� �ϴ� ������ ���� �̾߱��մϴ�. �� ���� �а� ���� ������ ������ ���ǲ�� ���۸� �ߴ� SI�� ���ؼ� ������ �� �� ������ �� �ֽ��ϴ�.');
insert into book values (book_no_seq.nextval, 9788997924820,'���� ������ �ڵ��ϸ� ��� ��','ȫ����','2021/04/12','�ڷγ�19�� �������� ������ ����� ���������� �����ߴ�. �����δ� ������ ���� ������ �������� ���� ������ ���� ���� �ڷγ�19 �������� ���� �� ���� ������ �þ ���̴�. ���� ��� �ڵ��� ������ ȯ���� �Ǿ�� �ִ�. ������ �������� �󸶳� �غ�Ǿ� �ֳ���? �ڵ����� �ܻ��� ���� ����� ġŲ���� ����Ÿ��⺸��, ''���� ������ �ڵ�''�ϸ� ��� ���� �´�. �츮�� �ڵ� �ӿ��� ������ �ູ�� �����غ��ұ� �����̴�. �� å�� "���� ������ �ڵ��ϱ�", �� ����� ã�ư��� �̾߱��.');



--===============================================���� ����
--���� ���̺� ��������
drop table Library;
drop sequence Library_no_seq;

create table Library(
no number not null,
isbn not null, -- book���� �������� �ܷ�Ű
serial varchar2(20) not null, --isbn+�Ϸù�ȣ, �⺻Ű
callNum varchar2(30) not null, -- û����ȣ, å ��ġ
bookLocation varchar2(20) not null, --å ��ġ
borrowState varchar2(20), -- ���� ����
borrowMemid, -- ����ȸ��
returnDate date, -- �ݳ���
reserveState varchar2(20), -- ���� ����
reserveMemid, -- ����ȸ��
constraint Library_serial_pk primary key(serial),
constraint Library_isbn_fk foreign key(isbn) references book(isbn),
constraint Library_borrowMemid_fk foreign key(borrowMemid) references member(memid),
constraint Library_reserveMemid_fk foreign key(reserveMemid) references member(memid),
constraint Library_no_uk unique(no), -- ����Ű
constraint Library_callNum_uk unique(callNum) -- ����Ű
);
create sequence Library_no_seq start with 1 increment by 1;

select * from member;
select * from Library;
select * from library join book using(isbn);
select * from book;
--insert into Library values(bookcase_no_seq.nextval,isbn, 'serial','callNum','bookLocation','borrowState','memid','returnDate','reserveState');
insert into Library values(Library_no_seq.nextval,9788960883727, '978896088372701','004.76-��76��','3��','','','','','');
insert into Library values(Library_no_seq.nextval,9788960883727, '978896088372702','004.76-��76��=2','3��','','','','','');
insert into Library values(Library_no_seq.nextval,9788979149418, '978897914941801','005.����','2��','','','','','');
insert into Library values(Library_no_seq.nextval,9788997924820, '978899792482001','005.104-ȫ74��','2��','','','','','');
insert into Library values(Library_no_seq.nextval,9791137227279, '979113722727901','005.����','2��','','','','','');

select * from Library where serial = 978896088372702 AND memid is null;

--=====================================ȸ��
--���� ���̺� ȸ��
drop table member;
drop sequence member_no_seq;

create table member(
no number not null,
memid varchar2(20) not null,
mempw varchar2(20) not null,
memName varchar2(30) not null,
memPhone varchar2(30) not null,
constraint member_memid_pk primary key(memid),
constraint member_no_uk unique(no) -- ����Ű
);
create sequence member_no_seq start with 1 increment by 1;

select * from member;

--insert into member values ( member_no_seq.nextval, 'memid','mempw','memName','memPhone');
insert into member values ( member_no_seq.nextval, 'seol1','seol1','seoldong1','111');
delete from member where memId = 'seol3' and memPw = 'seol3';
select * from book bk join library lb on lb.isbn = bk.isbn right join member mb 
on lb.borrowmemid = mb.memid or lb.reserveMemid = mb.memid where memId = 'seol3';
select * from book bk join library lb on lb.isbn = bk.isbn right join member mb 
on mb.memid in(lb.borrowmemid,lb.reserveMemid) where memId = 'seol3' order by returnDate;

select * from book;

commit;

rollback;
update Library set borrowState = '' , borrowMemid = '', returnDate = '' where serial = '978897914941801' AND borrowMemid = 'seol3';
--=================================
select * from library;
select * from library lb inner join book bk on lb.isbn = bk.isbn where booktitle like '%������%';
select * from library lb inner join book bk on lb.isbn = bk.isbn where booktitle like '%'������'%';
select serial,booktitle,bookauthor, callnum,booklocation,borrowstate,returndate,reservestate from library lb inner join book bk on lb.isbn = bk.isbn;
update Library set borrowState = '' , borrowMemid = '', returnDate = '' where serial = '978896088372701';

update Library set borrowState = '������' , memId = 'seol1', returnDate = sysdate+1 where serial = '978896088372701';
select sysdate+1 from dual;


select * from library lb join book bk on lb.isbn = bk.isbn;
select * from member mb join library lb on lb.borrowmemid = mb.memid join book bk on lb.isbn = bk.isbn;
select * from Library where serial = '978896088372701' AND reserveMemid is null AND NVL(borrowMemid,0) != 'seol2';
select * from Library where serial = '978896088372702' AND reserveMemid is null AND NVL(borrowMemid,0) != 'seol1';
select * from library where serial = '978896088372702' AND borrowMemid = 'seol2';
select returnDate from library where serial = '978896088372702';
select * from library order by no;
update library set returnDate = (select returnDate from library where serial = '978896088372702')+1 
where serial = '978896088372702' AND borrowMemid = 'seol2' AND reserveState is null; 
update library set returnDate = (select returnDate from library where serial = '978896088372701')+1 
where serial = '978896088372701' AND borrowMemid = 'seol1' AND reserveState is null; 

select * from member mb join library lb on lb.borrowmemid = mb.memid join book bk on lb.isbn = bk.isbn where memId = 'seol1' and memPw = 'seol1';
select * from book bk join library lb on lb.isbn = bk.isbn right join member mb on lb.borrowmemid = mb.memid where memId = 'seol1' and memPw = 'seol1';
select * from member where memId = 'seol1' and memPw = 'seol1';

select LPAD(count(*), 2,'0') as bookCount from library where isbn = '9788960883727';
select LPAD(count(*), 2,'0') as bookCount from library where isbn = '9788979149418';
select LPAD(count(*), 2,'0') as bookCount from library where isbn = '9788997924820';

select LPAD(count(*), 2,'0') as bookCount from library where isbn = '1234';
select LPAD(count(*)+1, 2,'0') as bookCount from library where isbn = '1234';
select * from library lb inner join book bk on lb.isbn = bk.isbn order by lb.no;