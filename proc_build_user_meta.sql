DROP PROCEDURE main.proc_build_user_meta;

DELIMITER $$;
CREATE PROCEDURE main.proc_build_user_meta (
	in userId  bigint, 
    in token VARCHAR(400))
Begin
	-- comment
    insert into user_meta (user_id, token, status, created, updated)  
		values( userId,token,1,sysdate(), sysdate());
end $$;
DELIMITER ;
