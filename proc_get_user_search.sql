DROP PROCEDURE proc_get_user_search;

DELIMITER $$;
CREATE PROCEDURE proc_get_user_search (
	in userId int8,
    in token varchar(1000),
    inout name VARCHAR(400),
    inout profile VARCHAR(10000)
    )
BEGIN
	-- variables
	declare id int8;
	declare done boolean default false ;
    
	-- cursors
    declare search_user 
		cursor for 
			select u.user_id, u.name
				from main.user u
					where u.user_id=userId;
                    
	
                    
	declare search_profile
		cursor for 
			select u.url
				from main.user_picture u 
					where u.user_id=userId
                    and u.type=1
                    and u.status=1;
    
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    open search_user;    
		read_loop:loop    
			fetch search_user into id, name;   
			
			IF done THEN
			  LEAVE read_loop;
			END IF;
            
		end loop;    
    close search_user;
    
      open search_profile;    
		read_loop:loop    
			fetch search_profile into profile;   
			
			IF done THEN
			  LEAVE read_loop;
			END IF;
			
		end loop;    
    close search_profile;
END $$;
DELIMITER ;

commit;
