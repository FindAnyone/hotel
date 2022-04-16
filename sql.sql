prompt PL/SQL Developer import file
prompt Created on 2021年11月21日 by 曾曾
set feedback off
set define off
prompt Creating CHECKIN...
create table CHECKIN
(
  checkinid   NUMBER,
  orderid     NUMBER,
  checkname   VARCHAR2(255),
  checkidcard VARCHAR2(255),
  roomid      NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating EMP...
create table EMP
(
  empid    NUMBER,
  empname  VARCHAR2(255),
  gender   NUMBER,
  idcard   VARCHAR2(255),
  empphone VARCHAR2(255),
  empimg   VARCHAR2(255),
  salary   NUMBER(10,2),
  grade    NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating ENGAGE...
create table ENGAGE
(
  engageid  NUMBER not null,
  typeid    NUMBER,
  userid    NUMBER,
  quantity  NUMBER,
  checktime DATE,
  outtime   DATE,
  total     NUMBER(10,2),
  state     NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ENGAGE
  add constraint ENGAGEID primary key (ENGAGEID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating HISTORY_ORDERS...
create table HISTORY_ORDERS
(
  orderid    NUMBER not null,
  typeid     NUMBER,
  roomid     NUMBER,
  userid     NUMBER,
  quantity   NUMBER,
  total      NUMBER(10,2),
  checktime  DATE,
  outtime    DATE,
  cashpledge NUMBER(10,2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating ORDERS...
create table ORDERS
(
  orderid    NUMBER not null,
  typeid     NUMBER,
  roomid     NUMBER,
  userid     NUMBER,
  quantity   NUMBER,
  total      NUMBER(10,2),
  checktime  DATE,
  outtime    DATE,
  cashpledge NUMBER(10,2),
  state      NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ORDERS
  add constraint ORDERID primary key (ORDERID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating POSITION...
create table POSITION
(
  positionid NUMBER not null,
  position   VARCHAR2(255),
  introduce  VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table POSITION
  add constraint POSITIONID primary key (POSITIONID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating ROOM_DETAILET...
create table ROOM_DETAILET
(
  roomid     NUMBER,
  rtypeid    NUMBER,
  roomprice  NUMBER(10,2),
  roomimg    VARCHAR2(255),
  state      VARCHAR2(255),
  roomnumber NUMBER,
  floor      NUMBER,
  area       NUMBER,
  peoplenum  NUMBER,
  facilities VARCHAR2(255),
  remark     VARCHAR2(255),
  window     NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ROOM_DETAILET.roomid
  is '主键';
comment on column ROOM_DETAILET.rtypeid
  is '房间类型';
comment on column ROOM_DETAILET.roomprice
  is '房间价格';
comment on column ROOM_DETAILET.roomimg
  is '房间图片';
comment on column ROOM_DETAILET.state
  is '房间状态';
comment on column ROOM_DETAILET.roomnumber
  is '房间号';
comment on column ROOM_DETAILET.floor
  is '房间楼层';
comment on column ROOM_DETAILET.area
  is '房间面积';
comment on column ROOM_DETAILET.peoplenum
  is '可住人数';
comment on column ROOM_DETAILET.facilities
  is '房间设施';
comment on column ROOM_DETAILET.remark
  is '备注';
comment on column ROOM_DETAILET.window
  is '是否有窗';

prompt Creating ROOM_TYPE...
create table ROOM_TYPE
(
  typeid       NUMBER not null,
  roomtypename VARCHAR2(255),
  introduce    VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column ROOM_TYPE.typeid
  is '房间类别编号';
comment on column ROOM_TYPE.roomtypename
  is '类别名称';
comment on column ROOM_TYPE.introduce
  is '简介';
alter table ROOM_TYPE
  add constraint ROOMTYPEID primary key (TYPEID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating USERS...
create table USERS
(
  userid   NUMBER not null,
  username VARCHAR2(255),
  password VARCHAR2(255),
  grade    NUMBER,
  name     VARCHAR2(255),
  gender   NUMBER,
  phone    VARCHAR2(255),
  birthday DATE,
  idcard   VARCHAR2(255),
  userimg  VARCHAR2(255),
  address  VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table USERS
  add constraint USERID primary key (USERID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for CHECKIN...
alter table CHECKIN disable all triggers;
prompt Disabling triggers for EMP...
alter table EMP disable all triggers;
prompt Disabling triggers for ENGAGE...
alter table ENGAGE disable all triggers;
prompt Disabling triggers for HISTORY_ORDERS...
alter table HISTORY_ORDERS disable all triggers;
prompt Disabling triggers for ORDERS...
alter table ORDERS disable all triggers;
prompt Disabling triggers for POSITION...
alter table POSITION disable all triggers;
prompt Disabling triggers for ROOM_DETAILET...
alter table ROOM_DETAILET disable all triggers;
prompt Disabling triggers for ROOM_TYPE...
alter table ROOM_TYPE disable all triggers;
prompt Disabling triggers for USERS...
alter table USERS disable all triggers;
prompt Deleting USERS...
delete from USERS;
commit;
prompt Deleting ROOM_TYPE...
delete from ROOM_TYPE;
commit;
prompt Deleting ROOM_DETAILET...
delete from ROOM_DETAILET;
commit;
prompt Deleting POSITION...
delete from POSITION;
commit;
prompt Deleting ORDERS...
delete from ORDERS;
commit;
prompt Deleting HISTORY_ORDERS...
delete from HISTORY_ORDERS;
commit;
prompt Deleting ENGAGE...
delete from ENGAGE;
commit;
prompt Deleting EMP...
delete from EMP;
commit;
prompt Deleting CHECKIN...
delete from CHECKIN;
commit;
prompt Loading CHECKIN...
insert into CHECKIN (checkinid, orderid, checkname, checkidcard, roomid)
values (1102, 3088, '222', '222', 14);
insert into CHECKIN (checkinid, orderid, checkname, checkidcard, roomid)
values (1103, 3088, '222', '222', 14);
commit;
prompt 2 records loaded
prompt Loading EMP...
insert into EMP (empid, empname, gender, idcard, empphone, empimg, salary, grade)
values (1022, '如果法国', 2, '45641564', '18178303876', 'http://localhost:8080/hotel//pic/c6d778ad-bf7b-4122-8b79-f0075869f167.jpg', 5555, 3);
insert into EMP (empid, empname, gender, idcard, empphone, empimg, salary, grade)
values (5555, ' 热个', 1, null, null, 'http://localhost:8080/hotel//pic/f547ced4-b327-4ce4-aa75-f2f8b6d5fa62.jpg', null, 3);
insert into EMP (empid, empname, gender, idcard, empphone, empimg, salary, grade)
values (6666, '个人', 1, null, null, 'http://localhost:8080/hotel//pic/c9918307-011b-4f42-99de-97d398a2d60b.jpeg', null, 2);
commit;
prompt 3 records loaded
prompt Loading ENGAGE...
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (2, 1, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (3, 1, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (4, 1, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (5, 1, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (6, 1, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (7, 1, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (8, 2, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (9, 2, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (11, 2, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (12, 2, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (13, 2, 2002, null, null, null, null, null);
insert into ENGAGE (engageid, typeid, userid, quantity, checktime, outtime, total, state)
values (14, 2, 2002, null, null, null, null, null);
commit;
prompt 12 records loaded
prompt Loading HISTORY_ORDERS...
insert into HISTORY_ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge)
values (22, 1, 14, 1061, 2, 120, to_date('24-11-2021', 'dd-mm-yyyy'), to_date('25-11-2021', 'dd-mm-yyyy'), 22);
insert into HISTORY_ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge)
values (9, 1, 14, 1041, 2, 120, to_date('19-11-2021', 'dd-mm-yyyy'), to_date('21-11-2021', 'dd-mm-yyyy'), 100);
insert into HISTORY_ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge)
values (11, 1, 14, 1061, 2, 120, to_date('19-11-2021', 'dd-mm-yyyy'), to_date('22-11-2021', 'dd-mm-yyyy'), 100);
insert into HISTORY_ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge)
values (7, 1, 15, 1041, 2, 120, to_date('19-11-2021', 'dd-mm-yyyy'), to_date('21-11-2021', 'dd-mm-yyyy'), 1111);
insert into HISTORY_ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge)
values (8, 1, 14, 1041, 2, 120, to_date('19-11-2021', 'dd-mm-yyyy'), to_date('21-11-2021', 'dd-mm-yyyy'), 1111);
insert into HISTORY_ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge)
values (10, 1, 14, 1041, 2, 120, to_date('19-11-2021', 'dd-mm-yyyy'), to_date('22-11-2021', 'dd-mm-yyyy'), 100);
commit;
prompt 6 records loaded
prompt Loading ORDERS...
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3090, 1, 14, 1061, 2, 120, to_date('23-11-2021', 'dd-mm-yyyy'), to_date('28-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3093, 1, 11, 1061, 2, 120, to_date('17-11-2021', 'dd-mm-yyyy'), to_date('24-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3091, 1, 15, 1061, 2, 120, to_date('23-11-2021', 'dd-mm-yyyy'), to_date('26-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3092, 1, 10, 1061, 2, 120, to_date('16-11-2021', 'dd-mm-yyyy'), to_date('27-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3094, 1, 12, 1061, 2, 120, to_date('23-11-2021', 'dd-mm-yyyy'), to_date('26-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3095, 1, 13, 1061, 2, 120, to_date('17-11-2021', 'dd-mm-yyyy'), to_date('24-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3096, 1, 16, 1061, 2, 120, to_date('16-11-2021', 'dd-mm-yyyy'), to_date('27-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3097, 2, 6, 1061, 2, 120, to_date('17-11-2021', 'dd-mm-yyyy'), to_date('24-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3098, 1, 17, 1061, 2, 120, to_date('23-11-2021', 'dd-mm-yyyy'), to_date('26-11-2021', 'dd-mm-yyyy'), 0, 1);
insert into ORDERS (orderid, typeid, roomid, userid, quantity, total, checktime, outtime, cashpledge, state)
values (3099, 1, 18, 1061, 2, 120, to_date('23-11-2021', 'dd-mm-yyyy'), to_date('26-11-2021', 'dd-mm-yyyy'), 0, 1);
commit;
prompt 10 records loaded
prompt Loading POSITION...
insert into POSITION (positionid, position, introduce)
values (1, '经理', '22222222');
insert into POSITION (positionid, position, introduce)
values (2, '前台', '2222');
insert into POSITION (positionid, position, introduce)
values (3, '扫地阿姨', '555');
commit;
prompt 3 records loaded
prompt Loading ROOM_DETAILET...
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (10, 1, 120, 'http://localhost:8080/hotel//pic/14f42894-1536-4c51-ae5d-0ced572c8bc4.webp', '1', 402, 4, 22, 2, '独立卫浴,阳台，热水器', null, 1);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (12, 1, 120, 'http://localhost:8080/hotel//pic/424638b4-b0d2-414f-a810-4153108fe85f.webp', '1', 403, 4, 28, 4, '独立卫浴,阳台，热水器', null, 1);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (13, 1, 120, 'http://localhost:8080/hotel//pic/bfeaea09-855c-4600-afee-4562c54d839f.webp', '1', 401, 4, 30, 1, '独立卫浴,阳台，热水器', null, 1);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (15, 1, 120, 'http://localhost:8080/hotel//pic/c5262f51-1eeb-4f8a-8e67-f3de520d28cf.webp', '1', 102, 1, 15, 2, '独立卫浴,阳台，热水器', null, 0);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (5, 4, 200, 'http://localhost:8080/hotel//pic/c5262f51-1eeb-4f8a-8e67-f3de520d28cf.webp', '1', 202, 2, 30, 2, '独立卫浴,阳台，热水器', null, 0);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (6, 2, 230, 'http://localhost:8080/hotel//pic/c5262f51-1eeb-4f8a-8e67-f3de520d28cf.webp', '1', 203, 2, 28, 2, '独立卫浴,阳台，热水器', null, 1);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (11, 1, 120, 'http://localhost:8080/hotel//pic/c5262f51-1eeb-4f8a-8e67-f3de520d28cf.webp', '1', 411, 4, 30, 2, '独立卫浴,阳台，热水器', null, 1);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (14, 1, 120, 'http://localhost:8080/hotel//pic/c5262f51-1eeb-4f8a-8e67-f3de520d28cf.webp', '1', 101, 1, 25, 2, '独立卫浴,阳台，热水器', null, 1);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (16, 1, 120, 'http://localhost:8080/hotel//pic/c5262f51-1eeb-4f8a-8e67-f3de520d28cf.webp', '1', 408, 4, 25, 2, '独立卫浴,阳台，热水器', null, null);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (17, 1, 120, 'http://localhost:8080/hotel//pic/c5262f51-1eeb-4f8a-8e67-f3de520d28cf.webp', '1', 409, 4, 25, 2, '独立卫浴,阳台，热水器', null, null);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (18, 1, 120, 'http://localhost:8080/hotel//pic/c5262f51-1eeb-4f8a-8e67-f3de520d28cf.webp', '1', 405, 4, 25, 2, '独立卫浴,阳台，热水器', null, null);
insert into ROOM_DETAILET (roomid, rtypeid, roomprice, roomimg, state, roomnumber, floor, area, peoplenum, facilities, remark, window)
values (20, 4, 55, 'http://localhost:8080/hotel//pic/9b5fa81d-9338-4873-aafb-4e6a34ffc05b.webp', '1', 501, 55, 55, 55, '55', '55', 1);
commit;
prompt 12 records loaded
prompt Loading ROOM_TYPE...
insert into ROOM_TYPE (typeid, roomtypename, introduce)
values (2, '标准双人间', '房内设两张单人床或一张双人床的叫标准间，这样的房间适合住两位客人和夫妻同住，适合旅游团体住用。');
insert into ROOM_TYPE (typeid, roomtypename, introduce)
values (3, '豪华间', '房内设两张单人床或一张双人床，房间的装修，房内设施比标准间档次' || chr(10) || '高，其价格也比标准间高一些。');
insert into ROOM_TYPE (typeid, roomtypename, introduce)
values (4, '商务间', '房内设两张单人床或一张双人床，房内可以上网，满足商务客人的需求');
insert into ROOM_TYPE (typeid, roomtypename, introduce)
values (5, '双套间', '一般是连通的两个房间。一间是会客室，一间是卧室。卧室内设两张单人床或一张双人床。这样的房间适合夫妻或旅游团住用。');
insert into ROOM_TYPE (typeid, roomtypename, introduce)
values (1, '标准单人间', '一般是连通的两个房间。一间是会客室，一间是卧室。卧室内设两张单人床或一张双人床。这样的房间适合夫妻或旅游团住用。');
commit;
prompt 5 records loaded
prompt Loading USERS...
insert into USERS (userid, username, password, grade, name, gender, phone, birthday, idcard, userimg, address)
values (2002, 'zhangsan', 'mlq123456', 2, '张三', 2, '18178303877', to_date('09-11-2021', 'dd-mm-yyyy'), '450329200101211958', 'http://localhost:8080/hotel//pic/ca4142c9-5996-4222-bf97-37fea2f03cef.jpg', '广西省桂林市资源县');
insert into USERS (userid, username, password, grade, name, gender, phone, birthday, idcard, userimg, address)
values (1001, 'admin', 'msy123456', 1, 'Admin', 1, null, null, null, 'http://localhost:8080/hotel//pic/9b5fa81d-9338-4873-aafb-4e6a34ffc05b.webp', null);
insert into USERS (userid, username, password, grade, name, gender, phone, birthday, idcard, userimg, address)
values (1061, '辅导费', '123456', null, '曾凡经', null, '18178303876', null, '45000000', 'http://localhost:8080/hotel//pic/e06bab36-7fd9-411e-955e-5731e3ebd8ff.jfif', null);
commit;
prompt 3 records loaded
prompt Enabling triggers for CHECKIN...
alter table CHECKIN enable all triggers;
prompt Enabling triggers for EMP...
alter table EMP enable all triggers;
prompt Enabling triggers for ENGAGE...
alter table ENGAGE enable all triggers;
prompt Enabling triggers for HISTORY_ORDERS...
alter table HISTORY_ORDERS enable all triggers;
prompt Enabling triggers for ORDERS...
alter table ORDERS enable all triggers;
prompt Enabling triggers for POSITION...
alter table POSITION enable all triggers;
prompt Enabling triggers for ROOM_DETAILET...
alter table ROOM_DETAILET enable all triggers;
prompt Enabling triggers for ROOM_TYPE...
alter table ROOM_TYPE enable all triggers;
prompt Enabling triggers for USERS...
alter table USERS enable all triggers;
set feedback on
set define on
prompt Done.
