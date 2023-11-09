-- 테이블과 시퀀스 삭제
execute pro_drop_booklibrary;
-- 테이블과 시퀀스 생성
execute pro_create_booklibrary;
-- 테이블에 기본적인 레코드 삽입
execute pro_in_booklibrary;
-- 프로시저 생성
execute pro_protri_booklibrary;





--===================== drop 테이블&시퀀스=============================
--===================== drop 테이블&시퀀스=============================
--===================== drop 테이블&시퀀스=============================
execute pro_drop_booklibrary;
create or replace procedure pro_drop_booklibrary
is
begin
    execute immediate 'drop table Library';
    execute immediate 'drop sequence Library_no_seq';
    execute immediate 'drop table book';
    execute immediate 'drop sequence book_no_seq';
    execute immediate 'drop table member';
    execute immediate 'drop sequence member_no_seq';
end;
/
show error;

--===================== create 테이블&시퀀스=============================
--===================== create 테이블&시퀀스=============================
--===================== create 테이블&시퀀스=============================
execute pro_create_booklibrary;
create or replace procedure pro_create_booklibrary
is
begin
    --생성 테이블 회원
    execute immediate 'create table member(
    no number not null,
    memid varchar2(20) not null,
    mempw varchar2(20) not null,
    memName varchar2(30) not null,
    memPhone varchar2(30) not null,
    constraint member_memid_pk primary key(memid),
    constraint member_no_uk unique(no) -- 유일키
    )';
    --생성 시퀀스 일련번호
    execute immediate 'create sequence member_no_seq start with 1 increment by 1';
    
    execute immediate 'create table book(
    no number not null, -- 일련번호
    ISBN varchar2(20) not null, -- 국제표준도서번호 
    bookTitle varchar2(100) not null, -- 책 제목
    bookAuthor varchar2(50) not null, -- 작가
    bookRelease date not null, -- 책 발행일
    bookIntro varchar2(1000), -- 책소개
    CONSTRAINT book_isbn_uk unique (isbn), -- 유일키
    constraint book_no_pk primary key (no) -- 기본키
    )';
    --생성 시퀀스 일련번호
    execute immediate 'create sequence book_no_seq start with 1 increment by 1';

    --생성 테이블 보유서적
    execute immediate 'create table Library(
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
    )';
    --생성 시퀀스 일련번호
    execute immediate 'create sequence Library_no_seq start with 1 increment by 1';
end;
/
show error;

--================================ insert 레코드=============================
--================================ insert 레코드=============================
--================================ insert 레코드=============================
select * from book order by no;
select * from Library;
select * from member;

execute pro_in_booklibrary;
create or replace procedure pro_in_booklibrary
is
begin
    --삽입 레코드 회원 정보
    --insert into member values ( member_no_seq.nextval, 'memid','mempw','memName','memPhone');
    insert into member values ( member_no_seq.nextval, 'seol1','seol1','seoldong1','111');
    insert into member values ( member_no_seq.nextval, 'seol2','seol2','seoldong2','2222');
    
    --삽입 레코드 도서 정보
    --insert into book values (book_no_seq.nextval,ISBN,title,bookauthor,bookrelease,bookintro);
    insert into book values (book_no_seq.nextval, 9788960883727,'오늘도 개발자가 안 된다고 말했다','김중철,김수지','2021/03/25','“개발하기 싫어서 안 된다고 말하는 게 아니다” 많은 IT 종사자들이 안 된다고 말하는 개발자로 인해 협업에 어려움을 겪는다. 우리는 IT 비전공자로서 소통을 잘하기 위해 개발자의 입장에서 많이 생각하게 됐고, 이 과정을 통해 개발자의 안 된다는 말에 담겨 있는 여러 가지 의미를 깨달았다. 이 책에는 우리의 성장 과정에서 발견한 협업 노하우들을 담아냈다. 개발 언어를 배우기 전에 꼭 먼저 알고 있어야 할 정보들에 대해 알아보자.');
    insert into book values (book_no_seq.nextval, 9788979149418, '유지보수하기 어렵게 코딩하는 방법','로에디 그린', '2012/07/16','남들이 쉽게 이해할 수 있는 코드만 작성한다면 자신의 가치를 높일 수 있을까? 제대로 기능은 수행하는데, 남들이 코드를 이해하기 어렵다면? 아마도 주변 사람들은 관련 프로그램을 유지보수하기 위해서 당신만을 찾게 될 것 이다. 이 책의 저자인 로에디 그린은 이런 의문을 시작으로 유지보수하기 어렵게 작성하는 방법들에 대해서 고민하였다. 저자가 제시하는 방법들을 하나하나 따라 하다 보면, 자신의 가치를 높일 수 있는 코딩 방법을 배울 수 있을 것이다.');
    insert into book values (book_no_seq.nextval, 9791137227279,'생계형 개발자, SI에서 살아남기','연서은','2020/12/28','대한민국 웹 개발자의 약 70% 정도는 SI에서 일합니다. 생계형 개발자로서의 이야기를 나눕니다. 개발도 좋아하고 개인의 삶도 좋아하는, 어찌보면 개발이 다른 것보다 조금 더 잘하는 일인 직업으로써의 개발자로서의 삶을 생각해 봅니다. 누군가는 해야 하고 누군가는 하고 있는, 그리고 누군가는 하고 싶어 하는 직업에 대해 이야기합니다. 이 글을 읽고 나면 경험이 부족해 어렴풋이 짐작만 했던 SI에 대해서 조금은 더 잘 이해할 수 있습니다.');
    insert into book values (book_no_seq.nextval, 9788997924820,'죽을 때까지 코딩하며 사는 법','홍전일','2021/04/12','코로나19를 기점으로 개발자 수요는 폭발적으로 증가했다. 앞으로는 개발자 연령 상한이 높아지고 재택 업무의 비중 또한 코로나19 이전과는 비교할 수 없을 정도로 늘어날 것이다. 점점 평생 코딩에 유리한 환경이 되어가고 있다. 하지만 여러분은 얼마나 준비되어 있나요? 코딩으로 잔뼈가 굵은 사람은 치킨집을 기웃거리기보다, ''죽을 때까지 코딩''하며 사는 것이 맞다. 우리는 코딩 속에서 열정과 행복을 경험해보았기 때문이다. 이 책은 "죽을 때까지 코딩하기", 그 방법을 찾아가는 이야기다.');
    
    --삽입 레코드 보유도서 정보
    --insert into Library values(bookcase_no_seq.nextval,isbn, 'serial','callNum','bookLocation','borrowState','memid','returnDate','reserveState');
    insert into Library values(Library_no_seq.nextval,9788960883727, '978896088372701','004.76-김76ㅇ','3층','','','','','');
    insert into Library values(Library_no_seq.nextval,9788960883727, '978896088372702','004.76-김76ㅇ=2','3층','','','','','');
    insert into Library values(Library_no_seq.nextval,9788979149418, '978897914941801','005.이쯤','2층','','','','','');
    insert into Library values(Library_no_seq.nextval,9788997924820, '978899792482001','005.104-홍74ㅈ','2층','','','','','');
    insert into Library values(Library_no_seq.nextval,9791137227279, '979113722727901','005.대충','2층','','','','','');

end;
/
show error;

--===============================create 프로시저=============================
--===============================create 프로시저=============================
--===============================create 프로시저=============================
execute pro_protri_booklibrary;
create or replace procedure pro_protri_booklibrary
is
begin
-----------------------------------book 테이블 프로시저----------------------
    -- book 테이블에 책 정보를 삽입하는 프로시저
    execute immediate 'create or replace procedure pro_in_book(
    v_ISBN in book.ISBN%type,
    v_bookTitle in book.bookTitle%type,
    v_bookAuthor in book.bookAuthor%type,
    v_bookRelease in book.bookRelease%type,
    v_bookIntro in book.bookIntro%type,
    v_check out number)
    is
    begin
        insert into book values (book_no_seq.nextval,v_ISBN ,v_bookTitle,v_bookAuthor,v_bookRelease,v_bookIntro);
        select count(*) into v_check
        from book
        where ISBN = v_ISBN;
    end;';  

    -- book 테이블에 책 정보를 수정하는 프로시저
    execute immediate ' create or replace procedure pro_up_book(
        v_ISBN in book.ISBN%type,
        v_bookTitle in book.bookTitle%type,
        v_bookAuthor in book.bookAuthor%type,
        v_bookRelease in book.bookRelease%type,
        v_bookIntro in book.bookIntro%type,
        v_no in book.no%type,
        v_check out number)
    is
    begin
        update book 
        set ISBN=v_ISBN, bookTitle=v_bookTitle ,bookAuthor=v_bookAuthor, 
            bookRelease=v_bookRelease ,bookIntro=v_bookIntro 
        where no=v_no;
        
        select count(*) into v_check from book
        where ISBN=v_ISBN and bookTitle=v_bookTitle and bookAuthor=v_bookAuthor and  
        bookRelease=v_bookRelease and bookIntro=v_bookIntro and no=v_no;
    end;';

    -- book 테이블에 책 정보를 삭제하는 프로시저
    execute immediate ' create or replace procedure pro_del_book(
        v_no in book.no%type,
        v_check1 out number,
        v_check2 out number)
    is
    begin
        select count(*) into v_check1 from book where no = v_no; 
        
        if v_check1 !=0 THEN
            delete from book where no = v_no;
        end if;
        
        select count(*) into v_check2 from book where no = v_no; 
        
    end;';

-----------------------------------library 테이블 프로시저----------------------
    
    --Library 테이블에 레코드 삽입 프로시저
    execute immediate ' create or replace procedure pro_in_library(
        v_isbn in library.isbn%type,
        v_serial in library.serial%type,
        v_callNum in library.callNum%type,
        v_bookLocation in library.bookLocation%type,
        v_check out number)
    is
    begin
        insert into Library(no,isbn,serial,callNum,bookLocation) 
        values(Library_no_seq.nextval,v_isbn,v_serial,v_callNum,v_bookLocation);
        
        select count(*) into v_check from library
        where serial = v_serial;
    end;';
    
    -- 보유도서 카운트 (=레코드 삽입시 일련번호로 사용)
    execute immediate ' create or replace procedure pro_cnt_library(
        v_isbn in library.isbn%type,
        v_cnt out varchar2)
    is
        v_cnt_tmp varchar2(100);
    begin
        select LPAD(count(*)+1, 2,''0'') into v_cnt
        from library where isbn = v_isbn;
        
    end;';
    
    -- Library 테이블에 레코드를 삭제하는 프로시저
    execute immediate ' create or replace procedure pro_del_library(
        v_serial in library.serial%type,
        v_check1 out number,
        v_check2 out number)
    is
    begin
        select count(*) into v_check1 from library where serial = v_serial;
        
        if v_check1 = 1 then
            delete from library where serial = v_serial;
        end if;
        
        select count(*) into v_check2 from library where serial = v_serial;
    end;';
    
    --- 도서 대출 프로시저
    execute immediate ' create or replace procedure pro_borrowbook_library(
        v_memid in member.memid%type,
        v_serial in library.serial%type,
        v_check out number)
    is
    begin
        update Library 
        set borrowState = ''대출중'' , 
        borrowMemid = v_memid, 
        returnDate = sysdate+1, 
        reserveState ='', 
        reserveMemid = '' 
        where serial = v_serial;
        
        select count(*) into v_check from library
        where borrowmemid = v_memid and serial = v_serial;
    end;';
    
    -- 도서 반납 프로시저
    execute immediate ' create or replace procedure pro_returnbook_library(
        v_memid in member.memid%type,
        v_serial in library.serial%type,
        v_check out number)
    is
    begin
        update Library 
        set borrowState = null, 
        borrowMemid = null, 
        returnDate = null 
        where serial = v_serial AND borrowMemid = v_memid;
        
        select count(*) into v_check from library
        where serial = v_serial and borrowState is null and borrowMemid is null and returnDate is null;
    end;';
    
    --도서 예약 프로시저
    execute immediate ' create or replace procedure pro_reservebook_library(
        v_memid in member.memid%type,
        v_serial in library.serial%type,
        v_check out number)
    is
    begin
        update Library 
        set reserveState = ''예약중'' , 
        reserveMemid = v_memid 
        where serial = v_serial;
        
        select count(*) into v_check
        from  Library 
        where reserveState = ''예약중'' and reserveMemid = v_memid and serial = v_serial;
    end;';
    
    -- 반납 연기 프로시저
    execute immediate ' create or replace procedure pro_postponebook_library(
        v_memid in member.memid%type,
        v_serial in library.serial%type
        ,v_check1 out number, -- 도서의 일련번호와 대출아이디 확인
        v_check2 out number)
    is
        v_old_returnDate date := sysdate;
        v_new_returnDate date := sysdate;
    begin
        select count(*) into v_check1 from library
        where serial = v_serial AND borrowMemid = v_memid;
        
        if v_check1 =1 then
            select returnDate into v_old_returnDate  
            from library where serial = v_serial;
        
            update library 
            set returnDate = v_old_returnDate+1
            where serial = v_serial AND borrowMemid = v_memid AND reserveState is null;
           
            select returnDate into v_new_returnDate  
            from library where serial = v_serial;  
        end if;
        
        if v_old_returnDate = v_new_returnDate then
            v_check2 := 0;
        else
            v_check2 := 1;
        end if;
    end;';
--------------------member table 프로시저------------------------
    --memeber 테이블에 레코드 삽입
    execute immediate ' create or replace procedure pro_in_member(
        v_memid in member.memid%type,
        v_mempw in member.mempw%type,
        v_memName in member.memName%type,
        v_memPhone in member.memPhone%type,
        v_check out number)
    is
    begin
        insert into member values ( member_no_seq.nextval, v_memid,v_mempw,v_memName,v_memPhone);
        select count(*) into v_check from member where memid = v_memid;
    end;';
    
    --memeber 테이블 레코드 수정
    execute immediate ' create or replace procedure pro_up_member(
        v_memid in member.memid%type,
        v_mempw in member.mempw%type,
        v_memPhone in member.memPhone%type,
        v_check out number)
    is
    begin
        update member set memPw=v_mempw, memPhone=v_memPhone where memId=v_memid;
        select count(*) into v_check
        from member
        where memPw=v_mempw and memPhone=v_memPhone and memId=v_memid;
    end;';
    
    -- memid 중복 체크 
    execute immediate ' create or replace procedure pro_id_member(
        v_memid in member.memid%type
        ,v_check out varchar2)
    is
        v_check_tmp number;
    begin
        --select no
        --select nvl(no,0)
        select nvl(max(no),0)
        into v_check
        from member where memId = v_memid;
    end;';
    
    -- 회원 로그인
    execute immediate ' create or replace procedure pro_login_member(
        v_memid in member.memid%type,
        v_mempw in member.mempw%type,
        v_memNo out member.no%type,
        v_memName out member.memname%type,
        v_memPhone out member.memphone%type,
        v_check out number)
    is
    begin
        select count(*) into v_check from member
        where memId = v_memid and memPw = v_mempw;
        if v_check = 1 then
            select no,memname,memphone into v_memNo,v_memName,v_memPhone
            from member 
            where memId = v_memid and memPw = v_mempw;
        end if;
    end;';
    
    -- 회원 이름
    execute immediate ' create or replace procedure pro_name_member(
        v_memid in member.memid%type,
        v_mempw in member.mempw%type,
        v_memName out member.memname%type,
        v_check out number)
    is
    begin
        select count(*) into v_check 
        from member 
        where memId = v_memid and memPw = v_mempw;
        if v_check=1 then
            select memname into v_memName
            from member 
            where memId = v_memid and memPw = v_mempw;
        end if;
    end;';
    
    --회원 탈퇴
    execute immediate ' create or replace procedure pro_del_member(
        v_memid in member.memid%type,
        v_mempw in member.mempw%type,
        v_check1 out number,
        v_check2 out number)
    is
    begin
        select count(*) into v_check1 
        from member where memId = v_memid and memPw = v_mempw; 
        
        select count(*) into v_check2
        from library lb join member mb 
        on mb.memid in(lb.borrowmemid,lb.reserveMemid)
        where memId = v_memid;
        
        if v_check1 = 1 and v_check2 = 0 then
            delete from member where memId = v_memid and memPw = v_mempw;
        end if; 
    end;';

 end;
/
show error; 

----
