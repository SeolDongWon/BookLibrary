-- ���̺�� ������ ����
execute pro_drop_booklibrary;
-- ���̺�� ������ ����
execute pro_create_booklibrary;
-- ���̺� �⺻���� ���ڵ� ����
execute pro_in_booklibrary;
-- ���ν��� ����
execute pro_protri_booklibrary;





--===================== drop ���̺�&������=============================
--===================== drop ���̺�&������=============================
--===================== drop ���̺�&������=============================
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

--===================== create ���̺�&������=============================
--===================== create ���̺�&������=============================
--===================== create ���̺�&������=============================
execute pro_create_booklibrary;
create or replace procedure pro_create_booklibrary
is
begin
    --���� ���̺� ȸ��
    execute immediate 'create table member(
    no number not null,
    memid varchar2(20) not null,
    mempw varchar2(20) not null,
    memName varchar2(30) not null,
    memPhone varchar2(30) not null,
    constraint member_memid_pk primary key(memid),
    constraint member_no_uk unique(no) -- ����Ű
    )';
    --���� ������ �Ϸù�ȣ
    execute immediate 'create sequence member_no_seq start with 1 increment by 1';
    
    execute immediate 'create table book(
    no number not null, -- �Ϸù�ȣ
    ISBN varchar2(20) not null, -- ����ǥ�ص�����ȣ 
    bookTitle varchar2(100) not null, -- å ����
    bookAuthor varchar2(50) not null, -- �۰�
    bookRelease date not null, -- å ������
    bookIntro varchar2(1000), -- å�Ұ�
    CONSTRAINT book_isbn_uk unique (isbn), -- ����Ű
    constraint book_no_pk primary key (no) -- �⺻Ű
    )';
    --���� ������ �Ϸù�ȣ
    execute immediate 'create sequence book_no_seq start with 1 increment by 1';

    --���� ���̺� ��������
    execute immediate 'create table Library(
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
    )';
    --���� ������ �Ϸù�ȣ
    execute immediate 'create sequence Library_no_seq start with 1 increment by 1';
end;
/
show error;

--================================ insert ���ڵ�=============================
--================================ insert ���ڵ�=============================
--================================ insert ���ڵ�=============================
select * from book order by no;
select * from Library;
select * from member;

execute pro_in_booklibrary;
create or replace procedure pro_in_booklibrary
is
begin
    --���� ���ڵ� ȸ�� ����
    --insert into member values ( member_no_seq.nextval, 'memid','mempw','memName','memPhone');
    insert into member values ( member_no_seq.nextval, 'seol1','seol1','seoldong1','111');
    insert into member values ( member_no_seq.nextval, 'seol2','seol2','seoldong2','2222');
    
    --���� ���ڵ� ���� ����
    --insert into book values (book_no_seq.nextval,ISBN,title,bookauthor,bookrelease,bookintro);
    insert into book values (book_no_seq.nextval, 9788960883727,'���õ� �����ڰ� �� �ȴٰ� ���ߴ�','����ö,�����','2021/03/25','�������ϱ� �Ⱦ �� �ȴٰ� ���ϴ� �� �ƴϴ١� ���� IT �����ڵ��� �� �ȴٰ� ���ϴ� �����ڷ� ���� ������ ������� �޴´�. �츮�� IT �������ڷμ� ������ ���ϱ� ���� �������� ���忡�� ���� �����ϰ� �ư�, �� ������ ���� �������� �� �ȴٴ� ���� ��� �ִ� ���� ���� �ǹ̸� ���޾Ҵ�. �� å���� �츮�� ���� �������� �߰��� ���� ���Ͽ���� ��Ƴ´�. ���� �� ���� ���� �� ���� �˰� �־�� �� �����鿡 ���� �˾ƺ���.');
    insert into book values (book_no_seq.nextval, 9788979149418, '���������ϱ� ��ư� �ڵ��ϴ� ���','�ο��� �׸�', '2012/07/16','������ ���� ������ �� �ִ� �ڵ常 �ۼ��Ѵٸ� �ڽ��� ��ġ�� ���� �� ������? ����� ����� �����ϴµ�, ������ �ڵ带 �����ϱ� ��ƴٸ�? �Ƹ��� �ֺ� ������� ���� ���α׷��� ���������ϱ� ���ؼ� ��Ÿ��� ã�� �� �� �̴�. �� å�� ������ �ο��� �׸��� �̷� �ǹ��� �������� ���������ϱ� ��ư� �ۼ��ϴ� ����鿡 ���ؼ� ����Ͽ���. ���ڰ� �����ϴ� ������� �ϳ��ϳ� ���� �ϴ� ����, �ڽ��� ��ġ�� ���� �� �ִ� �ڵ� ����� ��� �� ���� ���̴�.');
    insert into book values (book_no_seq.nextval, 9791137227279,'������ ������, SI���� ��Ƴ���','������','2020/12/28','���ѹα� �� �������� �� 70% ������ SI���� ���մϴ�. ������ �����ڷμ��� �̾߱⸦ �����ϴ�. ���ߵ� �����ϰ� ������ � �����ϴ�, ����� ������ �ٸ� �ͺ��� ���� �� ���ϴ� ���� �������ν��� �����ڷμ��� ���� ������ ���ϴ�. �������� �ؾ� �ϰ� �������� �ϰ� �ִ�, �׸��� �������� �ϰ� �;� �ϴ� ������ ���� �̾߱��մϴ�. �� ���� �а� ���� ������ ������ ���ǲ�� ���۸� �ߴ� SI�� ���ؼ� ������ �� �� ������ �� �ֽ��ϴ�.');
    insert into book values (book_no_seq.nextval, 9788997924820,'���� ������ �ڵ��ϸ� ��� ��','ȫ����','2021/04/12','�ڷγ�19�� �������� ������ ����� ���������� �����ߴ�. �����δ� ������ ���� ������ �������� ���� ������ ���� ���� �ڷγ�19 �������� ���� �� ���� ������ �þ ���̴�. ���� ��� �ڵ��� ������ ȯ���� �Ǿ�� �ִ�. ������ �������� �󸶳� �غ�Ǿ� �ֳ���? �ڵ����� �ܻ��� ���� ����� ġŲ���� ����Ÿ��⺸��, ''���� ������ �ڵ�''�ϸ� ��� ���� �´�. �츮�� �ڵ� �ӿ��� ������ �ູ�� �����غ��ұ� �����̴�. �� å�� "���� ������ �ڵ��ϱ�", �� ����� ã�ư��� �̾߱��.');
    
    --���� ���ڵ� �������� ����
    --insert into Library values(bookcase_no_seq.nextval,isbn, 'serial','callNum','bookLocation','borrowState','memid','returnDate','reserveState');
    insert into Library values(Library_no_seq.nextval,9788960883727, '978896088372701','004.76-��76��','3��','','','','','');
    insert into Library values(Library_no_seq.nextval,9788960883727, '978896088372702','004.76-��76��=2','3��','','','','','');
    insert into Library values(Library_no_seq.nextval,9788979149418, '978897914941801','005.����','2��','','','','','');
    insert into Library values(Library_no_seq.nextval,9788997924820, '978899792482001','005.104-ȫ74��','2��','','','','','');
    insert into Library values(Library_no_seq.nextval,9791137227279, '979113722727901','005.����','2��','','','','','');

end;
/
show error;

--===============================create ���ν���=============================
--===============================create ���ν���=============================
--===============================create ���ν���=============================
execute pro_protri_booklibrary;
create or replace procedure pro_protri_booklibrary
is
begin
-----------------------------------book ���̺� ���ν���----------------------
    -- book ���̺� å ������ �����ϴ� ���ν���
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

    -- book ���̺� å ������ �����ϴ� ���ν���
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

    -- book ���̺� å ������ �����ϴ� ���ν���
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

-----------------------------------library ���̺� ���ν���----------------------
    
    --Library ���̺� ���ڵ� ���� ���ν���
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
    
    -- �������� ī��Ʈ (=���ڵ� ���Խ� �Ϸù�ȣ�� ���)
    execute immediate ' create or replace procedure pro_cnt_library(
        v_isbn in library.isbn%type,
        v_cnt out varchar2)
    is
        v_cnt_tmp varchar2(100);
    begin
        select LPAD(count(*)+1, 2,''0'') into v_cnt
        from library where isbn = v_isbn;
        
    end;';
    
    -- Library ���̺� ���ڵ带 �����ϴ� ���ν���
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
    
    --- ���� ���� ���ν���
    execute immediate ' create or replace procedure pro_borrowbook_library(
        v_memid in member.memid%type,
        v_serial in library.serial%type,
        v_check out number)
    is
    begin
        update Library 
        set borrowState = ''������'' , 
        borrowMemid = v_memid, 
        returnDate = sysdate+1, 
        reserveState ='', 
        reserveMemid = '' 
        where serial = v_serial;
        
        select count(*) into v_check from library
        where borrowmemid = v_memid and serial = v_serial;
    end;';
    
    -- ���� �ݳ� ���ν���
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
    
    --���� ���� ���ν���
    execute immediate ' create or replace procedure pro_reservebook_library(
        v_memid in member.memid%type,
        v_serial in library.serial%type,
        v_check out number)
    is
    begin
        update Library 
        set reserveState = ''������'' , 
        reserveMemid = v_memid 
        where serial = v_serial;
        
        select count(*) into v_check
        from  Library 
        where reserveState = ''������'' and reserveMemid = v_memid and serial = v_serial;
    end;';
    
    -- �ݳ� ���� ���ν���
    execute immediate ' create or replace procedure pro_postponebook_library(
        v_memid in member.memid%type,
        v_serial in library.serial%type
        ,v_check1 out number, -- ������ �Ϸù�ȣ�� ������̵� Ȯ��
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
--------------------member table ���ν���------------------------
    --memeber ���̺� ���ڵ� ����
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
    
    --memeber ���̺� ���ڵ� ����
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
    
    -- memid �ߺ� üũ 
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
    
    -- ȸ�� �α���
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
    
    -- ȸ�� �̸�
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
    
    --ȸ�� Ż��
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
