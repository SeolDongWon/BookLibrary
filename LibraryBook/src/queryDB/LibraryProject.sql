--================================================도서정보
--생성 테이블 도서정보
drop table book;
drop sequence book_no_seq;

create table book(
no number not null, -- 일련번호
ISBN varchar2(20) not null, -- 국제표준도서번호 
bookTitle varchar2(100) not null, -- 책 제목
bookAuthor varchar2(50) not null, -- 작가
bookRelease date not null, -- 책 발행일
bookIntro varchar2(1000), -- 책소개
CONSTRAINT book_isbn_uk unique (isbn), -- 유일키
constraint book_no_pk primary key (no) -- 기본키
);
--생성 시퀀스 일련번호
create sequence book_no_seq start with 1 increment by 1;

select * from book order by no;
--삽입 레코드 도서정보
--insert into book values (book_no_seq.nextval,ISBN,title,bookauthor,bookrelease,bookintro);
insert into book values (book_no_seq.nextval, 9788960883727,'오늘도 개발자가 안 된다고 말했다','김중철,김수지','2021/03/25','“개발하기 싫어서 안 된다고 말하는 게 아니다” 많은 IT 종사자들이 안 된다고 말하는 개발자로 인해 협업에 어려움을 겪는다. 우리는 IT 비전공자로서 소통을 잘하기 위해 개발자의 입장에서 많이 생각하게 됐고, 이 과정을 통해 개발자의 안 된다는 말에 담겨 있는 여러 가지 의미를 깨달았다. 이 책에는 우리의 성장 과정에서 발견한 협업 노하우들을 담아냈다. 개발 언어를 배우기 전에 꼭 먼저 알고 있어야 할 정보들에 대해 알아보자.');
insert into book values (book_no_seq.nextval, 9788979149418, '유지보수하기 어렵게 코딩하는 방법','로에디 그린', '2012/07/16','남들이 쉽게 이해할 수 있는 코드만 작성한다면 자신의 가치를 높일 수 있을까? 제대로 기능은 수행하는데, 남들이 코드를 이해하기 어렵다면? 아마도 주변 사람들은 관련 프로그램을 유지보수하기 위해서 당신만을 찾게 될 것 이다. 이 책의 저자인 로에디 그린은 이런 의문을 시작으로 유지보수하기 어렵게 작성하는 방법들에 대해서 고민하였다. 저자가 제시하는 방법들을 하나하나 따라 하다 보면, 자신의 가치를 높일 수 있는 코딩 방법을 배울 수 있을 것이다.');
insert into book values (book_no_seq.nextval, 9791137227279,'생계형 개발자, SI에서 살아남기','연서은','2020/12/28','대한민국 웹 개발자의 약 70% 정도는 SI에서 일합니다. 생계형 개발자로서의 이야기를 나눕니다. 개발도 좋아하고 개인의 삶도 좋아하는, 어찌보면 개발이 다른 것보다 조금 더 잘하는 일인 직업으로써의 개발자로서의 삶을 생각해 봅니다. 누군가는 해야 하고 누군가는 하고 있는, 그리고 누군가는 하고 싶어 하는 직업에 대해 이야기합니다. 이 글을 읽고 나면 경험이 부족해 어렴풋이 짐작만 했던 SI에 대해서 조금은 더 잘 이해할 수 있습니다.');
insert into book values (book_no_seq.nextval, 9788997924820,'죽을 때까지 코딩하며 사는 법','홍전일','2021/04/12','코로나19를 기점으로 개발자 수요는 폭발적으로 증가했다. 앞으로는 개발자 연령 상한이 높아지고 재택 업무의 비중 또한 코로나19 이전과는 비교할 수 없을 정도로 늘어날 것이다. 점점 평생 코딩에 유리한 환경이 되어가고 있다. 하지만 여러분은 얼마나 준비되어 있나요? 코딩으로 잔뼈가 굵은 사람은 치킨집을 기웃거리기보다, ''죽을 때까지 코딩''하며 사는 것이 맞다. 우리는 코딩 속에서 열정과 행복을 경험해보았기 때문이다. 이 책은 "죽을 때까지 코딩하기", 그 방법을 찾아가는 이야기다.');



--===============================================보유 서적
--생성 테이블 보유서적
drop table Library;
drop sequence Library_no_seq;

create table Library(
no number not null,
isbn not null, -- book에서 가져오는 외래키
serial varchar2(20) not null, --isbn+일련번호, 기본키
callNum varchar2(30) not null, -- 청구기호, 책 위치
bookLocation varchar2(20) not null, --책 위치
borrowState varchar2(20), -- 대출 상태
borrowMemid, -- 대출회원
returnDate date, -- 반납일
reserveState varchar2(20), -- 예약 상태
reserveMemid, -- 예약회원
constraint Library_serial_pk primary key(serial),
constraint Library_isbn_fk foreign key(isbn) references book(isbn),
constraint Library_borrowMemid_fk foreign key(borrowMemid) references member(memid),
constraint Library_reserveMemid_fk foreign key(reserveMemid) references member(memid),
constraint Library_no_uk unique(no), -- 유일키
constraint Library_callNum_uk unique(callNum) -- 유일키
);
create sequence Library_no_seq start with 1 increment by 1;

select * from member;
select * from Library;
select * from library join book using(isbn);
select * from book;
--insert into Library values(bookcase_no_seq.nextval,isbn, 'serial','callNum','bookLocation','borrowState','memid','returnDate','reserveState');
insert into Library values(Library_no_seq.nextval,9788960883727, '978896088372701','004.76-김76ㅇ','3층','','','','','');
insert into Library values(Library_no_seq.nextval,9788960883727, '978896088372702','004.76-김76ㅇ=2','3층','','','','','');
insert into Library values(Library_no_seq.nextval,9788979149418, '978897914941801','005.이쯤','2층','','','','','');
insert into Library values(Library_no_seq.nextval,9788997924820, '978899792482001','005.104-홍74ㅈ','2층','','','','','');
insert into Library values(Library_no_seq.nextval,9791137227279, '979113722727901','005.대충','2층','','','','','');

select * from Library where serial = 978896088372702 AND memid is null;

--=====================================회원
--생성 테이블 회원
drop table member;
drop sequence member_no_seq;

create table member(
no number not null,
memid varchar2(20) not null,
mempw varchar2(20) not null,
memName varchar2(30) not null,
memPhone varchar2(30) not null,
constraint member_memid_pk primary key(memid),
constraint member_no_uk unique(no) -- 유일키
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
select * from library lb inner join book bk on lb.isbn = bk.isbn where booktitle like '%개발자%';
select * from library lb inner join book bk on lb.isbn = bk.isbn where booktitle like '%'개발자'%';
select serial,booktitle,bookauthor, callnum,booklocation,borrowstate,returndate,reservestate from library lb inner join book bk on lb.isbn = bk.isbn;
update Library set borrowState = '' , borrowMemid = '', returnDate = '' where serial = '978896088372701';

update Library set borrowState = '대출중' , memId = 'seol1', returnDate = sysdate+1 where serial = '978896088372701';
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