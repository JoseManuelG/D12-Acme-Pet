start transaction;

use `acme-pet`;

revoke all privileges on `acme-pet`.* from  'acme-user'@'%' ;
revoke all privileges on `acme-pet`.* from  'acme-manager'@'%';

drop user 'acme-user'@'%' ;
drop user 'acme-manager'@'%' ;

drop database `acme-pet` ;


commit;