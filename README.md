# 게시판 JDBC 실습 프로젝트
## 시작 가이드
### 1. 프로젝트 다운로드
```bash
git clone
```
### 2. mysql jdbc connector 다운로드 및 라이브러리 추가
2-1. 링크의 Platform Independent 다운로드

https://dev.mysql.com/downloads/connector/j/


2-2. Project Structure - Libraries - + - Java - mysql-connector-j-9.0.0.jar 추가

### 4. DB 연결 정보 Properties 파일 생성
🚨 본인이 사용할 정보로 필수적으로 변경해주세요 !
```bash
#!/bin/bash

# Create the resources directory under src
mkdir -p src/resources

# Create the dbconfig.properties file with the specified content
cat <<EOL > src/resources/dbconfig.properties
# 본인 DB URL 입력
url=jdbc:mysql://127.0.0.1:3306/mini_board
# 본인 DB 계정 입력
username=root
# 본인 DB 비밀번호 입력
password=140716
EOL

echo "설정파일 생성 완료 자신이 사용하는 DB 정보로 수정해주세요 !"
```


## 주요 기능
### 게시판 기능
- 게시글 작성
- 게시글 삭제
- 게시글 수정
- 게시글 전체 조회 (목록 조회)
- 게시글 개별 조회 (상세 조회)
- 게시글 제목으로 검색

### 댓글 기능
- 댓글 작성 ( + 대댓글 작성 )
- 댓글 삭제
- 댓글 수정
- 게시글의 전체 댓글 조회

![스크린샷 2024-08-05 오후 2 27 03](https://github.com/user-attachments/assets/fea4412c-6c00-4871-a572-e742fbd6ff8b)

### 1. 댓글 작성
![스크린샷 2024-08-05 오후 2 27 36](https://github.com/user-attachments/assets/8f797c7c-edb5-4737-8d45-eced39d0c84d)

### 2. 게시글의 댓글 전체 조회
![스크린샷 2024-08-05 오후 2 27 46](https://github.com/user-attachments/assets/a10183ea-1e66-48eb-a034-45900f26f07b)


### 3. 댓글 수정
![스크린샷 2024-08-05 오후 2 28 03](https://github.com/user-attachments/assets/951d7914-c624-4fd0-b01b-c2da5114f4ef)

### 4. 댓글 삭제
![스크린샷 2024-08-05 오후 2 28 10](https://github.com/user-attachments/assets/1f370006-2656-46f2-9559-cc3da354a48e)


### 회원 기능
- 회원 가입
- 로그인

##  ERD
![ERD](https://github.com/user-attachments/assets/35c11868-5e3a-43b2-8798-6d43fb2f480e)


###  DB 생성 쿼리
```sql
CREATE TABLE `Comment` (
	comment_id	BIGINT	    PRIMARY KEY    AUTO_INCREMENT,
	board_id	    BIGINT	    NOT NULL,
	member_id	    BIGINT	    NOT NULL,
	parent_id	    BIGINT	    NULL	COMMENT '원래 댓글 번호',
	content	    	TEXT	    NOT NULL,
	created_at		DATETIME	NOT NULL   DEFAULT NOW(),
	modified_at		DATETIME	NOT NULL,
	is_deleted    ENUM('Y', 'N')  NOT NULL DEFAULT 'N'
);

DROP TABLE IF EXISTS `Member`;

CREATE TABLE `Member` (
	member_id    BIGINT       PRIMARY KEY    AUTO_INCREMENT,
	email        VARCHAR(500) NOT NULL COMMENT '로그인 아이디',
	password     VARCHAR(500) NOT NULL,
	nickname     VARCHAR(500) NOT NULL COMMENT '회원 닉네임',
	created_at   DATETIME     NOT NULL     DEFAULT NOW(),
	is_deleted   ENUM('Y', 'N')  NOT NULL DEFAULT 'N'
);

DROP TABLE IF EXISTS `Board`;

CREATE TABLE `Board` (
	board_id	   BIGINT	    PRIMARY KEY    AUTO_INCREMENT,
	member_id	   BIGINT	    NOT NULL,
	title	       VARCHAR(50)	NOT NULL,
	content	   TEXT	        NOT NULL,
	created_at   DATETIME	    NOT NULL     DEFAULT NOW(),
	modified_at  DATETIME	    NOT NULL,
	is_deleted   ENUM('Y', 'N')  NOT NULL DEFAULT 'N'
);

ALTER TABLE Board ADD CONSTRAINT `fk_member_board` FOREIGN KEY (member_id)
	REFERENCES Member(member_id);

ALTER TABLE Comment ADD CONSTRAINT `fk_member_comment` FOREIGN KEY (member_id)
	REFERENCES Member(member_id);

ALTER TABLE Comment ADD CONSTRAINT `fk_board_comment` FOREIGN KEY (board_id)
	REFERENCES Board(board_id);

ALTER TABLE Comment ADD CONSTRAINT `fk_comment_comment` FOREIGN KEY (parent_id)
	REFERENCES Comment(comment_id);
```
