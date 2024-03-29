start transaction;

use `Acme-Pet`;

revoke all privileges on `Acme-Pet`.* from  'acme-user'@'%' ;
revoke all privileges on `Acme-Pet`.* from  'acme-manager'@'%';

drop user 'acme-user'@'%' ;
drop user 'acme-manager'@'%' ;

drop database `Acme-Pet` ;


commit;